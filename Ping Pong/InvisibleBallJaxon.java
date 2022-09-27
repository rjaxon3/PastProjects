//(c) A+ Computer Science
//www.apluscompsci.com
//Name - Rhea Jaxon

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

class InvisibleBallJaxon extends BallJaxon
{

   //constructors
   public InvisibleBallJaxon()
   {
		super();
   }
   //this sets up position
   public InvisibleBallJaxon(int x, int y)
   {
      super(x,y);

   }
   //this sets up position and size
   public InvisibleBallJaxon(int x, int y, int wid, int ht)
   {
      super(x,y,wid,ht);
  
   }
   //this sets up position, size, and speed of the ball
   public InvisibleBallJaxon(int x, int y, int wid, int ht, int xSpd, int ySpd)
   {
      super(x,y,wid,ht,xSpd,ySpd);
   

   }
   //this sets up position, size, speed, and color of the ball
   public InvisibleBallJaxon(int x, int y, int wid, int ht, Color col, int xSpd, int ySpd)
   {

      super(x,y,wid,ht,col,xSpd,ySpd);


   }
   //randomizes the color so that when the screen refreshes a new color is picked
   //there are if statements placed to act like a timer until the balls shows up again
   //white was chosen as it was the same color as my pong table
   public Color randomColor()
   {
      Random rand = new Random();
      int r = rand.nextInt(9);
   	if(r != 0)
      return Color.white;
      if(r == 0)
      return getColor();
 		return getColor();
   }
   //refreshes the page and draw the ball, calling the ball color to switch the visiblity
   public void moveAndDraw(Graphics window)
   {
      //draw a white ball at old ball location
      draw(window, Color.white);
      //setX
      setX(getX()+xSpeed);
		//setY
      setY(getY()+ySpeed);
		//draw the ball at its new location
      draw(window, randomColor());

   }
}