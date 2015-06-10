package small_yan;

import debug.Debug;

public class Class8_DPI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test2();
//		test6_2();
		test8();
	}
	
	/*
	 * task1
	 * Longest Increasing XXX
	 * task1.1 longest Increasing SubArray
	 * task1.2 longest Increasing SubSequence
	 * 
	 * 
	 */
	
	/*
	 * task1.3 Set of points in 2-d space, how to find the largest subset of points such that
	 * each of the lines conducted by any pair of points in the subset has positive slope
	 * 
	 * (y2 - y1)/(x2 - x1) > 0
	 * 
	 * sorting by x,  longest inscreasing subsequence of y
	 */
	
	/*
	 * task1.4
	 * set of boxes, each one has (length, width, height), we want to put them as a stack,
	 * when we put b2 on b1, we need to guarantee that b2.length < b1.length && b2.width < b1.width
	 * 
	 * Longest Increasing Subsequence
	 */
	
	
	/*
	 * task1.5
	 *  
	 * 
	 */
	
	/*
	 * task2
	 * There are several rooms and in each of the rooms there is some amount of money, 
	 * if we can not pick the neighbor room’s money, what is the max amount we can have?
	 * 
	 *  
	 *  Look at the following 
	 *  http://www.geeksforgeeks.org/maximum-sum-such-that-no-two-elements-are-adjacent/
	 */
	public static void test2() {
		int[] a = {5,  5, 10, 40, 50, 35};
		
		System.out.println(maxSumNoAdjacent(a));
	}
	
	
	public static int maxSumNoAdjacent(int[] array) {
		if (array == null || array.length == 0) {
			return 0;
		}
		if (array.length == 1) {
			return array[0];
		}
		if (array.length == 2) {
			return Math.max(array[0], array[1]);
		}
		int incl = array[0];
		int excl = 0;
		for(int i = 1; i < array.length; i ++) {
			// incl
			int excl_new = Math.max(excl, incl);
			
			incl = excl + array[i];
			excl = excl_new;
		
		}
		return Math.max(incl, excl);
	}
	
	/*
	 * task2.1 
	 * make the rooms a cycle, what is the max amount we can have? the rule is not changed.
	 * !!!!
	 */
	
	/*
	 * task3
	 * page43 encoding 
	 * 
	 * finish later
	 */
	
	/*
	 * task4
	 * Paint House
	 * 
	 */
	
	/*
	 * task5
	 * N casino 30 day max profit, 每个赌场每天的盈利情况不一样,每天只能在一个赌场, 第二天只能跳跃到相邻的赌场
	 * 
	 * state[i][j]   i 表示赌场， j 表示天数
	 * state[i][j] = max(state[i - 1][j - 1], state[i + 1][j - 1]) + profit[i][j]
	 *                   from 赌场 i - 1       from 赌场 i + 1
	 * 
	 * result:max( state[i][30])
	 *                   
	 * 1st column -> 2nd column -> ...-> etc
	 */
	
	/*
	 * task6
	 * largest subarray sum
	 * {1, ­3, 1, 3, ­5} → 1 + 3 = 4
	 * 
	 */
	/*
	 * task6.1
	 * largest submatrix sum 
	 * O(n ^ 3)
	 */
	/*
	 * task6.2
	 * largest subarray product
	 * {1, ­-3, 1, 3, ­-5} → 1 * -­3 * 1 * 3 * -­5 = 45
	 * 
	 * stateMax[i] = max(statMax[i ­-1] * array[i], statMin[i - 1] * array[i], array[i])
	 * stateMin[i] = min(statMax[i ­-1] * array[i], statMin[i ­-1] * array[i], array[i])
	 * 
	 * 
	 */
	public static void test6_2() {
		int[] array = {6, -3, -10, 0, 2};
		System.out.println(task6_2_maxSubarrayProduct1(array));
		System.out.println(task6_2maxSubarrayProduct2(array));
		int[] array2 = {1, -2, -3, 0, 7, -8, -2};
		System.out.println(task6_2_maxSubarrayProduct1(array2));
		System.out.println(task6_2maxSubarrayProduct2(array2));
	}
	
	public static int task6_2_maxSubarrayProduct1(int[] array) {
		if (array == null || array.length == 0) {
			return 0;
		}
		int n = array.length;
		int[] stateMax = new int[n];
		int[] stateMin = new int[n];
		stateMax[0] = array[0];
		stateMin[0] = array[0];
		int max = Integer.MIN_VALUE;
		
		for(int i = 1; i < n; i ++) {
			stateMax[i] = Math.max(stateMax[i - 1] * array[i], Math.max(stateMin[i- 1]* array[i], array[i] ));
			stateMin[i] = Math.min(stateMax[i - 1] * array[i], Math.min(stateMin[i- 1]* array[i], array[i] ));
			
			if (max < stateMax[i]) {
				max = stateMax[i];
			}
		}
		return max;
	}
	
	public static int task6_2maxSubarrayProduct2(int[] array) {
		if (array == null || array.length == 0) {
			return 0;
		}
		int max_end_here = 1;
		int min_end_here = 1;
		int max = Integer.MIN_VALUE;
		
		for(int i = 0; i < array.length; i ++) {
			if (array[i] > 0) {
				max_end_here = max_end_here * array[i];
				min_end_here = Math.min(min_end_here* array[i], 1);
			} else if (array[i] == 0) {
				max_end_here = 1;
				min_end_here = 1;
			} else {
				// array[i] < 0
				int temp = max_end_here;
				max_end_here = Math.max(min_end_here * array[i], 1);
				min_end_here = temp * array[i];
			}
			if (max < max_end_here) {
				max = max_end_here;
			}
		}
		return max;
	}
	
	/*
	 * task6_3
	 * largest submatrix productc
	 * 
	 */
	
	/*
	 * task7 
	 * number of square in a graph
	 * 
	 */
	
	/*
	 * task8
	 * Given a String, we can insert characters at any places, 
	 * what is the least number of insertions we can do to make it a palindrome?
	 * 
	 * “abca” → “abc​b​a” insert 1 
	 * “abcdc” → “abcdc​ba​” insert 2
	 * 
	 * method1 
	 * for each i, cut into two parts, reverse one part, then use Longest Common Substring. 
	 * Time: O(n^3)
	 * 
	 * method2
	 * state(i, j) substring(i,j) least # of insertion
	 * state(i,j) = if s[i] == s[j]  state(i+ 1, j - 1)
	 *              else  min(state (i + 1, j), state(i, j - 1)) + 1
	 * base case: state(i,j) if i == j, 0
	 *            state(i,j) if i + 1 == j, if (s[i] == s[j]) 0, else 1
	 * return state(0, n- 1)
	 */
	public static void test8() {
		String str = "abca";
		int rev = task8_least_insertions(str);
		System.out.println("rev = " + rev);
	}
	
	public static int task8_least_insertions(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		int n = str.length();
		int[][] state = new int[n][n];
		for(int i = 0; i < n; i ++) {
			state[i][i] = 0;
			if (i < n - 1) {
				if (str.charAt(i) != str.charAt(i + 1)) {
					state[i][i + 1] = 1;  
				}
			}
		}
		
		for(int len = 2; len <= str.length(); len ++ ) { // !!! here, len <= str.length()
			for(int i = 0; i <= n - len; i ++) {
				int j = i + len - 1;
				if (str.charAt(i) == str.charAt(j)) {
					state[i][j] = state[i + 1][j - 1];
				} else {
					state[i][j] = Math.min(state[i + 1][j], state[i][j - 1]) + 1;
				}
			}
		}
		
		Debug.printMatrix(state);
		return state[0][n - 1];
	}
	
	
	

}
