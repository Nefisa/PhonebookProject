package phonebook.main;

import java.util.InputMismatchException;
import java.util.Scanner;
import phonebook.ui.UserUI;

public class PhonebookApplication {

	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) throws Exception {

		app();

	}

	public static void app() throws Exception {
		
		System.out.println("Welcome!");
		System.out.println("Today's menu: ");
		System.out.println("0. New User");
		System.out.println("1. Login to your phonebook");
		System.out.println("2. Exit!");
		
		try {
		int choice = input.nextInt();
		
		while (choice != 0 && choice != 1) {
			System.out.println("Wrong input. Try again: ");
			choice = input.nextInt();
		}
		
		switch (choice) {
		case 0: 
			UserUI.createUser();
			break;
		
		case 1: 
			UserUI.userLogin();
			break;
		}
		}
		 catch (InputMismatchException e) {
			input.nextLine();
			System.out.println("Invalid input.");
			app();
			}
		}

}
