package Module2;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Sam Kopkin
 *
 */
public class Assignment2 {
	/**
	 * Main method that defines a console based menu system to interact with the To-Do List
	 * @param args
	 */
	public static void main(String[] args) {
		HibernateDb db = new HibernateDb();
		ArrayList<ToDoItem> list = db.updateList();
		Scanner input = new Scanner(System.in);
		
		final String menuText = "1. Add a to-do item\n"
				+ "2. Delete a to-do item\n"
				+ "3. View all to-do items\n"
				+ "4. Exit application";
		
		int menuOption = 0;
		String textInput;
		
		do {
			System.out.println("Welcome to the To-Do List application. Please select an option:\n" + menuText);
			try {
				menuOption = input.nextInt();
			} catch (Exception e) {
				menuOption = 0;
				input.nextLine();
			}
		
			switch (menuOption) {
				case 1: System.out.println("Please enter in a to-do item:");
						input.nextLine();
						textInput = input.nextLine();
						ToDoItem tempItem = new ToDoItem(textInput);
						if (db.addItem(tempItem)) {
							list = db.updateList();
							System.out.println("\"" + textInput + "\" added to the list");
						} else {
							System.out.println("\"" + textInput + "\" is already on the list");
						}
						break;
				case 2: System.out.println("Please enter a to-do item to remove:");
						input.nextLine();
						textInput = input.nextLine();
						if (db.removeItem(textInput)) {
							System.out.println("\"" + textInput + "\" removed from the list");
							list = db.updateList();
						} else {
							System.out.println("\"" + textInput + "\" not found in the to-do list.");
						}
						break;
				case 3: System.out.println("To-Do List:");
						for (ToDoItem i: list) {
							System.out.println(i);
						}
						break;
				case 4: System.out.println("Thank you for using the To-Do List Application");
						input.close();
						break;
				default: System.out.println("Invalid menu option"); break;
			}
			System.out.println();
		} while (menuOption != 4);
		
	}
}