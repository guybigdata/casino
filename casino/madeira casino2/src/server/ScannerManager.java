package server;

import java.util.Scanner;

public class ScannerManager {

	private Scanner scanner;
	
	
	public String getStringValueFromUser(String printToUser){
		scanner = new Scanner(System.in);
		System.out.println(printToUser);
		String userInputString = scanner.nextLine();
		return userInputString;
	}	
	public int getIntValueFromUser(String printToUser){
		scanner = new Scanner(System.in);
		System.out.println(printToUser);
		int userInputInt = scanner.nextInt();
		return userInputInt;	
			
	}
}
