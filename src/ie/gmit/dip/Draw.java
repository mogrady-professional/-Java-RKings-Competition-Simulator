package ie.gmit.dip;

import java.util.ArrayList;
import java.util.Scanner;

public class Draw {
	private static Scanner sc;
	private static double ticketPrice;
	private static char drawChoice;
	private static double currentBalance;
	private static double balanceAfterPurchase;
	private static int ticketQuantity;
	private static int MAX_RANGE;
//	private static ArrayList<Integer> userTicketNumbers;
	private static ArrayList<Integer> entryList;
	private static int counter;
	private static double result;

	public static boolean TodaysDraw() {
		System.out.println("Welcome to RKing Competitions...\n");
		System.out.println("The following draws are currently available: ");
		System.out.println("a) 499 tickets		Low Odds 2004 Subaru Impreza WRX			£19.99");
		System.out.println("b) 4995 tickets		Audi A4 S-Line						£4.99");
		System.out.println("c) 9995 tickets 	Audi A6 S-Line						£8.99");
		System.out.println("d) 34999 tickets	Nissan R35 GTR JDM 800bhp				£1.99");
		System.out.println("e) 44999 tickets	£125,000 or Stunning Ferrari 488  			£4.99\n");
		System.out.println("Do you wish to enter any of the competitions? Enter (y)es to play or (n)o to pass...");

		sc = new Scanner(System.in);
		char choice = sc.next().charAt(0);

		boolean invalid = true;

		while (invalid) {
			if (choice == 'y' || choice == 'Y') {
				invalid = false;
				return true;
			} else if (choice == 'n' || choice == 'N') {
				invalid = false;
				return false;
			} else {
				System.out.println("Invalid option: " + choice);
				return false;
			}
		}
		return false;
	}
	
	public static void verify() {
		// verify the user can awford the tickets
		
		
	}

	// User is asked for draw choice -> set ticket price
	public static char ChooseDraw() {
		System.out.println("[>] Enter a, b, c, d or e...");
		boolean invalid = true;
		char draw = sc.next().toLowerCase().charAt(0);

		while (invalid) {
			if (draw == 'a' || draw == 'b' || draw == 'c' || draw == 'd' || draw == 'e') {
				// Valid choice
				invalid = false;
				return draw;
			} else {
				// Recall Method
				invalid = false;
				System.out.println("Invalid choice...");
				ChooseDraw();
			}
		}
		return 0;
	}

	public static double GetTicketPrice(char draw) {
		char decision;
		// TODO Auto-generated method stub
		// Assign the ticket price amount based on the user selected draw
		switch (draw) {
		case 'a' -> ticketPrice = 19.99;
		case 'b' -> ticketPrice = 4.99;
		case 'c' -> ticketPrice = 8.99;
		case 'd' -> ticketPrice = 1.99;
		case 'e' -> ticketPrice = 4.99;
		default -> throw new IllegalArgumentException("Unexpected value: " + draw);
		}
//		System.out.println("[Ticket Price] for draw: " + draw + " is £" + ticketPrice);
		System.out.println("It costs [£" + ticketPrice + "] for 1 ticket. Do you wish to continue [?]");
		System.out.println("Please enter [y] or [n]");
		decision = sc.next().charAt(0);

		if (decision == 'y' || decision == 'Y') {
			System.out.println("User has agreed to proceed with this draw...");
			return ticketPrice;
		} else if (decision == 'n' || decision == 'N') {
			System.out.println("Would you like to choose another draw instead [?]");
			System.out.println("Please enter [y] or [n]");
			decision = sc.next().charAt(0);
			if (decision == 'y' || decision == 'Y') {
				System.out.println("User would like to participate in another draw instead...");
				ChooseDraw();
			} else if (decision == 'n' || decision == 'N') {
				System.out.println("User decided not to proceed with the draws at this time...");
				System.out.println("Exiting...");
				System.exit(0);
			}else {
//				Invalid Input.. call again
				ChooseDraw();
			}

		}
		return ticketPrice;
	}

	public static int getTicketRange(char drawSize) {

		switch (drawSize) {
		case 'a' -> MAX_RANGE = 499;
		case 'b' -> MAX_RANGE = 4995;
		case 'c' -> MAX_RANGE = 9995;
		case 'd' -> MAX_RANGE = 34999;
		case 'e' -> MAX_RANGE = 44999;
		default -> throw new IllegalArgumentException("Unexpected value: " + drawSize);
		}

		return MAX_RANGE;
	}

	public static int GetUserTicketChoice(char drawChoice2, double awfordDraw) {
		int ticketQuantity;
		if(awfordDraw == 0) {
			System.out.println("Sorry you cannot awford to purchase a ticket for this draw...");
			ticketQuantity = 0;
		}else {
			System.out.println("How many tickets do you wish to puchase for the draw: " + drawChoice2 + " ?");
			System.out.println("You can awford [" + awfordDraw + "] tickets.");
			ticketQuantity = sc.nextInt();
		}
		return ticketQuantity;
	}
	

	public static void runDraw(int mAX_RANGE2, ArrayList<Integer> userTickets, double ticketPrice) {
		int winningNumber;
		entryList = Random.generateDrawNumbers(mAX_RANGE2);
		// Generate Random Winning Number between the max range and 1
		winningNumber = Random.drawNumber(mAX_RANGE2);
		// Print the Winning number to the console
		System.out.println("[Winning Number] " + winningNumber);

		// check the tickets for the winning number, take in the generatedTicketNumbers,
		// the winningNumber and the complete list of tickets
		checkTickets(ticketPrice, userTickets, winningNumber, entryList);
	}

	// Check if the winning number existed within the array of generated numbers
	private static void checkTickets(double ticketPrice, ArrayList<Integer> userTickets, int winningNumber,
			ArrayList<Integer> entryList) {
		System.out.println("Checking your tickets...");
		boolean numberDrawn = false;

		// Start draw -> keep the draw running infinite
		while (!numberDrawn) {

			// for each possible ticket in the entryList -> (you can't possibly draw more
			// tickets than the maximum allowed in the competition in order to win)
			for (int i = 0; i < entryList.size();) {
				// Increment count
				counter++;
				double totalCost = (double) (ticketPrice * counter);
				
				if (userTickets.contains(winningNumber)) {
					// the winning number IS contained within the usersTicketNumbers

					// True win ->
					if (userTickets.contains(winningNumber) && counter <= userTickets.size()) {
						System.out.println("Correct! Well done.");
						System.out.println("One of your number(s) was drawn, number: [" + winningNumber
								+ "] and it took [" + counter + "] draws and cost you £" +totalCost);
						numberDrawn = true;
						return;

					}
					// If the number was not drawn within the number of tickets bought -> no win,
					// but number was drawn eventually at a finite cost.

					// the winning number is contained within the usersTicketNumbers
					System.out.println("Sorry you did not win.");
    				System.out.println("However, one of your numbers was eventually drawn, number: [" + winningNumber + "] but it took [" + counter + "] draws and would have cost you: £"+totalCost);

					numberDrawn = true;
					return;
				} else {
//    				// the winning number is NOT contained within the usersTicketNumbers -> but must be drawn eventually (the users numbers do not change & random number is always generated within the range)
					System.out.println("Draw #[" + counter + "] " + "Winning number is [" + winningNumber
							+ "] and is NOT contained within the users TicketNumbers" +userTickets);

					// Redraw -> Pull another number within the range of the draw until a number
					// from the users range of tickets is chosen
					winningNumber = Random.drawNumber(MAX_RANGE);

					// check against users tickets
//					checkTickets(ticketPrice, userTickets, winningNumber, entryList);
					// Increment count
//					return;
				}
			}
			numberDrawn = true;
		}

		// For each integer in the ArrayList of
//		for (Integer n : numberList2) {
//			System.out.println(n);
//			
//		}

	}

}
