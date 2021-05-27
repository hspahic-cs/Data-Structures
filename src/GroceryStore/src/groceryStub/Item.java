package groceryStub;

public class Item {
	
	public String name;
	public double price;
	public int count;
	
	public Item(String name, double price) {
		this.name = name;
		this.price = price;
		this.count = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public int getCount() {
		return count;
	}
	
	public void decrementCount() {
		if (count == 0) {
			throw new IllegalStateException("decrementCount: out of this particular item");
		}
		count--;
	}
	
	public void incrementCount() {
		count++;
	}
}