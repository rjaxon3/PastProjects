/**
 * @author Rhea Jaxon
 * @version 1
 */
public class Laptop extends Device {
    /**
     * The ability of a device to increase cpuRemaining by
     * adding 1/4 of cpuCapacity
     */
    private boolean overclockable;
    /**
     * Constructor taking in serialNumber, cpuCapacity, length, and overclockable.
     * @param serialNumber serialNumber is the identification number of each device
     * @param cpuCapacity cpuCapacity is the amount of cpu capacity a device has
     * @param length length of task array list
     * @param overclockable boolean expression of if cpuRemaining can be
     * temporarily increased
     */
    public Laptop(int serialNumber, int cpuCapacity, int length, boolean overclockable) {
        super(serialNumber, cpuCapacity, length);
        this.overclockable = overclockable;
    }
    /**
     * Constructor taking in serialNumber, cpuCapacity, and length.
     * @param serialNumber serialNumber is the identification number of each device
     * @param cpuCapacity cpuCapacity is the amount of cpu capacity a device has
     * @param length length of task array list
     */
    public Laptop(int serialNumber, int cpuCapacity, int length) {
        this(serialNumber, cpuCapacity, length, false);
    }
    /**
     * This method bufferSlotsRequired() checks how many open slot spots in tasks[]
     * array should be maintained to maintain proper device function.
     * @param remain remain represents cpuRemaining
     * @return int value of how many slots must be maintained open
     */
    public int bufferSlotsRequired(int remain) {
        if (tasks.length <= 4) {
            return 0;
        } else if (remain < 128) {
            return 2;
        } else {
            return 1;
        }
    }
    /**
     * This method canAddTask() was originally an abstract method of
     * Device class, which is now defined in Laptop class.
     * @param obj obj represents Task object, which will be inputted
     * in order to perform the check if it can be added to tasks[] array
     * @return a boolean expression of whether or not task can be added
     * to tasks[] array
     */
    public boolean canAddTask(Task obj) {
        int count = 0;
        if (!overclockable) {
            for (int i = 0; i < tasks.length; i++) {
                if (tasks[i] == null && cpuRemaining >= obj.cpuCost) {
                    count++;
                }
            }
            if (count >= bufferSlotsRequired(cpuRemaining - obj.cpuCost)) {
                return true;
            }
        } else if (overclockable) {
            for (int i = 0; i < tasks.length; i++) {
                if (tasks[i] == null && cpuRemaining + cpuCapacity / 4 >= obj.cpuCost) {
                    count++;
                }
            }
            if (count >= bufferSlotsRequired(cpuRemaining + cpuCapacity / 4 - obj.cpuCost)) {
                cpuRemaining += cpuCapacity / 4;
                overclockable = false;
                return true;
            }
        }
        return false;
    }
    /**
     * This method addTask() was originally an abstract method of
     * Device class, which is now defined in Laptop class.
     * @param obj obj represents Task object, which will be inputted
     * in order to perform the check if task was added to tasks[] array
     * @return a boolean expression of whether or not task was added
     * to tasks[] array
     */
    public boolean addTask(Task obj) {
        if (canAddTask(obj)) {
            for (int i = 0; i < tasks.length; i++) {
                if (tasks[i] == null) {
                    cpuRemaining -= obj.cpuCost;
                    tasks[i] = obj;
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * This equals() method compares inputted object with a Laptop object.
     * @param other other represents the Object other which will be compared
     * to the Laptop object
     * @return a boolean expression of whether or not the inputted object
     * has identical field values of Laptop object
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (this.getClass().equals(other.getClass())) {
            Laptop obj = (Laptop) other;
            if (super.equals(other) && this.overclockable == obj.overclockable) {
                return true;
            }
        }
        return false;
    }
    /**
     * @return toString() of the instance created in this class
     */
    public String toString() {
        String s = overclockable ? "does" : "does not";
        return super.toString() + " This laptop " + s + " have overclocking.";
    }
}