public class Armor extends Item
{
	//////////////////VARIABLES///////////////
	
	private double damageReduction; //double between 0.0 and 1.0
	private double chanceReduction; //inverted double between 0.0 and 1.0
	
	//////////////////CONSTRUCTOR/////////////
	
	public Armor(String t,String n, String des, double d, double c)
	{
		super(t,n,des);
		setDamageReduction(d);
		setChanceReduction(c);
	}
	
	///////////////////SETTERS////////////////
	
	public void setDamageReduction(double d)
	{
		damageReduction = d;
	}
	public void setChanceReduction(double c)
	{
		chanceReduction = c;
	}
	
	////////////////////GETTERS///////////////
	
	public double getDamageReduction()
	{
		return damageReduction;
	}
	public double getChanceReduction()
	{
		return chanceReduction;
	}
	
	////////////////////METHODS////////////////
	
	public void use(Player target) //sets to equipped weapon
	{
		if(target.getActiveArmor() != null)
		{
			if((target.getActiveArmor()).equals(this))
			{
				System.out.println("You already have that equipped.");
			}
			else
			{
				System.out.println("You equipped the " + getTitle() + ".");
				target.setActiveArmor(this);
			}
		}
		else
		{
			System.out.println("You equipped the " + getTitle() + ".");
			target.setActiveArmor(this);
		}
	}
	
	public boolean equals(Object o)
	{
		if(o instanceof Armor)
		{
			if(super.equals(o))
			{
				if(this.getDamageReduction() == ((Armor)o).getDamageReduction() && this.getChanceReduction() == ((Armor)o).getChanceReduction())
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
		return (super.toString() + "\nDamage Reduction: %" + (getDamageReduction() * 100) +
				"\nHit Chance Reduction: %" + (getChanceReduction() * 100));
	}
}