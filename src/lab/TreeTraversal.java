package lab;

import java.util.LinkedList;
import java.util.Stack;

import ds.TreeNode;

public class TreeTraversal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	/*
	 * Pre Order Traversal
	 * 
	 * 1 We always print root first, then root can be eliminated from stack.
	 * 2 We traverse left sub first, so the right sub should be retained in the stack before the left sub is done.
	 */
	public static void preOrderIter(TreeNode root) {
		if (root == null) {
			return;
		}
		LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
		stack.offer(root);
		while(!stack.isEmpty()) {
			// visit the node
			TreeNode cur = stack.poll();
			System.out.print(cur.val + " ");
			if () {
				
			}
		}
	}
	
	
	
	
	
	/*
	 * Post Order Traversal
	 * 
	 * if previous is null → going down (left subtree has priority)
	 * if previous is current’s parent, → going down(left subtree has priority)
	 * if previous == current.left, → left subtree finished, going current.right
	 * if previous == current.right → right subtree finished, pop current, going up
	 */
	public static void postOrder(TreeNode root) {
		if (root == null) {
			return ;
		}
		Stack<TreeNode> st = new Stack<TreeNode>();
		st.push(root);
		TreeNode prev = null;
		while(!st.isEmpty()) {
			TreeNode cur = st.peek();
			// if previous is null, going down
			
			// if previous is null → going down (left subtree has priority)
			if (prev == null || cur == prev.left || cur == prev.right) {
				if (cur.left != null) {
					st.push(cur.left);
				} else if (cur.right != null){
					st.push(cur.right);
				} else {
					// leaf node
					System.out.println(cur.val + " ");
					st.pop();
				}
			} else if (prev == cur.left) {  // from left, bottom to top
				if (cur.right != null) {
					st.push(cur.right);
				} else {
					System.out.println(cur.val + " ");
					st.pop();
				}
			} else {
				// from right bottom to top
				System.out.println(cur.val + " ");
				st.pop();
			}
			prev = cur;
		}
	}

}
