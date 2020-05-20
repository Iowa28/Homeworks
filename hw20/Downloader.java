package hw20;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Downloader implements Runnable{

	@Override
	public void run() {
		download();
	}
	
	public void download() {
		String dir = "C:\\Users\\ACER\\Desktop\\Other\\music.txt"; // my directory in disk
		File file = new File(dir);
		try (FileOutputStream fos = new FileOutputStream(new File(dir))){
			URI adress = new URI("http://myflex.org/yf/podru/budam479.mp3"); // some music
			System.out.println("Starting download...");
			ReadableByteChannel rbc = Channels.newChannel(adress.toURL().openStream());
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			rbc.close();
			
			System.out.println("Uploading is complete.");
		} catch (MalformedURLException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (URISyntaxException e) {
			System.out.println("Error: " + e.getMessage());
		}
		String newName = dir.substring(0, dir.length() - 3) + "mp3"; // changing extension
		file.renameTo(new File(newName));
	}
}
