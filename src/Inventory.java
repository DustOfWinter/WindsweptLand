public class Inventory
{
	///////ITEMS THE PLAYER OWNS///////
	private Item[] itemsInInv = new Item[10];	//items held by the player

	
	/////////////NEEDED METHODS///////////
	public void useItem(String target, Player p, Room r)
	{
		if(indexOfItem(target) != -1)
		{
			int index = indexOfItem(target);
			if(itemsInInv[index] instanceof MapItem)
			{
				((MapItem)itemsInInv[index]).use(r);
			}
			else
			{
				itemsInInv[index].use(p);
				if(itemsInInv[index] instanceof Potion) //weapons stay in inventory while equipped.
				{
					itemsInInv[index] = null;
				}
			}
		}
		else
		{
			System.out.println("You do not have that item.");
		}
	}
	
	public void getItem(String i, Room r)
	{
		if(r.indexOfItem(i) != -1)
		{
			int index1 = r.indexOfItem(i);
			int index2 = indexOfFreeSpace();
			
			if(index2 != -1)
			{
				itemsInInv[index2] = r.getItemsInRoom()[index1];
				
				System.out.println("You picked up the " + itemsInInv[index2].getTitle() + ".");
				
				r.getItemsInRoom()[index1] = null;
			}
			else
			{
				System.out.println("You cannot carry any more items.");
			}
			
		}
		else
		{
			System.out.println("That is not a valid target.");
		}
	}
	
	public void dropItem(String target, Room r, Player player)
	{
		if(indexOfItem(target) != -1)
		{
			if(r.indexOfFreeSpace() != -1)
			{
				r.getItemsInRoom()[r.indexOfFreeSpace()] = itemsInInv[indexOfItem(target)];
				if(player.getActiveWeapon() != null && player.getActiveWeapon().getTitle().equals(itemsInInv[indexOfItem(target)].getTitle()))
				{
					System.out.println("You unequip the " + player.getActiveWeapon().getTitle() + ".");
					player.setActiveWeapon(null);
				}
				if(player.getActiveArmor() != null && player.getActiveArmor().getTitle().equals(itemsInInv[indexOfItem(target)].getTitle()))
				{
					System.out.println("You unequip the " + player.getActiveArmor().getTitle() + ".");
					player.setActiveArmor(null);
				}
				System.out.println("You drop the " + itemsInInv[indexOfItem(target)].getTitle() + ".");
				itemsInInv[indexOfItem(target)] = null;
			}
			
		}
		else
		{
			System.out.println("You don't have that item.");
		}
	}
	
	
	public Item[] getItemsInInv()
	{
		return itemsInInv;
	}
	
	public void listItems()
	{
		boolean itemsHeld = false;
		System.out.println("********************");
		for(int index = 0; index < itemsInInv.length; index++)
		{
			if(itemsInInv[index] != null)
			{
				System.out.println(itemsInInv[index]);
				itemsHeld = true;
				System.out.println("********************");
			}
		}
		if(!itemsHeld)
		{
			System.out.println("You don't currently have any items.");
			System.out.println("********************");
		}
	}
	public int indexOfFreeSpace()
	{
		for(int index = 0; index < itemsInInv.length;index++)
		{
			if(itemsInInv[index] == null)
			{
				return index;
			}
		}
		return -1;
	}
	public int indexOfItem(String target)
	{
		for(int index = 0; index < itemsInInv.length; index++)
		{
			if(itemsInInv[index] != null)
			{
				if(target.equals(itemsInInv[index].getName()))
				{
					return index;
				}
			}
		}
		return -1;
	}
}