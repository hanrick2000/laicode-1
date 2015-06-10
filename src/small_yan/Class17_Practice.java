package small_yan;

public class Class17_Practice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
		test2();
	}
	
	/*
	 * task1
	 * largest subarray sum. Given an integer array, what is the largest sum of any of the subarrays?
	 */
	public static void test1() {
		int[] a = {1,2,-1,2,3,-1};
		int res = task1_sum(a);
		System.out.println(res);
	}
	public static int task1_sum(int[] a) {
		int lastMax = 0;
		int result = Integer.MIN_VALUE;
		for(int i = 0; i < a.length; i ++) {
			lastMax = Math.max(lastMax + a[i], a[i]);
			result = Math.max(result, lastMax);
		}
		return result;
	}
	/*
	 * task2
	 *  I​f we can make the array into a circle, what is the largest sum of subarray?
	 *  {1,2,-4,2,3,-1}  largest is 2 + 3 + -1 + 1 + 2 = 7
	 */
	public static void test2() {
		int[] a = {1,2,-4,2,3,-1};
		int res = task2_sum(a);
		System.out.println(res);
	}
	public static int task2_sum(int[] a) {
		int lastMax = 0;
		int lastMin = 0;
		int sum = 0;
		int resultMax = Integer.MIN_VALUE;
		int resultMin = Integer.MAX_VALUE;
		for(int i = 0; i < a.length; i ++) {
			lastMax = Math.max(lastMax + a[i], a[i]);
			lastMin = Math.min(lastMin + a[i], a[i]);
			sum += a[i];
			resultMax = Math.max(resultMax, lastMax);
			resultMin = Math.min(resultMin, lastMin);
		}
		return resultMax > sum - resultMin ? resultMax : sum - resultMin;
	}
	
	/*
	 * task3
	 * 
	 */
	
	/*
	 * task4
	 * 
	 */
	
	/*
	 * task5
	 * Longest increasing subarray, return the length
	 * 
	 */
	
	/*
	 * task5.2
	 * the edge are directed and there is no edge directed to parent. 
	 * 1 find all the paths from root to leaf node
	 * 2 
	 */
	
	/*
	 * task5.3
	 * Longest increasing path in matrix(from one cell can to to neighbors cells, 4 directions)
	 * 
	 */
	
	
	/*
	 * task6
	 * for each of the nodes in the max tree, the node itself is the largest one within all the nodes in his subtree. 
	 * no duplicate elements in the tree.
	 * 1). if we know the inorder traversal sequence, can we reconstruct the tree?
	 * 2). if so, given the inorder traversal, reconstruct the tree. what is the time complexity?
	 * O(n ^ 2).
	 * 3). suppose the inorder traversal sequence is not given you at once, 
	 * and it is a stream. The requirement is to at anytime, 
	 * I need to know what is the max tree reconstructed by the stream so far.
	 * 4).​What is the time complexity of constructing the tree in this way? O(n ^ 2) ?
	 * O(n) → read() amortized O(1)
	 */
	
	/*
	 * task7
	 * 
	 */

}
