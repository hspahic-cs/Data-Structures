package JUnit;
import basics.ShoppingCart;
import groceryStub.CheckoutLine;
import groceryStub.Item;

import org.junit.Test;
import static org.junit.Assert.*;

public class CheckoutLineTest {
	
	@Test()
	public void testGetSize() {
		CheckoutLine test = new CheckoutLine();
		
		assertEquals(test.getSize(), 0);
		
		Item i1 = new Item("butter", 4.99);
		Item i2 = new Item("steak", 21.99);
		Item i3 = new Item("popcorn", 9.99);
		Item i4 = new Item("butter", 4.99);
		
		ShoppingCart C1 = new ShoppingCart();
		ShoppingCart C2 = new ShoppingCart();
		
		C1.putItem(i1, 2);
		C1.putItem(i2, 5);
		C2.putItem(i3, 10);
		C2.putItem(i4, 5);
		
		test.addShoppingCart(C1);
		test.addShoppingCart(C2);
		
		assertEquals(test.getSize(), 2);
	}
	
	
	@Test()
	public void testGetNextShoppingCart() {
		CheckoutLine test = new CheckoutLine();

		Item i1 = new Item("butter", 4.99);
		Item i2 = new Item("steak", 21.99);
		Item i3 = new Item("popcorn", 9.99);
		Item i4 = new Item("butter", 4.99);
		
		ShoppingCart C1 = new ShoppingCart();
		ShoppingCart C2 = new ShoppingCart();
		
		C1.putItem(i1, 2);
		C1.putItem(i2, 5);
		C2.putItem(i3, 10);
		C2.putItem(i4, 5);
		
		test.addShoppingCart(C1);
		test.addShoppingCart(C2);
		assertEquals(test.getNextShoppingCart(), C1);
		assertEquals(test.getNextShoppingCart(), C2);
	}
	
}
