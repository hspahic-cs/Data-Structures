package basics;

// CHECKED ^

public class SLL<E> {

	public static class Node<F>{
		// data fields
		F data;
		Node<F> next;
			
		public Node(F data, Node<F> next) {
			super();
			this.data = data;
			this.next = next;
		}

		public Node(F data) {
			super();
			this.data = data;
			this.next = null;
		}
		
	}
	
	// data fields
	Node<E> head;
	int size;
	
	public SLL() {
		head=null;
		size=0;
	}
	
	public int getSize() {
		return size;
	}
	
	public void addFirst(E item) {
		head = new Node<>(item,head);
		size++;
	}
	
	public void addLast(E item) {
		add(item, size);
	}
	
	public void add(E item, int index) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException("add: index out of bounds");
		}
		if (index == 0) {
			addFirst(item);
		}
		else {
			Node<E> current = head;
			for (int i=0; i < index -1; i++) {
				current = current.next;
			}
			
			current.next = new Node<>(item, current.next);
			size++;
		}
	}
	
	public E getAt(int index) {
		if (index<0 || index>size) {
			throw new IllegalArgumentException("getAt: index out of bounds");
		}
		Node<E> current = head;
		for (int i=0; i<index; i++) {
			current=current.next;
		}
		
		return current.data;
	}	
	
	public E removeFirst() {
		if (head==null) {
			throw new IllegalStateException("removeFirst: list is empty");
		}
		E temp = head.data;
		head = head.next;
		size--;
		return temp;
	}
	
	public E remove(int index) {
		if (index<0 || index>size) {
			throw new IllegalArgumentException("remove: index out of bounds");
		}
		if (index==0) {
			return removeFirst();
		} else {
			Node<E> current = head;
			for (int i=0; i<index-1; i++) {
				current=current.next;
			}
			E temp = current.next.data;
			current.next = current.next.next;
			size--;
			return temp;
		}
	}
	
	//Removes the first instance of the provided item
	public void removeItem(E item) {
		if (head == null) {
			throw new IllegalStateException("removeItem: list is empty");
		}
		
		Node<E> current = head;
		boolean found = false;
		
		while (current != null) {
			if (current.next.data == item) {
				current.next = current.next.next;
				found = true;
				break;
			}
			else {
				current = current.next;
			}
		}
		
		if (!found) {
			throw new IllegalArgumentException("removeItem: item is not in list");
		}
		
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		
		Node<E> current = head;
		s.append("[");
		while (current!=null) {
			s.append(current.data.toString()+";");
			current = current.next;
		}
		s.append("]");
		return s.toString();
		
	}
	
	public static void main(String[] args) {
		SLL<Integer> test = new SLL<Integer>();
		test.addFirst(5);
		test.addFirst(4);
		test.addFirst(3);
		test.addFirst(2);
		System.out.println(test);
		
		test.removeItem(5);
		System.out.println(test);
	}
}
