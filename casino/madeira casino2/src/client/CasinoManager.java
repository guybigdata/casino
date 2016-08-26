package client;

import server.TransactionHistory;
import server.ScannerManager;
import server.UsersDetails;
import server.Users;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;

import server.PaymentDetails;
import server.RouletaTable;


public class CasinoManager 
{
	//int userSelection;  
	private Users user ;   
	private ScannerManager scannerManager;
	public PaymentDetails paymentMethod;
	public TransactionHistory Transaction;
	private int chipsAmount;
	RouletaTable rouletaTable; 
	
	public CasinoManager()    
	{
		user = new Users();   //member of game manager class for the ability to get the user id for all the class
		scannerManager = new ScannerManager();
		paymentMethod = new PaymentDetails();
		Transaction = new TransactionHistory();
		rouletaTable = new RouletaTable();
	}
	
	public void Start()
	{
		
		int userSelection = scannerManager.getIntValueFromUser("To signin Enter 1: \nTo Create user and Start to play press 2:\nTo exit enter 3: ");
		
		switch (userSelection) {
					
			case 1:
				signIn(); 
				gameZone();
				break;
			
			case 2: 

				signUp(); // need to check if user already exists
				System.out.println("Enter your new login details: ");
				signIn();
				savePurchaseTransaction();
				
				gameZone();
				break;
				
			case 3: 
				System.out.println("Hopefully to see you soon, bye bye");
				System.exit(0); 
			
			default:
		    	System.out.println("Wrong input, please enter 1,2 or 3");
				Start();
		    }
			
		}
		
	
	
	
	public void gameZone() {

		System.out.println("welcome to the game zone");
		int mainMenu = scannerManager.getIntValueFromUser("To play Enter 1:"
				+ "\nTo buy chips Enter 2:"
				+ "\nGo to your 'game zone reports' Enter 3 ");

		switch (mainMenu) 
		{	
			case 1:
			
				String gameType = scannerManager.getStringValueFromUser("Which game would you "
						+ "like to play? Enter r for rouleta: ");
				
				
				if (gameType.equals("r")){
					
					rouletaTable.setGameType(gameType);
					rouletaTable.setPlayerId(this.user.getUserId());
					rouletaTable.setBalance(user.getUserBalance());
					rouletaTable.startGame();
					user.setUserBalance(rouletaTable.getBalance());
					user.updateUserBalance();
					gameZone();
					
				}else{
						System.out.println("More games will comming soon, please enter 'r':");
				}
			
			case 2:
				boolean numOfUserId = Transaction.ifPaymentDetailsExists();
				
				if (numOfUserId == true){
					chipsAmount = scannerManager.getIntValueFromUser("Welcome to the"
							+ " cash desk,"
							+ " Enter how much chips you wish to purchase  (please Enter"
							+ " 10,20,30,40,50,100,500 or 1000)?"); 
					String confirmCharge = scannerManager.getStringValueFromUser("Your"
							+ " account will"
							+ " charge in amount of " + chipsAmount + " dollar. "
									+ "are yoy confirm (yes/no)");
					if (confirmCharge.equals("yes")){
						System.out.print("In progress...");
						System.out.print(".");System.out.print(".");System.out.print(".");System.out.print(".");System.out.print(".");System.out.print(".");System.out.print(".");System.out.print(".");System.out.print(".");
						System.out.println("your balance id updated");
						savePurchaseTransaction();
						gameZone();
					}else{
							mainMenu = 2;
						}
				}else{
				        UsersDetails userDetails = new UsersDetails();
				        
						System.out.println("Payment method:\nPlease fill in the next filds ");
						String firstName = scannerManager.getStringValueFromUser("first name :");
						userDetails.setFirstName(firstName);
						String lastName = scannerManager.getStringValueFromUser("Last name :");
						userDetails.setLastName(lastName);
						String gender = scannerManager.getStringValueFromUser("Gender : 'M' for male ,'F' for femail:");
						userDetails.setGender(gender.toUpperCase());
						String phoneNumber = scannerManager.getStringValueFromUser("Phone Number :");
						userDetails.setPhoneNumber(phoneNumber);
						System.out.println("Address:");
						String street = scannerManager.getStringValueFromUser("Street : ");
						String streetNumber = scannerManager.getStringValueFromUser("Street number : ");
						userDetails.setStreet(street+ " "+ streetNumber);
						String city = scannerManager.getStringValueFromUser("City :");
						userDetails.setCity(city);
						boolean isCityIdExists = userDetails.getCityIdAsFK();
						if (isCityIdExists == true){
						
								String country = scannerManager.getStringValueFromUser("Country :");
								userDetails.setCountry(country);
								boolean isCountryExists = userDetails.getCountryID();
								if (isCountryExists == true){
								
									String email = scannerManager.getStringValueFromUser("Email address :");
									userDetails.setEmail(email);
									int year = scannerManager.getIntValueFromUser("Year of birth 'YYYY':");
									int month = scannerManager.getIntValueFromUser("Month of birth 'MM':");
									int day = scannerManager.getIntValueFromUser("Day of birth 'DD':");
									LocalDate birthDate = LocalDate.of(year,month,day);
									userDetails.setBirthDate(birthDate);
													
									userDetails.setuserId(user.getUserId());
									userDetails.save();
									
									
				
									String socialSec = scannerManager.getStringValueFromUser("Socialsec :");
									paymentMethod.setSocialsec(socialSec);
									String ownerFirstName = scannerManager.getStringValueFromUser("firstName : ");
									paymentMethod.setFirstName(ownerFirstName);
									String ownerLastName = scannerManager.getStringValueFromUser("lastName : ");
									paymentMethod.setLastName(ownerLastName);
									String ccType = scannerManager.getStringValueFromUser(" cc Type: \nFor Visa Type 1>,\nFor Isracard Type 2>");
									paymentMethod.setCcType(ccType);
									validateCcNumber();
									
									System.out.println("expirationDate : ");
									String expyear = scannerManager.getStringValueFromUser("Expiration Year 'YY':");
									String expmonth = scannerManager.getStringValueFromUser("Expiration Month 'MM':");
									String expDate = expyear + "-" + expmonth;
									paymentMethod.setExpirationDate(expDate);
									
									paymentMethod.setUserId(user.getUserId());
									
									paymentMethod.savePayment();
									
								
							}else{
									gameZone();
							}
						}else{
							gameZone();
						}
				}
			case 3:
				 reports();
			
			default:
				System.out.println("wrong input please enter 1, 2 or 3: ");
				gameZone();
			}
		
	}
	
	public void reports(){
		System.out.println("Welcometo your account reports!");
		int userSelection = scannerManager.getIntValueFromUser(""
				+ "Enter 1 to see your balance : "
				+ "\nEnter 2 to see your purchase:"
				+ "\nEnter 3 to see your games details:"
				+ "\nEnter 4 to return to the gameZone menu: ");
		
		while (userSelection != 5){
			if (userSelection == 1){
				System.out.println("Current balance : " + user.getUserBalance());
				reports();
				
			}else if (userSelection == 2){
				Transaction.setUserID(user.getUserId());
				int [] purchaseReport = Transaction.getUserPurchase();
				System.out.println("purchase amount :" + purchaseReport[0]);
				System.out.println("Chips quantity :" + purchaseReport[1]);
				reports();
				
			}else if (userSelection == 3){ 
				rouletaTable.setPlayerId(user.getUserId());
				int gameReport = rouletaTable.getNumOfWin();
				System.out.println("you won " + gameReport + " times: ");
				int luckyNum = rouletaTable.getLuckyNum();
				System.out.println("your lucky numbers is: " + luckyNum);
				System.out.println("your highly winning amount is  ");
				reports();
				
			}else if (userSelection == 4){
				gameZone();
				
			}else{
				System.out.println("Wrong input, please enter 1,2,3 or 4 ");
				reports();
			}	
					
		}
		System.out.println("Wrong input, please enter 1,2 or 3  ");
		reports();
	}
		
	private  void signIn() {
		String userName = scannerManager.getStringValueFromUser("User Name :");
		String password = scannerManager.getStringValueFromUser("password (8 char):");

		user.setPassword(password);
		user.setUserName(userName);
		boolean isUserExist = user.isUserExist();

		if (isUserExist == true) 
		{
			System.out.println("Successfully signed!");
		}
		else 
		{
			System.out.println("Worng user name or password! please try again or press 2 to sign up ");
		}
	}

	private void signUp() {
		String userName = scannerManager.getStringValueFromUser("Enter a User Name :");
		String password = scannerManager.getStringValueFromUser("Pick password (8 char):");

		Users User = new Users();
		User.setPassword(password);
		User.setUserName(userName);
		int balance = 100;
		Transaction.setTransactionAmount(balance);
		User.setUserBalance(balance);
		User.saveUser();
	
	}
	public void validateCcNumber(){
		String cardNumber = scannerManager.getStringValueFromUser("Card Number: ");
		boolean ccnumber = paymentMethod.setccNumber(cardNumber);
	
		while (ccnumber == false){
			validateCcNumber();
		}
	}
	
	private void savePurchaseTransaction(){
		
	Transaction.setTransactionAmount(chipsAmount); //we can create trigger to set value to 100 when insert newuser
	Transaction.setUserID(user.getUserId());
	Transaction.setTransactionType(Transaction.TransactionTypeList[0]);
	Transaction.setActionType(Transaction.actionTypeList[0]);
	if (Arrays.asList(Transaction.chipsQuantityList).contains(chipsAmount))
		Transaction.setChipsQuantity(chipsAmount);
		user.setUserBalance(user.getUserBalance()+ chipsAmount);
		user.updateUserBalance();
		Transaction.saveTransaction();

	}
}
