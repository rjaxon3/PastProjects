/**
 * @author Rhea Jaxon
 * @version 1
 */
public class FlyingHero extends SuperheroRecruit {
    /**
     * The altitude at which a hero flies
     */
    private double altitude;
    /**
     * Constructor taking in name, speed, strength, intelligence, alititude.
     * @param name name of superhero
     * @param speed speed the superhero flies
     * @param strength strength of superhero
     * @param intelligence intelligence of superhero
     * @param altitude altitude at which superhero flies
     */
    public FlyingHero(String name, int speed, double strength, int intelligence, double altitude) {
        super(name, speed, strength, intelligence);
        if (altitude >= 10 && altitude <= 50) {
            this.altitude = altitude;
        } else {
            this.altitude = 50.0;
        }
    }
    /**
     * Constructor taking in name, speed and defaults other fields.
     * @param name name of superhero
     * @param speed speed the superhero flies
     */
    public FlyingHero(String name, int speed) {
        this(name, speed, 220.0, 100, 50.0);
    }
    /**
     * Constructor taking in name and defaults other fields.
     * @param name name of superhero
     */
    public FlyingHero(String name) {
        this(name, 20, 220.0, 100, 50.0);
    }
    /**
     * Copy constructor to allow for deep or shallow copies of objects.
     * @param toCopy toCopy is the object copy that we can create for deep
     * and shallow copies
     */
    public FlyingHero(FlyingHero toCopy) {
        super(toCopy);
        altitude = toCopy.altitude;
    }
    /**
     * This method shows the weight of how valuable a superhero is for
     * recruitment purposes.
     * @return the sum of the average of superhero speed, strength, and intelligence
     * With the altitude of which the hero flies at
     */
    @Override
    public double powerScaling() {
        return super.powerScaling() + altitude;
    }
    /**
     * @return toString of the instance created in this class
     */
    public String toString() {
        String s = super.toString();
        return String.format("%s can fly %.2f feet in the air! %s", getName(), getAlt(), s);
    }
    /**
     * Accessor method for altitude.
     * @return Returns altitude at which superhero flies
     */
    public double getAlt() {
        return altitude;
    }
}