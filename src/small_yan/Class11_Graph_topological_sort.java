package small_yan;

import java.util.*;

import ds.GraphNode;

public class Class11_Graph_topological_sort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test5();
//		test4_1();
		test4_0();
	}

	/*
	 * Class 11 Graph, Topological Sort
	 */
	/*
	 * task1 How to represent a graph? 1 adjacent matrix 2 adjacent list
	 * 
	 * DFS BFS Topological Sort
	 */

	/*
	 * task2 Celebrity Problem a group of people, if there exists one person
	 * such that he does not know any other people, but every person knows him.
	 * is there such a person existed? if so, who is it? 1 how many celebrities
	 * in total we can have? -- at most one suppose we have two celebrities, c1
	 * and c2. if c1 knows c2, c1 is not celebrity if c1 donesn't knows c2, c2
	 * is not celebrity
	 * 
	 * 2 graph, represents in matrix (1) each pair of persons, we know one of
	 * them is not celebrity. (2) we can do pair comparison for n-1 times, then
	 * we have a candidate (3) we still need to check if the candidate is the
	 * celebrity.
	 */

	/*
	 * task3 maximum value
	 */

	/*
	 * task4 DFS 
	 * continiued 
	 * 1 deep copy a directed/undirected graph DFS/BFS 
	 * 2 how to determine if an directed graph is a tree? root-node tree: from
	 * root, there in only one path DFS- a node is visited only once, we can not
	 * visit it again
	 * 
	 * 3 how to determine if an directed graph does not have circle? 
	 * method 1: for each of the nodes in the graph, do DFS -> only mark current path
	 * visit 
	 * method 2: We will need two markers - visiting: we are visiting the
	 * node now, but not finished visiting:: after all the neighbors of node are
	 * visited, we can mark the node visited, before that, the node is in
	 * visiting status. -visited: we finish the visiting node
	 * 
	 * dfs(GrapohNode node) 
	 * { 
	 * 	  if (node is visited) 
	 *    { 
	 *    	return ; 
	 *    } 
	 *    if (node is visiting) 
	 *    { 
	 *    	there is a cycle. 
	 *    }
	 * 
	 *    if (node is not visited or visiting) 
	 *    { 
	 *    	mark node visiting for (neighbor: node.neighbors) 
	 *    { 
	 *    	dfs(neighbor); 
	 *    } 
	 *    mark node visited 
	 *    } 
	 *    }
	 */
	
	public static void test4_0() {
		GraphNode n1 = new GraphNode(1);
		GraphNode n2 = new GraphNode(2);
		GraphNode n3 = new GraphNode(3);
		
		n1.neighbors.add(n2);
		n2.neighbors.add(n3);
		n1.neighbors.add(n3);
		
		List<GraphNode> graph = new ArrayList<GraphNode>();
		graph.add(n1);
		graph.add(n2);
		graph.add(n3);
		
		
		GraphNode n4 = new GraphNode(4);
		GraphNode n5 = new GraphNode(5);
		
		n4.neighbors.add(n3);
		n5.neighbors.add(n4);
		
		graph.add(n4);
		graph.add(n5);
		boolean rev = task4_directedGraphHasCycle(graph);
		System.out.println("rev = " + rev);
	}
	
	public static boolean directed_graph_has_cycle = false;
	public static boolean task4_directedGraphHasCycle(List<GraphNode> graph) {
		HashSet<GraphNode> visited = new HashSet<GraphNode>();
		HashSet<GraphNode> visiting = new HashSet<GraphNode>();
		
		for(GraphNode node: graph) {
			task4_directed_dfs(node, visited, visiting);
		}
		return directed_graph_has_cycle;
	
	}
	
	public static void task4_directed_dfs(GraphNode node, HashSet<GraphNode> visited, HashSet<GraphNode> visiting) {
		if (visited.contains(node)) {
			return ;
		}
		if (visiting.contains(node)) {
			directed_graph_has_cycle = true;
			return ;
		}
		visiting.add(node);
		
		for(GraphNode nei: node.neighbors) {
			task4_directed_dfs(nei, visited, visiting);
		}
		
		visited.add(node);
		
	}
	

	public static boolean task4_determineDirectedGraphIsTree(List<GraphNode> graph) {
		return false;
	}
	

	
	// this we use n node from 0..n-1 and the adjacentMatrix
	public static boolean task4_determineUndirectedGraphIsTree(int n, int[][] adjacentMatrix) {
		return false;
	}
	
	
	
	
	
	public static void test4_1() {
		int n = 3; 
		int[][] adjacentMatrix = new int[n][n];
		adjacentMatrix[0][1] = 1;
		adjacentMatrix[1][0] = 1;
		adjacentMatrix[0][2] = 1;
		adjacentMatrix[2][0] = 1;
		
//		adjacentMatrix[1][2] = 1;
//		adjacentMatrix[2][1] = 1;
		
		boolean hasCycle  = task4_undirectedGraphHasCycle(3, adjacentMatrix);
		System.out.println("hasCycle = " + hasCycle);
	}
	public static boolean task4_undirectedGraphHasCycle(int n, int[][] adjacentMatrix) {
		if (adjacentMatrix == null || adjacentMatrix.length == 0 || adjacentMatrix[0] == null || 
				adjacentMatrix[0].length == 0) {
			return false;
		}
		boolean[] visited = new boolean[n];
		return task4_un_hasCycle_helper(0, adjacentMatrix, visited, -1);
	}
	
	public static boolean task4_un_hasCycle_helper(int n, int[][] adjacentMatrix, boolean[] visited, int parent) {
		visited[n] = true;
		
		for(int i = 0; i < adjacentMatrix[n].length; i ++) {
			if (adjacentMatrix[n][i] == 1) {
				if (visited[i] == true) {
					// here we need to introduce parent. 
					// 1 -> 2, we visited 1, visited[1] = true
					// when we visited 2, visited[1] = true, but i = 2 == parent, so, this is NOT cycle
					// if i != parent, which means there is a cycle
					if (i != parent) {
						return true;
					}
				} else {
					if(task4_un_hasCycle_helper(i, adjacentMatrix, visited, n)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	

	
	
	
	
	
	
	
	
	
	

	/*
	 * task5 Topological Sort
	 * 
	 * graph: list of nodes L <- Empty list that will contain the topological
	 * order ofthe nodes S <- Set of all nodes with no incoming edges
	 * 
	 * while (S is NOT empty) do remove a node n from S add n to tail of L for
	 * each node m with an edge e from n to m (n -> m) do remove edge e from the
	 * graph. (m's in-degree --) if m has no other incoming edges (m's in-degree
	 * == 0) insert m into S
	 * 
	 * if graph has vertices, then return error(graph has at least one cycle)
	 * else return L (a topological sorted order)
	 */

	/*
	 * BFS do topological sorting
	 */

	public static void test5() {
		GraphNode n1 = new GraphNode(1);
		GraphNode n2 = new GraphNode(2);
		GraphNode n3 = new GraphNode(3);
		
		n1.neighbors.add(n2);
		n1.neighbors.add(n3);
		n2.neighbors.add(n3);
		n3.neighbors.add(n2);

		ArrayList<GraphNode> graph = new ArrayList<GraphNode>();
		graph.add(n1);
		graph.add(n2);
		graph.add(n3);
		ArrayList<GraphNode> topSort = topologicalSort(graph);

		System.out.println("==========top sort1 ===========");
		for (GraphNode node : topSort) {
			System.out.print(node.key + "  ");
		}
		System.out.println("\n-----------------");
		
		
		System.out.println("==========top sort2 ===========");
		ArrayList<GraphNode> topSort2 = topologicalSort2(graph);
		for (GraphNode node : topSort2) {
			System.out.print(node.key + "  ");
		}
		System.out.println("\n-----------------");

	}

	public static ArrayList<GraphNode> topologicalSort(
			ArrayList<GraphNode> graph) {
		ArrayList<GraphNode> result = new ArrayList<GraphNode>();
		HashMap<GraphNode, Integer> map = new HashMap<GraphNode, Integer>();
		// store <GraphNode, in-degree> of node

		// put all neighbors into map, their in-degree >= 1.
		for (GraphNode node : graph) {
			for (GraphNode neighbor : node.neighbors) {
				if (map.containsKey(neighbor)) {
					map.put(neighbor, map.get(neighbor) + 1);
				} else {
					map.put(neighbor, 1);
				}
			}
		}

		// for debug
//		for (Entry<GraphNode, Integer> entry : map.entrySet()) {
//			System.out.println(entry.getKey().key + " :  " + entry.getValue());
//		}
//		System.out.println();

		// add all in-degree == 0 node into result, they are not in the hashMap
		// and add them into the queue
		Queue<GraphNode> queue = new LinkedList<GraphNode>();
		for (GraphNode node : graph) {
			if (!map.containsKey(node)) {
				result.add(node);
				queue.offer(node);
			}
		}

		// for debug
//		System.out.println("----------print queue -----------");
//		for (GraphNode n : queue) {
//			System.out.print(n.key + "   ");
//			
//		}
//		System.out.println();
//		System.out.println("----------end print queue--------");

		// do BFS
		while (!queue.isEmpty()) {
			GraphNode node = queue.poll();
			for (GraphNode n : node.neighbors) {
				// in-degree --
				if (map.containsKey(n)) {
					map.put(n, map.get(n) - 1);
					// if in-degree == 0
					if (map.get(n) == 0) {
						result.add(n);
						queue.offer(n);
						map.remove(n);
					}
				}

			}
			
		}

		// check if map.size == 0, if is, then output the result
		// otherwise, there are still element in the map, so, there are cycle.

		// for debug
//		System.out.println("map.size = " + map.size());

//		for (Entry<GraphNode, Integer> entry : map.entrySet()) {
//			System.out.println(entry.getKey().key + " :  " + entry.getValue());
//		}
//		System.out.println("-----print result -----");
//		for (GraphNode n : result) {
//			System.out.print(n.key + " ");
//		}
//		System.out.println("\n--- finish print result ---");
		
		if (map.size() != 0) {
			// NOT a DAG
			return new ArrayList<GraphNode>();
		}
		return result;
	}
	
	
	/*
	 * task6
	 * DFS for topological sorting
	 * 
	 * L <- Empty list that will contain the sorted nodes
	 * 
	 * for each of the nodes in the graph do
	 *    visit(n)
	 *    
	 *    
	 * visit(node n) 
	 * 		if n is visited, then return
	 * 		if n is visiting, then stop, NOT a DAG
	 * 		if n is not visited nor visiting then
	 * 			mark n visiting
	 * 			for each node m which is n's dependency do
	 * 				visit(m)
	 * 			mark n visited
	 * 			add n to head of L
	 * 
	 * 
	 */
	
	public static ArrayList<GraphNode> topologicalSort2(ArrayList<GraphNode> graph) {
		HashSet<GraphNode> visited = new HashSet<GraphNode>();
		HashSet<GraphNode> visiting = new HashSet<GraphNode>();
		ArrayList<GraphNode> list = new ArrayList<GraphNode>();
		for(GraphNode node: graph) {
			visit(node, visited, visiting, list);
		}
		
		if (NOTDAG) {
			return new ArrayList<GraphNode>();
		} 
		
		ArrayList<GraphNode> result = new ArrayList<GraphNode>();
		for(int i = list.size() - 1; i >= 0; i --) {
			result.add(list.get(i));
		}
		
		return result;
	}
	
	public static boolean NOTDAG = false;
	
	public static void visit(GraphNode n, HashSet<GraphNode> visited, HashSet<GraphNode> visiting, ArrayList<GraphNode> list) {
		if (visited.contains(n)) {
			return ;
		}
		if (visiting.contains(n)) {
			NOTDAG = true;
			return;
		}
		
		visiting.add(n);
		for(GraphNode neighbor : n.neighbors) {
			visit(neighbor, visited, visiting, list);
		}
		visited.add(n);
		list.add(n);
	}
	
	
	
	/*
	 * task7
	 * List of courses you need to take to complete the program,
	 * what is the order of the courses you can only take one course at a time.
	 * 
	 * see leetcode 207 210  Course Schedule
	 * 
	 */
	
	/* task8
	 * given a dictionary, but not knowing how the character sequence order(排序方法 未知),
	 * 知道的只有在字典中所有String的order,求可能的character order(假设 character set已经知道了)
	 * 
	 * see leetcode 269 Alien Dictionary
	 */
	
	/*
	 * task9
	 * Excel 表格计算
	 * 
	 * 把所有的格子都计算出来
	 */
	
	
	
	
	

}
