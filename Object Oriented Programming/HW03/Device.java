/**
 * @author Rhea Jaxon
 * @version 1
 */
public abstract class Device {
    /**
     * The serial number of each device
     */
    private final int serialNumber;
    /**
     * The cpu capacity for each device
     */
    protected final int cpuCapacity;
    /**
     * The remaining cpu capacity (used once devices are added)
     */
    protected int cpuRemaining;
    /**
     * The array list that holds task objects
     */
    protected Task[] tasks;
    /**
     * Constructor taking in serialNumber, cpuCapacity, and length.
     * @param serialNumber serialNumber is the identification number of each device
     * @param cpuCapacity cpuCapacity is the amount of cpu capacity a device has
     * @param length length of task array list
     */
    public Device(int serialNumber, int cpuCapacity, int length) {
        this.serialNumber = serialNumber;
        this.cpuCapacity = cpuCapacity;
        this.cpuRemaining = cpuCapacity;
        this.tasks = new Task[length];
    }
    /**
     * Constructor taking in serialNumber and length.
     * @param serialNumber serialNumber is the identification number of each device
     * @param length length of task array list
     */
    public Device(int serialNumber, int length) {
        this(serialNumber, 512, length);
        this.cpuRemaining = 512;
    }
    /**
     * abstract method canAddTask(), which will be defined in
     * CellPhone and Laptop class.
     * @param obj obj represent the Task object which is inputted
     * @return a boolean expression of whether or not task can be added
     * to tasks[] array
     */
    public abstract boolean canAddTask(Task obj);
    /**
     * abstract method addTask(), which will be defined in
     * CellPhone and Laptop class.
     * @param obj obj represent the Task object which is inputted
     * @return a boolean expression of whether or not task was added
     * to tasks[] array
     */
    public abstract boolean addTask(Task obj);
    /**
     * This method processTask() removes inputted Task objects if it matches
     * a Task objects already in tasks[] array.
     * @param obj obj represent the Task object which is inputted to
     * check a match
     * @return a boolean expression of whether or not the inputted
     * Task object was found and removed from the tasks[] array
     */
    public boolean processTask(Task obj) {
        if (obj == null) {
            return false;
        }
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] != null) {
                if (tasks[i].equals((Object) obj)) {
                    cpuRemaining += obj.cpuCost;
                    tasks[i] = null;
                    System.out.println("Processed: " + obj.toString());
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * This equals() method compares inputted object with a Device object.
     * @param other other represents the Object other which will be compared
     * to the Device object
     * @return a boolean expression of whether or not the inputted object
     * has identical field values of Device object
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (this.getClass().equals(other.getClass())) {
            Device obj = (Device) other;
            if (this.serialNumber == obj.serialNumber && this.cpuCapacity == obj.cpuCapacity
                && this.cpuRemaining == obj.cpuRemaining) {
                return true;
            }
        }
        return false;
    }
    /**
     * @return toString() of the instance created in this class
     */
    public String toString() {
        String s = "Device with serial number";
        return String.format("%s %d has %d of %d CPU remaining.", s, serialNumber, cpuRemaining, cpuCapacity);
    }
}