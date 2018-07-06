import java.util.Random;

public class BossType extends Enemy
{
	private int dialogueCount = 0;				//used to count the lines of dialogue spoken
	private String[] dialogue = new String[3];	//used to have the character speak
	private String deathSpeech;
	
	
	public BossType(String t, String n, String des, int h, int d, double c)
	{
		super(t,n,des,h,d,c);
	}
	public void setDialogue(int index, String d)
	{
		dialogue[index] = d;
	}
	public void setDeathSpeech(String s)
	{
		deathSpeech = s;
	}
	public void attack(Player p)
	{
		System.out.println("--------------------");
		if(dialogueCount == 3)
		{
			System.out.println(getTitle() + " attacks you!");
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
				System.out.println(getTitle() + "'s attack misses!");
			}
		}
		else
		{
			System.out.println(getTitle() + " says, \"" + dialogue[dialogueCount] + "\"");
			dialogueCount++;
		}
		System.out.println("--------------------");
	}
	
	public void die(Room r)
	{
		System.out.println(getTitle() + " cries out, \"" + deathSpeech + "\"");
		System.out.println(getTitle() + " collapses.");
		if(heldItem != null)
		{
			if(r.indexOfFreeSpace() != -1)
			{
				System.out.println(getTitle()  + " dropped a " + getHeldItem().getTitle() + "!");
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