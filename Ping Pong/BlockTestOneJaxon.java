//(c) A+ Computer Science
//www.apluscompsci.com
//Name -- 

import static java.lang.System.*;
import java.awt.Color;

class BlockTestOneJaxon
{
	public static void main( String args[] )
	{
		BlockJaxon one = new BlockJaxon();
		out.println(one);

		BlockJaxon two = new BlockJaxon(50,50,30,30);
		out.println(two);

		BlockJaxon three = new BlockJaxon(350,350,15,15,Color.RED);
		out.println(three);

		BlockJaxon four = new BlockJaxon(450,50,20,60, Color.GREEN);
		out.println(four);
		
		out.println(one.equals(two));
		out.println(one.equals(one));		
	}
}