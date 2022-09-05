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

	public static void generateDrawNumbers(int mAX_RANGE, int ticketQuantity) {
		// TODO Auto-generated method stub
		ArrayList<Integer> numberList = new ArrayList<Integer>(); // Create an ArrayList object

		for (int i = 1; i <= mAX_RANGE; i++) {
			numberList.add(i);
		}
		Collections.shuffle(numberList);
		
//		numberList.sort(null); // Sort numbers to draw in ascending order
		
		System.out.println(numberList);
		System.out.println(numberList.size());
	}

}
