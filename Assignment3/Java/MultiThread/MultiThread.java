import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

class LinkedList {
	public Node head = null;
	public Node tail = null;
	private int size = 0;
	public int NUM_ITERATION = 0;

	private static class Node {
		int data;
		Node next;

		public Node(int data) {
			this.data = data;
		}
	}

	public int size() {
		return size;
	}

	public Node sortedMerge(Node low, Node high) {

		LinkedList list = new LinkedList();

		while (high != null && low != null) {
			if (high.data > low.data) {
				list.push(low.data);
				low = low.next;
			} else {
				list.push(high.data);
				high = high.next;
			}

			NUM_ITERATION = NUM_ITERATION + 1;
		}

		while (low != null) {
			list.push(low.data);
			low = low.next;
			NUM_ITERATION = NUM_ITERATION + 1;
		}

		while (high != null) {
			list.push(high.data);
			high = high.next;
			NUM_ITERATION = NUM_ITERATION + 1;
		}

		return list.head;
	}

	public Node mergeSort(Node h) {
		if (h == null || h.next == null) {
			return h;
		}

		Node middle = getMiddle(h);
		Node nextofmiddle = middle.next;
		middle.next = null;

		Node left = mergeSort(h);
		Node right = mergeSort(nextofmiddle);

		Node sortedlist = sortedMerge(left, right);

		return sortedlist;
	}

	public Node getMiddle(Node head) {
		if (head == null || head.next == null) {
			return head;
		}

		Node slow = head, fast = head;

		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	public void push(int value) {
		if (head == null && tail == null) {
			head = new Node(value);
			tail = head;
		} else {
			Node newNode = new Node(value);
			tail.next = newNode;
			tail = tail.next;
		}

		size = size + 1;
	}

	public void writeSortedLinkedList(Node head, String fileName) {
		try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)))) {

			if (head == null) {
				writer.write("No Data\n");
			} else {
				while (head != null) {
					writer.write(head.data + " \n");
					head = head.next;
				}
			}

		} catch (Exception e) {
		}
	}

}

class Driver extends Thread {

	public LinkedList m1;
	private String name;

	public Driver(LinkedList m, String name) throws InterruptedException {
		this.m1 = m;
		this.name = name;
	}

	@Override
	public void run() {
		m1.NUM_ITERATION = 0;
		long beforeUsedMem1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long startTime = System.nanoTime();
		m1.head = m1.mergeSort(m1.head);
		long endTime = System.nanoTime();
		long elapsedTime = (endTime - startTime) / 1000;
		long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long actualMemUsed = afterUsedMem - beforeUsedMem1;

		System.out.println("Time for " + name + "List: " + elapsedTime + " MicroSeconds");
		System.out
				.println(String.format("%-50s: %10s", "Average Memory Consumed for " + name, actualMemUsed + " bytes"));
		System.out.println(String.format("%-50s: %10s", "Number of iteration for " + name, m1.NUM_ITERATION));
		System.out.println();
	}
}

public class MultiThread {
	public static boolean isPrime(int num) {
		if (num > 2 && num % 2 == 0) {
			return false;
		}
		int top = (int) Math.sqrt(num) + 1;
		for (int i = 3; i < top; i += 2) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String args[]) throws InterruptedException {
		LinkedList evenAndPrimeList = new LinkedList();
		LinkedList evenAndUnprimeList = new LinkedList();
		LinkedList oddAndPrimeList = new LinkedList();
		LinkedList oddAndUnprimeList = new LinkedList();

		int cnt = 0;

		long endtime_even_prime = 0, endtime_even_nonprime = 0, endtime_odd_prime = 0, endtime_odd_unprime = 0;

		try {
			File myObj = new File("rand.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {

				long startTime = System.nanoTime();
				int data = Integer.parseInt(myReader.nextLine());

				// even number
				if (data % 2 == 0) {
					// prime number
					int num = data;

					if (isPrime(num)) {
						evenAndPrimeList.push(data);
						long endTime = System.nanoTime();
						long elapsedTime = (endTime - startTime) / 1000;
						endtime_even_prime += elapsedTime;

					} else {
						evenAndUnprimeList.push(data);
						long endTime = System.nanoTime();
						long elapsedTime = (endTime - startTime) / 1000;
						endtime_even_nonprime += elapsedTime;
					}

					// Prime number
				} else {
					int num = data;

					if (!isPrime(num)) {
						oddAndPrimeList.push(data);
						long endTime = System.nanoTime();
						long elapsedTime = (endTime - startTime) / 1000;
						endtime_odd_prime += elapsedTime;
					} else {
						oddAndUnprimeList.push(data);
						long endTime = System.nanoTime();
						long elapsedTime = (endTime - startTime) / 1000;
						endtime_odd_unprime += elapsedTime;
					}
				}

			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		System.out.println("========================================================================================");
		System.out.println(String.format("%-50s: %10s", "List size of evenAndPrimeList ", evenAndPrimeList.size()));
		System.out.println(String.format("%-50s: %10s", "List size of evenAndUnprimeList ", evenAndUnprimeList.size()));
		System.out.println(String.format("%-50s: %10s", "List size of oddAndPrimeList ", oddAndPrimeList.size()));
		System.out.println(String.format("%-50s: %10s", "List size of oddAndUnprimeList ", oddAndUnprimeList.size()));

		System.out.println(String.format("%-50s: %10s ", "Insertion time for evenAndPrimeList: ",
				endtime_even_prime + " MicroSeconds"));
		System.out.println(String.format("%-50s: %10s", "Insertion time for evenAndUnprimeList: ",
				endtime_even_nonprime + " MicroSeconds"));
		System.out.println(String.format("%-50s: %10s", "Insertion time for oddAndPrimeList: ",
				endtime_odd_prime + " MicroSeconds"));
		System.out.println(String.format("%-50s: %10s", "Insertion time for oddAndUnprimeList: ",
				endtime_odd_unprime + " MicroSeconds"));
		System.out.println("========================================================================================");

		Driver driver1 = new Driver(evenAndPrimeList, "EvenPrimeSortedList");
		Driver driver2 = new Driver(evenAndUnprimeList, "EvenUnPrimeSortedList");
		Driver driver3 = new Driver(oddAndPrimeList, "OddPrimeSortedList");
		Driver driver4 = new Driver(oddAndUnprimeList, "OddUnPrimeSortedList");

		driver1.start();
		driver2.start();
		driver3.start();
		driver4.start();

		driver2.join();
		driver3.join();
		driver4.join();

		driver1.m1.writeSortedLinkedList(driver1.m1.head, "EvenPrimeSortedArrayMultiThread.txt");
		driver2.m1.writeSortedLinkedList(driver2.m1.head, "EvenUnPrimeSortedArrayMultiThread.txt");
		driver3.m1.writeSortedLinkedList(driver3.m1.head, "OddPrimeSortedArrayMultiThread.txt");
		driver4.m1.writeSortedLinkedList(driver4.m1.head, "OddUnPrimeSortedArrayMultiThread.txt");

		System.out.println("Four files are generated");
	}

}