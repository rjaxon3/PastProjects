import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.LinkedList;
import java.util.List;

/**
 * Your implementation of various sorting algorithms.
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
public class Sorting {

    /**
     * Implement selection sort.
     *
     * It should be:
     * in-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n^2)
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("The array inputted is empty!");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("The comparator inputted is empty!");
        }
        for (int n = arr.length - 1; n > 0; n--) {
            int max = 0;
            for (int i = 1; i <= n; i++) {
                if (comparator.compare(arr[i], arr[max]) > 0) {
                    max = i;
                }
            }
            swap(n, max, arr);
        }

    }

    /**
     * This method swaps two elements from an array
     * @param first element to be swapped
     * @param second element to be swapped
     * @param arr array the elements are swapped from
     * @param <T> Generic tag to allow swaps made from any type of array
     */
    private static <T> void swap(int first, int second, T[] arr) {
        T data = arr[first];
        arr[first] = arr[second];
        arr[second] = data;
    }

    /**
     * Implement cocktail sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * NOTE: See pdf for last swapped optimization for cocktail sort. You
     * MUST implement cocktail sort with this optimization
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void cocktailSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("The array inputted is empty!");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("The comparator inputted is empty!");
        }
        boolean swapsMade = true;
        int start = 0;
        int end = arr.length - 1;
        int last = 0;
        while (swapsMade) {
            swapsMade = false;
            for (int n = start; n < end; n++) {
                if (comparator.compare(arr[n], arr[n + 1]) > 0) {
                    swap(n, n + 1, arr);
                    swapsMade = true;
                    last = n;
                }
            }
            end = last;
            if (swapsMade) {
                swapsMade = false;
                for (int j = end; j > start; j--) {
                    if (comparator.compare(arr[j - 1], arr[j]) > 0) {
                        swap(j - 1, j, arr);
                        swapsMade = true;
                        last = j;
                    }
                }
                start = last;
            }
        }

    }

    /**
     * Implement merge sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * Hint: If two data are equal when merging, think about which subarray
     * you should pull from first
     *
     * @param <T>        data type to sort
     * @param arr        the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("The array inputted is empty!");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("The comparator inputted is empty!");
        }
        if (arr.length > 1) {
            int mid = (int) (arr.length / 2);
            int left = (int) (arr.length / 2);
            int right = arr.length - left;
            T[] leftarr = (T[]) new Object[left];
            T[] rightarr = (T[]) new Object[right];
            for (int i = 0; i < left; i++) {
                leftarr[i] = arr[i];
            }
            for (int i = left; i < arr.length; i++) {
                rightarr[i - left] = arr[i];
            }
            mergeSort(leftarr, comparator);
            mergeSort(rightarr, comparator);
            int leftindex = 0;
            int rightindex = 0;
            int currentindex = 0;
            while (leftindex < mid && rightindex < arr.length - mid) {
                if (comparator.compare(leftarr[leftindex],
                        rightarr[rightindex]) <= 0) {
                    arr[currentindex] = leftarr[leftindex];
                    leftindex++;
                } else {
                    arr[currentindex] = rightarr[rightindex];
                    rightindex++;
                }
                currentindex++;
            }
            while (leftindex < mid) {
                arr[currentindex] = leftarr[leftindex];
                leftindex++;
                currentindex++;
            }
            while (rightindex < arr.length - mid) {
                arr[currentindex] = rightarr[rightindex];
                rightindex++;
                currentindex++;
            }
        }

    }

    /**
     * Implement kth select.
     *
     * Use the provided random object to select your pivots. For example if you
     * need a pivot between a (inclusive) and b (exclusive) where b > a, use
     * the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
     *
     * If your recursion uses an inclusive b instead of an exclusive one,
     * the formula changes by adding 1 to the nextInt() call:
     *
     * int pivotIndex = rand.nextInt(b - a + 1) + a;
     *
     * It should be:
     * in-place
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * @param <T>        data type to sort
     * @param k          the index to retrieve data from + 1 (due to
     *                   0-indexing) if the array was sorted; the 'k' in "kth
     *                   select"; e.g. if k == 1, return the smallest element
     *                   in the array
     * @param arr        the array that should be modified after the method
     *                   is finished executing as needed
     * @param comparator the Comparator used to compare the data in arr
     * @param rand       the Random object used to select pivots
     * @return the kth smallest element
     * @throws java.lang.IllegalArgumentException if the array or comparator
     *                                            or rand is null or k is not
     *                                            in the range of 1 to arr
     *                                            .length
     */
    public static <T> T kthSelect(int k, T[] arr, Comparator<T> comparator, Random rand) {
        if (arr == null) {
            throw new IllegalArgumentException("The array inputted is empty!");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("The comparator inputted is empty!");
        }
        if (rand == null) {
            throw new IllegalArgumentException("The Random object inputted is empty!");
        }
        if (k < 1) {
            throw new IllegalArgumentException("K cannot be a negative index!");
        }
        if (k > arr.length) {
            throw new IllegalArgumentException("K cannot be an index greater than array length!");
        }
        return kHelper(0, arr.length - 1, arr, k, comparator, rand);
    }

    /**
     * This helper method first sorts and then returns the element at index k
     * @param start the start index for the kSelect
     * @param end the end index for the kSelect
     * @param arr the array the element is chosen from
     * @param k the index to retrieve data from
     * @param comparator comparator object to compare elements
     * @param rand Random object to get a random pivot
     * @param <T> Generic tag to allow array of any elements
     * @return T element from the given k index
     */
    private static <T> T kHelper(int start, int end, T[] arr, int k, Comparator<T> comparator, Random rand) {
        int pivotIdx = rand.nextInt(end - start + 1) + start;
        T pivotVal = arr[pivotIdx];
        swap(start, pivotIdx, arr);
        int i = start + 1;
        int j = end;
        while (i <= j) {
            while (i <= j && comparator.compare(arr[i], pivotVal) <= 0) {
                i++;
            }
            while (i <= j && comparator.compare(arr[j], pivotVal) >= 0) {
                j--;
            }
            if (i <= j) {
                swap(i, j, arr);
                i++;
                j--;
            }
        }
        swap(start, j, arr);
        if (j == k - 1) {
            return arr[j];
        }
        if (j > k - 1) {
            return kHelper(start, j - 1, arr, k, comparator, rand);
        } else {
            return kHelper(j + 1, end, arr, k, comparator, rand);
        }
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(kn)
     *
     * And a best case running time of:
     * O(kn)
     *
     * You are allowed to make an initial O(n) passthrough of the array to
     * determine the number of iterations you need. The number of iterations
     * can be determined using the number with the largest magnitude.
     *
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use ArrayList or LinkedList if you wish, but it may only be
     * used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with other sorts. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * @param arr the array to be sorted
     * @throws java.lang.IllegalArgumentException if the array is null
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null)  {
            throw new IllegalArgumentException("Array must not be null");
        }

        if (arr.length <= 1)    {
            return;
        }

        LinkedList<Integer>[] buckets = new LinkedList[19];
        int max = arr[0];
        int min = arr[0];
        int exp = 1;
        for (int i = 0; i < arr.length; i++)    {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min)   {
                min = arr[i];
            }
        }
        int iterations = 0;
        while (max != 0 || min != 0)   {
            max = max / 10;
            min = min / 10;
            iterations++;
        }

        for (int i = 1; i <= iterations; i++)    {
            for (int j = 0; j <= arr.length - 1; j++)    {
                int bucket = (arr[j] / exp) % 10;
                bucket += 9;
                if (buckets[bucket] == null)    {
                    buckets[bucket] = new LinkedList<Integer>();
                }
                buckets[bucket].add(arr[j]);
            }
            int index = 0;
            for (int bucket = 0; bucket < 19; bucket++)    {
                if (buckets[bucket] == null)    {
                    buckets[bucket] = new LinkedList<Integer>();
                }
                while (!buckets[bucket].isEmpty())  {
                    arr[index] = buckets[bucket].removeFirst();
                    index++;
                }
            }
            exp *= 10;
        }
    }

    /**
     * Implement heap sort.
     *
     * It should be:
     * out-of-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * Use java.util.PriorityQueue as the heap. Note that in this
     * PriorityQueue implementation, elements are removed from smallest
     * element to largest element.
     *
     * Initialize the PriorityQueue using its build heap constructor (look at
     * the different constructors of java.util.PriorityQueue).
     *
     * Return an int array with a capacity equal to the size of the list. The
     * returned array should have the elements in the list in sorted order.
     *
     * @param data the data to sort
     * @return the array with length equal to the size of the input list that
     * holds the elements from the list is sorted order
     * @throws java.lang.IllegalArgumentException if the data is null
     */
    public static int[] heapSort(List<Integer> data) {
        if (data == null) {
            throw new IllegalArgumentException("The inputted data is null!");
        }
        int[] out = new int[data.size()];
        PriorityQueue<Integer> in = new PriorityQueue<>(data);
        for (int i = 0; i < out.length; i++) {
            out[i] = in.poll();
        }
        return out;
    }
}
