public class Room
{
	//INSTANCE VARIABLES
	private String name;
	private String description;						//description of the room
	private boolean[] validExits = new boolean[4];	//true if exit is valid, false if not
	private Room northExit;							//room to the north
	private Room southExit;							//room to the south
	private Room eastExit;							//room to the east
	private Room westExit;							//room to the west
	
	////////////ITEMS/ENEMIES IN ROOM///////////
	Item[] itemsInRoom = new Item[20];				//items within the room
	Enemy[] enemyGroup = new Enemy[5];				//enemies within the room (more than one not advised currently)
	
	////////////CONSTRUCTORS////////////
	public Room(String s, String d)
	{
		setName(s);
		setDescription(d);
	}
	
	///////////////setters/////////////////////
	public void putEnemyInRoom(Enemy e, int index)
	{
		if(e != null)
		{
			enemyGroup[index] = e;
		}
	}
	public void putItemInRoom(Item i, int index)
	{
		if(i != null)
		{
			itemsInRoom[index] = i;
		}
	}
	
	public void setName(String s)
	{
		name = s;
	}
	
	public void setDescription(String d)
	{
		description = d;
	}
	
	public void setExitRoom(int direction, Room exit)
	{
		if(direction <=3 && direction >=0)
		{
			validExits[direction] = true;
			switch(direction)
			{
			case 0:
					northExit = exit;
					break;
			case 1:
					southExit = exit;
					break;
			case 2:
					eastExit = exit;
					break;
			case 3:
					westExit = exit;
					break;
			default:
					break;
			}
		}
	}
	
	////////////////getters//////////////////////
	public String getName()
	{
		return name;
	}
	public Item[] getItemsInRoom()
	{
		return itemsInRoom;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public Enemy[] getEnemyGroup()
	{
		return enemyGroup;
	}
	
	public void showValidExits()
	{
		System.out.print("The valid exits are: ");
		if (validExits[0])
		{
			System.out.print("[NORTH] ");
		}
		if (validExits[1])
		{
			System.out.print("[SOUTH] ");
		}
		if (validExits[2])
		{
			System.out.print("[EAST] ");
		}
		if (validExits[3])
		{
			System.out.print("[WEST] ");
		}
		if (!validExits[0] && !validExits[1] && !validExits[2] && !validExits[3])
		{
			System.out.print("NONE.");
		}
		System.out.println("");
	}
	
	////////////NEEDED METHODS//////////
	
	public void printDescription()
	{
		System.out.println("********************");
		System.out.println(getDescription());
		//showValidExits();
		showItemsInRoom();
		showEnemies();
		System.out.println("********************");
	}
	public Room goTo(String t)
	{
		if(checkExit(t))
		{
			if(t.equals("NORTH"))
			{
				return northExit;
			}
			else if(t.equals("SOUTH"))
			{
				return southExit;
			}
			else if(t.equals("EAST"))
			{
				return eastExit;
			}
			else if(t.equals("WEST"))
			{
				return westExit;
			}
			else
			{
				return null;
			}
		}
		else
		{
			return null;
		}	
	}
	
	
	
	public void showItemsInRoom()
	{
		System.out.print("The items in this room are: ");
		int itemCount = 0;
		for (int index = 0; index < itemsInRoom.length; index++)
		{
			if(itemsInRoom[index] != null)
			{
				System.out.print(itemsInRoom[index].getTitle() + ". ");
				itemCount++;
			}
		}
		if (itemCount == 0)
		{
			System.out.print("NONE");
		}
		System.out.println("");		
	}
	
	public void showEnemies()
	{
		for(int index = 0; index < enemyGroup.length; index++)
		{
			if(enemyGroup[index] != null)
			{	
				if(enemyGroup[index] instanceof BossType)
				{
					System.out.print(((BossType)enemyGroup[index]).getTitle() + " stands firm in this room.\n");
				}
				else
				{
					System.out.print("A " + enemyGroup[index].getTitle() + " lingers in this room.\n");
				}
			}
		}
	}
	
	//////////////PROCESSING METHODS//////////////
	
	public boolean checkExit(String target)
	{
		if(target.equals("NORTH"))
		{
			return validExits[0];
		}
		else if(target.equals("SOUTH"))
		{
			return validExits[1];
		}
		else if(target.equals("EAST"))
		{
			return validExits[2];
		}
		else if(target.equals("WEST"))
		{
			return validExits[3];
		}
		else
		{
			return false;
		}
	}
	
	public boolean checkForEnemies()
	{
		for(int index = 0; index < enemyGroup.length; index++)
		{
			if(enemyGroup[index] != null)
			{
				return true;
			}
		}
		return false;
	}
	
	public int indexOfEnemy(String target)
	{
		for(int index = 0; index < enemyGroup.length; index++)
		{
			if(enemyGroup[index] != null)
			{
				if(target.equals(enemyGroup[index].getName()))
				{
					return index;
				}
				else
				{
					return -1;
				}
			}
		}
		return -1;
	}
	
	public int indexOfItem(String target)
	{
		for(int index = 0; index < itemsInRoom.length; index++)
		{
			if(itemsInRoom[index] != null)
			{
				if(target.equals(itemsInRoom[index].getName()))
				{
					return index;
				}
			}
		}
		return -1;
	}
	public int indexOfFreeSpace()
	{
		for(int index = 0; index < itemsInRoom.length;index++)
		{
			if(itemsInRoom[index] == null)
			{
				return index;
			}
		}
		return -1;
	}
}