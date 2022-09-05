package ie.gmit.dip;

import java.util.ArrayList;
import java.util.Scanner;

public class Draw {
	private static Scanner sc;
	private static double ticketPrice;
	private static char drawChoice;
	private static double currentBalance;
	private static boolean sufficientFunds;
	private static int ticketQuantity;
	private static int MAX_RANGE;
	private static ArrayList<Integer> userTicketNumbers;
	private static ArrayList<Integer> entryList;

	public static void TodaysDraw() {

		System.out.println("The following draws are currently available: ");
		System.out.println("a) 499 tickets		Low Odds 2004 Subaru Impreza WRX			£19.99");
		System.out.println("b) 4995 tickets		Audi A4 S-Line						£4.99");
		System.out.println("c) 9995 tickets 	Audi A6 S-Line						£8.99");
		System.out.println("d) 34999 tickets	Nissan R35 GTR JDM 800bhp				£1.99");
		System.out.println("e) 44999 tickets	£125,000 or Stunning Ferrari 488  			£4.99\n");
		System.out.println(
				"Do you wish to enter any of the above competitions? Enter (y)es to play or (n)o to pass on these competitions... ");

		sc = new Scanner(System.in);
		char choice = sc.next().charAt(0);

		boolean invalid = true;
		while (invalid) {

			if (choice == 'y' || choice == 'Y') {
				invalid = false;

				// Get the user's current balance
				currentBalance = Bank.getBalance(); // returns double bankBalance
				
				// get draw choice (a, b, c or d)
				drawChoice = ChooseDraw(); // -> Returns choice (char)

				// get the ticket price based on the drawChoice -> Returns ticketPrice (double)
				ticketPrice = GetTicketPrice(drawChoice);
				
				// Get the draw size to return MAX_RANGE for tickets
				MAX_RANGE = getTicketRange(drawChoice); // returns double MAX_RANGE
				
				// Find out how many tickets the user wishes to purchase -> returns int ticketQuantity
				ticketQuantity = GetUserTicketChoice(drawChoice);
								

				// get ticket quantity and determine max ticket quantity
				if (ticketQuantity <= MAX_RANGE) {
					// Valid
					// Tickets can be bought within this range

					// Check if the user has sufficient funds to buy the ticketQuantity -> returns boolean 
					sufficientFunds = Bank.runBalanceCheck(currentBalance, ticketPrice, drawChoice, ticketQuantity);
					
					if(sufficientFunds == false) {
						ticketQuantity = GetUserTicketChoice(drawChoice);
						sufficientFunds = Bank.runBalanceCheck(currentBalance, ticketPrice, drawChoice, ticketQuantity);
					}
					
					// Sufficient funds to proceed
					System.out.println("Success");
					
					// Generate x number of tickets for the user (within the range)
					userTicketNumbers = Random.generateUserTickets(MAX_RANGE, ticketQuantity);

				} else {
					// Invalid
					// Cannot over purchase tickets for competition
					System.out.println("Cannot over purchase tickets for competition");
				}

			} else if (choice == 'n' || choice == 'N') {
				invalid = false;
//					user returned false -> exiting the application
				System.out.println("User decided to decline to participate in the draws this time...");
				System.out.println("Exiting...");
				System.exit(0);
			} else {
				System.out.println("Invalid entry: " +choice);
			}
			
			
			runDraw(MAX_RANGE);

		}
	}

	// User is asked for draw choice -> set ticket price
	private static char ChooseDraw() {
		System.out.println("Brilliant. Which draw do you wish to enter? \n");
//		double bankBalance = Random.Rand();
//		bankBalance = 35555; // set larger budget

//		System.out.println("Your bank balance is £" + bankBalance + "\n");

		boolean invalid = true;

		char choice = sc.next().toLowerCase().charAt(0);
		while (invalid) {

			System.out.println("[>] Enter a, b, c, d or e...");
			if (choice == 'a' || choice == 'b' || choice == 'c' || choice == 'd' || choice == 'e') {
				// Valid choice
				invalid = false;
				return choice;
			}
		}
		return choice;
	}

	private static double GetTicketPrice(char choice) {
		// TODO Auto-generated method stub
		// Assign the ticket price amount based on the user selected draw
		switch (choice) {
		case 'a' -> ticketPrice = 19.99;
		case 'b' -> ticketPrice = 4.99;
		case 'c' -> ticketPrice = 8.99;
		case 'd' -> ticketPrice = 1.99;
		case 'e' -> ticketPrice = 4.99;
		default -> throw new IllegalArgumentException("Unexpected value: " + choice);
		}
		System.out.println("[Ticket Price] for draw: " + choice + " £" + ticketPrice);
		return ticketPrice;
	}

	private static int getTicketRange(char drawSize) {

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

	private static int GetUserTicketChoice(char drawChoice2) {
		System.out.println("How many tickets do you wish to puchase for the draw:" + drawChoice2 + " ?");
		int ticketQuantity = sc.nextInt();
		return ticketQuantity;

	}
	
	private static void runDraw(int mAX_RANGE2) {
		int winningNumber;
		entryList = Random.generateDrawNumbers(mAX_RANGE2);
		// Generate Random Winning Number between the max range and 1
		winningNumber = Random.drawNumber(mAX_RANGE2);
		// Print the Winning number to the console
		System.out.println("[Winning Number] " + winningNumber);

		// check the tickets for the winning number, take in the generatedTicketNumbers, the winningNumber and the complete list of tickets
		checkTickets(userTicketNumbers, winningNumber, entryList);
	}
	
	// Check if the winning number existed within the array of generated numbers
	private static void checkTickets(ArrayList<Integer> numberList2, int winningNumber, ArrayList<Integer> entryList2) {
		System.out.println("Checking your tickets...");	
		
		// For each integer in the ArrayList of 
//		for (Integer n : numberList2) {
//			System.out.println(n);
//			
//		}
	}

}
