package ie.gmit.dip;

import java.util.Scanner;

public class Draw {
	private static Scanner sc;
	private static double ticketPrice;

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
				ChooseDraw();
			} else {
				invalid = false;
//					user returned false -> exiting the application
				System.out.println("User decided to decline to participate in the draws this time...");
				System.out.println("Exiting...");
				System.exit(0);
			}

		}
	}

	// User is asked for draw choice
	private static double ChooseDraw() {
		System.out.println("Brilliant. Which draw do you wish to enter? \n");
		System.out.println("[>] Enter a, b, c, d or e...");

//		double bankBalance = Random.Rand();
//		bankBalance = 35555; // set larger budget

//		System.out.println("Your bank balance is £" + bankBalance + "\n");

		char drawSize = sc.next().toLowerCase().charAt(0);

		// Assign the ticket price amount based on the user selected draw
		switch (drawSize) {
		case 'a' -> ticketPrice = 19.99;
		case 'b' -> ticketPrice = 4.99;
		case 'c' -> ticketPrice = 8.99;
		case 'd' -> ticketPrice = 1.99;
		case 'e' -> ticketPrice = 4.99;
		default -> throw new IllegalArgumentException("Unexpected value: " + drawSize);
		}
		return ticketPrice;

	}
}
