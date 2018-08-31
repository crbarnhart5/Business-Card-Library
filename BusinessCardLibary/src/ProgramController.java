import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;

public class ProgramController {
	public static void Main(String[] args) {
		System.out.println("Hello, please select your choice");
		InteractWithUser();
		System.out.println("Goodbye");
	}

	private static void InteractWithUser() {
		Scanner sc = new Scanner(System.in);
		String initialMessage = "Please enter number of option:\n" 
				+ "1: View contact info\n" 
				+ "2: Add contact info\n"
				+ "3: Update contact info\n" 
				+ "4: Delete contact info\n" 
				+ "5: Exit";

		do {
			System.out.println(initialMessage);
			String choice = sc.nextLine();
			switch (choice) {
			case "1":
				View();
				break;
			case "2":
				Add();
				break;
			case "3":
				Update();
				break;
			case "4":
				Delete();
				break;
			case "5":
				sc.close();
				return;
			default:
				System.out.println("Invalid choice entered");
				break;
			}

		} while (true);

	}

	private static void View() {
		Scanner sc = new Scanner(System.in);
		String viewMessage = "View: Please enter the number of what you would like to search by:\n" 
				+ "1: First name\n"
				+ "2: Last name\n" 
				+ "3: Company\n" 
				+ "4: Exit";
		do {
			System.out.println(viewMessage);
			String choice = sc.nextLine();
			switch (choice) {
			case "1":
				break;
			case "2":
				break;
			case "3":
				break;
			case "4": sc.close();
				return;
			default:
				System.out.println("Invalid choice entered");
				break;
			}
		} while (true);

	}

	private static void Add() {
		Scanner sc = new Scanner(System.in);
		
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
		BufferedImage businessCard = TakePhoto();
		
	}

	private static void Update() {
		Scanner sc = new Scanner(System.in);
		String viewMessage = "Update: Please enter the number of what you would like to search by:\n" 
				+ "1: First name\n"
				+ "2: Last name\n" 
				+ "3: Company\n" 
				+ "4: Exit";
		
		do {
			System.out.println(viewMessage);
			String choice = sc.nextLine();
			switch (choice) {
			case "1":
				break;
			case "2":
				break;
			case "3":
				break;
			case "4": sc.close();
				return;
			default:
				System.out.println("Invalid choice entered");
				break;
			}
		
		//Iterate through options matching search
		
		//Ask what field they would like to update, iterate through available options
		
		System.out.println("What would you like to replace the field with?");
		String replacement = sc.nextLine();
		
		//Update field
		
		System.out.println("Update completed");
		} while (true);
	}

	private static void Delete() {

	}

	private static BufferedImage TakePhoto() {

		return null;
	}
}
