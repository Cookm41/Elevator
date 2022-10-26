package elevator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Madelynn Cook
 * 
 * Takes an initial starting floor value (integer) and then a list of floors visited, input on one line and separated by commas.
 * These values are then used to find the total travel time of the elevator based on all the floors it stopped at and prints the 
 * travel time to the console, along with all the floors visited, including the starting floor.
 *
 */
public class elevatorMain {
	
	public static int TRAVEL_TIME_PER_FLOOR = 10;
	
	// assuming first floor is lowest possible floor
	public static int LOWER_FLOOR_LIMIT = 1;
	
	//assuming building has 150 floors or less 
	public static int UPPER_FLOOR_LIMIT = 150;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Enter the starting floor (between 1 and 150): ");
		String startFloor = scan.nextLine();
		
		// catch exception if the value input is not an integer
		try
		{
			int startFloorInt = Integer.parseInt(startFloor);
			if(startFloorInt >= LOWER_FLOOR_LIMIT && startFloorInt <= UPPER_FLOOR_LIMIT)
			{
				System.out.print("Enter the floors visited (between 1 and 150), separated by a comma: ");  
				String floorsInput = scan.nextLine();
				
				scan.close();
				
				String[] floorsVisited = floorsInput.split(",");
				
				List<Integer> totalFloors = parseFloorsFromInput(startFloorInt, floorsVisited);
				int time = addTravelTime(totalFloors);
				
				System.out.print("Total travel time: " + time + ". Floors visited: " + listToString(totalFloors));
			}
			else
			{
				System.out.println("Invalid start floor number entered. Must be between 1 and 150.");
			}
		}
		catch(NumberFormatException nfe)
		{
			System.out.println("Invalid start floor number entered.");
		}
	}
	
	
	/**
	 * @param start -> int
	 * @param floors -> String array
	 * @return List of Integers
	 * 
	 * takes starting floor integer value and string array of floors visited. Adds integer start floor to List 
	 * and parses string array for integer values to add to List. Will remove any whitespace or skip any non-integer 
	 * values. Also restricts floors to only those between 1 and 150
	 */
	public static List<Integer> parseFloorsFromInput(int start, String[] floors)
	{
		List<Integer> floorsToIntList = new ArrayList<Integer>();
		floorsToIntList.add(start);
		for(int i = 0; i < floors.length; i ++)
		{
			// catching exception if a value is not an integer, skips it and goes to the next value
			try
			{
				// removes any potential whitespace
				String trimmed = floors[i].trim();
				int x = Integer.parseInt(trimmed);
				if(x >= LOWER_FLOOR_LIMIT && x <= UPPER_FLOOR_LIMIT)
				{
					floorsToIntList.add(x);
				}
				else
				{
					System.out.println("Floor number " + x + " is outside the allowable range of 1-150. Skipping " + x);
				}
			}
			catch(NumberFormatException nfe)
			{
				System.out.println("Invalid floor number entered. Skipping " + floors[i]);
			}
		}
		return floorsToIntList;
	}
	
	
	/**
	 * @param floorsTravelled -> List of Integers
	 * @return integer value of total travel time
	 * 
	 * Takes a List and iterates over values, finding the absolute value of the difference between two values, 
	 * then multiplies that by the travel time constant, and adds that value to the total travel time 
	 */
	public static int addTravelTime(List<Integer> floorsTravelled)
	{
		int travelTime = 0;
		int currentFloor = floorsTravelled.get(0);
		for(int floor : floorsTravelled)
		{
			int timeBetweenFloors = Math.abs(currentFloor - floor) * TRAVEL_TIME_PER_FLOOR;
			currentFloor = floor;
			travelTime += timeBetweenFloors;
		}
		return travelTime;
	}
	
	/**
	 * @param list -> List of Integers
	 * @return String representation of the list
	 * 
	 * iterates over list values and appends them to a string builder. Just creating a comma-separated string 
	 * representation of the list for printing to the console 
	 */
	public static String listToString(List<Integer> list)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (int i = 0; i < list.size() - 1; i++) {
		  int num = list.get(i);
		  sb.append(num + ", ");
		}
		sb.append(list.get(list.size() -1) + "}");
		return sb.toString();
	}

}
