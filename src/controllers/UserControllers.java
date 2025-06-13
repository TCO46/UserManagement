package controllers;

import utils.Utils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
	public boolean update(User user, String username, String firstName, String lastName, String password, String phoneNumber, String email) {
		String encryptedPassword = Utils.sha256(password);

		user.setUsername(username);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(encryptedPassword);
		user.setPhoneNumber(phoneNumber);
		user.setEmail(email);

		return true;
	}

	@Override
	public boolean delete(String username) {
		List<Object> users = getAllUserExept(username);

		if(searchByName(username).isEmpty()) {
			return false;
		}

		Utils.writeListObjectToFile("User-tmp.dat", users);
		File oldFile = new File("User.dat");
		oldFile.delete();
		boolean newFile = new File("User-tmp.dat").renameTo(oldFile);

		try {
			readUser();
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(UserControllers.class.getName()).log(Level.SEVERE, null, ex);
		}

		return newFile;

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

	@Override
	public User getUser(String username) {
		User user = null;
		for(User i : this) {
			if(i.getUsername().equals(username)) {
				user = i;
				break;
			}
		}

		return user;
	}

	@Override
	public List<User> getAllUser() {
		List<User> users = new ArrayList<>();

		for(User i : this) {
			users.add(i);
		}

		return users;
	}

	public List<Object> getAllUserExept(String username) {
		List<Object> users = new ArrayList<>();

		for(User i : this) {
			if(i.getUsername().equals(username)) {
				continue;
			}

			users.add(i);
		}

		return users;
	}

	public boolean writeDataToFile(String path) {
		List<Object> list = new ArrayList<>();
		for (User i : this) {
			list.add((Object) i);
		}
		Utils.writeListObjectToFile(path, list);
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

	@Override
	public boolean authentication(String username, String password) {
		User user = getUser(username);

		if(user == null) {
			return false;
		}

		if(!user.getPassword().equals(Utils.sha256(password))) {
			return false;
		}

		return true;
	}
}
