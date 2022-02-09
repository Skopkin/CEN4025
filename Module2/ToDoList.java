package Module2;

import java.util.ArrayList;

/**
 * Defines a ToDoList class that represents a list of To-Do items
 * @author Sam
 *
 */
public class ToDoList {
	ArrayList<ToDoItem> list;
	
	/**
	 * Constructor for a ToDoList
	 */
	public ToDoList() {
		list = new ArrayList<ToDoItem>();
	}
	
	/**
	 * Adds a ToDoItem to the list
	 * @param item Item to be added
	 */
	public void add(ToDoItem item) {
		list.add(item);
	}
	
	/**
	 * Removes a given ToDoItem from the list
	 * @param s Item to be removed
	 */
	public void remove(String s) {
		ToDoItem item = null;
		for (ToDoItem i: list) {
			if (i.getToDo().equals(s)) {
				item = i;
				break;
			}
		}
		if (item != null) {
			list.remove(item);
		}
	}
	
	/**
	 * Getter method for the list
	 * @return
	 */
	public ArrayList<ToDoItem> getList() {
		return list;
	}
	
	/**
	 * Checks if the given item is on the list
	 * @param s Item to be checked for
	 * @return returns true if the item is on the list.
	 */
	public boolean contains(String s) {
		for (ToDoItem i: list) {
			if (i.getToDo().equals(s)) {
				return true;
			}
		}
		return false;
	}
}

