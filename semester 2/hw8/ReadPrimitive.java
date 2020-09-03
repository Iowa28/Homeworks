import java.io.*;

public class ReadPrimitive {
	public static void main(String[] args) {
		try(FileInputStream fis = new FileInputStream(new File("file.txt"))){
			char c = 0;
			c |= (byte) fis.read()<<8;
			c |= (byte) fis.read()<<0;
			System.out.println(c);
			int n = 0;
			n |= (byte) fis.read()<<24;
			n |= (byte) fis.read()<<16;
			n |= (byte) fis.read()<<8;
			n |= (byte) fis.read()<<0;
			System.out.println(n);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
}