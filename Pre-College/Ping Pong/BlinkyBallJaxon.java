//(c) A+ Computer Science
//www.apluscompsci.com
//Name - Rhea Jaxon

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

class BlinkyBallJaxon extends BallJaxon
{

   //constructors
   public BlinkyBallJaxon()
   {
		super();
   }
   //sets up position
   public BlinkyBallJaxon(int x, int y)
   {
      super(x,y);

   }
   //sets up postion and size
   public BlinkyBallJaxon(int x, int y, int wid, int ht)
   {
      super(x,y,wid,ht);
  
   }
   //sets up position, size, and speed of the ball
   public BlinkyBallJaxon(int x, int y, int wid, int ht, int xSpd, int ySpd)
   {
      super(x,y,wid,ht,xSpd,ySpd);
   

   }
   //sets up position, color, size, and speed of the ball
   public BlinkyBallJaxon(int x, int y, int wid, int ht, Color col, int xSpd, int ySpd)
   {

      super(x,y,wid,ht,col,xSpd,ySpd);


   }
   //changes the color so that every single time the page refreshes, 
   //random color is picked using Math.random()
   public Color randomColor()
   {
      Random rand = new Random();
   	int r = rand.nextInt(255);		//use Math.random()
 		int g = rand.nextInt(255);
  		int b = rand.nextInt(255);
 		return new Color(r,g,b);
   }

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