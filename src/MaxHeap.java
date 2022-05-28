
import java.util.*;

public class MaxHeap<K, V> {

List<HeapEntry<K,V>> entries;
int capacity;
int heapSize = 0;
Comparator comparator;

    public MaxHeap(int capacity, Comparator comparator){
        // Constructor for the max heap
       
		this.entries = new ArrayList<HeapEntry<K,V>>();
		this.capacity = capacity;
		this.comparator = comparator;

    }

	// Defining some helper methods 
	
	// Get parent
	private int getParentIndex(int index) { 
		return (index - 1) / 2; 
	}
 
    // Get left child index
    private int getLeftChildIndex(int index) { 
		return (2 * index) + 1; 
	}
 
    // Get right child index
    private int getRightChildIndex(int index){ 
		return (2 * index) + 2; 
	}

	// swapping function (defining it just for aesthetic)
	private void swap(int index1, int index2) {
        Collections.swap(entries, index1, index2);
    }

	// function that will make the tree satisfy max heap property 
	// should only take the highest input, index 0
	private void bubbleDown(int index) {
        
		// checking if its a leaf so i can end early
		if (index <= heapSize && index > (heapSize / 2)) {
            return; 
		}

		// keys to compare
		try {
			K currentKey = entries.get(index).getKey();
			K leftKey = entries.get(getLeftChildIndex(index)).getKey();
			K rightKey = entries.get(getRightChildIndex(index)).getKey();
		} catch(Exception IndexOutOfBoundsException) {
			return;
		}

		// pain, fix later
		K currentKey = entries.get(index).getKey();
		K leftKey = entries.get(getLeftChildIndex(index)).getKey();
		K rightKey = entries.get(getRightChildIndex(index)).getKey();
		
		// how compare works:
		// compare(Object obj1, Object obj2)
		// if obj1 > obj2, return positive value
		// if obj1 < obj2, return negative value
		// if obj1 = obj2, return 0

		// current key compared to the left child key (is left greater than currnet)
		int leftCurrentCompared = comparator.compare(leftKey, currentKey);
		boolean leftGreaterThanCurrent = leftCurrentCompared > 0;

		// current key compared to the right child key  (is right greater than current)
		int rightCurrentCompared = comparator.compare(rightKey, currentKey);
		boolean rightGreaterThanCurrent = rightCurrentCompared > 0;

		// left key compared to right child key  key (is left greater than right)
		int leftRightCompared = comparator.compare(leftKey, rightKey);
		boolean leftGreaterThanRight = leftRightCompared > 0;

		// does the current parent need to be swapped?
        if (leftGreaterThanCurrent|| rightGreaterThanCurrent) {

			// if the left child is greater than the right child,
			// then I will swap the left with the parent
            if (leftGreaterThanRight) {
                swap(index, getLeftChildIndex(index));
                bubbleDown(getLeftChildIndex(index));
			} 
			
			// otherwise, swap the right with the parent 
			// (which is what should happen if right=left)
			else {
                swap(index, getRightChildIndex(index));
                bubbleDown(getRightChildIndex(index));
            }

        }

    }

	// should take the lowest input! such as the size of entries.
	private void bubbleUp(int index) {

		// check if the index is 0 so i can end early (cant go up more than 0)
		if(index == 0) {
			return;
		}

		// not sure if this is entirely necessary
		int currentIndex = index;

		// keys to compare
		K currentKey = entries.get(currentIndex).getKey();
		K parentKey = entries.get(getParentIndex(currentIndex)).getKey();


		// current key compared to parent key key (is current greater than parent)
		int currentParentCompared = comparator.compare(currentKey, parentKey);
		boolean currentGreaterThanParent = currentParentCompared > 0;

		// swap (FIX THIS INTO WHILE LOOP IF NEEDED)
		if(currentGreaterThanParent) {
			//int tempParentIndex = getParentIndex(currentIndex);
			swap(currentIndex, getParentIndex(currentIndex));

			// currentIndex = tempParentIndex;

			bubbleUp(getParentIndex(currentIndex));
		}

		return;

	}

	public void add(K key, V value){
	    // Method to add the key value pair in the heap, remember to satisfy max heap Property
		HeapEntry<K,V> newEntry = new HeapEntry(key, value);

		if(heapSize < capacity) {
			entries.add(newEntry);
		} 
		// NOT SURE IF THIS IS PROPER BEHAVIOR
		// returning and adding nothing if its at full capacity
		else {return;}

		// FIX THIS IF NEEDED
		bubbleUp(heapSize);

		heapSize++;
	}

	public HeapEntry<K,V> peek() {

		// just in case, i'm going to bubble down...
		if (entries.get(0) != null) {
			bubbleDown(0); 
		}

		return entries.get(0);

	}
	
	public HeapEntry<K,V> remove() {
		//Method to remove the max element in the heap, remember to satisfy max heap Property
		HeapEntry<K,V> maxEntry = peek();
		HeapEntry<K,V> minEntry = entries.get(entries.size() -1);

		//ending early if it's already empty
		if (heapSize == 0) {
			return maxEntry;
		}

		// size goes down when remove
		heapSize--;

		entries.remove(0);

		// i will be re-adding the smallest so I can bubbleDown effectively, 
		// but i must remove it first
		entries.remove(entries.size() -1);

		entries.add(0,minEntry);

		bubbleDown(0);

		return maxEntry;
	}



	// for easier testing
	public void print() {
       
      for(int i=0;i<heapSize/2;i++){
 
            System.out.print("Parent Node : " + entries.get(i).getKey() );

			entries.get(getLeftChildIndex(i));
             
			System.out.print( " Left Child Node: " + entries.get(getLeftChildIndex(i)).getKey());
		
			System.out.print(" Right Child Node: "+ entries.get(getRightChildIndex(i)).getKey());
			
			System.out.println(); //for new line
             
        }
           
    }

	
}

class HeapEntry<K, V> implements DefaultMap.Entry<K,V>{
	K key;
	V value;

	HeapEntry(K key, V value){
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return key;
	}
	
	public V getValue() {
		return value;
	}

	public void setValue(V value){
		this.value = value;
	}



// // DEL LATER
// public static void main(String[] arg) {
// 	// need to test: 
// 	// when capacity is more
// 	// when given null values
// 	// when all vals equal

// 	// also use PA8 tester to further test maxheap


// 	System.out.println("The Max Heap is ");

// 	Comparator<Integer> comparator = new Comparator<Integer>() {
// 	@Override
// 	public int compare(Integer o1, Integer o2) {
// 		return Integer.compare(o1, o2);
// 		}
// 	};

// 	MaxHeap maxHeap = new MaxHeap(10, comparator);

// 	// Inserting nodes
// 	// Custom inputs
// 	maxHeap.add(6,6);
// 	maxHeap.add(3,3);
// 	maxHeap.add(4,4);
// 	maxHeap.add(4,4);
// 	maxHeap.add(8,8);
// 	maxHeap.add(5,5);
// 	maxHeap.add(10,10);

// 	// Calling maxHeap() as defined above
// 	maxHeap.print();

// 	// Print and display the maximum value in heap
// 	System.out.println("The max val is "
// 					   + maxHeap.remove().getKey());

// 	}
}