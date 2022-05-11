import time

def merge_sort(data_storage):
    if len(data_storage)>1:
        mid = len(data_storage)//2
        left_side = data_storage[:mid]
        right_side = data_storage[mid:]

        merge_sort(left_side)
        merge_sort(right_side)
        i=j=k=0
        while i<len(left_side) and j<len(right_side):
            if left_side[i] < right_side[j]:
                data_storage[k] = left_side[i]
                i = i+1
            else:
                data_storage[k] = right_side[j]
                j = j+1
            k = k+1 

        while i< len(left_side):
            data_storage[k] = left_side[i]
            i = i+1
            k = k+1

        while j<len(right_side):
            data_storage[k] = right_side[j]
            j = j+1
            k = k+1


def main():
    start_time = time.time()
    data_storage = []
    data_file = open('rand.txt' , 'r')
    lines = data_file.readlines()
    count = 0
    for data in lines:
        count = count + 1
        data_storage.append(data)
    print(" BeFORE Total data:" , len(data_storage))
    merge_sort(data_storage)
    print(" After Total data:" , len(data_storage))
    
    end_time = time.time() - start_time

    print("after sorting:",data_storage[0],data_storage[1],data_storage[2])
    print(end_time)

    with open('merge_sort_output_python.txt' , 'w') as f:
        f.write("**********************Stats*********************")
        data_to_write  = 'Time :' + str(end_time)+'  second'
        f.write("\n")
        f.write(data_to_write)
        f.write("\n")
        f.write("**********************Output*********************")
        f.write("\n")
        d = 1
        ada = data_storage[998675]
        print(ada)
        for data in data_storage:
            f.write(data)
     
        print("counter value",d)
if __name__ == '__main__':
    main()
