package BSTs;

import java.util.ArrayList;

public class BTree<E> {
	class Node<F>{
		F data;
		private Node<F> left;
		private Node<F> right;
	
	
	public Node(F data) {
		this.data = data;
		this.setLeft(null);
		this.setRight(null);
		}
	
	public Node(F data, Node<F> left, Node<F> right) {
		this.data = data;
		this.setLeft(left);
		this.setRight(right);
		}

	public Node<F> getLeft() {
		return left;
	}

	public void setLeft(Node<F> left) {
		this.left = left;
	}

	public Node<F> getRight() {
		return right;
	}

	public void setRight(Node<F> right) {
		this.right = right;
	}
	}

	public Node<E> root;
	private int size;

	public BTree() {
		root = null;
		size = 0;
	}
	
	public BTree(E data) {
		root = new Node<E>(data);
		size = 1;
	}
	
	public BTree(E data, BTree<E> left, BTree<E> right) {
		root = new Node<E>(data, left.root, right.root);
		size = 1 + left.size + right.size;
	}
	

	public int height() {
		return height(root);
	}
	
	public int height(Node<E> current) {
		if (current ==  null) {
			return 0;
		}
		else {
			return 1 + Math.max(height(current.getLeft()), height(current.getRight()));
		}
		
	}
	
	public int noOfLeaves() {
		return noOfLeaves(root);
	}
	
	private int noOfLeaves(Node<E> current){
		if (current == null) {	
			return 0;
		}
		
		if(current.getLeft() == null && current.getRight() == null) {
			return 1;
		}

		return noOfLeaves(current.getLeft()) + noOfLeaves(current.getRight());		
	}
	
	private ArrayList<E> level(int l, Node<E> current){
		ArrayList<E> lv = new ArrayList<E>();
		
		if(l != 0 && current != null) {
			lv.addAll(level(l-1, current.getRight()));
			lv.addAll(level(l-1, current.getLeft()));
		}
		
		else if(current != null) {
			lv.add(current.data);
		}
		
		return lv;
		
	}
	
	public ArrayList<E> level(int l){
		if (l<0) {
			throw new IllegalArgumentException("level: Invalid input");
		}
		
		return level(l, root);
	}
	
	
	private String toString(Node<E> current, int level) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i<level; i++) {
			s.append("--");
		}
		
		if (current == null) {
			s.append("null\n");
		}
		else {
			s.append(current.data.toString() + "\n");
			s.append(toString(current.getLeft(), level +1));
			s.append(toString(current.getRight(), level+1));
		}
		return s.toString();
	}
	
	public String toString() {
		return toString(root, 1);
	}
	
	public static void main(String[] args) {
		BTree<Integer> t1 = new BTree<>(4);
		BTree<Integer> t2 = new BTree<Integer>(33,new BTree<Integer>(24), new BTree<Integer>(44));
		BTree<Integer> t = new BTree<>(12,t1,t2);
		
		System.out.println(t1);
		System.out.println();
		System.out.println(t2);
		System.out.println();
		System.out.println(t);
		System.out.println(t.height());
		//System.out.println(t.noOfLeaves(t.root));
		System.out.println(t.level(2));
		
	}
	
	
	
}