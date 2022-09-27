//(c) A+ Computer Science
//www.apluscompsci.com
//Name - Rhea Jaxon

import java.awt.Color;
import java.awt.Graphics;

public class BallJaxon extends BlockJaxon
{
   //the global variables
	public int xSpeed;
	public int ySpeed;

	public BallJaxon()
	{
		super(200,200);
		xSpeed = 3;
		ySpeed = 1;
	}

	//add the other Ball constructors
	 public BallJaxon(int x, int y)
    {
      super(x,y);
      xSpeed = 3;
		ySpeed = 1;
    }
    //sets the position and size of the ball
    public BallJaxon(int x, int y, int wdth, int hgth)
    {
      super(x,y,wdth,hgth);
      xSpeed = 3;
		ySpeed = 1;
    }
	 //sets the position, size, and color of the ball
	 public BallJaxon(int x, int y, int wdth, int hgth, Color col)
    {
      super(x,y,wdth,hgth,col);
      xSpeed = 3;
		ySpeed = 1;
    }
   //sets the position, size, and speed of the ball 
   public BallJaxon(int x, int y, int wdth, int hgth, int xspd, int yspd)
   {
      super(x,y,wdth,hgth);
      xSpeed = xspd;
		ySpeed = yspd;
   }
   //sets the position, size, color, and speed of the ball
	public BallJaxon(int x, int y, int wdth, int hgth, Color col, int xspd, int yspd)
   {
      super(x,y,wdth,hgth,col);
      xSpeed = xspd;
      ySpeed = yspd;
   }
   //get methods
   public int getXSpeed()
   {
      return xSpeed;
   }
    
   public int getYSpeed()
   {
      return ySpeed;
   }

   //add the set methods
   public void setXSpeed(int xspd)
   {
      xSpeed = xspd;
   }
   
   public void setYSpeed(int yspd)
   {
      ySpeed = yspd;
   }
   //draws the ball after the page refreshes with movement
   public void moveAndDraw(Graphics window)
   {
   	//draw a white ball at old ball location
      draw(window, Color.white);
      //setX
      setX(getX()+xSpeed);
		//setY
      setY(getY()+ySpeed);
		//draw the ball at its new location
      draw(window);
   }
   //checks for balls with the same dimensions and values
	public boolean equals(Object obj)
	{
      PaddleJaxon other = (PaddleJaxon)obj;
      if(getX() + getWidth() >= other.getX() + other.getWidth() && getY() + getHeight() >= other.getY() && getY() <= other.getY() + other.getHeight())
      {
         setYSpeed(getYSpeed() *-1);
         return true;
      }

		return false;
	}   
    
   //add a toString() method  - x , y , width, height, color
   public String toString()
   {
      return super.toString() + "Speed: " + xSpeed + " , " + ySpeed;
   }
}