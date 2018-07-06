public class Item
{
	///////////variables////////
	protected String title;			//title of item, for long names
	protected String name;			//name of the item
	protected String description;	//description of the item
	
	//////////CONSTRUCTOR//////
	public Item(String t,String n, String des)
	{
		setTitle(t);
		setName(n);
		setDescription(des);
	}
	
	/////////SETTERS///////////
	
	public void setTitle(String t)
	{
		title = t;
	}
	public void setName(String n)
	{
		name = n;
	}
	public void setDescription(String d)
	{
		description = d;
	}
	
	/////////GETTERS///////////
	public String getName()
	{
		return name;
	}
	public String getDescription()
	{
		return description;
	}
	public String getTitle()
	{
		return title;
	}
	
	///////////METHODS/////////
	public void use(Player p)
	{
		//no purpose in base class, must override
	}
	
	public boolean equals(Object o)
	{
		if(o != null)
		{
			if(o instanceof Item)
			{
				if(this.getName().equals(((Item)o).getName()))
				{
					if(this.getTitle().equals(((Item)o).getTitle()))
					{
						return true;
					}
					else
					{
						return false;
					}
				}
				else
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	public String toString()
	{
		return (getTitle() + ":\n" +
				getDescription());
	}
}