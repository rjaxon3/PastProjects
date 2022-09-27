//(c) A+ Computer Science
//www.apluscompsci.com
//Name - Rhea Jaxon

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

class SpeedUpBallJaxon extends BallJaxon
{

   //these constructors take care of its position, speed, color, and size

   public SpeedUpBallJaxon()
   {

   }
   //sets up position
   public SpeedUpBallJaxon(int x, int y)
   {
      super(x,y);

   }
   //sets up position and speed of the ball
   public SpeedUpBallJaxon(int x, int y, int xSpd, int ySpd)
   {
      super(x,y,xSpd,ySpd);

   }
   //sets up position, speed, and size of the ball
   public SpeedUpBallJaxon(int x, int y, int wid, int ht, int xSpd, int ySpd)
   {
      super(x,y,wid,ht,xSpd,ySpd);
   
   }

   //sets up position, speed, size, and color of the ball
   public SpeedUpBallJaxon(int x, int y, int wid, int ht, Color col, int xSpd, int ySpd)
   {
      super(x,y,wid,ht,col,xSpd,ySpd);

   }
   //set method
   public void setXSpeed( int xSpd )
   {

      xSpeed = xSpd;

   }
   //changes the speed of the ball with the collision where the speed turns 0
   public void setYSpeed( int ySpd )
   {
      if(ySpd == 0)
      {
         ySpd = 0;
      }
      
      if(ySpd > 0)
      {
         super.setYSpeed(ySpd + 1);
      }
      
      if(ySpd < 0)
      {
         super.setYSpeed(ySpd - 1);
      }

   }
   //draws the ball after the page refreshes to account for movement
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

}

