package Module2;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


/**
 * Defines a ToDoItem class. A ToDoItem contains a string representing a task to do
 * @author Sam
 *
 */
@Entity(name="ToDoList")
public class ToDoItem {
	@Id
	String task;
	
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	
	public ToDoItem() {
		
	}

	/**
	 * Constructor for a ToDoItem
	 * @param s String to represent the item
	 */
	public ToDoItem(String s) {
		setTask(s);
	}
	
	/**
	 * Override of the toString method
	 */
	@Override
	public String toString() {
		return task;
	}
}
