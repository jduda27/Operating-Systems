import java.io.IOException;
import java.util.Scanner;


public class MyShell{
	
	public static void shell() throws IOException {
		String cmnd = null;
		Scanner input = new Scanner(System.in);
		boolean exit = false;
		boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
		// if(!isWindows) {
		// System.out.println("This program uses windows 10 commands and cannot be run
		// on your computer. The program will not run.");
		// }
		while (!exit) { // && isWindows
			System.out.print(">>> ");
			cmnd = input.nextLine();
			String[] arguments = cmnd.split(" ");
			if (cmnd.equalsIgnoreCase("exit")) {
				exit = true;
			} else if (cmnd.equalsIgnoreCase("clear")) {
				Runtime.getRuntime().exec("clear").getInputStream();
			} else if (cmnd.equalsIgnoreCase("date")) {
				Runtime.getRuntime().exec("date").getOutputStream();
			} else if (cmnd.equalsIgnoreCase("pwd")) {
				System.out.println(System.getProperty("user.dir"));
			} else if (cmnd.equalsIgnoreCase("whoami")) {
				System.out.println(System.getProperty("user.name"));
			} else if (arguments[0].equalsIgnoreCase("help")) {
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
			} else {
				System.out.println(cmnd + ": command not found");
			}
		}
	}

	public static void main(String[] args) throws IOException {
		shell();
	}

}