public class ExitRoom extends Room
{	
	private Player currentPlayer; 		//current player, only needed to end the game.
	
	public ExitRoom(String t,String d, Player p)
	{
		super(t,d);
		setPlayer(p);
	}
	
	public void setPlayer(Player p)
	{
		currentPlayer = p;
	}

	public void printDescription()
	{
		for(int i = 0;i <= 50;i++)
		{
				System.out.println("");
		}
		System.out.println("********************");
		System.out.println(getDescription());
		System.out.println("********************");
		System.out.println("Congratulations, thank you for playing!");
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		currentPlayer.setHealth(0);
	}
}