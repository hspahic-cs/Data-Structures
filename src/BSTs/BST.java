package BSTs;

public class BST<E extends Comparable<E>> extends BTree<E>{
	BST() {
		super();
	}
	
	BST(E data) {
		super(data);
	}
	
	BST(E data, BST<E> left, BST<E> right) {
		super(data,left,right);
	}
	
	/**
	 * Returns the largest element in a non-empty binary tree (not necessarily a BST)
	 * @param current
	 * @return
	 */
	
	private E max(Node<E> current) {
		
		if (current == null) {
			return null;
		}
		
		E res = current.data;
		E lres = max(current.getLeft());
		E rres = max(current.getRight());
		
			if(lres != null) {
				if (res.compareTo(lres) < 0) {
					res = lres;
				}
			}
			
			if(rres != null) {
				if(res.compareTo(rres) < 0) {
					res = rres;
				}
			}
		
		return res;
	}
	
	public E max() {
		return max(root);
	}
	
	
	/**
	 * Returns the smallest element in a non-empty binary tree (not necessarily a BST)
	 * @param current
	 * @return
	 */
	private E min(Node<E> current) {
		if (current == null) {
			return null;
		}
		
		E res = current.data;
		E lres = min(current.getLeft());
		E rres = min(current.getRight());
		
			if(lres != null) {
				if (res.compareTo(lres) > 0) {
					res = lres;
				}
			}
			
			if(rres != null) {
				if(res.compareTo(rres) > 0) {
					res = rres;
				}
			}
		
		return res;
	}
	
	public E min() {
		return min(root);
	}
	
	/**
	 * Returns minimum inOrder successor of BST (not necessarily a BST)
	 * @param current
	 * @return
	 */
	
	private Node<E> minPointer(Node<E> current){
		
		Node<E> minv = current;
		
		while(current.getLeft() != null) {
			minv = current.getLeft();
			current = current.getLeft();
		}
		
		return minv;
	}
	
	private Boolean isBst(Node<E> current) {
		if (current==null) {
			return true;
		}
		
		int left = current.data.compareTo(current.getLeft().data);
		int right = current.data.compareTo(current.getRight().data);
		
		if (left <= 0 || right >= 0) {
			return false;
		}
		
		return isBst(current.getLeft()) && isBst(current.getRight());
	}
	
	
	/**
	 * Determines whether the recipient tree is a BST or not.
	 * @return true if it is a BST, false otherwise.
	 */
	public Boolean isBst() {
		return isBst(root);
	}
	
	private Node<E> add(E item, Node<E> current) {
		if (current==null) {
			return new Node<>(item);
		}
		
		int i = current.data.compareTo(item);
		if (i==0) {
			throw new IllegalStateException("add: duplicate key");
		}
		if (i<0) {
			current.setRight(add(item,current.getRight()));
			return current;
		} else {
			current.setLeft(add(item,current.getLeft()));
			return current;
		}
		
	}
	
	public void add(E item) {
		root = add(item,root);
	}
	
	/**
	 * Finds pointer to node with particular key
	 * @return pointer to node with key
	 * @param item: item to be found
	 * @param current: pointer to current node
	 * @exception IllegalStateException: thrown if item does not exist
	 */
	
	private Node<E> findKey(E item, Node<E> current){
		
		// If item not found
		if(current == null) {
			throw new IllegalStateException("remove: item does not exist in BST");
		}
		
		// If neither child has key, continue
		else {
			int dif = item.compareTo(current.data);
			
			if(dif > 0) {
				return findKey(item, current.getRight());
			}
			
			else if (dif < 0) {
				return findKey(item, current.getLeft());
			}
		}
		
		// If key found
		return current;
		
	}
	
	private Node<E> remove(E item, Node<E> current){

		// Throws exception if node not found
		if(current == null) {			
			throw new IllegalArgumentException("remove: item is not in BST");
		}
		
		// Finds current node
		int i = item.compareTo(current.data);
		
		if(i < 0) {
			current.setLeft(remove(item, current.getLeft()));
			return current;
 		}
		
		if(i > 0) {
			current.setRight(remove(item, current.getRight()));
			return current;
		}
		
		
		// Item is found, check cases

			// Either one child or none 
			if(current.getLeft() == null) {
				return current.getRight();
			}
			
			else if(current.getRight() == null) {
				return current.getLeft();
			}
			
			else {
				Node<E> temp = minPointer(current);
				remove(minPointer(current).data);
				current.data = temp.data;
				return current;
			}
	}
	
	
	
	public void remove(E item) {
		root = remove(item, root);
	}
	
	public static void main(String[] args) {
		BST<Integer> t1 = new BST<>(7,new BST<>(), new BST<>());
		BST<Integer> t2 = new BST<>(33,new BST<>(27,new BST<>(), new BST<>()),new BST<>(44,new BST<>(), new BST<>()));
		BST<Integer> t3 = new BST<>(23,t1,t2);
		
		System.out.println(t3);
//		System.out.println(t3.height());
//		System.out.println(t3.noOfLeaves2());
//		
//		System.out.println(t3.level(3));
		t3.add(55);
		System.out.println(t3);
		System.out.println(t3.max());
		System.out.println(t3.min());
		
		t3.remove(33);
		System.out.println(t3);
		
	}
	
}
