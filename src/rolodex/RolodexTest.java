package rolodex;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class RolodexTest {
	
	@Test
	public void testRolodex() {
		Rolodex empty = new Rolodex();
		for(int i = 0; i < 26; i++) {
			assertEquals(empty.getIndex()[i].next.getName().charAt(0), empty.alphabet[(i + 1)% 26]);
			assertEquals(empty.getIndex()[i].prev.getName().charAt(0), empty.alphabet[(i + 25)% 26]);
			assertEquals(empty.getIndex()[i].getName().charAt(0), empty.alphabet[i]);
		}
	}

	@Test()
	public void testContains() {
		Rolodex empty = new Rolodex();
		empty.addCard("Isabel", "123");
		empty.addCard("Lauren", "123");
		empty.addCard("Nicky", "1242");
		assertTrue(empty.contains("Isabel"));
		assertTrue(empty.contains("Nicky"));
		assertTrue(empty.contains("Lauren"));
		assertFalse(empty.contains("Isabella"));
		assertFalse(empty.contains("Nunez"));
		}
	
	@Test(expected = IllegalArgumentException.class)
	public void testContainsExceptions() {
		Rolodex empty = new Rolodex();
		empty.contains("");
	}
	
	@Test
	public void testSize() {
		Rolodex empty = new Rolodex();
		assertEquals(empty.size(), 0);
		empty.addCard("Isabel", "123");
		empty.addCard("Lauren", "123");
		assertEquals(empty.size(), 2);
	}

	@Test
	public void testLookup() {
		Rolodex empty = new Rolodex();
		empty.addCard("Isabel", "123");
		empty.addCard("Isabel", "121");
		empty.addCard("Isabel", "1242");
		ArrayList<String> test = new ArrayList<String>();
		test.add("123");
		test.add("121");
		test.add("1242");
		
		assertEquals(empty.lookup("Isabel"), test);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testLookupException() {
		Rolodex empty = new Rolodex();
		empty.lookup("Madi");
	}


	@Test
	public void testAddCard() {
		Rolodex empty = new Rolodex();
		empty.addCard("Hiya", "11121");
		Entry test = empty.getIndex()[7].next;
		
		assertEquals(test.prev.getName(), "H");
		assertEquals(test.next.getName(), "I");
		
		empty.addCard("Howard", "666");
		test = test.next;
		assertEquals(test.prev.getName(), "Hiya");
		assertEquals(test.next.getName(), "I");

	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddCardException() {
		Rolodex empty = new Rolodex();
		empty.addCard("Isabel", "123");
		empty.addCard("Isabel", "123");
	}
	
	@Test
	public void testRemoveCard() {
		Rolodex empty = new Rolodex();
		empty.addCard("Isabel", "123");
		empty.addCard("Isabel", "456");
		empty.removeCard("Isabel", "123");
		Entry test = empty.getIndex()[8].next;
		
		assertEquals(test.prev.getName(), "I");
		assertEquals(test.next.getName(), "J");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveCardException() {
		Rolodex empty = new Rolodex();
		
		empty.addCard("Heather", "123");
		empty.removeCard("Heather", "456");
	}

	@Test
	public void testRemoveAllCards() {
		Rolodex empty = new Rolodex();
		empty.addCard("Isabel", "123");
		empty.addCard("Isabel", "456");
		empty.removeAllCards("Isabel");
		
		Entry test = empty.getIndex()[8].next;
		
		assertEquals(test.getName(), "J");
		assertEquals(test.prev.getName(), "I");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveAllCardsException() {
		Rolodex empty = new Rolodex();
		empty.removeAllCards("Isabel");
	}

	@Test
	public void testInitializeCursor() {
		Rolodex empty = new Rolodex();
		empty.initializeCursor();
		assertEquals(empty.getCursor(), empty.getIndex()[0]);
	}

	@Test
	public void testNextSeparator() {
		Rolodex empty = new Rolodex();
		empty.initializeCursor();
		empty.nextSeparator();
		assertEquals(empty.getCursor(), empty.getIndex()[1]);
	}

	@Test
	public void testNextEntry() {
		Rolodex empty = new Rolodex();
		empty.initializeCursor();
		empty.addCard("Alice", "888");
		empty.nextEntry();
		assertEquals(empty.getCursor().getName(), "Alice");
	}

	@Test
	public void testCurrentEntryToString() {
		Rolodex empty = new Rolodex();
		empty.addCard("Alice", "888");
		empty.initializeCursor();
		empty.nextEntry();
		assertEquals(empty.currentEntryToString(), "Name: Alice, Cell: 888");
		empty.nextSeparator();
		System.out.println(empty.currentEntryToString());
		assertEquals(empty.currentEntryToString(), "Separator B");
	}

}
