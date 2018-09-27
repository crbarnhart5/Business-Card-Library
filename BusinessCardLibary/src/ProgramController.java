
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
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

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
	
	//Turning off MongoDB logging
	static Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
	static {
		root.setLevel(Level.OFF);
	}
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		
		//Setting up MongoDB connection
		MongoClient mongoClient = new MongoClient();
		DB database = mongoClient.getDB("contacts");
		cards = database.getCollection("cards");
		
		//interactWithUser();
		//taketo();
		
		
		//Close scanner and connection to database
		mongoClient.close();
		sc.close();
		
		System.out.println("Goodbye");
	}
	
	/**
	 * <p> Interacts with user selecting the action they choose </p>
	 */
	private static void interactWithUser() {
		String initialMessage = "Please enter number of option:\n1: View contact info\n2: Add contact info\n"
				+ "3: Update contact info\n4: Delete contact info\n5: Exit";
		boolean exit = false;

		//Loop until user has selected exit
		do {
			System.out.println(initialMessage);
			String choice = sc.nextLine();
			
			//Calls the choice user chose
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

	/**
	 * <p> Asks the user what they would like to search for the contact by, and calls appropriate method </p>
	 */
	private static void view() {
		DBObject query = new BasicDBObject();
		DBObject result = new BasicDBObject();
		boolean exit = false;
		String viewMessage = "View: Please enter the number of what you would like to search by:\n1: First name\n"
				+ "2: Last name\n3: Company\n4: Phone number\n5: Exit";
		
		//Loops until user has selected exit
		do {
			System.out.println(viewMessage);
			String choice = sc.nextLine();
			
			//Calls the choice the user chose to search by
			switch (choice) {
			case "1":
				
				//Calls findFName method to get contact by first name
				result = findFName();
				
				//If contact is found calls printMethod method on it and exits loop 
				if (result != null) {
					printContact(result);
					exit = true;
				} else {
					System.out.println("No contact found");
				}
				break;
			case "2":
				//Calls findLName method to get contact by last name
				result = findLName();
				
				//If contact is found calls printMethod method on it and exits loop 
				if (result != null) {
					printContact(result);
					exit = true;
				} else {
					System.out.println("No contact found");
				}

				break;
			case "3":
				//Calls findCompany method to get contact by company
				result = findCompany();
				
				//If contact is found calls printMethod method on it and exits loop 
				if (result != null) {
					printContact(result);
					exit = true;
				} else {
					System.out.println("No contact found");
				}
				break;
			case "4":
				//Calls findNumber method to get contact by phone number
				result = findNumber();
				
				//If contact is found calls printMethod method on it and exits loop 
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

	/**
	 * <p> Prompts the user for contact info, and inserts new contact into database </p>
	 */
	private static void add() {
		DBObject query = new BasicDBObject();

		//Collects information about new contact
		System.out.println("Add:");
		System.out.println("Please enter the contact's first name.");
		String fName = sc.nextLine();
		System.out.println("Please enter the contact's last name");
		String lName = sc.nextLine();
		System.out.println("Please enter the contact's company");
		String company = sc.nextLine();
		System.out.println("Please enter the contact's phone number");
		String number = sc.nextLine();

		//Supplies new information to DBObject
		query.put("first_name", fName);
		query.put("last_name", lName);
		query.put("company", company);
		query.put("number", number);
		
		//Adds contact to database
		cards.insert(query);
	}

	/**
	 * <p> Asks the user what they would like to search for the contact by, and calls appropriate method </p>
	 */
	private static void update() {
		DBObject result = new BasicDBObject();
		boolean exit = false;
		String viewMessage = "View: Please enter the number of what you would like to search by:\n1: First name\n"
				+ "2: Last name\n3: Company\n4: Phone number\n5: Exit";
		
		//Loop until user has selected exit
		do {
			
			//Calls the choice the user chose to search by
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

	/**
	 * <p> Prompts the user for information about contact, and deletes given contact from the database </p>
	 */
	private static void delete() {
		BasicDBObject query = new BasicDBObject();
		//Collects information about contact to delete
		System.out.println("Please enter the first name of the contact");
		String fName = sc.nextLine();
		System.out.println("Please enter the last name of the contact");
		String lName = sc.nextLine();
		
		//Deletes contact
		query.put("first_name", fName);
		query.put("last_name", lName);
		cards.remove(query);
		System.out.println("Contact deleted");

	}

	/**
	 * <p> Prompts the user for the first name of contact and returns contact </P
	 * @return the contact of given information
	 */
	private static DBObject findFName() {
		DBObject query = new BasicDBObject();
		
		//Collects first name of contact to find
		System.out.println("Please enter the first name of the contact");
		String name = sc.nextLine();
		query.put("first_name", name);
		
		//Returns contact
		DBObject result = cards.findOne(query);
		return result;

	}
	
	/**
	 * <p> Prompts the user for the last name of contact and returns contact </p>
	 * @return the contact of given information
	 */
	private static DBObject findLName() {
		DBObject query = new BasicDBObject();
		
		//Collects last name of contact to find
		System.out.println("Please enter the last name of the contact");
		String name = sc.nextLine();
		query.put("last_name", name);
		
		//Returns contact
		DBObject result = cards.findOne(query);
		return result;

	}

	/**
	 * <p> Prompts the user for the company of contact and returns contact </p>
	 * @return the contact of given information
	 */
	private static DBObject findCompany() {
		DBObject query = new BasicDBObject();
		
		//Collects company of contact to find
		System.out.println("Please enter the company of the contact");
		String company = sc.nextLine();
		query.put("company", company);
		
		//Returns contact
		DBObject result = cards.findOne(query);
		return result;

	}

	/**
	 * <p> Prompts the user for the number of contact and returns contact </p>
	 * @return the contact of given information
	 */
	private static DBObject findNumber() {
		DBObject query = new BasicDBObject();
		
		//Collects number of contact to find
		System.out.println("Please enter the phone number of the contact");
		String number = sc.nextLine();
		query.put("number", number);
		
		//Returns contact
		DBObject result = cards.findOne(query);
		return result;

	}

	/**
	 * <p> Prompts the user for the first name of contact and updates contact with new info </p>
	 */
	private static void updateFName(DBObject query) {
		BasicDBObject updateDoc = new BasicDBObject();
		
		//Collects first name of contact to update
		System.out.println("Please enter new first name");
		String name = sc.nextLine();
		
		//Updates contact
		updateDoc.append("$set", new BasicDBObject().append("first_name", name));
		cards.update(query, updateDoc);
	}

	/**
	 * <p> Prompts the user for the last name of contact and updates contact with new info </p>
	 */
	private static void updateLName(DBObject query) {
		BasicDBObject updateDoc = new BasicDBObject();
		
		//Collects last name of contact to update
		System.out.println("Please enter new last name");
		String name = sc.nextLine();
		
		//Updates contact
		updateDoc.append("$set", new BasicDBObject().append("last_name", name));
		cards.update(query, updateDoc);
	}

	/**
	 * <p> Prompts the user for the company of contact and updates contact with new info </p>
	 */
	private static void updateCompany(DBObject query) {
		BasicDBObject updateDoc = new BasicDBObject();
		
		//Collects company of contact to update
		System.out.println("Please enter new company name");
		String company = sc.nextLine();
		
		//Updates contact
		updateDoc.append("$set", new BasicDBObject().append("company", company));
		cards.update(query, updateDoc);
	}

	/**
	 * <p> Prompts the user for the number of contact and updates contact with new info </p>
	 */
	private static void updateNumber(DBObject query) {
		BasicDBObject updateDoc = new BasicDBObject();
		
		//Collects number of contact to update
		System.out.println("Please enter new company name");
		String number = sc.nextLine();
		
		//Updates contact
		updateDoc.append("$set", new BasicDBObject().append("number", number));
		cards.update(query, updateDoc);
	}

	private static BufferedImage takePhoto() {
        TakePhoto tp = new TakePhoto();
        tp.capureSnapShot();
		return null;
	}

	/**
	 * <p> Asks the user what field they are updating, and calls appropriate method </p>
	 * @param result the contact that will be updated
	 */
	private static void updateHelper(DBObject result) {
		boolean exit = false;
		String viewMessage = "Please enter which field you would like to update:\n1: First name\n"
				+ "2: Last name\n3: Company\n4: Phone number\n5: Exit";

		System.out.println(viewMessage);
		String choice = sc.nextLine();
		
		//Loops until valid choice has been selected
		do {
			
			//Calls update method selected by user
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

	/**
	 * <p> prints out information of given contact </p>
	 * @param result the contact to be printed
	 */
	public static void printContact(DBObject result) {
		System.out.print("\nFirst Name: " + result.get("first_name"));
		System.out.print(", Last Name: " + result.get("last_name"));
		System.out.print(", Company: " + result.get("company"));
		System.out.println(", Phone number: " + result.get("number"));
	}

}
