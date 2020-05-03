package work13;

import java.io.*;
import org.json.simple.JsonObject;
import org.json.simple.Jsoner;

import hw13.Student;

public class DataJsonStudentOutputDecorator extends OutputStream{
private BufferedWriter writer;
	
	public DataJsonStudentOutputDecorator(OutputStream os) {
		writer = new BufferedWriter(new OutputStreamWriter(os));
	}
	
	public void writeStudent(Student student){
		try {
			JsonObject customer = new JsonObject();
			customer.put("name", student.getName());
			customer.put("sex", student.getSex());
			customer.put("age", student.getAge());
			customer.put("group", student.getGroup());
			Jsoner.serialize(customer, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void write(int arg) throws IOException {
		writer.write(arg);
	}
	
	public static void main(String[] args) {
		try {
			FileOutputStream fos = new FileOutputStream("C:\\Users\\ACER\\Desktop\\Other\\Hey, Jackson.txt", true);
			Student student = new Student("Jackson", "man", 46, "2020-20");
			DataJsonStudentOutputDecorator dssos = new DataJsonStudentOutputDecorator(fos);
			dssos.writeStudent(student);
		} catch (FileNotFoundException e) {
			System.out.println("Give me the file, Lebowski!");
		} catch (IOException e) {
			System.out.println("Error :(");
		}
	}
}
