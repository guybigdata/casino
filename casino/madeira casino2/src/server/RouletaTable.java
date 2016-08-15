package server;

import jdk.nashorn.internal.ir.ContinueNode;

public class RouletaTable {
	
	private int playerId;
	private int gambleAmount;     
	private String gameResault;   //win or lose
	//private String colorGamble ;
	private int gambelNumber;
	private int userWiningAmount;
	private int winningNumber;
	private int userLosingAmount;
	private String GambleOption;
	private ScannerManager scanner;
	private int balance;
	
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public RouletaTable(){
		 scanner = new ScannerManager();
	}
	
	public String getGambleOption() {
		return GambleOption;
	}

	public void setGambleOption(String gambleOption) {
		GambleOption = gambleOption;
	}

	public int getWiningNumber() {
		return winningNumber;
	}
	public void setWiningNumber(int winingNumber) {
		this.winningNumber = winingNumber;
	}
	public int getUserWiningAmount() {
		return userWiningAmount;
	}
	public void setUserWiningAmount(int userWiningAmount) {
		this.userWiningAmount = userWiningAmount;
	}
	public int getUserLosingAmount() {
		return userLosingAmount;
	}
	public void setUserLosingAmount(int userLosingAmount) {
		this.userLosingAmount = userLosingAmount;
	}
	public int getGamblNumber() {
		return gambelNumber;
	}
	public void setGamblNumber(int gamblNumber) {
		this.gambelNumber = gamblNumber;
	}

	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int PlayerId) {
		this.playerId = PlayerId;
	}

	public int getGambleAmount() {
		return gambleAmount;
	}
	public void setGambleAmount(int gambleAmount) {
		this.gambleAmount = gambleAmount;
	}

	public String getGameResault() {
		return gameResault;
	}
	public void setGameResault(String gameResault) {
		this.gameResault = gameResault;
	}

	public void startGame(){
		
		int menu = scanner.getIntValueFromUser("To gamble Enter 1>\nTo go back to 'GAME ZONE' enter 2> ");
		while (menu !=3){
			if (menu == 1){
				gambleProcces();
			}
			else if(menu == 2){
				
			}
			else{
				System.out.println("Wrong number, Please chose 1 or 2: ");
			}
			menu = 3;
		}
	}
	
	private void gambleProcces(){
		int gambleAmount = scanner.getIntValueFromUser("How much are you bringing to the table? ");
		Boolean ifBalanceExists = validateBalance(gambleAmount);
		if (ifBalanceExists == false){
			startGame();
		}
		else{
			
			setGambleAmount(gambleAmount);
	 		String gambleType = scanner.getStringValueFromUser("Enter n to gamble on number or o for color?");//later we can add or color or range etc..
			
			if (gambleType.equals("n")){
				setGambleOption(gambleType);
				int gambleNumber = scanner.getIntValueFromUser("pick a number between 1-36?");
				setGamblNumber(gambleNumber);
				printRowllingNumbers();
				RouletaWheel rouletaWhee = new RouletaWheel();
				int winingNumber = rouletaWhee.lotteryNumber();
				setWiningNumber(winingNumber);
				endRound();
			}
			else{
					System.out.println("Color Will coming soon");
					gambleType = "n";
					gambleProcces();
			}
			
		}
	}
	


	private void endRound(){	
			if (this.winningNumber == this.gambelNumber){
				String win = "win";
				setGameResault(win);
				int winningPrise = gambleAmount*4;
				setUserWiningAmount(winningPrise);
				System.out.println("You Won!! Congradulation!! your winning amount is " + winningPrise + " dollar. Luky you!");
				setBalance(balance + winningPrise);
				
			}
		
	    	else{
	    		System.out.println("The winning number is " + this.winningNumber + " your loss is "
	    				+ gambleAmount + " dollar");
	    		setUserLosingAmount(gambleAmount);
	    		setBalance(balance - gambleAmount);
	    		startGame();
				

			}
			
		}
	
	private boolean validateBalance(int gambleAmount) {
		if (this.balance < gambleAmount){
			System.out.println("Sorry but you don't have enaf chips, please go back to 'GAME ZONE' to buy more chips");
			return false;
		}
		else{
			return true;
		}
		
		
	}
	
		
	private void printRowllingNumbers(){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
				System.out.println();
				System.out.println("The table is close to gamble... wheel start rolling");
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
				System.out.print(2);
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
				System.out.print(15);
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
				System.out.print(14);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
				System.out.print(5);
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
				System.out.print(12);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
				System.out.print(22);
			try {
				Thread.sleep(80);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
				System.out.print(7);
			try {
				Thread.sleep(70);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
				System.out.print(14);
			try {
				Thread.sleep(60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
				System.out.print(24);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
				System.out.print(3);
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
				System.out.print(2);
			try {
				Thread.sleep(700);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
			
				System.out.println(".........");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
				System.out.println("DONE ROLLING");
				System.out.println();
				System.out.println();
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
			
		}
	
}

// if he lose we need to subtract the gamble amount from the total amount in accountdetails class