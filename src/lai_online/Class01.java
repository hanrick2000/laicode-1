
package lai_online;

import debug.Debug;

public class Class01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test_quickSort();
		test5();
	}

	/*
	 * Task1 
	 * Selection Sort Data Structure 
	 * Given an array of integers, sort the
	 * elements in the array in ascending order. 
	 * The selection sort algorithm should be used to solve this problem.
	 * 
	 * Examples
	 * 
	 * {1} is sorted to {1} 
	 * {1, 2, 3} is sorted to {1, 2, 3} 
	 * {3, 2, 1} is sorted
	 * to {1, 2, 3} {4, 2, -3, 6, 1} is sorted to {-3, 1, 2, 4, 6} Corner Cases
	 * 
	 * What if the given array is null? In this case, we do not need to do
	 * anything. What if the given array is of length zero? In this case, we do
	 * not need to do anything.
	 */
	
	/*
	 * selection:
	 * 5 8 9 1 4
	 * every time, select the minimum of the remaining subarray. 
	 * then, swap the min element with current element 
	 */
	
	
	
	
	

	/*
	 * task2 
	 * Move 0s To The End I 
	 * Easy Data Structure 
	 * Given an array of integers, move all the 0s to the right end of the array.
	 * 
	 * The relative order of the elements in the original array does not need to
	 * be maintained.
	 * 
	 * Assumptions:
	 * 
	 * The given array is not null. Examples:
	 * 
	 * {1} --> {1} 
	 * {1, 0, 3, 0, 1} --> {1, 3, 1, 0, 0} or {1, 1, 3, 0, 0} or {3,1, 1, 0, 0}
	 */
	
	

	/*
	 * task3 
	 * Rainbow Sort Fair Data Structure Given an array of balls, where the
	 * color of the balls can only be Red, Green or Blue, sort the balls such
	 * that all the Red balls are grouped on the left side, all the Green balls
	 * are grouped in the middle and all the Blue balls are grouped on the right
	 * side. (Red is denoted by -1, Green is denoted by 0, and Blue is denoted
	 * by 1).
	 * 
	 * Examples
	 * 
	 * {0} is sorted to {0} {1, 0} is sorted to {0, 1} {1, 0, 1, -1, 0} is
	 * sorted to {-1, 0, 0, 1, 1} Assumptions
	 * 
	 * The input array is not null. 
	 * Corner Cases
	 * 
	 * What if the input array is of length zero? In this case, we should not do
	 * anything as well.
	 */

	
	/*
	 * task4 
	 * Merge Sort Fair Recursion 
	 * Given an array of integers, sort the
	 * elements in the array in ascending order. The merge sort algorithm should
	 * be used to solve this problem.
	 * 
	 * Examples
	 * 
	 * {1} is sorted to {1} {1, 2, 3} is sorted to {1, 2, 3} {3, 2, 1} is sorted
	 * to {1, 2, 3} {4, 2, -3, 6, 1} is sorted to {-3, 1, 2, 4, 6} Corner Cases
	 * 
	 * What if the given array is null? In this case, we do not need to do
	 * anything. What if the given array is of length zero? In this case, we do
	 * not need to do anything.
	 */

	/*
	 * task5 
	 * Quick Sort Fair Recursion Given an array of integers, sort the
	 * elements in the array in ascending order. The quick sort algorithm should
	 * be used to solve this problem.
	 * 
	 * Examples
	 * 
	 * {1} is sorted to {1} {1, 2, 3} is sorted to {1, 2, 3} {3, 2, 1} is sorted
	 * to {3, 2, 1} {4, 2, -3, 6, 1} is sorted to {-3, 1, 2, 4, 6} Corner Cases
	 * 
	 * What if the given array is null? In this case, we do not need to do
	 * anything. What if the given array is of length zero? In this case, we do
	 * not need to do anything.
	 */
	
	public static void test_quickSort() {
		int[] array = {5,3,8,10,1,8,2};
		quickSort(array);
		
		Debug.printArray(array);
	}
	
	public static void quickSort(int[] array) {
		if (array == null) {
			return ;
		}
		quickSort(array, 0, array.length - 1);
	}
	
	public static void quickSort(int[] array, int left, int right) {
		if (left >= right) {
			return ;
		}
		int pivotPos = partition(array, left, right);
		quickSort(array, left, pivotPos - 1);
		quickSort(array, pivotPos + 1,  right);
	}
	
	public static int partition(int[] array, int left, int right) {
		int pivotIndex = pivotIndex(left, right);
		int pivot = array[pivotIndex];
		
		// swap the pivot with the rightmost position first
		swap(array, pivotIndex, right);
		
		int leftBound = left, rightBound = right - 1;
		while(leftBound <= rightBound) {
			if (array[leftBound] < pivot) {
				leftBound ++;
			} else if (array[rightBound] >= pivot) {
				rightBound --;
			} else {
				swap(array, leftBound, rightBound);
			}
		}
		
		// swap right with leftBound, i.e, put the pivot to its position. 
		swap(array, leftBound, right);
		
		return leftBound;
		
		
	}
	
	public static int pivotIndex(int left, int right) {
		// Math.random() greater than or equal to 0.0 and less than 1.0
		// left = 5, right = 10   5 6 7 8 9 10  => 5 + random(10 - 5) 
		// [0.0, 1.0)* 9 => [0,9)   1    [0.0, 1.0) * 5 
		int random = left + (int)Math.random()* (right - left + 1);
		return random;
		
	}
	
	public static void swap(int[] array, int i , int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	
	public static void test5() {
		int[] array = {0, 0, 0, 0, 1};
		
		int[] output = moveZero(array);
		Debug.printArray(output);
	}
	
	
	// [0, s)  processed. >0
	// [s, f)  useless
	// [f, n)  to explore
	public static int[] moveZero(int[] array) {
		// Write your solution here.
		if (array == null || array.length == 0) {
			return array;
		}
		int s = 0, f = 0;
		for(; f < array.length; f ++) {
			if (array[f] != 0) {
				array[s ++] = array[f];
			}
		}
		for(int i = s; i < array.length; i ++) {
			array[i] = 0;
		}
		
		return array;
	}
	  
	 
	
}
