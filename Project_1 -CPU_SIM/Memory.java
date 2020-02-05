
public class Memory {

	private int mem3AE;
	private int mem3AD;
	private int mem3AC;

	public Memory() {
		this.setMem3AC(0x0);
		this.setMem3AD(0x0);
		this.setMem3AE(0x0);
	}

	/**
	 * set() method This method sets a specified value to the specified mem location
	 * precondition: String memLoc is a valid string describing the mem location in
	 * hex int val is a valid integer value represented in HEX
	 */
	public void memSet(String memLoc, int val) {
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

	public int memGet(String memLoc) {
		if (Integer.parseInt(memLoc) == 940) {
			return mem3AC;
		} else if (Integer.parseInt(memLoc) == 941) {
			return mem3AD;
		} else {
			return mem3AE;
		}
	}

	public int getMem3AE() {
		return mem3AE;
	}

	public void setMem3AE(int mem3ae) {
		this.mem3AE = mem3ae;
	}

	public int getMem3AD() {
		return mem3AD;
	}

	public void setMem3AD(int mem3ad) {
		mem3AD = mem3ad;
	}

	public int getMem3AC() {
		return mem3AC;
	}

	public void setMem3AC(int mem3ac) {
		this.mem3AC = mem3ac;
	}

}