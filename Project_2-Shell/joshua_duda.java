
/** Operating Systems Project 1
*   A MyShell Class that uses windows 10 commands to read in user commands and perform it's operations.
*   @Author Joshua Duda
*   @Date 3/24/2020
*/

import java.io.*;
import java.util.Scanner;

public class joshua_duda {

	/**
	 * shell Method - This method is what connects our sell to the windows 10
	 * command line
	 * 
	 * @throws IOException
	 */
	public static void shell() throws IOException {
		// initializing variables that will be used in the program
		String cmnd = null;
		String line = null;

		// creating a new scanner to read in user input
		Scanner input = new Scanner(System.in);

		// the exit boolean is defaulted to false until the exit command is activated
		boolean exit = false;

		// This determines is our shell will work properly on the users machine
		boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");

		// If it can't be run we inform the user and the program does not execute
		if (!isWindows) {
			System.out.println(
					"This program uses windows 10 commands and cannot be run on your computer. The program will not run.");
		}

		// In this while loop we check that we are running on windows and the exit case
		// has not been triggered
		while (!exit && isWindows) {
			// printing a input queue and waiting for a user to enter in the next line
			System.out.print(">>> ");
			cmnd = input.nextLine();

			// we split up any arguments the line might have had so we can use them for
			// commands that allow them
			String[] arguments = cmnd.split(" ");

			// if the user types exit we change our exit state to true and end the program
			if (cmnd.equalsIgnoreCase("exit")) {
				exit = true;

				// clear command uses the process builder and the windows command cls to clear
				// the command screen
			} else if (cmnd.equalsIgnoreCase("clear")) {
				try {
					new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				} catch (Exception E) {
					System.out.println(E);
				}

				// date command uses the command echo with windows variables and the tzutil to
				// display the time zone
			} else if (cmnd.equalsIgnoreCase("date")) {
				Process p = Runtime.getRuntime().exec("cmd /c echo %date% %time%");
				BufferedReader StdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
				System.out.print(StdInput.readLine());
				p = Runtime.getRuntime().exec("cmd /c tzutil /g");
				StdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
				System.out.print(" " + StdInput.readLine() + "\n");

				// this command display the users current directory to the console
			} else if (cmnd.equalsIgnoreCase("ls")) {
				Process p = Runtime.getRuntime().exec("cmd /c dir");
				BufferedReader StdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
				while ((line = StdInput.readLine()) != null) {
					System.out.println(line);
				}

				// this command displays the current absolute path to the current directory
			} else if (cmnd.equalsIgnoreCase("pwd")) {
				Process p = Runtime.getRuntime().exec("cmd /c cd");
				BufferedReader StdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
				System.out.println(StdInput.readLine());

				// this command returns the current users username to the console using echo and
				// the username variable in windows
			} else if (cmnd.equalsIgnoreCase("whoami")) {
				Process p = Runtime.getRuntime().exec("cmd /c echo %USERNAME%");
				BufferedReader StdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
				System.out.println(StdInput.readLine());

				// the help command either prints all commands and shell information to the
				// screen or if there are arguments only the specified command information is
				// displayed to the screen.
			} else if (arguments[0].equalsIgnoreCase("help")) {

				// if there are more than 1 argument we print all relevant commands to the
				// screen.
				if (arguments.length > 1) {
					for (int i = 0; i < arguments.length; i++) {
						if (arguments[i].equals("exit")) {
							System.out.println("exit:\texit\tExit the Current Shell.");
						}
						if (arguments[i].equals("date")) {
							System.out.println("date:\tdate\tPrints out the current date and time information.");
						}
						if (arguments[i].equals("clear")) {
							System.out.println("clear:\tclear\tClear the console.");
						}
						if (arguments[i].equals("ls")) {
							System.out.println("ls:\tls\tLists the contents of the current directory to the console.");
						}
						if (arguments[i].equals("pwd")) {
							System.out.println(
									"pwd:\tpwd\tPrints to the console the absolute path for the current directory.");
						}
						if (arguments[i].equals("whoami")) {
							System.out.println("whoami:\twhoami\tReturns to the console the current username.");
						}
					}

					// if there are no arguments we print all the information to the screen
				} else {
					System.out.println(
							"myShell, version 1.0, runs on Windows10, Developed by: Joshua Duda\nRelease date 3/14/2020");
					System.out.println("These shell commands are defined internally. Type 'help' to see the list.");
					System.out.println("Command and its parameter, if any, should be separated by one space only.");
					System.out.println("Type 'help name' to find more about the command 'name'.\n");
					System.out.println("[Command]\t[Function]\n=========\t==========");
					System.out.println("clear\t\tClear the console.");
					System.out.println("date\t\tPrints out the current date and time information.");
					System.out.println("exit\t\tExit the current shell.");
					System.out.println("ls\t\tLists the contents of the current directory to the console.");
					System.out.println("pwd\t\tPrints to the console the absolute path for the current directory.");
					System.out.println("whoami\t\tReturns to the console the current username.");
				}

				// if the user types in a command that does not exist we let the user know the
				// entered command does not exist
			} else {
				System.out.println(cmnd + ": command not found");
			}
		}
	}

	// main method that runs our program
	public static void main(String[] args) throws IOException {

		// static call of our shell.
		shell();
	}

}