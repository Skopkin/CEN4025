package Module2;

/**
 * Defines a ToDoItem class. A ToDoItem contains a string representing a task to do
 * @author Sam
 *
 */
public class ToDoItem {
	String todo;
	
	/**
	 * Constructor for a ToDoItem
	 * @param s String to represent the item
	 */
	public ToDoItem(String s) {
		setToDo(s);
	}
	
	/**
	 * Private method to set to-do item
	 * @param s
	 */
	private void setToDo(String s) {
		this.todo = s;
	}
	
	/**
	 * Getter method for to-do item
	 * @return returns to-do item
	 */
	public String getToDo() {
		return todo;
	}
	
	/**
	 * Override of the toString method
	 */
	@Override
	public String toString() {
		return todo;
	}
}
