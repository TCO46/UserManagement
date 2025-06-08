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

	@Override
	public boolean create(String username, String firstName, String lastName, String password, String phoneNumber, String email) {
		boolean result = false;
		String id = Utils.generateUUID();
		String encryptedPassword = Utils.sha256(password);

		try {
			User newUser = new User(id, username, firstName, lastName, encryptedPassword, phoneNumber, email);
			if(!searchByName(username).isEmpty()) {
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

	@Override
	public List<User> searchByName(String username) {
		List<User> user = new ArrayList<>();
		for(User i : this) {
			if(i.getUsername().contains(username.toUpperCase()) || i.getUsername().contains(username.toLowerCase())) {
				user.add(i);
			}
		}

		return user;
	}

	public boolean writeDataToFile() {
		List<Object> list = new ArrayList<>();
		for (User i : this) {
			list.add((Object) i);
		}
		Utils.writeListObjectToFile("User.dat", list);
		return true;
	}	

	public void readUser() throws ClassNotFoundException {
		this.clear();
		List<Object> list = new ArrayList<>();
		try {
			list = Utils.readListOjectFromFile("User.dat");
		} catch (IOException ex) {
//			Logger.getLogger(UserControllers.class.getName()).log(Level.SEVERE, null, ex);

		}

		for (Object ob : list) {
			if (ob instanceof User) {
				this.add((User) ob);
			}
		}
	}
}
