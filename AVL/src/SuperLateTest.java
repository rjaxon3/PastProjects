import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.Assert.*;

/**
 * This is a basic set of unit tests for AVL.
 *
 * Passing these tests doesn't guarantee any grade on these assignments. These
 * student JUnits that we provide should be thought of as a sanity check to
 * help you get started on the homework and writing JUnits in general.
 *
 * We highly encourage you to write your own set of JUnits for each homework
 * to cover edge cases you can think of for each data structure. Your code must
 * work correctly and efficiently in all cases, which is why it's important
 * to write comprehensive tests to cover as many cases as possible.
 *
 * @author Abhinav Vemulapalli
 * @version 1.0
 */
public class SuperLateTest {
    private static final int TIMEOUT = 200;
    private AVL<Integer> tree;
    private AVL<String> stringTree;

    private ArrayList<String> stringValues;
    private ArrayList<Integer> integerValues;
    private ArrayList<Integer> bfs;
    private ArrayList<Integer> heights;

    @Before
    public void setup() {
        tree = new AVL<>();
        stringTree = new AVL<>();
    }

    private void levelOrder(String type) {
        bfs = new ArrayList<>();
        heights = new ArrayList<>();

        if (type.equals("int")) {
            integerValues = new ArrayList<>();
            AVLNode<Integer> intRoot = tree.getRoot();
            Queue<AVLNode<Integer>> q = new LinkedBlockingQueue<>();
            if (intRoot == null) {
                return;
            }
            q.add(intRoot);
            while (!q.isEmpty()) {
                AVLNode<Integer> intCurrent = q.remove();
                if (intCurrent != null) {
                    integerValues.add(intCurrent.getData());
                    heights.add(intCurrent.getHeight());
                    bfs.add(intCurrent.getBalanceFactor());
                    if (intCurrent.getLeft() != null) {
                        q.add(intCurrent.getLeft());
                    }
                    if(intCurrent.getRight() != null) {
                        q.add(intCurrent.getRight());
                    }
                }
            }
        } else {
            stringValues = new ArrayList<>();
            AVLNode<String> stringRoot = stringTree.getRoot();
            Queue<AVLNode<String>> q = new LinkedBlockingQueue<>();
            if (stringRoot == null) {
                return;
            }
            q.add(stringRoot);
            while (!q.isEmpty()) {
                AVLNode<String> stringCurrent = q.remove();
                if (stringCurrent != null) {
                    stringValues.add(stringCurrent.getData());
                    heights.add(stringCurrent.getHeight());
                    bfs.add(stringCurrent.getBalanceFactor());
                    if (stringCurrent.getLeft() != null) {
                        q.add(stringCurrent.getLeft());
                    }
                    if(stringCurrent.getRight() != null) {
                        q.add(stringCurrent.getRight());
                    }
                }
            }
        }
    }

    @Test(timeout=TIMEOUT)
    public void testAddDuplicate() {
        tree.add(5);
        tree.add(5);

        assertEquals(1, tree.size());
        assertEquals((Integer) 5, tree.getRoot().getData());
    }

    @Test(timeout=TIMEOUT)
    public void testConstructor() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        tree = new AVL<>(list);
        assertEquals(6, tree.size());

        levelOrder("int");
        Integer[] values = {4, 2, 5, 1, 3, 6};
        Integer[] heights = {2, 1, 1, 0, 0, 0};
        Integer[] bfs = {0, 0, -1, 0, 0, 0};
        assertArrayEquals(values, this.integerValues.toArray());
        assertArrayEquals(heights, this.heights.toArray());
        assertArrayEquals(bfs, this.bfs.toArray());
    }

    @Test(timeout=TIMEOUT)
    public void testConstructorDuplicate() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(6);

        tree = new AVL<>(list);
        assertEquals(6, tree.size());

        levelOrder("int");
        Integer[] values = {4, 2, 5, 1, 3, 6};
        Integer[] heights = {2, 1, 1, 0, 0, 0};
        Integer[] bfs = {0, 0, -1, 0, 0, 0};
        assertArrayEquals(values, this.integerValues.toArray());
        assertArrayEquals(heights, this.heights.toArray());
        assertArrayEquals(bfs, this.bfs.toArray());
    }

    @Test(timeout=TIMEOUT)
    public void testAddRightLeftRootRotation() {
        tree.add(5);
        tree.add(3);
        tree.add(8);
        tree.add(6);
        tree.add(9);
        tree.add(7);
        assertEquals(6, tree.size());

        levelOrder("int");
        Integer[] values = {6, 5, 8, 3, 7, 9};
        Integer[] heights = {2, 1, 1, 0, 0, 0};
        Integer[] bfs = {0, 1, 0, 0, 0, 0};
        assertArrayEquals(values, this.integerValues.toArray());
        assertArrayEquals(heights, this.heights.toArray());
        assertArrayEquals(bfs, this.bfs.toArray());
    }

    @Test(timeout=TIMEOUT)
    public void testAddLeftRightRootRotation() {
        tree.add(8);
        tree.add(5);
        tree.add(10);
        tree.add(3);
        tree.add(7);
        tree.add(6);
        assertEquals(6, tree.size());

        levelOrder("int");
        Integer[] values = {7, 5, 8, 3, 6, 10};
        Integer[] heights = {2, 1, 1, 0, 0, 0};
        Integer[] bfs = {0, 0, -1, 0, 0, 0};
        assertArrayEquals(values, this.integerValues.toArray());
        assertArrayEquals(heights, this.heights.toArray());
        assertArrayEquals(bfs, this.bfs.toArray());
    }

    @Test(timeout=TIMEOUT)
    public void testAddLeftRootRotation() {
        tree.add(7);
        tree.add(5);
        tree.add(10);
        tree.add(8);
        tree.add(15);
        tree.add(20);
        assertEquals(6, tree.size());

        levelOrder("int");
        Integer[] values = {10, 7, 15, 5, 8, 20};
        Integer[] heights = {2, 1, 1, 0, 0, 0};
        Integer[] bfs = {0, 0, -1, 0, 0, 0};
        assertArrayEquals(values, this.integerValues.toArray());
        assertArrayEquals(heights, this.heights.toArray());
        assertArrayEquals(bfs, this.bfs.toArray());
    }

    @Test(timeout=TIMEOUT)
    public void testAddRightRootRotation() {
        tree.add(10);
        tree.add(15);
        tree.add(7);
        tree.add(6);
        tree.add(8);
        tree.add(5);
        assertEquals(6, tree.size());

        levelOrder("int");
        Integer[] values = {7, 6, 10, 5, 8, 15};
        Integer[] heights = {2, 1, 1, 0, 0, 0};
        Integer[] bfs = {0, 1, 0, 0, 0, 0};
        assertArrayEquals(values, this.integerValues.toArray());
        assertArrayEquals(heights, this.heights.toArray());
        assertArrayEquals(bfs, this.bfs.toArray());
    }

    @Test(timeout=TIMEOUT)
    public void testAddInternalRightLeftRotation() {
        tree.add(15);
        tree.add(10);
        tree.add(40);
        tree.add(5);
        tree.add(25);
        tree.add(60);
        tree.add(65);
        tree.add(55);
        tree.add(50);
        assertEquals(9, tree.size());

        levelOrder("int");
        Integer[] values = {15, 10 , 55, 5, 40, 60, 25, 50, 65};
        Integer[] heights = {3, 1, 2, 0, 1, 1, 0, 0, 0};
        Integer[] bfs = {-1, 1, 0, 0, 0, -1, 0, 0, 0};
        assertArrayEquals(values, this.integerValues.toArray());
        assertArrayEquals(heights, this.heights.toArray());
        assertArrayEquals(bfs, this.bfs.toArray());
    }

    @Test(timeout=TIMEOUT)
    public void testAddInternalLeftRightRotation() {
        tree.add(15);
        tree.add(10);
        tree.add(40);
        tree.add(5);
        tree.add(45);
        tree.add(30);
        tree.add(25);
        tree.add(35);
        tree.add(36);

        assertEquals(9, tree.size());

        levelOrder("int");
        Integer[] values = {15, 10, 35, 5, 30, 40, 25, 36, 45};
        Integer[] heights = {3, 1, 2, 0, 1, 1, 0, 0, 0};
        Integer[] bfs = {-1, 1, 0, 0, 1, 0, 0, 0, 0};
        assertArrayEquals(values, this.integerValues.toArray());
        assertArrayEquals(heights, this.heights.toArray());
        assertArrayEquals(bfs, this.bfs.toArray());
    }

    @Test(timeout=TIMEOUT)
    public void testRemoveRootLeftRotation() {
        tree.add(15);
        tree.add(10);
        tree.add(40);
        tree.add(5);
        tree.add(45);
        tree.add(30);
        tree.add(25);
        tree.add(35);
        tree.add(36);
        assertEquals(9, tree.size());

        tree.remove(5);
        assertEquals(8, tree.size());
        levelOrder("int");
        Integer[] values = {35, 15, 40, 10, 30, 36, 45, 25};
        Integer[] heights = {3, 2, 1, 0, 1, 0, 0, 0};
        Integer[] bfs = {1, -1, 0, 0, 1, 0, 0, 0};
        assertArrayEquals(values, this.integerValues.toArray());
        assertArrayEquals(heights, this.heights.toArray());
        assertArrayEquals(bfs, this.bfs.toArray());
    }

    @Test(timeout=TIMEOUT)
    public void testRemoveRootLeftRightRotation() {
        tree.add(15);
        tree.add(10);
        tree.add(40);
        tree.add(5);
        tree.add(45);
        tree.add(30);
        tree.add(25);
        tree.add(35);
        tree.add(36);
        assertEquals(9, tree.size());

        tree.remove(5);
        tree.remove(36);
        tree.remove(45);
        assertEquals(6, tree.size());

        levelOrder("int");
        Integer[] values = {30, 15, 35, 10, 25, 40};
        Integer[] heights = {2, 1, 1, 0, 0, 0};
        Integer[] bfs = {0, 0, -1, 0, 0, 0};
        assertArrayEquals(values, this.integerValues.toArray());
        assertArrayEquals(heights, this.heights.toArray());
        assertArrayEquals(bfs, this.bfs.toArray());
    }

    @Test(timeout=TIMEOUT)
    public void testRemoveInternalRightRotation() {
        tree.add(15);
        tree.add(10);
        tree.add(40);
        tree.add(5);
        tree.add(25);
        tree.add(60);
        tree.add(65);
        tree.add(55);
        tree.add(50);
        assertEquals(9, tree.size());

        tree.remove(65);
        tree.remove(60);
        assertEquals(7, tree.size());

        levelOrder("int");
        Integer[] values = {15, 10, 40, 5, 25, 55, 50};
        Integer[] heights = {3, 1, 2, 0, 0, 1, 0};
        Integer[] bfs = {-1, 1, -1, 0, 0, 1, 0};
        assertArrayEquals(values, this.integerValues.toArray());
        assertArrayEquals(heights, this.heights.toArray());
        assertArrayEquals(bfs, this.bfs.toArray());
    }

    @Test(timeout=TIMEOUT)
    public void testRemoveInternalRightLeftRotation() {
        tree.add(15);
        tree.add(10);
        tree.add(40);
        tree.add(5);
        tree.add(25);
        tree.add(60);
        tree.add(65);
        tree.add(55);
        tree.add(50);
        assertEquals(9, tree.size());

        tree.remove(65);
        tree.remove(60);
        tree.remove(25);
        assertEquals(6, tree.size());
        levelOrder("int");
        Integer[] values = {15, 10, 50, 5, 40, 55};
        Integer[] heights = {2, 1, 1, 0, 0, 0};
        Integer[] bfs = {0, 1, 0, 0, 0, 0};
        assertArrayEquals(values, this.integerValues.toArray());
        assertArrayEquals(heights, this.heights.toArray());
        assertArrayEquals(bfs, this.bfs.toArray());
    }

    @Test(timeout=TIMEOUT)
    public void testEqualsUsageAndSameObjectReturn() {
        String temp = "a";
        stringTree.add(temp);

        assertEquals(temp, stringTree.get("a"));
        assertTrue(stringTree.contains("a"));
        assertEquals(temp, stringTree.remove("a"));
    }

    @Test(timeout=TIMEOUT)
    public void testCompareToUsage() {
        stringTree.add("a");
        stringTree.add("b");

        levelOrder("string");
        String[] valuesArray = {"a", "b"};
        Integer[] heightsArray = {1, 0};
        Integer[] bfArray = {-1, 0};

        assertArrayEquals(valuesArray, this.stringValues.toArray());
        assertArrayEquals(heightsArray, this.heights.toArray());
        assertArrayEquals(bfArray, this.bfs.toArray());

        stringTree.add("c");

        levelOrder("string");
        valuesArray = new String[]{"b", "a", "c"};
        heightsArray = new Integer[]{1, 0, 0};
        bfArray = new Integer[]{0, 0, 0};
        assertArrayEquals(valuesArray, this.stringValues.toArray());
        assertArrayEquals(heightsArray, this.heights.toArray());
        assertArrayEquals(bfArray, this.bfs.toArray());

        stringTree.add("d");

        levelOrder("string");
        valuesArray = new String[]{"b", "a", "c", "d"};
        heightsArray = new Integer[]{2, 0, 1, 0};
        bfArray = new Integer[]{-1, 0, -1, 0};
        assertArrayEquals(valuesArray, this.stringValues.toArray());
        assertArrayEquals(heightsArray, this.heights.toArray());
        assertArrayEquals(bfArray, this.bfs.toArray());
    }

    @Test(timeout=TIMEOUT)
    public void testEmptyGetHeight() {
        assertEquals(-1, tree.height());
    }

    @Test(timeout=TIMEOUT)
    public void testConstructorException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new AVL<Integer>(null));
        assertNotNull(exception.getMessage());

        List<Integer> test = new ArrayList<>();
        test.add(1);
        test.add(null);
        test.add(2);

        exception = assertThrows(IllegalArgumentException.class, () -> new AVL<Integer>(test));
        assertNotNull(exception.getMessage());
    }

    @Test(timeout=TIMEOUT)
    public void testAddException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> tree.add(null));
        assertNotNull(exception.getMessage());
    }

    @Test(timeout=TIMEOUT)
    public void testRemoveException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> tree.remove(null));
        assertNotNull(exception.getMessage());

        exception = assertThrows(NoSuchElementException.class, () -> tree.remove(2));
        assertNotNull(exception.getMessage());

    }

    @Test(timeout=TIMEOUT)
    public void testGetException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> tree.get(null));
        assertNotNull(exception.getMessage());

        exception = assertThrows(NoSuchElementException.class, () -> tree.get(2));
        assertNotNull(exception.getMessage());
    }

    @Test(timeout=TIMEOUT)
    public void testContainsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> tree.contains(null));
        assertNotNull(exception.getMessage());
    }

    @Test(timeout=TIMEOUT)
    public void testSortedInBetweenException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> tree.sortedInBetween(null, 2));
        assertNotNull(exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> tree.sortedInBetween(1, null));
        assertNotNull(exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> tree.sortedInBetween(3, 2));
        assertNotNull(exception.getMessage());
    }

}