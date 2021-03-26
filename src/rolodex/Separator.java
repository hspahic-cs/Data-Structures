package rolodex;

public class Separator extends Entry {
	protected Character letter;

	public Separator(Entry prev, Entry next, Character letter) {
		super(prev, next);
		this.letter = letter;
	}


	public Character getLetter() {
		return letter;
	}


	public void setLetter(Character letter) {
		this.letter = letter;
	}


	public Boolean isSeparator() {
		return true;
	}
	
	public int size() {
		return 0;
	}
	
	public String getName() {
		return letter.toString();
	}
	
	public String toString() {
		return "Separator "+letter;
	}
	
}
