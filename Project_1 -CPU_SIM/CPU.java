/** Operating Systems Project 1
*   A CPU class that works with a stack and a memory object to execute a input file
*   producing an output file.
*   @Author Joshua Duda
*   @Date 2/5/2020
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class CPU {

	//bellow are all of the private variables the CPU needs to operate
	private int AC;
	private int REG;
	private Boolean isRunning;
	private Boolean reset;
	private String PC;
	private String IR;
	private int rtn;
	private int count;

	/**
	 * Main Method This method runs our program to be executed.
	 * 
	 */
	public static void main(String[] args) throws IOException {
		Memory mem = new Memory();
		CPU cp = new CPU();

		Stack reg = new Stack();

		// this is our run method that takes in a valid file input to be executed.
		cp.run("joshua_duda_input.txt", mem, reg);
	}

	// Initializing our default values for the different registers in our CPU
	public CPU() {
		AC = 0x0;
		PC = "0";
		IR = "0";
		REG = 0x0;
		isRunning = true;
		reset = false;
		rtn = 0;
		count = 0;
	}

	/**
	 * run() Method - This method takes in a valid String "input" for a file name,a
	 * Memory object mem that has designated memory slots, and a Stack for registers
	 * and then processes the information through our CPU and outputs the
	 * information to an output file
	 * 
	 * @Preconditions: String input - this needs to be valid file name otherwise it
	 *                 throws a IOException; Memory mem - this needs to be a valid
	 *                 memory object to read and write to; Stack registers - this
	 *                 needs to be a valid stack object to store register
	 *                 information when entering new subroutines
	 * @postconditions: This program creates or overwrites a file named
	 *                  "joshua_duda_output.txt" with a breakdown of registers,
	 *                  memory, and stack data that is being stored at the end of
	 *                  each subroutine and main program.
	 */
	public void run(String input, Memory mem, Stack registers) throws IOException {

		// Here we are creating a buffered reader for the file entered by the user
		BufferedReader br = new BufferedReader(new FileReader(input));
		Scanner line = new Scanner(br);

		// This is our buffered writer that is making a new file that we can write text
		// to.
		BufferedWriter bw = new BufferedWriter(new FileWriter("joshua_duda_output.txt"));

		// here we are declaring a blank ArrayList of type String to store the lines
		// from the program
		ArrayList<String> aList = new ArrayList<String>();

		// Now, until there are no more lines we add the next line to the array list
		while (line.hasNext()) {
			String current = line.nextLine();
			if (!current.contains("==")) {
				aList.add(current);
			}
		}

		// Now we want to check for memory within our program so we will use the
		// checkMem() method to look for any declarations for our memory locations
		for (int i = 0; i < aList.size(); i++) {
			checkMem(aList.get(i), mem);
		}

		// we need to find the first PC to start the program so we look at the first
		// line of code and see the memory location we begin with
		String[] first = aList.get(0).split(" +");
		this.PC = Integer.toHexString(Integer.parseInt(first[1], 16));

		// from here we run through every line and use the execute method to interpret
		// the code
		for (int i = 0; i < aList.size(); i++) {
			// when reset is true we go back to the first line so we don't miss any lines
			if (reset) {
				i = 0;
				reset = false;
			}

			// when isRunning is true we use our execute method to determine what to do with
			// each line of code
			if (isRunning) {
				this.execute(aList.get(i), mem, registers, bw);
			}
		}

		// We have finished running the program so we now can close the output file from
		// writing
		bw.close();
	}

	/**
	 * 
	 * @preconditions: String line - We read in valid string that represents a
	 *                 single line of code to be checked for a memory declaration;
	 *                 Memory mem - This is a valid memory object that contains
	 *                 dedicated memory locations to be read and written to.
	 * @postcondition: The program sets any designated memory to the correct
	 *                 location in memory
	 */
	public void checkMem(String line, Memory mem) {
		// first we split our code at any spaces so we can look at each piece of the
		// code separate
		String[] code = line.split(" +");

		// we know opCode and memory locations are stored in the 3rd position and that
		// we can split the first bit off to represent out opCode and the remaining
		// part is the memory location or hex value we'd like to use
		String opCode = code[2].substring(0, 1);
		String num = code[2].substring(1, 4);

		// if the opCode is 0 it is a memory declaration so we will see what that memory
		// location is which is the 2nd position of our code syntax and we set the value
		// in hex at that memory location.
		if (opCode.equals("0")) {
			mem.memSet(code[1], Integer.parseInt(num, 16));
		}
	}

	/**
	 * writeData() Method - This method prints to a file the registers and stack
	 * when called
	 * 
	 * @preconditions Memory mem - this is a valid Memory object that contains
	 *                locations to save data to; BufferedWritter bw - this is a
	 *                valid BufferedWritter object that contains a place to write
	 *                information to; Stack reg - This is a valid stack object that
	 *                contains register information during subroutines
	 * @postcondition The method writes the register/memory and stack status to a
	 *                designated file.
	 */
	public void writeData(Memory mem, BufferedWriter bw, Stack reg) throws IOException {
		// below we write all of the current register and memory location information to
		// a file. All values are in hex except for the count of instructions executed.
		bw.write("=============Registers & Memory Status=============\n");
		bw.write("Accumulator = " + Integer.toHexString(AC).toUpperCase() + "\n");
		bw.write("Register = " + Integer.toHexString(REG).toUpperCase() + "\n");
		bw.write("PC = " + PC + "\n");
		bw.write("IR = " + IR + "\n");
		bw.write("Memory 940: " + Integer.toHexString(mem.getMem3AC()).toUpperCase() + "\n");
		bw.write("Memory 941: " + Integer.toHexString(mem.getMem3AD()).toUpperCase() + "\n");
		bw.write("Memory 942: " + Integer.toHexString(mem.getMem3AE()).toUpperCase() + "\n");
		bw.write("Number of instructions executed: " + count + "\n");

		// below we check if the register stack is empty if it is not we list the data
		// in order and assign the values back to their proper places
		bw.write("=============Stack Status=============\n");
		if (!reg.isEmpty() && reg.peek() != null) {
			this.REG = Integer.parseInt(reg.pop());
			bw.write("Stack contents at " + Integer.toHexString(0x3FF - reg.top).toUpperCase() + " = " + REG + "\n");
			this.AC = Integer.parseInt(reg.pop());
			bw.write("Stack contents at " + Integer.toHexString(0x3FF - reg.top).toUpperCase() + " = " + AC + "\n");
			this.IR = reg.pop();
			bw.write("Stack contents at " + Integer.toHexString(0x3FF - reg.top).toUpperCase() + " = " + IR + "\n");
			PC = reg.pop();
			bw.write("Stack contents at " + Integer.toHexString(0x3FF - reg.top).toUpperCase() + " = " + PC + "\n");
		} else {
			// if the stack is null or empty we write that there is no data in the stack.
			bw.write("No Data in Stack!\n");
		}
	}

	/**
	 * The execute() Method This method takes in a line of code and then interprets
	 * the actions required
	 * 
	 * @preconditions String line - A valid String that is a line of code to be
	 *                interpreted; Memory mem - A valid Memory object that contains
	 *                memory locations that can be read and written to; Stack reg -
	 *                this is a valid Stack object that can store register
	 *                information during subroutines; BufferedWriter bw - This is a
	 *                valid BufferedWriter object that contains a file to be written
	 *                to.
	 * @postcondition All lines of code are executed and actions are performed on
	 *                the registers and memory locations
	 */
	public void execute(String line, Memory mem, Stack reg, BufferedWriter bw) throws IOException {

		// this is splitting the code into a String array that splits the
		// information at any white space
		String[] code = line.split(" +");

		// This for loop looks at each piece of the code and determines what to do with
		// it
		for (int i = 1; i < code.length - 1; i++) {

			// if the code segment contains a ; we know that anything including and after
			// the symbol is a comment and can be ignored
			if (code[i].contains(";")) {
				// we set the string array location to this to be end the line
				i = code.length;

				// since the syntax has the memory location in the 2nd position we can check
				// with the PC to see if we are reading the correct next line in the program 
			} else if (i == 1) {
				// if the program counter is equal to the current memory location (in HEX) we finish 
				// executing the line and we increase the PC by one for the next instruction;
				if (code[i].equals(PC)) {
					PC = Integer.toHexString(Integer.parseInt(PC, 16) + 1).toUpperCase();
				} else if (Integer.parseInt(code[i], 16) < Integer.parseInt(PC, 16)) {
					i = code.length; // if we are not equal we skip the rest of the code and
							 // end the line so we can move on to the next line
				}

				// since the proper syntax requires the instruction to be in the 3rd position 
				// and we catch all comments first, we can take the current memory location's 
				// instruction and put it into the IR and execute it.
			} else {
				count++; //we increase the count of executed commands by one
				
				//we set our IR to the current line instruction code.
				this.IR = code[2];
				//Now we split up the IR into the two parts, OPcode and Memory Location so we 
				//have easily execute values
				String opCode = this.IR.substring(0, 1);
				String memLoc = this.IR.substring(1, 4);

				// Here we check the OPCodes and execute the proper procedure for that OPCode.
				// Note we skip opCode 0 because we already have tested for that case and there
				// should be no more left in our code
				if (opCode.equals("1")) { // load AC from MemLoc
					this.AC = mem.memGet(memLoc);
				} else if (opCode.equals("2")) { // Store AC from MemLoc
					mem.memSet(memLoc, this.AC);
				} else if (opCode.equals("3")) { // load AC from REG
					this.AC = this.REG;
				} else if (opCode.equals("4")) { // Store AC to REG
					this.REG = this.AC;
				} else if (opCode.equals("5")) { // Add to AC from MemLoc
					this.AC = this.AC + mem.memGet(memLoc);
				} else if (opCode.equals("6")) { // Load REG with a value
					this.REG = Integer.parseInt(memLoc, 16);
				} else if (opCode.equals("7")) { // Add REG to AC
					this.AC += this.REG;
				} else if (opCode.equals("8")) { // Multiply REG to AC
					this.AC *= this.REG;
				} else if (opCode.equals("9")) { // Subtract REG from AC
					this.AC -= this.REG;
				} else if (opCode.equals("A")) { // Divide AC by REG (integer division)
					this.AC = this.AC / this.REG;
				} else if (opCode.equals("B")) { // Jump to a Subroutine starting at memLoc
					this.rtn++; // We increase the amount of subroutines that have been run.
					
					//Now we push the register to the stack in the correct order PC,IR,AC,REG
					reg.push(this.PC);
					reg.push(this.IR);
					reg.push(Integer.toString(this.AC));
					reg.push(Integer.toString(this.REG));
					
					// Now the stack has been pushed we take the target PC and set it to the
					// current PC that we are wanting to start at.
					this.PC = memLoc;
					
				} else if (opCode.equals("C")) { // Return from subroutine
					// We set the boolean reset to be true so that we read everyline again so we can catch
					// the memory locations we skipped with the PC.
					this.reset = true;
					
					// We write that we are exiting the proper subroutine to the output file and 
					// use the writeData() method to put the needed data to memory.
					bw.write("\n======Before return from Subroutine " + rtn + " Status=======\n");
					this.writeData(mem, bw, reg);
				} else if (opCode.equals("F")) { // Halt Program
					
					// We write to the output file that the program has ended and use writeData()
					// nethod to put the needed data to memory.
					bw.write("\nEnd of Program Status\n");
					this.writeData(mem, bw, reg);
					
					//sset the isRunning boolean to false to stop executing code
					this.isRunning = false;
				} else {
					count--; // if we did not execute any commands we remove a number from the count
						 // since we did not execute.
				}
			}
		}
	}

}
