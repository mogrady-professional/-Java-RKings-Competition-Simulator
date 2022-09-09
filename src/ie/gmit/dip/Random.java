package ie.gmit.dip;

import java.util.ArrayList;
import java.util.Collections;

public class Random {
	private static final double MAX_RANDOM_BALANCE = 1000;
	
	
	
	
// Generate a random number between 1 and 1000
	public static int randomBankBalance() {
		int number = (int) ((Math.random() * (MAX_RANDOM_BALANCE - 1)) + 1); // integer value between 1 and 1000
		System.out.println("[Random] Random Bank Balance: £" + number);
		return number;

	}
	
	
	public static int generateWinner(int MAX_TICKETS) {
		double winner = (int) ((Math.random() * (MAX_TICKETS - 1)) + 1); // integer value between 1 and MAX_TICKETS
//		System.out.println("[Random] Random Bank Balance: £" + number);
		return (int) winner;		
	}
	
	
	

	// Generate ticket numbers for the user
	public static ArrayList<Integer> generateUserTickets(int maxTickets, int ticketQuantity) {
		// TODO Auto-generated method stub
		ArrayList<Integer> numberList = new ArrayList<Integer>(); // Create an ArrayList object

		// for each number within the range
		for (int i = 1; i <= maxTickets; i++) {
			// add each number in the list
			numberList.add(i);
		}

		// shuffle the numbers
		Collections.shuffle(numberList);
		
		// For each number in the draw, remove a number until you are left with an amount of random numbers that equal the amount the user has purchased
		for (int i = 1; i <= numberList.size(); i++) {
			while (numberList.size() > ticketQuantity) {
				numberList.remove(i);
			}
		}

//		Collections.shuffle(numberList);

		numberList.sort(null); // Sort numbers to draw in ascending order

		// print each number to the console
		System.out.println("[USER-TICKETS->>>>] " + numberList);
//		System.out.println(numberList);

		// print each number to the console
//		for (Integer integer : numberList) {
//			System.out.println(integer);
//		}

		// Print the amount of numbers in the collection
		System.out.println("[Number of Tickets Generated] " +numberList.size());

		return numberList;
	}

	public static int drawNumber(int maxTickets) {
		// TODO Auto-generated method stub
		int drawnNumber = (int) ((Math.random() * (maxTickets - 1)) + 1);
		return drawnNumber;

	}

	public static ArrayList<Integer> generateDrawNumbers(int maxTickets) {
		// TODO Auto-generated method stub
		ArrayList<Integer> draw = new ArrayList<Integer>(); // Create an ArrayList object

		// for each number within the range
		for (int i = 1; i <= maxTickets; i++) {
			// add each number in the list
			draw.add(i);
		} 
		// shuffle the numbers
		Collections.shuffle(draw);
 

//		draw.sort(null); // Sort numbers in ascending order

		System.out.println("[DRAW-LIST->>>>] " + draw);
		
		
		// print each number to the console  
//		for (Integer integer : draw) {
//			System.out.println(integer);
//		}

		// Print the amount of numbers in the collection
		System.out.println("[Number of Tickets In the Draw] " +draw.size());

		return draw;
	}

}
