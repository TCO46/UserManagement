/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import Utils.Utils;
import controllers.UserControllers;
import java.util.List;
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
			int line = 150;
			System.out.println("Matching User: " + name);
			for (int i = 0; i < line; i++) {
				System.out.print("-");
			}
			System.out.println();
			System.out.println("id                                   | Username             | First name           | Last name            | Phone        | Customer email     ");
			for (int i = 0; i < line; i++) {
				System.out.print("-");
			}
			System.out.println();
			for (User i : _user) {
				System.out.println(i.display());
			}
			for (int i = 0; i < line; i++) {
				System.out.print("-");
			}
			System.out.println();
			return true;
		}
	}

	public boolean updateUser() {
		String username = Utils.getString("Enter username: ");
		User _user = userControllers.getUser(username);
		if(_user == null) {
			System.out.println("Username does not exist");
			return false;
		}

		String updateUsername = Utils.checkValidUpdateString("Enter username: ", "username", _user.getUsername());
		String updateFirstName = Utils.checkValidUpdateString("Enter first name: ", "firstName", _user.getFirstName());
		String updateLastName = Utils.checkValidUpdateString("Enter last name: ", "lastName", _user.getLastName());
		String updatePassword = Utils.checkValidUpdateString("Enter password: ", "password", _user.getPassword());
		String updatePhoneNumber = Utils.checkValidUpdateString("Enter phone number: ", "phone", _user.getPhoneNumber());
		String updateEmail = Utils.checkValidUpdateString("Enter email: ", "email", _user.getEmail());

		return userControllers.update(_user, updateUsername, updateFirstName, updateLastName, updatePassword, updatePhoneNumber, updateEmail);
	}

	public boolean getAllUser() {
		List<User> users = userControllers.getAllUser();

		if(users.isEmpty()) {
			return false;
		} else {
			int line = 150;
			for (int i = 0; i < line; i++) {
				System.out.print("-");
			}
			System.out.println();
			System.out.println("id                                   | Username             | First name           | Last name            | Phone        | Customer email     ");
			for (int i = 0; i < line; i++) {
				System.out.print("-");
			}
			System.out.println();
			for (User i : users) {
				System.out.println(i.display());
			}
			for (int i = 0; i < line; i++) {
				System.out.print("-");
			}
			System.out.println();
			return true;
		}
	}

	public boolean saveData() {
		return userControllers.writeDataToFile();
	}
}
