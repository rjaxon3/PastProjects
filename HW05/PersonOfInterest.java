/**
 * @author Rhea Jaxon
 * @version 1
 */
public abstract class PersonOfInterest implements Comparable<PersonOfInterest> {
    /**
     * Enum value used to represent color of cuplrit hair color
     */
    private Color hairColor;
    /**
     * int value of how close a person object is
     */
    private int proximity;
    /**
     * RubberDuck object the culprit uses in crime
     */
    private RubberDuck rubberDuck;
    /**
     * Constructor taking in hairColor, proximity, and rubberDuck.
     * @param hairColor hairColor is a color value that represents
     * the color of hair the culprit has
     * @param proximity proximity is how close the culprit is
     * @param rubberDuck rubberDuck is a RubberDuck object
     */
    public PersonOfInterest(Color hairColor, int proximity, RubberDuck rubberDuck) {
        if (hairColor == null) {
            this.hairColor = Color.BLACK;
        } else {
            this.hairColor = hairColor;
        }
        if (proximity < 0) {
            this.proximity = Math.abs(proximity);
        } else {
            this.proximity = proximity;
        }
        if (rubberDuck == null) {
            this.rubberDuck = new RubberDuck();
        } else {
            this.rubberDuck = new RubberDuck(rubberDuck.getColor(), rubberDuck.getHat());
        }
    }
    /**
     * Constructor taking in proximity and rubberDuck.
     * @param proximity proximity is how close the culprit is
     * @param rubberDuck rubberDuck is a RubberDuck object
     */
    public PersonOfInterest(int proximity, RubberDuck rubberDuck) {
        this(Color.BLONDE, proximity, rubberDuck);
    }
    /**
     * Accessor method to get RubberDuck object.
     * @return rubberDuck which is a RubberDuck object
     */
    public RubberDuck getRubberDuck() {
        return rubberDuck;
    }
    /**
     * @return toString() of the instance created in this class
     */
    public String toString() {
        String s = "A POI with " + this.hairColor + " hair was last seen "
            + proximity + " miles away holding " + this.rubberDuck.toString();
        return s;
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
            PersonOfInterest obj = (PersonOfInterest) other;
            if (this.rubberDuck.equals(obj.rubberDuck) && this.hairColor == obj.hairColor
                && this.proximity == obj.proximity) {
                return true;
            }
        }
        return false;
    }
    /**
     * This compareTo() method compares inputted object with a PersonOfInterest object.
     * @param other other represents the Object other which will be compared
     * to the PersonOfInterest object
     * @return an int value of whether or not the inputted object has identical
     * field values of PersonOfInterest object
     */
    public int compareTo(PersonOfInterest other) {
        if (other == null) {
            return -1;
        }
        if (this.proximity == other.proximity) {
            if (this.hairColor.compareTo(other.hairColor) == 0) {
                return 0;
            }
        } else if (this.proximity < other.proximity) {
            return -1;
        } else {
            return 1;
        }
        return 1;
    }
}