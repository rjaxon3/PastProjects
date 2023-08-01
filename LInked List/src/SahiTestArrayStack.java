import static org.junit.Assert.assertArrayEquals;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

//ADD THIS TO ARRAYSTACKCLASS FOR JUNITS TO WORK PROPERLY. DONT FORGET TO COMMENT IT OUT WHEN DONE.


public class SahiTestArrayStack {
    private static final int TIMEOUT = 200;
    private ArrayStack<String> array;
    private LinkedStack<String> linked;

    @Before
    public void setup() {
        array = new ArrayStack<>();
        linked = new LinkedStack<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, array.size());
        assertArrayEquals(new Object[ArrayStack.INITIAL_CAPACITY],
                array.getBackingArray());
        assertEquals(0, linked.size());
        assertNull(linked.getHead());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testIllegalData() {
        array.push(null);
    }


    @Test(timeout = TIMEOUT)
    public void testArrayPushZeroElements() {
        array.push("a");
        assertEquals(1, array.size());
        Object[] expected = new Object[ArrayStack.INITIAL_CAPACITY];
        expected[0] = "a";
        assertEquals(array.length(), expected.length);
        assertArrayEquals(expected, array.getBackingArray());
    }


    @Test(timeout = TIMEOUT)
    public void testArrayPushOneElement() {
        array.push("a");
        array.push("b");
        assertEquals(2, array.size());
        Object[] expected = new Object[ArrayStack.INITIAL_CAPACITY];
        expected[0] = "a";
        expected[1] = "b";
        assertEquals(array.length(), expected.length);
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPushTwoElements() {
        array.push("a");
        array.push("b");
        array.push("c");
        assertEquals(3, array.size());
        Object[] expected = new Object[ArrayStack.INITIAL_CAPACITY];
        expected[0] = "a";
        expected[1] = "b";
        expected[2] = "c";
        assertEquals(array.length(), expected.length);
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPushCapacityMinusOneElements() {
        array.push("a");
        array.push("b");
        array.push("c");
        array.push("d");
        array.push("e");
        array.push("f");
        array.push("g");
        assertSame("g", array.peek());
        array.push("h");
        assertEquals(8, array.size());
        Object[] expected = new Object[ArrayStack.INITIAL_CAPACITY];
        expected[0] = "a";
        expected[1] = "b";
        expected[2] = "c";
        expected[3] = "d";
        expected[4] = "e";
        expected[5] = "f";
        expected[6] = "g";
        expected[7] = "h";
        assertEquals(array.length(), expected.length);
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPushCapacityNoResize() {
        array.push("a");
        assertSame("a", array.peek());
        array.push("b");
        assertSame("b", array.peek());
        array.push("c");
        array.push("d");
        array.push("e");
        array.push("f");
        array.push("g");
        array.push("h");
        array.push("i");
        assertEquals(9, array.size());
        Object[] expected = new Object[ArrayStack.INITIAL_CAPACITY];
        expected[0] = "a";
        expected[1] = "b";
        expected[2] = "c";
        expected[3] = "d";
        expected[4] = "e";
        expected[5] = "f";
        expected[6] = "g";
        expected[7] = "h";
        expected[8] = "i";
        assertEquals(array.length(), expected.length);
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPushCapacityOneResize() {
        array.push("a");
        array.push("b");
        array.push("c");
        array.push("d");
        array.push("e");
        array.push("f");
        array.push("g");
        assertSame("g", array.peek());
        array.push("h");
        array.push("i");
        array.push("j");
        assertEquals(10, array.size());
        Object[] expected = new Object[ArrayStack.INITIAL_CAPACITY * 2];
        expected[0] = "a";
        expected[1] = "b";
        expected[2] = "c";
        expected[3] = "d";
        expected[4] = "e";
        expected[5] = "f";
        expected[6] = "g";
        expected[7] = "h";
        expected[8] = "i";
        expected[9] = "j";
        assertEquals(array.length(), expected.length);
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPushCapacityTwiceCapacityNoResize() {
        array.push("a");
        array.push("b");
        array.push("c");
        array.push("d");
        assertSame("d", array.peek());
        array.push("e");
        array.push("f");
        array.push("g");
        array.push("h");
        array.push("i");
        array.push("j");
        array.push("k");
        array.push("l");
        array.push("m");
        array.push("n");
        array.push("o");
        assertSame("o", array.peek());
        array.push("p");
        array.push("q");
        array.push("r");
        assertEquals(18, array.size());
        Object[] expected = new Object[ArrayStack.INITIAL_CAPACITY * 2];
        expected[0] = "a";
        expected[1] = "b";
        expected[2] = "c";
        expected[3] = "d";
        expected[4] = "e";
        expected[5] = "f";
        expected[6] = "g";
        expected[7] = "h";
        expected[8] = "i";
        expected[9] = "j";
        expected[10] = "k";
        expected[11] = "l";
        expected[12] = "m";
        expected[13] = "n";
        expected[14] = "o";
        expected[15] = "p";
        expected[16] = "q";
        expected[17] = "r";
        assertEquals(array.length(), expected.length);
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPushCapacityTwiceCapacityResize() {
        array.push("a");
        array.push("b");
        array.push("c");
        array.push("d");
        array.push("e");
        array.push("f");
        array.push("g");
        array.push("h");
        array.push("i");
        array.push("j");
        array.push("k");
        array.push("l");
        array.push("m");
        array.push("n");
        array.push("o");
        array.push("p");
        array.push("q");
        array.push("r");
        array.push("s");
        assertEquals(19, array.size());
        Object[] expected = new Object[(ArrayStack.INITIAL_CAPACITY * 2) * 2];
        expected[0] = "a";
        expected[1] = "b";
        expected[2] = "c";
        expected[3] = "d";
        expected[4] = "e";
        expected[5] = "f";
        expected[6] = "g";
        expected[7] = "h";
        expected[8] = "i";
        expected[9] = "j";
        expected[10] = "k";
        expected[11] = "l";
        expected[12] = "m";
        expected[13] = "n";
        expected[14] = "o";
        expected[15] = "p";
        expected[16] = "q";
        expected[17] = "r";
        expected[18] = "s";
        assertEquals(array.length(), expected.length);
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testArrayPopSizeZero() {
        array.pop();
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPopOneElementSizeOne() {
        array.push("a");
        assertEquals(1, array.size());
        assertSame("a", array.pop());
        assertEquals(0, array.size());
        Object[] expected = new Object[ArrayStack.INITIAL_CAPACITY];
        expected[0] = null;
        assertEquals(array.length(), expected.length);
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPopOneElementSizeTwo() {
        array.push("a");
        array.push("b");
        assertEquals(2, array.size());
        assertSame("b", array.pop());
        assertEquals(1, array.size());
        Object[] expected = new Object[ArrayStack.INITIAL_CAPACITY];
        expected[0] = "a";
        expected[1] = null;
        assertEquals(array.length(), expected.length);
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPopTwoElementsSizeTwo() {
        array.push("a");
        array.push("b");
        assertEquals(2, array.size());
        assertSame("b", array.pop());
        assertSame("a", array.pop());
        assertEquals(0, array.size());
        Object[] expected = new Object[ArrayStack.INITIAL_CAPACITY];
        expected[0] = null;
        expected[1] = null;
        assertEquals(array.length(), expected.length);
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testArrayPopTwoElementsSizeTwoPopAtEnd() {
        array.push("a");
        array.push("b");
        assertEquals(2, array.size());
        assertSame("b", array.pop());
        assertSame("a", array.pop());
        assertEquals(0, array.size());
        array.pop();
        Object[] expected = new Object[ArrayStack.INITIAL_CAPACITY];
        expected[0] = null;
        expected[1] = null;
        assertEquals(array.length(), expected.length);
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPopAtCapacity() {
        array.push("a");
        array.push("b");
        array.push("c");
        array.push("d");
        array.push("e");
        array.push("f");
        array.push("g");
        array.push("h");
        array.push("i");
        assertEquals(9, array.size());
        assertSame("i", array.pop());
        assertEquals(8, array.size());
        Object[] expected = new Object[ArrayStack.INITIAL_CAPACITY];
        expected[0] = "a";
        expected[1] = "b";
        expected[2] = "c";
        expected[3] = "d";
        expected[4] = "e";
        expected[5] = "f";
        expected[6] = "g";
        expected[7] = "h";
        expected[8] = null;
        assertEquals(array.length(), expected.length);
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPopAndPushNoResize() {
        array.push("a"); // a
        array.push("b"); // a b
        assertSame("b", array.peek());
        array.push("c"); // a b c
        assertEquals(3, array.size());
        assertSame("c", array.pop()); // a b 
        assertSame("b", array.pop()); // a
        assertSame("a", array.pop()); //
        assertEquals(0, array.size());
        array.push("d"); // d
        array.push("e"); // d e
        array.push("f"); // d e f
        assertSame("f", array.peek());
        array.push("g"); // d e f g
        array.push("h"); // d e f g h
        assertEquals(5, array.size());
        assertSame("h", array.pop()); // d e f g
        assertEquals(4, array.size());
        array.push("i"); // d e f g i
        assertEquals(5, array.size());
        assertSame("i", array.pop()); // d e f g
        assertEquals(4, array.size());
        Object[] expected = new Object[ArrayStack.INITIAL_CAPACITY];
        expected[0] = "d";
        expected[1] = "e";
        expected[2] = "f";
        expected[3] = "g";
        expected[4] = null;
        expected[5] = null;
        expected[6] = null;
        expected[7] = null;
        expected[8] = null;
        assertEquals(array.length(), expected.length);
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPopAndPushOneResize() {
        array.push("a"); // a
        array.push("b"); // a b
        array.push("c"); // a b c
        assertEquals(3, array.size());
        assertSame("c", array.pop()); // a b 
        assertSame("b", array.pop()); // a
        assertSame("a", array.pop()); //
        assertEquals(0, array.size());
        array.push("d"); // d
        assertSame("d", array.peek());
        array.push("e"); // d e
        array.push("f"); // d e f
        array.push("g"); // d e f g
        assertSame("g", array.peek());
        array.push("h"); // d e f g h
        assertEquals(5, array.size());
        assertSame("h", array.pop()); // d e f g
        assertEquals(4, array.size());
        array.push("i"); // d e f g i
        array.push("j"); // d e f g i j
        assertSame("j", array.peek());
        array.push("k"); // d e f g i j k
        array.push("l"); // d e f g i j k l
        array.push("m"); // d e f g i j k l m
        assertSame("m", array.peek());
        array.push("n"); // d e f g i j k l m n
        assertEquals(10, array.size());
        assertSame("n", array.pop()); // d e f g i j k l m 
        assertEquals(9, array.size());
        Object[] expected = new Object[ArrayStack.INITIAL_CAPACITY * 2];
        expected[0] = "d";
        expected[1] = "e";
        expected[2] = "f";
        expected[3] = "g";
        expected[4] = "i";
        expected[5] = "j";
        expected[6] = "k";
        expected[7] = "l";
        expected[8] = "m";
        expected[9] = null;
        expected[10] = null;
        assertEquals(array.length(), expected.length);
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testArrayPopPeekException() {
        array.push("a");
        array.push("b");
        assertEquals(2, array.size());
        assertSame("b", array.pop());
        assertSame("a", array.pop());
        array.peek();
        assertEquals(0, array.size());
        Object[] expected = new Object[ArrayStack.INITIAL_CAPACITY];
        expected[0] = null;
        expected[1] = null;
        assertEquals(array.length(), expected.length);
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testPeekEmptyList() {
        array.peek();
    }




}