import java.util.*;

public class Team 
{
	private String name; // Team's name
	private ArrayList<Player> players; // Team's player list
    private int playerIndex = -1;
    private Helper helper;
	
	// Constructor Method: setting only team's name
	public Team(String name)
	{
		this.name = name;
		
		this.players = new ArrayList<Player>();

        this.helper = new Helper();
	}
	
	// Method to set/update team's name
	public void setName(String name)
	{
		this.name = name;
	}
	
	// Method to get team's name
	public String getName()
	{
		return this.name;
	}
	
	// Method to get team's total of players
	public int getTotalPlayers()
	{
		return this.players.size();
	}
	
	// Method to get team's sum of player's score
	public int getScore()
	{
		int score = 0;
		for (int i = 0; i < this.players.size(); i++)
		{
			score += this.players.get(i).getScore();
		}
		return score;
	}
	
	// Method to print all players
	public void listPlayersByName()
	{
        int total = this.players.size();

        if (total > 0)
        {
            int i;
            String[] list = new String[total];
            
            for (i = 0; i < total; ++i) 
            {
                list[i] = this.players.get(i).getName();
            }

            int[] result = this.helper.sortString(list);

            System.out.println("Players by name:");
            for (i = 0; i < total; ++i) 
            {
                this.players.get(result[i]).print();
            }
        }
        else
        {
            System.out.println("No players have been added.");
        }
	}

	// Method to print all players ordered by jersey
	public void listPlayersByJersey()
	{
		int total = this.players.size();
        if (total > 0)
        {
            int i;
            int[] list = new int[total];;
            
            for (i = 0; i < total; ++i) 
            {
                list[i] = this.players.get(i).getJersey();
            }
            
            int[] result = this.helper.sortInt(list);
            
            System.out.println("Players by jersey:");
            for (i = 0; i < total; ++i) 
            {
                this.players.get(result[i]).print();
            }
        }
	}

	// Method to print all players ordered by score
	public void listPlayersByScore()
	{
        int total = this.players.size();
        if (total > 0)
        {
            int i;
            int[] list = new int[total];;
            
            for (i = 0; i < total; ++i) 
            {
                list[i] = this.players.get(i).getScore();
            }
            
            int[] result = this.helper.sortInt(list);
            
            System.out.println("Players by score:");
            for (i = 0; i < total; ++i) 
            {
                this.players.get(result[i]).print();
            }
        }
        else
        {
            System.out.println("No players have been added.");
        }
	}
	
	// Method to add a player to the team
	public void addPlayer(Player player)
	{
		this.players.add(player);
	}
	
	// Method to update a player to the team
	public void updatePlayer(int index, String name, int score)
	{
		this.players.get(index).setName(name);
		this.players.get(index).setScore(score);
	}
	
	// Method to remove a player to the team based on player's jersey
	public void removePlayer(int jersey)
	{
		for (int i = 0; i < this.players.size(); i++)
		{
			if (this.players.get(i).getJersey() == jersey)
			{
				this.players.remove(i);	
			}
		}
	}

    // Method to delete a player by jersey
    public void playerFindAndDelete(int jersey, boolean printNotFound)
    {
        if (playerFind(jersey, printNotFound))
        {
            this.players.remove(playerIndex);
            System.out.println("Player with jersey '" + jersey + "' was successfully deleted.");
        }
    }

    // Method to delete a player by jersey
    public int playerFindAndToEdit(int jersey, boolean printNotFound)
    {
        int result = -1;
        if (playerFind(jersey, printNotFound))
        {
            result = playerIndex;
        }
        return result;
    }

    // Method to print a player by jersey
    public void playerFindAndPrint(int jersey, boolean printNotFound)
    {
        if (playerFind(jersey, printNotFound))
        {
            this.players.get(playerIndex).print();
        }
    }
	
	// Method to find a player by jersey
	public boolean playerFind(int jersey, boolean printNotFound)
	{
		boolean result = false;
		
		this.playerIndex = -1;
		
		if (this.players.size() > 0) 
		{
			for (int i = 0; i < this.players.size(); ++i) 
			{
				if (jersey == this.players.get(i).getJersey())
				{
					this.playerIndex = i;
					result = true;
				}
			}

            if (printNotFound && !result)
            {
                System.out.println("Player with jersey '" + jersey + "' not found.");
            }
		} 
		else 
		{
			System.out.println("No players have been added.");
		}
		
		return result;
	}
	
	// Method to print team's basic information
	public void print()
	{
		System.out.println(" - Team's name: " + this.name + " | Score: " + getScore());
	}
	
	// Method to print team's basic information and list of players
	public void printFull()
	{
		System.out.println("Team: " + this.name);
		System.out.println("Score: " + getScore());
		System.out.println("Players:");
		this.listPlayersByJersey();
	}
}
