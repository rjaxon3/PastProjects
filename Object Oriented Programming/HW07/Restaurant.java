/**
 * @author Rhea Jaxon
 * @version 1
 */
public class Restaurant {
    /**
     * This method is a recursive method that utilizes merge sorts to
     * arrange the SushiRoll objects lexicographically.
     * @param roll roll is a SushiRoll object array
     * @return SushiRoll object array that is arranged in ascending
     * order
     */
    public static SushiRoll[] mergeSortRolls(SushiRoll[] roll) {
        if (roll.length <= 1) {
            return roll;
        }
        int mid = roll.length / 2;
        SushiRoll[] left = new SushiRoll[mid];
        SushiRoll[] right = new SushiRoll[roll.length - mid];
        for (int i = 0; i < left.length; i++) {
            left[i] = roll[i];
        }
        int k = 0;
        for (int j = mid; j <= roll.length - 1; j++) {
            right[k] = roll[j];
            k += 1;
        }
        SushiRoll[] arr1 = mergeSortRolls(left);
        SushiRoll[] arr2 = mergeSortRolls(right);
        return MergeHelper.merge(arr1, arr2);
    }
    /**
     * The mergeOrders() method takes a two-dimensional array and returns
     * a one-dimensional array in its place.
     * @param orders orders is a two-dimensional SushiRoll object array
     * @return one-dimnsional SushiRoll array
     */
    public static SushiRoll[] mergeOrders(SushiRoll[][] orders) {
        if (orders.length == 0) {
            return new SushiRoll[0];
        } else if (orders.length == 1) {
            return orders[0];
        } else {
            return MergeHelper.merge(orders[0], mergeOrders(split(orders, 1, orders.length - 1)));
        }
    }
    /**
     * The split() method is a helper method for mergeOrders()
     * that helps split the two-dimensional array.
     * @param roll roll is the SushiRoll array
     * @param beg beg is the start index
     * @param end end is the end index
     * @re
     */
    private static SushiRoll[][] split(SushiRoll[][] roll, int beg, int end) {
        SushiRoll[][] arr = new SushiRoll[end - beg + 1][];
        for (int i = beg; i <= end; i++) {
            arr[i - beg] = roll[i];
        }
        return arr;
    }
    /**
     * The platesOfColor() returns an array of SushiRoll objects that are
     * the same color as the inputted String color.
     * @param roll roll is the SushiRoll array which contains the order of SushiRoll
     * objects
     * @param color color of the SushiRoll objects that need to be returned
     * @return SushiRoll array with corretly chosen SushiRoll objects
     */
    public static SushiRoll[] platesOfColor(SushiRoll[] roll, String color) {
        SushiRoll[] arr = plateHelper(roll, 0, color);
        if (arr.length == 0) {
            return null;
        }
        return plateHelper(roll, 0, color);
    }
    /**
     * The plateHelper() method is a recursive helper method for platesOfColor().
     * It recursively sorts through the array and merges the SushiRoll objects that
     * match the inputted color.
     * @param roll roll is the SushiRoll array which contains the order of SushiRoll
     * objects
     * @param index index of place in array
     * @param color color of the SushiRoll objects that need to be returned
     * @return SushiRoll array with correctly chosen SushiRoll objects
     */
    private static SushiRoll[] plateHelper(SushiRoll[] roll, int index, String color) {
        if (roll.length - 1 < index) {
            return new SushiRoll[0];
        }
        SushiRoll[] arr = new SushiRoll[1];
        if (roll[index].getColor().equals(color)) {
            arr[0] = roll[index];
            return MergeHelper.merge(arr, plateHelper(roll, index + 1, color));
        } else {
            return plateHelper(roll, index + 1, color);
        }
    }
    /**
     * The totalPrice() method returns the total price of the SushiRoll array.
     * @param roll roll is the SushiRoll array which contains the order of
     * SushiRoll objects
     * @return double value of total price of SushiRoll objects
     */
    public static double totalPrice(SushiRoll[] roll) {
        return cashier(roll, 0);
    }
    /**
     * The cashier() method is a recursive helper method for totalPrice().
     * It calculates the total price by recursively adding the price for
     * each index of the inputted array in totalPrice().
     * @param roll roll is a SushiRoll array which contains the order of
     * SushiRoll objects
     * @param index index keeps track of the recursive calls
     * @return a double value of the price of the SushiRoll objects
     */
    private static double cashier(SushiRoll[] roll, int index) {
        if (roll.length - 1 < index) {
            return 0.0;
        }
        double price = 0.0;
        if (roll[index].getColor().equals("Red")) {
            price += 7.00;
        } else if (roll[index].getColor().equals("Green")) {
            price += 6.50;
        } else if (roll[index].getColor().equals("Blue")) {
            price += 6.00;
        } else {
            price += 6.50;
        }
        return price + cashier(roll, index + 1);
    }
    /**
     * This flip() method arranges the SushiRoll array in descending order.
     * @param roll roll is a SushiRoll object array
     */
    public static void flip(SushiRoll[] roll) {
        flipper(roll, 0, roll.length - 1);
    }
    /**
     * The flipper() method is the recursive helper method of flip. It
     * swaps the entries from the end to the beginning of the array.
     * @param roll roll is the SushiRoll array
     * @param beg beg is the start index
     * @param end end is the end index
     * @return SushiRoll array in descending order
     */
    private static SushiRoll[] flipper(SushiRoll[] roll, int beg, int end) {
        if ((end - beg) <= 0) {
            return roll;
        } else {
            SushiRoll temp = roll[beg];
            roll[beg] = roll[end];
            roll[end] = temp;
            return flipper(roll, beg + 1, end - 1);
        }
    }
    /**
     * This method is used to run tests.
     * @param args args are arguments for tests
     */
    public static void main(String[] args) {
        SushiRoll one = new SushiRoll("A", "Red");
        SushiRoll two = new SushiRoll("B", "Red");
        SushiRoll three = new SushiRoll("C", "Red");
        SushiRoll four = new SushiRoll("D", "Green");
        SushiRoll five = new SushiRoll("E", "Blue");
        SushiRoll six = new SushiRoll("F", "Red");
        SushiRoll seven = new SushiRoll("G", "Green");
        SushiRoll eight = new SushiRoll("H", "Red");
        SushiRoll nine = new SushiRoll("I", "Red");
        SushiRoll[] arr1 = new SushiRoll[3];
        arr1[0] = one;
        arr1[1] = two;
        arr1[2] = three;
        SushiRoll[] arr3 = new SushiRoll[9];
        arr3[0] = one;
        arr3[1] = seven;
        arr3[2] = three;
        arr3[3] = five;
        arr3[4] = four;
        arr3[5] = six;
        arr3[6] = two;
        arr3[7] = eight;
        arr3[8] = nine;
        System.out.println("==============TESTS FOR HW07==============\n");
        System.out.println("----------MergeSortRolls() Tests----------");
        System.out.println("Sorted array : ");
        SushiRoll[] arr2 = mergeSortRolls(arr1);
        for (SushiRoll s: arr2) {
            System.out.println(s);
        }
        arr2 = mergeSortRolls(arr3);
        for (SushiRoll s: arr2) {
            System.out.println(s);
        }
        System.out.println("\n----------platesOfColor() Tests----------");
        System.out.println("Array for red sushi rolls: ");
        SushiRoll[] arr4 = platesOfColor(arr3, "Red");
        for (SushiRoll s: arr4) {
            System.out.println(s);
        }
        System.out.println("Array for green sushi rolls: ");
        arr4 = platesOfColor(arr3, "Green");
        for (SushiRoll s: arr4) {
            System.out.println(s);
        }
        System.out.println("Array for blue sushi rolls: ");
        arr4 = platesOfColor(arr3, "Blue");
        for (SushiRoll s: arr4) {
            System.out.println(s);
        }
        System.out.println("Array for yellow sushi rolls: ");
        arr4 = platesOfColor(arr3, "Yellow");
        System.out.println(arr4);
        System.out.println("\n----------totalPrice() Tests----------");
        System.out.println("Price for Sushi Tray 1: " + totalPrice(arr1));
    }
}