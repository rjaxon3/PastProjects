public class Tile
{
	
		int val;
		
	public Tile()
	{
		//sets tile value to 0 initially
		val = 0;
	}
	
	public Tile(int number)
	{
		//gives the tile a number value for the game
		val = number;
	}
	
	public int getVal()
	{
		return val;
	}
	
	public void setVal(int val)
	{
		this.val = val;
	}
	
	public String toString()
	{
		return "" + val + "";
	}
	
	
}
