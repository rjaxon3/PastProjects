//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;


public class PongJaxon extends Canvas implements KeyListener, Runnable
{
	private BallJaxon ball;
	private PaddleJaxon leftPaddle;
	private PaddleJaxon rightPaddle;
	private boolean[] keys;
	private BufferedImage back;
   private int rightScore, leftScore;

	public PongJaxon()
   {
		//set up all variables related to the game
      //instantiate a Ball
		ball = new BallJaxon(400,100,10,10,Color.BLUE,1,1);
		
		
		//instantiate a left Paddle
		leftPaddle = new PaddleJaxon(100,200,20,80,6);
		
		
		//instantiate a right Paddle
		rightPaddle = new PaddleJaxon(700,200,20,80,6);


      rightScore = 0;
      leftScore = 0;
		keys = new boolean[5];

    
    	setBackground(Color.WHITE);
		setVisible(true);
		
		new Thread(this).start();
		addKeyListener(this);		//starts the key thread to log key strokes
	}
	
   public void update(Graphics window){
	   paint(window);
   }

   public void paint(Graphics window)
   {
		//set up the double buffering to make the game animation nice and smooth
		Graphics2D twoDGraph = (Graphics2D)window;

		//take a snap shop of the current screen and same it as an image
		//that is the exact same width and height as the current screen
		if(back==null)
		   back = (BufferedImage)(createImage(getWidth(),getHeight()));

		//create a graphics reference to the back ground image
		//we will draw all changes on the background image
		Graphics graphToBack = back.createGraphics();
      
      //this sets up the ping pong table
      graphToBack.setColor(Color.white);
      graphToBack.fillRect(0,0,800,600);
      graphToBack.setColor(Color.blue);
      graphToBack.drawRect(300,0,200,580);
      graphToBack.drawRect(10,10,780, 522);
      graphToBack.drawRect(8, 8,784, 526);
      graphToBack.drawLine(400,1,400,600);

		ball.moveAndDraw(graphToBack);
		leftPaddle.draw(graphToBack);
      rightPaddle.draw(graphToBack);
      

		//see if ball hits left wall or right wall
		if(!(ball.getX()>=20 && ball.getX() + ball.getWidth() <=780))
		{
			if(ball.getX()<= 20)
         {
            ball.setXSpeed(0);
            ball.setYSpeed(0);
            rightScore++;
               
         }
         
         if(ball.getX()+ball.getWidth() >= 780)
         {
            ball.setXSpeed(0);
            ball.setYSpeed(0);
            leftScore++;
         }
         //gives the player time before another ball comes
         try
         {
            Thread.currentThread().sleep(10);
         }
         catch(Exception e)
         {
         }
         //this checks if the score is below 10 to continue creating more balls
         if(rightScore < 10 && leftScore < 10)
         {
            ball.setX((int)(Math.random()*50 + 400));
            ball.setY((int)(Math.random()*50 + 300));
         
            int gameChanger = (int)Math.random() * 2;
            if(gameChanger == 0)
            {
               ball.setXSpeed(2);
               ball.setYSpeed(1);
            }
            else
            {
               ball.setXSpeed(-2);
               ball.setYSpeed(1);
            }
         }
		}
      //shows the score but only if below 10
      if(rightScore < 10 && leftScore < 10)
      {
         graphToBack.setColor(Color.BLUE);
         graphToBack.drawString("RightScore: " + rightScore, 100,100);
         graphToBack.drawString("LeftScore: " + leftScore, 600, 100);
      }
      else
      {
      //if one side reaches 10, then game over
      //I put in the option to reset the game to play again
        graphToBack.setColor(Color.BLACK);
        graphToBack.fillRect(0,0,800,600);     
        //game over screen            
        graphToBack.setColor(Color.RED);
        graphToBack.setFont(new Font("Courier", Font.BOLD, 20));
        graphToBack.drawString("Game Over!",350, 260);
        graphToBack.drawString("Press SPACE BAR to play again!", 250, 280);
         
         
   
         if(keys[4] == true)
         { 
         //if space is hit then board resets to start settings
            leftScore = 0;
            rightScore = 0;
            graphToBack.setColor(Color.white);
            graphToBack.fillRect(0,0,800,600);
            graphToBack.setColor(Color.blue);
            graphToBack.drawRect(300,0,200,580);
            graphToBack.drawLine(400,1,400,600);
            graphToBack.drawRect(8, 8,784, 526);
            graphToBack.drawLine(400,1,400,600);

		      ball.moveAndDraw(graphToBack);
		      leftPaddle.draw(graphToBack);
            rightPaddle.draw(graphToBack);
         }
      }
         

		//see if the ball hits the top or bottom wall 
      if(!(ball.getY()>=20 && ball.getY() + ball.getHeight() <= 550))
		{
			ball.setYSpeed(-ball.getYSpeed());
		}

		//see if the ball hits the left paddle
     
		if(ball.getX()<=leftPaddle.getX()+leftPaddle.getWidth()+Math.abs(ball.getXSpeed()) &&
      (ball.getY() >= leftPaddle.getY() && ball.getY() <=leftPaddle.getY() + leftPaddle.getHeight() || ball.getY() + ball.getHeight() >= leftPaddle.getY() && ball.getY() + ball.getHeight() <= leftPaddle.getY() + leftPaddle.getHeight()))
		{
         if(ball.getX() <= leftPaddle.getX() + leftPaddle.getWidth() - Math.abs(ball.getXSpeed()))
            ball.setYSpeed(-ball.getYSpeed());
         else
            ball.setXSpeed(-ball.getXSpeed());
      }
		
		//see if the ball hits the right paddle
		  if((ball.getX()+ ball.getWidth() >= rightPaddle.getX()- Math.abs(ball.getXSpeed()))&&
          (ball.getY()-ball.getHeight() >= rightPaddle.getY() && ball.getY() - ball.getHeight() <= rightPaddle.getY()+rightPaddle.getHeight() ||
           ball.getY()+ ball.getHeight() >= rightPaddle.getY() &&
            ball.getY()+ball.getHeight() <= rightPaddle.getY()+rightPaddle.getHeight()))
          {
            if(ball.getX() + ball.getWidth() >= rightPaddle.getX() + Math.abs(ball.getXSpeed()))
               ball.setYSpeed(-ball.getYSpeed());
            else
               ball.setXSpeed(-ball.getXSpeed());
      
          }   
		
		//see if the paddles need to be moved
      //I added in conditions to stop the paddle from moving beyond the window
      //visibility
      if(keys[0] == true)
		{
			//move left paddle up and draw it on the window
         if(leftPaddle.getHeight() + leftPaddle.getY() > 80)
			   leftPaddle.moveUpAndDraw(graphToBack);
         else
            leftPaddle.setY(0);
		}
		if(keys[1] == true)
		{
			//move left paddle down and draw it on the window
         if(leftPaddle.getY()< 460)
			   leftPaddle.moveDownAndDraw(graphToBack);
         else
            leftPaddle.setY(460);
		}
		if(keys[2] == true)
		{
         if(rightPaddle.getHeight() + rightPaddle.getY() > 80)
			   rightPaddle.moveUpAndDraw(graphToBack);
         else
            rightPaddle.setY(0);
		}
		if(keys[3] == true)
		{
         if(rightPaddle.getY()< 460)
			   rightPaddle.moveDownAndDraw(graphToBack);
         else
            rightPaddle.setY(460);
		}
                  
      

		
		twoDGraph.drawImage(back, null, 0, 0);
	}

	public void keyPressed(KeyEvent e)
	{
		switch(toUpperCase(e.getKeyChar()))
		{
			case 'W' : keys[0]=true; break;
			case 'Z' : keys[1]=true; break;
			case 'I' : keys[2]=true; break;
			case 'M' : keys[3]=true; break;
         case ' ' : keys[4]=true; break;
         
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
         case ' ' : keys[4]=false; break;
  		}
      
	}

	public void keyTyped(KeyEvent e){}
	
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