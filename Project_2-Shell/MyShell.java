import java.io.IOException;
import java.util.Scanner;

public class MyShell{
	public static void run() throws IOException {
		String cmnd = null;
		Scanner input = new Scanner(System.in);
		boolean exit = false;
		while(!exit) {
			System.out.print(">>> ");
			cmnd = input.nextLine();
			String[] arguments = cmnd.split(" ");
			if(cmnd.equalsIgnoreCase("exit")){
				exit = true;
			}else if(cmnd.equalsIgnoreCase("clear")) {
				Runtime.getRuntime().exec("clear");
			}else if(cmnd.equalsIgnoreCase("date")){
				Runtime.getRuntime().exec("date");
			}else if(arguments[0].equalsIgnoreCase("help")) {
				if(arguments.length>1) {
					for(int i =0; i < arguments.length; i++) {
						if (arguments[i].equals("exit")){
							System.out.println("exit:\texit\tExit the Current Shell.");
						}
						if(arguments[i].equals("date")) {
							System.out.println("date:\tdate\tPrints out the current date and time information.");
						}
					}
				}else {
				System.out.println("[Command]\t\t[Description]");
				System.out.println("clear\t\tClear the console.");
				System.out.println("date\t\tPrints out the current date and time information.");
				System.out.println("exit\t\tExit the current shell.");
				}
			}else {
				System.out.println(cmnd+": command not found");
			}
		}
	}
	
	public static void main(String [] args) throws IOException {
		run();
	}
	
}