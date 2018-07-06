import java.util.Random;

public class Enemy
{
	//instance variables
	protected int health;			//health of the enemy
	protected int damage;			//max damage the enemy can inflict
	protected double chanceToHit;	//inverted double of hit chance
	protected String title;			//title used for long names
	protected String name;			//name of enemy
	protected String description;	//description of enemy(only seen if LOOKed)
	protected Item heldItem;		//item held, dropped on death
	
	//constructor
	public Enemy(String t,String n, String des, int h, int d, double c)
	{
		setTitle(t);
		setName(n);
		setDescription(des);
		setDamage(d);
		setChance(c);
		setHealth(h);
	}
	public Enemy(Enemy e)
	{
		setTitle(e.getTitle());
		setName(e.getName());
		setDescription(e.getDescription());
		setDamage(e.getDamage());
		setHealth(e.getHealth());
		setChance(e.getChance());
		if(e.getHeldItem() != null)
		{
			setHeldItem(e.getHeldItem());
		}
	}
	
	//setters
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
	public void setDamage(int dm)
	{
		damage = dm;
	}
	public void setHealth(int hp)
	{
		health = hp;
	}
	public void setChance(double c)
	{
		chanceToHit = c;
	}
	public void damageHealth(int d)
	{
		health -= d;
	}
	public void recoverHealth(int e)
	{
		health += e;
	}
	public void setHeldItem(Item i)
	{
		heldItem = i;
	}
	
	//getters
	public String getTitle()
	{
		return title;
	}
	public String getName()
	{
		return name;
	}
	public String getDescription()
	{
		return description;
	}
	public int getDamage()
	{
		return damage;
	}
	public int getHealth()
	{
		return health;
	}
	public double getChance()
	{
		return chanceToHit;
	}
	public Item getHeldItem()
	{
		return heldItem;
	}
	
	//methods
	public void attack(Player p)
	{
		System.out.println("--------------------");
		System.out.println("The " + getTitle() + " attacks you!");
		Random chancer = new Random();
		if(chancer.nextDouble() > getChance())
		{
			System.out.println("It deals " + ((int)p.getDamageAmount(getDamage())) + " points of damage!");
			p.damageHealth(((int)p.getDamageAmount(getDamage())));
			if(p.getCurrentHealth() <= 0)
			{
				p.die();
			}
		}
		else
		{
			System.out.println("It's attack misses!");
		}
		System.out.println("--------------------");
	}
	
	public void die(Room r)
	{
		System.out.println("The " + getTitle() + " collapses.");
		if(heldItem != null)
		{
			if(r.indexOfFreeSpace() != -1)
			{
				System.out.println("The " + getTitle() + " dropped a " + getHeldItem().getTitle() + "!");
				r.getItemsInRoom()[r.indexOfFreeSpace()] = heldItem;
				heldItem = null;
			}
		}
	}
	
	public String toString()
	{
		return (getTitle() + ":\n" + getDescription() + "\n" + "It has " + getHealth() + " points of health");
	}
}