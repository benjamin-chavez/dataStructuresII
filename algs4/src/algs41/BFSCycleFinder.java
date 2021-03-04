//package algs41;
//import algs13.Queue;
//import stdlib.*;
//import stdlib.StdOut;
//
///* ************************************************************ 
// * 
// * Use breadth first search on an undirected graph to determine if it has
// * a cycle.
// *************************************************************/
//
//public class BFSCycleFinder {
//	private final boolean[] marked;	//marked[V] = is there an s-v path
//	private final int[] edgeTo;		//edgeTo[V] = previous edge on shortest s-v path
//	
//	private boolean hasCycle;
//	private Queue<Integer> aCycle;
//	
//	public boolean hasCycle() {
//		return hasCycle;
//	}
//	
//	public Iterable<Integer> cycleVerts() {
//		return aCycle;
//	}
//	
//	// single source
//	public BFSCycleFinder(Graph G, int s) {
//		marked = new boolean[G.V()];
//		edgeTo = new int[G.V()];
//		
//		bfs(G, s);	// search from s
//	}
//	
//	// BFS from single source
//	private void bfs(Graph G, int s) {
//		Queue<Integer> q = new Queue<>();
//		for (int v = 0; v < G.V(); v++) distTo[v] = INFINITY;
//		distTo[s] = 0;
//		marked[s] = true;
//		q.enqueue(s);
//
//		while (!q.isEmpty()) {
//			int v = q.dequeue();
//			for (int w : G.adj(v)) {
//				if (!marked[w]) {
//					edgeTo[w] = v;
////					distTo[w] = distTo[v] + 1;
//					marked[w] = true;
//					q.enqueue(w);
//				}
//				else if ( marked[w] && edgeTo[v] !=w) {
//					hasCycle = true;
//				}
//				
//			}
//		}
//	}
//
//	// test client
//	public static void main(String[] args) {
//		args = new String [] {"data/cycleTest1.txt", "0"};
//		In in = new In(args[0]);
//		Graph G = new Graph(in);
//		//G = GraphGenerator.path(8)
//		//G = GraphGenerator.binaryTree(8)
//		//G = GraphGenerator.complete(6)
//		//G = GraphGenerator.cycle(6)
//		
//		StdOut.println(G);
////		G.toGraphviz("g.png");
//		
//		BFSCycleFinder aBfsCycle = new BFSCycleFinder(G, 0);
//		
//		if ( aBfsCycle.hasCycle()) {
//			StdOut.println(" has a cycle");
//		}
//		else StdOut.println(" acyclic");
//	}
//
//}
