/**
 * @author Rhea Jaxon
 * @version 1
 */
public class Museum extends Attraction {
    /**
     * int value of the maximum number of people in a Museum object
     */
    private final int maxCapacity;
    /**
     * number of occupants currently in the Museum object
     */
    private int occupancy;
     /**
     * Constructor taking in name, admissionFee, maxCapacity.
     * @param name name is the name of the Attraction object
     * @param admissionFee admissionFee is the price of entering an Attraction object
     * @param maxCapacity max capacity of the Museum object
     */
    public Museum(String name, double admissionFee, int maxCapacity) {
        super(name, admissionFee);
        if (maxCapacity < 25) {
            this.maxCapacity = 25;
        } else {
            this.maxCapacity = maxCapacity;
        }
        this.occupancy = 0;
    }
    /**
     * Constructor taking in name.
     * @param name name is the name of the Attraction object
     */
    public Museum(String name) {
        this(name, 5.25, 25);
    }
    /**
     * This method instantiates the Group array visitors and inputs
     * the visitor names in groups of five. It overrides from the Attraction
     * class.
     * @param names names is the String array of the list of visitor names
     */
    @Override
    public void admit(String[] names) {
        occupancy = names.length;
        if (occupancy > maxCapacity) {
            System.out.println("Museum has reached maximum capacity. Please visit another time!");
            occupancy = 0;
        } else {
            super.admit(names);
        }
    }
    /**
     * This method removes a Group object from the Group array visitors
     * and updates the rating of the Attraction from the visitor group
     * that left. This method overrides from the Attraction class.
     * @param index index of Group object that will be taken out
     * @param rating rating that the visitor group gave to the Attraction
     */
    @Override
    public void rateAndExit(int index, int rating) {
        String[] other = getVisitors()[index].getPeople();
        for (int i = 0; i < other.length; i++) {
            if (other[i] != null) {
                occupancy -= 1;
            }
        }
        super.rateAndExit(index, rating);
    }
    /**
     * This method gives the leftover capacity in percent of the Museum object.
     * @return percent value of the current occupants in a Museum object
     */
    public double percentOccupancy() {
        double percent = (double) occupancy / maxCapacity;
        return percent;
    }
    /**
     * @return toString() of the instance created in this class
     */
    public String toString() {
        return String.format("Museum: %s/%.2f/%.2f/%.2f%%", getName(),
        averageRating(), getAdmissionFee(), percentOccupancy());
    }
}