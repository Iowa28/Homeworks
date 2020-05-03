package inf9;

import java.io.*;

import Student;

public class DataStudentIDecorator extends InputStream {
	private DataInputStream is;
	
	public DataStudentIDecorator(InputStream is) {
		this.is = new DataInputStream(is);
	}
	
	public Student readStudent() throws IOException{
		byte nameLength = is.readByte();
		String name = "";
        for(int j = 0;j < nameLength;j++){
            name += is.readChar();
        }
        
        byte sexLength = is.readByte();
        String sex = "";
        for(int j = 0;j < sexLength;j++){
            sex += is.readChar();
        }
        
        int age = is.readInt();
        
        byte groupLength = is.readByte();
        String group = "";
        for(int j = 0;j < groupLength;j++){
            group += is.readChar();
        }
        return new Student(name, sex, age, group);
	}

	@Override
	public int read() throws IOException {
		return is.read();
	}
	
	public static void main(String[] args) {
		File file = new File("file");
		try (DataStudentODecorator dsod = new DataStudentODecorator(new FileOutputStream(file))){
			Student student = new Student("Azamat Muratov", "man", 39, "87-312");
			
			dsod.studentWrite(student);
			
			
		} catch (FileNotFoundException e) {
			System.out.println("Where's the file, Lebowski!?");
		} catch (IOException e1) {
			System.out.println("Error!");
		}
		
		
		
		try (DataStudentIDecorator dsid = new DataStudentIDecorator(new FileInputStream(file))){
			
			Student student = dsid.readStudent();
			System.out.println(student.getGroup());
			
		} catch(IOException e) {
			System.out.println("Error!");
		}
	}
}
