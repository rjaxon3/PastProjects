/**
 * @author Rhea Jaxon
 * @version 1
 */
public class RubberDuck {
    /**
     * Enum value used for color of objects
     */
    private final Color color;
    /**
     * Boolean value of whether or not rubber duck wears a hat
     */
    private final boolean hasHat;
    /**
     * Constructor taking in color and hasHat.
     * @param color color is the color of the rubber duck
     * @param hasHat hasHat is the boolean to see if rubber duck wears a hat
     */
    public RubberDuck(Color color, boolean hasHat) {
        if (color == null) {
            this.color = Color.YELLOW;
        } else {
            this.color = color;
        }
        this.hasHat = hasHat;
    }
    /**
     * Constructor taking in no parameters.
     * Sets color to yellow and hasHat to true
     */
    public RubberDuck() {
        this(Color.YELLOW, true);
    }
    /**
     * Accessor method to get color.
     * @return color color of object
     */
    public Color getColor() {
        return color;
    }
    /**
     * Accessor method to see if rubber duck has hat.
     * @return hasHat boolean value if rubber duck has hat
     */
    public boolean getHat() {
        return hasHat;
    }
    /**
     * @return toString() of the instance created in this class
     */
    public String toString() {
        String s = (hasHat) ? "with" : "without";
        return "a " + this.color + " rubber duck " + s + " a hat.";
    }
    /**
     * This equals() method compares inputted object with a RubberDuck object.
     * @param other other represents the Object other which will be compared
     * to the RubberDuck object
     * @return a boolean expression of whether or not the inputted object
     * has identical field values of RubberDuck object
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (this.getClass().equals(other.getClass())) {
            RubberDuck obj = (RubberDuck) other;
            if (this.color == obj.color && this.hasHat == obj.hasHat) {
                return true;
            }
        }
        return false;
    }
}