//(c) A+ Computer Science
//www.apluscompsci.com
//Name -
 
import javax.swing.JFrame;
import java.awt.Component;

public class TesterJaxon extends JFrame
{
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;

	public TesterJaxon()
	{
		super("PONG TESTER");
		setSize(WIDTH,HEIGHT);

		//getContentPane().add(new BlockTestTwoJaxon());

		//uncomment when you are ready to test the Ball
		//getContentPane().add(new BallTestTwoJaxon());

		PaddleTestTwoJaxon padTest = new PaddleTestTwoJaxon();
		((Component)padTest).setFocusable(true);
		getContentPane().add(padTest);

		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main( String args[] )
	{
		TesterJaxon run = new TesterJaxon();
	}
}