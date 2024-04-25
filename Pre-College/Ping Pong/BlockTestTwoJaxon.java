//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Canvas;

public class BlockTestTwoJaxon extends Canvas
{
	public BlockTestTwoJaxon()
	{
		setBackground(Color.WHITE);
	}

	public void paint(Graphics window)
	{
		BlockJaxon one = new BlockJaxon();
		one.draw(window);

		BlockJaxon two = new BlockJaxon(50,50,30,30);
		two.draw(window);

		BlockJaxon three = new BlockJaxon(350,350,15,15,Color.RED);
		three.draw(window);

		//two.draw(window, Color.white);

		BlockJaxon four = new BlockJaxon(450,50,20,60, Color.GREEN);
		four.draw(window);
      
      BlockJaxon five = new BlockJaxon(150,200,30,30, Color.MAGENTA);
      five.draw(window);
      
      BlockJaxon six = new BlockJaxon(30,30,30,30);
      six.draw(window);
		
		//add more test cases			
	}
   public static void main( String args[] )
	{
      BlockTestTwoJaxon test = new BlockTestTwoJaxon();      
   }

}