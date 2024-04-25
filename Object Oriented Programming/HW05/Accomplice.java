/**
 * @author Rhea Jaxon
 * @version 1
 */
public class Accomplice extends PersonOfInterest {
    /**
     * boolean expression of if instance of class can code
     */
    private boolean canCode;
    /**
     * Constructor taking in hairColor, proximity, rubberDuck, and canCode.
     * @param hairColor hairColor is a color value that represents
     * the color of hair the culprit has
     * @param proximity proximity is how close the culprit is
     * @param rubberDuck rubberDuck is a RubberDuck object
     * @param canCode canCode is a boolean expression if culprit can code
     */
    public Accomplice(Color hairColor, int proximity, RubberDuck rubberDuck, boolean canCode) {
        super(hairColor, proximity, rubberDuck);
        this.canCode = canCode;
    }
    /**
     * Accessor method to see if instance can code.
     * @return canCode which is a boolean expression if
     * object can code
     */
    public boolean getCanCode() {
        return canCode;
    }
    /**
     * @return toString() of the instance created in this class
     */
    @Override
    public String toString() {
        String s = (canCode) ? "do" : "do not";
        return "Possible Accomplice: " + super.toString()
            + " They " + s + " have the ability to hack doorbells.";
    }
    /**
     * This equals() method compares inputted object with a Accomplice object.
     * @param other other represents the Object other which will be compared
     * to the Accomplice object
     * @return a boolean expression of whether or not the inputted object
     * has identical field values of Accomplice object
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (this.getClass().equals(other.getClass())) {
            Accomplice obj = (Accomplice) other;
            if (super.equals(other) && this.canCode == obj.canCode) {
                return true;
            }
        }
        return false;
    }
}