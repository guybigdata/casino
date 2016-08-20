package client;

import server.TransactionHistory;
import server.ScannerManager;
import server.UsersDetails;
import server.Users;

import java.time.LocalDate;
import java.util.Calendar;

import server.PaymentDetails;
import server.RouletaTable;


public class CasinoManager 
{
	int userSelection;  
	private Users user ;   
	private ScannerManager scannerManager;
	private String gameType;
	
	public CasinoManager()    
	{
		user = new Users();   //member of game manager class for the ability to get the user id for all the class
		scannerManager = new ScannerManager();
	}
	
	public void Start()
	{
		
		int userSelection = scannerManager.getIntValueFromUser("To signin Enter 1: \nTo Create user and Start to play press 2:\nTo exit enter 3: ");
		
		while (userSelection != 4) {
					
			if (userSelection == 1) {
				signIn(); 
				gameZone();
			}
			
			else if (userSelection == 2) {

				signUp(); // need to check if user already exists
				System.out.println("Enter your new login details: ");
				signIn();
				
				TransactionHistory Transaction = new TransactionHistory();
				Transaction.setTransactionAmount(user.getUserBalance()); //we can create trigger to set value to 100 when insert newuser
				Transaction.setUserID(user.getUserId());
				Transaction.setTransactionType(Transaction.TransactionTypeList[0]);
				Transaction.setActionType(Transaction.actionTypeList[0]);
				Transaction.setChipsQuantity(Transaction.chipsQuantityList[5]);
				Transaction.saveTransaction();
				
				gameZone();
			}	
		    else if (userSelection == 3){
				System.out.println("Hopefully to see you soon, bye bye");
				System.exit(0); 
			}
		    else{
		    	System.out.println("Wrong input, please enter 1,2 or 3");
				Start();
		    }
			
		}
		System.out.println("Wrong input, please enter 1,2 or 3");
		Start();
	}
	
	
	public void gameZone() {

		System.out.println("welcome to the game zone");
		int mainMenu = scannerManager.getIntValueFromUser("To play Enter 1:"
				+ "\nTo buy chips Enter 2:"
				+ "\nGo to your 'game zone reports' Enter 3 ");

		while (mainMenu != 3) 
		{	
			if (mainMenu == 1)
			{
				String gameType = scannerManager.getStringValueFromUser("Which game would you "
						+ "like to play? Enter r for rouleta: ");
				
				
				if (gameType.equals("r")){
					RouletaTable rouletaTable = new RouletaTable();
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
			}
			if (mainMenu == 2){ //if the user want to buy chips before playing first time
				//need to update balance
			
				TransactionHistory Transaction = new TransactionHistory();
				Transaction.setUserID(user.getUserId());
				boolean numOfUserId = Transaction.ifPaymentDetailsExists();
				
				if (numOfUserId == true){
					int chipsAmount = scannerManager.getIntValueFromUser("Welcome to the cash desk,"
							+ " Enter how much chips you wish to purchase  (please Enter 10,20,30,40 or 50)?"); 
					String confirmCharge = scannerManager.getStringValueFromUser("Your account will"
							+ " charge in amount of " + chipsAmount + " dollar. "
									+ "are yoy confirm (yes/no)");
					if (confirmCharge.equals("yes")){
						System.out.print("In progress...");
						System.out.print(".");System.out.print(".");System.out.print(".");System.out.print(".");System.out.print(".");System.out.print(".");System.out.print(".");System.out.print(".");System.out.print(".");
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
						boolean isCityIdExists = userDetails.getCityID();
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
									
									PaymentDetails newPaymentMethod = new PaymentDetails();
				
									String socialSec = scannerManager.getStringValueFromUser("Socialsec :");
									newPaymentMethod.setSocialsec(socialSec);
									String ownerFirstName = scannerManager.getStringValueFromUser("firstName : ");
									newPaymentMethod.setFirstName(ownerFirstName);
									String ownerLastName = scannerManager.getStringValueFromUser("lastName : ");
									newPaymentMethod.setLastName(ownerLastName);
									String ccType = scannerManager.getStringValueFromUser(" cc Type: \nvisa Type 1>,\nIsracard Type 2>");
									newPaymentMethod.setCcType(ccType);
									String cardNumber = scannerManager.getStringValueFromUser("cardNumber:");
									newPaymentMethod.setccNumber(cardNumber);
									String expDate = scannerManager.getStringValueFromUser("expirationDate : ");
									newPaymentMethod.setExpirationDate(expDate);
									newPaymentMethod.setUserId(userDetails.getUserId());
									newPaymentMethod.savePayment();
								
							}else{
									gameZone();
							}
						}else{
							gameZone();
						}
				}
			}
		}
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
		User.setUserBalance(balance);
		User.saveUser();
	
	}

}
