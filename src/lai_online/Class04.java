package lai_online;

import java.util.ArrayList;
import java.util.LinkedList;

import ds.TreeNode;

import java.util.*;

public class Class04 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * task1
	 * preOrder
	 * 
	 * 
	 * inOrder
	 * 
	 * 
	 * postOrder
	 */
	
	
	public static void task1_preOrder(TreeNode root) {
		if (root == null) {
			return ;
		}
		LinkedList<TreeNode> st = new LinkedList<TreeNode>();
		st.offerFirst(root);
		while(!st.isEmpty()) {
			TreeNode cur = st.pollFirst();
			System.out.print(cur.val + " ");
			if (cur.right != null) {
				st.offerFirst(cur.right);
			}
			if (cur.left != null) {
				st.offerFirst(cur.left);
			}
		}
	}
	
	
	public static void task1_preOrder2(TreeNode root) {
		if (root == null) {
			return ;
		}
		
		LinkedList<TreeNode> st = new LinkedList<TreeNode>();
		TreeNode cur = root;
		while(cur != null || !st.isEmpty()) {
			if (cur != null) {
				System.out.print(cur.val + " ");
				// push cur into stack
				st.offerFirst(cur);
				cur = cur.left;
			} else {
				// cur == null, get cur = st.pop()
				// go to cur.right
				cur = st.pollFirst();
				cur = cur.right;
			}
		}
	}
	
	public static void task1_inOrder(TreeNode root) {
		if (root == null) {
			return ;
		}
		LinkedList<TreeNode> st = new LinkedList<TreeNode>();
		TreeNode cur = root;
		while(cur != null || !st.isEmpty()) {
			if (cur != null) {
				st.offerFirst(cur);
				cur = cur.left;
			} else {
				cur = st.pollFirst();
				System.out.print(cur.val + " ");
				cur = cur.right;
			}
		}
	}
	
	
	
	// prev is the pointer in traverse, the previous node we access
	public static List<Integer> task1_postOrder(TreeNode root) {
		List<Integer> postOrder = new ArrayList<Integer>();
		if (root == null) {
			return postOrder;
		}
		LinkedList<TreeNode> st = new LinkedList<TreeNode>();
		TreeNode prev = null;
		TreeNode cur = root;
		
		st.offerFirst(cur);
		while(!st.isEmpty()) {
			cur = st.peekFirst();
			// prev == null or prev is cur's parent --> going down
			if (prev == null || prev.left == cur || prev.right == cur) {
				if (cur.left != null) {
					st.offerFirst(cur.left);
				} else if (cur.right != null) {
					st.offerFirst(cur.right);
				} else {
					// cur is leaf node, print out
					postOrder.add(cur.val);
					st.pollFirst();
				}
			} else if (prev == cur.left) {
				// prev is cur.left, left subtree
				// if cur.right exists
				if (cur.right != null) {
					st.offerFirst(cur.right);
				} else {
					// print out this node
					postOrder.add(cur.val);
					st.pollFirst();
				}
			} else {
				// prev == cur.right  go up
				postOrder.add(cur.val);
				st.pollFirst();
			}
			prev = cur;
		}
		return postOrder;
	}
	
	
	/*
	 * task2
	 * 
	 */
	
	
	
	
}
