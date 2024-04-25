import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * @author Rhea Jaxon
 * @version 1
 * @param <T> for data that will allow all variable types
 */
public class TrainIterator<T> implements Iterator<T> {
    /**
     * Instance variable of the next TrainCar object to form linked list.
     */
    private TrainCar<T> nextCar;
    /**
     * Constructor taking in Train object.
     * @param obj obj is a Train Linked List
     * @throws IllegalArgumentException if passed in Train object is null
     */
    public TrainIterator(Train<T> obj) throws IllegalArgumentException {
        if (obj == null) {
            throw new IllegalArgumentException();
        }
        this.nextCar = obj.getEngine();
    }
    /**
     * The hasNext() method checks if the nextCar object in the linked list
     * is ever null.
     * @return boolean value if nextCar object is ever null
     */
    public boolean hasNext() {
        if (nextCar == null) {
            return false;
        }
        return true;
    }
    /**
     * The next() method returns the data in the next TrainCar object in
     * the linked list.
     * @return data of any type consistent with the linked list
     */
    public T next() {
        if (nextCar == null) {
            throw new NoSuchElementException();
        } else {
            T data = nextCar.getCargo();
            nextCar = nextCar.getNextCar();
            return data;
        }
    }
}