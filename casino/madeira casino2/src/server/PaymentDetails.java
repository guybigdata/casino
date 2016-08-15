package server;
import Accessors.PaymentDetailsAccessor;


public class PaymentDetails
{
	private String socialSec;
	private String firstName;
	private String lastName;
	private String ccType;
	private String cardNumber ;
	private String expirationDate;
	private int userId ;
		
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getCcType() {
		return ccType;
	}

	public void setCcType(String ccType) {
		this.ccType = ccType;
	}


	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSocialsec() {
		return socialSec;
	}

	public void setSocialsec(String socialsec) {
		this.socialSec = socialsec;
	}

	public void setccNumber(String cardnumber){
		
		if (cardnumber.length()< 17)
		{
			this.cardNumber = cardnumber;
		}
		else
		{
			System.out.println(" Wrong number of char, please enter 8 numbers: ");
		}		
	}
	
	public String getccNumber(){
		return this.cardNumber;
	}
	
		
	public void savePayment(){
		PaymentDetailsAccessor newPaymentMethodAccessor = new PaymentDetailsAccessor();
		newPaymentMethodAccessor.savePayment(this);
		
	}
	
}	
