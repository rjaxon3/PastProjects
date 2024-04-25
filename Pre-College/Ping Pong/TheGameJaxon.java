//(c) A+ Computer Science
//www.apluscompsci.com
//Name - Rhea Jaxon

import javax.swing.JFrame;
import java.awt.Component;
import javax.swing.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import java.net.URL;
import javax.sound.sampled.*;
public class TheGameJaxon extends JFrame
{
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
   private JButton blinky, normal, invisible, speed;
   private JPanel start;
	public TheGameJaxon()
	{
      //sets up the start screen with only the buttons
      //the buttons provide the option to allow the player to choose the type of ball
		super("PONG");
		setSize(WIDTH,HEIGHT);
      setLayout(new BorderLayout());
      //starts the background music
      sound();
      start = new JPanel();
      //I added the buttons to create the start up page
      start.setBackground(Color.gray);
      blinky = new JButton("Blinky Ball");
      start.add(blinky);
      blinky.addActionListener(new Blinky());
      normal = new JButton("Normal Ball");
      start.add(normal);
      normal.addActionListener(new Normal());
      invisible = new JButton("Invisible Ball");
      start.add(invisible);
      invisible.addActionListener(new Invisible());
      speed = new JButton("SpeedUp Ball");
      start.add(speed);
      speed.addActionListener(new Speed());
      add(start, BorderLayout.NORTH);

		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
   //I added in Good Vibrations to make the gameplay better
   public void sound()
    {
      try 
      {
         // Open an audio input stream.
         File soundFile = new File("vibe.wav");
         AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
         // Get a sound clip resource.
         Clip clip = AudioSystem.getClip();
         // Open audio clip and load samples from the audio input stream.
         clip.open(audioIn);
         clip.start();
      }
       catch (UnsupportedAudioFileException e)
       {
         e.printStackTrace();
       } 
      catch (IOException e)
       {
         e.printStackTrace();
       } 
      catch (LineUnavailableException e)
       {
         e.printStackTrace();
      }
   }
   //these classes are the buttons that all have the same action
   //they all start the game but with the different code for the balls
   class Blinky implements ActionListener
   {
      public void actionPerformed(ActionEvent ae)
      {
         PongJaxon2 game = new PongJaxon2();
         ((Component)game).setFocusable(true);
		      getContentPane().add(game);
        setVisible(true);
      }
   }
   
   class Invisible implements ActionListener
   {
      public void actionPerformed(ActionEvent ae)
      {
         PongJaxon3 game = new PongJaxon3();
         ((Component)game).setFocusable(true);
		      getContentPane().add(game);
        setVisible(true);
      }
   }
   
   class Speed implements ActionListener
   {
      public void actionPerformed(ActionEvent ae)
      {
         PongJaxon4 game = new PongJaxon4();
         ((Component)game).setFocusable(true);
		      getContentPane().add(game);
        setVisible(true);
      }
   }
   
   class Normal implements ActionListener
   {
      public void actionPerformed(ActionEvent ae)
      {
         PongJaxon game = new PongJaxon();
         ((Component)game).setFocusable(true);
		      getContentPane().add(game);
        setVisible(true);
      }
   }
   

	public static void main( String args[] )
	{
		TheGameJaxon run = new TheGameJaxon();
	}
}