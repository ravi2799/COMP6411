import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

class ChainedHashlinkedListable {

    public LinkedList<Integer>[] linkedList;

    public ChainedHashlinkedListable(int n) {
        linkedList = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            linkedList[i] = new LinkedList<>();
        }
    }

    public void insert(Integer value) {
        linkedList[hash(value)].add(value);
    }

    public void delete(Integer value) {
        linkedList[hash(value)].remove(value);
    }

    public Integer search(Integer value) {
        int n = linkedList[hash(value)].indexOf(value);
        if (n == -1) {
            return linkedList[hash(value)].size();
        } else {
            return n + 1;
        }
    }

    private int hash(Integer value) {

        int hashValue =  value % linkedList.length;
        //System.out.println("Hash1: " + hashValue);

       
        //6System.out.println("Hash2: " + hashValue);

        return hashValue;
    }

    public void printlinkedListable() {
        for (int i = 0; i < linkedList.length; i++) {
            System.out.println("index: " + i + " " + linkedList[i]);
        }
    }
}

public class HashTable {
    private final static int AVERAGE_COUNT = 5;

    public static void main(String[] args) throws FileNotFoundException, IOException {
        ChainedHashlinkedListable table = new ChainedHashlinkedListable(10000);
        File myObj = new File("C:\\Users\\Shree\\Desktop\\MS\\raw_material\\comp6411\\Assignment2\\java\\rand.txt");
        ArrayList<Integer> data = new ArrayList<>();
        File file = new File("HashTable.txt");
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\Shree\\Desktop\\MS\\raw_material\\comp6411\\Assignment2\\java\\HashTable_output_java.txt", true)));
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
            table.insert(data.get(i));
        }

        long stopTime = System.currentTimeMillis();
        long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        long actualMemUsed = afterUsedMem - beforeUsedMem1;
        long elapsedTime = stopTime - startTime;

        System.out.println("Inserted " + data.size() + " elemenets into Hashtable.");
        System.out.println("Insertion Time: " + elapsedTime + " ms");
        System.out.println("Memory consumed: " + actualMemUsed / 1000000 + " MB");
        System.out.println("--------------------------------------------");
        
        try {
            writer.write("**********************Stats*********************\n");
            writer.write("Inserted " + data.size() + " elemenets into Hashtable."+ "\n");
            writer.write("Insertion Time: " + elapsedTime + " ms" + "\n");
            writer.write("Memory consumed: " + actualMemUsed / 1000000 + " MB"+"\n\n");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        int[] searched_items = {733002260, 254875457, 178558520, 353221028, 670746645, 10721678, 10674480, 10332995, 10470047, 10250395};

        for (int i = 0; i < searched_items.length; i++) {
            long averageSearchTime = 0;
            startTime = System.nanoTime();
            int searchItem = searched_items[i];
            stopTime = System.nanoTime();
            elapsedTime = stopTime - startTime;
            int no_search = table.search(searchItem);
            if (i < searched_items.length / 2) {
                System.out.println("--------------------------------------------");
                System.out.println("Searching successfull " + searchItem + " element into hash table in " + elapsedTime  +" Nano second");
                System.out.println("--------------------------------------------");
                
                writer.write("--------------------------------------------\n");
                writer.write("Searching successfull " + searchItem + " element into hash table in " +no_search+ " searches and time taken is"+ elapsedTime + "  Nano Second"+ "\n");
                writer.write("--------------------------------------------\n");
                
                
            } else {
                System.out.println("--------------------------------------------");
                System.out.println("Searching unsuccessfull " + searchItem + " element into hash table");
                System.out.println("--------------------------------------------");
                
                writer.write("--------------------------------------------\n");
                writer.write("Searching unsuccessfull " + searchItem + " element into hash table in "+ no_search+" searches and time taken is" +elapsedTime + "  Nano Second" +  "\n");
                writer.write("--------------------------------------------\n");
            }

        }
        
        writer.close();
        
     
    }
}
