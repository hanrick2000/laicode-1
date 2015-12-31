package small_yan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import debug.Debug;
import ds.ListNode;

public class Class7_PriorityQueue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test4();
//		test2();
		test1();
	}
	
	/*
	 * task1
	 * 
	 */
	
	/*
	 * Merge K sorted array/List
	 * 1 merge 2 sorted arrays
	 * 2 merge 2 sorted lists
	 * 
	 * task1_1 How to merge k sorted short array​ into one long sorted array?
	 * 
	 * M1: binary- reduction
	 *     k arrays, every array has n elements. 
	 *     Time: O(nk * log(k))
	 * 
	 * M2: minHeap
	 * 	   Time: O(nk * log(k))  
	 * HeapElement {
	 * 	 double value;
	 *   int index;  // for kth array
	 *   int pos;    // position at original array. 
	 * }  
	 */
	
	// binary reduction
	public static int[] task1_1MergeKSortedArray(ArrayList<int[]> listOfArrays) {
		return task1_1Helper(listOfArrays, 0, listOfArrays.size() - 1);
	} 
	
	public static int[] task1_1Helper(ArrayList<int[]> listOfArrays, int left, int right) {
		if (left == right) {
			return listOfArrays.get(left);
		}
		
		int mid = left + (right - left)/2;
		int[] leftSide = task1_1Helper(listOfArrays, left, mid);
		int[] rightSide = task1_1Helper(listOfArrays, mid + 1, right);
		return task1_1_merge2Arrays(leftSide, rightSide);	
	}
	
	public static int[] task1_1_merge2Arrays(int[] array1, int[] array2) {
		// sanity check
		if (array1 == null || array1.length == 0) {
			return array2;
		}
		if (array2 == null || array2.length == 0) {
			return array1;
		}
		int[] mergedArray = new int[array1.length + array2.length];
		int index = 0;
		int i = 0, j = 0;
		while(i < array1.length && j < array2.length) {
			if (array1[i] < array2[j]) {
				mergedArray[index++] = array1[i++];
			} else {
				mergedArray[index ++] = array2[j ++];
			}
		}
		
		while(i < array1.length) {
			mergedArray[index++] = array1[i];
			i ++;
		}
		
		while (j < array2.length) {
			mergedArray[index ++] = array2[j ++];
		}
		
		return mergedArray;
	}
	
	public static void test1() {
		int[][] matrix = {
				{},
				{1,5,7},
				{4},
				{2,3,5,11},
				{2,4,4,6,8}
		};
//		int[] merge = task1_2MergeKSortedArray(matrix);
		
		ArrayList<int[]> listOfArrays = new ArrayList<int[]>();
		for(int i = 0; i < matrix.length; i ++) {
			listOfArrays.add(matrix[i]);
		}
		int[] merge = task1_1MergeKSortedArray(listOfArrays);
		for(int i = 0; i < merge.length ; i ++) {
			System.out.print(merge[i] + " ");
		}
		System.out.println();
	}
	
	public static class HeapElement {
		public int val;
		public int index;
		public int pos;
		public  HeapElement(int v, int i, int pos) {
			this.val = v;
			this.index = i;
			this.pos = pos;
		}
	}
	
	public static int[] task1_2MergeKSortedArray(int[][] arrayOfArrays) {
		if (arrayOfArrays == null || arrayOfArrays.length == 0 ) {
			return null;
		}
		int rowLen = arrayOfArrays.length;
		ArrayList<Integer> rev = new ArrayList<Integer>();
		Comparator<HeapElement> myComp = new Comparator<HeapElement>() {
			@Override
			public int compare(HeapElement o1, HeapElement o2) {
				// TODO Auto-generated method stub
				if (o1.val == o2.val) {
					return 0;
				}
				return o1.val < o2.val ? -1 : 1; 
			}
		};
		// create a minHeap
		PriorityQueue<HeapElement> q = new PriorityQueue<HeapElement>(rowLen, myComp);
		
		// add the the first element of all rows into the priority queue
		for(int i = 0; i < rowLen; i ++) {
			// !!! Note, we need to check whether the array.length > 0
			if (arrayOfArrays[i] != null && arrayOfArrays[i].length > 0) {
				HeapElement element = new HeapElement(arrayOfArrays[i][0], i, 0);
				q.offer(element);
			}
		}
		
		while(!q.isEmpty()) {
			HeapElement cur = q.poll();
			rev.add(cur.val);
			
			// put the cur's next into minHeap
			if (arrayOfArrays[cur.index] != null && cur.pos < arrayOfArrays[cur.index].length - 1) {
				HeapElement elem = new HeapElement(arrayOfArrays[cur.index][cur.pos + 1], cur.index, cur.pos + 1);
				q.offer(elem);
			}
		}
		
		int[] result = new int[rev.size()];
		for(int i = 0; i < rev.size(); i ++) {
			result[i] = rev.get(i);
		}
		return result;
	}
	
	
	/*
	 * task2 How to merge k sorted LinkedList into one big Linked List. 
	 * simpler. 
	 */
	public static void test2() {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(5);
		ListNode n3 = new ListNode(9);
		n1.next = n2;
		n2.next = n3;
		
		ListNode n4 = new ListNode(2);
		ListNode n5 = new ListNode(4);
		ListNode n6 = new ListNode(11);
		n4.next = n5;
		n5.next = n6;
		
		ListNode n7 = new ListNode(1);
		ListNode n8 = new ListNode(2);
		ListNode n9 = new ListNode(7);
		n7.next = n8;
		n8.next = n9;
		
		Debug.printLinkedList(n1);
		Debug.printLinkedList(n4);
		Debug.printLinkedList(n7);
			
		List<ListNode> listofList = new ArrayList<ListNode>();
		listofList.add(n1);
		listofList.add(n4);
		listofList.add(n7);
		
		ListNode rev = task2_2MergeKLists(listofList);
		System.out.println("------------------------");
		Debug.printLinkedList(rev);
	} 
	
	// method 1: binary reduction
	public static ListNode task2_1MergeKList(List<ListNode> listOfLists) {
		if(listOfLists == null || listOfLists.size() == 0) {
			return null;
		}
		int left = 0, right = listOfLists.size() - 1;
		return task2_1helper(listOfLists, left, right);
	}
	
	public static ListNode task2_1helper(List<ListNode> lists, int left, int right) {
		if (left < right) {
			int mid = left + (right - left)/2;
			// merge the left side
			ListNode leftSide = task2_1helper(lists, left, mid);
			ListNode rightSide = task2_1helper(lists, mid + 1, right);
			return task2_1merge2Lists(leftSide, rightSide);
		}
		return lists.get(left);
	}
	
	
	// return newHead after merged two sorted list. 
	public static ListNode task2_1merge2Lists(ListNode list1, ListNode list2) {
		ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (list1 != null && list2 != null) {
            if (list1.value < list2.value) {
                tail.next = list1;
                tail = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                tail = list2;
                list2 = list2.next;
            }
        }
        if (list1 != null) {
            tail.next = list1;
        } else {
            tail.next = list2;
        }
        
        return dummy.next;
	}
	
	
	// method2: use minHeap
	public static ListNode task2_2MergeKLists(List<ListNode> lists) {
		if (lists == null || lists.size() == 0) {
			return null;
		}
		int size = lists.size();
		
		// minHeap
		PriorityQueue<ListNode> q = new PriorityQueue<ListNode>(size, new Comparator<ListNode>() {

			@Override
			public int compare(ListNode o1, ListNode o2) {
				// TODO Auto-generated method stub
				return o1.value - o2.value;
			}

		});
		
		// add all head into the priority queue
		for(ListNode node: lists) {
			if (node != null) {
				q.add(node);
			}
		}
		
		ListNode newHead = null;
		ListNode tail = newHead;
		
		while(!q.isEmpty()) {
			ListNode cur = q.poll();
			if (newHead == null) {
				newHead = cur;
				tail = newHead;
			} else {
				tail.next = cur;
				tail = tail.next;
			}
			
			if (cur.next != null) {
				q.offer(cur.next);
			}
		}
		return newHead;	
	}
	
	
	
	
	/*
	 * task2
	 * 1 kth-smallest/largest element in sorted array
	 * 2 kth smallest in Young's matrix
	 * 3 kth smallest sum of pair from two arrays
	 * 4 the k points in 3-d space closest to point to (0,0,0)
	 */
	
	
	/*
	 * task3 
	 * the most k frequent visited urls
	 */
	
	/*
	 * task4
	 * k-diff sorted array(each of the element has at most k as distance to its sorted position), how to sorted it efficiently
	 * 1 naive: jsut sorted
	 * 2 maintain k+1 sized minHeap.
	 *   for each index, poll(), offer()
	 *   Time: N log k
	 *   
	 * e.g  
	 * A = {3,2,1,5,4,6} k = 2
	 * A' = {1,2,3,4,5,6}
	 */
	public static void test4() {
		int[] A = {3,2,1,5,4,6};
		Debug.printArray(A);
		int k = 2;
		task4_kdiff_sort(A, k);
		Debug.printArray(A);
	}
	
	public static void task4_kdiff_sort(int[] a, int k) {
		if (a == null || a.length == 0) {
			return;
		}
		if (k >= a.length) {
			Arrays.sort(a);
			return;
		}
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(k + 1);
		System.out.println("minHeap.size = " + minHeap.size());
		int index = 0;
		for(int i = 0; i < a.length; i ++) {
			if (minHeap.size() == k + 1) {
				while(!minHeap.isEmpty()) {
					a[index ++] = minHeap.poll();
				}	
			}
			minHeap.add(a[i]);
		}
		while(!minHeap.isEmpty()) {
			a[index ++] = minHeap.poll();
		}
	}
	
	/*
	 * task5 
	 * k arrays, each has size n, pick one element from each of them to conduct a k­ sized array, 
	 * among all of these k­sized arrays, find the kth array with smallest sum, return the sum.
	 * 
	 * Expand:  ()
	 * Generate Rule:
	 *   (index1, index2, index3, ..., indexk)
	 *  
	 * for the deduplication, we can use an k-d matrix. 
	 * 
	 */
	public static int task5_kth_smallestSum(ArrayList<ArrayList<Integer>> input) {
		int k = input.size();
		
		return -1;
	}
	
	/*
	 * task6
	 * median of stream data flow
	 * 5% percentile of stream data flow
	 * 1 median = 50%, similar to median, maintain two heaps
	 */
	
	/*
	 * task7
	 * kth smallest of stream data flow
	 * keep a k size maxHeap. 
	 * every time, peek maxHeap.peek()
	 */
	
	/*
	 * task8
	 * id, name, score, 
	 * 1, A, 50
	 * 2, B, 70
	 * 3, C, 80→20 
	 * 4, D, 60
	 * 5, E, 40
	 * Find the max score in O(1) Update the score in O(logn).
	 * 
	 * Priority Queue + HashMap   
	 */
	
	/*
	 * task9
	 * data structure: support median, insert(), remover() operation
	 * 
	 * Priority Queue + hashMap
	 */
	
	/*
	 * task10
	 * skyline problem, list of buildings with (start, end, height), what is the skyline? 
	 * 
	 */
	
	/*
	 * task11
	 * give an array of process, each has start, end, load,
	 * find the process with max load at each time step and print it
	 * 
	 */
	
	/*
	 * task12
	 * Hoffman Encoding
	 * 
	 */
	

}
