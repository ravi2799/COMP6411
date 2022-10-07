import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BinarySearchTree {
	public Node root;
	private final static int MAX_SUCCESSFULL_SEARCH_ELEMENT = 5;
	private final static int MAX_UNSUCCESSFULL_SEARCH_ELEMENT = 5;
	private final static int AVERAGE_COUNT = 5;
	private int numberOfSearch = 0;

	public BinarySearchTree() {
		this.root = null;
	}

	public void insert(int newData) {
		this.root = insert(root, newData);
	}

	public Node insert(Node root, int newData) {
		if (root == null) {
			root = new Node(newData);
			return root;
		} else if (root.data >= newData) {
			root.left = insert(root.left, newData);
		} else {
			root.right = insert(root.right, newData);
		}
		return root;
	}

	public boolean search(int data) {
		return search(this.root, data);
	}

	private boolean search(Node root, int data) {
		numberOfSearch = numberOfSearch + 1;
		if (root == null) {
			return false;
		} else if (root.data == data) {
			return true;
		} else if (root.data > data) {
			return search(root.left, data);
		}
		return search(root.right, data);
	}

	private static class Node {
		public int data;
		public Node left;
		public Node right;

		public Node(int data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub

		BinarySearchTree tree = new BinarySearchTree();
		File myObj = new File("C:\\Users\\Shree\\Desktop\\MS\\raw_material\\comp6411\\Assignment2\\java\\rand.txt");
		ArrayList<Integer> data = new ArrayList<Integer>();
                File file = new File("C:\\Users\\Shree\\Desktop\\MS\\raw_material\\comp6411\\Assignment2\\java\\Binary_Search_tree_output_java.txt");
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\Shree\\Desktop\\MS\\raw_material\\comp6411\\Assignment2\\java\\Binary_Search_tree_output_java.txt", true)));
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getName());
                } else {
                    //System.out.println("File already exists.");
                }

		Scanner myReader = new Scanner(myObj);
		while (myReader.hasNextLine()) {
			int value = Integer.parseInt(myReader.nextLine());
			data.add(value);
		}

		long beforeUsedMem1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < data.size(); i++) {
			tree.insert(data.get(i));
		}

		long stopTime = System.currentTimeMillis();
		long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

		long actualMemUsed = afterUsedMem - beforeUsedMem1;
		long elapsedTime = stopTime - startTime;

		System.out.println("Inserted " + data.size() + " elemenets into BST.");
		System.out.println("Insertion Time: " + elapsedTime + " ms");
		System.out.println("Memory consumed: " + actualMemUsed / 1000000 + " MB");
		System.out.println("--------------------------------------------");
                
                writer.write("**********************Stats*********************\n");
                writer.write("Inserted " + data.size() + " elemenets into BST."+ "\n");
                writer.write("Insertion Time: " + elapsedTime + " ms"+ "\n");
                writer.write("Memory consumed: " + actualMemUsed / 1000000 + " MB"+"\n\n");

		int[] searched_items = { 733002260, 254875457, 178558520, 353221028, 670746645, 10721678, 10674480, 10332995,
				10470047, 10250395 };

		for (int i = 0; i < searched_items.length; i++) {
			long averageSearchTime = 0;
			startTime = System.nanoTime();
			int searchItem = searched_items[i];
			stopTime = System.nanoTime();
			tree.search(searchItem);
			elapsedTime = stopTime - startTime;

			if (i < searched_items.length / 2) {
				System.out.println("--------------------------------------------");
				System.out.println("Searching successfull " + searchItem + " element into binary search tree");
				System.out.println("--------------------------------------------");
                                
                                writer.write("--------------------------------------------\n");
                                writer.write("Searching successfull " + searchItem + " element into binary search tree in "+ tree.numberOfSearch + " searches and time taken is: " + elapsedTime  +"Nano second" +"\n");
                                writer.write("--------------------------------------------\n");
			} else {
				System.out.println("--------------------------------------------");
				System.out.println("Searching unsuccessfull " + searchItem + " element into binary search tree in "+ tree.numberOfSearch + " searches and time taken is: " + elapsedTime + " Nano second" + "\n");
				System.out.println("--------------------------------------------");
                                
                                writer.write("--------------------------------------------\n");
                                writer.write("Searching unsuccessfull " + searchItem + " element into binary search tree in "+ tree.numberOfSearch + " searches and time taken is: " + elapsedTime + " Nano second" + "\n");
                                writer.write("--------------------------------------------\n");
			}
			tree.numberOfSearch = 0;
                
	}
	writer.close();
}
}
