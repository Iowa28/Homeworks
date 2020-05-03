package inf9;

import java.io.*;

import Student;

public class ObjectStudentIDecorator extends InputStream{
	
	private ObjectInputStream ois;
	
	public ObjectStudentIDecorator(InputStream is) throws IOException{
		this.ois = new ObjectInputStream(is);
	}

	@Override
	public int read() throws IOException {
		return ois.read();
	}
	
	public Student readStudent() throws ClassNotFoundException, IOException {
		return (Student) ois.readObject();
	}
	
	public static void main(String[] args) {
		try(ObjectStudentODecorator osod = new ObjectStudentODecorator(new FileOutputStream("file"))){
			
			Student student = new Student("Sergey Salo", "man", 24, "15-906");
			osod.writeStudent(student);
			
		} catch(IOException e) {
			System.out.println("Error :(");
		}
		
		
		try(ObjectStudentIDecorator osid = new ObjectStudentIDecorator(new FileInputStream("file"))){
			
			Student someStudent = osid.readStudent();
			System.out.println(someStudent.getName());
			
		} catch(IOException e) {
			System.out.println("Error :(");
		} catch(ClassNotFoundException e) {
			System.out.println("Where's the class, Lebowski!?");
		}
	}
}
