package views;

import utils.Utils;
import java.io.IOException;
public class Main {
	public static void main(String args[]) throws IOException, ClassNotFoundException {
		int options;
		boolean cont = true;
		boolean isLoggedIn = false;
		UserView userView = new UserView();
		userView.init();

		do {
			System.out.print("\033[H\033[2J");
			System.out.flush();
			System.out.println(
				"1. Create User Account\n" +
				"2. Login\n" +
				"3. Check exist user\n" +
				"4. Search user infomation by name\n" + 
				"5. Update user\n" +
				"6. Delete user\n" +
				"7. Save account to file\n" + 
				"8. Print list user from file\n" +
				"9. exit\n"
			);

			options = Utils.getInt("Enter option: ", 1, 9);

			switch(options) {
				case 1:
					if(userView.create()) {
						System.out.println("Successfully created user");
					} else {
						System.out.println("Failed!!!");
					}

					Utils.promptEnterKey();
					break;

				case 2:
					if(userView.login()) {
						System.out.println("Login successfully!");
						isLoggedIn = true;
					} else {
						System.out.println("Wrong username or password");
					};

					Utils.promptEnterKey();
					break;
					
				case 3:
					if(userView.isUserExist()) {
						System.out.println("Exist User");
					} else {
						System.out.println("No User Found!");
					}
					
					Utils.promptEnterKey();
					break;
				case 4:
					if(!userView.searchByName()) {
						System.out.println("Have no any user");
					}

					Utils.promptEnterKey();
					break;
				case 5:
					if(userView.updateUser(isLoggedIn)) {
						System.out.println("Success");
					} else {
						System.out.println("Fail to update user");
					}

					Utils.promptEnterKey();
					break;
				case 6:
					if(userView.deleteUser(isLoggedIn)) {
						System.out.println("Successfully deleted a user");
					} else {
						System.out.println("Fail to delete user");
					}

					Utils.promptEnterKey();
					break;
				case 7:
					if(userView.saveData()) {
						System.out.println("Successfully saved data");
					} else {
						System.out.println("Failed!!!");
					}

					Utils.promptEnterKey();
					break;
				case 8:
					userView.getAllUser(); 

					Utils.promptEnterKey();
					break;
				case 9:
					if(Utils.confirmYesNo("Are you sure? ")) {
						cont = false;
					}
					break;
			}
		} while(true && cont);
	}
}
