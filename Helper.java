import java.util.*;

// Class created to be used as helper to order int and String arrays

public class Helper 
{
	// Helper function to sort by String
	public int[] sortString(String[] list)
	{
		int a;
		int total = list.length;
		String[] order = new String[total];
		String[] split;
		int[] result = new int[total];
		
		for (a = 0; a < total; ++a) 
		{
			order[a] = list[a] + "-split-" + a;
		}
		
		Arrays.sort(order);
		
		for (a = 0; a < total; ++a) 
		{
			split = order[a].split("-split-");
			result[a] = Integer.parseInt(split[1]);
		}
		
		return result;
	}
	
	// Helper function to sort by integer
	public int[] sortInt(int[] list)
	{
		int a;
		int b;
		int c;
		int change;
		int total = list.length;
		
		int[] result = new int[total];
		
		for (a = 0; a < total; a++)
		{
			result[a] = a;
		}
		
		for (a = 0; a < total; a++)
		{
			c = a;
			for (b = a ; b <= (total -1); b++)
			{
				if (list[b] < list[c])
				{
					c = b;
				}
			}
			
			change = list[a];
			list[a] = list[c];
			list[c] = change;
			
			change = result[a];
			result[a] = result[c];
			result[c] = change;
		}
		
		return result;
	}
}
