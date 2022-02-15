package Module2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class testToDoItem {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	@DisplayName("Test for ToDoItem getter method")
	void testGetTask() {
		String testString = "Test getter method";
		ToDoItem item = new ToDoItem(testString);
		assertEquals(testString, item.getTask());
	}
	
	@Test
	@DisplayName("Test for ToDoItem setter method")
	void testSetTask() {
		String testString = "Test setter method";
		ToDoItem item = new ToDoItem();
		item.setTask(testString);
		assertEquals(testString, item.getTask());
	}
}
