package models;

import java.io.Serializable;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Sigma 
 */
public class User implements Serializable {
	private static final long serialVersionUID = 5305293927933091843L;
	private String id;
	private String username;
	private String firstName;
	private String lastName;
	private String password;
	private String phoneNumber;
	private String email;

	public User() {}

	public User(String id, String username, String firstName, String lastName, String password, String phoneNumber, String email) {
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPassword() {
		return password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String display() {
		return String.format("%-30s | %-20s | %-20s | %-20s | %-12s | %-20s", this.getId(), this.getUsername(), this.getFirstName(), this.getLastName(), this.getPhoneNumber(), this.getEmail());
	}	
}
