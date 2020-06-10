/*
 * JungBok Cho
 * War Card Game in Java
 */

/**
 * This is a program to create a linked-list based generic queue.
 * 
 * @author  JungBok Cho
 * @version 1.0
 */
public class RenderQueue<T> {
	
	private Node front;	// Head of the List
	private Node rear;	// Last element on the list
	
	
	/**
	 * The Node class stores a list element
	 * and a reference to the next node.
	 */
	private class Node {
		
		public T value;		// Value of a list element
		public Node next;	// Next node in the list
		public Node prev;	// Previous element in the list
		
		/**
		 * Constructor of Node class
		 * 
		 * @param val	The element to store in the node.
		 * @param n	The reference to the successor node.
		 * @param p	The reference to the predecessor node.
		 */
		public Node(T val, Node n, Node p) {
			value = val;
			next = n;
			prev = p;
		}	
		
		/**
		 * Constructor of Node class
		 * 
		 * @param val	The element to store in the node.
		 */
		public Node(T val) {
			this(val, null, null);
		}
		
	}
	
	
	/**
	 * Constructor of RenderQueue class
	 */
	public RenderQueue() {
		front = null;
		rear = null;
	}

	
	/**
	 * Check to see if the queue is empty.
	 * 
	 * @return front == null  Return true if queue is empty, otherwise false.
	 */
	public boolean empty() {
		return front == null;
	}
	
	
	/**
	 * Get the size of queue
	 * 
	 * @return sizeNum  Return the size of queue
	 */
	public int size() {
		int sizeNum = 0;
		Node checking = front;
		while(checking != null) {
			sizeNum++;
			checking = checking.next;
		}
		return sizeNum;
	}
	
	
	/**
	 * Make a deep copy of the queue
	 * 
	 * @return copyObj  Return the new queue you copied
	 */
	public RenderQueue<T> copy() {
		RenderQueue<T> copyObj = new RenderQueue<T>();
		Node temp = front;
		while(temp != null) {
			copyObj.enqueue(temp.value);
			temp = temp.next;
		}
		return copyObj;
	}
	
	
	/**
	 * Adds a value to the queue.
	 * 
	 * @param val  The value to be added to the queue.
	 */
	public void enqueue(T val) {
		if (rear != null) {
			rear.next = new Node(val, null, rear);
			rear = rear.next;
		} else {
			rear = new Node(val, null, null);
			front = rear;
		}
	}
	

	/**
	 * Returns and removes the item at the front of the queue.
	 * 
	 * @return value  Return the item at front of queue.
	 * @exception IllegalArgumentException when the queue is empty.
	 */
	public T dequeue() {
		if (empty()) {
			throw new IllegalArgumentException("The queue is empty!");
		} else {
			T value = front.value;
			front = front.next;
			if (front == null) {
				rear = null;
			}	
			//front.prev = null;
			return value;
		}
	}
	
	
	/**
	 * Returns value at the front of the queue.
	 * 
	 * @return front.value  Return the item at front of queue.
	 * @exception IllegalArgumentException when the queue is empty
	 */
	public T peek() {
		if (empty()) {
			throw new IllegalArgumentException("The queue is empty!");
		} else {
			return front.value;
		}
	}
	
	
	/**
	 * Enqueue new elements from a temporary queue onto the original queue
	 * 
	 * @param temp	 Other queue with new elements
	 */
	public void append(RenderQueue<T> temp) {
		RenderQueue<T> newTemp = temp.copy();
		Node appendTemp = newTemp.front;
		
		while(appendTemp != null) {	
			if (rear != null) {
				rear.next = new Node(appendTemp.value, null, rear);
				rear = rear.next;
			} else {
				rear = new Node(appendTemp.value, null, null);
				front = rear;
			}
			appendTemp = appendTemp.next;
		}
	}
	

	/**
	 * Represent the elements in the queue
	 * 
	 * @return str	 Return the elements in the queue
	 */
	public String toString() {
		String str = "";

		Node temp = front;
		while (temp != null) {
			str += " " + temp.value + " |";
			temp = temp.next;
		}
		str = "|" + str;
		return str;
	}
	
	
	/**
	 * Check if two queues are equal
	 * 
	 * @param checkThis   A queue to check
	 * @return   Return true if two queues are equal, otherwise return false
	 */
	public boolean equals(RenderQueue<T> checkThis){
		Node temp = front;
		Node tempCheckThis = checkThis.front;
				
		if(size() != checkThis.size()) {
			return false;
		}
		
		while(temp!= null) {
			if(!((temp.value).equals(tempCheckThis.value))) {
				return false;
			}
			temp = temp.next;
			tempCheckThis = tempCheckThis.next;
		}
		return true;
	}
	
}
