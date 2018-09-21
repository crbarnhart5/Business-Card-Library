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
		interactWithUser();
		mongoClient.close();
		sc.close();
		System.out.println("Goodbye");
	}

	private static void interactWithUser() {
		String initialMessage = "Please enter number of option:\n" + "1: View contact info\n" + "2: Add contact info\n"
				+ "3: Update contact info\n" + "4: Delete contact info\n" + "5: Exit";

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
				break;
			default:
				System.out.println("Invalid choice entered");
				interactWithUser();
				break;
			}

	}

	private static void view() {
		DBObject query = new BasicDBObject();
		boolean exit = false;
		String viewMessage = "View: Please enter the number of what you would like to search by:\n" + "1: First name\n"
				+ "2: Last name\n3: Company\n4: Phone number\n5: Exit";
		do {
			System.out.println(viewMessage);
			String choice = sc.nextLine();
			switch (choice) {
			case "1":
				System.out.println("Please enter the first name of the contact");
				String name = sc.nextLine();
				query.put("first_name", name);
				try {
					DBObject result = cards.findOne(query);
					System.out.print("\nFirst Name: " + result.get("first_name"));
					System.out.print(", Last Name: " + result.get("last_name"));
					System.out.print(", Company: " + result.get("company"));
					System.out.println(", Phone number: " + result.get("number"));
					exit = true;

				} catch (NullPointerException e) {
					System.out.println("No result found");
					view();
				}

				break;
			case "2":
				System.out.println("Please enter the last name of the contact");
				name = sc.nextLine();
				query.put("last_name", name);
				try {
					DBObject result = cards.findOne(query);
					System.out.print("\nFirst Name: " + result.get("first_name"));
					System.out.print(", Last Name: " + result.get("last_name"));
					System.out.print(", Company: " + result.get("company"));
					System.out.println(", Phone number: " + result.get("number"));
					exit = true;

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
					System.out.print("\nFirst Name: " + result.get("first_name"));
					System.out.print(", Last Name: " + result.get("last_name"));
					System.out.print(", Company: " + result.get("company"));
					System.out.println(", Phone number: " + result.get("number"));
					exit = true;

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
					System.out.print("\nFirst Name: " + result.get("first_name"));
					System.out.print(", Last Name: " + result.get("last_name"));
					System.out.print(", Company: " + result.get("company"));
					System.out.println(", Phone number: " + result.get("number"));
					exit = true;

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
		interactWithUser();
	}

	private static void add() {
		DBObject query = new BasicDBObject();

		System.out.println("Add:");
		System.out.println("Please enter the contact's first name.");
		String fName = sc.nextLine();
		System.out.println("Please enter the contact's last name");
		String lName = sc.nextLine();
		System.out.println("Please enter the contact's company");
		String company = sc.nextLine();
		System.out.println("Please enter the contact's phone number, press enter if none");
		String number = sc.nextLine();

		query.put("first_name", fName);
		query.put("last_name", lName);
		query.put("company", company);
		if (number != null && number.length() > 0) {
			query.put("number", number);
		}
		cards.insert(query);
		interactWithUser();
	}

	private static void update() {
		DBObject query = new BasicDBObject();
		boolean exit = false;
		String viewMessage = "View: Please enter the number of what you would like to search by:\n" + "1: First name\n"
				+ "2: Last name\n3: Company\n4: Phone number\n5: Exit";
		do {
			System.out.println(viewMessage);
			String choice = sc.nextLine();
			switch (choice) {
			case "1":
				System.out.println("Please enter the first name of the contact");
				String name = sc.nextLine();
				query.put("first_name", name);
				try {
					DBObject result = cards.findOne(query);
					System.out.print("\nFirst Name: " + result.get("first_name"));
					System.out.print(", Last Name: " + result.get("last_name"));
					System.out.print(", Company: " + result.get("company"));
					System.out.println(", Phone number: " + result.get("number"));
					updateHelper(query);
					exit = true;

				} catch (NullPointerException e) {
					System.out.println("No result found");
					view();

				}
				break;
			case "2":
				System.out.println("Please enter the last name of the contact");
				name = sc.nextLine();
				query.put("last_name", name);
				try {
					DBObject result = cards.findOne(query);
					System.out.print("\nFirst Name: " + result.get("first_name"));
					System.out.print(", Last Name: " + result.get("last_name"));
					System.out.print(", Company: " + result.get("company"));
					System.out.println(", Phone number: " + result.get("number"));
					updateHelper(query);
					exit = true;

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
					System.out.print("\nFirst Name: " + result.get("first_name"));
					System.out.print(", Last Name: " + result.get("last_name"));
					System.out.print(", Company: " + result.get("company"));
					System.out.println(", Phone number: " + result.get("number"));
					updateHelper(query);
					exit = true;

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
					System.out.print("\nFirst Name: " + result.get("first_name"));
					System.out.print(", Last Name: " + result.get("last_name"));
					System.out.print(", Company: " + result.get("company"));
					System.out.println(", Phone number: " + result.get("number"));
					updateHelper(query);
					exit = true;

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
		interactWithUser();
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
				deleteHelperFName();
				break;
			case "2":
				deleteHelperLName();
				break;
			case "3":
				deleteHelperCompany();
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
		interactWithUser();
	}

	private static BufferedImage takePhoto() {

		return null;
	}

	private static void updateHelper(DBObject query) {
		boolean exit = false;
		BasicDBObject updateDoc = new BasicDBObject();
		String viewMessage = "Please enter which field you would like to update:\n1: First name\n"
				+ "2: Last name\n3: Company\n4: Phone number\n5: Exit";

		System.out.println(viewMessage);
		String choice = sc.nextLine();
		do {
			switch (choice) {
			case "1":
				System.out.println("Please enter new first name");
				String name = sc.nextLine();
				updateDoc.append("$set", new BasicDBObject().append("first_name", name));
				cards.update(query, updateDoc);
				exit = true;
				break;
			case "2":
				System.out.println("Please enter new last name");
				name = sc.nextLine();
				updateDoc.append("$set", new BasicDBObject().append("last_name", name));
				cards.update(query, updateDoc);
				exit = true;
				break;
			case "3":
				System.out.println("Please enter new company name");
				String company = sc.nextLine();
				updateDoc.append("$set", new BasicDBObject().append("company", company));
				cards.update(query, updateDoc);
				exit = true;
				break;
			case "4":
				System.out.println("Please enter new company name");
				String number = sc.nextLine();
				updateDoc.append("$set", new BasicDBObject().append("number", number));
				cards.update(query, updateDoc);
				exit = true;
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
	// implement a iterate through results method
	public static void deleteHelperFName(){
		DBObject query = new BasicDBObject();
		System.out.println("What is the first name");
		String name = sc.nextLine();
		query.put("first_name", name);
		cards.remove(query);
	}
	
	public static void deleteHelperLName() {
		DBObject query = new BasicDBObject();
		System.out.println("What is the last name");
		String name = sc.nextLine();
		query.put("last_name", name);
		cards.remove(query);
	}
	
	public static void deleteHelperCompany() {
		DBObject query = new BasicDBObject();
		System.out.println("What is the last name");
		String company = sc.nextLine();
		query.put("company", company);
		cards.remove(query);
	}

}
