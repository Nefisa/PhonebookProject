package phonebook.ui;

import java.sql.SQLException;
import java.util.Scanner;

import phonebook.beans.User;
import phonebook.tables.UserManager;

public class UserUI {
	
	static Scanner input = new Scanner(System.in);
	
	public static void userLogin() throws SQLException {
		
		System.out.println("Please enter your user ID: ");
		int userID = input.nextInt();
		
		System.out.println("Please enter your password: ");
		String password = input.next();
		
		if ( UserManager.checkLogin(userID,password)) {
			System.out.println("Login successfull.");
			PhonebookUI pb = new PhonebookUI();
			User user = new User(userID, password);
			pb.menu(user);
		}
		else
			System.out.println("Something went wrong. Try again.");
		
	}
	
	public static void createUser() throws SQLException {
		
		System.out.println("Please enter your first name: ");
		String firstName = input.next();
		
		System.out.println("Please enter your last name: ");
		String lastName = input.next();
		
		System.out.println("Please enter your password(minimum length 8 charachters): ");
		String password = input.next(); 
		if ( password.length() < 8) {
			System.out.println("Password has to have at least 8 charachters!. Try again: ");
			password = input.next();
		}
		
		User user = UserManager.createUser(firstName, lastName, password);
		System.out.println(user.toString() + " successfully created!");
		
		PhonebookUI pb = new PhonebookUI();
		pb.menu(user);
	
	}

}
