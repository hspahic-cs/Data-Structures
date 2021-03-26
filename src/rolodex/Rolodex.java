package rolodex;

import java.util.ArrayList;

import java.util.Arrays;

// Harris Spahic
// "I pledge my honor I have abided by the Steven's Honor System."

public class Rolodex {
	private Entry cursor;
	private final Entry[] index;
	
	final char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

	// Constructor

	Rolodex() {
		this.index = new Entry[26];

		int i = 0;
		
		while (i < 26*2) {
			if (i < 26) {
			getIndex()[i % 26] = new Separator(getIndex()[(i + 25) % 26] , getIndex()[(i + 1)%26], alphabet[i % 26]);
			}
			
			else {
				getIndex()[i % 26].prev = getIndex()[(i + 25) % 26];
				getIndex()[i % 26].next = getIndex()[(i + 1)%26];
			}
			
			i++;
		}
	
	}
	
	/*	returns the index i of the Rolodex.index that corresponds to the specific string letter
	 * 	@param: name --> name of card to be referenced
	 */
	
	public int letterIndex(String name) {
		char firstLetter = Character.toUpperCase(name.charAt(0));
		int i = Arrays.binarySearch(alphabet, firstLetter);
		return i;
	}
	
	/*	returns true if name is in Rolodex, false otherwise
	 * 	@param: name --> name of card to be referenced
	 *  @Exception: IllegalArgumentException --> Returns exception if @param: name is an empty string
	 */

	public Boolean contains(String name) {
	    // Checks if name is empty, otherwise throws exception
		if (name.equals("")) {
			throw new IllegalArgumentException("contains: name cannot be empty");
		}
		
		int i = letterIndex(name);
		
		Entry current = getIndex()[i].next;
		while(!(current instanceof Separator)) {
			
			if (current.getName().equals(name)){
				return true;
			}
			current = current.next;
		}
		
		return false;
	}
	
	/* 	returns number of cards in the Rolodex
	 */
	
	public int size() {
		int size = 0;
		
		for (int i = 0; i < 26; i++) {
			Entry current = getIndex()[i].next;
			while(!(current instanceof Separator)) {
				size++;
				current = current.next;
			}	
		}
		
		return size;
	}
	
	/* returns ArrayList of all numbers of particular person in the Rolodex
	 * @param: name --> name to be searched for
	 * @Exception: IllegalArgumentException --> Returns exception if name is not in Rolodex
	 */

	public ArrayList<String> lookup(String name) {
		if (!this.contains(name)) {
			throw new IllegalArgumentException("lookup: name not found");
		}
		
		int i = letterIndex(name);
		
		ArrayList<String> cellNums = new ArrayList<String>(); 
		
		Entry current = getIndex()[i].next;
		while(!(current instanceof Separator)) {
			if(current.getName().equals(name)) {
				cellNums.add(((Card) current).getCell());
			}
			current = current.next;
			}
		
		
		return cellNums;
	}

	/* returns Rolodex in string form --> easily displayed
	 */

	public String toString() {
		Entry current = getIndex()[0];

		StringBuilder b = new StringBuilder();
		while (current.next!=getIndex()[0]) {
			b.append(current.toString()+"\n");
			current=current.next;
		}
		b.append(current.toString()+"\n");		
		return b.toString();
	}
	
	/* adds a card to the Rolodex with specific name & cell
	 * @ param: name --> Name of card to be added
	 * @ param: cell --> Cell of person in added card
	 * @exception: IllegalArguementException --> Returns exception if card already exists in Rolodex
	 */

	public void addCard(String name, String cell) {
		
		if (this.contains(name)) {
			// Iterates through all instances of name, and throws exception if copy cell found
			ArrayList<String> cellNums = this.lookup(name);
			for (int i = 0; i < cellNums.size(); i++) {
				if (cellNums.get(i).equals(cell)) {
					throw new IllegalArgumentException("addCard: duplicate entry");
				}
			}
		}
		
		int i = letterIndex(name);
		Card client = new Card(null, null, name, cell);
		
		Entry current = getIndex()[i].next;
		// If no cards between separator, add card
		if (current instanceof Separator) {
			Entry temp = current.prev;
			
			current.prev = client;
			client.next = current;
			
			temp.next = client;
			client.prev = temp;
		}
		// Else iterate through lexicographically until correct name is found
		else {
			while((current).getName().compareTo(name) <= 0) {
				current = current.next;
			}
			Entry temp = current.prev;
			
			current.prev = client;
			client.next = current;

			temp.next = client;
			client.prev = temp;
			
		}

	}

	/* removes card from Rolodex
	 * @param: name --> name of card to be removed
	 * @param: cell --> cell of card to be removed
	 * @Exception: IllegalArgumentException --> Returns exception if card does not exist in Rolodex
	 */
	
	public void removeCard(String name, String cell) {
		if (!this.contains(name)) {
			throw new IllegalArgumentException("removeCard: name does not exist");
		}
		
		ArrayList<String> cellNums = this.lookup(name);
		boolean found = false;
		for (String num : cellNums) {
			if (num.equals(cell)) {
				found = true;
			}
		}
		
		if (!found) {
			throw new IllegalArgumentException("removeCard: cell for that name does not exist");
		}
		
		int i = letterIndex(name);
		
		Entry current = getIndex()[i].next;
		while(!(current).getName().equals(name) && !((Card)current).getCell().equals(cell)) {
			current = current.next;
		}
		
		Entry temp = current.prev;
		current = current.next;
		
		current.prev = temp;
		temp.next = current;
		
	}
	
	/* removes all cards with a specific name from Rolodex
	 * @param: name --> name of cards to be removed
	 * @exception: IllegalArgumentException --> throws exception if name does not exist
	 */
	
	public void removeAllCards(String name) {
		if (!this.contains(name)) {
			throw new IllegalArgumentException("removeAllCards: name does not exist");
		}
		
		int i = letterIndex(name);
		Entry current = getIndex()[i].next;
		
		
		// Get to first instance of name && store Entry right before it
		while(!current.getName().equals(name)) {
			current = current.next;	
		}
		
		Entry tempStart = current.prev;
		
		// Get last instance of name & store it
		while(current.getName().equals(name)) {
			current = current.next;
		}
		
		Entry tempEnd = current;
		
		tempStart.next = tempEnd;
		tempEnd.prev = tempStart;
		
	}

	// Cursor operations
	
	/* initializes cursor to first entry of Rolodex
	 */
	
	public void initializeCursor() {
		cursor = getIndex()[0];
	}

	/* moves cursor to next separator in Rolodex
	 */
	
	public void nextSeparator() {
		Entry current = getCursor().next;
		while (!current.isSeparator()) {
			current = current.next;
		}
		cursor = current;
	}

	/* moves cursor to next entry in Rolodex
	 */
	
	public void nextEntry() {
		cursor = cursor.next;
	}

	/* returns string of current entry selected by cursor
	 */
	
	public String currentEntryToString() {
		return cursor.getName();
	}
	

	public Entry[] getIndex() {
		return index;
	}

	public Entry getCursor() {
		return cursor;
	}

	
	public static void main(String[] args) {

		Rolodex r = new Rolodex();
		
		r.addCard("Chloe", "123");
		r.addCard("Chad", "23");
		r.addCard("Cris", "3");
		r.addCard("Cris", "4");
		r.addCard("Cris", "5");
		//r.addCard("Cris", "4");
		r.addCard("Maddie", "23");

		System.out.println(r);

		
		System.out.println(r.contains("Albert"));

		r.removeAllCards("Cris");

		System.out.println(r);

		r.removeCard("Chad","23");
		r.removeAllCards("Chloe");

		
		System.out.println(r);
		
		r.addCard("Chloe", "123");
		r.addCard("Chad", "23");
		r.addCard("Cris", "3");
		r.addCard("Cris", "4");
//
		System.out.println(r);
	}

	

}
