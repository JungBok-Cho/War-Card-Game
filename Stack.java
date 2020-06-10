/*
 * JungBok Cho
 * War Card Game in Java
 */

/**
 * This is a program to create an array based generic stack.
 * 
 * @author  JungBok Cho
 * @version 1.0
 */
public class Stack<T> {
	
	private T [] stackList;    // To hold stack elements
	private int top;   	   // Stack top pointer

	/**
	 * Constructor of Stack class
	 * 
	 * @param capcacity   The capacity of the stack
	 */
	public Stack (int capacity) {
		stackList = (T[]) new Object [capacity];
		top = 0;
	}
	
	
	/**
	 * Return the size of the stack
	 * 
	 * @return top  Return the size of the stack
	 */
	public int size() {
		return top;
	}

	
	/**
    	 * Checks for an empty stack
    	 * 
    	 * @return top == 0   Return true if the stack is empty, otherwise false.
	 */
	public boolean empty() { 
		return top == 0; 
	}

	
	/**
	 * Pushes a value onto the stack.
	 * 
	 * @param value  The value to push onto the stack
	 * @exception IllegalArgumentException	when the stack is full
	 */
	public void push(T value) {
		if (top == stackList.length) {
			throw new IllegalArgumentException("The stack is Full!");
		} else {
			stackList[top] = value;
			top++;   
		}
	}
	

	/** 
    	 * Pops a value off the stack
    	 * 
    	 * @return retVal   Return the value popped
	 * @exception IllegalArgumentException when the stack is empty
	 */
	public T pop() {
		if (empty()) {
			throw new IllegalArgumentException("The stack is empty!");
		} else {
			T retVal = stackList[top - 1];
			stackList[top - 1] = null;
			top--;
			return retVal;
		}
	}

	
	/** 
     	 * Returns the value at the top of the stack.
    	 * 
    	 * @return stackList[top - 1]  Returns the value at the top of the stack.
	 * @exception IllegalArgumentException when the stack is empty
	 */
	public T peek() {
		if (empty()) {
			throw new IllegalArgumentException("The stack is empty!");
		} else {
			return stackList[top - 1];
		}
	}
	
	
	/**
	 * Make a deep copy of the stack
	 * 
	 * @return copyObj   Return the copied object
	 */
	public Stack<T> copy() {
		Stack<T> copyObj = new Stack<T>(top);
		for(int i = 0; i < top; i++) {
			copyObj.push(stackList[i]);
		}
		return copyObj;
	}
	
	
	/**
	 * Check if two stacks are equal
	 * 
	 * @param checkThis   A stack to check
	 * @return   Return true if two stacks are equal, otherwise return false
	 */
	public boolean equals(Stack<T> checkThis) {
		if(top != checkThis.top) {
			return false;
		}
		for(int i = 0; i < top; i++) {
			if(!(stackList[i].equals(checkThis.stackList[i]))) {
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * Represent the elements in the stack
	 * 
	 * @return str  Return the elements in the stack
	 */
	public String toString() {
		String str = "";
		for(int i = top - 1; i >= 0; i--) {
			if(stackList[i] == null) {
				return str;
			}
			str += stackList[i] + " ";
		}
		return str;
	}
	
}
