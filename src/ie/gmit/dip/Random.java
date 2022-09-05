package ie.gmit.dip;


// Class to generate a random number between 1 and 1000
public class Random {

	public static int SingleRandomNumber() {
		int number = (int) ((Math.random() * (1000 - 1)) + 1); // integer value between 1 and 1000
		
		System.out.println("[Random] Random Generated Number: " + number);
		return number;
	}
	
}
