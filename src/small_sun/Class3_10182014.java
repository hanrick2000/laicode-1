package small_sun;

import ds.TreeNode;

public class Class3_10182014 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * Q1   Check if two nodes are cousins  in a Binary Tree
	 * Given the binary Tree and the two nodes say  ‘a’ and ‘b’ , determine whether the two nodes
	 * are cousins of each other or not.
	 * Two nodes are cousins of each other if they are at same level and have  different  parents.
	 * 
	 * 1 get LCA of a and b  if LCA == a || LCA == b || LCA is parents of a OR b, return false
	 * 2 get depth from LCA of a and b, if level_a == level_b, return True
	 *   otherwise, return false;
	 */
	public static boolean task1_isCousins(TreeNode root, TreeNode n1, TreeNode n2) {
		if (root == null || root == n1 || root == n2) {
			return false;
		}
		
		TreeNode LCA = task1_getLCA(root, n1, n2);
		if (LCA == n1 || LCA == n2 || isChild(LCA, n1) || isChild(LCA, n2)) {
			return false;
		}
		int n1_level = getLevel(root, n1, 0);
		int n2_level = getLevel(root, n2, 0);
		if (n1_level == n2_level) {
			return true;
		}
		return false;
	}
	
	public static boolean isChild(TreeNode p, TreeNode c) {
		boolean rev = false;
		if (p.left == c || p.right == c) {
			rev = true;
		}
		return rev;
	}
	
	
	
	public static TreeNode task1_getLCA(TreeNode root, TreeNode n1, TreeNode n2) {
		if (root == null) {
			return root;
		}
		if (root == n1 || root == n2) {
			return root;
		}
		TreeNode left = task1_getLCA(root.left, n1, n2);
		TreeNode right = task1_getLCA(root.right, n1, n2);
		if (left != null && right != null) {
			return root;
		}
		return left != null ? left : right;
	}
	
	public static int getLevel(TreeNode root, TreeNode n, int level) {
		if (root == null) {
			return 0;
		}
		if (root == n) {
			return level;
		}
		int nextLevel = getLevel(root.left, n, level + 1);
		if (nextLevel != 0) {
			return nextLevel;
		}
		nextLevel = getLevel(root.right, n, nextLevel);
		return nextLevel;
	}
	
	
	/*
	 * Q2 Given a linked list, reverse alternate nodes and append at the end
	 * Example1  Input List:  1 ­ > 2 ­> 3 ­>4­> 5 ­>6
	 *    curr next nnext
	 *    Output List:   1­>3­>5 ­>6­>4­>2 Example2  Input List:  1­ >2­> 3 ­>4­> 5
	 *    Output List:   1­>3­>5 ­>4­>2`
	 *    
	 * detail
	 */
	
	/*
	 * 
	 * Q3  Given a sorted array arr[] and a value X, find the k closest elements to X in arr[]. 
	 * Assuming X must be in the array.
	 * 
	 * (1)Binary search X
	 * (2)two direction, find the k closest elements
	 * 
	 */
	
	
	
	

}
