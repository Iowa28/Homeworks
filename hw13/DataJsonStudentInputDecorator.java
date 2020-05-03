package work13;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;

import org.json.simple.DeserializationException;
import org.json.simple.JsonObject;
import org.json.simple.Jsoner;

import Inf13.DataJsonStudentInputDecorator1;
import hw13.Student;

public class DataJsonStudentInputDecorator extends InputStream{
private BufferedReader reader;
	
	public DataJsonStudentInputDecorator(InputStream is) {
		reader = new BufferedReader(new InputStreamReader(is));
	}
	
	public Student readStudent() throws IOException {
		try {
			JsonObject parser = (JsonObject) Jsoner.deserialize(reader);
			
			String name = (String) parser.get("name");
			String sex = (String) parser.get("sex");
			BigDecimal age =  (BigDecimal) parser.get("age");
			String group = (String) parser.get("group");
			return new Student(name, sex, age.intValue(), group);
		} catch (DeserializationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public int read() throws IOException {
		return (int)reader.read();
	}
	
	public static void main(String[] args) {
		try {
			FileInputStream fos = new FileInputStream("C:\\Users\\ACER\\Desktop\\Other\\Hey, Jackson.txt");
			DataJsonStudentInputDecorator1 dssos = new DataJsonStudentInputDecorator1(fos);
			Student student = dssos.readStudent();
			System.out.println(student.getAge());
		} catch (FileNotFoundException e) {
			System.out.println("Where's the file, Lebowski");
		} catch (IOException e) {
			System.out.println("Error :(");
		}
	}
}
