
public class Board
{
	public Tile[][] board;
	int grid = 4;
	int bord = 0;
	public int score = 0;
	
	public Board()
	{
		board = new Tile[4][4];
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board[i].length; j++)
			{
				board[i][j] = new Tile();
			}
				
		}
	}
	
	public Board(int loss, int grid)
	{
		this.grid = grid;
		board = new Tile[grid][grid];
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board[i].length; j++)
			{
				board[i][j] = new Tile((loss + i + j) * (i + j));
			}
		}
	}
	
	public Tile[][] getBoard()
	{
		return board;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public int getHighTile()
	{
		int high = board[0][0].getVal();
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board.length; j++)
			{
				if(board[i][j].getVal() > high)
				{
					high = board[i][j].getVal();
				}
			}
		}
		return high;
			
	}
	
	public void print()
    {
        for ( int i = 0; i < board.length; i++ )
        {
            for ( int j = 0; j < board[i].length; j++ )
            {
                String s = board[i][j].toString() + " ";
                System.out.print( s );
            }
            System.out.println( "" );
        }
        System.out.println( "Score: " + score );
    }
	
	public String toString()
    {
        String s = "";
        for ( int i = 0; i < board.length; i++ )
        {
            for ( int j = 0; j < board[i].length; j++ )
            {
                s += board[i][j].toString() + " ";
            }
            s += "\n";
        }
        return s;
    }
	
	public void spawn()
    {
        boolean empty = true;
        while ( empty )
        {
            int row = (int)( Math.random() * 4);
            int col = (int)( Math.random() * 4);
            double x = Math.random() * 10;
            if ( board[row][col].getVal() == 0 )
            {
                if (x < 2)
                {
                    board[row][col] = new Tile(4);
                    empty = false;
                }
                else
                {
                    board[row][col] = new Tile(2);
                    empty = false;
                }
            }

        }

    }
	
	public boolean blackOut()
    {
        int count = 0;
        for ( int i = 0; i < board.length; i++)
        {
            for ( int j = 0; j < board[i].length; j++)
            {
                if ( board[i][j].getVal() > 0)
                {
                    count++;
                }
            }
        }
        if ( count == 16 )
        {
            return true;
        }
        return false;
    }
	
    public boolean gameOver()
    {
        int count = 0;
        for ( int i = 0; i < board.length; i++ )
        {
            for ( int j = 0; j < board[i].length; j++ )
            {
                if ( board[i][j].getVal() > 0 )
                {
                    if ( i == 0 && j == 0 )
                    {
                        if ( board[i][j].getVal() != board[i + 1][j].getVal()
                            && board[i][j].getVal() != board[i][j + 1].getVal() )
                        {
                            count++;
                        }
                    }
                    else if ( i == 0 && j == 3 )
                    {
                        if ( board[i][j].getVal() != board[i + 1][j].getVal()
                            && board[i][j].getVal() != board[i][j - 1].getVal() )
                        {
                            count++;
                        }
                    }
                    else if ( i == 3 && j == 3 )
                    {
                        if ( board[i][j].getVal() != board[i - 1][j].getVal()
                            && board[i][j].getVal() != board[i][j - 1].getVal() )
                        {
                            count++;
                        }
                    }
                    else if ( i == 3 && j == 0 )
                    {
                        if ( board[i][j].getVal() != board[i - 1][j].getVal()
                            && board[i][j].getVal() != board[i][j + 1].getVal() )
                        {
                            count++;
                        }
                    }
                    else if ( i == 0 && ( j == 1 || j == 2 ) )
                    {
                        if ( board[i][j].getVal() != board[i + 1][j].getVal()
                            && board[i][j].getVal() != board[i][j + 1].getVal()
                            && board[i][j].getVal() != board[i][j - 1].getVal() )
                        {
                            count++;
                        }
                    }
                    else if ( i == 3 && ( j == 1 || j == 2 ) )
                    {
                        if ( board[i][j].getVal() != board[i - 1][j].getVal()
                            && board[i][j].getVal() != board[i][j + 1].getVal()
                            && board[i][j].getVal() != board[i][j - 1].getVal() )
                        {
                            count++;
                        }
                    }
                    else if ( j == 0 && ( i == 1 || i == 2 ) )
                    {
                        if ( board[i][j].getVal() != board[i][j + 1].getVal()
                            && board[i][j].getVal() != board[i - 1][j].getVal()
                            && board[i][j].getVal() != board[i + 1][j].getVal() )
                        {
                            count++;
                        }
                    }
                    else if ( j == 3 && ( i == 1 || i == 2 ) )
                    {
                        if ( board[i][j].getVal() != board[i][j - 1].getVal()
                            && board[i][j].getVal() != board[i - 1][j].getVal()
                            && board[i][j].getVal() != board[i + 1][j].getVal() )
                        {
                            count++;
                        }
                    }
                    else
                    {
                        if ( board[i][j].getVal() != board[i][j - 1].getVal()
                            && board[i][j].getVal() != board[i][j + 1].getVal()
                            && board[i][j].getVal() != board[i - 1][j].getVal()
                            && board[i][j].getVal() != board[i + 1][j].getVal() )
                        {
                            count++;
                        }
                    }
                }
            }
        }
        if ( count == 16 )
        {
            return true;
        }
        return false;
    }
    
    private void vert(int row, int col, String direction)
    {
        Tile initial = board[bord][col];
        Tile compare = board[row][col];
        if(initial.getVal() == 0 || initial.getVal() == compare.getVal() )
        {
            if(row > bord || (direction.equals("down") && (row < bord)))
            {
                int addScore = initial.getVal() + compare.getVal();
                if(initial.getVal() != 0 )
                {
                    score += addScore;
                }
                initial.setVal(addScore);
                compare.setVal(0);
            }
        }
        else
        {
            if(direction.equals("down"))
            {
                bord--;
            }
            else
            {
                bord++;
            }
            vert(row, col, direction);
        }
    }
    
    private void hor( int row, int col, String direction )
    {
        Tile initial = board[row][bord];
        Tile compare = board[row][col];
        if(initial.getVal() == 0 || initial.getVal() == compare.getVal())
        {
            if(col > bord || ( direction.equals( "right" ) && ( col < bord)))
            {
                int addScore = initial.getVal() + compare.getVal();
                if(initial.getVal() != 0)
                {
                    score += addScore;
                }
                initial.setVal(addScore);
                compare.setVal(0);
            }
        }
        else
        {
            if (direction.equals("right"))
            {
                bord--;
            }
            else
            {
                bord++;
            }
            hor(row, col, direction);
        }
    }
    
    public void up()
    {
        for(int i = 0; i < grid; i++)
        {
            bord = 0;
            for(int j = 0; j < grid; j++)
            {
                if (board[j][i].getVal() != 0)
                {
                    if (bord <= j)
                    {
                        vert(j, i, "up");
                    }
                }
            }
        }
    }
    
    public void down()
    {
        for(int i = 0; i < grid; i++)
        {
            bord = (grid - 1);
            for(int j = grid - 1; j >= 0; j--)
            {
                if(board[j][i].getVal() != 0 )
                {
                    if( bord >= j )
                    {
                        vert(j, i, "down");
                    }
                }
            }
        }
    }
    
    public void left()
    {
        for(int i = 0; i < grid; i++)
        {
            bord = 0;
            for(int j = 0; j < grid; j++)
            {
                if(board[i][j].getVal() != 0)
                {
                    if(bord <= j)
                    {
                        hor(i, j, "left");
                    }
                }
            }
        }
    }
    
    public void right()
    {
        for(int i = 0; i < grid; i++)
        {
            bord = (grid - 1);
            for(int j = (grid - 1); j >= 0; j--)
            {
                if( board[i][j].getVal() != 0)
                {
                    if(bord >= j)
                    {
                        hor(i, j, "right");
                    }
                }
            }
        }
    }
    
    

	
	
}
