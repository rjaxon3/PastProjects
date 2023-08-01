/**
 * @author Rhea Jaxon
 * @version 1
 */
public class Group {
    /**
     * String array that keeps track of all the visitors
     * who visited an Attraction
     */
    private final String[] people;
    /**
     * Constructor taking in people.
     * @param people people is the String array of the names of
     * the visitors
     */
    public Group(String[] people) {
        if (people == null) {
            this.people = new String[0];
        } else {
            this.people = new String[people.length];
            for (int i = 0; i < people.length; i++) {
                this.people[i] = people[i];
            }
        }
    }
    /**
     * This method returns the size of the String array people.
     * @return String array people's length
     */
    public int size() {
        return people.length;
    }
    /**
     * This method returns the String array people.
     * @return String array people
     */
    public String[] getPeople() {
        return people;
    }
    /**
     * This method creates a string that lists out all the names
     * in the String array people that was inputted in the constructor
     * when the class was built.
     * @return toString() of the instance created in this class
     */
    public String toString() {
        String s = "";
        for (int i = 0; i < people.length; i++) {
            if (i < people.length - 1) {
                if (people[i] != null) {
                    s += people[i] + "/";
                }
            } else {
                if (people[i] != null) {
                    s += people[i];
                }
            }
        }
        return s;
    }
}