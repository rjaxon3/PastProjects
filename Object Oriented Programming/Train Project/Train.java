import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * @author Rhea Jaxon
 * @version 1
 * @param <T> for data that will allow all variable types
 */
public class Train<T> implements List<T> {
    /**
     * Variable of the first TrainCar in the Train linked list
     */
    private TrainCar<T> engine;
    /**
     * Variable for the integer value size of linked list
     */
    private int size;
     /**
     * Constructor taking in no parameters.
     * Sets engine and size to null and 0, respectively.
     */
    public Train() {
        engine = null;
        size = 0;
    }
    /**
     * Constructor taking in an array.
     * @param cargoArray cargoArray is any type of array that will become
     * a Train linked list
     * @throws IllegalArgumentException is cargoArray or any cargo is inputted
     * array is null
     */
    public Train(T[] cargoArray) {
        if (cargoArray == null) {
            throw new IllegalArgumentException("cargoArray cannot be null");
        }
        for (T cargo : cargoArray) {
            if (cargo == null) {
                throw new IllegalArgumentException("cargo cannot be null");
            }
            add(cargo); // keep adding to end
        }
    }
    /**
     * The getEngine() method is a getter method for the variable engine.
     * @return engine variable
     */
    public TrainCar<T> getEngine() {
        return engine;
    }
    /**
     * The toArray() method takes in array and runs a TrainIterator
     * through it.
     * @return array of the linked list the Train class has made
     */
    public T[] toArray() {
        T[] arr = (T[]) new Object[size];
        int i = 0;
        Iterator<T> itr = iterator();
        while (itr.hasNext()) {
            arr[i] = itr.next();
            i++;
        }
        return arr;
    }
    /**
     * This method returns a string value of an array built of the linked list created for the class.
     * @return string value of an array of linked list
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(
                String.format("===== TRAIN %d =====\nisEmpty: %s\nsize: %d\nengine: %s\nCHOO CHOO: [",
                        hashCode(),
                        isEmpty(),
                        size(),
                        (engine == null ? "null" : engine.getCargo())));

        T[] cargo = toArray();
        if (cargo == null) {
            sb.append("TODO: Implement toArray method...");
        } else {
            for (int i = 0; i < cargo.length - 1; ++i) {
                sb.append(String.format("%s, ", cargo[i])); // append all but last value
            }
            if (cargo.length > 0) {
                sb.append(String.format("%s", cargo[cargo.length - 1])); // append last value
            }
        }
        sb.append("]\n============================");
        return sb.toString();
    }
    /**
     * The add() method adds an element to the end of the linked list.
     * @param element element is of any type of value consistent with linked list
     * @throws IllegalArguementException if element entered is null
     */
    @Override
    public void add(T element) throws IllegalArgumentException {
        if (element == null) {
            throw new IllegalArgumentException();
        } else if (engine == null) {
            engine = new TrainCar(element, null);
            size++;
        } else {
            TrainCar<T> node = engine;
            for (int i = 0; i < size - 1; i++) {
                node = node.getNextCar();
            }
            node.setNextCar(new TrainCar(element, null));
            size++;
        }
    }
    /**
     * The add() method adds an element at a certain index of the linked list.
     * @param index index is an integer value of the position the elements needs to be inserted
     * @param element element is of any type of value consistent with linked list
     * @throws IndexOutOfBoundsException if index given is out of range of the current linked list
     * @throws IllegalArgumentException if element entered is null
     */
    @Override
    public void add(int index, T element) throws IndexOutOfBoundsException, IllegalArgumentException {
        TrainCar<T> current = engine;
        if (element == null) {
            throw new IllegalArgumentException();
        } else if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        } else if (engine == null || index == size) {
            add(element);
        } else if (index == 0) {
            TrainCar<T> newEngine = new TrainCar(element, engine);
            engine = newEngine;
            size++;
        } else {
            for (int i = 0; i < index - 1; i++) {
                current = current.getNextCar();
            }
            TrainCar<T> temp = new TrainCar(element, current.getNextCar());
            current.setNextCar(temp);
            size++;
        }
    }
    /**
     * The remove() method removes the first element of the linked list.
     * @return the removed element from the front of the list.
     * @throws NoSuchElementException if the list is empty.
     */
    @Override
    public T remove() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        TrainCar<T> temp = engine;
        engine = engine.getNextCar();
        size--;
        return temp.getCargo();
    }
    /**
     * Removes the element at the specified index and returns it.
     *
     * @param index the index of the element to be removed.
     * @return the removed element at the specified index of the list.
     * @throws NoSuchElementException if the list is empty.
     * @throws IndexOutOfBoundsException if the passed in index is invalid.
     */
    @Override
    public T remove(int index) throws NoSuchElementException, IndexOutOfBoundsException {
        TrainCar<T> current = engine;
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            return remove();
        } else if (index == 1) {
            TrainCar<T> temp = engine.getNextCar();
            engine.setNextCar(temp.getNextCar());
            size--;
            return temp.getCargo();
        } else {
            for (int i = 0; i < index - 1; i++) {
                current = current.getNextCar();
            }
            TrainCar<T> temp = current.getNextCar();
            TrainCar<T> next = temp.getNextCar();
            current.setNextCar(next);
            size--;
            return temp.getCargo();
        }
    }
    /**
     * Removes the first occurrence of the specified element from the list and returns it.
     *
     * @param element the element to be removed.
     * @return the element that is removed from the list. Do not return the passed in element!
     * @throws IllegalArgumentException if the passed in element is null.
     * @throws NoSuchElementException if the passed in element is not in the list.
     */
    @Override
    public T remove(T element) throws IllegalArgumentException, NoSuchElementException {
        TrainCar<T> node = engine;
        TrainCar<T> prev = node;
        if (element == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < size; i++) {
            if (node.getCargo() == element || node.getCargo().equals(element)) {
                return remove(i);
            }
            prev = node;
            node = node.getNextCar();
        }
        throw new NoSuchElementException();
    }
    /**
     * Replaces the element at a specific index with the passed in element.
     *
     * @param index the index of the element to be replaced.
     * @param element the element to replace the existing element at the passed in index with.
     * @return the element that was replaced.
     * @throws IndexOutOfBoundsException if the passed in index is invalid.
     * In the event both arguments are invalid, this exception should be thrown.
     * @throws IllegalArgumentException if the passed in element is null.
     */
    @Override
    public T set(int index, T element) throws IndexOutOfBoundsException, IllegalArgumentException {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException();
        } else if (element == null) {
            throw new IllegalArgumentException();
        }
        TrainCar<T> current = engine;
        for (int i = 0; i < index; i++) {
            current = current.getNextCar();
        }
        TrainCar<T> node = current;
        T oldCargo = node.getCargo();
        node.setCargo(element);
        return oldCargo;
    }
    /**
     * Returns the element at the specified index.
     * You must implement this method using an Iterator!
     *
     * @param index the index of the element to get
     * @return the element at the specified index.
     * @throws IndexOutOfBoundsException if the passed in index is invalid.
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        Iterator<T> itr = iterator();
        for (int i = 0; i < index; i++) {
            itr.next();
        }
        return itr.next();
    }
    /**
     * Checks if the list contains a specific element.
     * You must implement this method using an Iterator!
     *
     * @param element the element to search for in the list.
     * @return whether the list contains the element.
     * @throws IllegalArgumentException if the passed in element is null.
     */
    @Override
    public boolean contains(T element) throws IllegalArgumentException {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        boolean check = false;
        TrainCar<T> node = engine;
        for (int i = 0; i < size; i++) {
            if (node.getCargo().equals(element) || node.getCargo() == element) {
                check = true;
            }
            node = node.getNextCar();
        }
        return check;
    }
    /**
     * Clears the list.
     */
    @Override
    public void clear() {
        size = 0;
        engine = null;
    }
    /**
     * Checks if the list is empty.
     * @return whether the list is empty
     */
    @Override
    public boolean isEmpty() {
        if (engine == null || size == 0) {
            return true;
        }
        return false;
    }
    /**
     * Returns the number of elements in the list.
     * @return the number of elements in the list.
     */
    @Override
    public int size() {
        return size;
    }
    /**
     * This method returns an iterator object.
     * @return iterator object
     */
    @Override
    public Iterator<T> iterator() {
        return new TrainIterator<T>(this);
    }
    /**
     * This method helps test out the methods.
     * @param args arguments for the test
     */
    public static void main(String[] args) {
        System.out.println("==========Testing for HW08==========");
        Train<String> example = new Train<>();
        example.add("One");
        example.add("Two");
        example.add(1, "Three");
        example.add(3, "Four");
        System.out.println("Array size: " + example.size());
        System.out.println("Printing out Array...");
        System.out.println(example.toString());
        example.remove("One");
        System.out.println(example.toString());
        example.remove("Four");
        //example.remove("Five");
        example.set(1, "Two");
        example.get(1);
    }
}
