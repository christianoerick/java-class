public class Player 
{	
	private String name; // Player's name
	private int jersey; // Player's jersey
	private int score; // Player's score
	
	// Constructor Method: setting only player's name
	public Player(String name)
	{
		this.name = name;
	}
	
	// Constructor Method: setting only player's name and jersey
	public Player(String name, int jersey)
	{
		this.name = name;
		this.jersey = jersey;
	}
	
	// Constructor Method: setting player's name, jersey, and score
	public Player (String name, int jersey, int score)
	{
		this.name = name;
		this.jersey = jersey;
		this.score = score;
	}
	
	// Method to set/update player's name
	public void setName(String name)
	{
		this.name = name;
	}
	
	// Method to get player's name
	public String getName()
	{
		return this.name;
	}
	
	// Method to set/update player's jersey
	public void setJersey(int jersey)
	{
		this.jersey = jersey;
	}
	
	// Method to get player's jersey
	public int getJersey()
	{
		return this.jersey;
	}
	
	// Method to set/update player's score
	public void setScore(int score)
	{
		this.score = score;
	}
	
	// Method to get player's score
	public int getScore()
	{
		return this.score;
	}
	
	// Method to print player's information
	public void print()
	{
		System.out.println(" - Player's name: " + this.name + " | Jersey: " + this.jersey + " | Score: " + this.score);
	}
}
