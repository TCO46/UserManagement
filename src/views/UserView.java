/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import Utils.Utils;
import controllers.UserControllers;
import models.User;

/**
 *
 * @author Admin
 */
public class UserView {

	private User user = new User();
	private static UserControllers userControllers = new UserControllers();

	public void init() {
		userControllers.readUser();
	}

	public boolean create() {
		String username = Utils.checkValidString("Enter username: ", "username");
		String firstName = Utils.checkValidString("Enter first name: ", "firstName");
		String lastName = Utils.checkValidString("Enter last name: ", "lastName");
		String password = Utils.checkValidString("Enter password: ", "password");
		boolean confirmPassword = Utils.confirmPassword("Confirm password : ", password);
		String phone = Utils.checkValidString("Enter phone number: ", "phone");
		String email = Utils.checkValidString("Enter email: ", "email");

		if(confirmPassword) {
			return userControllers.create(username, firstName, lastName, password, phone, email);
		}

		return false;
	}
}
