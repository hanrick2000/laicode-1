package small_sun;

import java.util.Arrays;

public class Class5_11022014 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
	}
	
	/*
	 * Q1. Given a list of records, each record has (start, end, weight). 
	 * find a subset of the records, such that there is no overlap, and the sum of weight is maximized.
	 * 
	 * state[x] : the maximum weights ending at records whose starting at x
	 * state[x] = weight[x] + max(state[i]) if no overlapping between x and i  (0 <= i < x)
	 * 
	 */
	public static void test1() {
		Record r1 = new Record(0, 2, 3);
		Record r2 = new Record(1, 2, 4);
		Record r3 = new Record(3, 5, 9);
		Record[] records = {r1, r2, r3};
		int rev = task1_max_sum_weight(records);
		System.out.println(rev);
	}
	
	
	public static class Record{
		int x;
		int y;
		int w;
		public Record(int _x, int _y, int _w) {
			this.x = _x;
			this.y = _y;
			this.w = _w;
		}
	}
	public static int task1_max_sum_weight(Record[] records) {
		int n = records.length;
		int[] state = new int[n];
		state[0] = records[0].w;
		for(int i = 1; i < n; i ++) {
			int prevMax = 0;
			for(int j = 0; j < i ; j ++) {
				if (records[i].x < records[j].y) {
					// there is overlap
					continue;
				} else {
					// update the maximum previous weight
					prevMax = Math.max(prevMax, state[j]);
				}
			}
			// update the state
			state[i] = prevMax + records[i].w;
		}
		System.out.println(Arrays.toString(state));
		
		int maxSumWeight = Integer.MIN_VALUE;
		for(int i = 0; i < n; i ++) {
			maxSumWeight = Math.max(state[i], maxSumWeight);
		}
		
		return maxSumWeight;
	}
	
	
	
	/*
	 * Q2   Given two binary trees (with unique letters), check if the first tree is subtree of the second one. 
	 * A subtree of a tree T is a tree S consisting of a node in T and all of its descendants in T.
	 * 
	 * API
	 * isSameTree(TreeNode root1, TreeNode root2);
	 * 
	 * Traverse the second tree, find the node which equals first root;
	 * call isSameTree(node, root1)
	 */
	
	/*
	 * Q3  Sort an array according to the order defined by another array. 
	 * Given two arrays A1[] and A2[], sort A1 in such a way that the relative order 
	 * among the elements will be same as those are in A2. For the elements not present in A2, 
	 * append them at last in sorted order.
	 * 
	 */
	
	/*
	 * Q4:  Construct a tree from Inorder and Level order traversals
	 * Given inorder and level­order traversals of a Binary Tree  
	 * (you can assume all unique numbers in the tree) , construct the Binary Tree. 
	 * Following is an example to illustrate the problem.
	 */
	
	
	/*
	 * Q5 
	 * Given a binary tree, print all paths that can start from any node and end at any node 
	 * that sum to a target number.   e.g.  target  = 22 then return (1) {22}, and (2) {12, 10}
	 * the value of the node can be any arbitrary integer. {negative, zero, positive}
	 */
	
	
	/*
	 * Q6. Given a linked list, check whether it is a palindrome.   Space O(1)
	 */
	
	/*
	 * Q7 Given an array of strings, find if the strings can be chained to form a circle
	 * !!! implement
	 */
	
	/*
	 * Q8.  Find distance between two given keys of a Binary Tree
	 */
}
