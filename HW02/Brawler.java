/**
 * @author Rhea Jaxon
 * @version 1
 */
public class Brawler extends SuperheroRecruit {
    /**
     * declares catchphrase object that takes in a string and double
     */
    private Catchphrase catchphrase;
    /**
     * @param name name of superhero
     * @param speed speed the superhero flies
     * @param strength strength of superhero
     * @param intelligence intelligence of superhero
     * @param catchphrase Catchphrase object that holds the catchphrase
     * and statIncrease for superhero
     */
    public Brawler(String name, int speed, double strength, int intelligence, Catchphrase catchphrase) {
        super(name, speed, strength, intelligence);
        this.catchphrase = catchphrase;
    }
    /**
     * Constructor taking in name, speed and defaults other fields.
     * @param name name of superhero
     * @param speed speed the superhero flies
     */
    public Brawler(String name, int speed) {
        super(name, speed, 220.0, 100);
        this.catchphrase = null;
    }
    /**
     * Copy constructor to allow for deep or shallow copies of objects.
     * @param toCopy toCopy is the object copy that we can create for deep
     * and shallow copies
     */
    public Brawler(Brawler toCopy) {
        super(toCopy);
        this.catchphrase = toCopy.catchphrase;
    }
    /**
     * This method shows the weight of how valuable a superhero is for
     * recruitment purposes.
     * @return the product of the average of superhero speed, strength, and intelligence
     * with the statIncrease value in order to get a percent increase in scaling weight
     */
    @Override
    public double powerScaling() {
        if (this.catchphrase == null) {
            return super.powerScaling();
        } else {
            return super.powerScaling() * (1 + catchphrase.getStatIncrease() / 100);
        }
    }
    /**
     * This method will allow superheroes to have their stats changed through
     * the catchphrase object's statIncrease field which will change their PowerScale weight
     * for recruitment.
     */
    public void statChange() {
        if (catchphrase == null) {
            String s1 = "Catchphrase: null" + "\nOriginal Speed and Strength: "
                + getSpeed() + " and ";
            String s2 = "Stat Increase: ";
            System.out.printf("%s%.2f\n%s0.0\nFinalPowerScaling: %.2f\n", s1,
                getStrength(), s2, powerScaling());
        } else {
            String s3 = "Catchphrase: " + catchphrase.getCatchphrase() + "\nOriginal Speed and Strength: "
                + getSpeed() + " and ";
            String s4 = "Stat Increase: ";
            System.out.printf("%s%.2f\n%s%.2f\nFinalPowerScaling: %.2f\n", s3,
                getStrength(), s4, catchphrase.getStatIncrease(), powerScaling());
        }
    }
}