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
	private static ArrayList<Integer> userTicketNumbers;
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

	public static boolean TodaysDraw2() {

		sc = new Scanner(System.in);
		char choice = sc.next().charAt(0);

		boolean invalid = true;
		while (invalid) {

			if (choice == 'y' || choice == 'Y') {
				invalid = false;

				// Get the user's current balance
//				currentBalance = (double) Bank.getBalance(); // returns double bankBalance

				// get draw choice (a, b, c or d)
				drawChoice = ChooseDraw(); // -> Returns choice (char)

				// Show user their bank balance
//				System.out.println("You balance is: £" + currentBalance);			

				// get the ticket price based on the drawChoice -> Returns ticketPrice (double)
				ticketPrice = GetTicketPrice(drawChoice);

//				if()

				// Get the draw size to return MAX_RANGE for tickets
				MAX_RANGE = getTicketRange(drawChoice); // returns double MAX_RANGE

				// Find out how many tickets the user wishes to purchase -> returns int
				// ticketQuantity
				ticketQuantity = GetUserTicketChoice(drawChoice);

				// get ticket quantity and determine max ticket quantity
				
				
				
				if (ticketQuantity <= MAX_RANGE) {
					// Valid
					// Tickets can be bought within this range

					// Check if the user has sufficient funds to buy the ticketQuantity -> returns
					// boolean

					balanceAfterPurchase = Bank.runBalanceCheck(currentBalance, ticketPrice, drawChoice,
							ticketQuantity);

					result = (double) (currentBalance - balanceAfterPurchase);

					System.out.println("Balance after purchase £" + result);
					System.out.println("Current Balance->> " + currentBalance);
					System.out.println("Result ->>>> " + result);

//					while( result)

//					while(result < 0) {
//						// Rerun if balance is less than the requested tickets
//						ticketQuantity = GetUserTicketChoice(drawChoice);
//						balanceAfterPurchase = Bank.runBalanceCheck(currentBalance, ticketPrice, drawChoice,
//								ticketQuantity);
//					}

					// Sufficient funds to proceed
//					System.out.println("Payment success...\nRunning Draw...");

				} else {
					// Invalid
					// Cannot over purchase tickets for competition
					System.out.println("Cannot over purchase tickets for the competition...");
					invalid = false;
					System.exit(0);
//					return;
				}

			} else if (choice == 'n' || choice == 'N') {
				invalid = false;
//					user returned false -> exiting the application
				System.out.println("User decided to decline to participate in the draws this time...");
				System.out.println("Exiting...");
				System.exit(0);
			} else {
				System.out.println("Invalid entry: " + choice);
			}
//			runDraw(MAX_RANGE);

		}
		return result;
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
		System.out.println("How many tickets do you wish to puchase for the draw: " + drawChoice2 + " ? \n Remember, you can only awford [" + awfordDraw + "] tickets.");
		int ticketQuantity = sc.nextInt();
		return ticketQuantity;
	}
	
	public static void runCheck() {
		
		
		
	}

	private static void runDraw(int mAX_RANGE2) {
		int winningNumber;
		entryList = Random.generateDrawNumbers(mAX_RANGE2);
		// Generate Random Winning Number between the max range and 1
		winningNumber = Random.drawNumber(mAX_RANGE2);
		// Print the Winning number to the console
		System.out.println("[Winning Number] " + winningNumber);

		// check the tickets for the winning number, take in the generatedTicketNumbers,
		// the winningNumber and the complete list of tickets
		checkTickets(userTicketNumbers, winningNumber, entryList);
	}

	// Check if the winning number existed within the array of generated numbers
	private static void checkTickets(ArrayList<Integer> userTicketNumbers, int winningNumber,
			ArrayList<Integer> entryList) {
		System.out.println("Checking your tickets...");
		boolean numberDrawn = false;

		// Start draw -> keep the draw running infinite
		while (!numberDrawn) {

			// for each possible ticket in the entryList -> (you can't possibly draw more
			// tickets than the maximum allowed in the competition in order to win)
			for (int i = 0; i < entryList.size(); i++) {
				// Increment count
				counter++;
				if (userTicketNumbers.contains(winningNumber)) {
					// the winning number IS contained within the usersTicketNumbers

					// True win ->
					if (userTicketNumbers.contains(winningNumber) && counter <= userTicketNumbers.size()) {
						System.out.println("Correct! Well done.");
						System.out.println("One of your number(s) was drawn, number: [" + winningNumber
								+ "] and it took [" + counter + "] draws.");
						numberDrawn = true;
						return;

					}
					// If the number was not drawn within the number of tickets bought -> no win,
					// but number was drawn eventually at a finite cost.

					// the winning number is contained within the usersTicketNumbers
//    				System.out.println("One of your numbers was drawn, number: [" + winningNumber + "] however, it took [" + counter + "] draws and cost you: £" + bankBalance);

//					System.out.println("Your bank balance is now £" + transaction);

					numberDrawn = true;
					return;
				} else {
//    				// the winning number is NOT contained within the usersTicketNumbers
					System.out.println("Winning number is [" + winningNumber
							+ "] and is NOT contained within the users TicketNumbers");

					// Redraw -> Pull another number within the range of the draw until a number
					// from the users range of tickets is chosen
					winningNumber = Random.drawNumber(MAX_RANGE);

					// check against users tickets
					checkTickets(userTicketNumbers, winningNumber, entryList);
					// Increment count
					return;
				}
			}
		}
		System.out.println("Count ->>>>" + counter);

		// For each integer in the ArrayList of
//		for (Integer n : numberList2) {
//			System.out.println(n);
//			
//		}

	}

}
