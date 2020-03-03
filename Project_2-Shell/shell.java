import java.util.Scanner;

public class shell{
	public static void run() {
		String cmnd = null;
		Scanner input = new Scanner(System.in);
		boolean exit = false;
		while(!exit) {
			System.out.print(">>> ");
			cmnd = input.nextLine();
			if(cmnd.equalsIgnoreCase("exit")){
				exit = true;
			}else if(cmnd.equalsIgnoreCase("help")) {
				System.out.println("[Command]\t\t[Description]");
				System.out.println("date\t\tPrints out the current date and time information.");
				System.out.println("help\t\tDisplays a list of the commands in this shell. If followed by "
						+ "a command name, display the description for that command.");
				System.out.println("exit\t\tExit the current shell.");
			}
		}
	}
	
	public static void main(String [] args) {
		run();
	}
	
}