package ie.gmit.dip;

public class Bank {
	private static double bankBalance;
	private static double ticketCost;
	private boolean balanceNotVerified;
	private static int maxBuys;

	// Return a bank balance
	public static double getBalance() {
		// return single number as balance between 1 and 1000
		bankBalance = Random.GenerateBankBalanceMaxOneThousand();
		return bankBalance;

	}

	public static boolean runBalanceCheck(double currentBalance, double ticketPrice, char drawChoice,
			int ticketQuantity) {
		// Get total cost of tickets the user wishes to purchase
		ticketCost = (ticketQuantity * ticketPrice);
		// Get the maximum number of tickets the user could purchase with their balance
		maxBuys = (int) Math.floor(bankBalance / ticketPrice);

		// Define loop control
		boolean validBalanceUnverified = true;

		while (validBalanceUnverified) {

			System.out.println(ticketQuantity + " tickets cost £" + ticketCost);

			if (ticketCost > currentBalance) {
				// Rerun loop
				System.out.println("You have insufficient funds. You can only awford to buy " + maxBuys
						+ " tickets. Please choose less tickets");
				// Stop loop
				validBalanceUnverified = false;

			} else {
//				Sufficient funds to purchace tickets, break loop
				validBalanceUnverified = false;
				System.out.println("You have sufficient funds to purchase the tickets...");
			}

		}
		return validBalanceUnverified;

	}

}
