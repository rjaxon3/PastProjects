import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Your implementation of a MaxHeap.
 *
 * @author Rhea Jaxon
 * @version 1.0
 * @userid rjaxon3
 * @GTID 903760234
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class MaxHeap<T extends Comparable<? super T>> {

    /*
     * The initial capacity of the MaxHeap when created with the default
     * constructor.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new MaxHeap.
     *
     * The backing array should have an initial capacity of INITIAL_CAPACITY.
     */
    public MaxHeap() {
        T[] temp = (T[]) new Comparable[INITIAL_CAPACITY];
        backingArray = (T[]) temp;
        size = 0;
    }

    /**
     * Creates a properly ordered heap from a set of initial values.
     *
     * You must use the BuildHeap algorithm that was taught in lecture! Simply
     * adding the data one by one using the add method will not get any credit.
     * As a reminder, this is the algorithm that involves building the heap
     * from the bottom up by repeated use of downHeap operations.
     *
     * Before doing the algorithm, first copy over the data from the
     * ArrayList to the backingArray (leaving index 0 of the backingArray
     * empty). The data in the backingArray should be in the same order as it
     * appears in the passed in ArrayList before you start the BuildHeap
     * algorithm.
     *
     * The backingArray should have capacity 2n + 1 where n is the
     * number of data in the passed in ArrayList (not INITIAL_CAPACITY).
     * Index 0 should remain empty, indices 1 to n should contain the data in
     * proper order, and the rest of the indices should be empty.
     *
     * Consider how to most efficiently determine if the list contains null data.
     *
     * @param data a list of data to initialize the heap with
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public MaxHeap(ArrayList<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("The list entered is null!");
        }
        backingArray = (T[]) new Comparable[(data.size() * 2) + 1];
        size = data.size();
        int index = 1;
        for (T curr : data) {
            if (curr == null) {
                throw new IllegalArgumentException("List has data passed in that is null!");
            }
            backingArray[index++] = curr;
        }
        for (int i = (size / 2); i >= 1; i--) {
            heapHelper(i);
        }
    }

    /**
     * Adds the data to the heap.
     *
     * If sufficient space is not available in the backing array (the backing
     * array is full except for index 0), resize it to double the current
     * length.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data entered is null!");
        }
        if (size == backingArray.length - 1) {
            T[] arr = (T[]) new Comparable[backingArray.length * 2];
            for (int i = 1; i <= size; i++) {
                arr[i] = backingArray[i];
            }
            backingArray = arr;
        }
        backingArray[size + 1] = data;
        int j = size + 1;
        while (j > 1 && backingArray[j / 2].compareTo(data) < 0) {
            backingArray[j] = backingArray[j / 2];
            backingArray[j / 2] = data;
            j /= 2;
        }
        size++;
    }

    /**
     * Compares parent node to children nodes and swaps them based on data
     *
     * Checks to see if node has any children. If it does, then the method
     * recursively calls down the backingArray
     *
     * Returns if index of node entered has no children
     *
     * @param index of parent node to check order
     */

    private void heapHelper(int index) {
        if (index > size / 2) {
            return;
        }
        T leftChild = backingArray[index * 2];
        T rightChild = null;
        if ((index * 2) + 1 <= size && backingArray[(index * 2) + 1] != null) {
            rightChild = backingArray[(index * 2) + 1];
        }
        T largestChild = leftChild;
        int newIndex = index * 2;
        if (rightChild != null) {
            if (leftChild.compareTo(rightChild) < 0) {
                largestChild = rightChild;
                newIndex = (index * 2) + 1;
            }
        }
        if (backingArray[index].compareTo(largestChild) < 0) {
            backingArray[newIndex] = backingArray[index];
            backingArray[index] = largestChild;
            heapHelper(newIndex);
        }
    }

    /**
     * Removes and returns the root of the heap.
     *
     * Do not shrink the backing array.
     *
     * Replace any unused spots in the array with null.
     *
     * @return the data that was removed
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("The heap is empty so nothing can be removed!");
        }
        T temp = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        size--;
        heapHelper(1);
        return temp;
    }

    /**
     * Returns the maximum element in the heap.
     *
     * @return the maximum element
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T getMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("The heap is empty so no max can be returned!");
        }
        return backingArray[1];
    }

    /**
     * Returns whether or not the heap is empty.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the heap.
     *
     * Resets the backing array to a new array of the initial capacity and
     * resets the size.
     */
    public void clear() {
        T[] temp = (T[]) new Comparable[INITIAL_CAPACITY];
        backingArray = (T[]) temp;
        size = 0;

    }

    /**
     * Returns the backing array of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
