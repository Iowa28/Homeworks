package inf11;

import java.io.*;
import java.util.Properties;

import Student;

public class DataIniStudentOutputDecorator extends OutputStream{
	private DataOutputStream dos;
	private Properties props;
	
	public DataIniStudentOutputDecorator(OutputStream os) {
		this.dos = new DataOutputStream(os);
		props = new Properties();
	}
	
	public void studentWrite(Student student) throws IOException{
		props.setProperty("name", student.getName());
		props.setProperty("sex", student.getSex());
		props.setProperty("age", ((Integer)student.getAge()).toString());
		props.setProperty("group", student.getGroup());
		props.store(dos, "student");
		
	}

	@Override
	public void write(int number) throws IOException {
		dos.write(number);
	}
	
	
	public static void main(String[] args) {
		Student student = new Student("Boba", "man", 19, "33-033");
		try (FileOutputStream fos = new FileOutputStream("some_file")){
			DataIniStudentOutputDecorator disod = new DataIniStudentOutputDecorator(fos);
			disod.studentWrite(student);
		} catch (FileNotFoundException e) {
			System.out.println("Where's the file, Lebowski!?");
		} catch (IOException e1) {
			System.out.println("Error :(");
		}
	}
}
