package hw15;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Scanner;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class FileManager {
	private Path thisDirectory;
	private Scanner input;
	
	public static void main(String[] args) throws IOException {
		FileManager manager = new FileManager();
		manager.init();
		manager.run();
	}
	
	public void init() {
		thisDirectory = Paths.get(".").normalize().toAbsolutePath();
		input = new Scanner(System.in);
	}
	
	public void run() throws IOException {
		while(true) {
			System.out.println(thisDirectory + ">");
			String line = input.nextLine();
			String[] commands = line.split(" ", 2);
			String firstCommand = commands[0];
			switch (firstCommand) {
			case "exit":
				System.exit(0);
				break;
			case "ls":
				printFiles(thisDirectory);
				break;
			case "start":
				if (commands.length < 2) {
					System.out.println("���... �� �� ����� ��� ����� ��� �����, ������� ������ ���������.");
					break;
				}
				String fileName = commands[1];
				fileName = thisDirectory.toString() + "\\" + fileName;
				openFile(fileName);
				break;
			case "delete":
				if (commands.length < 2) {
					System.out.println("���... �� �� ����� ��� �����, ������� ������ �������.");
					break;
				}
				String fileToDelete = commands[1];
				fileToDelete = thisDirectory.toString() + "\\" + fileToDelete;
				if (Files.exists(Paths.get(fileToDelete))) {
					System.out.println("�� �������, ��� ������ ������� ���� ����? ������� 'Y' ��� 'N'");
					char answer = input.nextLine().charAt(0);
					if (answer == 'Y' || answer == 'y') {
						Files.delete(Paths.get(fileToDelete));
						if (!Files.exists(Paths.get(fileToDelete))) {
							System.out.println("���� ������� ������.");
						}
					}
				} else {
					System.out.println("�� ������� ����� ������ ����.");
				}
				break;
			case "copy":
				if (commands.length < 2) {
					System.out.println("���... �� �� ����� ��� �����, ������� ������ �����������.");
					break;
				}
				String file = commands[1];
				String fileToCopy = thisDirectory.toString() + "\\" + file;
				Path fileC = Paths.get(fileToCopy);
				if (Files.exists(fileC)) {
					Path dirToCopy = Files.createDirectory(Paths.get(thisDirectory + "\\copy"));
					if (Files.exists(dirToCopy)) {
						fileC = Files.copy(fileC, dirToCopy.resolve(fileC.getFileName()), REPLACE_EXISTING);
						if (Files.exists(Paths.get(dirToCopy.toString() + "\\" + file))) {
							System.out.println("���� ������� ���������� � ����� ����� " + dirToCopy.toString() + ".");
						} else {
							System.out.println("�� ������� ����������� ������ ����.");
						}
					} else {
						System.out.println("�� ������� ����������� ������ ����.");
					}
				} else {
					System.out.println("�� ������� ����� ������ ����.");
				}
				break;
			case "cd":
				if (commands.length < 2) {
					System.out.println("���... �� �� ����� ��� ����� ��� �����, � ������� ������ �������.");
					break;
				}
				String nextCommand = commands[1];
				switch(nextCommand) {
				case "\\":
					thisDirectory = thisDirectory.getRoot();
					break;
				case "..":
					thisDirectory = thisDirectory.getParent();
					break;
				default: // path name
					if (Files.exists(Paths.get(nextCommand).normalize().toAbsolutePath())) {
						thisDirectory = Paths.get(nextCommand).normalize().toAbsolutePath();
					} else {
						System.out.println("�� ������� ����� ���� ��� ���������� " + nextCommand + ".");
					}
					break;
				}
				
				break;
			case "help":
				System.out.println("exit - ����� �� ���������");
				System.out.println("ls - ����� ���������� � ���� ������ ������� ����������");
				System.out.println("start - ������ ����� ��� �����");
				System.out.println("delete - ������� ����");
				System.out.println("copy - ����������� ����");
				System.out.println("cd \\ - ������� � �������� ����������");
				System.out.println("cd .. - ������� �� ���������� ����");
				System.out.println("cd file - ������� � ���������� � ������ file");
				System.out.println("cd dir - ������� � ���������� dir");
				break;
			default:
				System.out.println("������, �� � �� ���� ��� �������.");
				break;
			}
			System.out.println();
		}
	}

	public void printFiles(Path path) {
		File dir = new File(path.toString());
		File[] files = dir.listFiles();
		for (File file: files) {
			Date date = new Date(file.lastModified());
			if (file.isFile()) {
				System.out.println("����: " + file.getName() + " ��������� ��������: " + date);
			}
			else if (file.isDirectory()) {
				System.out.println("�����: " + file.getName() + " ��������� ��������: " + date);
			}
		}
	}
	
	public void openFile(String fileName) throws IOException {
		if (!Desktop.isDesktopSupported()) {
			System.out.println("��� ������� ���� ������-�� �� �������������� :(");
			return;
		}
		Desktop desktop = Desktop.getDesktop();
		File file = new File(fileName);
		if (file.exists()) {
			desktop.open(file);
		} else {
			System.out.println("�� ������� ����� ������ ����. ���������, ��� �� ��������� ����� �������� ����� �����.");
		}
	}
}
