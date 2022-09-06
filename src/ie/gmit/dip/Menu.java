package ie.gmit.dip;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
	private static double initialBalanceCheckWithBank;
	private static double currentBalance;
	private static double sufficientFunds;
	private static double MIN_BALANCE = 2.50;
	private static Scanner sc;

	public void MainMenu() {
		System.out.println("##########################################");
		System.out.println("########## Number Guessing Game ##########");
		System.out.println("##########################################");

		Options();
	}

	public void Options() {
		System.out.println("###################### Rules #######################");
		System.out.println("1. You will be presented with a random draw with a random prize:");
		System.out.println("2. You are given the choice to participate in the competition");
		System.out.println("3. You are presented with:");
		System.out.println("	a) the ticket cost");
		System.out.println("	b) the maximum number of entries");
		System.out.println("You are presented with your bank balance and asked if you wish to enter this draw.");
		System.out.println("Are you ready to play? Enter (y)es to play or (n)o to exit... ");

		GetUserChoice();
	}

	private void GetUserChoice() {

		sc = new Scanner(System.in);

		char choice = sc.next().charAt(0);
		if (choice == 'y' || choice == 'Y') {

			// Check the user has funds in their account
			initialBalanceCheckWithBank = this.checkBalance();

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
		double awfordDraw;

		// Connect to the vendor and check the available draws
		boolean participate = Draw.TodaysDraw();

		if (participate) {
			System.out.println("User decided to participate...");
			System.out.println("Brilliant. Which draw do you wish to enter? \n");

			// Get draw choice
			drawChoice = Draw.ChooseDraw(); // -> returns char [option]

			// Get ticket price
			ticketPrice = Draw.GetTicketPrice(drawChoice); // Passes in -> char [option]

			// get max tickets -> pass in char
			maxTickets = Draw.getTicketRange(drawChoice); // Passes in -> char [option]

			// Find out how many tickets the user could purchase at a maximum
			awfordDraw = (int) (Math.floor(currentBalance2 / ticketPrice));

			System.out.println("[Balance] ->> £" + currentBalance2);
			System.out.println("[Draw Choice] ->> " + drawChoice);
			System.out.println("Ticket Price ->>>>>>>>> £" + ticketPrice);
			System.out.println("[Max Entries] ->> " + maxTickets);

			// Find out how many tickets the user wants to buy; pass in the max they can
			// afford
			ticketQuantity = Draw.GetUserTicketChoice(drawChoice, awfordDraw);

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
			System.out.println("You can awford to buy [" + maxlimit + "] tickets");

			// verify the user can awford the tickets
			ticketQuote = Bank.runBalanceCheck(currentBalance2, ticketPrice, drawChoice, ticketQuantity);
			result = (int) (currentBalance - ticketQuote);

			System.out.println("[Current Balance] £" + currentBalance2);
			System.out.println("[Balance after purchase] £" + result);

			if (result < 0) {
				System.out.println("This would have put you in debt to the tune of £" + result);
				System.out.println("The bank has declined your transaction");

			} else if (result > 0) {
				System.out.println("The bank has approved your transaction");
				System.out.println("Your current balance is now £" + result);
				System.out.println("Running the draw.. please wait [!]");

//				Entry Point
				// run draw
				// 1. pass in the final approved number of tickets / numbers to generate
				// 2. generate the numbers

				ArrayList<Integer> userTickets = Random.generateUserTickets(maxTickets, ticketQuantity);

				Draw.runDraw(maxTickets, userTickets, ticketPrice);
			}

		} else {
			System.out.println("User decided to decline to participate in the draws this time...");
		}

	}

	private static void calculateCostBenefit() {

	}

	private double checkBalance() {
		// Get the user's current balance
		currentBalance = (double) Bank.getBalance(); // returns double bankBalance
		return currentBalance;
	}

//	private double getBankBalance() {
//		double currentBalance = Bank.getBalance();
//		return currentBalance;
//	}

}
