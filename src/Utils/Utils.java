/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Pattern;




/**
 *
 * @author Admin
 */
public class Utils {
	//private static final String USERNAME = "/^\\S{5,}$/";
	private static final String USERNAME = "^\\S{5,}$";
	private static final String FIRSTNAME = "^.{0,25}$";
	private static final String LASTNAME = "^.{0,25}$";
	private static final String PASSWORD = "^\\S{6,}$";
	private static final String PHONE = "^(?:\\+84|0)(3[2-9]|5[2689]|7[0-9]|8[1-9]|9[0-9])\\d{7}$";
	private static final String EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

	public static boolean writeListObjectToFile(String path, List<Object> list) {
		boolean result = false;
		try {
			FileOutputStream file = new FileOutputStream(path);
			ObjectOutputStream oos = new ObjectOutputStream(file);
			try {

				for (Object sm : list) {
					oos.writeObject(sm);
				}
				result = true;
			} catch (IOException e) {
			} finally {
				if (oos != null) {
					oos.close();
				}
			}
			if (file != null) {
				file.close();
			}
		} catch (IOException e) {
		}

		return result;
	}

	public static ArrayList<Object> readListOjectFromFile(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		ObjectInputStream ois = new ObjectInputStream(fis);
		ArrayList<Object> list = new ArrayList();
		try {
			Object obj = null;
			while (fis.available() > 0) {
				obj = (Object) ois.readObject();
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ois != null) {
				ois.close();
			}
			if (fis != null) {
				fis.close();
			}
		}
		return list;
	}
	public static String sha256(String input) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));

			StringBuilder hexString = new StringBuilder();
			for (byte b : hashBytes) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}

			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("SHA-256 algorithm not found", e);
		}
	}

	public static String generateUUID() {
		return UUID.randomUUID().toString();
	}

	public static boolean isValid(String arg, String cases) {
		boolean result = false;
		switch (cases) {
			case "username":
				arg = arg.toUpperCase();
				result = arg.matches(Utils.USERNAME);
				break;
			case "firstName":
				result = arg.matches(Utils.FIRSTNAME);
				break;
			case "lastName":
				result = arg.matches(Utils.LASTNAME);
				break;
			case "password":
				//result = arg.matches(Utils.PASSWORD);
				result = Pattern.matches(PASSWORD, arg);
				break;
			case "phone":
				result = arg.matches(Utils.PHONE);
				break;
			case "email":
				result = arg.matches(Utils.EMAIL);
				break;
			default:
				throw new AssertionError();
		}
		return result;
	}

	public static String checkValidString(String welcome, String cases) {
		String arg = "";
		do {
			arg = getString(welcome);
			if (Utils.isValid(arg, cases)) {
				break;
			} else {
				System.out.println("Invalid value for " + cases);
			}
		} while (true);
		return arg;
	}

	public static boolean confirmPassword(String welcome, String password) {
		boolean confirm = false;

		do {
			String confirmPassword = getString("Confirm password: ");

			if (password.equals(confirmPassword)) {
				confirm = true;
				break;
			} else {
				System.out.println("Wrong confirm password!");
			}
		} while(true);

		return confirm;
	}

	public static String getString(String welcome) {
		boolean check = true;
		String result = "";
		do {
			Scanner sc = new Scanner(System.in);
			System.out.print(welcome);
			result = sc.nextLine();
			if (result.isEmpty()) {
				System.out.println("Input text!!!");
			} else {
				check = false;
			}
		} while (check);
		return result;
	}

	public static boolean confirmYesNo(String welcome) {
		boolean result = false;
		String confirm = Utils.getString(welcome);
		if ("Y".equalsIgnoreCase(confirm)) {
			result = true;
		}
		return result;
	}

	public static int getInt(String welcome, int min, int max) {
		boolean check = true;
		int number = 0;
		do {
			try {
				Scanner sc = new Scanner(System.in);
				System.out.print(welcome);
				number = Integer.parseInt(sc.nextLine());
				check = false;
			} catch (NumberFormatException e) {
				System.out.println("Input number!!!");
			}
		} while (check || number > max || number < min);
		return number;
	}

}
