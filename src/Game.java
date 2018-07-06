import java.util.Scanner;

public class Game
{
	public static void main(String[] args)
	{
		while(true)
		{
			System.out.println("                 |\\                              ,        ,,              |\\  " + "\n" + 
								";       '         \\\\        ;                   ||        ||   _           \\\\ " + "\n" + 
								"\\\\/\\/\\ \\\\ \\\\/\\\\  / \\\\  _-_, \\\\/\\/\\  _-_  -_-_  =||=       ||  < \\, \\\\/\\\\  / \\\\" + "\n" +
								"|| | | || || || || || ||_.  || | | || \\\\ || \\\\  ||        ||  /-|| || || || ||" + "\n" + 
								"|| | | || || || || ||  ~ || || | | ||/   || ||  ||        || (( || || || || ||" + "\n" +
								"\\\\/\\\\/ \\\\ \\\\ \\\\  \\\\/  ,-_-  \\\\/\\\\/ \\\\,/  ||-'   \\\\,       \\\\  \\/\\\\ \\\\ \\\\  \\\\/  " + "\n" +
								"                                         |/                                    " + "\n" +
								"                                         '                                     ");
			System.out.println("A game by Dustin Himes.");
			System.out.println("\n");
			System.out.println("Main Menu:");
			System.out.println("1 - New Game" + "\n2 - Help" + "\n3 - Exit");
			Scanner input = new Scanner(System.in);
			String choice;
			boolean chosen = false;
			System.out.print("\nEnter your choice: ");
			while(!chosen)
			{
				choice = input.nextLine();
				if (choice.equals(""))
				{
					System.out.println("That choice is not valid, try again.");	
				}
				else
				{
					int tempChoice = choice.charAt(0);
					if(tempChoice > 51 || tempChoice < 49)
					{
						System.out.println("That choice is not valid, try again.");
					}
					else if (tempChoice == 51)
					{
						System.exit(0);
					}
					else if (tempChoice == 50)
					{
						System.out.println("This is a text-based adventure game.\nYou must type in commands to progress.");
						System.out.println("Valid Commands:" +
											"\nLOOK <TARGET>" +
											"\nTAKE <TARGET>" +
											"\nGO <DIRECTION>" +
											"\nUSE <TARGET>" +
											"\nATTACK <TARGET>" +
											"\nDROP <TARGET>");
						System.out.println("Valid keywords are indicated by brackets in item and enemy names.\nUse [ME] to LOOK at yourself for a summary of your character.");
					}
					else if(tempChoice == 49)
					{
						chosen = true;
						for(int i = 0;i <= 50;i++)
						{
						System.out.println("");
						}
					
					
						System.out.println("You awaken alone and cold.\nYour [BAG] rests on the floor next to you. \nYou're face down in the dirt.");
						System.out.println("What is your name?");
						choice = input.nextLine();
					
						for(int i = 0;i <= 50;i++)
						{
						System.out.println("");
						}
					
						Inventory inv = new Inventory();
						Player player = new Player(choice);
					
						//room declarations
						Room room1 = new Room("CELL1","This is a decrepit old prison cell. Faint sunlight shines through the ceiling."
												+ "\nThere is an old, collapsed bed in the corner, \nand a long-dead skeleton atop that." +
												"\nThere is a door that leads out of the cell to the [NORTH].");
						Room room2 = new Room("CELLBLOCK","This is an old cell block." + "\nYou can hear the slight howl of the wind outside\nthrough" +
												" the iron-barred windows that line the upper walls." + "\nYour cell is back to the [SOUTH]."
												+ "\nThere is another cell to the [EAST]. \nThere is a door to the [NORTH].");
						Room room3 = new Room("CELL2","Here is another prison cell." + "\nThe smell of rotting flesh permeates the room." +
												"\nThe floor is slick with rainwater that leaks in through the cracks in the\nceiling."
													+ "\nThe cell block is to the [WEST].");
						Room room4 = new Room("QUARTERS","This appears to be the guard quarters. At some point, at least. The walls are\nlined with empty weapon racks" +
												" and armor stands. Torches flicker slowly in this\nwindowless room." + "\nThe door to the [SOUTH] leads back to" + 
												" the cell block." + "\nThere is another door to the [EAST].");
						Room room5 = new Room("COURTYARD","You find yourself in an old courtyard. It appears that a battle occured here\nlong ago. Rotting skeletons inside rusted" +
												" old plate mail litter the ground." + "\nThere are doors to the [NORTH], [SOUTH], and [WEST]." + "\nThere is a massive gate that leads outside to the [EAST].");
						Room room6 = new Room("PENS","The faint sounds of barking fill this room. It was once a hound pen for the\nguards." + 
												"\nNow it's a large den of feral beasts." + " It would not be wise to stay here long." + "\nThere is a door to the [SOUTH].");
						Room room7 = new Room("INFIRMARY","This appears to be an infirmary. The overturned desks and bottles that litter\nthe floor indicate that it's been" +
												" thoroughly looted." + "\nThere is a door to the [NORTH].");
						Room room8 = new Room("OUTSIDE","You travel through a massive gate and find yourself outside the prison." + "\nWind and rain batters you from all directions." +
												"\nThere is a shack to the [EAST]." + "\nThere is a path into some woods to the [SOUTH]." + "\nThe prison is to the [WEST].");
						Room room9 = new Room("FOREST","The rain struggles to make it's way through the dense forest,\nleaving the ground mostly dry." + 
												" The wind howls through the\ntrees, and a constant stream of leaves drift down to the dirt." + 
												"\nThere are clearings to the [NORTH] and [WEST].");
						Room room10 = new Room("CLEARING","A glowing portal appears before you. This could be the end!" + "\nThe forest is to the [EAST]." +
												"\nThe portal is to the [WEST]");
						Room room11 = new Room("SHACK","This is an old guard shack." + "\nThere is an exit to the [WEST].");
						ExitRoom exit = new ExitRoom("PORTAL","You step through the whirling, hissing portal." +
													" All sense of direction is lost\nasyour body becomes weightless." + 
													"\nSuddenly, you're laying in the darkness of your yard." + 
													"\nA bright star shines directly above.", player);
						
						
						//item declarations
						MapItem map1 = new MapItem("[MAP] OF PRISON","MAP", "A map of the prison and surrounding area.");
							//armor
						Armor leather = new Armor("[LEATHER] ARMOR", "LEATHER","Basic leather armor.\nLow damage reduction, no chance reduction.", 0.10, 0.0);
						Armor plate = new Armor("[PLATE] MAIL", "PLATE", "Heavy plate armor. \nHigh damage reduction, high hit chance reduction.", 0.50, 0.30);
						Armor chain = new Armor("[CHAIN] MAIL", "CHAIN", "Medium chain armor. \nDecent damage reduction, decent hit chance reduction.",0.25, 0.10);
							//weapons
						Weapon knife = new Weapon("BANDIT [KNIFE]","KNIFE","This is a basic survival knife.", 5, 0.25);
						Weapon sword = new Weapon("OLD [SWORD]","SWORD", "An old shortsword. It has seen many battles." , 9, 0.20);
						Weapon greatSword = new Weapon("[GREATSWORD]","GREATSWORD", "A massive twohanded sword. Clumsy and slow.", 20, 0.50);
						Weapon rapier = new Weapon("[RAPIER]","RAPIER", "A fast dueling sword. Low damage, but high accuracy.", 8, 0.10);
							//potions
						Potion standard = new Potion("[POTION]","POTION","A simple healing potion.", 20);
						Potion balm = new Potion("HEALTH [BALM]","BALM", "A potent healing salve.", 40);
						Potion highpotion = new Potion("[HIGH] POTION","HIGH","An advanced healing potion.", 60);
						Potion fairy = new Potion("[FAIRY] IN A BOTTLE","FAIRY", "A fairy in a bottle, restores full health on release.", player.getMaxHealth());
						
						//room items
						room2.putItemInRoom(knife, 0);
						room2.putItemInRoom(new Potion(standard), 1);
						room3.putItemInRoom(leather,0);
						room4.putItemInRoom(map1,0);
						room5.putItemInRoom(sword, 0);
						room6.putItemInRoom(chain,0);
						room7.putItemInRoom(new Potion(balm), 0);
						room8.putItemInRoom(plate,0);
						room8.putItemInRoom(new Potion(highpotion),1);
						room9.putItemInRoom(new Potion(highpotion),0);
						room11.putItemInRoom(new Potion(fairy),0);
						
						//room exits //0 = north, 1 = south, 2 = east, 3 = west
						room1.setExitRoom(0,room2);
						room2.setExitRoom(1,room1);
						room2.setExitRoom(2,room3);
						room2.setExitRoom(0,room4);
						room3.setExitRoom(3,room2);
						room4.setExitRoom(1,room2);
						room4.setExitRoom(2,room5);
						room5.setExitRoom(3,room4);
						room5.setExitRoom(0,room6);
						room5.setExitRoom(1,room7);
						room5.setExitRoom(2,room8);
						room6.setExitRoom(1,room5);
						room7.setExitRoom(0,room5);
						room8.setExitRoom(3,room5);
						room8.setExitRoom(2,room11);
						room8.setExitRoom(1,room9);
						room9.setExitRoom(0,room8);
						room9.setExitRoom(3,room10);
						room10.setExitRoom(2,room9);
						room10.setExitRoom(3,exit);
						room11.setExitRoom(3,room8);
						
						//enemy declarations
						Enemy zombie = new Enemy("[ZOMBIE]","ZOMBIE", "A shambling corpse that yearns for the taste of flesh.", 25, 5, 0.50);
						Enemy dog = new Enemy("FERAL [DOG]","DOG", "A feral dog. It foams from the mouth and growls menacingly.", 15, 8, 0.33);
						Enemy knight = new Enemy("CORRUPT [KNIGHT]","KNIGHT", "A corrupted knight of the Old Order.", 30, 10, 0.40);
						Enemy bandit = new Enemy("[BANDIT]","BANDIT","A thief of the shadows.",20,6,0.20);
						Enemy darkKnight = new Enemy("DARK [KNIGHT]","KNIGHT","An elite knight of the Old Order, fallen to darkness.",40,15,0.50);
						
						//boss enemy
						BossType ivan = new BossType("[IVAN] THE LOST","IVAN","The leader of the Old Order, lost forever.",60,20,0.33);
						ivan.setDialogue(0,("Ah, so you've made it this far, " + player.getName() + ".\nBut you're too late."));
						ivan.setDialogue(1,("The portal is about to close, and you'll never stop me!"));
						ivan.setDialogue(2,("Go ahead and TRY!"));
						ivan.setDeathSpeech("How could this be? How did you do it?");
						
						//special item enemies
						Enemy zombiePotion = new Enemy(zombie);
						zombiePotion.setHeldItem(new Potion(standard));
						
						Enemy zombieBalm = new Enemy(zombie);
						zombieBalm.setHeldItem(new Potion(balm));
						
						darkKnight.setHeldItem(greatSword);
						
						Enemy banditRapier = new Enemy(bandit);
						banditRapier.setTitle("[BANDIT] KING");
						banditRapier.setHeldItem(rapier);
						
						//room enemies
						room3.putEnemyInRoom(new Enemy(zombiePotion), 0);
						room4.putEnemyInRoom(new Enemy(knight),0);
						room6.putEnemyInRoom(new Enemy(dog),0);
						room8.putEnemyInRoom(new Enemy(darkKnight),0);
						room9.putEnemyInRoom(new Enemy(bandit),0);
						room9.putEnemyInRoom(new Enemy(banditRapier),1);
						room11.putEnemyInRoom(new Enemy(zombie),0);
						room11.putEnemyInRoom(new Enemy(zombieBalm),1);
						room10.putEnemyInRoom(ivan,0);
						
						//the command
						Command com = new Command(room1,player,inv);
						
						//starting the game
						room1.printDescription();
						
						//as long as the player is alive, game continues.
						while (player.getCurrentHealth() > 0)
						{
							com.executeCommand();
						}
					}
				}
			}
		}
	}
}