import java.awt.Color;
import java.awt.Graphics;

public class Bird 
{
    public float x, y, vx, vy;
    public static final int RAD = 15;
   
    public Bird()
    {
        x = FlappyBird.WIDTH/2;
        y = FlappyBird.HEIGHT/2;
    }
    public void physics()
    {
        x+=vx;
        y+=vy;
        vy+=0.5f;
    }
    public void update(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.drawOval(Math.round(x-RAD),Math.round(y-RAD),2*RAD,2*RAD);
    }
    public void jump()
    {
        vy = -8;
    }
    
    public void reset()
    {
        x = 640/2;
        y = 640/2;
        vx = vy = 0;
    }
}