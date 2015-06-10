package lab;

import java.util.Arrays;

public class MyHashMap<V, K> {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	static class Node<K, V> {
		// the key is not supposed to be changed once the Entry
		// for the key is constructed.
		final K key;
		V value;
		Node<K, V> next;

		Node(K key, V value, Node<K, V> next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public V setValue(V value) {
			V old = this.value;
			this.value = value;
			return old;
		}

		// for pretty print purpose
		@Override
		public String toString() {
			return String.valueOf(key) + ": " + String.valueOf(value);
		}
	}

	private Node<K, V>[] table;
	private int size; // how many entries are in the table.

	public MyHashMap(int capacity) {
		if (capacity <= 0) {
			throw new IllegalAccessError(".....");
		}
		this.table = (Node<K, V>[]) new Node[capacity];
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	public void clear() {
		Arrays.fill(this.table, null);
	}
	
	
	// O(n)
	//
	public boolean containsValue(V value) {
		if (isEmpty()) {
			return false;
		}
		for(Node<K, V> head: table) {
			while(head != null) {
				if (equalsValue(value, head.getValue())) {
					return true;
				}
			}
		}
		return false;
	}
	
	//
	private boolean equalsValue(V v1, V v2) {
		// handle the case v1 == null || v2 == null
		return v1 == v2 || v1!= null && v1.equals(v2);
	}
	
	private boolean equalsKey(K k1, K k2) {
		// handle the case v1 == null || v2 == null
		return k1 == k2 || k1!= null && k1.equals(k2);
	}

	// return int value is no-negative
	private int hash(K key) {
		// key == null
		if (key == null) {
			return 0;
		}	
		// 
//		int hashNum = key.hashCode();
//		return hashNum < 0 ? -hashNum : hashNum;
		
		return key.hashCode() & 0x7FFFFFFF;
		// set the sign num == 0
		// hashNum = Integer.min_value, overflow
	}
	
	// given a key, return all the node contains the key, or null if the key is not in the hashMap
	private  Node<K, V> getNode(K key) {
		if (isEmpty()) {
			return null;
		}
		// get index
		int index = getIndex(key);
		// traverse the Linked list
		Node<K, V> head = table[index];
		while(head != null) {
			if (equalsKey(key, head.getKey())) {
				return head;
			}
			head =head.next;
		}
		return null;
	}
	
	public boolean containsKey(K key) {
		return getNode(key) !=null;
	}
	
	public V get(K key) {
		Node<K, V> node = getNode(key);
		return node == null ? null: node.getValue();
	}
	
	public V put(K key, V value) {
		Node<K, V> node = getNode(key);
		// 1 
		if (node != null) {
			return node.setValue(value);
		}
		// key not exist in the hashMap
		int index = getIndex(key);
		 Node<K, V> newHead = new Node<K, V>(key, value, null);
		 newHead.next = table[index];
		 table[index] = newHead;
		 size ++;
		 return null;	
	}
	
	public V remove(K key) {
		int index = getIndex(key);
		Node<K, V> head = table[index];
		// 
		return head.getValue();
 	}
	
	private int getIndex(K key) {
		return hash(key) % table.length;
	}
}
