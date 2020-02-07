/** Operating Systems Project 1
*   A memory class with 3 designated memory locations that can be accessed 
*   securely by other classes.
*   @Author Joshua Duda
*   @Date 2/5/2020
*/
public class Memory {

	//initiating the private variables that will be stored
	private int mem3AE;
	private int mem3AD;
	private int mem3AC;

	//Initializing our memory location to default values
	public Memory() {
		this.setMem3AC(0x0);
		this.setMem3AD(0x0);
		this.setMem3AE(0x0);
	}

	/** memGet() method - This method is a getter method for the designated memory locations.
	* @precondition String memLoc - a valid string that represents the memory location that is
	*               being asked for.
	* @postcondition this method returns a integer that is our hex value being stored in the designated
	*                memory location.
	*/
	public int memGet(String memLoc) {
		if (Integer.parseInt(memLoc) == 940) {
			return getMem3AC();
		} else if (Integer.parseInt(memLoc) == 941) {
			return getMem3AD();
		} else {
			return getMem3AE();
		}
	}
	
	/**
	 * set() method This method sets a specified value to the specified mem location
	 * 
	 * @precondition: String memLoc - a valid string describing the memory location
	 *                hex int - a valid integer value represented in HEX
	 * @postcondition: The method sets the hex to the correct memory location or if
	 *                 invalid it lets the user know it is not a valid memory
	 *                 location.
	 */
	public void memSet(String memLoc, int val) {
		// checking for our three designated memory locations in decimal and using the setter 
		// methods to set the values to them.
		if (Integer.parseInt(memLoc) == 940) {
			setMem3AC(val);
		} else if (Integer.parseInt(memLoc) == 941) {
			setMem3AD(val);
		} else if (Integer.parseInt(memLoc) == 942 ) {
			setMem3AE(val);
		} else {
			System.out.println("Not a valid memory location: " + memLoc);
		}
	}
	
	/**
	 * getMem3AC() method This method gets and returns the memory stored in location 3AC
	 * @precondition: none;
	 * @postcondition: Returns the hex value stored at memory 3AC (940 decimal)
	 */
	public int getMem3AC() {
		return mem3AC;
	}
	
	/**
	 * getMem3AD() method This method gets and returns the memory stored in location 3AD
	 * @precondition: none;
	 * @postcondition: Returns the hex value stored at memory 3AD (941 decimal)
	 */
	public int getMem3AD() {
		return mem3AD;
	}
	
	/**
	 * getMem3AE() method This method gets and returns the memory stored in location 3AE
	 * @precondition: none;
	 * @postcondition: Returns the hex value stored at memory 3AE (942 decimal)
	 */
	public int getMem3AE() {
		return mem3AE;
	}
	
	/** setMem3AC() method - this method is a setter method that takes in a valid hex integer
	* @precondition int mem3ac - a valid integer that represents the hex we want to store in this memory location
	* @postcondition This method sets the memory location mem3AC to be the new hex integer.
	*/
	public void setMem3AC(int mem3ac) {
		this.mem3AC = mem3ac;
	}
	
	/** setMem3AD() method - this method is a setter method that takes in a valid hex integer
	* @precondition int mem3ad - a valid integer that represents the hex we want to store in this memory location
	* @postcondition This method sets the memory location mem3AD to be the new hex integer.
	*/
	public void setMem3AD(int mem3ad) {
		this.mem3AD = mem3ad;
	}
	
	/** setMem3AE() method - this method is a setter method that takes in a valid hex integer
	* @precondition int mem3ae - a valid integer that represents the hex we want to store in this memory location
	* @postcondition This method sets the memory location mem3AE to be the new hex integer.
	*/
	public void setMem3AE(int mem3ae) {
		this.mem3AE = mem3ae;
	}

}
