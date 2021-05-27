package groceryStub;

import basics.SLL;
import basics.SLL.Node;

public class Inventory {

	private SLL<Item> inventory = new SLL<Item>();
	
	public int getSize() {
		return inventory.getSize();
	}
	
	public Item getAt(int index) {
		return inventory.getAt(index);
	}
	
	/* findItem --> returns reference to item with name if in list
	*  
	*  @param: name --> name of item to be found
	*  @exception: If item is not found returns exception
	*/ 
	
	public Item findItem(String name){
		int i = 0;
		while(i < getSize()) {
			if (getAt(i).getName() == name) {
				return getAt(i);
			}
			i++;
		}
		throw new IllegalStateException("findItem: item does not exist in list");
	}
	
	
	public double getPrice(String name) {
		return findItem(name).getPrice();
	}
	
	public void addItems(String name, int number) {
		Item temp = findItem(name);

		for(int i = 0; i < number; i++) {
			temp.incrementCount();
		}
	}
	
	public void addNewItem(String name, double price, int count) {
		if(price < 0 || count < 0) {
			throw new IllegalArgumentException("addNewItem: price and count cannot be less than 0");
		}
		
		if (name == "") {
			throw new IllegalArgumentException("addNewItem: can't have an empty string for an item name");
		}
		
		Item temp = new Item(name, price);
		
		for(int i = 0; i < count; i++)
			temp.incrementCount();

		inventory.addLast(temp);
	}
	
	public boolean isAvailable(String name) {
		int i = 0;
		while(i < getSize()) {
			if (getAt(i).getName() == name) {
				return true;
			}
			i++;
		}
		return false;
	}
	
	public void takeItems(String name, int number) {
		Item temp = findItem(name);
		
		if (number > temp.getCount() || number < 0)
			throw new IllegalArgumentException("takeItems: cannot take negative items // cannot take more items than is in inventory");
		
		for (int i = 0; i < number; i++) {
			temp.decrementCount();
		}
		
		
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		
		int index = 0;
		s.append("[");
		while (index < getSize()) {
			s.append("[" + getAt(index).getName() + "," + getAt(index).getPrice() + "," + getAt(index).getCount() + "]");
			
			if (index != getSize() - 1) {
				s.append(";");
			}
			index++;
		}
		s.append("]");
		return s.toString();
	}
	
	public static void main (String[] args) {
		Item i1 = new Item("popcorn", 9.99);
		Item i2 = new Item("butter", 4.99);
		Item i3 = new Item("steak", 21.99);
		
		Inventory I = new Inventory();
		I.addNewItem("popcorn", 9.99, 90);
		I.addNewItem("butter", 4.99, 18);
		I.addNewItem("steak", 21.99, 45);

		System.out.println(I);
		System.out.println(I.getSize());
		System.out.println(I.getAt(2).getCount());
		System.out.println(I.getSize());
	}
	
	
}
