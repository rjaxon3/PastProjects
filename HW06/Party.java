import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
/**
 * @author Rhea Jaxon
 * @version 1
 */
public class Party {
    /**
     * This method returns a PartyMember ArrayList of all the players recruited onto a party
     * from a file.
     * @param fileName fileName is a string value of the file name
     * @return an ArrayList of PartyMember objects of players in the party
     * @throws FileNotFoundException for invalid file names
     */
    public static ArrayList<PartyMember> recruitParty(String fileName) throws FileNotFoundException {
        ArrayList<PartyMember> party = new ArrayList<>();
        if (fileName != null && (new File(fileName)).exists()) {
            File info = new File(fileName);
            Scanner scan = new Scanner(info);
            while (scan.hasNextLine()) {
                String data = scan.nextLine().trim();
                if (data.isBlank()) {
                    continue;
                } else {
                    party.add(processInfo(data));
                }
            }
            scan.close();
            return party;
        } else {
            throw new FileNotFoundException();
        }
    }
    /**
     * This method processes the information on the file to create Warrior and Mage objects.
     * @param line line is a String value of a line on the file
     * @return PartyMember object of either class Warrior or Mage
     * @throws InvalidPartyMemberException for invalid character inputs
     */
    private static PartyMember processInfo(String line) throws InvalidPartyMemberException {
        String[] temp = line.split(",");
        for (int i = 0; i < temp.length; i++) {
            String[] details = temp[i].split(": ");
            temp[i] = details[1];
        }
        if (temp[0].equals("Warrior")) {
            Warrior player = new Warrior(temp[1], Integer.parseInt(temp[2]),
                Integer.parseInt(temp[3]), temp[4], Integer.parseInt(temp[5]));
            return player;
        } else if (temp[0].equals("Mage")) {
            Mage player = new Mage(temp[1], Integer.parseInt(temp[2]), Integer.parseInt(temp[3]),
                Integer.parseInt(temp[4]), Integer.parseInt(temp[5]));
            return player;
        } else {
            throw new InvalidPartyMemberException();
        }
    }
    /**
     * This method returns a boolean value after printing out the party roster.
     * @param fileName fileName is the String value of the file name
     * @param obj obj is a PartyMember ArrayList that holds all the players
     * @return boolean value of if party roster string got printed
     */
    public static boolean partyRoster(String fileName, ArrayList<PartyMember> obj) {
        try {
            PrintWriter pw = new PrintWriter(fileName);
            for (int i = 0; i < obj.size(); i++) {
                pw.println(obj.get(i).toString());
            }
            pw.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Not a valid file name.");
            return false;
        } catch (NullPointerException n) {
            System.out.println("Oops, looks like there is no party built yet!");
            return false;
        }
    }
    /**
     * This method returns a boolean of whether or not the party was able to complete
     * a quest.
     * @param name name of the quest
     * @param obj obj is a PartyMember ArrayList
     * @return boolean of whether or not quest was completed
     */
    public static boolean getQuest(String name, ArrayList<PartyMember> obj)
        throws QuestNotFoundException, FileNotFoundException {
        if (!(new File("quest.csv")).exists()) {
            throw new FileNotFoundException();
        }
        File list = new File("quest.csv");
        ArrayList<String[]> info = new ArrayList<>();
        ArrayList<String> page = new ArrayList<>();
        Scanner scan = new Scanner(list);
        while (scan.hasNextLine()) {
            String data = scan.nextLine();
            page.add(data);
            String[] temp = data.split(":|,");
            info.add(temp);
        }
        scan.close();
        for (String[] s : info) {
            if (s[0].equals(name)) {
                int level = Integer.parseInt(s[1].trim());
                if (level < partyQuestLevel(obj)) {
                    System.out.println("Success! Your party gained " + s[2].trim()
                        + " coins. This calls for a trip to the Tavern!");
                    page.remove(info.indexOf(s));
                    File tmp = new File("temp.csv");
                    PrintWriter pw = new PrintWriter(tmp);
                    for (int i = 0; i < page.size(); i++) {
                        pw.println(page.get(i));
                    }
                    pw.close();
                    if (list.delete()) {
                        tmp.renameTo(list);
                    }
                    return true;
                } else {
                    System.out.println("Failure...Your party was defeated. Better Luck Next Time!");
                    return false;
                }
            }
        }
        throw new QuestNotFoundException();
    }
    /**
     * This method returns the sum of player's quest levels.
     * @param obj obj is a PartyMember Arraylist
     * @return int value of sum of quest levels
     */
    private static int partyQuestLevel(ArrayList<PartyMember> obj) {
        if (obj == null || obj.size() == 0) {
            return -1;
        }
        int sum = 0;
        for (PartyMember player: obj) {
            if (player instanceof Warrior) {
                sum += ((Warrior) player).questLevel();
            } else {
                sum += ((Mage) player).questLevel();
            }
        }
        return sum;
    }
    /**
     * This method helps run tests.
     * @param args arguments for tests
     */
    public static void main(String[] args) {
        Warrior one = new Warrior("Harry", 100, 40, "gun", 8);
        Warrior two = new Warrior("Vanessa", 100, 68, "sword", 10);
        Warrior three = new Warrior("Emma", 100, 60, "knife", 1);
        Mage four = new Mage("Jessie", 2, 2, 3, 1);
        Mage five = new Mage("Toby", 50, 2, 3, 1);
        File test = new File("TestParty.csv");
        ArrayList<PartyMember> obj = new ArrayList<>();
        obj.add(one);
        obj.add(two);
        obj.add(three);
        obj.add(four);
        obj.add(five);
        try {
            partyRoster("TestParty", obj);
            recruitParty("TestParty");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("==============TESTS FOR HW06=============\n");
        System.out.println("----------Tests for processInfo()----------");
        processInfo("Class: Warrior, Name: Warrior07, Health: 100, Base Attack: 200, Weapon: mace, Armor Class: 10");
        processInfo("Class: Warrior, Name: Warrior02, Health: 50, Base Attack: 0, Weapon: spear, Armor Class: 0");
        processInfo("Class: Warrior, Name: Warrior03, Health: 50, Base Attack: 50, Weapon: lance, Armor Class: 0");
        processInfo("Class: Warrior, Name: Warrior04, Health: 50, Base Attack: 50, Weapon: bola, Armor Class: 5");
        processInfo("Class: Mage, Name: Mage01, Health: 2, Base Attack: 2, Spell Attack: 3, Spell Slots: 1");
        processInfo("Class: Mage, Name: Mage02, Health: 50, Base Attack: 2, Spell Attack: 3, Spell Slots: 1");
        System.out.println("\n----------Tests for getQuest()----------");
        try {
            getQuest("Spire of Misery", obj);
            getQuest("Not Real", obj);
        } catch (QuestNotFoundException q) {
            System.out.println(q.getMessage());
        } catch (FileNotFoundException f) {
            System.out.println("File not found!");
        }
        System.out.println("\n----------Tests for partyQuestLevel()----------");
        System.out.println("Party level for obj: " + partyQuestLevel(obj));
        System.out.println("...Removing Player");
        obj.remove(five);
        System.out.println("Party level for obj: " + partyQuestLevel(obj));
        System.out.println("...Removing Player");
        obj.remove(four);
        System.out.println("Party level for obj: " + partyQuestLevel(obj));
        System.out.println("...Removing Player");
        obj.remove(three);
    }
}