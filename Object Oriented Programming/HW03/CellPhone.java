/**
 * @author Rhea Jaxon
 * @version 1
 */
public class CellPhone extends Device {
    /**
     * The number of tasks completed
     */
    private int tasksCompleted = 0;
     /**
     * Constructor taking in serialNumber, cpuCapacity, and length.
     * @param serialNumber serialNumber is the identification number of each device
     * @param cpuCapacity cpuCapacity is the amount of cpu capacity a device has
     * @param length length of task array list
     */
    public CellPhone(int serialNumber, int cpuCapacity, int length) {
        super(serialNumber, cpuCapacity, length);
    }
     /**
     * Constructor taking in serialNumber and cpuCapacity.
     * @param serialNumber serialNumber is the identification number of each device
     * @param cpuCapacity cpuCapacity is the amount of cpu capacity a device has
     */
    public CellPhone(int serialNumber, int cpuCapacity) {
        this(serialNumber, cpuCapacity, 10);
    }
    /**
     * This method canAddTask() was originally an abstract method of
     * Device class, which is now defined in CellPhone class.
     * @param obj obj represents Task object, which will be inputted
     * in order to perform the check if it can be added to tasks[] array
     * @return a boolean expression of whether or not task can be added
     * to tasks[] array
     */
    public boolean canAddTask(Task obj) {
        if (tasks.length <= 0) {
            return false;
        }
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] == null && cpuRemaining >= obj.cpuCost) {
                return true;
            }
        }
        return false;
    }
    /**
     * This method addTask() was originally an abstract method of
     * Device class, which is now defined in CellPhone class.
     * @param obj obj represents Task object, which will be inputted
     * in order to perform the check if task was added to tasks[] array
     * @return a boolean expression of whether or not task was added
     * to tasks[] array
     */
    public boolean addTask(Task obj) {
       if (canAddTask(obj)) {
            for (int i = 0; i < tasks.length; i++) {
                if (tasks[i] == null) {
                    tasks[i] = obj;
                    cpuRemaining -= obj.cpuCost;
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * This method processTask() removes inputted Task objects if it matches
     * a Task objects already in tasks[] array. The tasksCompleted field
     * is also updated here.
     * @param obj obj represent the Task object which is inputted to
     * check a match
     * @return a boolean expression of whether or not the inputted
     * Task object was found and removed from the tasks[] array
     */
    @Override
    public boolean processTask(Task obj) {
        if (super.processTask(obj)) {
            tasksCompleted += 1;
            return true;
        }
        return false;
    }
    /**
     * This equals() method compares inputted object with a CellPhone object.
     * @param other other represents the Object other which will be compared
     * to the Cellphone object
     * @return a boolean expression of whether or not the inputted object
     * has identical field values of CellPhone object
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (this.getClass().equals(other.getClass())) {
            CellPhone obj = (CellPhone) other;
            if (super.equals(other) && this.tasksCompleted == obj.tasksCompleted) {
                return true;
            }
        }
        return false;
    }
    /**
     * @return toString() of the instance created in this class
     */
    public String toString() {
        return super.toString() + " It has completed " + tasksCompleted + " tasks.";
    }
}