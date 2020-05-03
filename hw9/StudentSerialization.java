package inf9;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import Student;

public class StudentSerialization{
	
	public static ArrayList<Student> read(File file) {
		try (FileInputStream fis = new FileInputStream(file)) {
			int MAX_CAPACITY = 1024;
			int k = 0;
			int size = 0;
			ByteBuffer bb = ByteBuffer.allocate(MAX_CAPACITY);
			while((k = fis.read()) != -1 &  size != MAX_CAPACITY){
				bb.put((byte) k);
				size++;
			}
			bb.slice();
			bb.rewind();
			
			ArrayList<Student> students = new ArrayList<>();
			int capacity = 3*10*2 + 4; // 3 строки, у каждого ровно 10 символов по 2 байта, + число с 4 байтами
			int studentsCount = size/capacity; // вычитываем количество студентов
			
			for (int j = 0; j < studentsCount; j++) {
				String name = "";
				for (int i = 0; i < 10; i++) {
					char c = bb.getChar();
					if (c != ' ') {
						name += c;
					}
				}
				
				String sex = "";
				for (int i = 0; i < 10; i++) {
					char c = bb.getChar();
					if (c != ' ') {
						sex += c;
					}
				}
				
				int age = bb.getInt();
				
				String group = "";
				for (int i = 0; i < 10; i++) {
					char c = bb.getChar();
					if (c != ' ') {
						group += c;
					}
				}
				
				Student student = new Student(name, sex, age, group);
				students.add(student);
			}
			return students;
			
		} catch (IOException e) {
			System.out.println("Error :(");
		}
		return null;
	}
	
	public static void write(ArrayList<Student> students, File file) {
		try (FileOutputStream fos = new FileOutputStream(file)){
			for (int i =  0; i < students.size(); i++) { // going through all the students
				Student student = students.get(i);
				
				String name = student.getName() + ' ' + ' ' + ' ' + ' ' + ' ' + ' ' + ' ' + ' ' + ' ' + ' ';
				name = name.substring(0, 10); // длина строк студента всегда будет равна 10
				
				String sex = student.getSex() + ' ' + ' ' + ' ' + ' ' + ' ' + ' ' + ' ' + ' ' + ' ' + ' ';
				sex = sex.substring(0, 10);
				
				int age = student.getAge();
				
				String group = student.getGroup() + ' ' + ' ' + ' ' + ' ' + ' ' + ' ' + ' ' + ' ' + ' ' + ' ';
				group = group.substring(0, 10);
				
				int capacity = name.length()*2 + sex.length()*2 + 4 + group.length()*2;
				ByteBuffer bb = ByteBuffer.allocate(capacity);
				
				for (int j = 0; j < name.length(); j++) {
					char c = name.charAt(j);
					bb.putChar(c);
				}
				
				for (int j = 0; j < sex.length(); j++) {
					char c = sex.charAt(j);
					bb.putChar(c);
				}
				
				bb.putInt(age);
				
				for (int j = 0; j < group.length(); j++) {
					char c = group.charAt(j);
					bb.putChar(c);
				}
				fos.write(bb.array());
				fos.flush();
			}
		} catch(IOException e) {
			System.out.println("Error :(");
		}
		
	}
	
	public static void main(String[] args) {

		ArrayList<Student> al = new ArrayList<>();
		File file = new File("file.txt"); 
		al.add(new Student("Archi", "man", 18, "11-9035"));
		al.add(new Student("Rocky", "boy", 19, "11-8035"));
		
		write(al, file);
		ArrayList<Student> al1 = read(file);
		
		System.out.println(al1.get(0).getAge());
		System.out.println(al1.get(1).getAge());
		System.out.println(al1.get(1).getGroup());
	}
	
}
