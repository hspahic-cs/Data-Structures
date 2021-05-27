package JUnit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import basics.ShoppingCart;
import groceryStub.GroceryStore;
import groceryStub.Item;

public class GroceryStoreTest {
	@Test()
	public void testAddNewShopper() {
		GroceryStore test = new GroceryStore();
		
		test.getInventory().addNewItem("popcorn", 9.99, 90);
		test.getInventory().addNewItem("butter", 4.99, 18);
		test.getInventory().addNewItem("steak", 21.99, 45);
		
		ShoppingCart s1 = new ShoppingCart();
		Item s1i1 = new Item("butter", 4.99);
		Item s1i2 = new Item("steak", 21.99);
		
		ShoppingCart s2 = new ShoppingCart();
		Item s2i1 = new Item("popcorn", 9.99);
		Item s2i2 = new Item("butter", 4.99);
		
		s1.putItem(s1i1, 2);
		s1.putItem(s1i2, 5);
		
		s2.putItem(s2i1, 10);
		s2.putItem(s2i2, 5);
		
		test.getShoppers().addFirst(s1);
		assertEquals(test.getShoppers().getAt(0), s1);
		test.getShoppers().addFirst(s2);
		assertEquals(test.getShoppers().getAt(0), s2);
		
		
		Item i1 = new Item("popcorn", 9.99);
		Item i2 = new Item("butter", 4.99);
		
		Item i3 = new Item("butter", 4.99);
		Item i4 = new Item("steak", 21.99);
		
		ShoppingCart c1 = new ShoppingCart();
		ShoppingCart c2 = new ShoppingCart();
		
		c1.putItem(i3, 4);
		c1.putItem(i4, 2);
		c2.putItem(i1, 15);
		c2.putItem(i2, 1);
		
		test.getCheckout().addShoppingCart(c1);
		test.getCheckout().addShoppingCart(c2);
	}
	
	@Test()
	public void testMoveToQueue() {
		GroceryStore test = new GroceryStore();
		
		test.getInventory().addNewItem("popcorn", 9.99, 90);
		test.getInventory().addNewItem("butter", 4.99, 18);
		test.getInventory().addNewItem("steak", 21.99, 45);
		
		ShoppingCart s1 = new ShoppingCart();
		Item s1i1 = new Item("butter", 4.99);
		Item s1i2 = new Item("steak", 21.99);
		
		ShoppingCart s2 = new ShoppingCart();
		Item s2i1 = new Item("popcorn", 9.99);
		Item s2i2 = new Item("butter", 4.99);
		
		s1.putItem(s1i1, 2);
		s1.putItem(s1i2, 5);
		
		s2.putItem(s2i1, 10);
		s2.putItem(s2i2, 5);
		
		test.getShoppers().addFirst(s1);
		test.getShoppers().addFirst(s2);
		
		test.moveToQueue(0);
		
		assertEquals(test.getCheckout().getSize(), 1);
		assertEquals(test.getCheckout().getNextShoppingCart(), s2);	
	}
	
}
