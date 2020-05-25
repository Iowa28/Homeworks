package hw21;

import static org.junit.Assert.*;
import org.junit.Test;

public class SetTest {

	@Test
	public void addTest() {
		Set<String> set = new Set<>();
		set.add("abba");
		assertEquals(new Set<String>(new String[]{"abba"}) , set);
	}
	
	@Test
	public void hasTest() {
		Set<String> set = new Set<>(new String[] {"aa", "bb", "cc"});
		assertEquals(false, set.has("dd"));
	}
	
	@Test
	public void sizeTest() {
		Set<String> set = new Set<>(new String[] {"aa", "bb", "cc"});
		assertEquals(3, set.size(), 0.0);
	}
	
	@Test
	public void removeTest() {
		Set<String> set = new Set<>(new String[] {".NET"});
		set.remove(".NET");
		assertEquals(new Set<String>(), set);
	}
	
	@Test
	public void mergeTest() {
		Set<String> set = new Set<>(new String[] {".NET"});
		set = set.merge(new Set<String>(new String[] {"Java"}));
		assertEquals(new Set<String>(new String[] {".NET", "Java"}), set);
	}
	
	@Test
	public void containsTest() {
		Set<String> set = new Set<>(new String[] {"Java", ".NET"});
		Set<String >newSet = new Set<>(new String[] {"Java"});
		assertEquals(true, set.contains(newSet));
	}
	
}
