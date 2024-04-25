/**
 * @author Rhea Jaxon
 * @version 1
 */
public class Catchphrase {
    /**
     * catchphrase is something the superhero says in the middle of the battle.
     */
    private final String catchphrase;
    /**
     * statIncrease can change the PowerScale weight of a superhero for recruitment
     */
    private final double statIncrease;
    /**
     * Constructor taking in catchphrase and statIncrease.
     * @param catchphrase String phrase the superhero says in battle
     * @param statIncrease a change in powerScale weightage
     */
    public Catchphrase(String catchphrase, double statIncrease) {
        if (catchphrase == null || catchphrase.isEmpty() || catchphrase.trim().isEmpty()) {
            this.catchphrase = "I can do this all day";
        } else {
            this.catchphrase = catchphrase;
        }
        if (statIncrease > 0 && statIncrease <= 20.0) {
            this.statIncrease = statIncrease;
        } else {
            this.statIncrease = 15.0;
        }
    }
    /**
     * @return toString of the instance created in this class
     */
    public String toString() {
        if (catchphrase == null || catchphrase.isEmpty() || catchphrase.trim().isEmpty()) {
            return null;
        }
        return String.format("%s! Stats are increased by %.2f.", catchphrase, statIncrease);
    }
    /**
     * Accessor method that retrieves String catchphrase.
     * @return Returns the phrase superhero will say during battle
     */
    public String getCatchphrase() {
        return catchphrase;
    }
    /**
     * Accessor method that retrieves total weightage PowerScale will increase by.
     * @return Returns the percent increase for PowerScale change
     */
    public double getStatIncrease() {
        return statIncrease;
    }
}