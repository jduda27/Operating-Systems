# Project 1 - CPU/Memory/Stack Simulation
### [CPU Class](https://github.com/jduda27/Operating-Systems/blob/master/Project_1%20-CPU_SIM/CPU.java)
This class works along side the Stack and Memory Class in order to operate a simulation of a CPU that takes in a text file as a program. Inside we have the main method and then the methods listed below.

#### checkMem(String line,Memory mem)
The checkMem() method is used to search the lines that have been entered into the cpu, from a text file, looking for declarations that assign data to the memory locations specified in memory object mem if they exist within the program.
```Java
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
```


### [Memory Class](https://github.com/jduda27/Operating-Systems/blob/master/Project_1%20-CPU_SIM/Memory.java)
### [Stack Class](https://github.com/jduda27/Operating-Systems/blob/master/Project_1%20-CPU_SIM/Stack.java)
