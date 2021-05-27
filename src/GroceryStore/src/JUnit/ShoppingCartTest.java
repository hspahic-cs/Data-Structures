package JUnit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import basics.ShoppingCart;
import groceryStub.Item;

public class ShoppingCartTest {
	@Test()
	public void testGetSize() {
		ShoppingCart test = new ShoppingCart();
		assertEquals(test.getSize(), 0);
		
		Item i1 = new Item("word", 9.3);
		
		test.putItem(i1, 5);
		assertEquals(test.getSize(), 1);

		test.putItem(i1, 0);
		assertEquals(test.getSize(), 2);
	}
	
	@Test(expected = IllegalStateException.class)
	public void testGetFirstException() {
		ShoppingCart test = new ShoppingCart();
		test.getFirst();
	}
	
	@Test()
	public void testGetFirst() {
		ShoppingCart test = new ShoppingCart();
		Item i1 = new Item("word", 11.11);
		test.putItem(i1, 20);
		
		for(int i = 0; i < 20; i++) {
			i1.incrementCount();
		}
		
		assertEquals(test.getFirst().getName(), i1.getName());
		assertEquals(test.getFirst().getCount(), i1.getCount());
		
		test.putItem(i1, 21);
		i1.incrementCount();
		
		assertEquals(test.getFirst().getName(), i1.getName());
		assertEquals(test.getFirst().getCount(), i1.getCount());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPutItemException() {
		ShoppingCart test = new ShoppingCart();
		Item i1 = new Item("word", 11.11);
		Item i2 = new Item("", 1);
		test.putItem(i1, -1);
		test.putItem(i1, 40);
		test.putItem(i2, 10);
	}
	
	@Test()
	public void testPutItem() {
		ShoppingCart test = new ShoppingCart();
		Item i1 = new Item("word", 11.11);
		
		test.putItem(i1, 20);
		assertEquals(20, test.getFirst().getCount());
		assertEquals("word", test.getFirst().getName());
		
		test.putItem(i1, 24);
		assertEquals(24, test.getFirst().getCount());
		assertEquals("word", test.getFirst().getName());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetItemException() {
		ShoppingCart test = new ShoppingCart();
		Item i1 = new Item("word", 11.11);
		
		test.putItem(i1, 20);
		test.getItem(21);
		test.getItem(-4);
	}
	
	@Test()
	public void testGetItem() {
		ShoppingCart test = new ShoppingCart();
		Item i1 = new Item("word", 11.11);
		
		for(int i = 0; i < 14; i++) {
			i1.incrementCount();
		}
		
		test.putItem(i1, 20);
		Item temp = test.getItem(14);
		assertEquals(temp.getCount(), i1.getCount());
		
		for(int i = 0; i < 8; i++) {
			i1.decrementCount();
		}
		
		assertEquals(test.getFirst().getCount(), i1.getCount());
		assertEquals(test.getFirst().getName(), i1.getName());
		
		test.putItem(i1, 14);
		test.getItem(14);
		
		assertEquals(test.getFirst().getCount(), 6);
		assertEquals(test.getFirst().getName(), "word");
	}
		
}
