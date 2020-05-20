package hw20;

import java.io.File;

public class InfoClass implements Runnable{
	
	@Override
	public void run() {
		getFileInfo();
	}
	
	public void getFileInfo() {
		File file = new File("C:\\Users\\ACER\\Desktop\\Other\\music.txt");
		double REAL_SIZE = 29.4;
		double thisSize = file.length()/(1024*1024);
		System.out.print("Файл скачан на " + Math.round(thisSize/REAL_SIZE*100) + "%");
	}
}
