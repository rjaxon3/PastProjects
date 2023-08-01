import java.util.ArrayList;
/**
 * @author Rhea Jaxon
 * @version 1
 */
public class PoliceStation {
    /**
     * String value of name of the Police Station
     */
    private String name;
    /**
     * Array List of all the Suspect objects
     */
    private ArrayList<Suspect> suspects;
    /**
     * Array List of all the Accomplice objects
     */
    private ArrayList<Accomplice> accomplices;
    /**
     * Constructor taking in name.
     * This constructor also initializes the two Array List fields
     * @param name name of the Police Station
     */
    public PoliceStation(String name) {
        if (name == null) {
            this.name = "Station 1331";
        } else {
            this.name = name;
        }
        this.suspects = new ArrayList<Suspect>();
        this.accomplices = new ArrayList<Accomplice>();
    }
    /**
     * This method adds in Suspect and Accomplice objects to
     * their subsequent Array Lists.
     * @param obj obj is a PersonOfInterest object
     */
    public void addPersonOfInterest(PersonOfInterest obj) {
        if (obj == null) {
            return;
        }
        if (obj instanceof Suspect) {
            suspects.add((Suspect) obj);
        } else if (obj instanceof Accomplice) {
            accomplices.add((Accomplice) obj);
        }
    }
    /**
     * This method sorts suspects in order from least to greatest
     * based on proximity and the lexicographic comparison of the hair color.
     */
    public void sortSuspects() {
        for (int i = 0; i < suspects.size(); i++) {
            for (int j = suspects.size() - 1; j > i; j--) {
                if (suspects.get(i).compareTo(suspects.get(j)) > 0) {
                    Suspect other = suspects.get(i);
                    suspects.set(i, suspects.get(j));
                    suspects.set(j, other);
                }
            }
        }
    }
    /**
     * This method removes duplicates from the suspects Array List,
     * while also returning a separate Array List of all the duplicates found.
     * @return Array List of duplicate values
     */
    public ArrayList<Suspect> removeDuplicates() {
        sortSuspects();
        ArrayList<Suspect> dupes = new ArrayList<Suspect>();
        for (int i = 0; i < suspects.size() - 2;) {
            if (suspects.get(i).equals(suspects.get(i + 1))) {
                if (!dupes.contains(suspects.get(i))) {
                    dupes.add(suspects.get(i));
                }
                suspects.remove(i);
            } else {
                i++;
            }
        }
        return dupes;
    }
    /**
     * This method returns a Suspect object from the suspects
     * Array List based on suspects who have the same hair color and
     * proximity to the inputted Suspect object.
     * @param obj obj is a Suspect object
     * @return Suspect object from Array List suspects that
     * matches inputted Suspect object characteristics
     */
    public Suspect findSuspect(Suspect obj) {
        if (obj == null) {
            return null;
        }
        removeDuplicates();
        int beg = 0;
        int end = suspects.size() - 1;
        int mid = 0;
        while (beg <= end) {
            mid = (beg + end) / 2;
            if (suspects.get(mid).compareTo(obj) == 0) {
                return suspects.get(mid);
            } else if (suspects.get(mid).compareTo(obj) > 0) {
                beg = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return null;
    }
    /**
     * This method find the accomplice that worked with the suspect
     * by matching the rubber ducks of the two individuals.
     * @param obj obj is a Suspect object
     * @return Accomplice object that has the same RubberDuck object as
     * the inputted Suspect object
     */
    public Accomplice findMastermind(Suspect obj) {
        Suspect person = findSuspect(obj);
        for (int i = 0; i < accomplices.size(); i++) {
            if (accomplices.get(i).getRubberDuck().equals(person.getRubberDuck())
                && accomplices.get(i).getCanCode()) {
                return accomplices.get(i);
            }
        }
        return null;
    }
    /**
     * This method allows for tests of the instances to run.
     * @param args arguments for instances
     */
    public static void main(String[] args) {
        /*RubberDuck one = new RubberDuck(Color.GREEN, true);
        RubberDuck two = new RubberDuck(Color.WHITE, true);
        RubberDuck three = new RubberDuck(Color.RED, false);
        RubberDuck four = new RubberDuck(Color.GREEN, true);
        System.out.println("==========Tests for RubberDucks==========\n");
        System.out.println("Is RubberDuck one == RubberDuck four :: " + one.equals(four));
        Suspect alice = new Suspect(Color.BLACK, 48, one, 180);
        Suspect candace = new Suspect(Color.BLACK, 47, three, 180);
        System.out.println("==========Tests for Suspects==========\n");
        System.out.println("Is alice == candace :: " + alice.equals(candace));
        PoliceStation gtpd = new PoliceStation("GTPD");
        gtpd.addPersonOfInterest(alice);
        gtpd.addPersonOfInterest(alice);
        gtpd.addPersonOfInterest(alice);
        gtpd.addPersonOfInterest(candace);
        System.out.println("==========Tests for PoliceStation==========\n");
        System.out.println("...Sorting Array");
        gtpd.sortSuspects();
        System.out.println(gtpd.removeDuplicates());
        Accomplice hayley = new Accomplice(Color.BLONDE, 120, four, true);
        gtpd.addPersonOfInterest(hayley);
        gtpd.findSuspect(alice);
        gtpd.findMastermind(alice);*/

        ArrayList<Integer> nums = new ArrayList<>(4);
        System.out.println(nums.size());
        nums.add(new Integer(1));
        System.out.println(nums.size());

    }
}