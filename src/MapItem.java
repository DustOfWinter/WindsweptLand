public class MapItem extends Item
{
	public MapItem(String t, String n, String d)
	{
		super(t,n,d);
	}
	
	public void use(Room r)
	{
		System.out.println("      N        __________");
		System.out.println("      |       |          |");
		if(r.getName().equals("PENS"))
		{
			System.out.println("   <----->    |    O     |");
		}
		else
		{
			System.out.println("   <----->    |          |");
		};
		System.out.println("      |       |_____  ___|");
		System.out.println("    __V_____   _____||_____   __________");
		System.out.println("   |        | |            | |          |  _________");
		System.out.println("   |        |_|            |_|          |_|         |");
		if(r.getName().equals("QUARTERS"))
		{
			System.out.println("   |    O    _              _            ___________|");
		}
		else if(r.getName().equals("COURTYARD"))
		{
			System.out.println("   |         _      O       _            ___________|");
		}
		else if(r.getName().equals("OUTSIDE"))
		{
			System.out.println("   |         _              _      O     ___________|");
		}
		else if(r.getName().equals("SHACK"))
		{
			System.out.println("   |         _              _            _____O_____|");
		}
		else
		{
			System.out.println("   |         _              _            ___________|");
		}
		System.out.println("   |__  ____| |____  ______| |____  ____|");
		System.out.println("    __||____     __||__       ____||____");
		System.out.println("   |        |   |      |     |          |");
		if(r.getName().equals("INFIRMARY"))
		{
			System.out.println("   |        |   |___O__|     |          |");
		}
		else
		{
			System.out.println("   |        |   |______|     |          |");
		}
		System.out.println("   |        |  _____         |          |");
		System.out.println("   |        |_|     |        |          |");
		if(r.getName().equals("CELLBLOCK"))
		{
			System.out.println("   |    O    _______|        |          |");
		}
		else if(r.getName().equals("CELL2"))
		{
			System.out.println("   |         ____O__|        |          |");
		}
		else
		{
			System.out.println("   |         _______|        |          |");
		}
		System.out.println("   |        |                |          |");
		if(r.getName().equals("FOREST"))
		{
			System.out.println("   |__  ____|                |     O    |");
		}
		else
		{
			System.out.println("   |__  ____|                |          |");	
		}
		System.out.println("    __||____                 |          |");
		System.out.println("   |        |                |          |");
		if(r.getName().equals("CELL1"))
		{
			System.out.println("   |___O____|    __________  |          |");
		}
		else
		{
			System.out.println("   |________|    __________  |          |");
		}
		System.out.println("                |          |_|          |");
		if(r.getName().equals("CLEARING"))
		{
			System.out.println("                |    O      _           |");
		}
		else
		{
			System.out.println("                |           _           |");
		}
		System.out.println("                |          | |          |");
		System.out.println("                |__________| |__________|");
	}
}