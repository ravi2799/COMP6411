************* Folder structure *********************

Java and Python folder contains respective files for languages.

---java
	|-SingleThread
		|       SingleThread.java                              ----------------> java file contains code for mergesort on singly linkedlist
		|       EvenPrimeSortedArraySingleThread.txt           ----------------> file contain sorted data for the numbers which are even and prime 
		|       EvenUnPrimeSortedArraySingleThread.txt 		 ----------------> file contain sorted data for the numbers which are even and non-prime 
		|	    OddPrimeSortedArraySingleThread.txt	       ----------------> file contain sorted data for the numbers which are odd and prime
		|	    OddUnPrimeSortedArraySingleThread.txt 		 ----------------> file contain sorted data for the numbers which are odd and non-prime 
		|       SingleThread_txt.txt                     ---------------------> file contains code in txt file for single threading
		|       rand.txt                           		 -----------------> Input file for the code

	|-MultiThread
		|	    MultiThread.java                               ----------------> java file contains code for mergesort on four distinct linkedlist
		|       EvenPrimeSortedArrayMultiThread.txt            ----------------> file contain sorted data for the numbers which are even and prime 
		|       EvenUnPrimeSortedArrayMultiThread.txt 		 ----------------> file contain sorted data for the numbers which are even and non-prime 
		|	    OddPrimeSortedArrayMultiThread.txt	       ----------------> file contain sorted data for the numbers which are odd and prime
		|	    OddUnPrimeSortedArrayMultiThread.txt 		 ----------------> file contain sorted data for the numbers which are odd and non-prime 
		|       MultiThread_txt.txt                      --------------------> file contains code in txt file for multithreading   
		|       rand.txt                           		 -----------------> Input file for the code
		
---Python
	|-SingleThread
		|       SinglyLinkedList_singleThread.py               --------------->  Python file contains code for mergesort on singly linkedlist
		|       evenPrimeList_single_thread.txt		       --------------->  file contain sorted data for the numbers which are even and prime 
		|       evenNonPrimeList_single_thread.txt    		 --------------->  file contain sorted data for the numbers which are even and non-prime
		|       oddPrimeList_single_thread.txt        		 ----------------> file contain sorted data for the numbers which are odd and prime      
		|       oddNonPrimeList_single_thread.txt              ----------------> file contain sorted data for the numbers which are odd and non-prime
		|       merge_sort_singlethread_output_python.txt      ----------------> file contain stastics like insertion time for 4 list and overall memory consumption and total time
		|		SinglyLinkedList_singleThread_txt_file.txt   -----------------> file contain code in txt file for sigle threading
	|-MultiThread
		|       SinglyLinkedList_multiThread.py                --------------->  Python file contains code for mergesort on four distinct linkedlist
		|       evenPrimeList_multi_thread.txt		       --------------->  file contain sorted data for the numbers which are even and prime 
		|       evenNonPrimeList_multi_thread.txt    		 --------------->  file contain sorted data for the numbers which are even and non-prime
		|       oddPrimeList_multi_thread.txt        		 ----------------> file contain sorted data for the numbers which are odd and prime      
		|       oddNonPrimeList_multi_thread.txt               ----------------> file contain sorted data for the numbers which are odd and non-prime
		|       merge_sort_multithread_output_python.txt       ----------------> file contain stastics like insertion time for 4 list and overall memory consumption and total time
		|       rand.txt                            		 ----------------> Input file for the code
		|		SinglyLinkedList_multiThread_txt_file.txt    -----------------> file contain code in txt file for mutlti threading  

        
------------------- For run the program --------------------------

----------- **PYTHON** ----------

Inside SingleThread:

	Step1:  python SinglyLinkedList_singleThread.py (This will generate five output files as described above)

Inside MuliThread

	Step1:  python SinglyLinkedList_multiThread.py (This will generate five output files as described above)


----------- **JAVA** ----------
inside SingleThread:

	Step1: javac *.java
	Step2: java SingleThread  (This will generate four output files as described above)

Inside MultiThread:
	Step1: javac *.java
	Step2: java MultiThread (This will generate four output files as described above)
