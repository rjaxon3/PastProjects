/**
 * @author Rhea Jaxon
 * @version 1
 */
public class Attraction implements Admittable, Comparable<Attraction> {
    /**
     * The name of the Attraction object
     */
    private final String name;
    /**
     * The sum of all the ratings given by the visitors who left
     */
    private long sumRatings;
    /**
     * The number of ratings given by the visitors that left
     */
    private int numRatings;
    /**
     * The price of the admission fee of the Attraction object
     */
    private final double admissionFee;
    /**
     * The Group object array of all the visitor group
     */
    private Group[] visitors;
    /**
     * Constructor taking in name and admissionFee.
     * @param name name is the name of the Attraction object
     * @param admissionFee admissionFee is the price of entering an Attraction object
     */
    public Attraction(String name, double admissionFee) {
        if (name.equals(null)) {
            this.name = "No name";
        } else {
            this.name = name;
        }
        if (admissionFee < 0) {
            this.admissionFee = 0.0;
        } else {
            this.admissionFee = admissionFee;
        }
        visitors = new Group[5];
    }
    /**
     * Constructor taking in name.
     * @param name name is the name of the Attraction object
     */
    public Attraction(String name) {
        this(name, 5.25);
    }
    /**
     * This getter method allows for other classes to access
     * Attraction name.
     * @return name name of the Attraction object
     */
    public String getName() {
        return name;
    }
    /**
     * This getter method allows for other classes to access
     * the admission fee of an Attraction object.
     * @return admissionFee admission fee of Attraction object
     */
    public double getAdmissionFee() {
        return admissionFee;
    }
    /**
     * This getter method allows for other classes to access
     * the number of ratings an Attraction object has recieved.
     * @return numRatings the number of rating an Attraction object
     * has recieved
     */
    public int getNumRatings() {
        return numRatings;
    }
    /**
     * This getter method allows for other classes to access
     * the Group array of visitors.
     * @return visitors visitors is the Group array which holds
     * the name of all the people who visited an attraction
     */
    public Group[] getVisitors() {
        return visitors;
    }
    /**
     * This method instantiates the Group array visitors and inputs
     * the visitor names in groups of five.
     * @param names names is the String array of the list of visitor names
     */
    public void admit(String[] names) {
        resize(names);
        if (names.length > 5) {
            int iteration = visitors.length;
            int extra = names.length % 5;
            int i = 0;
            for (int j = 0; j < names.length; j += 5) {
                if (j + 5 < names.length) {
                    visitors[i] = new Group(slice(names, j, j + 5));
                    i++;
                } else {
                    visitors[i] = new Group(slice(names, j, names.length));
                }
            }
        } else {
            visitors[0] = new Group(names);
        }
    }
    /**
     * This method resizes the Group array visitors if need be
     * so that there are no null values in visitors.
     * @param names names is the String array of the list of visitor names
     */
    public void resize(String[] names) {
        if (names != null) {
            Group people = new Group(names);
            if (people.size() / 5 <= visitors.length) {
                visitors = new Group[5];
            } else {
                int newLength = 2 * visitors.length;
                visitors = new Group[newLength];
            }
        }
    }
    /**
     * This method makes portions of an array.
     * @param names names is the String array of the list of visitor names
     * @param start start is an int index value of the array
     * @param end end is the int index value of the end of the portion
     * of the array needed
     * @return a String[] object of the piece of the array taken
     * from the original
     */
    public String[] slice(String[] names, int start, int end) {
        String[] portion = new String[end - start];
        for (int i = 0; i < portion.length; i++) {
            portion[i] = names[start + i];
        }
        return portion;
    }
    /**
     * This method removes a Group object from the Group array visitors
     * and updates the rating of the Attraction from the visitor group
     * that left.
     * @param index index of Group object that will be taken out
     * @param rating rating that the visitor group gave to the Attraction
     */
    public void rateAndExit(int index, int rating) {
        if (visitors == null || index < 0 || index > visitors.length - 1) {
            System.out.println("Could not update rating. Index invalid.");
        } else {
            if (rating < 1) {
                rating = 1;
            } else if (rating > 10) {
                rating = 10;
            }
            numRatings += 1;
            sumRatings += rating;
            int j = 0;
            Group[] other = new Group[visitors.length];
            for (int i = 0; i < visitors.length; i++) {
                if (i == index) {
                    continue;
                }
                if (j < other.length) {
                    other[j++] = visitors[i];
                }
            }
            visitors = new Group[other.length];
            for (int i = 0; i < other.length; i++) {
                visitors[i] = other[i];
            }
        }
    }
    /**
     * This method returns the average rating of the Attraction object.
     * @return double value of the rating given by each visitor group
     */
    public double averageRating() {
        if (numRatings > 0) {
            double avg = (double) sumRatings / numRatings;
            return avg;
        }
        return 0;
    }
    /**
     * This method prints out all the visitors in the vistor array
     * and prints them in their groups assigned.
     */
    public void printVisitors() {
        System.out.println(toString());
        int count = 0;
        for (int i = 0; i < visitors.length; i++) {
            if (visitors[i] != null) {
                count++;
            }
        }
        for (int i = 1; i <= count; i++) {
            System.out.println("Group " + i + ": " + visitors[i - 1].toString());
        }
    }
    /**
     * @return toString() of the instance created in this class
     */
    @Override
    public String toString() {
        return String.format("%s/%.2f/%.2f", this.name, this.averageRating(), this.admissionFee);
    }
    /**
     * This method is overrided from Comparable.
     * @param other Attraction object
     * @return int value of whether the two objects compared were equal
     */
    public int compareTo(Attraction other) {
        if (other == null) {
            return -1;
        }
        if (this.averageRating() == other.averageRating()) {
            if (this.admissionFee == other.admissionFee) {
                return 0;
            } else if (this.admissionFee > other.admissionFee) {
                return 1;
            } else {
                return -1;
            }
        }
        return 1;
    }
}