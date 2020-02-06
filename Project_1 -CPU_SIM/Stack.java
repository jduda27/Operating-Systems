  /** Operating Systems Project 1
*   A stack class that can be accessed by other classes securly that can use 0x3FF memory
*   space.
*   @Author Joshua Duda
*   @Date 2/5/2020
*/
public class Stack {
	final int MAX_STACK = 0x3FF; // maximum size of stack
	private String registerItems[]; 
	public int top;

	//Here we have a constructor that initializes our stack to the default parameters
	public Stack() {
			registerItems = new String[MAX_STACK];
			top = 0x0;
		}

	/** isEmpty() method - Checks to see if the stack is empty
	* @preconditions none.
	* @postconditions Returns a boolean true if stack is empty or false if not.
	*/
	public boolean isEmpty() {
		return top < 0;
	}
	
	/** isFull() method - Checks to see if the stack is full
	* @preconditions none.
	* @postconditions Returns a boolean true if stack is full or false if not.
	*/
	public boolean isFull() {
		return top == MAX_STACK - 1;
	} // end isFull

	/** push() method - Adds a item to the top of the stack.
	* @preconditions String item - a valid string object that is being added to the stack.
	* @postconditions If the stack is not full we put the inputed item into the stack.
	*/
	public void push(String item) {
		if (!isFull()) {
			registerItems[++top] = item;
		}
	}
	/** pop() method - Removes a item to the top of the stack and returns the item.
	* @preconditions none.
	* @postconditions If the stack is not empty we return the string item that is being 
	* stored at the top and remove it.
	*/
	public String pop() {
		if (!isEmpty()){
			return registerItems[top--];
		}
	}
	
	/** peek() method - Returns the item that is at the top of the stack
	* @preconditions none.
	* @postconditions We return the item that is at the top of the stack.
	*/
	public String peek() {
			return registerItems[top];
	}
}
