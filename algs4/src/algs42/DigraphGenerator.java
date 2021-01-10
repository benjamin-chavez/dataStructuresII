package algs42;
import algs35.SET;
import algs13.Stack;
import  stdlib.*;

/*************************************************************************
 *  Compilation:  javac DigraphGenerator.java
 *  Execution:    java DigraphGenerator V E
 *  Dependencies: Digraph.java
 *
 *  A digraph generator.
 *
 *************************************************************************/

/**
 *  The <code>DigraphGenerator</code> class provides static methods for creating
 *  various digraphs, including Erdos-Renyi random digraphs, random DAGs,
 *  random rooted trees, random rooted DAGs, random tournaments, path digraphs,
 *  cycle digraphs, and the complete digraph.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/42digraph">Section 4.2</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class DigraphGenerator {
	private static class Edge implements Comparable<Edge> {
		private int v;
		private int w;
		private Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
		public int compareTo(Edge that) {
			if (this.v < that.v) return -1;
			if (this.v > that.v) return +1;
			if (this.w < that.w) return -1;
			if (this.w > that.w) return +1;
			return 0;
		}
	}

	public static Digraph fromIn(In in) {
		Digraph G = new Digraph (in.readInt());
		int E = in.readInt();
		if (E < 0) throw new IllegalArgumentException("Number of edges in a Digraph must be nonnegative");
		for (int i = 0; i < E; i++) {
			int v = in.readInt();
			int w = in.readInt();
			G.addEdge(v, w);
		}
		return G;
	}
	public static Digraph copy (Digraph G) {
		Digraph R = new Digraph (G.V());
        for (int v = 0; v < G.V(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : G.adj(v)) {
                reverse.push(w);
            }
            for (int w : reverse) {
                R.addEdge (v, w);
            }
        }
		return R;
	}
	/**
	 * Create a random digraph with V vertices and E edges.
	 * Expected running time is proportional to V + E.
	 */
	public static Digraph random(int V, int E) {
		if (E < 0) throw new Error("Number of edges must be nonnegative");
		Digraph G = new Digraph(V);

		for (int i = 0; i < E; i++) {
			int v = (int) (Math.random() * V);
			int w = (int) (Math.random() * V);
			G.addEdge(v, w);
		}
		return G;
	}
	/**
	 * Returns a random simple digraph containing <code>V</code> vertices and <code>E</code> edges.
	 * @param V the number of vertices
	 * @param E the number of vertices
	 * @return a random simple digraph on <code>V</code> vertices, containing a total
	 *     of <code>E</code> edges
	 * @throws IllegalArgumentException if no such simple digraph exists
	 */
	public static Digraph simple(int V, int E) {
		if (E > (long) V*(V-1)) throw new IllegalArgumentException("Too many edges");
		if (E < 0)              throw new IllegalArgumentException("Too few edges");
		Digraph G = new Digraph(V);
		SET<Edge> set = new SET<>();
		while (G.E() < E) {
			int v = StdRandom.uniform(V);
			int w = StdRandom.uniform(V);
			Edge e = new Edge(v, w);
			if ((v != w) && !set.contains(e)) {
				set.add(e);
				G.addEdge(v, w);
			}
		}
		return G;
	}

	/**
	 * Returns a random simple digraph on <code>V</code> vertices, with an
	 * edge between any two vertices with probability <code>p</code>. This is sometimes
	 * referred to as the Erdos-Renyi random digraph model.
	 * This implementations takes time propotional to V^2 (even if <code>p</code> is small).
	 * @param V the number of vertices
	 * @param p the probability of choosing an edge
	 * @return a random simple digraph on <code>V</code> vertices, with an edge between
	 *     any two vertices with probability <code>p</code>
	 * @throws IllegalArgumentException if probability is not between 0 and 1
	 */
	public static Digraph simple(int V, double p) {
		if (p < 0.0 || p > 1.0)
			throw new IllegalArgumentException("Probability must be between 0 and 1");
		Digraph G = new Digraph(V);
		for (int v = 0; v < V; v++)
			for (int w = 0; w < V; w++)
				if (v != w)
					if (StdRandom.bernoulli(p))
						G.addEdge(v, w);
		return G;
	}

	/**
	 * Returns the complete digraph on <code>V</code> vertices.
	 * @param V the number of vertices
	 * @return the complete digraph on <code>V</code> vertices
	 */
	public static Digraph complete(int V) {
		return simple(V, V*(V-1));
	}

	/**
	 * Returns a random simple DAG containing <code>V</code> vertices and <code>E</code> edges.
	 * Note: it is not uniformly selected at random among all such DAGs.
	 * @param V the number of vertices
	 * @param E the number of vertices
	 * @return a random simple DAG on <code>V</code> vertices, containing a total
	 *     of <code>E</code> edges
	 * @throws IllegalArgumentException if no such simple DAG exists
	 */
	public static Digraph dag(int V, int E) {
		if (E > (long) V*(V-1) / 2) throw new IllegalArgumentException("Too many edges");
		if (E < 0)                  throw new IllegalArgumentException("Too few edges");
		Digraph G = new Digraph(V);
		SET<Edge> set = new SET<>();
		int[] vertices = new int[V];
		for (int i = 0; i < V; i++) vertices[i] = i;
		StdRandom.shuffle(vertices);
		while (G.E() < E) {
			int v = StdRandom.uniform(V);
			int w = StdRandom.uniform(V);
			Edge e = new Edge(v, w);
			if ((v < w) && !set.contains(e)) {
				set.add(e);
				G.addEdge(vertices[v], vertices[w]);
			}
		}
		return G;
	}

	/**
	 * Returns a random tournament digraph on <code>V</code> vertices. A tournament digraph
	 * is a DAG in which for every two vertices, there is one directed edge.
	 * A tournament is an oriented complete graph.
	 * @param V the number of vertices
	 * @return a random tournament digraph on <code>V</code> vertices
	 */
	public static Digraph tournament(int V) {
		return dag(V, V*(V-1)/2);
	}

	/**
	 * Returns a random rooted-in DAG on <code>V</code> vertices and <code>E</code> edges.
	 * A rooted in-tree is a DAG in which there is a single vertex
	 * reachable from every other vertex.
	 * The DAG returned is not chosen uniformly at random among all such DAGs.
	 * @param V the number of vertices
	 * @param E the number of edges
	 * @return a random rooted-in DAG on <code>V</code> vertices and <code>E</code> edges
	 */
	public static Digraph rootedInDAG(int V, int E) {
		if (E > (long) V*(V-1) / 2) throw new IllegalArgumentException("Too many edges");
		if (E < V-1)                throw new IllegalArgumentException("Too few edges");
		Digraph G = new Digraph(V);
		SET<Edge> set = new SET<>();

		// fix a topological order
		int[] vertices = new int[V];
		for (int i = 0; i < V; i++) vertices[i] = i;
		StdRandom.shuffle(vertices);

		// one edge pointing from each vertex, other than the root = vertices[V-1]
		for (int v = 0; v < V-1; v++) {
			int w = StdRandom.uniform(v+1, V);
			Edge e = new Edge(v, w);
			set.add(e);
			G.addEdge(vertices[v], vertices[w]);
		}

		while (G.E() < E) {
			int v = StdRandom.uniform(V);
			int w = StdRandom.uniform(V);
			Edge e = new Edge(v, w);
			if ((v < w) && !set.contains(e)) {
				set.add(e);
				G.addEdge(vertices[v], vertices[w]);
			}
		}
		return G;
	}

	/**
	 * Returns a random rooted-out DAG on <code>V</code> vertices and <code>E</code> edges.
	 * A rooted out-tree is a DAG in which every vertex is reachable from a
	 * single vertex.
	 * The DAG returned is not chosen uniformly at random among all such DAGs.
	 * @param V the number of vertices
	 * @param E the number of edges
	 * @return a random rooted-out DAG on <code>V</code> vertices and <code>E</code> edges
	 */
	public static Digraph rootedOutDAG(int V, int E) {
		if (E > (long) V*(V-1) / 2) throw new IllegalArgumentException("Too many edges");
		if (E < V-1)                throw new IllegalArgumentException("Too few edges");
		Digraph G = new Digraph(V);
		SET<Edge> set = new SET<>();

		// fix a topological order
		int[] vertices = new int[V];
		for (int i = 0; i < V; i++) vertices[i] = i;
		StdRandom.shuffle(vertices);

		// one edge pointing from each vertex, other than the root = vertices[V-1]
		for (int v = 0; v < V-1; v++) {
			int w = StdRandom.uniform(v+1, V);
			Edge e = new Edge(w, v);
			set.add(e);
			G.addEdge(vertices[w], vertices[v]);
		}

		while (G.E() < E) {
			int v = StdRandom.uniform(V);
			int w = StdRandom.uniform(V);
			Edge e = new Edge(w, v);
			if ((v < w) && !set.contains(e)) {
				set.add(e);
				G.addEdge(vertices[w], vertices[v]);
			}
		}
		return G;
	}

	/**
	 * Returns a random rooted-in tree on <code>V</code> vertices.
	 * A rooted in-tree is an oriented tree in which there is a single vertex
	 * reachable from every other vertex.
	 * The tree returned is not chosen uniformly at random among all such trees.
	 * @param V the number of vertices
	 * @return a random rooted-in tree on <code>V</code> vertices
	 */
	public static Digraph rootedInTree(int V) {
		return rootedInDAG(V, V-1);
	}

	/**
	 * Returns a random rooted-out tree on <code>V</code> vertices. A rooted out-tree
	 * is an oriented tree in which each vertex is reachable from a single vertex.
	 * It is also known as a <em>arborescence</em> or <em>branching</em>.
	 * The tree returned is not chosen uniformly at random among all such trees.
	 * @param V the number of vertices
	 * @return a random rooted-out tree on <code>V</code> vertices
	 */
	public static Digraph rootedOutTree(int V) {
		return rootedOutDAG(V, V-1);
	}

	/**
	 * Returns a path digraph on <code>V</code> vertices.
	 * @param V the number of vertices in the path
	 * @return a digraph that is a directed path on <code>V</code> vertices
	 */
	public static Digraph path(int V) {
		Digraph G = new Digraph(V);
		int[] vertices = new int[V];
		for (int i = 0; i < V; i++) vertices[i] = i;
		StdRandom.shuffle(vertices);
		for (int i = 0; i < V-1; i++) {
			G.addEdge(vertices[i], vertices[i+1]);
		}
		return G;
	}

	/**
	 * Returns a complete binary tree digraph on <code>V</code> vertices.
	 * @param V the number of vertices in the binary tree
	 * @return a digraph that is a complete binary tree on <code>V</code> vertices
	 */
	public static Digraph binaryTree(int V) {
		Digraph G = new Digraph(V);
		int[] vertices = new int[V];
		for (int i = 0; i < V; i++) vertices[i] = i;
		StdRandom.shuffle(vertices);
		for (int i = 1; i < V; i++) {
			G.addEdge(vertices[i], vertices[(i-1)/2]);
		}
		return G;
	}

	/**
	 * Returns a cycle digraph on <code>V</code> vertices.
	 * @param V the number of vertices in the cycle
	 * @return a digraph that is a directed cycle on <code>V</code> vertices
	 */
	public static Digraph cycle(int V) {
		Digraph G = new Digraph(V);
		int[] vertices = new int[V];
		for (int i = 0; i < V; i++) vertices[i] = i;
		StdRandom.shuffle(vertices);
		for (int i = 0; i < V-1; i++) {
			G.addEdge(vertices[i], vertices[i+1]);
		}
		G.addEdge(vertices[V-1], vertices[0]);
		return G;
	}
	
    /**
     * Returns an Eulerian cycle digraph on {@code V} vertices.
     *
     * @param  V the number of vertices in the cycle
     * @param  E the number of edges in the cycle
     * @return a digraph that is a directed Eulerian cycle on {@code V} vertices
     *         and {@code E} edges
     * @throws IllegalArgumentException if either {@code V <= 0} or {@code E <= 0}
     */
    public static Digraph eulerianCycle(int V, int E) {
        if (E <= 0)
            throw new IllegalArgumentException("An Eulerian cycle must have at least one edge");
        if (V <= 0)
            throw new IllegalArgumentException("An Eulerian cycle must have at least one vertex");
        Digraph G = new Digraph(V);
        int[] vertices = new int[E];
        for (int i = 0; i < E; i++)
            vertices[i] = StdRandom.uniform(V);
        for (int i = 0; i < E-1; i++) {
            G.addEdge(vertices[i], vertices[i+1]);
        }
        G.addEdge(vertices[E-1], vertices[0]);
        return G;
    }

    /**
     * Returns an Eulerian path digraph on {@code V} vertices.
     *
     * @param  V the number of vertices in the path
     * @param  E the number of edges in the path
     * @return a digraph that is a directed Eulerian path on {@code V} vertices
     *         and {@code E} edges
     * @throws IllegalArgumentException if either {@code V <= 0} or {@code E < 0}
     */
    public static Digraph eulerianPath(int V, int E) {
        if (E < 0)
            throw new IllegalArgumentException("negative number of edges");
        if (V <= 0)
            throw new IllegalArgumentException("An Eulerian path must have at least one vertex");
        Digraph G = new Digraph(V);
        int[] vertices = new int[E+1];
        for (int i = 0; i < E+1; i++)
            vertices[i] = StdRandom.uniform(V);
        for (int i = 0; i < E; i++) {
            G.addEdge(vertices[i], vertices[i+1]);
        }
        return G;
    }


	/**
	 * Returns a random simple digraph on <code>V</code> vertices, <code>E</code>
	 * edges and (at most) <code>c</code> strong components. The vertices are randomly
	 * assigned integer labels between  <code>0</code> and <code>c-1</code> (corresponding to
	 * strong components). Then, a strong component is created among the vertices
	 * with the same label. Next, random edges (either between two vertices with
	 * the same labels or from a vertex with a smaller label to a vertex with a
	 * larger label). The number of components will be equal to the number of
	 * distinct labels that are assigned to vertices.
	 *
	 * @param V the number of vertices
	 * @param E the number of edges
	 * @param c the (maximum) number of strong components
	 * @return a random simple digraph on <code>V</code> vertices and
               <code>E</code> edges, with (at most) <code>c</code> strong components
	 * @throws IllegalArgumentException if <code>c</code> is larger than <code>V</code>
	 */
	public static Digraph strong(int V, int E, int c) {
		if (c >= V || c <= 0)
			throw new IllegalArgumentException("Number of components must be between 1 and V");
		if (E <= 2*(V-c))
			throw new IllegalArgumentException("Number of edges must be at least 2(V-c)");
		if (E > (long) V*(V-1) / 2)
			throw new IllegalArgumentException("Too many edges");

		// the digraph
		Digraph G = new Digraph(V);

		// edges added to G (to avoid duplicate edges)
		SET<Edge> set = new SET<>();

		int[] label = new int[V];
		for (int v = 0; v < V; v++)
			label[v] = StdRandom.uniform(c);

		// make all vertices with label c a strong component by
		// combining a rooted in-tree and a rooted out-tree
		for (int i = 0; i < c; i++) {
			// how many vertices in component c
			int count = 0;
			for (int v = 0; v < G.V(); v++) {
				if (label[v] == i) count++;
			}

			// if (count == 0) System.err.println("less than desired number of strong components");

			int[] vertices = new int[count];
			int j = 0;
			for (int v = 0; v < V; v++) {
				if (label[v] == i) vertices[j++] = v;
			}
			StdRandom.shuffle(vertices);

			// rooted-in tree with root = vertices[count-1]
			for (int v = 0; v < count-1; v++) {
				int w = StdRandom.uniform(v+1, count);
				Edge e = new Edge(w, v);
				set.add(e);
				G.addEdge(vertices[w], vertices[v]);
			}

			// rooted-out tree with root = vertices[count-1]
			for (int v = 0; v < count-1; v++) {
				int w = StdRandom.uniform(v+1, count);
				Edge e = new Edge(v, w);
				set.add(e);
				G.addEdge(vertices[v], vertices[w]);
			}
		}

		while (G.E() < E) {
			int v = StdRandom.uniform(V);
			int w = StdRandom.uniform(V);
			Edge e = new Edge(v, w);
			if (!set.contains(e) && v != w && label[v] <= label[w]) {
				set.add(e);
				G.addEdge(v, w);
			}
		}

		return G;
	}

	/**
	 * Unit tests the <code>DigraphGenerator</code> library.
	 */
	private static void print (Digraph G, String filename) {
		System.out.println(filename);
		System.out.println(G);
		System.out.println();
		G.toGraphviz (filename + ".png");
	}
	public static void main(String[] args) {
		args = new String [] { "6", "10", "0.25", "3" };

		int V = Integer.parseInt(args[0]);
		int E = Integer.parseInt(args[1]);
		double p = Double.parseDouble (args[2]);
		int c = Integer.parseInt(args[3]);

		for (int i=5; i>0; i--) {
			print(DigraphGenerator.random(V,E), "random-" + V + "-" + E);
			print(DigraphGenerator.simple(V,E), "simpleA-" + V + "-" + E);
			print(DigraphGenerator.simple(V,p), "simpleB-" + V + "-" + p);
			print(DigraphGenerator.complete(V), "complete-" + V);
			print(DigraphGenerator.dag(V,E), "dag-" + V + "-" + E);
			print(DigraphGenerator.tournament(V), "tournament-" + V);
			print(DigraphGenerator.rootedInDAG(V,E), "rootedInDAG-" + V + "-" + E);
			print(DigraphGenerator.rootedOutDAG(V,E), "rootedOutDAG-" + V + "-" + E);
			print(DigraphGenerator.rootedInTree(V), "rootedInTree-" + V);
			print(DigraphGenerator.rootedOutTree(V), "rootedOutTree-" + V);
			print(DigraphGenerator.path(V), "path-" + V);
			print(DigraphGenerator.binaryTree(V), "rootedInTreeBinary-" + V);
			print(DigraphGenerator.cycle(V), "cycle-" + V);
			if (E <= 2*(V-c)) E = 2*(V-c)+1;
			print(DigraphGenerator.strong(V,E,c), "strong-" + V + "-" + E + "-" + c);
		}
	}
}
