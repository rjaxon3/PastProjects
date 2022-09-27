//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;

public class PaddleTestTwoJaxon extends Canvas implements KeyListener, Runnable
{
	private BallJaxon ball;
	private PaddleJaxon leftPaddle;
   private PaddleJaxon rightPaddle;
	private boolean[] keys;		//keeps track of what keys are pressed

	public PaddleTestTwoJaxon()
	{
		//set up all game variables

		//instantiate a Ball
		ball = new BallJaxon(100,90,10,10,Color.BLUE,-9,2);
		
		
		//instantiate a left Paddle
		leftPaddle = new PaddleJaxon(100,100,30,50,6);
		
		
		//instantiate a right Paddle
		rightPaddle = new PaddleJaxon(700,200,30,50,6);


		keys = new boolean[5];


		//set up the Canvas
		setBackground(Color.WHITE);
		setVisible(true);

		this.addKeyListener(this);
		new Thread(this).start();
	}
	
	public void update(Graphics window)
	{
		paint(window);
	}

	public void paint(Graphics window)
	{
		ball.moveAndDraw(window);
		leftPaddle.moveUpAndDraw(window);
      leftPaddle.moveDownAndDraw(window);
		rightPaddle.moveUpAndDraw(window);
      rightPaddle.moveDownAndDraw(window);


		if(!(ball.getX()>=10 && ball.getX()<=550))
		{
			ball.setXSpeed(-ball.getXSpeed());
		}

		if(!(ball.getY()>=10 && ball.getY()<=450))
		{
			ball.setYSpeed(-ball.getYSpeed());
		}

		if(keys[0] == true)
		{
			//move left paddle up and draw it on the window
			leftPaddle.moveUpAndDraw(window);
		}
		if(keys[1] == true)
		{
			//move left paddle down and draw it on the window
         leftPaddle.moveDownAndDraw(window);

		}
		if(keys[2] == true)
		{
         rightPaddle.moveUpAndDraw(window);

		}
		if(keys[3] == true)
		{
         rightPaddle.moveDownAndDraw(window);

		}
	}

	public void keyPressed(KeyEvent e)
	{
		switch(toUpperCase(e.getKeyChar()))
		{
			case 'W' : keys[0]=true; break;
			case 'Z' : keys[1]=true; break;
			case 'I' : keys[2]=true; break;
			case 'M' : keys[3]=true; break;
		}
	}

	public void keyReleased(KeyEvent e)
	{
		switch(toUpperCase(e.getKeyChar()))
		{
			case 'W' : keys[0]=false; break;
			case 'Z' : keys[1]=false; break;
			case 'I' : keys[2]=false; break;
			case 'M' : keys[3]=false; break;
		}
	}

	public void keyTyped(KeyEvent e)
	{
		//no code needed here
	}
	
   public void run()
   {
   	try
   	{
   		while(true)
   		{
   		   Thread.currentThread().sleep(8);
            repaint();
         }
      }catch(Exception e)
      {
      }
  	}		
}