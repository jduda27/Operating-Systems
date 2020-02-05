
public class Stack {
	final int MAX_STACK = 0x3FF; // maximum size of stack
	private String items[];
	public int top;

	public Stack() {
			items = new String[MAX_STACK];
			top = 0x0;
		} // end default constructor

	public boolean isEmpty() {
		return top < 0;
	} // end isEmpty

	public boolean isFull() {
		return top == MAX_STACK - 1;
	} // end isFull

	public void push(String iR) {
		if (!isFull()) {
			items[++top] = iR;
		}
	} // end push

	public void popAll() {
		items = new String[MAX_STACK];
		top = -1;
	} // end popAll

	public String pop() {

			return items[top--];
	} // end pop

	public String peek() {
			return items[top];
		
	} // end peek
} // end StackArrayBased
