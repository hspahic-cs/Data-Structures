package HuffmanTree;

import static org.junit.Assert.*;
import org.junit.Test;

public class HuffmanTreeTest {
	
	@Test 
	public void testBitsToString() {
		HuffmanTree temp = new HuffmanTree("wow");
		Boolean[] empty = {};
		String emptySol = "";
		Boolean[] left = {false, false, false, false, false, false};
		String leftSol = "000000";
		Boolean[] right = {true, true, true, true, true, true};
		String rightSol = "111111";
		Boolean[] random = {false, true, false, true, true, true};
		String randomSol = "010111";
		
		assertEquals(temp.bitsToString(empty), emptySol);
		assertEquals(temp.bitsToString(left), leftSol);
		assertEquals(temp.bitsToString(right), rightSol);
		assertEquals(temp.bitsToString(random), randomSol);
		
	}
	
	@Test
	public void testToString() {
		HuffmanTree temp = new HuffmanTree("xxeeeeaaaaaaaaa");
		String solution = "(freq= 15)\n  (freq= 6)\n    [value= x,freq= 2]\n    [value= e,freq= 4]" +
				  "\n  [value= a,freq= 9]\n";
		assertEquals(solution, temp.toString());
	}
	
	@Test
	public void testEncode() {
		String s = "This string is used as a basis for setting up the Huffman tree";
		HuffmanTree temp = new HuffmanTree(s);
		Boolean[] test = {false, true, true, true, true, true, true,
				false, true, true, true, true, true, true, true, false,
				true, false, false, true, true, false, false};
		assertEquals(temp.bitsToString(temp.encode("hinge")), temp.bitsToString(test));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testExcpetionDecode() {
		String s = "This string is used as a basis for setting up the Huffman tree";
		HuffmanTree temp = new HuffmanTree(s);
		Boolean [] c = {false , true , false , false};
		temp.decode(c);	
	}
	
	@Test
	public void testDecode() {
		String s = "This string is used as a basis for setting up the Huffman tree";
		HuffmanTree temp = new HuffmanTree(s);
		
		Boolean[] firstRun = {false, true, false, false, true, true};
		Boolean[] secondRun = {false, true, false, true};
		Boolean[] thirdRun = {true, true, true, true, true};
		
		assertEquals(temp.decode(firstRun), "o");
		assertEquals(temp.decode(secondRun), "u");
		assertEquals(temp.decode(thirdRun), "n");		
	}
	
	@Test
	public void testEfficientEncode() {
		String s = "This string is used as a basis for setting up the Huffman tree";
		HuffmanTree temp = new HuffmanTree(s);
		Boolean[] test = {false, true, true, true, true, true, true,
				false, true, true, true, true, true, true, true, false,
				true, false, false, true, true, false, false};
		assertEquals(temp.bitsToString(temp.efficientEncode("hinge")), temp.bitsToString(test));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testExceptionEncode(){
		String s = "This string is used as a basis for setting up the Huffman tree";
		HuffmanTree temp = new HuffmanTree(s);
		temp.encode("abx");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testExceptionEfficientEncode(){
		String s = "This string is used as a basis for setting up the Huffman tree";
		HuffmanTree temp = new HuffmanTree(s);
		temp.efficientEncode("abx");
	}
	
}