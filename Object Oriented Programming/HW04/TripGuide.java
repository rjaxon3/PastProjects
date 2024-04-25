/**
 * @author Rhea Jaxon
 * @version 1
 */
public class TripGuide {
    /**
     * This main method will allow for tests to be run from
     * other classes for instantiation and methods.
     * @param args for object instantiation
     */
    public static void main(String[] args) {
        Attraction aquarium = new Attraction("Aquarium", 6.00);
        Attraction park = new Attraction("Park", 6.00);
        Museum muse = new Museum("Muse", 6.00, 25);
        Attraction movie = new Attraction("Movie", 7.52);
        Museum art = new Museum("Art Museum", 5.60, 26);
        Attraction playa = new Attraction("Playa", 7.52);
        Attraction mountains = new Attraction("Mountains", 7.52);
        String[] listOne = {"Vanessa", "Brian", "Aubrey", "Chloe"};
        String[] listTwo = {"Tristan", "Emma", "Anna", "Annie", "Jaxon"};
        String[] listThree = {"Rhea", "Shwetha", "Nikhila", "Elena", "Amira",
                              "Tvishi", "Patrick", "Anthony", "Aspen", "Kellie",
                                 "Eugene", "Gabe"};
        String[] listFour = {"Rhea", "Shwetha", "Nikhila", "Elena", "Amira",
                             "Tvishi", "Patrick", "Anthony", "Aspen", "Kellie",
                                "Eugene", "Gabe", "Vanessa", "Brian", "Aubrey", "Chloe",
                                "Tristan", "Emma", "Anna", "Annie", "Jaxon", "Harmony",
                                "Justice", "Aaryan", "Kevin"};
        System.out.println("==========Tests for HW04==========\n\n");
        aquarium.admit(listOne);
        aquarium.printVisitors();
        System.out.println(aquarium.toString());
        System.out.println("\n\n");
        park.admit(listTwo);
        park.printVisitors();
        System.out.println(park.toString());
        System.out.println("\n\n");
        movie.admit(listThree);
        movie.printVisitors();
        System.out.println(movie.toString());
        System.out.println("\n\n");
        playa.admit(listFour);
        playa.printVisitors();
        System.out.println(playa.toString());
        System.out.println("\n\n");
        mountains.admit(listOne);
        mountains.printVisitors();
        System.out.println(mountains.toString());
        System.out.println("\n\n");
        playa.rateAndExit(1, 5);
        movie.rateAndExit(0, 5);
        playa.printVisitors();
        System.out.println(movie.toString());
        System.out.println(playa.compareTo(movie));
        playa.rateAndExit(2, 3);
        movie.rateAndExit(0, 4);
        System.out.println(playa.toString());
        System.out.println(movie.toString());
        System.out.println(playa.compareTo(movie));
        art.admit(listThree);
        System.out.println(art.toString());
        art.rateAndExit(1, 5);
        System.out.println(art.toString());

    }
}