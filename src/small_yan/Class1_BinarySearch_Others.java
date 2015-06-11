package small_yan;

public class Class1_BinarySearch_Others {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * One Dimension → Two Dimension
	 * 1. given an unsorted integer array, return the position of any local minimum. → two dimension
	 * 6. given an unsorted integer matrix, return the position of any local minimum. does it exists?
	 * 
	 * M * N matrix, N个竖条.
	 * array[mid] → size = M的array, check the whole sized M array. find the minimum element of the array.
	 * | |​   |  ​   ​| | | 
	 *      mid
	 *      O(M *logN)
	 *      Thoughts:
	 *      1. solve the one dimensional problem first, then extends to two dimensions.
	 *      2. partition the 2d spaces directly.
	 */
	
	
	/*
	 * Young’s Matrix
	 * see the handout
	 * 
	 * 
	 * 1). if you start looking at the matrix from the top left corner, it is a h​eap.​
	 * 2). if you start looking at the matrix from the top right corner, it is a b​inary search tree.​ 
	 * 3). it is natural to think of a d​ivide & conquer​solution for problems on Young’s Matrix.
	 * What are the typical interview questions?
	 * 1. smallest/largest k elements
	 * 2. kth smallest/largest
	 * 3. search for a value in the Young Matrix
	 * 4. two (sorted) array, what is the kth smallest sum if picking one element from each of the array.
	 * 5. Two sorted array A[] and B[], exist two elements such that a from A[] and b from B[], a + b = target?
	 * 
	 * 
	 * 1’. Two sorted array, pick one element from each of them, what is the kth smallest sum.
	 * A = {1, 3, 6}
	 * B = {2, 7, 8} 
	 * M[i][j] = A[i] + B[j]
	 * 
	 * 6. search for a value in the Young Matrix
	 * 1 O(m + n) search from top right or from left buttom . 
	 * 2 Divide the matrix directly. 
	 *   Time: O(log m + log n)
	 * see the hand out. 
	 * 
	 * 
	 * 7. number of values <= 0 in Young’s Matrix.
	 * ­ 5 , ­ 3 , 0​,  1  3 ­ 
	 * -2 ,  2 , 3,  ​4  1
	 * -1 ,  ​5 , 6 , 7  1
	 *  0 ,  ​6 , 7 , 9  1
	 *  
	 *  
	 *  Method 1: smallest k elements → O(N * N log(N * N))
	 *  Method 2: binary search for each row, O(N * logN)
	 *  Method 3: search for <= 0, when move down, 
	 *  we can determine how many values <= 0 at the left side of the current row. O(N + N)
	 *  
	 * 7.1 two sorted array, pick one element from each of them, how many pairs has sum <= k.
	 * 
	 * 
	 * 
	 * 8. i​mage matrix with black(1)/white(0) cells, such that there is a black object , 
	 * given a random black point, 
	 * how do you determine the smallest rectangle can cover all the area of black cells.
	 * 
	 * !!! see handout. 
	 */
	

}
