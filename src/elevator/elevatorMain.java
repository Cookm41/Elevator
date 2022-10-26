package elevator;

import java.util.Arrays;
import java.util.Scanner;

public class elevatorMain {
	
	public static int TRAVEL_TIME_PER_FLOOR = 10;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter the starting floor: ");
		String startFloor = scan.nextLine();
		int startFloorInt = Integer.parseInt(startFloor);
		
		System.out.println("Enter the floors visited, separated by a comma: ");  
		String floorsInput = scan.nextLine();
		
		scan.close();
		
		String[] floorsVisited = floorsInput.split(",");
		
		int[] totalFloors = parseFloorsFromInput(startFloorInt, floorsVisited);
		int time = addTravelTime(totalFloors);
		
		System.out.print(printOutput(time, totalFloors));
		

	}
	
	
	public static int[] parseFloorsFromInput(int start, String[] floors)
	{
		int[] floorsToInt = new int[floors.length + 1];
		floorsToInt[0] = start;
		for(int i = 0; i < floors.length; i ++)
		{
			try
			{
				int x = Integer.parseInt(floors[i]);
				if(x >= 0)
				{
					floorsToInt[i + 1] = x;
				}
			}
			catch(NumberFormatException nfe)
			{
				System.out.println("Invalid floor number entered. Skipping " + floors[i]);
			}
		}
		
		return floorsToInt;
	}
	
	public static int addTravelTime(int[] floorsTravelled)
	{
		int travelTime = 0;
		int currentFloor = floorsTravelled[0];
		for(int floor : floorsTravelled)
		{
			int timeBetweenFloors = Math.abs(currentFloor - floor) * TRAVEL_TIME_PER_FLOOR;
			currentFloor = floor;
			travelTime += timeBetweenFloors;
		}
		return travelTime;
	}
	
	public static StringBuilder printOutput(int time, int[] floors)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Total travel time: " + time + ". Floors visited: " + Arrays.toString(floors));

		return sb;
	}

}
