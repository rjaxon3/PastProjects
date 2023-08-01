import java.util.Scanner;

public class MadLibGame
{
	String prompt, name, blank;
	static String newPrompt;
	Scanner scan = new Scanner(System.in);
	String[] rmve, clueBank;
	String[][] preMade = new String[2][10];
	String taco = "Today I went to my favorite Taco Stand called the 0 1 . Unlike most food stands, "
			+ "they cook and prepare the food in a 2 while you 3 . "
			+ "The best thing on the menu is the 4 5 . Instead of ground beef they fill the taco with 6 "
			+ ", cheese, and top it off with a salsa made from 7 . If that doesn't make your mouth water, then it' just like 8 "
			+ "always says: 9 !";
	String job = "Today a 0 named came to our school to talk to us about her job. "
			+ "She said the most important skill you need to know to do her job is to be able to 1 "
			+ "around (a) 2 . She said it was easy for her to learn her job because she loved to 3 when she was "
			+ "my age--and that helps a lot! If you're considering her 4 profession, I hope you can be near 5 (a)6 . "
			+ "That's very important! If you get too distracted in that 7 situation you won't be able to 8 next to (a)9 !";
	
	public MadLibGame()
	{
		preMade[0][0]= "Enter adjective: ";
		preMade[0][1] = "Enter noun: ";
		preMade[0][2] = "Enter something you drive in: ";
		preMade[0][3]= "Enter verb: ";
		preMade[0][4]= "Enter color: ";
		preMade[0][5]= "Enter noun: ";
		preMade[0][6]= "Enter food(s): ";
		preMade[0][7]= "Enter food(s): ";
		preMade[0][8]= "Enter person: ";
		preMade[0][9]= "Enter saying: ";
		
		preMade[1][0]= "Enter occupation: ";
		preMade[1][1] = "Enter verb: ";
		preMade[1][2] = "Enter noun: ";
		preMade[1][3]= "Enter verb: ";
		preMade[1][4]= "Enter adjective: ";
		preMade[1][5]= "Enter adjective: ";
		preMade[1][6]= "Enter noun: ";
		preMade[1][7]= "Enter adjective: ";
		preMade[1][8]= "Enter verb: ";
		preMade[1][9]= "Enter noun: ";
		
		System.out.println("Welcome to Madlibs creator! \nWhat is your name?");
		name = scan.nextLine();
		int welcome = 1;
		
		while(welcome == 1)
		{
			System.out.println("Welcome " + name + "! Would you like to create or play a game?");
			String choice = scan.nextLine();
			String letter = String.valueOf(choice.charAt(0));
			if(letter.equals("c") || letter.equals("C"))
			{
				System.out.println("Enter your prompt: ");
				create(scan.nextLine());
				System.out.println("Would you like to play again?");
				choice = scan.nextLine();
				letter = String.valueOf(choice.charAt(0));
				if(letter.equals("n") || letter.equals("N"))
				{
					welcome = 0;
				}
			}
			else
			{
				System.out.println("Would you like to do prompt 1 or 2?");
				playLibs(scan.nextInt());
				System.out.println("Would you like to play again?");
				choice = scan.nextLine();
				letter = String.valueOf(choice.charAt(0));
				if(letter.equals("n") || letter.equals("N"))
				{
					welcome = 0;
				}

			}

		}
	}
	
	private void playLibs(int nextInt)
	{
		String set = null;
		if(nextInt == 1)
		{
			nextInt = 0;
			set = taco;
		}
		else
		{
			nextInt = 1;
			set = job;
		}
		scan.nextLine();
		for(int i = 0; i < 10; i++)
		{
			String n = "" + i + "";
			System.out.println(n);
			System.out.println(preMade[nextInt][i]);
			remove(set, n, scan.nextLine());
			set = newPrompt;
		}
		
		
		System.out.print(set);
		
	}

	
	static void remove(String str, String word, String rplc)
    {
        // Split the string using split() method
        String msg[] = str.split(" ");
        String new_str = "";
  
        // Itearating the string using for each loop
        for (String words : msg)
        {
  
            // If desired word is found
            if (!words.equals(word))
            {
  
                // Concate the word not equal to the given
                // word
                new_str += words + " ";
            }
            else
            	new_str += rplc + " ";
        }
  
        // Print the new String
        newPrompt = new_str;
    }
	
	public void create(String in)
	{
		prompt = in;
		System.out.println("How many blank spots do you want?");
		int count = scan.nextInt();
		rmve = new String[count];
		for(int i = 0; i < count; i++)
		{
			System.out.println("");
			blank = scan.nextLine();
		}
		
		for(int i = 0; i < count; i++)
		{
			System.out.println("Enter phrase you want to remove: ");
			blank = scan.nextLine();
			rmve[i] = blank;
			
		}
	
		clueBank = new String[count];
		for(int i = 0; i < count; i++)
		{
			System.out.println("Enter clue for blank #" + (i+1) + ": ");
			blank = scan.nextLine();
			clueBank[i] = blank;
				
		}
		
		System.out.println("You are done creating your Madlibs prompt!\nWant to test it out?");
		String choice = scan.nextLine();
		String letter = String.valueOf(choice.charAt(0));
		if(letter.equals("y") || letter.equals("Y"))
		{
			for(int i = 0; i < count; i++)
			{
				System.out.println("Enter: " + clueBank[i]);
				blank = scan.nextLine();
				remove(prompt, rmve[i], blank);
				prompt = newPrompt;
					
			}
			System.out.println(prompt);
		}
		else
			System.out.println("Why are you even hear then??");
	}
		

	
	public static void main(String[] args)
	{
		MadLibGame run = new MadLibGame();
	}
}
