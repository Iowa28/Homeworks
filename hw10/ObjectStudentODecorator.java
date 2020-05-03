package inf9;

import java.io.*;

import Student;

public class ObjectStudentODecorator extends OutputStream{
	
	private ObjectOutputStream oos;
	
	public ObjectStudentODecorator(OutputStream os) throws IOException {
		this.oos = new ObjectOutputStream(os);
	}

	@Override
	public void write(int student) throws IOException {
		oos.writeObject(student);
	}
	
	public void writeStudent(Student student) throws IOException {
		oos.writeObject(student);
	}
}
