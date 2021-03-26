package SLL;

import SLL.StackSLL.Node;

public class StackSLL<E> {

	public static class Node<F> {
		// data fields
		private F data;
		private Node<F> next;
		
		public Node(F data, Node<F> next) {
			super();
			this.data = data;
			this.setNext(next);
		}

		public Node(F data) {
			super();
			this.data = data;
		}

		public Node<F> getNext() {
			return next;
		}

		public void setNext(Node<F> next) {
			this.next = next;
		}	
		
	}
	
	// data fields
	private Node<E> top;
	private int size;
	
	
	public StackSLL() {
		setTop(null);
		setSize(0);
	}
	
	public void push(E item) {
		setTop(new Node<>(item,getTop()));
		setSize(getSize() + 1);
	}
	
	public E peek() {
	    if (getTop()==null) {
	    	throw new IllegalStateException("peek: empty stack");
	    }	
		return getTop().data;
	}
	
	
	public E pop() {
		if (getTop()==null) {
			throw new IllegalStateException("pop: empty stack");
		}
		E temp = getTop().data;
		setTop(getTop().getNext());
		setSize(getSize() - 1);
		return temp;
	}
	
	public Boolean isEmpty() {
		return getTop()==null;
	}
	
	public String toString() {
		StringBuilder r = new StringBuilder();
		
		Node<E> current = getTop();
		r.append("[top] <- ");
		while (current!=null) {
			r.append(current.data.toString()+" <- ");
			current=current.getNext();
		}
		r.append("[bottom]");
		return r.toString();
	}

	public static boolean balancedParens(String s){
		StackSLL<Character> parens = new StackSLL<Character>();

		for (int i = 0, len = s.length(); i < len; i++){
			if (s.charAt(i) == '('){
				parens.push('(');
			}
			else{
				if(parens.isEmpty()){
					return false;
				}
				parens.pop();
				}
			}
		return parens.isEmpty();
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
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Node<E> getTop() {
		return top;
	}

	public void setTop(Node<E> top) {
		this.top = top;
	}
	
}