
/**
 * ProgramController.java 1.0
 * 
 * @author Connor Barnhart
 * @version 1.0
 *
 *
 */

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;

public class ProgramController {
	static DBCollection cards;
	static Scanner sc;
	static Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

	static {
		root.setLevel(Level.OFF);
	}

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
		String initialMessage = "Please enter number of option:\n1: View contact info\n2: Add contact info\n"
				+ "3: Update contact info\n4: Delete contact info\n5: Exit";
		boolean exit = false;

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
				exit = true;
				break;

			default:
				System.out.println("Invalid choice entered");
				interactWithUser();
				break;
			}
		} while (!exit);

	}

	private static void view() {
		DBObject query = new BasicDBObject();
		DBObject result = new BasicDBObject();
		boolean exit = false;
		String viewMessage = "View: Please enter the number of what you would like to search by:\n1: First name\n"
				+ "2: Last name\n3: Company\n4: Phone number\n5: Exit";
		do {
			System.out.println(viewMessage);
			String choice = sc.nextLine();
			switch (choice) {
			case "1":
				result = findFName();
				if (result != null) {
					printContact(result);
					exit = true;
				} else {
					System.out.println("No contact found");
				}
				break;
			case "2":
				result = findLName();
				if (result != null) {
					printContact(result);
					exit = true;
				} else {
					System.out.println("No contact found");
				}

				break;
			case "3":
				result = findCompany();
				if (result != null) {
					printContact(result);
					exit = true;
				} else {
					System.out.println("No contact found");
				}
				break;
			case "4":
				result = findNumber();
				if (result != null) {
					printContact(result);
					exit = true;
				} else {
					System.out.println("No contact found");
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
	}

	private static void update() {
		DBObject result = new BasicDBObject();
		boolean exit = false;
		String viewMessage = "View: Please enter the number of what you would like to search by:\n1: First name\n"
				+ "2: Last name\n3: Company\n4: Phone number\n5: Exit";
		do {
			System.out.println(viewMessage);
			String choice = sc.nextLine();
			switch (choice) {
			case "1":
				result = findFName();
				updateHelper(result);
				break;
			case "2":
				result = findLName();
				updateHelper(result);
				break;
			case "3":
				result = findCompany();
				updateHelper(result);
				break;
			case "4":
				result = findNumber();
				updateHelper(result);
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

	private static void delete() {
		BasicDBObject query = new BasicDBObject();
		System.out.println("Please enter the first name of the contact");
		String fName = sc.nextLine();
		System.out.println("Please enter the last name of the contact");
		String lName = sc.nextLine();

		query.put("first_name", fName);
		query.put("last_name", lName);
		cards.remove(query);
		System.out.println("Contact deleted");

	}

	private static DBObject findFName() {
		DBObject query = new BasicDBObject();
		System.out.println("Please enter the first name of the contact");
		String name = sc.nextLine();
		query.put("first_name", name);
		DBObject result = cards.findOne(query);
		return result;

	}

	private static DBObject findLName() {
		DBObject query = new BasicDBObject();
		System.out.println("Please enter the last name of the contact");
		String name = sc.nextLine();
		query.put("last_name", name);
		DBObject result = cards.findOne(query);
		return result;

	}

	private static DBObject findCompany() {
		DBObject query = new BasicDBObject();
		System.out.println("Please enter the company of the contact");
		String company = sc.nextLine();
		query.put("company", company);
		DBObject result = cards.findOne(query);
		return result;

	}

	private static DBObject findNumber() {
		DBObject query = new BasicDBObject();
		System.out.println("Please enter the phone number of the contact");
		String number = sc.nextLine();
		query.put("number", number);
		DBObject result = cards.findOne(query);
		return result;

	}

	private static void updateFName(DBObject query) {
		BasicDBObject updateDoc = new BasicDBObject();
		System.out.println("Please enter new first name");
		String name = sc.nextLine();
		updateDoc.append("$set", new BasicDBObject().append("first_name", name));
		cards.update(query, updateDoc);
	}

	private static void updateLName(DBObject query) {
		BasicDBObject updateDoc = new BasicDBObject();
		System.out.println("Please enter new last name");
		String name = sc.nextLine();
		updateDoc.append("$set", new BasicDBObject().append("last_name", name));
		cards.update(query, updateDoc);
	}

	private static void updateCompany(DBObject query) {
		BasicDBObject updateDoc = new BasicDBObject();
		System.out.println("Please enter new company name");
		String company = sc.nextLine();
		updateDoc.append("$set", new BasicDBObject().append("company", company));
		cards.update(query, updateDoc);
	}

	private static void updateNumber(DBObject query) {
		BasicDBObject updateDoc = new BasicDBObject();
		System.out.println("Please enter new company name");
		String number = sc.nextLine();
		updateDoc.append("$set", new BasicDBObject().append("number", number));
		cards.update(query, updateDoc);
	}

	private static BufferedImage takePhoto() {

		return null;
	}

	private static void updateHelper(DBObject result) {
		boolean exit = false;
		String viewMessage = "Please enter which field you would like to update:\n1: First name\n"
				+ "2: Last name\n3: Company\n4: Phone number\n5: Exit";

		System.out.println(viewMessage);
		String choice = sc.nextLine();
		do {
			switch (choice) {
			case "1":
				updateFName(result);
				exit = true;
				break;
			case "2":
				updateLName(result);
				exit = true;
				break;
			case "3":
				updateCompany(result);
				exit = true;
				break;
			case "4":
				updateNumber(result);
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

	public static void printContact(DBObject result) {
		System.out.print("\nFirst Name: " + result.get("first_name"));
		System.out.print(", Last Name: " + result.get("last_name"));
		System.out.print(", Company: " + result.get("company"));
		System.out.println(", Phone number: " + result.get("number"));
	}

}
