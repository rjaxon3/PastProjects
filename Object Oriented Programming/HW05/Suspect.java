/**
 * @author Rhea Jaxon
 * @version 1
 */
public class Suspect extends PersonOfInterest {
    /**
     * int value of the height of the culprit
     */
    private int height;
    /**
     * Constructor taking in hairColor, proximity, rubberDuck, and height.
     * @param hairColor hairColor is a color value that represents
     * the color of hair the culprit has
     * @param proximity proximity is how close the culprit is
     * @param rubberDuck rubberDuck is a RubberDuck object
     * @param height height of the culprit
     */
    public Suspect(Color hairColor, int proximity, RubberDuck rubberDuck, int height) {
        super(hairColor, proximity, rubberDuck);
        if (height < 150) {
            this.height = 150;
        } else {
            this.height = height;
        }
    }
    /**
     * Constructor taking in proximity and rubberDuck.
     * @param proximity proximity is how close the culprit is
     * @param rubberDuck rubberDuck is a RubberDuck object
     */
    public Suspect(int proximity, RubberDuck rubberDuck) {
        this(Color.BLONDE, proximity, rubberDuck, 160);
    }
    /**
     * @return toString() of the instance created in this class
     */
    @Override
    public String toString() {
        return "Possible Suspect: " + super.toString()
            + " They were observed to be " + this.height + " cm tall.";
    }
    /**
     * This equals() method compares inputted object with a PersonOfInterest object.
     * @param other other represents the Object other which will be compared
     * to the PersonOfInterest object
     * @return a boolean expression of whether or not the inputted object
     * has identical field values of PersonOfInterest object
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (this.getClass().equals(other.getClass())) {
            Suspect obj = (Suspect) other;
            if (super.equals(other) && this.height == obj.height) {
                return true;
            }
        }
        return false;
    }
}