import time
import psutil

class node:  
    def __init__(self , value=None):
        self.value = value
        self.left_child = None
        self.right_child = None 
            
class binary_search_tree:

    def __init__(self):
        self.root = None
        self.count = 0
    
    def counter(self):
        self.count = self.count + 1
        
    def insert(self, value):
        if self.root == None:
            self.root = node(value)
        else:
            self.insertion(value , self.root)
    
    def insertion(self , value , current_node):
        if value < current_node.value:
            if current_node.left_child == None:
                current_node.left_child = node(value)
            else:
                self.insertion(value, current_node.left_child)
        else:
            if current_node.right_child == None:
                current_node.right_child = node(value)
            else:
                self.insertion(value , current_node.right_child)
            
    def search(self , value):
        if self.root != None:
            return self._search_(value , self.root , count=1)
        else:
            return False
        
    def _search_(self, value , current_node , count):
        self.counter()
        if value == current_node.value:
            return True
        elif value < current_node.value and current_node.left_child != None:
            return self._search_(value , current_node.left_child , count)
        elif value > current_node.value and current_node.right_child != None:
            return self._search_(value , current_node.right_child , count)
        
        return False
    

def get_level_util(node , value , level):
    if(node == None):
        return 0
    if(node.value == value):
        return level 
    downlevel = get_level_util(node.left_child, value , level + 1)
    
    if(downlevel != 0):
        return downlevel
    downlevel = get_level_util(node.right_child , value , level + 1)
    
    return downlevel

   
data_file = open('rand.txt' , 'r')
lines = data_file.readlines()

start_time = time.process_time()
data_storage = []
for data in lines:
    data_storage.append(data)
    
# new_data_storage = data_storage[0:50]

tree = binary_search_tree()
root = node(838081090)

for data in data_storage:
    tree.insert(int(data))
print("Data inserted ", len(data_storage))
end_time = time.process_time() - start_time  
space = psutil.Process().memory_info().rss / (1024 * 1024)
data_to_write  = 'Insertion Time :' + str(end_time)+'Second'
space_to_write = "Memory Consumed: " + str(space) + 'MB'
insert_text_ = "Inserted " +  str(len(data_storage)) +  " elemenets into BST."

with open('Binary_Search_tree_python_output.txt', 'a') as f:
    f.write(insert_text_)
    f.write("\n")
    f.write(data_to_write)
    f.write("\n")
    f.write(space_to_write)
    f.write("\n")
    f.write("---------------------------------------------------------------------------------")
    f.write("\n")
data_to_test = [733002260, 254875457, 178558520, 353221028, 670746645, 10721678, 10674480, 10332995,10470047, 10250395]
for data in data_to_test:
    i = 1
    tree.count = 0 
    avg_search_time = 0

    search_time = time.time_ns()
    #print(search_time)
    #print("Start Time:" , time.time_ns())
    d = tree.search(data)
    #print("end_time",time.time_ns())
    print("search_time" , search_time)
    print("end time" , time.time_ns())
    diff =  time.time_ns() - search_time

    #print(time.time_ns()-  search_time )
    #search_complete = time.time_ns()- search_time
    #print(search_complete)
    with open('Binary_Search_tree_python_output.txt', 'a') as f:
        if d == False:
            daata_ = "Searching unsuccessfull for " + str(data) + " element into binary search tree in " + str(tree.count) + " access and require  " + str(diff) + "   Nano Seconds"
            f.write(daata_)
            f.write("\n")
            print(daata_)
        else:
            daata_  = "Searching successfull for  " + str(data) + " element into binary search tree in " + str(tree.count) + " access and require " +  str(diff) + "   Nano Seconds"
            f.write(daata_)
            f.write("\n")
            print(daata_)
           