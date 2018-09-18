import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class ProgramController {
	static DBCollection cards;
	static Scanner sc;

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		MongoClient mongoClient = new MongoClient();
		DB database = mongoClient.getDB("contacts");
		cards = database.getCollection("cards");
		InteractWithUser();
		mongoClient.close();
		System.out.println("Goodbye");
	}

	private static void InteractWithUser() {
		boolean exit = false;
		String initialMessage = "Please enter number of option:\n" + "1: View contact info\n" + "2: Add contact info\n"
				+ "3: Update contact info\n" + "4: Delete contact info\n" + "5: Exit";

		do {
			System.out.println(initialMessage);
			String choice = sc.nextLine();
			switch (choice) {
			case "1":
				view();
				break;
			case "2":
				add();
				break;
			case "3":
				update();
				break;
			case "4":
				delete();
				break;
			case "5":
				sc.close();
				exit = true;
				break;
			default:
				System.out.println("Invalid choice entered");
				break;
			}

		} while (exit);

	}

	private static void view() {
		DB database;
		DBObject query = new BasicDBObject();
		boolean exit = false;
		String viewMessage = "View: Please enter the number of what you would like to search by:\n" + "1: First name\n"
				+ "2: Last name\n3: Company\n4: Phone number\n5: Exit";
		do {
			System.out.println(viewMessage);
			String choice = sc.nextLine();
			switch (choice) {
			case "1": {
				System.out.println("Please enter the first name of the contact");
				String name = sc.nextLine();
				query.put("first_name", name);
				try {
					DBObject result = cards.findOne(query);
					System.out.print("First Name: " + result.get("first_name"));
					System.out.print(", Last Name: " + result.get("last_name"));
					System.out.print(", Company: " + result.get("company"));
					System.out.println(", Phone number: " + result.get("number"));
					InteractWithUser();
					
				} catch (NullPointerException e) {
					System.out.println("No result found");
					view();
				}
			}
				break;
			case "2":
				System.out.println("Please enter the last name of the contact");
				String name = sc.nextLine();
				query.put("last_name", name);
				try {
					DBObject result = cards.findOne(query);
					System.out.print("First Name: " + result.get("first_name"));
					System.out.print(", Last Name: " + result.get("last_name"));
					System.out.print(", Company: " + result.get("company"));
					System.out.println(", Phone number: " + result.get("number"));
					InteractWithUser();
					
				} catch (NullPointerException e) {
					System.out.println("No result found");
					view();
				}
				break;
			case "3":
				System.out.println("Please enter the company of the contact");
				String company = sc.nextLine();
				query.put("company", company);
				try {
					DBObject result = cards.findOne(query);
					System.out.print("First Name: " + result.get("first_name"));
					System.out.print(", Last Name: " + result.get("last_name"));
					System.out.print(", Company: " + result.get("company"));
					System.out.println(", Phone number: " + result.get("number"));
					InteractWithUser();
					
				} catch (NullPointerException e) {
					System.out.println("No result found");
					view();
				}
				break;
			case "4":
				System.out.println("Please enter the phone number of the contact");
				String number = sc.nextLine();
				query.put("number", number);
				try {
					DBObject result = cards.findOne(query);
					System.out.print("First Name: " + result.get("first_name"));
					System.out.print(", Last Name: " + result.get("last_name"));
					System.out.print(", Company: " + result.get("company"));
					System.out.println(", Phone number: " + result.get("number"));
					InteractWithUser();
					
				} catch (NullPointerException e) {
					System.out.println("No result found");
					view();
				}
				break;
			case "5":
				exit = true;
				break;
			default:
				System.out.println("Invalid choice entered");
				break;
			}
		} while (!exit);
	}

	private static void add() {
		// DECIDE IF WHILE(TRUE) OR CALL METHOD AGAIN
		boolean exit = false;

		System.out.println("Add:");
		System.out.println("Please enter the contact's first name.");
		String fName = sc.nextLine();
		System.out.println("Please enter the contact's last name");
		String lName = sc.nextLine();
		System.out.println("Please enter the contact's company");
		String company = sc.nextLine();
		System.out.println("Please enter the contact's phone number, press enter if none");
		String number = sc.nextLine();
		System.out.println("Prepare to take photo of business card. Press enter when ready");
		sc.nextLine();
		// BufferedImage businessCard = TakePhoto();
		System.out.println("Buffered image taken");
		System.out.println("add called");
		InteractWithUser();
		// Call DBController add method
	}

	private static void update() {
		//ADD NUMBER TO ALL
		boolean exit = false;
		String viewMessage = "Update: Please enter the number of what you would like to search by:\n"
				+ "1: First name\n" + "2: Last name\n" + "3: Company\n" + "4: Exit";

		do {
			System.out.println(viewMessage);
			String choice = sc.nextLine();
			switch (choice) {
			case "1":
				System.out.println("first name chosen");
				break;
			case "2":
				System.out.println("second name chosen");
				break;
			case "3":
				System.out.println("company chosen");
				break;
			case "4":
				sc.close();
				return;
			default:
				System.out.println("Invalid choice entered");
				break;
			}

			System.out.println("Please enter search term");
			String term = sc.nextLine();

			// Iterate through options matching search

			// Ask what field they would like to update, iterate through available options

			System.out.println("What would you like to replace the field with?");
			String replacement = sc.nextLine();

			// Update field

			System.out.println("Update completed");
		} while (true);
	}

	private static void delete() {
		boolean exit = false;
		String viewMessage = "Delete: Please enter the number of what you would like to search by:\n"
				+ "1: First name\n" + "2: Last name\n" + "3: Company\n" + "4: Exit";

		do {
			System.out.println(viewMessage);
			String choice = sc.nextLine();
			switch (choice) {
			case "1":
				System.out.println("First name chosen");
				break;
			case "2":
				System.out.println("Second name chosen");
				break;
			case "3":
				System.out.println("Company chosen");
				break;
			case "4":
				sc.close();
				exit = true;
				break;
			default:
				System.out.println("Invalid choice entered");
				break;
			}

			System.out.println("Please enter search term");
			String term = sc.nextLine();

			// List results asking which to delete

			// Call DBController delete method

			System.out.println("Contact deleted");

		} while (exit);
		InteractWithUser();
	}

	private static BufferedImage takePhoto() {

		return null;
	}

	// implement a iterate through results method

//	private static void tester() {
//		Scanner sc = new Scanner(System.in);
//		System.out.println("1: View \n2: Add");
//		String choice = sc.nextLine();
//		switch(choice) {
//		case "1":
//			break;
//		case "2":
//			break;
//		default: System.out.println("Didn't work");
//		tester();
//		break;
//		}
//	}
}
