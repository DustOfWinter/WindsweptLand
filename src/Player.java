import java.util.Random;

public class Player
{
	/////////DEFAULT STATS/////////
	private static final int DEFAULT_MAX_HEALTH = 100; 				//default max health
	private static final int DEFAULT_CURRENT_HEALTH = 100; 			//default starting health
	private static final int UNARMED_DAMAGE = 3; 					//weaponless damage
	private static final double UNARMED_HIT_CHANCE = 0.66; 			//all hit chances are inverted
	private static final double UNARMED_DAMAGE_REDUCTION = 0.0; 	//redution to damage with no armor
	private static final double UNARMED_HIT_CHANCE_REDUCTION = 0.0; //reduction to hit chance with no armor
	
	///////////INSTANCE VARIABLES///////
	private String name;
	private int currentHealth;
	private int maxHealth;
	private Weapon activeWeapon;
	private Armor activeArmor;
	
	////////////CONSTRUCTOR///////////
	public Player(String n)
	{
		setName(n);
		setHealth(DEFAULT_CURRENT_HEALTH);
		setMaxHealth(DEFAULT_MAX_HEALTH);
	}
	
	///////////SETTERS////////////////
	public void setName(String n)
	{
		name = n;
	}
	public void setHealth(int h)
	{
		currentHealth = h;
	}
	public void setMaxHealth(int m)
	{
		maxHealth = m;
	}
	public void setActiveWeapon(Weapon w)
	{
		activeWeapon = w;
	}
	public void setActiveArmor(Armor a)
	{
		activeArmor = a;
	}
	public void damageHealth(int d)
	{
		currentHealth -= d;
	}
	public void recoverHealth(int e)
	{
		currentHealth += e;
	}
	//////////GETTERS/////////////////
	public String getName()
	{
		return name;
	}
	public int getCurrentHealth()
	{
		return currentHealth;
	}
	public int getMaxHealth()
	{
		return maxHealth;
	}
	public Weapon getActiveWeapon()
	{
		return activeWeapon;
	}
	public Armor getActiveArmor()
	{
		return activeArmor;
	}
	public double getDamageReduction()
	{
		if(activeArmor != null)
		{
			return activeArmor.getDamageReduction();
		}
		else
		{
			return UNARMED_DAMAGE_REDUCTION;
		}
	}
	public double getDamageAmount(int d)
	{
		if (activeArmor != null)
		{
			return d - (d * activeArmor.getDamageReduction());
		}
		else
		{
			return d - (d * UNARMED_DAMAGE_REDUCTION);
		}
		
	}
	public double getHitChance()
	{
		if (activeArmor != null && activeWeapon != null)
		{
			return (activeWeapon.getChance() + activeArmor.getChanceReduction());
		}
		else if (activeArmor != null && activeWeapon == null)
		{
			return (UNARMED_HIT_CHANCE + activeArmor.getChanceReduction());
		}
		else if (activeArmor == null && activeWeapon != null)
		{
			return (activeWeapon.getChance() + UNARMED_HIT_CHANCE_REDUCTION);
		}
		else
		{
			return (UNARMED_HIT_CHANCE + UNARMED_HIT_CHANCE_REDUCTION);
		}
	}
	///////////NEEDED METHODS//////////
	public String toString()
	{
		return ("You are " + getName() + ". You are an unlikely adventurer in a strange land." +
							"\nYour current health is: " + printHealth());
	}
	
	public String printHealth()
	{
		return (getCurrentHealth() + "/" + getMaxHealth());
	}
	
	public void printDetails()
	{
		System.out.println("********************");
		if(activeWeapon != null)
		{
			System.out.println(this + "\nYou are currently wielding a " + activeWeapon.getTitle() + ".");
		}
		else
		{
			System.out.println(this + "\nYou don't currently have a weapon equipped.");
		}
		if(activeArmor != null)
		{
			System.out.println("You are currently wearing " + activeArmor.getTitle() + ".");
		}
		else
		{
			System.out.println("You aren't currently wearing armor.");
		}
		if(activeWeapon != null)
		{
			System.out.println("You deal " + activeWeapon.getDamage() + " points of damage.");
		}
		else
		{
			System.out.println("You deal " + UNARMED_DAMAGE + " points of damage.");
		}
		System.out.println("Your chance to hit is %" + ((int)((1.00 - getHitChance()) * 100)) + ".");
		System.out.println("Your damage reduction is %" + ((int)(getDamageReduction() * 100)) + ".");
		System.out.println("********************");
	}
	
	public void attack(String target, Room r)
	{
		Random chancer = new Random();
		
		boolean attacked = false;
		System.out.println("--------------------");
		if(r.indexOfEnemy(target) != -1)
		{
			int index = r.indexOfEnemy(target);
			
			if(activeWeapon != null)
			{
				if (r.getEnemyGroup()[index] instanceof BossType)
				{
					System.out.println("You attack " + ((BossType)r.getEnemyGroup()[index]).getTitle() + " with your " + activeWeapon.getTitle() + "!");
				}
				else
				{
					System.out.println("You attack the " + r.getEnemyGroup()[index].getTitle() + " with your " + activeWeapon.getTitle() + "!");
				}
				if(chancer.nextDouble() >= getHitChance())
				{
					r.getEnemyGroup()[index].damageHealth(activeWeapon.getDamage());
					System.out.println("You deal " + activeWeapon.getDamage() + " points of damage!");
				}
				else
				{
					System.out.println("Your attack misses!");
				}
			}
			else
			{
				if (r.getEnemyGroup()[index] instanceof BossType)
				{
					System.out.println("You lash out with your bare hands at " + ((BossType)r.getEnemyGroup()[index]).getTitle() + " with your bare hands!");
				}
				else
				{
					System.out.println("You lash out with your bare hands at the " + r.getEnemyGroup()[index].getTitle() + "!");
				}
				if(chancer.nextDouble() >= getHitChance())
				{
					r.getEnemyGroup()[index].damageHealth(UNARMED_DAMAGE);
					System.out.println("You deal " + UNARMED_DAMAGE + " points of damage!");
				}
				else
				{
					System.out.println("You miss!");
				}
			}
			if(r.getEnemyGroup()[index].getHealth() <= 0)
			{
				r.getEnemyGroup()[index].die(r);
				r.getEnemyGroup()[index] = null;
			}
		}
		else
		{
			System.out.println("That is not a valid target.");
		}
		System.out.println("--------------------");
	}
	
	public void die()
	{
		System.out.println("********************");
		System.out.println("You health has reached zero, you have died.");
		System.out.println("There is no one to mourn you in this unforgiving land.");
		System.out.println("********************");
	}
}