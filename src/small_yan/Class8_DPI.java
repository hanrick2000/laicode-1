package small_yan;

import debug.Debug;

public class Class8_DPI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test2();
		test4();
		test4_1();
//		test6_2();
//		test8();
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
	 *  
	 *  
	 *  incl : include this element
	 *  excl:  
	 */
	public static void test2() {
		int[] a = {5,  5, 10, 40, 50, 35};
		
		System.out.println(task2_maxSumNoAdjacent(a));
	}
	
	
	public static int task2_maxSumNoAdjacent(int[] array) {
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
	 * state[i][X] paint the hoiuse from 1 to i, the ith house is painted with color X, 
	 * which is the minimum cost. 
	 * 
	 * state[i][X] = R: min(state[i - 1][G], state[i - 1][B]) + cost[i][R]
	 *               G: min(state[i - 1][R], state[i - 1][B]) + cost[i][G]
	 *               B: min(state[i - 1][R], state[i - 1][G]) + cost[i][B]
	 * return min(state[n][X])  S = R, G, B
	 */
	public static final int R = 0;
	public static final int G = 1;
	public static final int B = 2;
	
	public static void test4() {
		int[][] cost = {
				{10, 15, 20},
				{30, 10, 15},
				{15, 5,  50},
				{5,  30, 20}
		};
		int rev = task4_paitHouse(cost);
		System.out.println("rev = " + rev);
	}
	public static int task4_paitHouse(int[][] cost) {
		int numHouse = cost.length;
		int[][] state = new int[numHouse][3];
		
		System.out.println(state.length);
		System.out.println(state[0].length);
		// init
		state[0][R] = cost[0][R];
		state[0][G] = cost[0][G];
		state[0][B] = cost[0][B];
		
		for(int i = 1; i < numHouse; i ++) {
			state[i][R] = Math.min(state[i - 1][G], state[i - 1][B]) + cost[i][R];
			state[i][G] = Math.min(state[i - 1][R], state[i - 1][B]) + cost[i][G];
			state[i][B] = Math.min(state[i - 1][R], state[i - 1][G]) + cost[i][B];
		}
		
		for(int i = 0; i < numHouse; i ++) {
			for(int j = 0; j < 3; j ++) {
				System.out.print(state[i][j] +" ");
			}
			System.out.println();
		}
		
		return Math.min(Math.min(state[numHouse - 1][R], state[numHouse - 1][G]), state[numHouse - 1][B]);
		
	}
	
	/*
	 * task4.1 follow up
	 * if the number of colors is k
	 * cost[n][k] nxk matrix. For example, cost[0][0] is the cost of paiting house 0 with color 0
	 * cost[1][2] is the cost of paiting house 1 with ciolor 2
	 * 
	 * Find the minimum cost to pait all houses
	 */
	public static void test4_1() {
		int[][] cost = {
				{10, 15, 20},
				{30, 10, 15},
				{15, 5,  50},
				{5,  30, 20}
		};
		int rev = task4_1_paitHouse(cost);
		System.out.println("rev = " + rev);
	}
	
	public static int task4_1_paitHouse(int[][] cost) {
		if (cost == null || cost.length == 0 || cost[0] == null || cost[0].length == 0) {
			return 0;
		}
		int numHouses = cost.length;
		int numColors = cost[0].length;
		int[][] state = new int[numHouses][numColors];
		
		// init
		for(int i = 0; i < numHouses; i ++) {
			for(int j = 0; j < numColors; j ++) {
				if (i == 0) {
					state[i][j] = cost[i][j];
				} else {
					int prevMin = Integer.MAX_VALUE;
					for(int k = 0; k < numColors; k ++) {
						if (k != j) {
							prevMin = Math.min(prevMin, state[i - 1][k]);
						}
					}
					
					state[i][j] = prevMin + cost[i][j];
				}
			}
		}
		
		// for debug
		System.out.println("print out the matrix");
		
		for(int i = 0; i < numHouses; i ++) {
			for(int j = 0; j < numColors; j ++) {
				System.out.print(state[i][j] + " ");
			}
			System.out.println();
		}
		
		int result = Integer.MAX_VALUE;
		for(int j = 0; j < numColors; j ++ ) {
			result = Math.min(result, state[numHouses - 1][j]);
		}
		
		return result;
	}
	
	// follow up: time:O(nk) 
	// http://www.meetqun.com/thread-10660-1-1.html
	
	
	
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
	 * 
	 * profit[numCasinos][numDays]
	 * stand for 每个赌场每天盈利钱数
	 * 
	 */
	public static int task5_NCasino_Max_Profit(int[][] profit) {
		if (profit == null || profit.length == 0 || profit[0] == null ||profit[0].length == 0) {
			return 0;
		}
		int numCasinos = profit.length;
		int numDays = profit[0].length;
		
		int[][] state = new int[numCasinos][numDays];
		
		for(int j = 0; j < numDays; j ++) {
			for(int i = 0; i < numCasinos; i ++) {
				if (j == 0) {
					state[i][j] = profit[i][j];
				} else {
					if (i == 0) {
						state[i][j] = state[i + 1][j - 1] + profit[i][j];
					} else if (i == numCasinos - 1) {
						state[i][j] = state[i - 1][j - 1] + profit[i][j];
					} else {
						state[i][j] = Math.min(state[i + 1][j - 1], state[i -1][j - 1]) + profit[i][j];
					}
				}
			}
		}
		
		int result = 0;
		for(int i = 0; i < numCasinos; i ++) {
			result = Math.min(result, state[i][numDays - 1]);
		}
		
		return result;
	}
	
	
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
	 * largest submatrix product
	 * 
	 */
	
	/*
	 * task7 
	 * number of square in a graph
	 * Given a rectangular image of horizontal and vertical lines, 
	 * return the number of squares present in the image.
	 * You should think about how to represent horizontal and vertical lines by yourself
	 *  
	 *  得到那个Node[][] matrix. 
	 *  然后对于每个点，（i,j）我们检查(i-1, j-1) ==> (i, j), (i-2, j - 2) ==> (i, j) ... 这些square 是否有效..
	 *  比如说，检查  （i-2, j - 2） ==> (i, j), 我们就需要检查一下， 
	 * （matrix[i-2][j].fromLeft == true && matrix[i-2][j].longestFromLeft >= 2） &&
	 *  (matrix[i][j-2].fromUp == true && matrix[i][j-2].longestFromUp >= 2), 
	 *  确保他们也能到达(i-2, j - 2) 这个点...
	 *  这样就得到了1个square. 
	 *  counter  //用来记录 square 的个数。
	 *  
	 */
	public static class Node {
		public boolean from_left;  // can from left
		public boolean from_up;   // can from up
		public int longest_from_left; // longest distance from left
		public int longest_from_up;   // longest distance from up
	}
	
	public static int task7_numSquare(Node[][] graph) {
		
		if (graph == null || graph.length == 0) {
			return 0;
		}
		int counter = 0;
		for(int i = 1; i < graph.length; i ++) {
			for(int j = 1; j < graph[0].length; j ++) {
				int minLongest = Math.min(graph[i][j].longest_from_left, graph[i][j].longest_from_up);
				if (graph[i][j].from_left == true && graph[i][j].from_up == true) {
					for(int diag = 1; diag <= minLongest; i ++) {
						int i_prev = i - diag;
						int j_prev = j - diag;
						// check (i, j_prev)
						boolean check1 = graph[i][j_prev].from_up && graph[i][j_prev].longest_from_up >= diag;
						// check (i_prev, j)
						boolean check2 = graph[i_prev][j].from_left && graph[i_prev][j].longest_from_left >= diag;
						
						if (check1 && check2) {
							counter ++;
						}
					}
				}
			}
		}
		return counter;
	}
	
	
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
