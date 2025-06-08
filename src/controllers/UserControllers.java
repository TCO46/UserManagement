package controllers;

import Utils.Utils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.I_User;
import models.User;

public class UserControllers extends ArrayList<User> implements I_User {

	public boolean create(String username, String firstName, String lastName, String password, String phoneNumber, String email) {
		boolean result = false;
		String id = Utils.generateUUID();
		String encryptedPassword = Utils.sha256(password);

		try {
			User newUser = new User(id, username, firstName, lastName, encryptedPassword, phoneNumber, email);
			if(this.contains(newUser)) {
				System.out.println("Duplicate User!!!");
				return false;
			}
			
			this.add(newUser);
			result = true;
		} catch(Exception e) {
			return false;
		}
		return result;
	}

}
