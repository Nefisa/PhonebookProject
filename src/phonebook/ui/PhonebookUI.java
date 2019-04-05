package phonebook.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import phonebook.beans.PersonPB;
import phonebook.beans.User;
import phonebook.tables.PhonebookManager;

public class PhonebookUI {

	Scanner input = new Scanner(System.in);

	public void menu(User user) throws SQLException, InputMismatchException {

		System.out.println("Welcome to phonebook menu:");
		System.out.println("0. Add person to your phonebook");
		System.out.println("1. update first name");
		System.out.println("2. update last name");
		System.out.println("3. update phone number");
		System.out.println("4. update mobile number");
		System.out.println("5. search by first name");
		System.out.println("6. search by last name");
		System.out.println("7. delete person");
		System.out.println("8. display all from your phonebook");
		System.out.println("9. exit");
		
		try {
			int choice = input.nextInt();

			while (choice < 0 && choice > 9) {
				System.out.println("Wrong input. Try again:");
				choice = input.nextInt();
			}

			switch (choice) {
			case 0:
				addPerson(user);
				break;
			case 1:
				editPersonsFirstName(user);
				break;
			case 2:
				editPersonsLastName(user);
				break;
			case 3:
				editPersonsPhoneNumber(user);
				break;
			case 4:
				editPersonsMobileNumber(user);
				break;
			case 5:
				searchByFirstName(user);
				break;
			case 6:
				searchByLastName(user);
				break;
			case 7:
				deletePerson(user);
				break;
			case 8:
				enlistPhonebook(user);
				break;
			case 9:
				break;
			}
		} catch (InputMismatchException e) {
			input.nextLine();
			System.out.println("Invalid input.");
			menu(user);
		}

	}

	public void addPerson(User user) throws SQLException {

		System.out.println("Insert person's first name: ");
		String firstName = input.next();

		System.out.println("Insert person's last name: ");
		String lastName = input.next();

		System.out.println("Insert person's phone number in format: ");
		String phone = input.next();

		System.out.println("Insert person's mobile phone number: ");
		String mobile = input.next();

		PersonPB person = PhonebookManager.insertPerson(firstName, lastName, phone, mobile, user);
		System.out.println(person.toString() + " added successfully!");
		
		menu(user);
	}

	public void deletePerson(User user) throws InputMismatchException, SQLException {

		System.out.println("Enter ID of a person you wish to delete: ");
		int personID = input.nextInt();

		if (PhonebookManager.deletePerson(personID, user))
			System.out.println("Deleted person with ID: " + personID + " successfully!");
		else
			System.out.println("Something went wrong. Try again!");
		
		menu(user);
	}

	public void enlistPhonebook(User user) throws SQLException {

		PhonebookManager.displayAllRows(user);
		
		menu(user);

	}

	public void editPersonsFirstName(User user) throws SQLException {

		System.out.println("Input person's ID: ");
		int personID = input.nextInt();

		System.out.println("Insert new name for person with ID " + personID + " :");
		String newName = input.next();

		if (PhonebookManager.updateFirstName(personID, newName, user))
			System.out.println("Update successfull!");
		else
			System.out.println("Something went wrong! Try again.");
		
		menu(user);
	}

	public void editPersonsLastName(User user) throws SQLException {

		System.out.println("Input person's ID: ");
		int personID = input.nextInt();

		System.out.println("Insert new last name for person with ID " + personID + " :");
		String newLastName = input.next();

		if (PhonebookManager.updateLastName(personID, newLastName, user))
			System.out.println("Update successfull!");
		else
			System.out.println("Something went wrong! Try again.");
		
		menu(user);
	}

	public void editPersonsPhoneNumber(User user) throws SQLException {

		System.out.println("Input person's ID: ");
		int personID = input.nextInt();

		System.out.println("Insert new phone number for person with ID " + personID + " :");
		String newPhoneNumber = input.next();

		if (PhonebookManager.updatePhoneNumber(personID, newPhoneNumber, user))
			System.out.println("Update successfull!");
		else
			System.out.println("Something went wrong! Try again.");
		
		menu(user);
	}

	public void editPersonsMobileNumber(User user) throws SQLException {

		System.out.println("Input person's ID: ");
		int personID = input.nextInt();

		System.out.println("Insert new mobile phone number for person with ID " + personID + " :");
		String newMobileNumber = input.next();

		if (PhonebookManager.updateMobileNumber(personID, newMobileNumber, user))
			System.out.println("Update successfull!");
		else
			System.out.println("Something went wrong! Try again.");
		
		menu(user);
	}

	public void searchByFirstName(User user) throws SQLException {

		System.out.println("Enter the first name you want to search: ");
		String firstName = input.next();

		ArrayList<String> list = PhonebookManager.searchByFirstName(firstName, user);

		if (list.size() == 0)
			System.out.println("There is no person with " + firstName + " first name in phonebook.");
		else {
			System.out.println("Persons found: " + list.size() + " and they are: ");
			for (String s : list)
				System.out.println(s);
		}
		
		menu(user);
	}

	public void searchByLastName(User user) throws SQLException {

		System.out.println("Enter the last name you want to search: ");
		String lastName = input.next();

		ArrayList<String> list = PhonebookManager.searchByLastName(lastName, user);

		if (list.size() == 0)
			System.out.println("There is no person with " + lastName + " as last name in phonebook.");
		else {
			System.out.println("Persons found: " + list.size() + " and they are: ");
			for (String s : list)
				System.out.println(s);
		}
		
		menu(user);
	}
}
