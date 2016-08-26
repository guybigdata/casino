package server;

import Accessors.UsersAccessor;

public class Users {
	
	private int userBalance;
	
	public int getUserBalance() {
		return userBalance;
	}

	public void setUserBalance(int userBalance) {
		this.userBalance = userBalance;
	}

	private int userId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isUserExist() {
		int[] idBalance;
		
		UsersAccessor searchUser = new UsersAccessor();
		idBalance = searchUser.FindUser(this);
		
		if (idBalance[0] != -1) {
			setUserId(idBalance[0]);
			setUserBalance(idBalance[1]);
			return true;
		} else {
			return false;
		}
	}

	public void saveUser() {
		UsersAccessor addNewUser = new UsersAccessor();
		addNewUser.save(this);

	}
	
	public void updateUserBalance(){
		UsersAccessor saveBalace = new UsersAccessor();
		saveBalace.saveBalance(this);
	}

}
