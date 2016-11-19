 package lai_online;

import java.util.Deque;
import java.util.*;


public class Class27 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test3();
	}
	
	/*
	 * task1: Kth Smallest In Two Sorted Array
	 * task2: 
	 * task3: Max Number in K Sliding Windows
	 * task4: LRU cache
	 * task5: First No-Repeating Character In Stream
	 * task6: Interval Arrays
	 */
	/*
	 * task3
	 * (Array) Sliding window of size k, always return the max element in the window size
	 * 1 3 2 5 8 9 4 7 3
	 */
	public static void test3() {
		int[] array = {1,3,2,5,8,4,7,3};
		int k = 3;
		int[] result = task3_maxSlidingWindow(array, k);
		System.out.println(Arrays.toString(result));
	}
	
	public static int[] task3_maxSlidingWindow(int[] array, int k) {
		if (array == null || array.length < k) {
			return new int[0];
		}
		int n = array.length;
		int[] result = new int[n - k + 1];
		int index = 0;
		Deque<Integer> dq = new LinkedList<Integer>(); 
		// store the Index of Element in descending order
		for(int i = 0; i < n; i ++) {
			int curElem = array[i];
			while(!dq.isEmpty() && array[dq.peekLast()]  < curElem) {
				dq.pollLast();
			}
			// add the curElem into dq
			dq.offerLast(i);
			while(!dq.isEmpty() && dq.peekFirst() <= i - k) {
				// out of the window
				dq.pollFirst();
			}
			// we onl have n - k + 1 elements in the result
			if (i >= k - 1) {
				result[index ++] = array[dq.peekFirst()];
			}
		}
		return result;
	}
	
	
	/*
	 * LRU Cache
	 * refer LRU Cache in the same directory
	 */
	
	
	

}
