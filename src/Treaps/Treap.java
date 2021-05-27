package Treaps;

import java.util.ArrayList;
import java.util.Random;

public class Treap <E extends Comparable<E>>{
	private static class Node<F extends Comparable<F>>{
		public F data;
		public int priority;
		public Node<F> left;
		public Node<F> right;
		
		/* Node Constructor --> Creates Node object with key & priority, defaulting children to null
		 * @parameter: data --> data to be stored in Node
		 * @parameter: priority --> priority of Node in Treap
		 * @except: IllegalArg --> If data == null throw exception
		 */
		
		public Node(F data, int priority) {
			if(data == null) {
				throw new IllegalArgumentException("Node: data is null");
			}
			this.data = data;
			this.priority = priority;
			left = null;
			right = null;
		}
		
		/* rotateRight --> rotates root Node to the right, lifting its left child & inheriting left.right child to its left
		 * !!! Used to maintain heap properties of treap !!!
		 */
		
		Node<F> rotateRight(){
			Node<F> temp = new Node<F>(data, priority);
			temp.right = right;
			
			// Switch values of root with left 
			data = left.data;
			priority = left.priority;
			
			temp.left = left.right;
			left = left.left;
			
			right = temp;
			
			return this;
		}
	
		/* rotateLeft --> rotates root Node to the left, lifting its right child & inheriting right.left child to its right
		 * !!! Used to maintain heap properties of treap !!!
		 * !!! Inverse operation of rotateRight !!!!
		 */
		
		Node<F> rotateLeft(){
			Node<F> temp = new Node<F>(data, priority);
			temp.left = left;
			
			// Switch values of root with right
			data = right.data;
			priority = right.priority;
			
			
			temp.right = right.left;
			right = right.right;
			
			left = temp;
			
			return this;
		}
		
		/* toString: prints Node in this form (key= __, priority= ___)
		 */
		
		public String toString() {
			return ("(key=" + data + ", priority=" + priority + ")");
		}
	}
	
	private Random priorityGenerator;
	private Node<E> root;

	/* Constructor for Treap, with root = null & Random priority generator
	 */
	
	public Treap() {
		root = null;
		priorityGenerator = new Random();
	}
	
	/* Constructor for Treap, with root = null & Seeded priority generator
	 * @param: seed --> seed for Random priority generator
	 */
	
	public Treap(long seed) {
		root = null;
		priorityGenerator = new Random(seed);
	}
	
	/* reheaps Treap so that maintains both heap & Treap propoerties
	 * @param: added --> Node that was just added to Treap
	 */
	
	public void reheap(Node<E> added) {
		ArrayList<Node<E>> storage = new ArrayList<Node<E>>();
		
		Node<E> current = root;

		while(current != added) {
			storage.add(current);
			if (added.data.compareTo(current.data) > 0) {
				current = current.right;
			}
			else {
				current = current.left;
			}
			
		}	
		
		// All parents added to list [root, --> ] 
		// While last element of storage priority is < added.priority
		while(storage.size() != 0 && storage.get(storage.size() - 1).priority < added.priority) {
			if(storage.get(storage.size() - 1).left != null && storage.get(storage.size() - 1).left.data == added.data) {
				storage.get(storage.size() - 1).rotateRight();
			}
			else {
				storage.get(storage.size() - 1).rotateLeft();
			}
			
			storage.remove(storage.size() - 1);
		}
	}
	
	/* adds node to Treap & reheaps accordingly, returns true if successfully added / false if key already exists
	 * @param: key --> key of Node to be added
	 * @param: priority --> priority of Node to be added
	 */
	
	boolean add(E key, int priority) {
		if(root == null){
			root = new Node<E>(key, priority);
		}
		
		Node<E> added = new Node<E>(key, priority);
		Node<E> current = root;
		// Stores previous position in current
		Node<E> mem = null;
		
		while(current != null) {
			mem = current;
			int i = current.data.compareTo(key);
			
			if (i == 0) {
				return false;
			}
			else if (i < 0) {
				current = current.right;
			}
			else {
				current = current.left;
			}	
		}
		
		// Changes children of last branch
		
		int i = mem.data.compareTo(key);
		if (i < 0) {
			mem.right = added;
		}
		else {
			mem.left = added;
		}
		
		reheap(added);
		
		return true;
	}
	
	/* helper function for add, calling add with generated priority
	 * @param: key --> key of Node to be added
	 */
	
	boolean add(E key) {
		return add(key, priorityGenerator.nextInt());
	}
	
	/* finds if given key is in sub-Treap with root current
	 * @param: key --> key of Node to be checked
	 * @param: current --> root of sub-Treap to be checked
	 */
	
	private boolean find(Node<E> current, E key) {
		if (current == null) {
			return false;
		}
		
		int i = current.data.compareTo(key);
		
		if(i == 0) {
			return true;
		}
		
		else if (i < 0) {
			return find(current.right, key);
		}
		
		else {
			return find(current.left, key);
		}
	}
	
	/* helper function for find, calling find on root of Treap
	 * @param: key --> key of Node to be added
	 */
	
	public boolean find(E key) {
		return find(root, key);
	}
	
	/* deletes Node with given key in Treap & reheaps accordingly, if key is not found in Treap returns false; otherwise returns true
	 * @param: key --> key of Node to be removed
	 */
	
	boolean delete(E key) {
		if(!find(key)) {
			return false;
		}
		
		Node<E> current = root;
		Node<E> mem = null;
		while(current.data != key) {
			int i = current.data.compareTo(key);
			mem = current;
			
			if (i < 0) {
				current = current.right;
			}
			
			else {
				current = current.left;
			}
		}
		
		// current = correct node 
		
		while(!(current.left == null && current.right == null)) {
			mem = current;
			if(current.left != null && current.right == null) {
				current.rotateRight();
				current = current.right;
			}
			
			else if(current.left == null && current.right != null) {
				current.rotateLeft();	
				current = current.left;
			}
			
			
			else {
				boolean leftBigger = current.left.priority > (current.right.priority);
				if (leftBigger) {
					current.rotateRight();
					current = current.right;
				}
				else {
					current.rotateLeft();
					current = current.left;
				}
			}	
		}
		
		int i = mem.data.compareTo(key);
		if (i < 0) {
			mem.right = null;
		}
		else {
			mem.left = null;
		}
		
		return true;
	}
	
	/* recursively prints Treap in the form of a BST with root current
	 * @param current: root of Treap to be printed
	 * @param count: recursively variable storing number of iterations toString has recursed
	 */
	
	public String toString(Node<E> current, int count) {
		StringBuilder s = new StringBuilder();
		
		for(int i = 0; i < count; i++) {
			s.append("  ");
		}
		
		if (current == null) {
			s.append("null\n");
		}
		else {
			s.append(current.toString() + "\n");
			s.append(toString(current.left, count + 1));
			s.append(toString(current.right, count + 1));
		}
		return s.toString();
	} 
	
	/* helper method for toString, calling toString on root of Treap
	 */
	
	public String toString() {
		return toString(root, 0);
	}
	
	public static void main(String[] args) {
		Treap testTree = new Treap<Integer>();	
		testTree.add(4, 19);
		testTree.add(2, 31);
		testTree.add(6,70);
		testTree.add(1,84);
		testTree.add(3, 12);
		testTree.add(5, 83);
		testTree.add(7, 26);
		
		//System.out.println(testTree.find(18));
		//System.out.println(testTree.delete(40));
		System.out.println(testTree.delete(1));
		System.out.println(testTree);
		
		Treap testTree2 = new Treap<Character>();
		testTree2.add('p', 99);
		testTree2.add('g', 80);
		testTree2.add('u', 75);
		testTree2.add('a', 60);
		testTree2.add('j', 65);
		testTree2.add('r', 40);
		testTree2.add('z', 47);
		testTree2.add('w', 32);
		testTree2.add('v', 21);
		testTree2.add('x', 25);
		
		System.out.println(testTree2);
		System.out.println(testTree2.delete('r'));
		System.out.println(testTree2);
		
		/*
		Treap testTree3 = new Treap<Integer>();
		testTree3.add(7, 30);
		testTree3.add(4, 25);
		testTree3.add(8, 20);
		testTree3.add(1, 10);
		testTree3.add(5, 15);
		
		Node<Integer> t = testTree3.root;
		t.rotateRight();
		System.out.println(testTree3);
		t = t.right;
		
		System.out.println(t);
		System.out.println(t.left);
		System.out.println(t.right);
		*/
		
		
		
		
		
	}


	
}
