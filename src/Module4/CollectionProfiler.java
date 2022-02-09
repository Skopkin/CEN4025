package Module4;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;

/**
 * @author Sam
 *
 */
public class CollectionProfiler {

	/**
	 * Main method. Calls each type of collection testing method
	 * @param args
	 */
	public static void main(String[] args) {
		final int ITERATIONS = 2000000;
		addDeleteArrayList(ITERATIONS);
		addDeleteLinkedList(ITERATIONS);
		addDeleteHashTable(ITERATIONS);
	}
	
	/**
	 * Adds a given number of integers to an ArrayList and then removes them
	 * @param iterations Number of integers to add
	 */
	private static void addDeleteArrayList(int iterations) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for (int i = 0; i < iterations; i++) {
			list.add((int) (Math.random() * 10) + 1);
		}
		
		System.out.println("ArrayList max size: " + list.size());
		
		list.removeAll(list);
		
		System.out.println("ArrayList final size: " + list.size());
	}
	
	/**
	 * Adds a given number of integers to an LinkedList and then removes them
	 * @param iterations Number of integers to add
	 */
	private static void addDeleteLinkedList(int iterations) {
		LinkedList<Integer> list = new LinkedList<Integer>();
			
		for (int i = 0; i < iterations; i++) {
			list.add((int) (Math.random() * 10) + 1);
		}
		
		System.out.println("LinkedList max size: " + list.size());
		
		list.removeAll(list);
		
		System.out.println("LinkedList final size: " + list.size());
	}
	
	/**
	 * Adds a given number of integers to an ArrayList and then removes them
	 * @param iterations Number of integers to add
	 */
	private static void addDeleteHashTable(int iterations) {
		Hashtable<Integer, Integer> table = new Hashtable<Integer, Integer>();
		
		for (int i = 0; i < iterations; i++) {
			table.put(i, (int) (Math.random() * 10) + 1);
		}	
		
		System.out.println("HashTable max size: " + table.size());
		
		table.clear();
		
		System.out.println("HashTable final size: " + table.size());
	}
}
