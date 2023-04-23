import java.util.*;

public class TeamProject 
{
	private static ArrayList<Team> teams;
	private static char optionMain;
	private static char optionTeam;
	private static char optionExtra;
	private static Scanner scnr;
	private static int teamIndex = -1;
	private static Team teamCurrent;
	private static Helper helper;
	
	public static void main(String[] args) 
	{
		scnr = new Scanner(System.in);
		teams = new ArrayList<Team>();
		helper = new Helper();
		
		// Method below added only to add fake data
		fakeData();
		
		do
		{
			showMainMenu();
			optionMain = scnr.nextLine().toUpperCase().charAt(0);
			
			switch(optionMain)
			{
				case 'L': mainTeamList(); break; // List teams
				case 'A': mainTeamAdd(); break; // Add a team
				case 'M': mainTeamManage(); break; // Manage a team
				case 'D': mainTeamDelete(); break; // Delete a team
				case 'P': mainTeamPrint(); break; // Print information about a team
			}
		}
		while(optionMain != 'Q');
		
		System.out.println("Program finished.");
	}
	
	public static void mainTeamManage()
	{
		String teamName;
		boolean teamFound = false;
		
		do
		{
			System.out.println("Enter the name of the team or Q to go back to the Main Menu:");
			
			teamName = scnr.nextLine();
			
			optionExtra = teamName.toUpperCase().charAt(0);
			if (optionExtra != 'Q')
			{
				teamFound = teamFind(teamName, true);
			}
		}
		while (optionExtra != 'Q' && !teamFound);
		
		if (teamFound)
		{
			do
			{
				showTeamMenu();
				optionTeam = scnr.nextLine().toUpperCase().charAt(0);
				
				switch(optionTeam)
				{
					case 'U': teamUpdate(); break; // Update team's name
					case 'L': teamPlayerList(); break; // List players
					case 'A': teamPlayerAdd(); break; // Add a player
					case 'E': teamPlayerEdit(); break; // Edit a player
					case 'D': teamPlayerDelete(); break; // Delete a player
					case 'P': teamPlayerPrint(); break; // Print information about a player
					case 'Z': teamPrint(); break; // Print team's information
				}
			}
			while(optionTeam != 'Q');
		}
	}
	
	// Method to find a team by name
	public static boolean teamFind(String name, boolean printNotFound)
	{
		boolean result = false;
		
		teamIndex = -1;
		
		if (teams.size() > 0) 
		{
			for (int i = 0; i < teams.size(); ++i) 
			{
				if (name.equals(teams.get(i).getName()))
				{
					teamIndex = i;
					teamCurrent = teams.get(i);
					result = true;
				}
			}
			
			if (printNotFound && !result)
			{
				System.out.println("Team '" + name + "' not found.");
			}
		} 
		else 
		{
			System.out.println("No teams have been added.");
		}
		
		return result;
	}
	
	// Method to update team's name
	public static void teamUpdate()
	{
		System.out.println("Enter the new name:");
		
		String name = scnr.nextLine();
		if (teamFind(name, false))
		{
			System.out.println("Name '" + name + "' already in use");
		}
		else
		{
			teamCurrent.setName(name);
			System.out.println("Team name was successfully updated to '" + name + "'.");
		}
	}
	
	// Method to list players of a team
	public static void teamPlayerList()
	{
		do
		{
			System.out.println("");
			printDivisor("LIST PLAYERS BY:");
			System.out.println("# N: Name");
			System.out.println("# J: Jersey");
			System.out.println("# S: Score");
			System.out.println("# Q: Go back to Team Menu");
			printDivisor("");
			
			System.out.print("Select an option: ");
			
			optionExtra = scnr.nextLine().toUpperCase().charAt(0);
			
			switch(optionExtra)
			{
				case 'N': teamCurrent.listPlayersByName(); break; // List players by name
				case 'J': teamCurrent.listPlayersByJersey(); break; // List players by jersey
				case 'S': teamCurrent.listPlayersByScore(); break; // List players by score
			}
		}
		while(optionExtra != 'Q');
	}
	
	// Method to add a player to a team
	public static void teamPlayerAdd()
	{
		System.out.println("Enter the player's name");
		String name = scnr.nextLine();
		
		System.out.println("Enter the player's jersey");
		int jersey = Integer.parseInt(scnr.nextLine());
		
		System.out.println("Enter the player's score");
		int score = Integer.parseInt(scnr.nextLine());
		
		Player player = new Player(name, jersey, score);
		teamCurrent.addPlayer(player);
		
		System.out.println("Player was successfully added.");
	}
	
	// Method to edit a player of a team
	public static void teamPlayerEdit()
	{
		int jersey = teamAskJersey();
		int playerIndex = teamCurrent.playerFindAndToEdit(jersey, true);
		if (playerIndex >= 0)
		{
			System.out.println("Enter the player's name");
			String name = scnr.nextLine();
			
			System.out.println("Enter the player's score");
			int score = Integer.parseInt(scnr.nextLine());
			
			teamCurrent.updatePlayer(playerIndex, name, score);
		}
	}
	
	// Method to delete a player of a team
	public static void teamPlayerDelete()
	{
		int jersey = teamAskJersey();
		teamCurrent.playerFindAndDelete(jersey, true);
	}
	
	// Method to print a player's information
	public static void teamPlayerPrint()
	{
		int jersey = teamAskJersey();
		teamCurrent.playerFindAndPrint(jersey, true);
	}
	
	// Method to print team's information
	public static void teamPrint()
	{
		teamCurrent.printFull();
	}

	// Method to ask for a player's jersey
	public static int teamAskJersey()
	{
		System.out.println("Enter the jersey of the player:");
		return Integer.parseInt(scnr.nextLine());
	}
	
	// Method to ask for a team's name
	public static String teamAskName()
	{
		System.out.println("Enter the name of the team:");
		return scnr.nextLine();
	}
	
	// Method to print a team
	public static void mainTeamPrint()
	{
		String teamName = teamAskName();   
		if (teamFind(teamName, true))
		{
			teamCurrent.printFull();
		}
	}
	
	// Method to remove a team
	public static void mainTeamDelete()
	{
		String teamName = teamAskName();   
		if (teamFind(teamName, true))
		{
			teams.remove(teamIndex);
			teamIndex = -1;
			System.out.println("Team '" + teamName + "' was successfully deleted.");
		}
	}
	
	// Method to add a team
	public static void mainTeamAdd()
	{
		String teamName = teamAskName();   
		if (teamFind(teamName, false))
		{
			System.out.println("Team '" + teamName + "' already added.");
		}
		else
		{
			Team newTeam = new Team(teamName);
			teams.add(newTeam);
			System.out.println("Team added successfully.");
		}
	}
	
	// Method to list all teams and print their basic information
	public static void mainTeamList()
	{
		do
		{
			System.out.println("");
			printDivisor("LIST TEAMS BY:");
			System.out.println("# N: Name");
			System.out.println("# S: Score");
			System.out.println("# Q: Go back to Main Menu");
			printDivisor("");
			
			System.out.print("Select an option: ");
			
			optionExtra = scnr.nextLine().toUpperCase().charAt(0);
			
			switch(optionExtra)
			{
				case 'N': mainTeamListByName(); break; // List teams by name
				case 'S': mainTeamListByScore(); break; // List teams by score
			}
		}
		while(optionExtra != 'Q');
	}

	// Method to list teams by name
	private static void mainTeamListByName()
	{
		int total = teams.size();
		
		if (total > 0)
		{
			int i;
			String[] list = new String[total];
			
			for (i = 0; i < total; ++i) 
			{
				list[i] = teams.get(i).getName();
			}
			
			int[] result = helper.sortString(list);
			
			System.out.println("Teams:");
			for (i = 0; i < total; ++i) 
			{
				teams.get(result[i]).print();
			}
		}
		else
		{
			System.out.println("No teams have been added.");
		}
	}
	
	// Method to lsit teams by score
	private static void mainTeamListByScore()
	{
		int total = teams.size();
		if (total > 0)
		{
			int i;
			int[] list = new int[total];
			
			for (i = 0; i < total; ++i) 
			{
				list[i] = teams.get(i).getScore();
			}
			
			int[] result = helper.sortInt(list);
			
			System.out.println("Teams:");
			for (i = 0; i < total; ++i) 
			{
				teams.get(result[i]).print();
			}
		}
		else
		{
			System.out.println("No teams have been added.");
		}
	}
	
	// Method to print menu title's and divisor
	private static void printDivisor(String word)
	{
		int size = 50;
		String print = "";
		
		if (word.length() > 0)
		{
			int before = (int) Math.ceil((size - word.length() - 2) / 2.00);
			for (int i = 0; i < before; i++)
			{
				print += "#";
			}
			print += " " + word + " ";
		}
		
		int max = 50 - print.length();
		
		for (int i = 0; i < max; i++)
		{
			print += "#";
		}
		
		System.out.println(print);
	}
	
	// Method to show Main Menu
	public static void showMainMenu()
	{
		System.out.println("");
		printDivisor("MAIN MENU");
		System.out.println("# L: List teams");
		System.out.println("# A: Add a team");
		System.out.println("# M: Manage a team");
		System.out.println("# D: Delete a team");
		System.out.println("# P: Print information about a team");
		System.out.println("# Q: Quit");
		printDivisor("");
		
		System.out.print("Select an option: ");
	}
	
	// Method to show Team Menu
	public static void showTeamMenu()
	{
		System.out.println("");
		printDivisor("TEAM '" + teamCurrent.getName() + "' MENU");
		System.out.println("# U: Update team's name");
		System.out.println("# L: List players");
		System.out.println("# A: Add a player");
		System.out.println("# E: Edit a player");
		System.out.println("# D: Delete a player");
		System.out.println("# P: Print information about a player");
		System.out.println("# Z: Print team's information");
		System.out.println("# Q: Go back to Main Menu");
		printDivisor("");
		
		System.out.print("Select an option: ");
	}
	
	// Method to auto-populate data
	public static void fakeData()
	{
		// team 1 - Utah Jazz
		
		Team team1 = new Team("Utah Jazz");
		
		Player team1Player1 = new Player("Micah Potter", 25, 30);
		Player team1Player2 = new Player("Juan Toscano-Anderson", 95, 45);
		Player team1Player3 = new Player("Udoka Azubuike", 20, 20);
		Player team1Player4 = new Player("Ochai Agbaji", 30, 25);
		
		team1.addPlayer(team1Player1);
		team1.addPlayer(team1Player2);
		team1.addPlayer(team1Player3);
		team1.addPlayer(team1Player4);
		
		teams.add(team1);
		
		// team 2 - Chicago Bulls
		
		Team team2 = new Team("Chicago Bulls");
		
		Player team2Player1 = new Player("Alex Caruso", 6, 13);
		Player team2Player2 = new Player("Justin Lewis", 34, 22);
		Player team2Player3 = new Player("Terry Taylor", 32, 32);
		Player team2Player4 = new Player("Coby White", 0, 35);
		
		team2.addPlayer(team2Player1);
		team2.addPlayer(team2Player2);
		team2.addPlayer(team2Player3);
		team2.addPlayer(team2Player4);
		
		teams.add(team2);
	}
}
