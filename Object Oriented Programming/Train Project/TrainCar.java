/**
 * @author Rhea Jaxon
 * @version 1
 * @param <T> for data that will allow all variable types
 */
public class TrainCar<T> {
    /**
     * Instance variable of the data held in each TrainCar object.
     */
    private T cargo;
    /**
     * Instance variable of the next TrainCar object to form linked list.
     */
    private TrainCar<T> nextCar;
    /**
     * Constructor taking in cargo and nextCar.
     * @param cargo cargo is any type of value that is consistent with the linked list
     * @param nextCar nextCar is the next TrainCar value in the linked list
     * @throws IllegalArgumentException if cargo entered is null
     */
    public TrainCar(T cargo, TrainCar<T> nextCar) {
        if (cargo == null) {
            throw new IllegalArgumentException();
        } else if (nextCar == null) {
            this.nextCar = null;
        }
        this.cargo = cargo;
        this.nextCar = nextCar;
    }
    /**
     * Constructor taking in cargo.
     * @param cargo cargo is any type of value that is consistent with the linked list
     */
    public TrainCar(T cargo) {
        this(cargo, null);
    }
    /**
     * The getCargo() method is a getter method for cargo.
     * @return cargo data in the TrainCar
     */
    public T getCargo() {
        return cargo;
    }
    /**
     * The getNextCar() method is a getter method for the next TrainCar object in
     * the linked list.
     * @return nextCar in the linked list
     */
    public TrainCar<T> getNextCar() {
        return nextCar;
    }
    /**
     * The setNextCar() method is a setter method for the next TrainCar object in
     * the linked list.
     * @param nextCar nextCar is the next TrainCar object in the linked list
     */
    public void setNextCar(TrainCar<T> nextCar) {
        this.nextCar = nextCar;
    }
    /**
     * The setCargo() method is a setter method for the cargo in a TrainCar object.
     * @param cargo of any data type consistent with the linked list
     */
    public void setCargo(T cargo) {
        this.cargo = cargo;
    }
}