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

            System.out.println();
        } catch (Exception e) {
        }
    }

}

public class SingleThread {

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

    public static void main(String[] args) throws InterruptedException {

        LinkedList evenAndPrimeList = new LinkedList();
        LinkedList evenAndUnprimeList = new LinkedList();
        LinkedList oddAndPrimeList = new LinkedList();
        LinkedList oddAndUnprimeList = new LinkedList();

        int cnt = 0;
        try {
            File myObj = new File("rand.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                int data = Integer.parseInt(myReader.nextLine());

                // even number
                if (data % 2 == 0) {

                    // prime number
                    int num = data;

                    if (isPrime(num)) {
                        evenAndPrimeList.push(data);
                    } else {
                        evenAndUnprimeList.push(data);
                    }

                    // Prime number
                } else {
                    int num = data;

                    if (!isPrime(num)) {
                        oddAndPrimeList.push(data);
                    } else {
                        oddAndUnprimeList.push(data);
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
        System.out.println("========================================================================================");

        Runnable p1 = new Runnable() {
            @Override
            public void run() {
                long beforeUsedMem1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                long startTime = System.nanoTime();

                evenAndPrimeList.head = evenAndPrimeList.mergeSort(evenAndPrimeList.head);
                evenAndUnprimeList.head = evenAndUnprimeList.mergeSort(evenAndUnprimeList.head);
                oddAndPrimeList.head = oddAndPrimeList.mergeSort(oddAndPrimeList.head);
                oddAndUnprimeList.head = oddAndUnprimeList.mergeSort(oddAndUnprimeList.head);

                long endTime = System.nanoTime();
                long elapsedTime = (endTime - startTime)/1000;
                long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                long actualMemUsed = afterUsedMem - beforeUsedMem1;

                System.out.println(String.format("%-50s: %10s" ,"Time for All List to sort: " , elapsedTime + " MicroSeconds"));
                System.out.println(String.format("%-50s: %10s", "Average Memory Consumed", actualMemUsed + " bytes"));
                System.out.println(String.format("%-50s: %10s", "Number of iteration for evenAndPrimeList", evenAndPrimeList.NUM_ITERATION));
                System.out.println(String.format("%-50s: %10s", "Number of iteration for evenAndUnprimeList", evenAndUnprimeList.NUM_ITERATION));
                System.out.println(String.format("%-50s: %10s", "Number of iteration for oddAndPrimeList", oddAndPrimeList.NUM_ITERATION));
                System.out.println(String.format("%-50s: %10s", "Number of iteration for oddAndUnprimeList", oddAndUnprimeList.NUM_ITERATION));
                

                evenAndPrimeList.writeSortedLinkedList(evenAndPrimeList.head, "EvenPrimeSortedArraySingleThread.txt");
                evenAndUnprimeList.writeSortedLinkedList(evenAndUnprimeList.head, "EvenUnPrimeSortedArraySingleThread.txt");
                oddAndPrimeList.writeSortedLinkedList(oddAndPrimeList.head, "OddPrimeSortedArraySingleThread.txt");
                oddAndUnprimeList.writeSortedLinkedList(oddAndUnprimeList.head, "OddUnPrimeSortedArraySingleThread.txt");
                System.out.println("");
            }
        };

        Thread t1 = new Thread(p1);
        t1.start();
    }
}
