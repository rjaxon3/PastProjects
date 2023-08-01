import java.util.NoSuchElementException;

/**
 * Your implementation of a CircularSinglyLinkedList without a tail pointer.
 *
 * @author Rhea Jaxon
 * @version 1.0
 * @userid rjaxon3
 * @GTID 903760234
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 * Used shared JUNITS on github
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class CircularSinglyLinkedList<T> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private CircularSinglyLinkedListNode<T> head;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the data to the specified index.
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * @param index the index at which to add the new data
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            if (index < 0) {
                throw new IndexOutOfBoundsException("The index given is a negative number!");
            } else {
                throw new IndexOutOfBoundsException("The index given is greater than list size!");
            }
        } else if (data == null) {
            throw new IllegalArgumentException("Data inputted is null!");
        }
        if (size == 0) {
            CircularSinglyLinkedListNode<T> temp = new CircularSinglyLinkedListNode<T>(data);
            head = temp;
            head.setNext(head);
        } else if (index == 0) {
            CircularSinglyLinkedListNode<T> temp = new CircularSinglyLinkedListNode<T>(data);
            temp.setNext(head.getNext());
            temp.setData(head.getData());
            head.setNext(temp);
            head.setData(data);
        } else if (index == size) {
            CircularSinglyLinkedListNode<T> temp = new CircularSinglyLinkedListNode<T>(data);
            CircularSinglyLinkedListNode<T> curr = head;
            while (curr.getNext() != head) {
                curr = curr.getNext();
            }
            curr.setNext(temp);
            temp.setNext(head);
        } else {
            CircularSinglyLinkedListNode<T> curr = head;
            CircularSinglyLinkedListNode<T> temp = new CircularSinglyLinkedListNode<T>(data);
            for (int i = 0; i < index - 1; i++) {
                curr = curr.getNext();
            }
            temp.setNext(curr.getNext());
            curr.setNext(temp);
        }
        size++;
    }

    /**
     * Adds the data to the front of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        addAtIndex(0, data);
    }

    /**
     * Adds the data to the back of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        addAtIndex(size, data);
    }

    /**
     * Removes and returns the data at the specified index.
     *
     * Must be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        if (index < 0 || index > size || size == 0) {
            if (index < 0) {
                throw new IndexOutOfBoundsException("The index given is a negative number!");
            } else if (index > size){
                throw new IndexOutOfBoundsException("The index given is greater than list size!");
            } else {
                throw new NoSuchElementException("The list is empty!");
            }
        } else if (size == 1) {
            T data = head.getData();
            clear();
            return data;
        } else if (index == 0) {
            T data = head.getData();
            head.setData(head.getNext().getData());
            head.setNext(head.getNext().getNext());
            if (--size == 0) {
                clear();
            }
            return data;
        } else {
            CircularSinglyLinkedListNode<T> curr = head;
            for (int i = 0; i < index - 1; i++) {
                curr = curr.getNext();
            }
            CircularSinglyLinkedListNode<T> temp = curr.getNext();
            curr.setNext(curr.getNext().getNext());
            if (--size == 0) {
                clear();
            }
            return temp.getData();
        }
    }

    /**
     * Removes and returns the first data of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (size == 0) {
            throw new NoSuchElementException("The list is empty!");
        }
        return removeAtIndex(0);
    }

    /**
     * Removes and returns the last data of the list.
     *
     * Must be O(n).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        if (size == 0) {
            throw new NoSuchElementException("The list is empty!");
        }
        return removeAtIndex(size - 1);
    }

    /**
     * Returns the data at the specified index.
     *
     * Should be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (index < 0 || index > size) {
            if (index < 0) {
                throw new IndexOutOfBoundsException("The index given is a negative number!");
            } else {
                throw new IndexOutOfBoundsException("The index given is greater than list size!");
            }
        } else if (index == 0) {
                return head.getData();
        } else {
                CircularSinglyLinkedListNode<T> curr = head;
                for (int i = 0; i < index; i++) {
                    curr = curr.getNext();
                }
                return curr.getData();
        }
    }


    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Clears the list.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Removes and returns the last copy of the given data from the list.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the list.
     *
     * Must be O(n).
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if data is not found
     */
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data inputted is null!");
        } else {
            CircularSinglyLinkedListNode<T> curr = head;
            CircularSinglyLinkedListNode<T> prev = null;
            for (int i = 0; i < size; i++) {
                if (curr.getNext().getData().equals(data)) {
                    prev = curr;
                }
                curr = curr.getNext();
            }
            if (prev != null) {
                curr = prev.getNext();
                prev.setNext(prev.getNext().getNext());
                if(--size == 0) {
                    clear();
                }
                return curr.getData();
            } else {
                if (head.getData().equals(data)) {
                    return removeAtIndex(0);
                }
            }
        }
        throw new NoSuchElementException("Element could not be found in list!");
    }

    /**
     * Returns an array representation of the linked list.
     *
     * Must be O(n) for all cases.
     *
     * @return the array of length size holding all of the data (not the
     * nodes) in the list in the same order
     */
    public T[] toArray() {
        if (isEmpty()) {
            throw new NullPointerException("There are no elements in the list yet!");
        }
        T[] arr = (T[]) new Object[size];
        CircularSinglyLinkedListNode<T> curr = head;
        arr[0] = curr.getData();
        for (int i = 1; i < size; i++) {
            curr = curr.getNext();
            arr[i] = curr.getData();
        }
        return arr;
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public CircularSinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }
}
