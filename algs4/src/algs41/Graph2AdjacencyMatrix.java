//
//
////Exercise 4.1.3 (Solution published at http://algs4.cs.princeton.edu/)
//package algs41;
//import stdlib.*;
//import algs13.Bag;
//import algs13.Queue;
//
///**
//*  The <code>Graph</code> class represents an undirected graph of vertices
//*  named 0 through V-1.
//*  It supports the following operations: add an edge to the graph,
//*  iterate over all of the neighbors adjacent to a vertex.
//*  Parallel edges and self-loops are permitted.
//*  <p>
//*  For additional documentation, see <a href="http://algs4.cs.princeton.edu/51undirected">Section 5.1</a> of
//*  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
//*/
//public class Graph2AdjacencyMatrix {
//	int[][] adjMat;		//two-dimensional array to store the number of edges
//	int E;				// number of edges
//	
//
//	/**
//	 * Create an empty graph with V vertices.
//	 */
//	@SuppressWarnings("unchecked")
//	public Graph2AdjacencyMatrix(int V) {
//		adjMat = new int[V][V];
//		E = 0;
//	}
//	
//	/**
//	 * Return the number of vertices in the graph.
//	 */
//	public int V() { 
//		return adjMat.length; 
//	}
//
//	/**
//	 * Return the number of edges in the graph.
//	 */
//	public int E() { return E; }
//
//
//	/**
//	 * Add the undirected edge v-w to graph.
//	 * @throws java.lang.IndexOutOfBoundsException unless both 0 <= v < V and 0 <= w < V
//	 */
//	public void addEdge(int v, int w) {
//		adjMat[v][w] = 1;
//		adjMat[w][v] = 1;
//		E++; 
//	}
//
//
//	/**
//	 * Return the list of neighbors of vertex v as in Iterable.
//	 * @throws java.lang.IndexOutOfBoundsException unless 0 <= v < V
//	 */
//	public Iterable<Integer> adj(int v) {
//		if (v < 0 || v >= V) throw new IndexOutOfBoundsException();
//		Queue<Integer> q = new Queue<Integer>();
//		for (int col=0; col < adjMat.length; col++ ) {
//			if ( adjMat[v][col] == 1)
//				q.enqueue(col);
//		}
//		return q;
//	}
//	
// /**
//  * Returns the degree of vertex {@code v}.
//  *
//  * @param  v the vertex
//  * @return the degree of vertex {@code v}
//  * @throws IllegalArgumentException unless {@code 0 <= v < V}
//  */
//
//
//	/**
//	 * Return a string representation of the graph.
//	 */
//	public String toString() {
//		StringBuilder s = new StringBuilder();
//		String NEWLINE = System.getProperty("line.separator");
//		s.append(V + " vertices, " + E + " edges " + NEWLINE);
//		for (int v = 0; v < V; v++) {
//			s.append(v + ": ");
//			for (int w : adj[v]) {
//				s.append(w + " ");
//			}
//			s.append(NEWLINE);
//		}
//		return s.toString();
//	}
//
//	/**
//	 * Save a graphviz representation of the graph.
//	 * See <a href="http://www.graphviz.org/">graphviz.org</a>.
//	 */
//	public void toGraphviz(String filename) {
//		GraphvizBuilder gb = new GraphvizBuilder ();
//		for (int v = 0; v < V; v++) {
//			gb.addNode (v);
//			boolean showSelfLoop = false;
//			for (int w : adj[v]) {
//				if (v < w) // only once each edge
//					gb.addEdge (v, w);
//				if (v == w) {
//					showSelfLoop = !showSelfLoop;
//					if (showSelfLoop)
//						gb.addEdge (v, w);
//				}
//			}
//		}
//		gb.toFileUndirected (filename);
//	}
//
//	/**
//	 * Test client.
//	 */
//	public static void main(String[] args) {
//		//args = new String [] { "data/tinyCG.txt" };
//		args = new String [] { "data/tinyG.txt" };
//		//args = new String [] { "20", "40" };
//
//		Graph2AdjacencyMatrix G;
//		if (args.length == 1) {
//			In in = new In(args[0]);
//			G = GraphGenerator.fromIn (in);
//		} else {
//			int V = Integer.parseInt (args[0]);
//			int E = Integer.parseInt (args[1]);
//			G = GraphGenerator.simple(V, E);
//		}
//		StdOut.println(G);
//		G.toGraphviz ("g.png");
//	}
//}
