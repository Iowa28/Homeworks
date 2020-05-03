package inf9;

import java.io.*;
import java.nio.ByteBuffer;

import Student;

public class DataStudentODecorator extends OutputStream{
	private DataOutputStream os;
	
	public DataStudentODecorator(OutputStream os) {
		this.os =  new DataOutputStream(os);
	}
	
	public void studentWrite(Student student) throws IOException{
        os.writeByte(student.getName().length());
        os.writeChars(student.getName());
        
        os.writeByte(student.getSex().length());
        os.writeChars(student.getSex());
        
        os.writeInt(student.getAge());
        
        os.writeByte(student.getGroup().length());
        os.writeChars(student.getGroup());
	}

	@Override
	public void write(int number) throws IOException {
		os.write(number);
	}
}