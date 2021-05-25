package anagram;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//import sun.awt.AWTAccessor.SystemColorAccessor;

public class Anagrams {
	final Integer[] primes =  
			{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 
			31, 37, 41, 43, 47, 53, 59, 61, 67, 
			71, 73, 79, 83, 89, 97, 101};
	Map<Character,Integer> letterTable;
	Map<Long,ArrayList<String>> anagramTable;

	/* Constructor --> Note calls buildLetterTable()
	 */
	
	Anagrams() {
		buildLetterTable();
		anagramTable = new HashMap<Long,ArrayList<String>>();
	}
	
	/* initializes @letterTable hash table between first 25 primes & 25 characters
	 */
	
	public void buildLetterTable() {
		letterTable = new HashMap<Character, Integer>();
		final char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		for(int i = 0; i < alphabet.length; i++) {
			letterTable.put(alphabet[i], primes[i]);
		}
	}

	/* adds word to anagramTable
	 * @param: s --> string to be added to anagram table
	 * @exception: IllegalArgumentException --> if duplicate word added throws exception
	 */
	
	public void addWord(String s) {
		long hash = this.myHashCode(s);
		if(anagramTable.get(hash) == null) {
			anagramTable.put(hash, new ArrayList<String>());
		}
		
		ArrayList<String> storage = anagramTable.get(hash);
		
		for(int i = 0; i < storage.size(); i++) {
			if(storage.get(i).equals(s)) {
				throw new IllegalArgumentException("addWord: duplicate value");
			}
		}
		
		storage.add(s);
	}
	
	/* calculates & returns hashvalue of string using @letterTable
	 * @param: s --> string to have hashvalue calculated
	 * @exception: IllegalArgumentException --> throws illegal exception if s is empty string
	 */
	
	public long myHashCode(String s) {
		// Throws exception if string empty
		if(s.equals("")) {
			throw new IllegalArgumentException("myHashCode: s is empty");
		}
		
		// Initial value of hash
		long hash = 1; 
		
		// Multiply hash by each prime mapped to the characters of String
		for(int i = 0; i < s.length(); i++) {
			hash *= letterTable.get(s.charAt(i));
		}
		
		return hash;
	}
	
	public void processFile(String s) throws IOException {
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		while ((strLine = br.readLine()) != null)   {
		  this.addWord(strLine);
		}
		br.close();
	}
	
	/* returns arraylist of entries of words with most amount of anagrams in text file
	 */
	
	public ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries() {
		int max = 0;
		
	    for(Map.Entry<Long, ArrayList<String>> e : anagramTable.entrySet()) {
	    	if (e.getValue().size() > max) {
	    		max = e.getValue().size();
	    	}
	    }
	    
	    ArrayList<Map.Entry<Long,ArrayList<String>>> storage = new ArrayList<Map.Entry<Long,ArrayList<String>>>();
	    
    	for(Map.Entry<Long, ArrayList<String>> e : anagramTable.entrySet()) {
	    	if (e.getValue().size() == max) {
	    		storage.add(e);
	    	}	
	    }
		
	    return storage;
	}
	
	public static void main(String[] args) {
		Anagrams a = new Anagrams();
		System.out.println(a.letterTable.toString());

		final long startTime = System.nanoTime();    
		try {
			a.processFile("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime/1000000000);
		System.out.println("Time: "+ seconds);
		System.out.println("List of max anagrams: "+ maxEntries);
	}
}
