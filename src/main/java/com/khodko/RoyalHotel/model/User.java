package com.khodko.RoyalHotel.model;


public class User extends IdEntity {
		
	private String username;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private String country;
	private String cardType;
	private String cardNumber;
	
	private String role;	
		
	public User() {
		super();
	}
	
	public User(Integer id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public User(String username, String password, String email, String firstName,
				String lastName, String country, String cardType, String cardNumber) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
		this.cardType = cardType;
		this.cardNumber = cardNumber;
	}
	
	public User(Integer id, String username, String password, String email, String firstName,
			String lastName, String country, String cardType, String cardNumber) {
		this(username, password, email, firstName, lastName, country, cardType, cardNumber);
		this.id = id;		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
		
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
			
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" 
				+ firstName + ", lastName=" + lastName + ", country=" + country + ", cardType="
				+ cardType + ", cardNumber=" + cardNumber + ", role=" + role +"]";
	}
       	
}
