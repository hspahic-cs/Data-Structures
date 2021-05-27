package groceryStub;
import basics.SLL;
import basics.ShoppingCart;
import basics.SLL.Node;

public class GroceryStore {
	
	private Inventory inventory = new Inventory();
	private CheckoutLine checkout = new CheckoutLine();
	private SLL<ShoppingCart> shoppers = new SLL<ShoppingCart>();
	private double funds = 0;
	
	public double getFunds() {
		return funds;
	}

	public Inventory getInventory() {
		return inventory;
	}
	
	public CheckoutLine getCheckout() {
		return checkout;
	}
	
	public SLL<ShoppingCart> getShoppers() {
		return shoppers;
	}
	
	public void addNewShopper(ShoppingCart s) {
		shoppers.addFirst(s);
	}

	public void insertInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	public double checkoutNext(){
		// Error already thrown by getNextShoppingCart
		double total = checkout.getNextShoppingCart().checkout();
		funds += total;
		return total;
	}
	
	public double checkoutAll() {
		double total = checkout.processAll();
		funds += total;
		return total;
	}
	
	public void moveToQueue(int i) {
		if (i < 0 || i > shoppers.getSize()) {
			throw new IllegalArgumentException("moveToQueue: invalid index");
		}
		checkout.addShoppingCart(shoppers.getAt(i));
		shoppers.remove(i);
	}

	public void takeFromInventory(int index, String name, int number) {
		if(!inventory.isAvailable(name)){
			throw new IllegalArgumentException("takeFromInventory: item is not in inventory");
		}
		
		if (number > inventory.findItem(name).getCount() || number < 0) {
			throw new IllegalArgumentException("takeFromInventory: invalid remove number");
		}
		
		inventory.takeItems(name, number);
		Item temp = new Item(name, inventory.getPrice(name));
		shoppers.getAt(index).putItem(temp, number);
	}
	
	public void putBack(int index, int number) {
		if (index > shoppers.getSize() || index < 0) {
			throw new IllegalArgumentException("putBack: not valid index");
		}
		
		Item top = shoppers.getAt(index).getFirst();
		// findItem will return exception if item not in inventory
		Item inv = inventory.findItem(top.getName());
		
		// getItem returns exception if number is invalid
		top = shoppers.getAt(index).getItem(number);
		
		for(int i =0; i < top.getCount(); i++) {
			inv.incrementCount();
		}
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Inventory:\n");
		s.append(inventory.toString() + "\n");
		s.append("\nShoppers:\n");
		s.append("[");
		for(int i = 0; i < shoppers.getSize(); i++) {
			s.append(shoppers.getAt(i).toString());
			if (i != shoppers.getSize() - 1) {
				s.append("\n");
			}
		}
		s.append("]\n");
		
		s.append("\nCheckout Line:\n");
		s.append(checkout.toString());
		return s.toString();
	}
	
	public static void main(String[] args) {
		GroceryStore test = new GroceryStore();
		
		test.inventory.addNewItem("popcorn", 9.99, 90);
		test.inventory.addNewItem("butter", 4.99, 18);
		test.inventory.addNewItem("steak", 21.99, 45);
		
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
		
		test.shoppers.addFirst(s1);
		test.shoppers.addFirst(s2);
		
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
		
		test.checkout.addShoppingCart(c1);
		test.checkout.addShoppingCart(c2);
		
		System.out.println(test);
		
		test.takeFromInventory(1, "popcorn", 80);
		
		System.out.println();
		System.out.println(test);
		
		test.putBack(1, 70);
		
		System.out.println();
		System.out.println(test);
		

		
	}
}
