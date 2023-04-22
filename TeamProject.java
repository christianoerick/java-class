import java.util.*;

public class TeamProject 
{
	private static ArrayList<Team> teams;
	private static boolean isMain = true;
	private static int teamIndice = -1;
	private static char option;
	private static Scanner scnr;
	
	public static void main(String[] args) 
	{
		scnr = new Scanner(System.in);
		teams = new ArrayList<Team>(); //updated

		do
		{
			showMainMenu();
			option = scnr.nextLine().toUpperCase().charAt(0);
			
			switch(option)
			{
				case 'L': mainTeamList(); break; // List teams
				case 'A': mainTeamAdd(); break; // Add a team
				case 'M': mainTeamManage(); break; // Manage a team
				case 'D': mainTeamDelete(); break; // Delete a team
				case 'P': mainTeamPrint(); break; // Print information about a team
			}
		}
		while(option != 'Q');
		
		System.out.println("Program finished.");
	}
	
	public static void mainTeamManage()
	{
		do
		{
			showTeamMenu();
			option = scnr.nextLine().toUpperCase().charAt(0);
			
			switch(option)
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
		while(option != 'Q');
	}
	
	// 
	
	// Method to update team's name
	public static void teamUpdate() {
		System.out.println("Enter the name of the team to update:");
		String teamName = scnr.nextLine();

		for (Team team : teams) {
			if (team.getName().equals(teamName)) {
				System.out.println("Enter the new name for the team:");
				String newTeamName = scnr.nextLine();
				team.setName(newTeamName);
				System.out.println("Team name updated successfully.");
				return;
			}
		}

		System.out.println("Team not found.");
	}

	
	// Method to list players of a team
	public static void teamPlayerList()
	{
		System.out.println("Enter the name of the team:");
		String teamName = scnr.nextLine();

		int team = findTeam(teamName);
		if (team < 0) {
			System.out.println("Team not found.");
		} else {
			Team currentTeam = teams.get(team);
			currentTeam.printFull();
		}
	}
	
	// Method to add a player to a team
	public static void teamPlayerAdd()
	{
		System.out.println("Enter the name of the team:");
		String teamName = scnr.nextLine();

		int team = findTeam(teamName);
		if (team < 0) {
			System.out.println("Team not found.");
		} else {
			Team currentTeam = teams.get(team);
			System.out.println("Enter the player's name:");
			String name = scnr.nextLine();

			System.out.println("Enter the player's jersey:");
			int jersey = scnr.nextInt();
			scnr.nextLine();

			System.out.println("Enter the player's score:");
			
			int score = Integer.parseInt(scnr.nextLine());

			Player newPlayer = new Player(name, jersey, score);
			currentTeam.addPlayer(newPlayer);

			System.out.println("Player added successfully.");
		}


	}

	
	// Method to edit a player of a team
	public static void teamPlayerEdit()
	{
		// TODO
	}
	
	// Method to delete a player of a team
	public static void teamPlayerDelete()
	{
		// TODO
	}
	
	// Method to print a player's information
	public static void teamPlayerPrint()
	{
		// TODO
	}
	
	// Method to print team's information
	public static void teamPrint()
	{
		// TODO
		
	}
	
	// Method to print a team
	public static void mainTeamPrint()
	{
		System.out.println("Enter the name of the team to print:");
		String teamName = scnr.nextLine();
	
		// Search for the team in the list of teams
		boolean found = false;
		for (Team team : teams) {
			if (team.getName().equals(teamName)) {
				team.print();
				found = true;
				break;
			}
		}
	
		// Print an error message if the team was not found
		if (!found) {
			System.out.println("Team not found.");
		}
	}
	
	// Method to remove a team
	public static void mainTeamDelete()
	{
		System.out.println("Enter the name of the team to delete:");
		String teamName = scnr.nextLine();

		boolean isDeleted = false;
		for (int i = 0; i < teams.size(); i++)
		{
			if (teams.get(i).getName().equals(teamName))
			{
				teams.remove(i);
				isDeleted = true;
				System.out.println("Team deleted successfully.");
				break;
			}
		}
		if (!isDeleted)
		{
			System.out.println("Team not found.");
		}

	}
	
	// Method to add a team
	public static void mainTeamAdd()
	{
		System.out.println("Enter the name of the team:");
		String teamName = scnr.nextLine();
		
		Team newTeam = new Team(teamName);
		teams.add(newTeam);

		System.out.println("Team added successfully.");
	}
	
	// Method to list all teams and print their basic information
	public static void mainTeamList()
	{
		System.out.println("Teams:");
		for (int i = 0; i < teams.size(); i++)
		{
			teams.get(i).print();
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
		printDivisor("TEAM MENU");
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
	public static int findTeam(String teamName)
	{
		teamIndice = -1;
		for (int i=0; i < teams.size(); ++i){
			if (teamName.equals(teams.get(i).getName()))
			{
				teamIndice = i;
			}
		}
		return teamIndice;
	}
}
