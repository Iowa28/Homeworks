package inf10;
import java.util.regex.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTasks {
	public static void main(String[] args) {
		Scanner cs = new Scanner(System.in);
		checkRegex(cs.nextLine());
		
		String mails = "email@gmail.com yazzzik@list.ru Egor@egor.egor.egor.egor.ru some@some.html home@home.home";
		domainAllocation(mails);
		
		String mail = "DaddyLoba@rururu.list.ru";
		getDomains(mail);
	}
	
	public static void checkRegex(String line) { // task 1
		String pattern = "^([A-Za-z0-9-/]+\\.)*[A-Za-z0-9]{2,}$";
		
		if (line.matches(pattern)) {
			System.out.println("The domain name is entered correctly.");
		} else {
			System.out.println("The domain name is entered incorrectly.");
		}
	}
	
	public static void domainAllocation(String line) { // task 2
		Pattern p = Pattern.compile("[A-Za-z0-9-/]@(?:[A-Za-z0-9-/]+\\.)*[A-Za-z0-9]{2,}");
		Matcher m = p.matcher(line);
		while(m.find()) {
			System.out.println(m.start() + " " + m.group().replaceAll("(?:.+)@(.+)", "$1") + " "); //I get the domain and leave only that after the symbol @
		} 
	}
	
	public static void getDomains(String line) { // task 3
		System.out.println(line.replaceAll("[A-Za-z0-9-/]+@([A-Za-z0-9-/]+)\\.(?:[A-Za-z0-9-/]+\\.)*[A-Za-z0-9-/]{2,}", "$1")); //upper domain
		
		System.out.println(line.replaceAll("[A-Za-z0-9-/]+@(?:[A-Za-z0-9-/]+\\.)*([A-Za-z0-9-/]{2,})", "$1")); // lower domain
	}
}
