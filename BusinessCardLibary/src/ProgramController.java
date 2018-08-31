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
				+ "1: View contact info" 
				+ "2: Add contact info"
				+ "3: Update contact info" 
				+ "4: Delete contact info" 
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
		String viewMessage = "Please enter the number of what you would like to search by:" 
				+ "1: First name"
				+ "2: Last name" 
				+ "3: Company" 
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

	}

	private static void Delete() {

	}

	private static BufferedImage TakePhoto() {

		return null;
	}
}
