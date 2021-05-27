package groceryStub;
import basics.SLL;
import basics.ShoppingCart;
import basics.SLL.Node;

public class CheckoutLine {
	
	private SLL<ShoppingCart> checkout = new SLL<ShoppingCart>();
	
	public int getSize() {
		return checkout.getSize();
	}
	
	public void addShoppingCart(ShoppingCart cart) {
		checkout.addLast(cart);
	}
	
	public ShoppingCart getNextShoppingCart() {
		if (getSize() == 0) {
			throw new IllegalStateException("getNextShoppingCart: checkout is empty");
		}
		
		return checkout.removeFirst();
	}
	
	public double processAll() {
		double total = 0;
		
		while(getSize() != 0) {
			total += getNextShoppingCart().checkout();
		}
		
		return total;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		int i = 0;
		
		s.append("[");
		while(i < getSize()) {
			s.append(checkout.getAt(i).toString());
			if(i != getSize() - 1) {
				s.append("\n");
			}
			i++;
		}
		s.append("]");
		
		return s.toString();
	}
	
	public static void main(String[] args) {
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
		
		System.out.println(test);
		
		System.out.println(test.processAll());
		
		System.out.println(test);
		
	}
	
}