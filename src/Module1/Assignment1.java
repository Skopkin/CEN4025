package Module1;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.stream.Stream;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/** JavaFX Application that allows a user to select and scan a directory 
 * 		for all sub-directories. Displays a hierarchy of directories that
 * 		can be viewed and selected for more information.
 * @author Sam Kopkin
 *
 */
public class Assignment1 extends Application{
	
	/** Override of the Application start method
	 *
	 */
	@Override
	public void start(Stage primaryStage) {
		// Create a BorderPane to store all other nodes
		BorderPane mainPane = new BorderPane();
		
		// Create a DirectoryChooser to allow user to select a directory
		DirectoryChooser directory = new DirectoryChooser();
		directory.setTitle("Select Directory to Scan");
		
		// Create a UI element to display the file hierarchy
		TreeView<FileTreeNode> folderTree = new TreeView<FileTreeNode>();
		
		// Create a TextArea to display directory information
		TextArea folderDescription = new TextArea();
		
		// Add a listener to the folderTree to display a selected directory's file info in the TextArea when selected
		folderTree.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<FileTreeNode>>() {
			@Override
			public void changed(ObservableValue<? extends TreeItem<FileTreeNode>> observable, 
					TreeItem<FileTreeNode> oldValue, TreeItem<FileTreeNode> newValue) {
				if (newValue != null) {
					folderDescription.setText("Filename: " + newValue.getValue() +
							"\n# of files: " + newValue.getValue().getFile().listFiles().length +
							"\nTotal file size: " + readableFileSize(dirSize(newValue.getValue().getFile())));
				} else {
					folderDescription.setText("");
				}
			}
		});
		
		// Create a button to activate the DirectoryChooser
		Button fileSelect = new Button("Select Directory");
		Label fileSelectLabel = new Label("No File Selected", fileSelect);
		
		// When the fileSelect button is pressed, open the DirectoryChooser and set the label to the chosen directory
		fileSelect.setOnAction(e -> {
			File selectedDirectory = directory.showDialog(primaryStage);
			if (selectedDirectory != null) {
				fileSelectLabel.setText(selectedDirectory.getAbsolutePath());
			}
		});
		
		// Create a button to initiate file scanning once a directory has been selected
		Button scanButton = new Button("Scan");
		
		// When the scan button is pressed, scan the selected file and create a folder tree to be displayed
		scanButton.setOnAction(e -> {
			if (fileSelectLabel.getText() != "No File Selected") {
				// Create a File object for the selected file
				File selectedDirectory = new File(fileSelectLabel.getText());
				// Create a root FileTreeNode for the File object
				FileTreeNode rootNode = new FileTreeNode(selectedDirectory);
				// Using the root node, call the recursive method populateTree to add all sub directories as nodes
				populateTree(rootNode);
				// Create a TreeItem for the root node
				TreeItem<FileTreeNode> rootItem = new TreeItem<FileTreeNode>(rootNode);
				// Using the root item, call the recursive method setTreeItem to add child tree items for each sub node
				setTreeItem(rootItem);
				// Set the root item as the root of the TreeView
				folderTree.setRoot(rootItem);
			}
		});
		
		// Create a VBox and store the two buttons
		VBox inputButtonBox = new VBox(5);
		inputButtonBox.getChildren().addAll(fileSelectLabel, scanButton);
		inputButtonBox.setPadding(new Insets(5, 10, 5, 10));
		
		// Set each node to the appropriate area of the BorderPane
		mainPane.setTop(inputButtonBox);
		mainPane.setLeft(folderTree);
		mainPane.setRight(folderDescription);
		
		// Set scene and stage
		Scene scene = new Scene(mainPane);
		primaryStage.setScene(scene);
		primaryStage.setTitle("File Directory Scanner");
		primaryStage.show();
	}
	
	/** Main method used to launch application
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/** Recursive method that takes a FileTreeNode, obtains a list of sub directories
	 * 		adds them as children, and then calls this function again with each child
	 * @param node FileTreeNode containing a File to be scanned
	 */
	private void populateTree(FileTreeNode node) {
		// Create a string array containing the names of any sub directories
		String[] subDirectories = node.getFile().list(new FilenameFilter() {
			@Override
			public boolean accept(File current, String name) {
				return new File(current, name).isDirectory();
			}
		});
		
		// For each sub directory, add it as a child to this FileTreeNode
		//	and then recursively call this method again with the child
		for (String s : subDirectories) {
			File tempFile = new File(node.getFile().getAbsolutePath() + "\\" + s);
			FileTreeNode subNode = new FileTreeNode(tempFile, node);
			node.addChildren(subNode);
			populateTree(subNode);
		}
	}
	
	
	/** Recursive method that takes a TreeItem containing a FileTreeNode
	 * 		and for each child FileTreeNode, create a new TreeItem for it as
	 * 		a child for this one and then call this function again using it
	 * @param item TreeItem containing a FileTreeNode
	 */
	private void setTreeItem(TreeItem<FileTreeNode> item) {
		for (FileTreeNode n : item.getValue().getChildren()) {
			TreeItem<FileTreeNode> subItem = new TreeItem<FileTreeNode>(n);
			item.getChildren().add(subItem);
			setTreeItem(subItem);
		}
	}
	
	/** Calculates the total file size of a directory
	 * @param file File object for the directory to be read
	 * @return long integer value of the total file size
	 */
	private Long dirSize(File file) {
		long size = 0;
		
		// Walk through all files underneath the starting file and sum up the file sizes
		try (Stream<Path> walk = Files.walk(Paths.get(file.toURI()))) {
			size = walk.filter(Files::isRegularFile).mapToLong(p -> {
				try {
					return Files.size(p);
				} catch (IOException e) {
					System.out.printf("Failed to get size of %s%n%s", p, e);
					return 0L;
				}
			})
			.sum();
		} catch (IOException e) {
			System.out.printf("IO errors %s", e);
		}
		
		// Return the total size
		return size;
	}
	
	/** Converts a long integer into an easier to read string in b/kb/mb etc. formatting
	 * @param size long integer to be formatted
	 * @return formatted string
	 */
	private String readableFileSize(long size) {
		 if(size <= 0) return "0";
		    final String[] units = new String[] { "B", "kB", "MB", "GB", "TB" };
		    int digitGroups = (int) (Math.log10(size)/Math.log10(1024));
		    return new DecimalFormat("#,##0.#").format(size/Math.pow(1024, digitGroups)) + " " + units[digitGroups];
	}
}

/** Class that defines a TreeNode containing a File object
 * @author Sam
 *
 */
class FileTreeNode {
	// File object for this node
	File file;
	
	// ArrayList that contains child nodes
	ArrayList<FileTreeNode> children;
	
	// FileTreeNode that represents the parent of this node
	FileTreeNode parent;
	
	/** Constructor for the root node that does not have a parent
	 * @param f File object for this node
	 */
	public FileTreeNode(File f) {
		this.file = f;
		children = new ArrayList<FileTreeNode>();
		parent = null;
	}
	
	/** Constructor for a node that is a child of another node
	 * @param f File object for this node
	 * @param n FileTreeNode to be set as the parent for this node
	 */
	public FileTreeNode(File f, FileTreeNode n) {
		this.setFile(f);
		this.children = new ArrayList<FileTreeNode>();
		this.setParent(n);
	}
	
	/** Getter method for the File
	 * @return File object
	 */
	public File getFile() {
		return this.file;
	}
	
	/** Setter method for the File
	 * @param f
	 */
	private void setFile(File f) {
		this.file = f;
	}
	
	/** Getter method for the children of this node
	 * @return ArrayList containing the child nodes of this node
	 */
	public ArrayList<FileTreeNode> getChildren() {
		return this.children;
	}
	
	/** Setter method for the parent node
	 * @param n FileTreeNode to set as the parent
	 */
	private void setParent(FileTreeNode n) {
		this.parent = n;
	}
	
	/** Adds a child FileTreeNode to this node
	 * @param n FileTreeNode to be added as a child
	 */
	public void addChildren(FileTreeNode n) {
		children.add(n);
	}
	
	/** Creates a new FileTreeNode using a given File and adds it as
	 * 		a child node to this one
	 * @param f File used to create a new child FileTreeNode
	 */
	public void addChildren(File f) {
		children.add(new FileTreeNode(f));
	}
	
	/** Checks if this node is a leaf in the node tree
	 * @return True if this node has no children
	 */
	public boolean isLeaf() {
		return children.isEmpty();
	}
	
	/** Override of the toString method to output the local file name
	 *
	 */
	@Override
	public String toString() {
		return this.file.getName();
	}
}

