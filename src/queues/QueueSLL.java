package queues;

public class QueueSLL<E>{

	public static class Node<F> {
		//data fields
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
	
		
	}
	// data fields
	private Node<E> front;
	private Node<E> tail;
	private int size;
	
	// Constructor
	QueueSLL() {
		front = null;
		tail = null;
		size=0;
	}
	
	public void add (E item) {

		if (front==null) {
			front = new Node<>(item);
			tail = front;
		} else {
			tail.next = new Node<>(item);
			tail = tail.next;
		}
		size++;

	}
	
	public E remove() {
		if (front==null) {
			throw new IllegalStateException("remove: queue is empty");
		}
		E temp = front.data;
		if (front.next==null) {
			front=null;
			tail=null;
		} else {
			front = front.next;
		}
		size--;
		return temp;
		
	}
	
	public E element() {
		if (front==null) {
			throw new IllegalStateException("remove: queue is empty");
		}
		return front.data;
		
	}
	
	public Boolean isEmpty() {
		return front==null;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		
		Node<E> current=front;
		s.append("[front]<-");
		while (current!=null) {
			s.append(current.data.toString()+ "<-");
			current=current.next;
		}
		s.append("<-[tail]");
		return s.toString();
			
		}
				
	public static void main(String[] args) {
		QueueSLL<String> q = new QueueSLL<>();
		
		q.add("one");
		q.add("two");
		q.add("three");
		
		System.out.println(q);
		q.remove();
		System.out.println(q);
		
		
	}

}
