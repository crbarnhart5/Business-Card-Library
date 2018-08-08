import java.awt.image.BufferedImage;
import java.util.Scanner;

public class ProgramController {
	public static void Main(String[] args){
		System.out.println("Hello, please select your choice");
		InteractWithUser();
		System.out.println("Goodbye");
	}
	
	private static void InteractWithUser(){
		boolean loop = true;
		String initialMessage = "Please enter number of option"
								+ "1: View contact info"
								+ "2: Add contact info"
								+ "3: Update contact info"
								+ "4: Delete contact info"
								+ "5: Exit";
		Scanner sc = new Scanner(System.in);
		do{
			System.out.println(initialMessage);
			String choice = sc.nextLine();
			switch(choice){
				case "1": View();
					break;
				case "2": Add();
					break;
				case "3": Update();
					break;
				case "4": Delete();
					break;
				case "5": return;
				default: System.out.println("Invalid choice entered");
					break;
			}
			
		} while(loop);
	}
	
	private static void View(){
		
	}
	
	private static void Add(){
		
	}
	
	private static void Update(){
		
	}
	
	private static void Delete(){
		
	}
	
	private static BufferedImage TakePhoto(){
		
		return null;
	}
}
