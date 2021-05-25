package anagram;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Test;

import static org.junit.Assert.*;

public class AnagramsTest {
	
	@Test
	public void testBuildLetterTable() {
		Anagrams temp = new Anagrams();
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		
		int index = 0;
		
		for(Character letter: alphabet) {
			assertEquals(temp.primes[index], temp.letterTable.get(letter));
			index++;
		}
	}
	
	@Test
	public void testAddWord() {
		Anagrams temp = new Anagrams();
		temp.addWord("least");
		temp.addWord("tesla");
		temp.addWord("steal");
		temp.addWord("slate");
		temp.addWord("tales");
		temp.addWord("racecar");
		temp.addWord("carrace");
		
		ArrayList<String> first = new ArrayList<String>();
		first.add("least");
		first.add("tesla");
		first.add("steal");
		first.add("slate");
		first.add("tales");
		
		ArrayList<String> second = new ArrayList<String>();
		second.add("racecar");
		second.add("carrace");
		
		ArrayList<String> firstArr = temp.anagramTable.get(temp.myHashCode("least"));
		ArrayList<String> secondArr = temp.anagramTable.get(temp.myHashCode("racecar"));
		
		for(int i = 0; i < firstArr.size(); i++) {
			assertEquals(first.get(i), firstArr.get(i));
		}
		
		for(int i = 0; i < secondArr.size(); i++) {
			assertEquals(second.get(i), secondArr.get(i));
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testExceptAddWord() {
		Anagrams temp = new Anagrams();
		temp.addWord("least");
		temp.addWord("least");
	}
	
	@Test
	public void testMyHashCode() {
		Anagrams temp = new Anagrams();
		String first = "least";
		String second = "tesla";
		String third = "steal";
		
		assertEquals(temp.myHashCode(first), temp.myHashCode(second));
		assertEquals(temp.myHashCode(first), temp.myHashCode(third));
		assertEquals(temp.myHashCode(second), temp.myHashCode(third));		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testExceptMyHashCode() {
		Anagrams temp = new Anagrams();
		temp.myHashCode("");
	}
	
	@Test
	public void testGetMaxEntries() {
		Anagrams temp = new Anagrams();
		temp.addWord("least");
		temp.addWord("tesla");
		temp.addWord("steal");
		temp.addWord("slate");
		temp.addWord("tales");
		temp.addWord("racecar");
		temp.addWord("carrace");
		temp.addWord("abets");
		temp.addWord("baste");
		temp.addWord("betas");
		temp.addWord("beast");
		temp.addWord("beats");
		
		ArrayList<Map.Entry<Long,ArrayList<String>>> storage = temp.getMaxEntries();
		assertEquals(storage.size(), 2);
		assertEquals(storage.get(0).getValue().size(), 5);
		
		Anagrams temp2 = new Anagrams();
		ArrayList<Map.Entry<Long,ArrayList<String>>> storage2 = temp2.getMaxEntries();
		
		assertEquals(storage2.size(), 0);
	}
	
	
	
}
