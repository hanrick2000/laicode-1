package lai_online;

public class Class12_DP1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * task1
	 * Longest Ascending SubArray
	 * Easy DP
	 * Given an unsorted array, find the length of the longest subarray in 
	 * which the numbers are in ascending order.
	 * Assumptions
	 * The given array is not null
	 * Examples
	 * {7, 2, 3, 1, 5, 8, 9, 6}, longest ascending subarray is {1, 5, 8, 9}, length is 4.
	 * {1, 2, 3, 3, 4, 4, 5}, longest ascending subarray is {1, 2, 3}, length is 3.
	 */
	
	public static int longest(int[] array) {
	    // write your solution here
	    if(array == null || array.length == 0) {
	      return 0;
	    }
	    if (array.length == 1) {
	      return 1;
	    }
	    int curLen = 1;
	    int maxLen = Integer.MIN_VALUE;  // global max
	    for(int i = 1; i < array.length; i++) {
	      if (array[i] > array[i - 1]) {
	        curLen ++;
	      } else {
	        curLen = 1;
	      }
	      maxLen = Math.max(curLen, maxLen);
	    }
	    return maxLen;
	    
	  }
	
	
	/*
	 * task2
	 * Max Product Of Cutting Rope Fair DP
	 * Given a rope with positive integer-length n, how to cut the rope into m integer-length parts 
	 * with length p[0], p[1], ...,p[m-1], in order to 
	 * get the maximal product of p[0]*p[1]* ... *p[m-1]? 
	 * m is determined by you and must be greater than 0 (at least one cut must be made). 
	 * Return the max product you can have.
	 * Assumptions
	 * n >= 2
	 * Examples
	 * n = 12, the max product is 3 * 3 * 3 * 3 = 81(cut the rope into 4 pieces with length of each is 3).
	 */

}
