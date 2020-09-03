package inf11;

import java.io.*;
import java.util.Properties;

import Student;

public class DataIniStudentInputDecorator extends InputStream{
	private DataInputStream dis;
	private Properties props;
	
	public DataIniStudentInputDecorator(InputStream is) {
		this.dis = new DataInputStream(is);
		props = new Properties();
	}
	
	public Student readStudent() throws IOException {
		props.load(dis);
		String name = props.getProperty("name");
		String sex = props.getProperty("sex");
		Integer age = Integer.valueOf(props.getProperty("age"));
		String group = props.getProperty("group");
		
		return new Student(name, sex, age, group);
	}

	@Override
	public int read() throws IOException {
		return dis.readInt();
	}
	
	public static void main(String[] args) {
		try (FileInputStream fis = new FileInputStream("some_file")){
			DataIniStudentInputDecorator disid = new DataIniStudentInputDecorator(fis);
			Student student = disid.readStudent();
			System.out.println(student.getAge());
		} catch (FileNotFoundException e) {
			System.out.println("Where's the file, Lebowski!?");
		} catch (IOException e1) {
			System.out.println("Error :(");
		}
		
	}
}
