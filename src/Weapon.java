//a form of item used for weapons

public class Weapon extends Item
{
	private int damage;			//the damage the weapon can inflict
	private double chanceToHit;	//inverted double, used for chance to hit.
	
	//constructor
	public Weapon(String t,String n, String des, int d, double c)
	{
		super(t,n,des);
		setDamage(d);
		setChance(c);
	}
	
	//setters
	public void setDamage(int d)
	{
		damage = d;
	}
	public void setChance(double c)
	{
		chanceToHit = c;
	}
	//getters
	public int getDamage()
	{
		return damage;
	}
	public double getChance()
	{
		return chanceToHit;
	}
	
	
	//methods
	public void use(Player target) //sets to equipped weapon
	{
		if(target.getActiveWeapon() != null)
		{
			if((target.getActiveWeapon()).equals(this))
			{
				System.out.println("You already have that equipped.");
			}
			else
			{
				System.out.println("You equipped the " + getTitle() + ".");
				target.setActiveWeapon(this);
			}
		}
		else
		{
			System.out.println("You equipped the " + getTitle() + ".");
			target.setActiveWeapon(this);
		}
	}
	public boolean equals(Object o)
	{
		if(o instanceof Weapon)
		{
			if(super.equals(o))
			{
				if(this.getDamage() == ((Weapon) o).getDamage() && this.getChance() == ((Weapon) o).getChance())
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
		return (super.toString() + "\nDamage: " + getDamage() + " pts." +
				"\nHit Chance: %" + ((1.0 - getChance()) * 100));
	}
}