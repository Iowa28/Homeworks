package hw20;

import java.util.Scanner;

public class DownloadWizard {
	private Scanner input;
	private Thread thisThread;
	
	public static void main(String[] args) {
		DownloadWizard wizard = new DownloadWizard();
		wizard.init();
		wizard.run();
	}
	
	public void init() {
		input = new Scanner(System.in);
		thisThread = new Thread();
	}
	
	public void run() {
		while (true) {
			System.out.println("Enter a command!");
			String command = input.nextLine();
			switch (command) {
			case "exit":
				System.out.println("Exiting...");
				System.exit(0);
			case "start":
				Thread thread1 = new Thread(new Downloader());
				thisThread = thread1;
				thread1.start();
				break;
			case "status":
				Thread thread2 = new Thread(new InfoClass());
				thread2.start();
				break;
			case "stop":
				if (!thisThread.isAlive()) {
					System.out.println("File download has not yet begun.");
				} else {
					// stopping current thread
					thisThread.interrupt();
					System.out.println("File download stopped.");
				}
				break;
			default:
				System.out.println("Unknown command.");
				break;
			}  
		}
	}
}
