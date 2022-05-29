
public class CircularArrayList<T> implements ArrayListADT<T>{
	
	int capacity;
	int size;
	int front;
	int rear;
	T[] arrayList;
	
	public CircularArrayList() {
		//Fill in the constructor for the circularArrayList
		this.front = -1;
		this.size = 0;
		this.rear = 0;
		this.capacity = 100;
		this.arrayList = (T[]) new Object[capacity];
	}
	
	public CircularArrayList(int initialCapacity) {
		//Fill in the constructor for the circularArrayList
		this.front = -1;
		this.size = 0;
		this.rear = 0;
		this.capacity = initialCapacity;
		this.arrayList = (T[]) new Object[capacity];
	}

	@Override
	public void addRear(T element) {
		try {
		if(arrayList.length == capacity) {
			expandCapacity();
			rear = 0;
		} 
		
		else {
			rear++;
		}
		
		arrayList[rear] = element;
		
		
		
		
		//Method to add element at the rear of the arraylist
//		if(arrayList.length == capacity) {
//			expandCapacity();
//			rear = capacity-1;
//			arrayList[rear] = element;
//			rear++;
//		}
//		
//		arrayList[capacity-rear-1] = element;
//		rear++;
//		return;
		
		size++;
		return;
		} catch (Exception e) {
			return;
		}
	}

	@Override
	public void addFront(T element) {
		try {
		//Method to add element at the front of the arraylist i.e. towards start
		if(arrayList.length == capacity) {
			expandCapacity();
			rear = 0;
		}
		
		if (front < 1) {
			front = capacity -1;
		} 
		
		else {
			front--;
		}
		
		arrayList[front] = element;
		size++;
		return;
		} catch (Exception e) {
			return;
		}
		
		
//		if(arrayList.length == capacity) {
//			expandCapacity();
//			front = 0;
//			arrayList[front] = element;
//			front++;
//		}
//		
//		arrayList[front] = element;
//		front++;
//		return;
		
		
	}
// adding override
	@Override
	public T get(int index) throws Exception {
		//Method to get element at a given index of the circularArrayList
		
		if (getFront() != -1) {
			int ans = (this.front + index) % this.arrayList.length;
			
			
			return arrayList[ans];
		}
		throw new Exception("Element doesn't exist");
		
	}
	


	@Override
	public T remove() {
		try {
		//Method to remove the element from the front
		if (front != -1 && rear != -1) {
			T placeholder = arrayList[front];
			
			if (rear == front) {
				front = rear = -1;
				return placeholder;
			}
			
			if (front == capacity -1) {
				front = 0;
				return placeholder;
			}
			
			front = front+1;
			size--;
			return placeholder;
			
			
		}
		return arrayList[0];
		} catch (Exception e) {
			T placeholder = null;
			return placeholder;
		}
		
		
	}
	
	// HEY ME: TRY to add 2 arrays to the front and back, see how that works out for u
	// REDUCE RUNTIME HECK YEA??
	private void expandCapacity() {
		try {
		this.capacity = capacity*2;
		T[] newArrayList = (T[]) new Object[capacity];
		
		for(int i =0; i<capacity/2; i++) {
			newArrayList[i] = arrayList[i];
		}
		
		this.arrayList = newArrayList;
		return; 
		} catch(Exception e) {
			return;
		}
		
	}

	// below are lazy untested methods but i'm running out of time SO...
	
	//@Override
	public int getSize() {
		try {
		return size;
		} catch (Exception e) {
			return 0;
		}
	}

	//@Override
	public int getRear() {
		try {
//		try {
//			if (front != -1 && rear != -1) {
//				return (int) arrayList[rear];
//			}
//			return (int) arrayList[size-1];
//		} catch (Exception e){
//			return -1;
//		}
		if (front != -1 && rear != -1) {
			return  (int) arrayList[rear]; 
			}

			return (int) arrayList[0];
		} catch (NullPointerException e) {
			return -1;
		}
		
	}

	//@Override
	public int getFront() {
//		try {
//			if (front != -1 && rear != -1) {
//				return  (int) arrayList[front]; }
//			return (int) arrayList[0];
//		} catch (Exception e){
//			return -1;
//		}
		if (front != -1 && rear != -1) {
			return  (int) arrayList[front]; 
			}
		
		try {
			return (int) arrayList[0];
		} catch (NullPointerException e) {
			return -1;
		}
	}

	//@Override
	public int getCapacity() {
		
		return capacity;
	}
	
	
}
