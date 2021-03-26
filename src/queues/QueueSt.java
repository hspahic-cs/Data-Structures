package queues;

import stacks.StackSLL;
import stacks.StackSLL.Node;

//Harris Spahic
// I pledge my honor I have abided by the Steven's Honor System

public class QueueSt<E> {

		// data fields
		private StackSLL<E> left;
		private StackSLL<E> right;
		private int size;

		// Constructor
		QueueSt(){
			this.left = new StackSLL<E>();
			this.right = new StackSLL<E>();
			this.size = 0;
		}

		public void add (E item) {
			left.push(item);
			size ++;
		}

		public E remove() {
			if (size == 0){
				throw new IllegalStateException("remove: queue is empty");
			}

			E temp = null;

			if (right.getSize() != 0){
				temp = right.pop();
			}

			else{
			Node<E> current = left.getTop();
			while (current!= null){
				right.push(left.pop());
				current = current.getNext();
			}
				temp = right.pop();
			}

			size --;
			return temp;

		}

		public E element() {
			if (size == 0) {
				throw new IllegalStateException("element: queue is empty");
			}

			if(right.getSize() > 0){
				return right.peek();
			}

			else {
				Node<E> current = left.getTop();
				while (current.getNext() != null) {
					current = current.getNext();
				}
				return current.getData();
			}

		}

		// Corrected Code

		public Boolean isEmpty() {
			return right.isEmpty() && left.isEmpty();
		}

		public String toString() {
			StringBuilder s = new StringBuilder();
			StackSLL<E> temp = new StackSLL<E>();
			
			Node<E> current = left.getTop();
			while (current != null) {
				temp.push(current.getData());
				current = current.getNext();
			}
		
			s.append("[Front]" + right.toString() + temp.toString() + "[Tail]");
			return s.toString();

		}

		public static void main(String[] args) {
			QueueSt<String> q = new QueueSt<>();

			System.out.println(q);
			q.add("one");
			q.add("two");
			q.add("three");

			System.out.println(q);
			q.remove();
			System.out.println(q);

			
			q.add("four");
			q.add("five");
			q.add("six");
			q.add("seven");
			
			System.out.println(q);

		}


	}