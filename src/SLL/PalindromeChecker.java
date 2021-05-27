package SLL;

public class PalindromeChecker {
	private String inputString;
	private StackSLL<Character> s;
	
	PalindromeChecker(String str){
		inputString = str.replaceAll("\\s", "");
		s = new StackSLL<>();
		fillStack(str);
	}
	
	/* fills stack with letters from string
	 * @param: str --> string to fill with
	 */
	
	private void fillStack(String str) {
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) != ' ') {
				s.push(str.charAt(i));
			}
		}
	}
	
	/* fills new stack with end of Palindrome to be checked until we get reverse
	 */
	
	private String buildReverse() {
		StringBuilder result = new StringBuilder();
		
		while(!s.isEmpty()) {
			result.append(s.pop());
		}
		
		return result.toString();
	}
	
	/* Compares original with reverse & returns true if they are equal
	 * I.e checks if word isPalindrome
	 */
	
	public boolean isPalindrome() {
		return inputString.equalsIgnoreCase(buildReverse());
	}
	
	public static void main(String[] args) {
		PalindromeChecker p1 = new PalindromeChecker("kayak");
		PalindromeChecker p2 = new PalindromeChecker("I saw I was I");
		PalindromeChecker p3 = new PalindromeChecker("Able was I ere I saw Elba");
		PalindromeChecker p4 = new PalindromeChecker("Level, madam, level");
		PalindromeChecker p5 = new PalindromeChecker("kayk");
		System.out.println(p1.isPalindrome());
		System.out.println(p2.isPalindrome());
		System.out.println(p3.isPalindrome());
		System.out.println(p4.isPalindrome());
		System.out.println(p5.isPalindrome());
	}
	
}
