import time
import psutil

def partition(array_data, l, h):
  pivot = array_data[h]
  i = l - 1

  for j in range(l, h):
    if array_data[j] <= pivot:
      i = i + 1
      (array_data[i], array_data[j]) = (array_data[j], array_data[i])
  (array_data[i + 1], array_data[h]) = (array_data[h], array_data[i + 1])
  return i + 1

def quick_sort(array_data, l, h):
  if l < h:
    chunk = partition(array_data, l, h)
    quick_sort(array_data, l, chunk - 1)
    quick_sort(array_data, chunk + 1, h)
 

def main():
  start_time = time.time()
  data_storage = []
  data_file  = open('rand.txt' , 'r')
  lines = data_file.readlines()
  count =0
  for data in lines:
    count = count+ 1
    data_storage.append(data)
  
  quick_sort(data_storage, 0, len(data_storage)-1)
  #print("1st elm", data_storage[0],data_storage[1],data_storage[2],data_storage[3] )
  end_time = time.time() - start_time
  space = psutil.Process().memory_info().rss / (1024 * 1024)
  print(space)
  with open('quick_sort_output_python.txt' , 'w') as f:
    
    f.write("**********************Stats*********************")
    data_to_write  = 'Time :' + str(end_time)+'Second'
    space_to_write = "Memory Consumption: " + str(space) + 'MB'
    f.write("\n")
    f.write(data_to_write)
    f.write("\n")
    f.write(space_to_write)
    f.write("\n")
    f.write("**********************Output*********************")
    f.write("\n")
    for data in data_storage:
      f.write(data)
if __name__ == '__main__':
	main()