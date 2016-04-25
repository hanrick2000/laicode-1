package onceagain;

import java.util.Arrays;

public class Class18 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test4();
	}
	/*
	 * task1 
	 * Array dedeuplicate I (sorted array, duplicate element only retain one)
	 */
	
	/*
	 * task2 
	 * Array Deduplicate II (sorted array, duplicate element only retain two)
	 */
	
	/*
	 * task3
	 * Array Deduplicate III (sorted array, duplicate element not retain any)
	 */
	
	/*
	 * task4 
	 * Array Deduplicate IV (unsorted array, repeatedly deduplication)
	 * input: {1,2,2,3,3,1}
	 * output {}
	 * 
	 * input: {1,2,2,2,3,3,2}
	 * output: {1,2}
	 * 
	 * [0, end) 
	 */
	public static int[] task4_ArrayDeduplicateIV(int[] array) {
		if (array == null || array.length == 0) {
			return null;
		}
		int end = -1;
		for(int i = 0; i < array.length; i++) {
			if (end == -1 || array[end] != array[i]) {
				array[++ end] = array[i];
			} else {
				// array[i] == array[i - 1]
				// skip the following element which is same with array[i]
				while(i + 1 < array.length && array[i] == array[i + 1]) {
					i ++;
				}
				// end --
				end --;
			}
		}
		System.out.println("end = " + end);
		
		return Arrays.copyOfRange(array, 0, end + 1);
	}
	public static void test4() {
		int[] array = {1,2,2,2,3,3,1};
		int[] result = task4_ArrayDeduplicateIV(array);
		System.out.println(Arrays.toString(result));
	}
	
	
	/*
	 * task5
	 * Largest And Smallest
	 */
	
	/*
	 * task6
	 * Largest And Second Largest
	 */
	
	/*
	 * task7
	 * Spiral Order Print
	 */
	
	/*
	 * task8
	 * Rotate Matrix By 90 Degree Clockwise
	 */
	
	/*
	 * task9
	 * Zig-Zag Order Print Binary Tree
	 */
	

}
