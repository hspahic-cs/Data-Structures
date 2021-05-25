package Treaps;

import static org.junit.Assert.*;
import org.junit.Test;

public class TreapTest {
		
		@Test()
		public void testAdd() {
			Treap testTree = new Treap<Integer>();	
			testTree.add(4, 19);
			testTree.add(2, 31);
			testTree.add(6,70);
			testTree.add(1,84);
			testTree.add(3, 12);
			testTree.add(5, 83);
			testTree.add(7, 26);
			
			assertTrue(testTree.find(4));
			assertTrue(testTree.find(2));
			assertTrue(testTree.find(6));
			assertTrue(testTree.find(1));
			assertTrue(testTree.find(3));
			assertTrue(testTree.find(5));
			assertTrue(testTree.find(7));
		}

		@Test()
		public void testFind() {
			Treap testTree = new Treap<Integer>();	
			testTree.add(4, 19);
			testTree.add(2, 31);
			testTree.add(6,70);
			testTree.add(1,84);
			testTree.add(3, 12);
			testTree.add(5, 83);
			testTree.add(7, 26);
			
			assertTrue(testTree.find(4));
			assertTrue(testTree.find(2));
			assertTrue(testTree.find(6));
			assertTrue(testTree.find(1));
			assertTrue(testTree.find(3));
			assertTrue(testTree.find(5));
			assertTrue(testTree.find(7));
			
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
			
			assertTrue(testTree2.find('p'));
			assertTrue(testTree2.find('x'));
			assertTrue(testTree2.find('w'));
			
			assertFalse(testTree2.find('k'));
			assertFalse(testTree2.find('t'));	
			}
		
		@Test()
		public void testDelete() {
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
			
			testTree2.delete('x');
			assertFalse(testTree2.find('x'));
			
			testTree2.delete('p');
			assertFalse(testTree2.find('p'));
			
			testTree2.delete('r');
			assertFalse(testTree2.find('r'));
			
			testTree2.delete('z');
			assertFalse(testTree2.find('z'));
		}
		
}
