package small_yan;

public class Class11_Graph_topological_sort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * Class 11 Graph, Topological Sort
	 * 
	 */
	/*
	 * task1
	 * How to represent a graph? 
	 * 1 adjacent matrix
	 * 2 adjacent list
	 * 
	 * DFS
	 * BFS 
	 * Topological Sort
	 */
	
	/*
	 * task2
	 * Celebrity Problem
	 * a group of people, if there exists one person such that he does not know any other people, 
	 * but every person knows him. is there such a person existed? if so, who is it?
	 * 1 how many celebrities in total we can have? -- at most one
	 * suppose we have two celebrities, c1 and c2. if c1 knows c2, c1 is not celebrity
	 * if c1 donesn't knows c2, c2 is not celebrity
	 * 
	 * 2 graph, represents in matrix
	 * (1) each pair of persons, we know one of them is not celebrity.
	 * (2) we can do pair comparison for n-1 times, then we have a candidate
	 * (3) we still need to check if the candidate is the celebrity.  
	 */
	
	/*
	 * task3
	 * maximum value
	 */
	
	
	/*
	 * task4
	 * DFS continiued
	 * 1 deep copy a directed/undirected graph
	 * DFS/BFS
	 * 2 how to determine if an directed graph is a tree? root-node
	 * tree: from root, there in only one path
	 * DFS- a node is visited only once, we can not visit it again
	 * 
	 * 3 how to determine if an directed graph does not have circle? 
	 * method 1: 
	 * for each of the nodes in the graph, do DFS -> only mark current path visit
	 * method 2: 
	 * We will need two markers
	 * - visiting: we are visiting the node now, but not finished
	 *    visiting:: after all the neighbors of node are visited, we can mark the node visited, before that, 
	 *               the node is in visiting status.
	 * -visited: we finish the visiting node
	 * 
	 * dfs(GrapohNode node) {
	 * 	  if (node is visited) {
	 * 		 return ;
	 *    }
	 *    if (node is visiting) {
	 *    	 there is a cycle.
	 *    }
	 *    
	 *    if (node is not visited or visiting) {
	 *    	 mark node visiting
	 *       for (neighbor: node.neighbors) {
	 *       	dfs(neighbor);
	 *       }
	 *       mark node visited
 	 *    }
	 * }
	 * 
	 */
	
	
	/*
	 * task5
	 * Topological Sort
	 * 
	 * 
	 * 
	 */
	
	
	

}
