/**
 * @author Rhea Jaxon
 * @version 1
 */
public final class Task {
    /**
     * The name of each device.
     */
    public final String name;
    /**
     * The cpu cost of each device.
     */
    public final int cpuCost;
    /**
     * Constructor taking in name and cpuCost.
     * @param name name of each device
     * @param cpuCost cost of cpu for each device
     */
    public Task(String name, int cpuCost) {
        if (name == null) {
            this.name = "GEN_TASK";
        } else {
            this.name = name;
        }
        if (cpuCost < 8) {
            this.cpuCost = 8;
        } else {
            this.cpuCost = cpuCost;
        }
    }
    /**
     * This method equals() compares inputted object with a Task object.
     * @param other other represents the Object other which will be compared
     * to the Task object
     * @return a boolean expression of whether or not the inputted object
     * has identical field values of Task object
     */
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (this.getClass().equals(other.getClass())) {
            Task obj = (Task) other;
            if (this.name.equals(obj.name) && this.cpuCost == obj.cpuCost) {
                return true;
            }
        }
        return false;
    }
    /**
     * @return toString() of the instance created in this class
     */
    public String toString() {
        return String.format("%s has CPU cost of %d", name, cpuCost);
    }
}