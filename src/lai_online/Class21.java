package lai_online;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ds.*;

public class Class21 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// test2_5();
//		test6();
		test7();
	}
	
	/*
	 * task1: Deep Copy Skip List
	 * task2: Deep Copy List With random pointer
	 * task2.1: Deep Copy Tree With Random Pointer
	 * task3: Deep Copy Undirected Graph
	 * task4: Merge K sorted array
	 * task5: Merge K Sorted List
	 * task6: Closest Number In Binary Search Tree
	 * 		  Binary Search Tree Closest To Target
	 * task7: Largest Number Smaller In Binary Search Tree 
	 * task8: Cutting Wood I
	 * task9: Binary Search Delete
	 * task10: Merge Stone
	 */
	

	/*
	 * task1 Deep Copy Skip List Fair Data Structure A Skip List is a special
	 * type of linked list, where each of the nodes has a forward pointer to
	 * another node in the front and forward pointers are guaranteed to be in
	 * non-descending order. Make a deep copy of the original skip list.
	 */
	public static class SkipListNode {
		public int value;
		public SkipListNode next;
		public SkipListNode forward;

		public SkipListNode(int value) {
			this.value = value;
		}
	}

	public static SkipListNode task1_copy(SkipListNode head) {
		if (head == null) {
			return null;
		}
		HashMap<SkipListNode, SkipListNode> map = new HashMap<SkipListNode, SkipListNode>();
		SkipListNode newHead = new SkipListNode(head.value);
		map.put(head, newHead);
		SkipListNode cur = newHead;
		while (head != null) {
			// the original's node has next pointer
			if (head.next != null) {
				// head.next hasn't been put into hashMap due to forward pointer
				if (!map.containsKey(head.next)) {
					SkipListNode node = new SkipListNode(head.next.value);
					// put into map
					map.put(head.next, node);
				}
				cur.next = map.get(head.next);
			}

			if (head.forward != null) {
				// head.forward hasn't been put into hashMap
				if (!map.containsKey(head.forward)) {
					SkipListNode node = new SkipListNode(head.forward.value);
					map.put(head.forward, node);
				}
				cur.forward = map.get(head.forward);
			}
			// update head
			head = head.next;
			// update cur
			cur = cur.next;
		}
		return newHead;
	}

	/*
	 * task2 
	 * Deep Copy Linked List With Random Pointer Fair Data Structure Each
	 * of the nodes in the linked list has another pointer pointing to a random
	 * node in the list or null. Make a deep copy of the original list.
	 */
	public static class RandomListNode {
		public int value;
		public RandomListNode next;
		public RandomListNode random;

		public RandomListNode(int value) {
			this.value = value;
		}
	}

	// method1
	// using hashMap <Original node, copy node>
	public static RandomListNode task2_copy(RandomListNode head) {
		if (head == null) {
			return null;
		}
		HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
		RandomListNode cur = head;

		// create copy node and put into hashMap
		while (cur != null) {
			// create the copy node
			RandomListNode copy = new RandomListNode(cur.value);

			// put cur and copy into map
			map.put(cur, copy);

			// update cur;
			cur = cur.next;
		}

		// all nodes are there, link them according with original node
		cur = head;

		while (cur != null) {
			RandomListNode curCopy = map.get(cur);
			if (cur.next != null) {
				curCopy.next = map.get(cur.next);
			}
			if (cur.random != null) {
				curCopy.random = map.get(cur.random);
			}
			cur = cur.next;
		}
		return map.get(head);
	}

	// method2
	/*
	 * 1 ->2 ->3 ->4 -> 5 1 ->1'->2->2' ->3 -3'
	 */
	public static RandomListNode task2_2copyt(RandomListNode head) {
		if (head == null) {
			return null;
		}
		RandomListNode cur = head;
		// create the copy node and assert it after cur
		while (cur != null) {
			RandomListNode curCopy = new RandomListNode(cur.value);
			curCopy.random = cur.random;

			curCopy.next = cur.next;
			cur.next = curCopy;

			cur = cur.next.next;
		}

		// update random pointer
		cur = head;
		RandomListNode newHead = head.next;
		while (cur != null) {
			if (cur.random != null) {
				cur.next.random = cur.random.next;
			}
			cur = cur.next.next;
		}

		// split the list
		cur = head;
		while (cur != null) {
			RandomListNode temp = cur.next;
			cur.next = temp.next;
			cur = cur.next;

			if (temp.next != null) {
				temp.next = temp.next.next;
			}
		}
		return newHead;
	}

	/*
	 * task2.5 Copy tree with random pointer
	 */
	public static class RandomTreeNode {
		public int val;
		public RandomTreeNode left;
		public RandomTreeNode right;
		public RandomTreeNode random;

		public RandomTreeNode(int v) {
			this.val = v;
			this.left = null;
			this.right = null;
			this.random = null;
		}
	}

	public static RandomTreeNode task2_5DeepCopyTreeRandom(RandomTreeNode root) {
		if (root == null) {
			return null;
		}

		RandomTreeNode cloneRoot = copyLeftRightNode(root);
		setRandomPointer(root, cloneRoot);
		restoreTree(root, cloneRoot);

		return cloneRoot;
	}

	/*
	 * 1 / \ 2 3
	 * 
	 * 1 / \ 1' 3 / \ / 2 3' / 2'
	 */
	// return the copyNode of the curNode, which is node.left
	public static RandomTreeNode copyLeftRightNode(RandomTreeNode node) {
		if (node == null) {
			return null;
		}
		RandomTreeNode oldLeft = node.left;
		// create the copy of node
		RandomTreeNode copyNode = new RandomTreeNode(node.val);

		// link the copyNode to node's left
		node.left = copyNode;

		// link oldLeft to copyNode.left
		copyNode.left = oldLeft;
		if (oldLeft != null) {
			oldLeft.left = copyLeftRightNode(oldLeft);
		}
		copyNode.right = copyLeftRightNode(node.right);

		return copyNode;

	}

	/*
	 * 1 / \ 2 3
	 * 
	 * 1 / \ 1' 3 / \ / 2 3' / 2'
	 */
	public static void setRandomPointer(RandomTreeNode node,
			RandomTreeNode cloneNode) {
		if (node == null) {
			return;
		}
		if (node.random != null) {
			cloneNode.random = node.random.left;
		} else {
			cloneNode.random = null;
		}

		if (node.left != null && cloneNode.left != null) {
			setRandomPointer(node.left.left, cloneNode.left.left);
		}
		setRandomPointer(node.right, cloneNode.right);
	}

	/*
	 * 1 / \ 2 3
	 * 
	 * 1 / \ 1' 3 / \ / 2 3' / 2'
	 */
	public static void restoreTree(RandomTreeNode node, RandomTreeNode cloneNode) {
		if (node == null) {
			return;
		}
		if (cloneNode.left != null) {
			//
			RandomTreeNode cloneLeft = cloneNode.left.left;
			node.left = node.left.left;
			cloneNode.left = cloneLeft;
		} else {
			node.left = null;
		}

		restoreTree(node.left, cloneNode.left);
		restoreTree(node.right, cloneNode.right);
	}

	public static void PrintInOrder(RandomTreeNode node) {
		if (node == null) {
			return;
		}
		PrintInOrder(node.left);
		System.out.print("[");
		System.out.print(node.val + " ");
		if (node.random == null) {
			System.out.print("  Null ");
		} else {
			System.out.print(" " + node.random.val + " ");
		}
		System.out.print("] ");
		PrintInOrder(node.right);
	}

	public static void test2_5() {
		RandomTreeNode n1 = new RandomTreeNode(1);
		RandomTreeNode n2 = new RandomTreeNode(2);
		RandomTreeNode n3 = new RandomTreeNode(3);
		RandomTreeNode n4 = new RandomTreeNode(4);
		RandomTreeNode n5 = new RandomTreeNode(5);
		RandomTreeNode n6 = new RandomTreeNode(6);
		RandomTreeNode n7 = new RandomTreeNode(7);

		n1.left = n2;
		n1.right = n3;

		n2.left = n4;
		n2.right = n5;
		n2.random = n3;

		n3.left = n6;
		n3.right = n7;

		n4.random = n1;
		n5.random = n7;

		PrintInOrder(n1);

		System.out.println();

		RandomTreeNode newHead = task2_5DeepCopyTreeRandom(n1);
		PrintInOrder(newHead);

		System.out.println();
	}

	/*
	 * task3 
	 * Deep Copy Undirected Graph Fair Graph Make a deep copy of an
	 * undirected graph, there could be cycles in the original graph.
	 * Assumptions The given graph is not null
	 */
	public static class GraphNode {
		public int key;
		public List<GraphNode> neighbors;

		public GraphNode(int key) {
			this.key = key;
			this.neighbors = new ArrayList<GraphNode>();
		}
	}

	public static List<GraphNode> task3_deepCopyGraph(List<GraphNode> graph) {
		if (graph == null) {
			return null;
		}
		HashMap<GraphNode, GraphNode> map = new HashMap<GraphNode, GraphNode>();
		List<GraphNode> copyGraph = new ArrayList<GraphNode>();

		// copy the node
		for (GraphNode node : graph) {
			GraphNode copyNode = new GraphNode(node.key);
			copyGraph.add(copyNode);
			map.put(node, copyNode);
		}

		// clone the graph
		for (GraphNode node : graph) {
			GraphNode copyNode = map.get(node);
			for (GraphNode neiNode : node.neighbors) {
				copyNode.neighbors.add(map.get(neiNode));
			}
		}
		return copyGraph;
	}

	/*
	 * task4 
	 * Merge K Sorted Array Fair Data Structure Merge K sorted array into
	 * one big sorted array in ascending order. Assumptions The input
	 * arrayOfArrays is not null, none of the arrays is null either.
	 */
	
	

	/*
	 * task5 
	 * Merge K Sorted Lists Fair Data Structure Merge K sorted lists into
	 * one big sorted list in ascending order. Assumptions None of the lists is
	 * null.
	 */

	/*
	 * task6 Closest Number In Binary Search Tree 
	 * Fair Data Structure In a
	 * binary search tree, find the node containing the closest number to the
	 * given target number.
	 * 
	 * Assumptions:
	 * 
	 * The given root is not null. There are no duplicate keys in the binary
	 * search tree. Examples:
	 * 
	 * 5 / \ 2 11 / \ 6 14
	 * 
	 * closest number to 4 is 5
	 * 
	 * closest number to 10 is 11
	 * 
	 * closest number to 6 is 6
	 * 
	 * How is the binary tree represented?
	 * 
	 * We use the level order traversal sequence with a special symbol "#"
	 * denoting the null node.
	 * 
	 * For Example: The sequence [1, 2, 3, #, #, 4] represents the following
	 * binary tree:
	 * 
	 * 1 / \ 2 3 / 4
	 */

	public static void test6() {
		int[] a = { 1, 2, 5, 6, 7, 11, 15 };
		TreeNode root = Tree.createTree(a);
		int target = 12;
		int result = task6_closest(root, target);
		System.out.println("result = " + result);
	}

	public static int task6_closest(TreeNode root, int target) {
		// Write your solution here.
		task6_helper(root, target);
		
		return closest;
	}

	public static int closest = Integer.MAX_VALUE;

	public static void task6_helper(TreeNode node, int target) {
		if (node == null) {
			return;
		}
		System.out.println("closest = " + closest);
		if (node.val == target) {
			closest = node.val;
			return;
		}
		if (Math.abs(node.val - target) < Math.abs(closest - target)) {
			closest = node.val;
		}
		if (node.val < target) {
			task6_helper(node.right, target);
		} else {
			task6_helper(node.left, target);
		}
	}

	/*
	 * task7 
	 * Largest Number Smaller In Binary Search Tree 
	 * Fair Data Structure In
	 * a binary search tree, find the node containing the largest number smaller
	 * than the given target number. If there is no such number, return INT_MIN.
	 * Assumptions: The given root is not null. There are no duplicate keys in
	 * the binary search tree. Examples 5 / \ 2 11 / \ 6 14
	 * 
	 * largest number smaller than 1 is null
	 * 
	 * largest number smaller than 10 is 6
	 * 
	 * largest number smaller than 6 is 5
	 */
	public static void test7() {
		int[] a = {1,2,3,4,7,9, 15, 19};
		TreeNode root = Tree.createTree(a);
		int target = 19;
		int result = task7_largetSmaller(root, target);
		System.out.println("result = " + result);
	}
	
	public static int largestSmaller = Integer.MIN_VALUE;
	public static int task7_largetSmaller(TreeNode root, int target) {
		task7_helper(root, target);
		return largestSmaller;
	}
	public static void task7_helper(TreeNode node, int target) {
		if (node == null) {
			return ;
		}
		if (node.val < target && Math.abs(node.val - target) < Math.abs(largestSmaller - target)) {
			largestSmaller = node.val;
		}
		if (node.val < target) {
			task7_helper(node.right, target);
		} else {
			task7_helper(node.left, target);
		}
	}
	
	
	/*
	 * task8 
	 * Cutting Wood I 
	 * Hard DP There is a wooden stick with length L >= 1,
	 * we need to cut it into pieces, where the cutting positions are defined in
	 * an int array A. The positions are guaranteed to be in ascending order in
	 * the range of [1, L - 1]. The cost of each cut is the length of the stick
	 * segment being cut. Determine the minimum total cost to cut the stick into
	 * the defined pieces.
	 * 
	 * Examples
	 * 
	 * L = 10, A = {2, 4, 7}, the minimum total cost is 10 + 4 + 6 = 20 (cut at
	 * 4 first then cut at 2 and cut at 7)
	 */
	
	/*
	 * task9
	 * Binary Search Tree Delete
	 */
	
	/*
	 * task10
	 * Merge Stones
	 */
	

}
