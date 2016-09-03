package server;

import Accessors.GameTransactionEccessor;

public class RouletaTable {

	private int playerId;
	private int gambleAmount;
	private String gameResault; // win or lose
	private int gambelNumber;
	private int Amount;
	private int winningNumber;
	private String GambleOption;
	private ScannerManager scanner;
	private int balance;
	private String gametype;
	private GameTransactionEccessor gameTransactionEccessor;
	private String gambleColor;
	private String winningColor;
	RouletaWheel rouletaWheel = new RouletaWheel();

	public String getWinningColor() {
		return winningColor;
	}

	public void setWinningColor(String winningColor) {
		this.winningColor = winningColor;
	}

	public String getGambleColor() {
		return gambleColor;
	}

	public void setGambleColor(String gambleColor) {
		this.gambleColor = gambleColor;
	}

	public RouletaTable() {
		gameTransactionEccessor = new GameTransactionEccessor();
		scanner = new ScannerManager();
	}

	public String getGametype() {
		return gametype;
	}

	public void setGameType(String gametype) {
		this.gametype = gametype;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
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

	public int getAmount() {
		return Amount;
	}

	public void setAmount(int Amount) {
		this.Amount = Amount;
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

	public void startGame() {

		int menu = scanner.getIntValueFromUser("To gamble Enter 1>\nTo go back to " + "'GAME ZONE' enter 2> ");
		while (menu != 3) {
			if (menu == 1) {
				gambleProcces();
			} else if (menu == 2) {

			} else {
				System.out.println("Wrong number, Please chose 1 or 2: ");
			}
			menu = 3;
		}
	}

	private void gambleProcces() {
		int gambleAmount = scanner.getIntValueFromUser("How much are you bringing to the table? ");
		Boolean ifBalanceExists = validateBalance(gambleAmount);
		if (ifBalanceExists == false) {
			startGame();

		} else {

			setGambleAmount(gambleAmount);
			String gambleType = scanner.getStringValueFromUser("Enter \"n\" to gamble on number or \"c\" for color?");// later
																														// etc..

			if (gambleType.equals("n")) {
				setGambleOption(gambleType);
				int gambleNumber = scanner.getIntValueFromUser("pick a number between 1-36?");
				setGamblNumber(gambleNumber);
				printRowllingNumbers();
				int winingNumber = rouletaWheel.lotteryNumber();
				setWiningNumber(winingNumber);
				endNumbersRound();

			} else if (gambleType.equals("c")) {
				setGambleOption(gambleType);
				String gambleColor = scanner.getStringValueFromUser("pick \"b\" for black or \"r\" for red:");

				if (gambleColor.equals("b")) {
					setGambleColor(gambleColor);
					printRowllingNumbers();
					String winningColor = rouletaWheel.lotteryColor();
					setWinningColor(winningColor);
					endColorsRound();
				} else if (gambleColor.equals("r")) {
					setGambleColor(gambleColor);
					printRowllingNumbers();
					String winningColor = rouletaWheel.lotteryColor();
					setWinningColor(winningColor);
					endColorsRound();
				} else {
					System.out.println("Wrong input please type \"r\" or \"b\" ");
					gambleType = "c";
					gambleProcces();

				}

			} else {
				System.out.println("Wrong input please type \"n\" or \"c\" ");
				gambleProcces();
			}
		}
	}

	private void endNumbersRound() {
		if (this.winningNumber == this.gambelNumber) {
			String win = "win";
			setGameResault(win);
			int winningPrise = gambleAmount * 4;
			setAmount(winningPrise);
			System.out.println(
					"You Won!! Congradulation!! your winning amount is " + winningPrise + " dollar. Luky you!");
			setBalance(balance + winningPrise);
			gameTransactionEccessor.saveNumberTranHistory(this);
			startGame();

		} else {
			System.out.println(
					"The winning number is " + this.winningNumber + " your loss is " + gambleAmount + " dollar");
			setAmount(gambleAmount);
			setBalance(balance - gambleAmount);
			String lose = "lose";
			setGameResault(lose);
			gameTransactionEccessor.saveNumberTranHistory(this);
			startGame();
		}
	}

	private void endColorsRound() {
		if (this.winningColor.equals(this.gambleColor)) {
			String win = "win";
			setGameResault(win);
			int winningPrise = gambleAmount * 3;
			setAmount(winningPrise);
			System.out.println("You Won!! Congradulation!! your winning amount is " + winningPrise + " dollar. Luky you!");
			setBalance(balance + winningPrise);
			gameTransactionEccessor.saveColorTranHistory(this);
			startGame();

		} else {
			System.out.println("The winning color is " + this.winningColor + " your loss is "
		        + gambleAmount + " dollar");
			setAmount(gambleAmount);
			setBalance(balance - gambleAmount);
			String lose = "lose";
			setGameResault(lose);
			gameTransactionEccessor.saveColorTranHistory(this);
			startGame();

		}
	}

	private boolean validateBalance(int gambleAmount) {
		if (this.balance < gambleAmount) {
			System.out.println("Sorry but you don't have enaf chips, please go back to 'GAME ZONE' to buy more chips");
			return false;
		} else {
			return true;
		}
	}

	private void printRowllingNumbers() {
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

	public long getNumOfWin() {
		long winNum = gameTransactionEccessor.getNumOfWin(this);
		return winNum;
	}

	public void getLuckyNum() {
		// FindIterable<Document> luckyNum =
		gameTransactionEccessor.getLuckyNu(this);
		// return luckyNum;

	}

	public Object getHighlyWinAmount() {
		Object highlyWinAmount = gameTransactionEccessor.getHighlyWinAmount(this);
		return highlyWinAmount;
	}

}
