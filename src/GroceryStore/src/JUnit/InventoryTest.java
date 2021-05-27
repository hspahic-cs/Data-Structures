package JUnit;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import basics.ShoppingCart;
import groceryStub.GroceryStore;
import groceryStub.Inventory;
import groceryStub.Item;

public class InventoryTest {
	@Test(expected = IllegalStateException.class)
	public void testGetPrice() {
		Item i1 = new Item("popcorn", 9.99);
		Item i2 = new Item("butter", 4.99);
		Item i3 = new Item("steak", 21.99);
		
		Inventory I = new Inventory();
		I.addNewItem("popcorn", 9.99, 90);
		I.addNewItem("butter", 4.99, 18);
		I.addNewItem("steak", 21.99, 45);

		I.getPrice("cookies");		
	}
	
	@Test()
	public void testAddItems() {
		Item i1 = new Item("popcorn", 9.99);
		Item i2 = new Item("butter", 4.99);
		Item i3 = new Item("steak", 21.99);
		
		Inventory I = new Inventory();
		I.addNewItem("popcorn", 9.99, 90);
		I.addNewItem("butter", 4.99, 18);
		I.addNewItem("steak", 21.99, 45);
		I.addItems("popcorn", 10);

		assertEquals(I.getAt(0).getCount(), 100); 		
	}
	
	@Test()
	public void testAddNewItem() {
		Item i1 = new Item("popcorn", 9.99);
		Item i2 = new Item("butter", 4.99);
		Item i3 = new Item("steak", 21.99);
		
		Inventory I = new Inventory();
		I.addNewItem("popcorn", 9.99, 90);
		I.addNewItem("butter", 4.99, 18);
		I.addNewItem("steak", 21.99, 45);
		I.addItems("popcorn", 10);

		assertEquals(I.getAt(0).getCount(), 100);
	}
	
	@Test()
	public void testIsAvailable() {
		Item i1 = new Item("popcorn", 9.99);
		Item i2 = new Item("butter", 4.99);
		Item i3 = new Item("steak", 21.99);
		
		Inventory I = new Inventory();
		I.addNewItem("popcorn", 9.99, 90);
		I.addNewItem("butter", 4.99, 18);
		I.addNewItem("steak", 21.99, 45);


		assertEquals(I.isAvailable("popcorn"), true);
		assertEquals(I.isAvailable("cookies"), false);
	}
	
	@Test()
	public void testTakeItems() {
		Item i1 = new Item("popcorn", 9.99);
		Item i2 = new Item("butter", 4.99);
		Item i3 = new Item("steak", 21.99);
		
		Inventory I = new Inventory();
		I.addNewItem("popcorn", 9.99, 90);
		I.addNewItem("butter", 4.99, 18);
		I.addNewItem("steak", 21.99, 45);
		
		I.takeItems("popcorn", 40);

		assertEquals(I.getAt(0).getCount(), 50);
	}
	
}
