from collections import deque
import time
import psutil

cour=0
def counter():
    global cour
    cour += 1

class HashTable:
    
    def __init__(self, capacity=1000):
        self.capacity = capacity
        self.linkedList = [deque() for _ in range(capacity)]

    def _find_by_key(self, key):
        index = self._hash_function(int(key))
        hash_table_bucket = self.linkedList[index]

        if str(key)+"\n" in hash_table_bucket:
            
            return hash_table_bucket.index(str(key)+"\n")
        else:
            return len(hash_table_bucket ) + 1

    def _hash_function(self, key):
        key  = key % self.capacity
        return key


    def insert(self, value):
        self.linkedList[self._hash_function(int(value))].append(value)
        return self

    def get(self, key):
        return self._find_by_key(key)

    def remove(self, value):
        self.linkedList[self._hash_function(value)].remove(value)
        return self

if __name__ == "__main__":
    ht = HashTable(10000)
    data_storage = []
    data_file = open('rand.txt' , 'r')
    lines = data_file.readlines()
    count = 0
    for data in lines:
        count = count + 1
        data_storage.append(data)
    start_time = time.time()
    for data in data_storage:
        ht.insert(data)
    end_time = time.time() - start_time
    space = psutil.Process().memory_info().rss / (1024 * 1024)
    print("Inserted ", len(data_storage), " elements into Hashtable\n")
    print("Insertion Time: ", end_time , "\n")
    print("Memory consumed: ", space, "\n")
    print("--------------------------------------------------")

    with open('hash_table_output_python.txt' , 'w') as f:
        f.write("---------------------------------------------")
        f.write("\n")
        f.write("Memory consumed: " + str(space) + "MB")
        f.write("\n")
        f.write("Insertion Time: " + str(end_time) +"Second")
        f.write("\n")
        f.write("Inserted  " + str(len(data_storage)) + "elements in Hashtable.")
        
        f.write("\n")
        f.write("---------------------------------------------")
        f.write("\n")
    
        items_to_search = [733002260, 254875457, 178558520, 353221028, 670746645, 10721678, 10674480, 10332995,
				10470047, 10250395 ]
        for  i in range(len(items_to_search)):
            item = items_to_search[i]
            averageSearchTime = 0
            start_time = time.time_ns()
            searched_item = ht.get(item) + 1
            end_time = time.time_ns() - start_time

            if i >= len(items_to_search) // 2:
                daata_ = "Searching unsuccessfull for " + str(item) + " element into hash table in " + str(searched_item) + "access and require  " + str(end_time) + "   Nano Seconds"
                f.write(daata_)
                f.write("\n")
                print(daata_)
            else:
                daata_  = "Searching successfull " + str(item) + " element into hash table in " + str(searched_item) + " access and require " +  str(end_time) + "   Nano Seconds"
                f.write(daata_)
                f.write("\n")
                print(daata_)
           
            print("Search time: " , end_time, " ns", "\n")
