import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import javax.swing.JPanel;

public class GamePanel extends JPanel
{
    
    private Bird bird;
    private ArrayList<Rectangle> rects;
    private FlappyBird fb;
    private Font scoreFont, pauseFont;
    public static final Color bg = new Color(0, 158, 158);
    public static final int PIPE_W = 50, PIPE_H = 30;

    public GamePanel(FlappyBird fb, Bird bird, ArrayList<Rectangle> rects)
    {
        this.fb = fb;
        this.bird = bird;
        this.rects = rects;
        scoreFont = new Font("Comic Sans MS", Font.BOLD, 18);
        pauseFont = new Font("Arial", Font.BOLD, 48);
        
    }
    @Override
    public void paintComponent(Graphics g)
    {
        g.setColor(bg);
        g.fillRect(0,0,FlappyBird.WIDTH,FlappyBird.HEIGHT);
        bird.update(g);
        g.setColor(Color.RED);
        for(Rectangle r : rects)
        {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.GREEN);
            g2d.fillRect(r.x, r.y, r.width, r.height);
            AffineTransform old = g2d.getTransform();
            g2d.translate(r.x+PIPE_W/2, r.y+PIPE_H/2);
            if(r.y < FlappyBird.HEIGHT/2)
            {
                g2d.translate(0, r.height);
                g2d.rotate(Math.PI);
            }
            g2d.drawRect(-PIPE_W/2, -PIPE_H/2, GamePanel.PIPE_W, GamePanel.PIPE_H);
            g2d.drawRect(-PIPE_W/2, PIPE_H/2, GamePanel.PIPE_W, r.height);
            g2d.fillRect(-PIPE_W/2, -PIPE_H/2, GamePanel.PIPE_W, GamePanel.PIPE_H);
            g2d.fillRect(-PIPE_W/2, PIPE_H/2, GamePanel.PIPE_W, r.height);
            g2d.setTransform(old);
        }
        g.setFont(scoreFont);
        g.setColor(Color.BLACK);
        if(fb.getScore() > 0)
        	g.drawString("Score: "+ fb.getScore(), 10, 20);
        
        if(fb.paused())
        {
            g.setFont(pauseFont);
            g.setColor(new Color(0,0,0,170));
            g.drawString("PAUSED", FlappyBird.WIDTH/2-100, FlappyBird.HEIGHT/2-100);
            g.drawString("PRESS SPACE TO BEGIN", FlappyBird.WIDTH/2-300, FlappyBird.HEIGHT/2+50);
        }
    }
}