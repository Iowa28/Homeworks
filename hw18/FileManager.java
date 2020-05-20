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
					System.out.println("Эмм... вы не ввели имя файла или папки, которую хотите запустить.");
					break;
				}
				String fileName = commands[1];
				fileName = thisDirectory.toString() + "\\" + fileName;
				openFile(fileName);
				break;
			case "delete":
				if (commands.length < 2) {
					System.out.println("Эмм... вы не ввели имя файла или папки, которую хотите запустить.");
					break;
				}
				String fileToDelete = commands[1];
				fileToDelete = thisDirectory.toString() + "\\" + fileToDelete;
				if (Files.exists(Paths.get(fileToDelete))) {
					System.out.println("Вы уверены, что хотите удалить этот файл? Введите 'Y' или 'N'");
					char answer = input.nextLine().charAt(0);
					if (answer == 'Y' || answer == 'y') {
						Files.delete(Paths.get(fileToDelete));
						if (!Files.exists(Paths.get(fileToDelete))) {
							System.out.println("Не удалось найти данный файл.");
						}
					}
				} else {
					System.out.println("Íå óäàëîñü íàéòè äàííûé ôàéë.");
				}
				break;
			case "copy":
				if (commands.length < 2) {
					System.out.println("Эмм... вы не ввели имя файла или папки, которую хотите запустить.");
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
							System.out.println("Файл успешно скопирован в новую папку " + dirToCopy.toString() + ".");
						} else {
							System.out.println("Не удалось скопировать данный файл.");
						}
					} else {
						System.out.println("Не удалось скопировать данный файл.");
					}
				} else {
					System.out.println("Не удалось найти данный файл");
				}
				break;
			case "cd":
				if (commands.length < 2) {
					System.out.println("Эмм... вы не ввели имя файла или папки, которую хотите запустить.");
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
						System.out.println("Не удалось найти файл или директорию " + nextCommand + ".");
					}
					break;
				}
				
				break;
			case "help":
				System.out.println("exit - выход из программы");
				System.out.println("ls - вывод информации о всех файлах текущей директории");
				System.out.println("start - запуск файла или папки");
				System.out.println("delete - удалить файл");
				System.out.println("copy - скопировать файл");
				System.out.println("cd \\ - переход к корневой директории");
				System.out.println("cd .. - переход на директорию выше");
				System.out.println("cd file - переход в директорию с файлом file");
				System.out.println("cd dir - переход в директорию dir");
				break;
			default:
				System.out.println("Èçâèíè, íî ÿ íå çíàþ ýòó êîìàíäó.");
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
				System.out.println("файл: " + file.getName() + " последнее изменеие: " + date);
			}
			else if (file.isDirectory()) {
				System.out.println("папка: " + file.getName() + " последнее изменеие: " + date);
			}
		}
	}
	
	public void openFile(String fileName) throws IOException {
		if (!Desktop.isDesktopSupported()) {
			System.out.println("Ваш рабочий стол почему-то не поддерживается :(");
			return;
		}
		Desktop desktop = Desktop.getDesktop();
		File file = new File(fileName);
		if (file.exists()) {
			desktop.open(file);
		} else {
			System.out.println("Не удается найти данный файл. Убедитесь, что вы правильно ввели название этого файла.");
		}
	}
}
