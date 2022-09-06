package ie.gmit.dip;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
	private static double currentBalance;
	private static Scanner sc;

	public void MainMenu() {

		Options();
	}

	public void Options() {
		System.out
				.println("******************************************************************************************");
		System.out
				.println("*                          RKings Competitions Simulator                                 *");
		System.out
				.println("*                                      Rules                                             *");
		System.out
				.println("* 1. You will be presented with a random draw with a random prize:                       *");
		System.out
				.println("* 2. You are given the choice to participate in the competition                          *");
		System.out
				.println("* 3. You are presented with:                                                             *");
		System.out
				.println("*     a) the ticket cost                                                                 *");
		System.out
				.println("*     b) the maximum number of entries                                                   *");
		System.out
				.println("* 4. You are presented with your bank balance and asked if you wish to enter this draw   *");
		System.out
				.println("******************************************************************************************");
		System.out
				.println("          [>] Are you ready to play? Enter (y)es to play or (n)o to exit...               ");

		GetUserChoice();
	}

	private void GetUserChoice() {
		double initialBalanceCheckWithBank = 0;

		sc = new Scanner(System.in);

		char choice = sc.next().charAt(0);
		if (choice == 'y' || choice == 'Y') {

			// give the user the option to provide a bank balance or get a random balance;
			boolean randomBalance = manageBalance();

			if (randomBalance == true) {
				// Check the user has funds in their account
				initialBalanceCheckWithBank = checkBalance();
			} else if (randomBalance == false) {
				System.out.println("Enter your balance [>]");
				double balance = sc.nextDouble();
				initialBalanceCheckWithBank = Bank.specifyBalance(balance);
				currentBalance = initialBalanceCheckWithBank;
			}

			// Boolean check (false) [0]
			if (initialBalanceCheckWithBank < 0) {
				System.out.println("The user has been declined by their bank...");
			} else {
				System.out
						.println("The user has been authorized by the bank to carry out a transaction successfully...");

//			System.out.println("[INITIAL BANK BALANCE] " + initialBalanceCheckWithBank);
				// Show user their bank balance
				System.out.println("You balance is: £" + currentBalance);
				this.runDraw(currentBalance);
			}

		} else if (choice == 'n' || choice == 'N') {
			System.out.println("User decided to decline to participate in the draws this time...");
			System.out.println("Exiting...");
			System.exit(0);
		} else {
//			Recall function
			System.out.println("Invalid choice, please enter [y] or [n]");
			this.GetUserChoice();
		}

	}

	private void runDraw(double currentBalance2) {
		char drawChoice;
		double ticketPrice;
		int maxTickets;
		int ticketQuantity;
		double ticketQuote;
		double result;
		double quicksum;
		int maxlimit;
		double affordDraw;

		// Connect to the vendor and check the available draws
		boolean participate = Draw.TodaysDraw();

		if (participate) {
			System.out.println("User decided to participate...");
			System.out.println("Brilliant. Which draw do you wish to enter? \n");

			// Get draw choice
			drawChoice = Draw.ChooseDraw(); // -> returns char [option]

			// Get ticket price
			ticketPrice = Draw.GetTicketPrice(drawChoice, currentBalance2); // Passes in -> char [option]

			// get max tickets -> pass in char
			maxTickets = Draw.getTicketRange(drawChoice); // Passes in -> char [option]

			// Find out how many tickets the user could purchase at a maximum
			affordDraw = (int) (Math.floor(currentBalance2 / ticketPrice));

			System.out.println("[Balance] ->> £" + currentBalance2);
			System.out.println("[Draw Choice] ->> " + drawChoice);
			System.out.println("Ticket Price ->>>>>>>>> £" + ticketPrice);
			System.out.println("[Max Entries] ->> " + maxTickets);

			// Find out how many tickets the user wants to buy; pass in the max they can
			// afford
			ticketQuantity = Draw.GetUserTicketChoice(drawChoice, affordDraw);

			// User has either decided to purchase 0 tickets, or the user cannot afford to
			// Purchase even 1 ticket for this draw
			if (ticketQuantity == 0) {
				System.out.println("Sorry you do not have sufficient funds to participate in this draw...\nExiting...");
				return;
			}

			System.out.println("User wants to purchase [" + ticketQuantity + "] tickets");

			quicksum = (double) (ticketPrice * ticketQuantity);

			System.out.println(ticketQuantity + " tickets cost £" + quicksum);

			maxlimit = (int) Math.floor(currentBalance2 / ticketPrice);
			System.out.println("You can afford to buy [" + maxlimit + "] tickets");

			// verify the user can afford the tickets
			ticketQuote = Bank.runBalanceCheck(currentBalance2, ticketPrice, drawChoice, ticketQuantity);
			result = (double) (currentBalance2 - ticketQuote);

			System.out.println("[Current Balance] £" + currentBalance2);
			System.out.println("[Balance after purchase] £" + result);

			if (result < 0) {
				System.out.println("This would have put you in debt to the tune of £" + result);
				System.out.println("The bank has declined your transaction");

			} else if (result > 0) {
				System.out.println("The bank has approved your transaction");
				System.out.println("Your current balance is now £" + result);
				System.out.println("Running the draw.. please wait [!]");

				ArrayList<Integer> userTickets = Random.generateUserTickets(maxTickets, ticketQuantity);

				Draw.runDraw(maxTickets, userTickets, ticketPrice);
				System.out.println("Your bank balance is now £" + result);
				double rollingBalance = (double) (result);
				if (rollingBalance > 0) {
					System.out.println(
							"\nYou still have £" + result + " money remaining would you like to do another draw[?]");
					System.out.println("Enter (y)es or (n)o [>]");
					char choice = sc.next().charAt(0);
					if (choice == 'y' || choice == 'Y') {
						// User wants another draw
//						Recall function
						this.runDraw(rollingBalance);
					} else if (choice == 'n' || choice == 'N') {
						// User wishes to quit
						System.out.println("Smart choice... try again another time!\nThank you for participating.");
					} else {
						System.out.println("Invalid choice, please enter [y] or [n]");
					}
				} else {
					System.out.println("No money left!");
					return;
				}
			}

		} else {
			System.out.println("User decided to decline to participate in the draws this time...");
		}

	}

	private static double checkBalance() {
		// Get the user's current balance
		currentBalance = (double) Bank.getBalance(); // returns double bankBalance
		return currentBalance;
	}

	public static boolean manageBalance() {
		char choice;
		// ask the user if they wish to be assigned a random bank balance or specify
		// their balance
		System.out.println("Would you like to be assigned a random bank balance, or specify your bank balance?");
		System.out.println("Enter (r)andom or (s)pecify your balance");

		choice = sc.next().charAt(0);

		if (choice == 'r' || choice == 'R') {
			// User wants a random balance
			return true;
		} else if (choice == 's' || choice == 'S') {
			// User wishes to specify their balance
			return false;
		} else {
//			Recall function
			System.out.println("Invalid choice, please enter [r] or [s]");
			manageBalance();
		}
		return false;
	}
}
