package server;

import Accessors.TransactionAccessor;


public class TransactionHistory {
	
	public String[] TransactionTypeList = new String[]{"Payment","Cashout", "Gift"};
	public String[] actionTypeList = new String[]{"PurchaseChips","ReturnChips"};
	public int[] chipsQuantityList = new int[]{10,20,30,40,50,100,500,1000};
	
	TransactionAccessor TransactionAccessor = new TransactionAccessor();
	
	private String TransactionType;
	private String actionType;
	private int chipsQuantity;
	private int transactionAmount;
	private int UserID ;

	public int getChipsQuantity() {
		return chipsQuantity;
	}
	public void setChipsQuantity(int chipsQuantity) {
		this.chipsQuantity = chipsQuantity;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getTransactionType() {
		return TransactionType;
	}
	public void setTransactionType(String transactionType) {
		TransactionType = transactionType;
	}



	
	public int getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(int transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	

	public void saveTransaction()
	{
		
		TransactionAccessor.saveTransaction(this);
	}	
	
	public boolean ifPaymentDetailsExists() {

		int IdExists = TransactionAccessor.searchPaymentDetails(this);
		if (IdExists >= 1){
			return true;
		}else{
			return false;
		}
	}
	public void savePurchase(){
		TransactionAccessor.saveTransaction(this);
		
	}
	

}
