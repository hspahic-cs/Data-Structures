package basics;

import basics.SLL.Node;
import groceryStub.Item;


// Checked 100%

public class ShoppingCart {
	
	private SLL<Item> stack = new SLL<Item>();
	
	public int getSize() {
		return stack.getSize();	
	}
	
	/* getFirst : returns first Item in ShoppingCart
	 * 
	 * @exception: if ShoppingCart is empty, throws exception
	 */
	
	public Item getFirst() {
		if (getSize() == 0)
			throw new IllegalStateException("getFirst: list is empty");
		return stack.getAt(0);
	}
	
	public Item getItem(int number) {
		if (getSize() == 0) 
			throw new IllegalStateException("getItem: no items to remove from");
		
		
		if (number < 0 || number > stack.getAt(0).getCount()) 
			throw new IllegalArgumentException("getItem: invalid number of items to remove");
		
		
		for(int i = 0; i < number; i++) {
			stack.getAt(0).decrementCount();
		}
		
		Item temp = new Item(stack.getAt(0).getName(), stack.getAt(0).getPrice());
		for(int i = 0; i < number; i++)
			temp.incrementCount();
		
		if (stack.getAt(0).getCount() == 0) { 
			stack.removeFirst();		
		}
		
		return temp;
	}
	
	public void putItem(Item item, int number) {
		if(item.getPrice() < 0 || item.getCount() < 0) 
			throw new IllegalArgumentException("addNewItem: price and count cannot be less than 0");
		
		if(item.getName() == "")
			throw new IllegalArgumentException("addNewItem: can't have an empty string for an item name");
		
		Item temp = new Item(item.getName(), item.getPrice());
		
		stack.addFirst(temp);
		
		for (int i = 0; i < number; i++) 
			stack.getAt(0).incrementCount();
		
	}
	
	public double checkout() {
		double cost = 0;
		while(getSize() != 0) {
			cost += stack.getAt(0).getPrice() * stack.getAt(0).getCount();
			stack.removeFirst();
		}
		
		return Math.round(cost*100) / 100.0;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		
		int index = 0;
		s.append("[");
		while (index < getSize()) {
			s.append("[" + stack.getAt(index).getName() + "," + stack.getAt(index).getPrice() + "," + stack.getAt(index).getCount() + "]");
			
			if (index != stack.getSize() - 1) {
				s.append(";");
			}
			index++;
		}
		s.append("]");
		return s.toString();
	}
	
	public static void main (String[] args) {
		ShoppingCart test = new ShoppingCart();
		
		Item i1 = new Item("popcorn", 9.99);
		Item i2 = new Item("butter", 4.99);
		
		test.putItem(i1, 2);
		test.putItem(i2, 2);
		
		System.out.println(test);
		
		test.getItem(1);
		System.out.println(test);
		
		test.getItem(1);
		System.out.println(test);

		
		
		
		System.out.println(test.checkout());
		
	}
}
