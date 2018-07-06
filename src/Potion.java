//a form of item used for restorative items
public class Potion extends Item
{
	//restorative power
	private int effect; //effectiveness of the item
	
	//constructor
	public Potion(String t,String n, String des, int e)
	{
		super(t,n,des);
		setEffect(e);
	}
	public Potion(Potion p)
	{
		super(p.getTitle(),p.getName(), p.getDescription());
		setEffect(p.getEffect());
	}
	
	//setters
	public void setEffect(int e)
	{
		effect = e;
	}
	
	//getters
	public int getEffect()
	{
		return effect;
	}
	
	//methods
	public void use(Player target)
	{
		int currentHealth = target.getCurrentHealth();
		if(currentHealth == target.getMaxHealth())
		{
			System.out.println("You used the " + getName() + ", but it did nothing. That was foolish.");
		}
		else if(currentHealth + effect > target.getMaxHealth())
		{
			target.setHealth(target.getMaxHealth());
			System.out.println("Your health is now: " + target.printHealth());
		}
		else
		{
			target.recoverHealth(effect);
			System.out.println("Your health is now: " + target.printHealth());
		}
	}
	public boolean equals(Object o)
	{
		if(o instanceof Potion)
		{
			if(super.equals(o))
			{
				if(this.getEffect() == ((Potion) o).getEffect())
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
	
	public String toString()
	{
		return (super.toString() + "\nEffectiveness: " + getEffect() + " pts.");
	}
}