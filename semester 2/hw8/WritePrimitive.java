import java.io.*;

public class WritePrimitive {
	public static void main(String[] args) {
		try (FileOutputStream fos = new FileOutputStream(new File("file.txt"))) {
			int n = 8;
			char c = 'ß';
			fos.write(c>>8);
			fos.write(c>>0);
			fos.write(n>>24);
			fos.write(n>>16);
			fos.write(n>>8);
			fos.write(n>>0);
		} catch(IOException e) {
			System.err.println(e.getMessage());
		}
	}
}