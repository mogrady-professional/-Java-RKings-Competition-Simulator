package ie.gmit.dip;

import java.util.ArrayList;
import java.util.Scanner;

public class Draw {
	private static Scanner sc;
	private static double ticketPrice;
	private static ArrayList<Integer> entryList;
	private static int counter;
	public static boolean TodaysDraw() {
		sc = new Scanner(System.in);

		System.out.println("Welcome to RKing Competitions...\n");
		System.out.println("The following draws are currently available: ");
		System.out.println("a) 499 tickets		Low Odds 2004 Subaru Impreza WRX			£19.99");
		System.out.println("b) 4995 tickets		Audi A4 S-Line						£4.99");
		System.out.println("c) 9995 tickets 	Audi A6 S-Line						£8.99");
		System.out.println("d) 34999 tickets	Nissan R35 GTR JDM 800bhp				£1.99");
		System.out.println("e) 44999 tickets	£125,000 or Stunning Ferrari 488  			£4.99\n");
	
		System.out.println("Do you wish to enter any of the competitions? \nEnter (y)es to play or (n)o to pass...");
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


	// User is asked for draw choice -> set ticket price
	public static char ChooseDraw() {
		System.out.println("Enter (a), (b), (c), (d) or (e)");
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

	public static double GetTicketPrice(char draw, double currentBalance2) {
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
		System.out.println("It costs [£" + ticketPrice + "] for [1] ticket. Your balance is: [£" + currentBalance2 + "] Do you wish to continue [?]");
		System.out.println("Please enter (y)es or (n)o");
		decision = sc.next().charAt(0);

		if (decision == 'y' || decision == 'Y') {
			System.out.println("User has agreed to proceed with this draw...");
			return ticketPrice;
		} else if (decision == 'n' || decision == 'N') {
			System.out.println("Would you like to choose another draw instead [?]");
			System.out.println("Please enter  (y)es or (n)o");
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
		// Temporary assigned instance variables
		int maxTickets;
		
		switch (drawSize) {
		case 'a' -> maxTickets = 499;
		case 'b' -> maxTickets = 4995;
		case 'c' -> maxTickets = 9995;
		case 'd' -> maxTickets = 34999;
		case 'e' -> maxTickets = 44999;
		default -> throw new IllegalArgumentException("Unexpected value: " + drawSize);
		}

		return maxTickets;
	}

	public static int GetUserTicketChoice(char drawChoice2, double affordDraw) {
		int ticketQuantity;
		if(affordDraw == 0) {
			System.out.println("Sorry you cannot afford to purchase a ticket for this draw...");
			ticketQuantity = 0;
		}else {
			System.out.println("How many tickets do you wish to puchase for the draw: [" + drawChoice2 + "] ?");
			System.out.println("[i] You can afford [" + affordDraw + "] tickets.\nEnter number of tickets:");
			ticketQuantity = sc.nextInt();
		}
		return ticketQuantity;
	}
	

	public static void runDraw(int maxTickets, ArrayList<Integer> userTickets, double ticketPrice) {
		int winningNumber;
		// Generate all the numbers up to the max entries
		entryList = Random.generateDrawNumbers(maxTickets);
		// Generate Random Winning Number between the max range and 1
		winningNumber = Random.drawNumber(maxTickets);
		// Print the Winning number to the console
		System.out.println("[Winning Number] " + winningNumber);

		// check the tickets for the winning number, take in the generatedTicketNumbers,
		// the winningNumber and the complete list of tickets
		checkTickets(maxTickets,ticketPrice, userTickets, winningNumber, entryList);
	}
	
	public static void runCustomDraw(int maxTickets, double ticketPrice, int ticketQuantity) {
		int winningNumber;
//		maxTickets = MAX_RANGE;
		// Create an ArrayList of user tickets
		
		long userTicketGenerationTime = System.currentTimeMillis();
		ArrayList<Integer> userTickets = Random.generateUserTickets(maxTickets, ticketQuantity);
				System.out.println("User Ticket Generation time (ms): " + (System.currentTimeMillis() - userTicketGenerationTime));
		
		// Generate all the numbers up to the max entries
		long ticketNumberGenerationTime = System.currentTimeMillis();
		entryList = Random.generateDrawNumbers(maxTickets);
		System.out.println("Ticket Number Generation time (ms): " + (System.currentTimeMillis() - ticketNumberGenerationTime));
		
		// Generate winning number within range
		winningNumber =  Random.drawNumber(maxTickets);
		
		// Print the Winning number to the console
		System.out.println("[Winning Number] " + winningNumber);
		System.out.println(maxTickets);
		System.out.println(userTickets);
		System.out.println(ticketPrice);
		// check the tickets for the winning number, take in the generatedTicketNumbers,
		// the winningNumber and the complete list of tickets
		
//		Set start time
		long ticketCheckTime = System.currentTimeMillis();
		checkTickets(maxTickets, ticketPrice, userTickets, winningNumber, entryList);
		System.out.println("Ticket check time (ms): " + (System.currentTimeMillis() - ticketCheckTime));
		
		
		System.out.println("Total time (ms): " + (System.currentTimeMillis() - userTicketGenerationTime));

	}

	// Check if the winning number existed within the array of generated numbers
	private static void checkTickets(int maxTickets, double ticketPrice, ArrayList<Integer> userTickets, int winningNumber,
			ArrayList<Integer> entryList) {

		double totalCost = (double) (ticketPrice * userTickets.size());
		int numberOfTickets = (int) (userTickets.size());
		System.out.println("Checking your tickets...");
		boolean beginDraw = true;

		// Start draw -> keep the draw running infinite
		while (beginDraw) {

			// for each possible ticket in the entryList -> (you can't possibly draw more
			// tickets than the maximum allowed in the competition in order to win)
			for (int i = 0; i < entryList.size();) {
				// Increment count
				counter++;
				double winCost = (double) (ticketPrice * counter);
				
				if (userTickets.contains(winningNumber)) {
					// the winning number IS contained within the usersTicketNumbers

					
					// Real win -> On the first draw, one of the users tickets has came up
					if (userTickets.contains(winningNumber) && counter == 1) {
						System.out.println("\nYou won! Congratulations...");
						System.out.println("One of your number(s) was drawn, number: [" + winningNumber
								+ "] on draw number [" + counter + "] at a cost of £" + winCost + ". However, you spent a total of £" + totalCost + " on " + numberOfTickets + " tickets for this draw.");
						beginDraw = true;
						return;
						
					}
					
					// Win within ticket range -> user purchased x count of tickets, one of the numbers came up within within the number of tickets the user had
					if (userTickets.contains(winningNumber) && counter <= userTickets.size()) {
						System.out.println("\nSorry, you were unsuccessful.");
						System.out.println("One of your number(s) was drawn within the range of tickets you purchased, number: [" + winningNumber
								+ "] and it would have taken [" + counter + "] draws to have won this competition at a cost of £" +winCost + ". However you spent a total of £" + totalCost + " on " + numberOfTickets + " tickets for this draw.");
						beginDraw = true;
						return;

					}
					// If the number was not drawn within the number of tickets bought -> no win,
					// but number was drawn eventually at a finite cost.

					// the winning number is contained within the usersTicketNumbers
					System.out.println("\nSorry you never had a chance. None of your numbers even came up at all within the range of tickets you bought.");
    				System.out.println("However, one of them was eventually drawn, number: [" + winningNumber + "] but it took [" + counter + "] draws and would have cost you: £"+winCost);

					beginDraw = true;
					return;
				} else {
//    				// the winning number is NOT contained within the usersTicketNumbers -> but must be drawn eventually (the users numbers do not change & random number is always generated within the range)

//					System.out.println("Draw #[" + counter + "] " + "Winning number is [" + winningNumber
//							+ "] and is NOT contained within the users TicketNumbers" +userTickets);

					// Redraw -> Pull another number within the range of the draw until a number
					// from the users range of tickets is chosen
					winningNumber = Random.drawNumber(maxTickets);
				}
//				System.out.println(counter);
			}
			beginDraw = false;
		}
	}

}
