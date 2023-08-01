/**
 * @author Rhea Jaxon
 * @version 1
 */
public class Driver {
    /**
     * A run method to run all of the tests for the project.
     * @param args For any initialized class object to run their tests
     */
    public static void main(String[] args) {
        Task one = new Task("one", 1);
        Task two = new Task("two", 2);
        Task three = new Task("one", 1);
        System.out.println("==========Tests for Task Objects==========");
        System.out.println("\nIs Task one equal to Task two? :: " + one.equals(three));
        CellPhone four = new CellPhone(4, 10, 6);
        CellPhone five = new CellPhone(4, 5, 6);
        System.out.println("\n\n==========Tests for CellPhone Objects==========");
        System.out.println("\nCellPhone four :: " + four.toString());
        four.addTask(one);
        four.addTask(two);
        four.addTask(three);
        System.out.println("Tasks have been added to CellPhone four!");
        System.out.println("CellPhone four (updated) :: " + four.toString());
        four.processTask(one);
        System.out.println("Tasks have been removed from CellPhone four!");
        System.out.println("CellPhone four (updated) :: " + four.toString());
        System.out.println("Is CellPhone four equal to CellPhone five? :: " + four.equals(five));
        Laptop six = new Laptop(6, 7, 8, true);
        Laptop seven = new Laptop(6, 7, 8, true);
        System.out.println("\n\n==========Tests for Laptop Objects==========");
        System.out.println("\nLaptop six :: " + six.toString());
        six.addTask(one);
        six.addTask(two);
        six.addTask(three);
        System.out.println("Tasks have been added to Laptop six!");
        System.out.println("Laptop six (updated) :: " + six.toString());
        six.processTask(one);
        System.out.println("Tasks have been removed from Laptop six!");
        System.out.println("Laptop six (updated) :: " + six.toString());
        System.out.println("Is Laptop six equal to Laptop seven? :: " + six.equals(seven));
    }
}