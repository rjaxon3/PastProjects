import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;


public class MadLibs extends JPanel implements ActionListener, KeyListener
{
	public JTextField userPrompt = new JTextField();
	public String prompt;
	public JTextField userBlank = new JTextField();
	public String blank = userBlank.getText();
	public JButton create, test, play;
	public int blankCount;
	public JTextArea text;
	public JTextField textField = new JTextField(20);
	public JLabel keyLabel = new JLabel("Print prompt");
	
	public MadLibs()
	{
		
        
		JFrame window = new JFrame("MadLibs!");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(400,200,500,500);
        window.setVisible(true);
		
       /* create = new JButton("Create");
		create.addActionListener(this);
		create.setActionCommand("Create");
		create.setBounds(100,100,100,60);
		
		test = new JButton("Test");
		test.addActionListener(this);
		test.setActionCommand("Test");
		test.setBounds(200,100,100,60);
		
		play = new JButton("Play");
		play.addActionListener(this);
		play.setActionCommand("Play");
		play.setBounds(300,100,100,60);*/
		
		textField.addKeyListener(this);
		BorderLayout layout = new BorderLayout();
		setLayout(layout);
		add(keyLabel, BorderLayout.NORTH);
		add(textField, BorderLayout.CENTER);
		
		/*text = new JTextArea();
		text.setText("\n\n\tWELCOME TO MadLibs Creator!!!");
		
		JPanel main = new JPanel();
		main.setLayout(null);
		main.add(create);
		main.add(test);
		main.add(play);
		main.add(text);
		main.add(textField);
		
		window.getContentPane().add(main);*/
		
	}
	
	
	
		
		public void keyTyped(KeyEvent e)
		{
			//not used
		}
		
		public void keyPressed(KeyEvent e)
		{
			char keyCode = e.getKeyChar();
			prompt += keyCode;
			textField.setText(prompt);
			int key = e.getKeyCode();
			if(key == KeyEvent.VK_F)
			{
				keyLabel.setText("You pressed");
			}
			else
			{
				keyLabel.setText("hi");
			}
		}
		
		public void keyReleased(KeyEvent e)
		{
			
		}
		
	
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("hello1");
		if(e.getSource() == create)
		{
			System.out.println("hello2");
			//keyPressed(e);
			prompt = userPrompt.getText();
			text.setText(prompt);
			System.out.println("hello3");
			
		}
	}
	
	public void setBlank(String str)
	{
		str = userPrompt.getText();
		prompt.replace(str, "_______");
		blankCount++;
	}
	
	
	
	public static void main(String[] args) 
    {
        MadLibs run = new MadLibs();
    }
}
