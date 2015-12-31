package small_yan;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.w3c.dom.Node;

public class Class1_FirstNonRepeating {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * HashMap with Double Linked List
	 */
	
	public static class Node{
		char key;
		Node prev;
		Node next;
		public Node(char k) {
			// TODO Auto-generated constructor stub
			this.key = k;
		}
	}
	
	HashMap<Character, Node> map;
	private Node head;
	private Node tail;
	Set<Character> set;
	
	// constructor
	public Class1_FirstNonRepeating() {
		map = new HashMap<Character, Class1_FirstNonRepeating.Node>();
		set = new HashSet<Character>();
	}
	
	/*
	 * read a char from stream
	 * if not exist in map, add it to the tail of DLL
	 * 
	 */
	public void read(char ch) {
		if (map.containsKey(ch)) {
			if (map.get(ch) == null) {
				// ch exists before, but already has duplicates. do nothing
			} else {
				// ch exists before, but this is the second time it appears
				remove(ch);
			}
		} else {
			addNode(ch);
		}
	}
	
	public Character firstNoRepeating() {
		if (head == null) {
			return null;
		}
		return head.key;
	}
	
	
	/*
	 * addNode(char ch)
	 * add Node(ch) to the tail of DDL and update map
	 */
	private void addNode(char ch) {
		Node node = new Node(ch);
		if (tail == null) {
			tail = node;
			head = node;
		} else {
			tail.next = node;
			tail = tail.next;
		}
	}
	
	private Node remove(char ch) {
		Node node = null;
		if (map.containsKey(ch)) {
			// map contains ch
			node = map.get(ch);

			if (node.prev != null) {
				node.prev.next = node.next;
			}
			if (node.next != null) {
				node.next.prev = node.prev;
			}

			// edge case
			if (node == head) {
				head = node.next;
			}

			if (node == tail) {
				tail = node.prev;
			}
			node.prev = null;
			node.next = null;
			
			map.put(ch, null);
		}

		return node;
	}
	
	
	
	
	

}
