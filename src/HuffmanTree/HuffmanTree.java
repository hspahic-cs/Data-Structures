package HuffmanTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class HuffmanTree {

	// ******************** Start of Stub Code ******************** //
	// ************************************************************ //
    /** Node<E> is an inner class and it is abstract.
     * There will be two kinds
     * of Node, one for leaves and one for internal nodes. */
    abstract static class Node implements Comparable<Node>{
        /** The frequency of all the items below this node */
        protected int frequency;
        
        public Node(int freq) {
        	this.frequency = freq;
        }
        
		/** Needed for the Minimum Heap used later in this stub. */
		public int compareTo(Node other) {
			return this.frequency - other.frequency;
		}
    }
    /** Leaves of a Huffman tree contain the data items */
    protected static class LeafNode extends Node {
        // Data Fields
        /** The data in the node */
        protected char data;
        /** Constructor to create a leaf node (i.e. no children) */
        public LeafNode(char data, int freq) {
            super(freq);
            this.data = data;
        }
        /** toString method */
        public String toString() {
            return "[value= "+this.data + ",freq= "+frequency+"]";
        }
    }
    /** Internal nodes contain no data,
     * just references to left and right subtrees */
    protected static class InternalNode extends Node {
        /** A reference to the left child */
        protected Node left;
        /** A reference to the right child */
        protected Node right;

        /** Constructor to create an internal node */
        public InternalNode(Node leftC, Node rightC) {
            super(leftC.frequency + rightC.frequency);
            left = leftC; right = rightC;
        }
        public String toString() {
            return "(freq= "+frequency+")";
        }
    }
	
	// Enough space to encode all "extended ascii" values
	// This size is probably overkill (since many of the values are not "printable" in the usual sense)
	private static final int codex_size = 256;
	
	/* Data Fields for Huffman Tree */
	private Node root;
	
	public HuffmanTree(String s) {
		root = buildHuffmanTree(s);
	}
	
	/**
	 * Returns the frequencies of all characters in s.
	 * @param s
	 * @return
	 */
	public static int[] frequency(String s) {
		int[] freq = new int[codex_size];
		for (char c: s.toCharArray()) {
			freq[c]++;
		}
		return freq;
	}
	
	/**
	 * Builds the actual Huffman tree for that particular string.
	 * @param s
	 * @return
	 */
	private static Node buildHuffmanTree(String s) {
		int[] freq = frequency(s);
		
		// Create a minimum heap for creating the Huffman Tree
		// Note to students: You probably won't know what this data structure
		// is yet, and that is okay.
		PriorityQueue<Node> min_heap = new PriorityQueue<Node>();
		
		// Go through and create all the nodes we need
		// as in, all the nodes that actually appear in our string (have a frequency greater then 0)
		for(int i = 0; i < codex_size; i++) {
			if (freq[i] > 0) {
				// Add a new node (for that character) to the min_heap, notice we have to cast our int i into a char.
				min_heap.add(new LeafNode((char) i, freq[i]));
			}
		}
		
		// Edge case (string was empty)
		if (min_heap.isEmpty()) {
			throw new NullPointerException("Cannot encode an empty String");
		}
		
		// Now to create the actual Huffman Tree 
		// NOTE: this algorithm is a bit beyond what we cover in cs284, 
		// you'll see this in depth in cs385
		
		// Merge smallest subtrees together
		while (min_heap.size() > 1) {
			Node left = min_heap.poll();
			Node right = min_heap.poll();
			Node merged_tree = new InternalNode(left, right);
			min_heap.add(merged_tree);
		}
		
		// Return our structured Huffman Tree
		return min_heap.poll();
	}
	
	// ******************** End of Stub Code ******************** //
	// ********************************************************** //
	
	///////////////////////////////////////////////////////////////////////////////////
	
	// ******************** HELPER FUNCTIONS ******************** //
	// ********************************************************** //
	
	///////////////////////////////////////////////////////////////////////////////////
	
	/* Helper for toString (pretty much all of toString but not called on root)
	 * @param: current -- pointer to current InternalNode for recursion
	 * @depth: depth -- depth of recursive calls for spacing
	 * @visual: visual -- storage variable for the total string
	 */
	static String preOrderTraversal(InternalNode current, int depth, StringBuilder visual) {
		
		for(int i = 0; i < depth; i++) {
			visual.append("  ");
		}
		
		visual.append(current.toString() + "\n");
		
		if(current.left instanceof LeafNode && current.right instanceof LeafNode) {
			for(int i = 0; i < depth + 1; i++) {
				visual.append("  ");
			}
			
			visual.append(current.left.toString() + "\n");
			
			for(int i = 0; i < depth + 1; i++) {
				visual.append("  ");
			}
			visual.append(current.right.toString() + "\n");
		}
		
		else if(current.left instanceof LeafNode){
			for(int i = 0; i < depth + 1; i++) {
				visual.append("  ");
			}
			visual.append(current.left.toString() + "\n");
			preOrderTraversal((InternalNode) current.right, depth + 1, visual);
		}
		
		else if(current.right instanceof LeafNode){
			preOrderTraversal((InternalNode) current.left, depth + 1, visual);
			for(int i = 0; i < depth + 1; i++) {
				visual.append("  ");
			}
			visual.append(current.right.toString() + "\n");
		}
		
		else {
			preOrderTraversal((InternalNode) current.left, depth + 1, visual);
			preOrderTraversal((InternalNode) current.right, depth + 1, visual);
		}
		
		return visual.toString();
	}
	
	/* Checks if node is in Tree
	 * @param: current -- pointer to current InternalNode for recursion
	 * @param: element -- element to be checked for
	 */
	
	static boolean inHuffman(InternalNode current, char element) {		
		
		if(current.left instanceof LeafNode && current.right instanceof LeafNode) {
			LeafNode left = (LeafNode)((InternalNode)current).left;
			LeafNode right =(LeafNode)((InternalNode)current).right;
			return left.data == element || right.data == element;
		}
		
		else if(current.left instanceof LeafNode){
			LeafNode left = (LeafNode)((InternalNode)current).left;
			return left.data == element || inHuffman((InternalNode) current.right, element);
		}
		
		else if(current.right instanceof LeafNode){
			LeafNode right =(LeafNode)((InternalNode)current).right;
			return inHuffman((InternalNode) current.left, element) || right.data == element;
		}
		
		else {
			return inHuffman((InternalNode) current.left, element) || inHuffman((InternalNode) current.right, element);
		}
	}

	/* Converts Boolean ArrayList to boolean array
	 * @param: directions -- encoded directions to be converted
	 * NOTE: Must call inHuffman before this function
	 */
	
	static Boolean[] convert(ArrayList<Boolean> directions) {
		Boolean[] temp = new Boolean[directions.size()];
		
		for(int i = 0; i < directions.size(); i++) {
			temp[i] = directions.get(i);
		}
		
		return temp;

	}
	
	/* Returns every possible path in terms of ArrayList<Booelean>
	 * @param: current -- pointer to current InternalNode for recursion
	 * @param: rightPath -- temporary variable for storing current recursive path
	 * @param: directionsList -- holder of all direction paths
	 */
	public ArrayList<ArrayList<Boolean>> findPaths(InternalNode current, ArrayList<Boolean> rightPath, ArrayList<ArrayList<Boolean>> directionsList) {	
		ArrayList<Boolean> leftPath = new ArrayList<>();
		leftPath.addAll(rightPath);
		
		// Precede down each path
		leftPath.add(false);
		rightPath.add(true);
		
		
		
		// If both are internal nodes continue down
		if(current.left instanceof InternalNode && current.right instanceof InternalNode) {
			findPaths((InternalNode)current.left, leftPath, directionsList);
			findPaths((InternalNode)current.right, rightPath, directionsList);
		}
		
		// If left is internal node, continue down left and check if right is eternal path
		else if(current.left instanceof InternalNode){
			findPaths((InternalNode) current.left, leftPath, directionsList);
			
			if(current.right != null) {
				directionsList.add(rightPath);
			}
		}
		
		// If right is internal node, check if left and continue down right 
		else if(current.right instanceof InternalNode){
			if(current.left != null) {
				directionsList.add(leftPath);
			}
			findPaths ((InternalNode) current.right, rightPath, directionsList);
			
		}
		
		else {
			if(current.left != null) {
				directionsList.add(leftPath);
			}
			
			if(current.right != null) {
				directionsList.add(rightPath);
			}
			
		}
		
		return directionsList;
	}
	
	/*
	 *  Helper for findPaths, calls at root
	 */
	
	public ArrayList<ArrayList<Boolean>> findPaths(){
		return this.findPaths((InternalNode)root, new ArrayList<Boolean>(), new ArrayList<ArrayList<Boolean>>());
	}
	
	// Takes in a given path and checks if element is at its end
	/* Takes in a given path and checks if element is at its end
	 * @param: directions -- path to be followed to check for element
	 * @param: element -- element to be checked for
	 */
	
	public Boolean[] search(Boolean[] directions, char element){
		
		InternalNode current = (InternalNode) root;
		
		for (int i = 0; i < directions.length - 1; i++) {
			if(!directions[i]) {
				current = (InternalNode) current.left;
			}
			else{
				current = (InternalNode) current.right;
			}
		}
		
		if(!directions[directions.length-1]) {
			if(((LeafNode)current.left).data != element) {
				return null;
			}
			else {
				return directions;
			}
		}
		
		else {
			if(((LeafNode)current.right).data != element) {
				return null;
			}
			else {
				return directions;
			}
		}
		
	}
	
	/*	Follows given path to last element then returns element
	 * 	@param: directions -- directions to be followed to get element
	 * 	NOTE: precondition that directions points to a valid leaf
	 */
	public char pathEndElement(Boolean[] directions) {
		InternalNode current = (InternalNode) root;
		
		for (int i = 0; i < directions.length - 1; i++) {
			if(!directions[i]) {
				current = (InternalNode) current.left;
			}
			else{
				current = (InternalNode) current.right;
			}
		}
		
		if(!directions[directions.length-1]) {
			return ((LeafNode)current.left).data;
		}
		
		else {
			return ((LeafNode)current.right).data;
		}
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////
	
	/* Converts boolean encoding to string
	 * @param: coding -- boolean encoding of element in huffman
	 */
	
	public static String bitsToString(Boolean[] coding) {
		StringBuilder temp = new StringBuilder();
		for(boolean right : coding) {
			if(right) {
				temp.append("1");
			}
			else {
				temp.append("0");
			}
		}
		return temp.toString();
	}
	
	// toString method which prints HuffmanTree in typical Binary Tree form
	// **uses preOrderTraversal**
	public String toString() {
		StringBuilder word = new StringBuilder();
		return preOrderTraversal((InternalNode)root, 0,  word);
    }
	
	/*	decodes a given Boolean[] of directions into its given String representation
	 *  @param: coding -- total encoded hufmman tree
	 *  @Exception: IllegalArgumentException -- throws exception if following path hits wall or doesn't end on leaf
	 */
 	public String decode(Boolean[] coding) {
		
		String decodedString = "";
		int index = 0; 
		
		while(index < coding.length) {
			InternalNode current = (InternalNode)root;
			Boolean repeat = true;
			
			while(repeat) {
				if(coding[index]) {
					if(current.right == null || ((current.right instanceof InternalNode) && index == coding.length - 1)){
						throw new IllegalArgumentException("decode: coding invalid");
					}
					
					else if(current.right instanceof InternalNode) {
						current = (InternalNode)current.right;
					}
					
					else{
						LeafNode temp = (LeafNode)current.right;
						decodedString += temp.data;
						repeat = false;
					}
				}
				
				else if(!coding[index]) {
					if (current.left == null || ((current.left instanceof InternalNode) && index == coding.length - 1)) {
						throw new IllegalArgumentException("decode: coding invalid");
					}
					
					else if(current.left instanceof InternalNode) {
						current = (InternalNode)current.left;
					}
					
					else {
						LeafNode temp = (LeafNode)current.left;
						decodedString += temp.data;
						repeat = false;
					}
				}
				index++;
			}
			
		}
		
		return decodedString;
	}
	
	/*	Inefficient encoding, takes text and returns encoded path
	 *  @param: inputText -- text to be encoded
	 *  @Exception: IllegalArgumentException -- invalid inputText
	 */
 	
	public Boolean[] encode(String inputText) {
		
		// Storage for all letters
		ArrayList<Boolean[]> encoding = new ArrayList<>();
		
		char[] letters = inputText.toCharArray();
		
		// Find all possible paths for table
		ArrayList<ArrayList<Boolean>> temp = (findPaths());
		
		for(char letter : letters) {
			
			// If letter not in Huffman throw exception
			if(!inHuffman((InternalNode)root, letter)) {
				throw new IllegalArgumentException("encode: " + letter + "is not in huffman tree");
			}
			
			// For every list of directions in directions 
			for (ArrayList<Boolean> dir : temp) {	
				
				// Check if specific set of directions leads to letter
				Boolean[] arr = search(convert(dir), letter);
				
				// If it does add directions 
				if(arr != null) {
					encoding.add(arr);
					break;
				}
			}
		}
		
		// encoding now holds every set of directions for each character in order 
		int totalSize = 0;
		
		for (Boolean[] list: encoding) {
			totalSize += list.length;
		}
		
		// Makes boolean array with size of total instructions
		Boolean[] newEncoding = new Boolean[totalSize];
		int index = 0;
		
		for(Boolean[] directions: encoding) {
			for (Boolean direction: directions) {
				newEncoding[index] = direction;
				index++;
			}
		}
		
		return newEncoding;
	}
	
	/*	Efficient encoding, takes text and returns encoded path
	 *  @param: inputText -- text to be encoded
	 *  @Exception: IllegalArgumentException -- invalid inputText
	 */
	
	public Boolean[] efficientEncode(String inputText) {
		
		ArrayList<Boolean> dirList = new ArrayList<>();
		
		HashMap<Character, Boolean[]>dirTable = new HashMap<Character, Boolean[]>();
		ArrayList<ArrayList<Boolean>> allPaths = findPaths();
		
		// Fill Hashtable of all characters & their direction booleans
		for(ArrayList<Boolean> path: allPaths) {
			// convert path to boolean array
			Boolean[] temp = convert(path);
			
			dirTable.put(pathEndElement(temp), temp);
		}
		
		char[] letters = inputText.toCharArray();
		
		// For each letter in the word check hash table
		for(char letter: letters) {
			if (dirTable.get(letter) == null) {
				throw new IllegalArgumentException("encode: " + letter + "is not in huffman tree");
			}
			
			Boolean[] path = dirTable.get(letter);
			for(Boolean dir: path) {
				dirList.add(dir);
			}
		}
		
		return convert(dirList);
	}
	
		
	
	public static void main(String[] args) {
		// Code to see if stuff works...
		String s = "This string is used as a basis for setting up the Huffman tree";
		HuffmanTree t = new HuffmanTree(s); // Creates specific Huffman Tree for "s"
		// Now you can use encode, decode, and toString to interact with your specific Huffman Tree
		System.out.println(t);	
	}
}
