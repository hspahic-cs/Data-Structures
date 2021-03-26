package rolodex;

public class Card extends Entry {
	private String name;
	private String cell;
	
		
	public Card(Entry prev, Entry next, String name, String cell) {
		super(prev, next);
		this.name = name;
		this.cell = cell;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCell() {
		return cell;
	}


	public void setCell(String cell) {
		this.cell = cell;
	}


	public Boolean isSeparator() {
		return false;
	}
	
	public int size() {
		return 1;
	}
	
	public String toString() {
		return "Name: "+name+", Cell: "+cell;
	}
}
