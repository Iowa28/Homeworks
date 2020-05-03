package inf11;

import Student;

public class Test {
	public static void main(String[] args) {
		Student student = new Student("Boris", "man", 25, "17-1337");
		System.out.println(student.getAge());
	}
}
