package onceagain;

import java.util.*;

import debug.Debug;
import ds.TreeNode;

public class Class24 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
//		test2();
//		test3();
		test4();
	}
	
	/*
	 * task1
	 * Reverse Binary Tree Upside Down    
	 * Given a binary tree where all the right nodes are leaf nodes, 
	 * flip it upside down and turn it into a tree with left leaf nodes as the root.
	 * Examples
	 *         			1
	 *                /    \
	 *               2      5
	 *             /   \
	 *           3      4
	 * is reversed to
     *  		3
     *
     *    	 /    \
     *      2        4
     *    /   \
     *   1      5
	 */
	// return the newRoot Of reversed Tree
	public static TreeNode task1_ReverseBTUpsideDown(TreeNode root) {
		// base case: root == null or root.left == null
		if (root == null || root.left == null) {
			return root;
		}
		TreeNode newRoot = task1_ReverseBTUpsideDown(root.left);	
		root.left.right = root.right;
		root.left.left = root;
		root.left = null;
		root.right = null;
		return newRoot;
	}
	
	public static void test1() {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		
		n1.left = n2;
		n1.right = n5;
		n2.left = n3;
		n2.right = n4;
		
		Debug.preOrderBT(n1);
		System.out.println();
		Debug.inOrderBT(n1);
		
		TreeNode newHead = task1_ReverseBTUpsideDown(n1);
		System.out.println();
		System.out.println("-----------");
		Debug.preOrderBT(newHead);
		System.out.println();
		Debug.inOrderBT(newHead);
		
	}
	
	/*
	 * task2
	 * All Valid Permutations Of Parentheses II(L pairs of (), M pairs of [], N pairs of{})
	 */
	public static List<String> task2_AllValidPermutationOfParentheses(int l, int m, int n) {
		char[] PS = {'(', ')', '[', ']', '{', '}'};
		int[] remain = {l, l, m, m, n, n};
		int totalLen = 2 * l + 2 * m + 2 * n;
		LinkedList<Character> stack = new LinkedList<Character>();
		List<String> result = new ArrayList<String>();
		task2_helper(PS, remain, totalLen, new StringBuilder(), stack, result);
		return result;
	}
	public static void task2_helper(char[] PS, int[] remain, 
			int totalLen, StringBuilder stb, LinkedList<Character> stack,
			List<String> result) {
		if (stb.length() == totalLen) {
			result.add(stb.toString());
			return ;
		}
		for(int i = 0; i < remain.length; i ++) {
			if (i % 2 == 0) {
				// left parentheses
				if (remain[i] > 0) { // !!! remember to check remain[i] > 0 !!!
					stack.offerFirst(PS[i]);
					stb.append(PS[i]);
					remain[i] --;
					task2_helper(PS, remain, totalLen, stb, stack, result);
					
					// backtraking
					stack.pollFirst();
					stb.deleteCharAt(stb.length() - 1);
					remain[i] ++;
				}
			} else {
				// i % 2 == 1
				// right parentheses
				if (!stack.isEmpty()) {
					Character preLeftP = stack.peekFirst();
					if (preLeftP == PS[i - 1]) {
						// matches
						stack.pollFirst(); // remove the matched parenthese's index
						stb.append(PS[i]);
						remain[i] --;
						
						task2_helper(PS, remain, totalLen, stb, stack, result);
						
						// backtracking
						stack.offerFirst(PS[i - 1]);
						remain[i] ++;
						stb.deleteCharAt(stb.length() - 1);
					}
				}
				
			}
		}
	}
	
	public static void test2() {
		int l = 1, m = 1, n = 1;
		List<String> result = task2_AllValidPermutationOfParentheses(l, m, n);
		System.out.println(result);
	}
	
	/*
	 * task3
	 * N Queens
	 * 
	 */
	public static void test3() {
		int n = 4;
		List<List<Integer>> result = task3_NQueens(n);
		System.out.println(result);
	}
	
	public static List<List<Integer>> task3_NQueens(int n) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> cur = new ArrayList<Integer>();
		task3_helper(n, 0, cur, result);
		return result;
	}
	
	public static void task3_helper(int n, int index, List<Integer> cur, List<List<Integer>> result) {
		if (index == n) {
			// we get a valid solution
			result.add(new ArrayList<Integer>(cur));
			return ;
		}
		
		for(int i = 0; i < n; i ++) {
			cur.add(i);
			if (task3_isValid(cur)) {
				task3_helper(n, index + 1, cur, result);
			}
			cur.remove(cur.size() - 1);
		}
	}
	
	// check whether the List is valid or NOT after add the latest element
	public static boolean task3_isValid(List<Integer> cur) {
		int size = cur.size();
		if (size <= 1) {
			return true;
		}
		int lastRow = size - 1;
		int lastCol = cur.get(size - 1);
		for(int rowI = 0; rowI <= size - 2; rowI ++) {
			int colI = cur.get(rowI);
			// cannot in one row
			// cannot in one column
			// cannot in the diagonal 
			// cannot in counter diagonal
			if (rowI == lastRow || 
				colI == lastCol || 
				(rowI + colI == lastRow + lastCol) ||
				(rowI - colI == lastRow - lastCol)) {
				return false;
			}
		}
		return true;
	}

	/*
	 * task4
	 *  All Subsequences Of Sorted String
	 */
	public static List<String> task4_subset2(String set) {
		List<String> result = new ArrayList<String>();
		if (set == null || set.length() == 0) {
			return result;
		}
		char[] array = set.toCharArray();
		StringBuilder stb = new StringBuilder();
		task4_helper(array, stb, 0, result);
		return result;
	}
	
	public static void task4_helper(char[] set, StringBuilder stb, int index, List<String> result) {
		result.add(stb.toString());
		for(int i = index; i < set.length; i ++) {
			if (i == index || set[i] != set[i - 1]) {
				stb.append(set[i]);
				task4_helper(set, stb, i + 1, result);
				stb.deleteCharAt(stb.length() - 1);
			}
		}
	}
	public static void test4() {
		String set = "abb";
		List<String> result = task4_subset2(set);
		System.out.println(result);
	}
	
	
	/*
	 * task5
	 * Two Sum
	 */
	
	
	
	/*
	 * task6
	 * Three Sum
	 */
	
	/*
	 * task7
	 * Four Sum
	 */

}
