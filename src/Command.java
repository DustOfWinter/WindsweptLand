import java.util.StringTokenizer;
import java.util.Scanner;

public class Command
{
	//instance variables
	private Room activeRoom; 		//room player is in
	private Player activePlayer; 	//current player
	private Inventory activeInv; 	//inventory of player
	
	private String command; 		//command USE, TAKE, GO, DROP, ATTACK, LOOK
	private String target; 			//target, name of item, enemy, BAG, or ME.
	
	//constructor
	public Command(Room active, Player player, Inventory inv)
	{
		setActiveRoom(active);
		setActivePlayer(player);
		setActiveInv(inv);
	}
	
	
	//setters
	public void setCommand()
	{
		Scanner keyboard = new Scanner(System.in);
		String c = keyboard.nextLine();
		
		StringTokenizer commandBreaker = new StringTokenizer(c, " ");
		try
		{
			command = commandBreaker.nextToken();
			command = command.toUpperCase();
			target = commandBreaker.nextToken();
			target = target.toUpperCase();
		}
		catch (java.util.NoSuchElementException e)
		{
			command = "NONE";
			target = "NONE";
		}
	}
	public void setActiveRoom(Room active)
	{
		activeRoom = active;
	}
	public void setActivePlayer(Player player)
	{
		activePlayer = player;
	}
	public void setActiveInv(Inventory inv)
	{
		activeInv = inv;
	}
	
	//getters
	public String getCommand()
	{
		return command;
	}
	public String getTarget()
	{
		return target;
	}
	public Room getActiveRoom()
	{
		return activeRoom;
	}
	public Player getActivePlayer()
	{
		return activePlayer;
	}
	public Inventory getActiveInv()
	{
		return activeInv;
	}
	
	//////////////////use this and only this to do a command//////////////
	public void executeCommand()										//
	{																	//
		setCommand();													//
																		//
		if (processCommand() != true)									//
		{																//
			System.out.println("That is not a valid command.");			//
		}																//
		if (activeRoom.checkForEnemies())								//
		{																//
			enemiesAttack();											//
		}																//
	}																	//
	//////////////////////////////////////////////////////////////////////
	
	//processing methods
	public boolean processCommand()
	{
		boolean result = false;
		if (getCommand().equals("LOOK"))
		{
			result = findLookTarget();
			return result;
		}
		else if (getCommand().equals("GO"))
		{
			if(!activeRoom.checkForEnemies())
			{
				goTo();
				return true;
			}
			else
			{
				System.out.println("You can't flee!");
				return true;
			}
			
		}
		else if (getCommand().equals("USE"))
		{
			activeInv.useItem(target, activePlayer, activeRoom);
			return true;
		}
		else if (getCommand().equals("TAKE"))
		{
			activeInv.getItem(target, activeRoom);
			return true;
		}
		else if (getCommand().equals("ATTACK"))
		{
			activePlayer.attack(target, activeRoom);
			return true;
		}
		else if (getCommand().equals("DROP"))
		{
			activeInv.dropItem(target, activeRoom, activePlayer);
			return true;
		}
		else
		{
			return result;
		}
	}
	
	public void enemiesAttack()
	{
		for(int index = 0; index < activeRoom.getEnemyGroup().length; index++)
		{
			if(activeRoom.getEnemyGroup()[index] != null)
			{
				activeRoom.getEnemyGroup()[index].attack(activePlayer);
			}
		}
	}
	
	//look precessor (REQUIRES ROOM CLASS TO BE WRITTEN AND PASSED IN)
	public boolean findLookTarget()
	{
		if (getTarget().equals("ME"))
		{
			activePlayer.printDetails();
			return true;
		}
		else if (getTarget().equals("ROOM"))
		{
			activeRoom.printDescription();
			return true;
		}
		else if (getTarget().equals("BAG"))
		{
			System.out.println("You peer into your bag.");
			activeInv.listItems();
			return true;
		}
		else if(activeInv.indexOfItem(target) != -1)
		{
			System.out.println(activeInv.getItemsInInv()[activeInv.indexOfItem(target)]);
			return true;
		}
		else if(activeRoom.indexOfItem(target) != -1)
		{
			System.out.println(activeRoom.getItemsInRoom()[activeRoom.indexOfItem(target)]);
			return true;
		}
		else if(activeRoom.indexOfEnemy(target) != -1)
		{
			System.out.println(activeRoom.getEnemyGroup()[activeRoom.indexOfEnemy(target)]);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//go processor (REQUIRES ROOM)
	public void goTo()
	{
		if(activeRoom.goTo(target) != null)
		{
			setActiveRoom(activeRoom.goTo(target));
			activeRoom.printDescription();
		}
		else
		{
			System.out.println("That is not a valid exit.");
		}
	}
}