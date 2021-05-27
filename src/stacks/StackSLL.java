package stacks;

import stacks.StackSLL.Node;

public class StackSLL<E> {

	public static class Node<F> {
		// data fields
		private F data;
		private Node<F> next;
		
		public Node(F data, Node<F> next) {
			super();
			this.data = data;
			this.next = next;
		}

		public Node(F data) {
			super();
			this.data = data;
		}	
		
		public Node<F> getNext(){
			return next;
		}
		
		public F getData() {
			return data;
		}
		
	}
	
	// data fields
	private Node<E> top;
	private int size;
	
	public StackSLL() {
		top = null;
		size = 0;
	}
	
	public void push(E item) {
		top = new Node<>(item,top);
		size++;
	}
	
	public E peek() {
	    if (top==null) {
	    	throw new IllegalStateException("peek: empty stack");
	    }	
		return top.data;
	}
	
	
	public E pop() {
		if (top==null) {
			throw new IllegalStateException("pop: empty stack");
		}
		E temp = top.data;
		top = top.next;
		size--;
		return temp;
	}
	
	public Boolean isEmpty() {
		return top==null;
	}
	
	public String toString() {
		StringBuilder r = new StringBuilder();
		
		Node<E> current = top;
		r.append("[top] <- ");
		while (current!=null) {
			r.append(current.data.toString()+" <- ");
			current=current.next;
		}
		r.append("[bottom]");
		return r.toString();
	}
	
	public static void main(String[] args) {
		StackSLL<String> s = new StackSLL<>();
		
		s.push("one");
		s.push("two");
		s.push("three");
		
		System.out.println(s);
		
		s.pop();
		
		System.out.println(s);
		
		
		
	}

	public int getSize() {
		// TODO Auto-generated method stub
		return size;
	}

	public Node<E> getTop() {
		// TODO Auto-generated method stub
		return top;
	}
	
}

