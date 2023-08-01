/**
 * @author Rhea Jaxon
 * @version 1
 */
public class Headquarters {
    /**
     * A run method to run all of the tests for the project.
     * @param args For any initialized class object to run their tests
     */
    public static void main(String[] args) {
        SuperheroRecruit uno = new SuperheroRecruit("Uno", 12);
        SuperheroRecruit dos = new SuperheroRecruit(uno);
        FlyingHero tres = new FlyingHero("Tres", 15);
        FlyingHero cuatro = new FlyingHero(tres);
        Brawler cinco = new Brawler("Cinco", 17);
        Brawler seis = new Brawler(cinco);
        Catchphrase siete = new Catchphrase("Gotcha!", 2.0);
        System.out.println("----------Tests for Objects----------");
        System.out.println("------------and Copy Tests-----------");
        System.out.println("Uno's power scaling:: " + uno.powerScaling());
        System.out.println("Dos's power scaling:: " + dos.powerScaling());
        System.out.println("Hero Decision for Uno:: " + uno.recruit("Mars"));
        System.out.println("Hero Decision for Dos:: " + dos.recruit("Mars"));
        System.out.println("Hero Decision for Tres:: " + tres.recruit("Mars"));
        System.out.println("Hero Decision for Cuatro:: " + cuatro.recruit("Mars"));
        System.out.println("Hero Decision for Cinco:: " + cinco.recruit("Mars"));
        System.out.println("Hero Decision for Seis:: " + seis.recruit("Mars"));
        System.out.println("----------Tests for Objects----------");
        System.out.println("-------------on toStrings------------");
        System.out.println(cinco.toString());
        cinco.statChange();
        //seis.statChange();
    }
}