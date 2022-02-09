package Module2;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


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
		ToDoList list = new ToDoList();
		Scanner input = new Scanner(System.in);
		
		Configuration config = new Configuration();
		
		SessionFactory sf = config.buildSessionFactory();
		
		Session session = sf.openSession();
		
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
						list.add(new ToDoItem(textInput));
						break;
				case 2: System.out.println("Please enter a to-do item to remove:");
						input.nextLine();
						textInput = input.nextLine();
						if (list.contains(textInput)) {
							list.remove(textInput);
							System.out.println("\"" + textInput + "\" removed from the list");
						} else {
							System.out.println("\"" + textInput + "\" not found in the to-do list.");
						}
						break;
				case 3: System.out.println("To-Do List:");
						for (ToDoItem i: list.getList()) {
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