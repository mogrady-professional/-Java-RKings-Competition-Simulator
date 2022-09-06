package ie.gmit.dip;

import java.util.ArrayList;
import java.util.Collections;

// Class to generate a random number between 1 and 1000
public class Random {

//	public static int getBalance() {
//	int number = (int) ((Math.random() * (1000 - 1)) + 1); // integer value between 1 and 1000
//	System.out.println("[Random] Random Generated Number: " + number);
//	return number; 
//	}

	public static int GenerateBankBalanceMaxOneThousand() {
		int number = (int) ((Math.random() * (1000 - 1)) + 1); // integer value between 1 and 1000
		System.out.println("[Random] Random Bank Balance: £" + number);
		return number;

	}

	// Generate ticket numbers for the user
	public static ArrayList<Integer> generateUserTickets(int mAX_RANGE, int ticketQuantity) {
		// TODO Auto-generated method stub
		ArrayList<Integer> numberList = new ArrayList<Integer>(); // Create an ArrayList object

		// for each number within the range
		for (int i = 1; i <= mAX_RANGE; i++) {
			// add each number in the list
			numberList.add(i);
			// shuffle the numbers
			Collections.shuffle(numberList);
		}
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

		// Prit the amount of numbers in the collection
		System.out.println("[Number of Tickets Generated] " +numberList.size());

		return numberList;
	}

	public static int drawNumber(int mAX_RANGE2) {
		// TODO Auto-generated method stub
		int drawnNumber = (int) ((Math.random() * (mAX_RANGE2 - 1)) + 1);
		return drawnNumber;

	}

	public static ArrayList<Integer> generateDrawNumbers(int mAX_RANGE2) {
		// TODO Auto-generated method stub
		ArrayList<Integer> draw = new ArrayList<Integer>(); // Create an ArrayList object

		// for each number within the range
		for (int i = 1; i <= mAX_RANGE2; i++) {
			// add each number in the list
			draw.add(i);
			// shuffle the numbers
		} 
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
