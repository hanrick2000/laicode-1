package small_yan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

import debug.Debug;

public class Class7_PriorityQueue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test4();
	}
	
	/*
	 * task1
	 * Merge K sorted array/List
	 * 1 merge 2 sorted arrays
	 * 2 merge 2 sorted lists
	 * 
	 */
	
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
