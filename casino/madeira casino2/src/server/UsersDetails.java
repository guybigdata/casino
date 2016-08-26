package server;

import java.time.LocalDate;
//import java.util.Calendar;

import Accessors.UsersDetailsAccessor;

public class  UsersDetails
{
	private int userId;
	private String firstName;
	private String lastName;
	private String gender;
	private String phoneNumber;
	private String street;
	private String city;
	private String country;
	private String email;
	private LocalDate birthDate ;
	private int cityID;
	


	public void setCityId(int cityID) {
		this.cityID = cityID;
	}
	
	public int getCityId(){
		return cityID;
	}
	public int getUserId() {
		return userId;
	}
	public void setuserId(int userId) {
		this.userId = userId;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street){
		this.street = street;
	}

	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void save(){
		UsersDetailsAccessor saveToDb = new UsersDetailsAccessor();
		saveToDb.saveDetails(this);
	}
	
	  public boolean getCountryID(){
		  UsersDetailsAccessor Accessor = new UsersDetailsAccessor();
		  String countryCode = Accessor.getCountryId(this);
		  if (countryCode != null){
			  setCountry(countryCode);
			  return true;
		  }else{
			  return false;
		  }
		  
	  }
	  
	  public boolean getCityIdAsFK(){
		  UsersDetailsAccessor Accessor = new UsersDetailsAccessor();
		  int cityID = Accessor.getCityIdFromDb(this);
		  if (cityID != 0){
			  setCityId(cityID);
			  return true;
		  }
		  else{
			  return false;
		  }
		  
	  }
}