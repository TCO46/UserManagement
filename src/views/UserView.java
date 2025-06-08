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

	private final User user = new User();
	private static final UserControllers userControllers = new UserControllers();

	public void init() throws ClassNotFoundException {
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
	public boolean isUserExist() {
		String username = Utils.getString("Enter username: ");
		List<User> _user = userControllers.searchByName(username);

		return !_user.isEmpty();
	}

	public boolean searchByName() {
		String name = Utils.getString("Enter User name: ");
		List<User> _user = userControllers.searchByName(name);

		if(_user.isEmpty()) {
			return false;
		} else {
			System.out.println("Matching User: " + name);
			for (int i = 0; i < 100; i++) {
				System.out.print("-");
			}
			System.out.println();
			System.out.println("id                                   | Username              | Phone        | Customer email     ");
			for (int i = 0; i < 100; i++) {
				System.out.print("-");
			}
			System.out.println();
			for (User i : _user) {
				System.out.println(i.display());
			}
			for (int i = 0; i < 100; i++) {
				System.out.print("-");
			}
			System.out.println();
			return true;
		}
	}
}
