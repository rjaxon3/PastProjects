import java.util.ArrayList;
import java.util.Scanner;

public class Calc 
{
	double h, x, y;
	String prime;
	ArrayList<Double> ks = new ArrayList<Double>(4);
	
	
	public Calc(double h, double x, double y, String prime)
	{
		this.h = h;
		this.x = x;
		this.y = y;
		this.prime = prime;
	}
	
	public double getH()
	{
		return h;
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public String getPrime()
	{
		return prime;
	}
	
	public void setX(double val)
	{
		x = val;
	}
	
	public void setY(double val)
	{
		y = val;
	}
	
	public void setH(double val)
	{
		h = val;
	}
	
	public void setPrime(String val)
	{
		prime = val;
	}
	
	public double calcF(double xnew, double ynew)	// enter in the new y' formula each time
	{
		return 2*xnew*ynew;
	}
	
	public ArrayList<Double> RK()
	{
		
		ks.add(calcF(x,y));
		ks.add(calcF(x + 0.5*h, y+0.5*h*ks.get(0)));
		ks.add(calcF(x + 0.5*h, y+ 0.5*h*ks.get(1)));
		ks.add(calcF(x+h,y+h*ks.get(2)));
		
		ArrayList<Double> answer = new ArrayList<Double>();
		answer.add(x+h);
		answer.add(y + (h / 6) * (ks.get(0) + 2*ks.get(1) + 2*ks.get(2) + ks.get(3)));
		
		return answer;
	}
	
	
	public static void main(String[] args) 
    {
		Scanner kb = new Scanner(System.in);
		
		System.out.println("Enter the h value :: ");
		double h = kb.nextDouble();
		
		System.out.println("Enter the x initial value :: ");
		double x = kb.nextDouble();
		
		System.out.println("Enter the y initial value :: ");
		double y = kb.nextDouble();
		
		System.out.println("Enter the y' equation :: ");
		String thing = kb.next();
		
		Calc test = new Calc(h,x,y,thing);
		System.out.println(test.RK());
		
    }
}
