package views;
import Utils.Utils;
import java.io.IOException;
public class Main {
	public static void main(String args[]) throws IOException, ClassNotFoundException {
		int options;
		boolean cont = true;
		UserView userView = new UserView();
		userView.init();

		do {
			System.out.println(
				"1. Create User Account\n" +
				"2. Check exist user\n" +
				"3. Search user infomation by name\n" + 
				"4. Update user\n" +
				"5. Delete user\n" +
				"6. Save account to file\n" + 
				"7. Print list user from file\n" +
				"8. exit\n"
			);

			options = Utils.getInt("Enter option: ", 1, 8);

			switch(options) {
				case 1:
					if(userView.create()) {
						System.out.println("Successfully created user");
						break;
					} else {
						System.out.println("Failed!!!");
					}
				case 2:
					if(userView.isUserExist()) {
						System.out.println("Exist User");
					} else {
						System.out.println("No User Found!");
					}
					Utils.promptEnterKey();
					break;
				case 3:
					if(!userView.searchByName()) {
						System.out.println("Have no any user");
					}
					Utils.promptEnterKey();
					break;
				case 6:
					if(userView.saveData()) {
						System.out.println("Successfully saved data");
					} else {
						System.out.println("Failed!!!");
					}

					Utils.promptEnterKey();
					break;
				case 8:
					if(Utils.confirmYesNo("Are you sure? ")) {
						cont = false;
					}
					break;
			}
		} while(true && cont);
	}
}
