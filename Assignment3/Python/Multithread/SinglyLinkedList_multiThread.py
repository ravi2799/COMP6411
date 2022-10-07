import threading
import time
import psutil
import math

class Node:
    def __init__(self, data=None):
        self.data = data
        self.next = None
    
    
class LinkedList:
    total=0
    NUM_ITERATION = 0 
    def __init__(self):
        self.total=0
        self.head = None
        self.tail = None
    
    def size(self):
        return self.total
    
    def push(self, value):
        if (self.head == None and self.tail == None):
            self.head =  Node(value)
            self.tail = self.head
        else:
            newNode = Node(value)
            self.tail.next = newNode
            self.tail = self.tail.next
            
        self.total += 1
       
    def sortedMerge(self,  low,  high):
        list = LinkedList();
        
        while (high != None and low != None):
            if (high.data > low.data):
                list.push(low.data)
                low = low.next
            else:
                list.push(high.data)
                high = high.next

            self.NUM_ITERATION += 1
        
        while (low != None):
            list.push(low.data)
            low = low.next
            self.NUM_ITERATION += 1

        while (high != None):
            list.push(high.data)
            high = high.next
            self.NUM_ITERATION = self.NUM_ITERATION + 1

        return list.head
    
    def mergeSort(self, h):
        if (h == None or h.next == None):
            return h
        

        middle = self.getMiddle(h)
        nextofmiddle = middle.next
        middle.next = None

        left = self.mergeSort(h)
        right = self.mergeSort(nextofmiddle)

        sortedlist = self.sortedMerge(left, right)
        return sortedlist
    
    def getMiddle(self, nn):
        if (nn == None or nn.next == None):
            return nn

        slow = nn
        fast = nn

        while (fast.next != None and fast.next.next != None):
            slow = slow.next
            fast = fast.next.next
        return slow
    
    def writeSortedLinkedList(self, n, name, time, space, iteration):
        with open(name, 'w') as f:
            f.write("===============================================================\n")
            f.write("Memory consumed: " + str(space) + " MB")
            f.write("\n")
            f.write("Time consumed: " + str(time) +" Second\n")
            f.write("Number of Iteration : "+str(iteration) +"\n")
            f.write("===============================================================\n")
            if (n == None):
                f.write("No Data")
            else:
                while (n != None):
                    new_data = str(n.data)
                    f.write(new_data)
                    f.write("\n")
                    n = n.next 
        f.close()
            
cour=0
def counter():
    global cour
    cour += 1

def isPrime(num):
    if num > 2 and num % 2 == 0:
	    return False

    square = (int) (math.sqrt(num) + 1)
    for i in range(3,square,2):
        if num % i == 0:
            return False
        
    return True

def callMergeSort(linkedlist, filename):
    start_time = time.time()
    space = 0
    
    linkedlist.head = linkedlist.mergeSort(linkedlist.head)
    end_time = time.time() - start_time
    space = psutil.Process().memory_info().rss / (1024 * 1024) - space

    linkedlist.writeSortedLinkedList(linkedlist.head, filename, end_time, space, linkedlist.NUM_ITERATION)
    
if __name__ == "__main__":

    evenPrimeLinkedList = LinkedList()
    evenNonPrimeLinkedList = LinkedList()
    oddPrimeLinkedList = LinkedList()
    oddNonPrimeLinkedList = LinkedList()
    
    data_storage = []
    data_file = open('rand.txt' , 'r')
    lines = data_file.readlines()

    count = 0
    endtime_even_prime = 0
    endtime_even_nonprime=0
    endtime_odd_prime=0
    endtime_odd_nonprime=0
    
    for i, data in enumerate(lines):
        start_time = time.time()
        count = count + 1
        #print("index",i)

        curr_number = int(data)
        try:
            if curr_number % 2 == 0:
                if isPrime(curr_number) == True:
                    endTime = time.time();
                    elapsedTime = endTime-start_time
                    endtime_even_prime+=elapsedTime;
                    evenPrimeLinkedList.push(curr_number)
                else:
                    endTime = time.time();
                    elapsedTime = endTime-start_time
                    endtime_even_nonprime+=elapsedTime;
                    evenNonPrimeLinkedList.push(curr_number)

            #odd numbers
            else:
                if isPrime(curr_number) == True:
                    endTime = time.time();
                    elapsedTime = endTime-start_time
                    endtime_odd_prime+=elapsedTime;
                    oddPrimeLinkedList.push(curr_number)
                else:
                    endTime = time.time();
                    elapsedTime = endTime-start_time
                    endtime_odd_nonprime+=elapsedTime;
                    oddNonPrimeLinkedList.push(curr_number)
        except Exception as e:
            print(e)
            
    #print linkedlist size
    print("Even Prime list size: ", evenPrimeLinkedList.size())
    print("Even Non Prime list size: ", evenNonPrimeLinkedList.size())
    print("Odd Prime list size: ", oddPrimeLinkedList.size())
    print("Odd Non Prime list size: ", oddNonPrimeLinkedList.size())

    start_time = time.time()
    
    t1 = threading.Thread(target=callMergeSort, args=(evenPrimeLinkedList, "evenPrimeList_multi_thread.txt"))
    t2 = threading.Thread(target=callMergeSort, args=(evenNonPrimeLinkedList,"evenNonPrimeList_multi_thread.txt"))
    t3 = threading.Thread(target=callMergeSort, args=(oddPrimeLinkedList, "oddPrimeList_multi_thread.txt"))
    t4 = threading.Thread(target=callMergeSort, args=(oddNonPrimeLinkedList,"oddNonPrimeList_multi_thread.txt"))
    
    t1.start()
    t2.start()
    t3.start()
    t4.start()
    
    t2.join()
    t3.join()
    t4.join()
    
    end_time = time.time() - start_time
    space = psutil.Process().memory_info().rss / (1024 * 1024)
    
    with open('merge_sort_multithread_output_python.txt' , 'w') as f:
        f.write("----------------- Insertion Time ----------------------------\n")
        f.write("Even Prime: "+str(endtime_even_prime)+" Seconds\n")
        f.write("Even NonPrime: "+str(endtime_even_nonprime)+" Seconds\n")
        f.write("Odd Prime: "+str(endtime_odd_prime)+" Seconds\n")
        f.write("Odd NonPrime: "+str(endtime_odd_nonprime)+" Seconds\n")
        f.write("---------------------------------------------")
        f.write("\n")
        f.write("Memory consumed: " + str(space) + "MB")
        f.write("\n")
        f.write("Time consumed: " + str(end_time) +"Second")
        f.write("\n")
        f.write("---------------------------------------------")
        f.write("\n")
    f.close()

    