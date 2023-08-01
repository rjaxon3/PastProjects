import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class Scramble extends JFrame
{
	JButton keyboard[] = new JButton[20];
	String[] list = {"apple", "catch", "faith", "anger", "shield", "heist", "table", "maple", "vivid", "music"};
	Random rand = new Random(); //instance of random class
	String word = list[rand.nextInt(10)];
	String guess = "";
	int attempt = 3;
	private JTextField textField;
	int letter = 5;
	
	public Scramble()
	{	
		  setTitle("Unscramble Me!");
	      setLayout(new FlowLayout());
	      initializekb();
	      textField = new JTextField(20);
	      //works as the enter button to submit guesses
	      JButton button = new JButton("Enter guess");
	      button.addActionListener(new ActionListener()
	      {
	         public void actionPerformed(ActionEvent ae)
	         {
	        	 //prevents the user from submitting a blank answer
	            if (!textField.getText().equals(""))
	            {
	            	 guess = textField.getText();
	            	 for(int i = 0; i < 5; i++)
	            	 {
	            		 //changes the blank spaces to the user's guess in the GUI
	            		 keyboard[letter].setText(guess.substring(i, i + 1));
	            		 letter++;
	            		 if(i == 4)
	            		 {
	            			 //keeps track of the number of guesses the user has
	            			 attempt--;
	            			 check();
	            		 }
	            	 }
	            }
	         }
	      });
	   
	      add(textField);
	      add(button);
	      setSize(400, 300);
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      setLocationRelativeTo(null);
	      setVisible(true);
		 
	}
	
	public void initializekb()
	{
		for(int i = 0; i < 20; i++)
        {
            keyboard[i] = new JButton();
            //keyboard[i].addActionListener(new buttonListener());
            
            add(keyboard[i]); //adds this button to JPanel (note: no need for JPanel.add(...)
                                //because this whole class is a JPanel already  
        }
		
		
		for(int i = 0; i < 5; i++)
		{
			keyboard[i].setText(scramble(word).substring(i,i+1));
		}
	}
	
	public String scramble(String wrd)
	{
		if(wrd.length() == 5)
		{
			for(int i = 1; i < 5; i++)
			{
				//scrambles the word by repeatedly mixing up substrings of the original word
				wrd = wrd.substring(0, i - 1) + wrd.substring(i, i + 1) + wrd.substring(i - 1, i) + wrd.substring(i + 1);
				
			}
		}
			return wrd;
	}
	
		
		public boolean check()
		{
			
			   
			//checks to see if user's attempt matches the program's selected word
			if(guess.equals(word))
			{
				int res = JOptionPane.showOptionDialog(new JFrame(), "Correct! Would you like to play again?","Game",
				         JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				         new Object[] { "Yes", "No" }, JOptionPane.YES_OPTION);
				      if (res == JOptionPane.YES_OPTION)
				      {
				         new Scramble();
				         repaint();
				      }
				      else if (res == JOptionPane.NO_OPTION)
				      {
				    	  setVisible(false);
				      }
				      else if (res == JOptionPane.CLOSED_OPTION)
				      {
				         setVisible(false);
				      }
					return true;
			}
			else
			{
				int res = JOptionPane.showOptionDialog(new JFrame(), "Incorrect! You have " + attempt + " attempts remaining. Would you like to play again?" ,"Game",
				         JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				         new Object[] { "Yes", "No" }, JOptionPane.YES_OPTION);
				      if (res == JOptionPane.YES_OPTION)
				      {
				         
				      } 
				      else if (res == JOptionPane.NO_OPTION)
				      {
				    	  setVisible(false);
				      }
				      else if (res == JOptionPane.CLOSED_OPTION)
				      {
				         setVisible(false);
				      }
				    return false;
			}
		}
	   
		
	
	
	public static void main(String[] args)
	{
		new Scramble();
	}
}
