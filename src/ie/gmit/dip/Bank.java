package ie.gmit.dip;

public class Bank {
	private static double bankBalance;
	private static double ticketCost;
	private static int maxBuys;
	private static double MIN_BALANCE = 2.50;
	
	
	// Return a bank balance
	public static double getBalance() {
				
		// return single number as balance between 1 and 1000
//		bankBalance =  Random.GenerateBankBalanceMaxOneThousand();
		bankBalance = 100;
		
		// Run credit check on client
		boolean creditCheckResult = creditCheck(bankBalance);
		
		if(creditCheckResult) {			
			return bankBalance;
		}
		
		return 0;
	}

	private static boolean creditCheck(double bankBalance) {
		// Balance must be above MIN_BALANCE
		if (bankBalance > MIN_BALANCE) {
			return true;
		}
		return false;
	}
	

	public static double runBalanceCheck(double currentBalance, double ticketPrice, char drawChoice,
			int ticketQuantity) {
		// Get total cost of tickets the user wishes to purchase
		ticketCost = (ticketQuantity * ticketPrice);
		// Get the maximum number of tickets the user could purchase with their balance
		maxBuys = (int) Math.floor(bankBalance / ticketPrice);

		// Define loop control

		System.out.println(ticketQuantity + " tickets cost £" + ticketCost);

		if (ticketCost > currentBalance) {
			// Rerun loop
			System.out.println("You have insufficient funds. You can only awford to buy " + maxBuys
					+ " tickets. Please choose less tickets next time.");
			return ticketCost;
		} else {
			System.out.println("You have sufficient funds to purchase the tickets...");
		}

		return ticketCost;

	}

}
