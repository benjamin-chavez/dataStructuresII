
package homework;
import stdlib.*;

import java.util.ArrayList;
import java.util.LinkedList;

import algs13.Bag;


/**
 *  The <code>ELGraph</code> class represents an undirected graph of vertices
 *  named 0 through V-1.   
 *  It uses the EdgeList representation.
 *  
 *  It supports the following operations: 
 *    - addEdge    add an edge to the graph,
 *    - adj        iterate over all of the neighbors adjacent to a vertex.
 *    - delete     remove an edge
 *    - degree     determine the degree of a vertex
 *    - E          return the number of edges
 *    - hasEdge    determine if an edge exists
 *    
 *      Parallel edges and self-loops are NOT permitted.
 */

/*
 * DSII Homework7  version 1.0
 * 
 * Complete the 6 methods marked ToDo
 * For each of the ToDo methods, determine the order of growth of the method in terms of V,E
 * place your answers in the space provided below. You must include a brief justification.
 * 
 * You must not change the declaration of any method.
 * 
 *   Benjamin M. Chavez
 *  
 *  Delete one of the following:
 *  A) The work submitted here is solely mine. 
 *  
 *  
 *  Order of growth  answers go here
 *  METHOD				ORDER OF GROWTH				EXPLANATION	
 *  addEdge()			O(1) - Constant Time		The addEdge() method is constant time because the size of the linkedList does not impact the 
 *  												amount of time it takes to add an Edge. Performance is independent of E & V. That being said, my implementation
 *  												is actually E performance because we check to see if the edge exists prior to adding it. In order to do this, we
 *  												have to look at every edge in the list.
 *  
 *  hasEdge()			E							In the worst case, we would have to look at every item in the list of edges if the edge did not exist.	
 *  						
 *  delete()			E							In the worst case, we would have to look at every item in the list of edges if the edge did not exist.
 *  
 *  E()					O(1) - Constant Time		I could not find how the Java LinkedList class actually implements its size function, so I am making an assumption that
 *  												it contains a counter variable like N to keep track of its size. If this is the case, then the E() method is simply 
 *  												constant time because it is just calling the linkedList size method.
 *  
 *  adj()				E							In my implementation, I basically created an adjacency list within my method. In order to do this, we must iterate over
 *  												the entire graph, visiting every edge, to place the edges in their corresponding list or adjacent edges. I believe there
 *  												would be a simpler way to implement the method, but any refactorization would still require us to iterate through the 
 *  												entire list of edges.	
 *  
 *  degree()			E							My implementation of degree calls the adj() method and therefore is also requiring us to process every edge in the list.
 *  
 */


public class ELGraph {   // an EdgeList Graph
	private final int V;				// number of vertices
	private LinkedList<Edge> theEdges;  // the edge list - uses Standard Java LinkedList class

	public class Edge {  // nested class to represent an edge
		public  int u,v;  // the end points of the edge
		public Edge(int u, int v) {  // constructor
			this.u = u;
			this.v = v;
		}
		public boolean equals(Object that) {      
			Edge thet = (Edge)that;
			if ( this == null || that == null ) throw new IllegalArgumentException(" bad edge");
			return ( this.u == thet.u && this.v == thet.v) || (this.u==thet.v && this.v==thet.u);
		}
	}
	/**
	 * Create an empty graph with V vertices.
	 */
	public ELGraph(int V) {
		if (V < 0) throw new Error("Number of vertices must be nonnegative");
		this.V = V;
		theEdges = new LinkedList<Edge>();		//CREATING LINKED LIST HERE
	}

	/**
	 * Add the undirected edge v-w to graph.
	 * @throws java.lang.IndexOutOfBoundsException unless both 0 <= v < V and 0 <= w < V
	 * 
	 * enforces No self loops or parallel edges
	 * 
	 * return true if edge was successfully added
	 * return false if edge already existed
	 */
	public boolean addEdge(int v, int w) {
		if (v < 0 || v >= V) throw new IndexOutOfBoundsException();
		if (w < 0 || w >= V) throw new IndexOutOfBoundsException();
		
		//ToDo 1.  implement this method
		// Need to explicitly disallow parallel edges and self loops
		// i.e. self loop = 0,0   <- this s/b illegal
		if (v == w) {
			// check to see if the new edge is a self loop and return false if it is
			return false;
		}
		Edge newEdge = new Edge(v, w);				//declare the new edge
		if (theEdges.contains(newEdge)) {		
			return false;
		}
		theEdges.add(newEdge);
		return true;
	}
	/*
	 * hasEdge  
	 *    Determine if the graph has an edge between  u,v
	 */
	public boolean hasEdge(int u,int v) {

		//ToDo 2.  implement this method
		Edge thisEdge = new Edge(u, v);				//declare the new edge
		if (theEdges.contains(thisEdge)) {
			return true;
		}
		return false;
	}
	/* delete
	 * 
	 * remove edge between u,v if it exists.
	 * 
	 * returns: 
	 *     true if successful
	 *     false if edge did not exist
	 */

	public boolean delete(int u, int v) {
		//To do 3   implement this method
		Edge thisEdge = new Edge(u, v);	
		return theEdges.remove(thisEdge);
	}
	/*
	 * Return the number of vertices in the graph.
	 */
	public int V() { return V; }

	/*
	 * Return the number of edges in the graph.
	 */
	public int E() { 
		// ToDo 4   implement this method
		return theEdges.size();
//		return -1;   
 
	}

	/*
	 * adj
	 * 
	 * Return the list of neighbors of vertex v as an Iterable.
	 * @throws java.lang.IndexOutOfBoundsException unless 0 <= v < V
	 */
	@SuppressWarnings("unchecked")
	public Iterable<Integer> adj(int v) {
		if (v < 0 || v >= V) throw new IndexOutOfBoundsException();

		// ToDo 5    implement this method
		Bag<Integer>[] adj;
		adj = (Bag<Integer>[]) new Bag[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new Bag<Integer>();
		}

		for (Edge e : theEdges) {
			adj[e.v].add(e.u);
			adj[e.u].add(e.v);
		}
		
		return adj[v];
		
//		Attempt at re-factoring in order to implement the method without building an entire adjacency list. I got stuck trying to define
//		the iterable and didn't complete the re-factorizaiton. 
//		
//		Iterable<Integer> adj = adj(v);
//		for (Edge e : theEdges) {
//			if (e.v == v) {
//				((Bag<Integer>) adj).add(e.u);
//			}
//			else if (e.u == v) {
//				((Bag<Integer>) adj).add(e.v);
//			}	
//		}
//		
//		return adj;
	}

	/**
	 * Returns the degree of vertex {@code v}.
	 *
	 * @param  v the vertex
	 * @return the degree of vertex {@code v}
	 * @throws IllegalArgumentException unless {@code 0 <= v < V}
	 */
	public int degree(int v) {
		if (v < 0 || v >= V) throw new IndexOutOfBoundsException();
		// ToDo 6    implement this method
		Iterable<Integer> adj = adj(v);
		return ((Bag<Integer>) adj).size();
		
	}


	/* perform tests
	 * 
	 * comment in/out as needed
	 */
	public static void main(String[] args) {

		AllAddEdgeTests();
//		adjTest();
//		hasEdgeTest();
//		degreeTest();
//		deleteEdgeTest();
		//size  tests?

	}

	/*
	 * AllAddEdgeTests
	 * 
	 * invoke addEdge tests
	 * 
	 */
	public static void AllAddEdgeTests() {

		String fileName;
		fileName = "data/tinyG.txt";
		// fileName = "data/tinyCG.txt";

		// test addEdge using  fromIn
		StdOut.println( "FromIn test.  graph data from file");
		In in = new In(fileName);
		while (! in.isEmpty()) {
			StdOut.println( in.readLine());
		}

		in = new In(fileName);
		ELGraph G = fromIn(in);   // create graph from data file
		StdOut.println( "actual graph info");
		StdOut.println( G);

		addEdgeTest(fileName,0,4);   // try to add edge that is not there
		addEdgeTest(fileName,0,5);	 // try to add an edge that is already there!

		// add your own addEdge tests if you want
		addEdgeTest(fileName,4,4);   // try to add a self loop
		addEdgeTest(fileName,5,0);	 // try to add a parallel edge
		
	}
	/* addEdgeTest
	 * 
	 * a single test of addEdge
	 * method
	 *      create a graph using fromIn
	 *      invoke addEdge using parameters
	 *      manually compare before and after graph data
	 */
	public static void addEdgeTest(String fileName, int u, int v) {
		In in = new In(fileName);

		ELGraph G = fromIn(in);   // create graph from data file

		//G.toGraphviz ("g.png");    // manually verify that the GraphViz images is correct
		StdOut.format("addEdge test:  file: %s  edge: (%d,%d)\n", fileName,u,v);
		StdOut.println("graph Before");
		StdOut.println(G);         // or the printed graph corresponds to the data file

		if ( G.addEdge(u,v) ) 
			StdOut.format("Success: add edge (%d,%d) \n",u,v);
		else
			StdOut.format("Failure: add edge (%d,%d) \n",u,v);

		//G.toGraphviz ("g.png");    // manually verify that the GraphViz images is correct
		StdOut.println("    graph After");
		StdOut.println(G);         // or the printed graph corresponds to the data file
	}
	/* adjTests
	 * 
	 * check that the adj method is correct
	 * method:  create a graph using fromIn
	 * 		    
	 *          print the graph file info or view graphViz file
	 *          choose a vertexToCheck
	 *          print the adjacency list using  adj method
	 *          manually compare the results
	 *          
	 *  precondition:   addEdge is correct
	 */
	public static void adjTest(){
		String fileName;
		fileName = "data/tinyG.txt";
		//fileName = "data/tinyCG.txt";
		In in = new In(fileName);
		ELGraph G = fromIn(in);   // create graph from data file

		int vertexToCheck = 5;   // try different values for the vertex
		StdOut.format("adj Test   file: %s  vertex to check %d \n", fileName, vertexToCheck);
		StdOut.println("graph info");
		StdOut.println(G);  

		StdOut.println("vertex  adjacency list"); 
		for (Integer u : G.adj(5) )
			StdOut.println(u);

	}

	/* hasEdgeTests
	 * 
	 * check that the hasEdge method is correct
	 * method:  create a graph using fromIn
	 * 		    
	 *          print the graph file info or view graphViz file
	 *          choose an edge to validate
	 *          print result of function
	 *          manually compare the results
	 *          
	 *  precondition:   addEdge is correct
	 */
	public static void hasEdgeTest() {
		String fileName;
		fileName = "data/tinyG.txt";
		//fileName = "data/tinyCG.txt";
		In in = new In(fileName);
		ELGraph G = fromIn(in);   // create graph from data file

		StdOut.format("hasEdge Test  file: %s  \n", fileName );
		StdOut.println("graph info");
		StdOut.println(G); 
		//G.toGraphviz ("g.png");

		int u = 0, v = 2;   // check for this edge, try different values

		boolean result =  G.hasEdge(u,v);
		StdOut.format(" hasEdge(%d,%d) --> %b   ;  manually check this\n\n", u,v,result);
	}

	/* degreeTest
	 * 
	 * check that the degree method works correctly.
	 * 
	 * method:  create a graph using fromIn
	 * 		    
	 *          print the graph file info or view graphViz file
	 *          choose an vertex to check
	 *          print result of function
	 *          manually compare the results
	 *          
	 *  precondition:   addEdge is correct
	 */
	public static void degreeTest() {
		String fileName;
		fileName = "data/tinyG.txt";
		//fileName = "data/tinyCG.txt";
		In in = new In(fileName);
		ELGraph G = fromIn(in);   // create graph from data file

		int vertexToCheck = 5;   // try different values for the vertex
		StdOut.format("degree Test   file: %s  vertex to check %d \n", fileName, vertexToCheck);
		StdOut.println("graph info");
		StdOut.println(G);  
		//G.toGraphviz ("g.png");

		int result = G.degree(vertexToCheck);
		StdOut.format(" degree(%d) --> %d   ;  manually check this\n\n", vertexToCheck,result);
	}


	/* deleteEdgeTest
	 * 
	 * remove an edge if it exits
	 * method:  create a graph using fromIn
	 * 		    
	 *          print the before graph file info or view graphViz file
	 *          choose an edge to delete
	 *          print status result of function
	 *          print the after graph file info
	 *          manually compare the results
	 *          
	 *  precondition:   addEdge is correct
	 */
	public static void deleteEdgeTest() {
		String fileName;
		fileName = "data/tinyG.txt";
		//fileName = "data/tinyCG.txt";
		In in = new In(fileName);
		ELGraph G = fromIn(in);   // create graph from data file

		StdOut.format("deleteEdge Test  file: %s  \n", fileName );
		StdOut.println("graph Before");
		StdOut.println(G); 
		//G.toGraphviz ("g.png");

		int u = 0, v = 2;   // attempt to delete edge, try different values

		boolean result =  G.delete(u,v);
		StdOut.format(" deleteEdge(%d,%d) --> %b   ;  manually check this\n\n", u,v,result);
		//G.toGraphviz ("g.png");    // manually verify that the GraphViz images is correct
		StdOut.println("    graph After");
		StdOut.println(G);         // or the printed graph corresponds to the data file
	}


	/* **********************************************************************
	 *               utility routines;  look but don't alter                
	 */


	/**
	 * Return a string representation of the graph.
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		String NEWLINE = System.getProperty("line.separator");
		s.append(V + " vertices, " + E() + " edges " + NEWLINE);
		for ( Edge e : theEdges ) {
			s.append(e.u + " " + e.v);
			s.append(NEWLINE);
		}
		return s.toString();
	}

	/**
	 * Save a graphviz representation of the graph.
	 * See <a href="http://www.graphviz.org/">graphviz.org</a>.
	 */
	public void toGraphviz(String filename) {
		GraphvizBuilder gb = new GraphvizBuilder ();
		for (int v = 0; v < V; v++) {
			gb.addNode (v);
		}
		for ( Edge e : theEdges ) {
			gb.addEdge(e.u,e.v);
		}
		gb.toFileUndirected (filename);
	}

	/* fromIn
	 * 
	 * create an ELGraph from an input file
	 * 
	 * requires addEdge for correct operation
	 */
	public static ELGraph fromIn (In in) {
		ELGraph G = new ELGraph (in.readInt());
		int E = in.readInt();
		for (int i = 0; i < E; i++) {
			int v = in.readInt();
			int w = in.readInt();
			G.addEdge(v, w);
		}
		return G;
	}

}
