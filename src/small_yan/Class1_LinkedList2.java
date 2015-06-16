package small_yan;

import java.util.HashSet;

import debug.Debug;
import ds.DListNode;
import ds.ListNode;

public class Class1_LinkedList2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
		test5();
	}
	
	/*
	 * Slow/Fast Pointers
	 * -- fast pointer moves with a faster speed then slow
	 * -- fast pointer moves a few steps prior to slow pointer, then move with the same speed
	 * -- fast/slow pointers move with different conditions..
	 * 
	 * !!! This usually apply to array questions as well. 
	 * 
	 * 1 mid node
	 * 2 check if linked list has cycle/ return the node where cycle starts
	 * 3 determine the mid node of linked list that possibly has cycle
	 * 4 get/ delete nth node from tail
	 * 5 determine the lngest sub list not containing duplicate values
	 *  
	 */
	public static void test1() {
		ListNode head = Debug.createLinkedList(3);
		ListNode mid = task1_getMidNode(head);
		Debug.printLinkedList(head);
		Debug.printLinkedList(mid);
	}
	
	
	/*
	 * 1->2->3     return 2
	 * 1->2->3->4  return 2
	 */
	public static ListNode task1_getMidNode(ListNode head) {
		if (head == null || head.next == null || head.next.next == null) {
			return head;
		}
		ListNode fast = head.next;
		ListNode slow = head;
		while(fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}
	
	
	public  static boolean  task2_1_check_cycle(ListNode head) {
		if (head == null || head.next == null) {
			return false;
		}
		ListNode fast = head;
		ListNode slow = head;
		while(fast != null && fast.next != null && slow != null && slow.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			
			if (fast == slow) {
				return true;
			}
		}
		return false;
	}
	
	public  static ListNode  task2_2_check_cycle_node(ListNode head) {
		if (head == null || head.next == null) {
			return null;
		}
		ListNode fast = head;
		ListNode slow = head;
		while(fast != null && fast.next != null && slow != null && slow.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			
			if (fast == slow) {
				break;
			}
		}
		
		if (fast == null || fast.next == null) {
			// there is no cycle
			return null;
		}
		slow = head;
		while(fast !=  slow) {
			fast = fast.next;
			slow = slow.next;
		}
		return fast;
	}
	
	/*
	 * task3
	 * determine the mid node of linked list that possibly has cycle
	 */
	
	/*
	 * task4
	 * get/ delete nth node from tail
	 */
	
	/*
	 * task5
	 * determine the longest sub list not containing duplicate values
	 * 1 -> 2 ->3 ->1 ->4 -> null, longest is 2 ->3 ->1 ->4
	 * 
	 * array: {1,2,3,1,4} longest subarray not containing duplicate values
	 * longest substring without duplicate characters
	 * 
	 * 1 use hashSet to store the values
	 * 2 slow faster pointer, no duplicated
	 *   a) when to mover fast: if fast.val not in the HashSet
	 *   b) when to move slow: if fast.val in the HashSet
	 * 3 count: number of nodes between slow and fast. count == set.size()
	 * 4 return max(count) 
	 */
	
	public static void test5() {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(1);
		ListNode n5 = new ListNode(4);
		
		n1.next =n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		
		int max = task5_longest_sublist_no_duplicate(n1);
		System.out.println("max = " + max);
		
		
	}
	
	public static int task5_longest_sublist_no_duplicate(ListNode head) {
		if (head == null) {
			return 0;
		}
		ListNode fast = head;
		ListNode slow = head;
		HashSet<Integer> myset = new HashSet<Integer>();
		int result = 0;
		while(fast != null) {
			if (!myset.contains(fast.value)) {
				myset.add(fast.value);
				fast = fast.next;
			} else {
				//move slow
				myset.remove(slow.value);
				slow = slow.next;
			}
			result = Math.max(result, myset.size());
		}
		return result;
	}
	
	/*
	 * 6 determine the longest sub list in a circular list not containing duplicate values
	 * 
	 * !!!
	 */
	
	/*
	 * task7
	 * 拍扁
	 * More generally, the list can be treated as Graph Example:
	 * ­ Clone Linked List with random pointer
	 * ­ Clone Graph
	 */
	
	/*
	 * task8
	 * 1. 拍扁一个double linked list with child successor.
	 * each child is the head of a separate list.
	 */
	
	public static class ListNodeC {
		ListNodeC child;
		ListNodeC next;
		ListNodeC prev;
		int val;
	} 
	
	
	/*
	 * task9
	 * HashMap + Double Linked List
	 * key points;
	 * --- HashMap provide average O(1) lookup
	 * --- Double linked list provide O(1) remove, appendTail, appendHead
	 * 
	 * task9.1
	 * LRU(last recently used cache)
	 * 
	 * HashMap + Double Linked List
	 */
	
	
	
	
	
	
	
	/*
	 * task9.2
	 * First Non-Repeating Character In Stream
	 * A stream of characters, read one by one, need to know at any time, the earlist read no-repeating character
	 * in the stream.
	 */
	
	
	
	
	/* 
	 * task9.3
	 * Implement a Stack with findMid(), at any time return the mid element in O(1).
	 * pop(), push(), findMid() O(1) time complicity
	 * 
	 */
	
	
	
	
	
	
	

}
