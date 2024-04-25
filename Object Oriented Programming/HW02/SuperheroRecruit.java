/**
 * @author Rhea Jaxon
 * @version 1
 */
public class SuperheroRecruit {
    /**
     * The name of the superhero
     */
    protected final String name;
    /**
     * The IQ level of the superhero
     */
    private int speed, intelligence;
    /**
     * The strength value of superhero
     */
    private final double strength;
    /**
     * The total number of superhero recruits
     */
    private static int totalRecruits = 10;
    /**
     * Constructor taking in name, speed, strength, intelligence.
     * @param name name of superhero
     * @param speed speed the superhero flies
     * @param strength strength of superhero
     * @param intelligence intelligence of superhero
     */
    public SuperheroRecruit(String name, int speed, double strength, int intelligence) {
        this.intelligence = intelligence;
        if (name == null || name.equals("")) {
            this.name = "Steve Rogers";
        } else {
            this.name = name;
        }
        if (speed < 0) {
            this.speed = 20;
        } else {
            this.speed = speed;
        }
        if (strength < 0.0) {
            this.strength = 220.0;
        } else {
            this.strength = strength;
        }
        if (intelligence < 0) {
            this.intelligence = 100;
        } else {
            this.intelligence = intelligence;
        }
    }
     /**
     * Constructor taking in name, speed, strength.
     * @param name name of superhero
     * @param speed speed the superhero flies
     * @param strength strength of superhero
     */
    public SuperheroRecruit(String name, int speed, double strength) {
        this(name, speed, strength, 100);
    }
    /**
     * Constructor taking in name, speed.
     * @param name name of superhero
     * @param speed speed the superhero flies
     */
    public SuperheroRecruit(String name, int speed) {
        this(name, speed, 220.0, 100);
    }
    /**
     * Copy constructor to allow for deep or shallow copies of objects.
     * @param toCopy toCopy is the object copy that we can create for deep
     * and shallow copies
     */
    public SuperheroRecruit(SuperheroRecruit toCopy) {
        name = toCopy.name;
        speed = toCopy.speed;
        strength = toCopy.strength;
        intelligence = toCopy.intelligence;
    }
    /**
     * This method shows the weight of how valuable a superhero is for
     * recruitment purposes.
     * @return the sum of the average of superhero speed, strength, and intelligence
     * With the altitude of which the hero flies at
     */
    public double powerScaling() {
        return (speed + strength + intelligence) / 3;
    }
    /**
     * @return toString of the instance created in this class
     */
    public String toString() {
        String s1 = getName() + " has " + getIntelligence() + " IQ, runs at " + getSpeed()
            + " miles per hour, and can lift ";
        String s2 = " pounds. The Power Scaling is ";
        return String.format("%s%.2f%s%.2f", s1, getStrength(), s2, powerScaling());
    }
    /**
     * @return Returns the result of whether or not the superhero will be recruited at
     * A certain station based on their stats
     * @param station Station of which the superhero is looking to get recruited to
     */
    public String recruit(String station) {
        if (totalRecruits != 0 && powerScaling() > 120) {
            totalRecruits--;
            return toString() + " Recruit is enlisted to " + station + ".";
        } else if (totalRecruits == 0) {
            return "Cannot recruit anyone, there are no slots left!";
        } else if (totalRecruits < 0) {
            return "Cannot recruit anyone, there are no slots left!";
        } else if (powerScaling() <= 120) {
            return "Rejected. Take a look at our sidekick program!";
        }
        return "";
    }
    /**
     * Accessor method that retrieves superhero name.
     * @return Returns name of superhero
     */
    public String getName() {
        return name;
    }
    /**
     * Accessor method that retrieves superhero speed.
     * @return Returns speed the superhero can fly at
     */
    public int getSpeed() {
        return speed;
    }
    /**
     * Accessor method that retrieves superhero strength value.
     * @return Returns the value of superhero strength
     */
    public double getStrength() {
        return strength;
    }
    /**
     * Accessor method that retrieves superhero IQ level.
     * @return Returns the IQ level of the superhero
     */
    public int getIntelligence() {
        return intelligence;
    }
    /**
     * Accessor method that retrieves total number of recruits.
     * @return Returns the total number of recruits left
     */
    public int getTotalRecruits() {
        return totalRecruits;
    }
    /**
     * Setter method that sets total number of recruits.
     * @param inc Inc represents the --; increment after a recruit is added
     * in order to keep track of total number of recruits
     */
    public void setTotalRecruits(int inc) {
        totalRecruits = inc;
    }
}