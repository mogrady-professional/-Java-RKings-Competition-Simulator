package ie.gmit.dip;

import java.util.Scanner;

public class Menu {
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

			Draw.TodaysDraw();
		} else {
			System.out.println("User decided to decline to participate in the draws this time...");
			System.out.println("Exiting...");
			System.exit(0);
		}

	}

//	private double getBankBalance() {
//		double currentBalance = Bank.getBalance();
//		return currentBalance;
//	}



}
