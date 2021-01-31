package examples126;

/*
 * this is the basic binary tree class code we used 
 * to practice writing tree methods
 * 
 * Note that it is NOT generic and the Node class only stores a integer key
 * it also contains routines that allow a binary search tree to be constructed
 * 
 */


import algs13.Queue;

import stdlib.GraphvizBuilder;
import stdlib.StdDraw;
import stdlib.StdOut;

public class BasicBinaryTree {

	private Node root;

	private static class Node {
		public final int key;
		public Node left, right;
		public Node(int key) { this.key = key; }
	}

	private BasicBinaryTree( Node x) { root = x; }

	public BasicBinaryTree() {};
	
	public int leftmost() { 
		return -1;
	}

	
	public static void main(String[] args) {

		BasicBinaryTree tree1 = buildTree(1);
		BasicBinaryTree tree2 = buildTree(2);
		BasicBinaryTree tree3 = buildTree(3);
//
//		tree1.drawTree();
//		tree1.toGraphviz("tree1.png");
//		tree2.toGraphviz("tree2.png");
//		tree3.toGraphviz("tree3.png");
//
//		StdOut.println("leftMost node of tree1 is " + tree3.leftmost());
//		StdOut.println("height of tree1 is " + tree3.height());

//		BasicBinaryTree bst = buildSearchTree( new int[] {6,5,7,4,8,3,9,2,1});
		//bst.toGraphviz("searchTre.png");
		
		BasicBinaryTree bst = buildSearchTree( new int[] {9,15,17,4,8,3,5,12,1});
		bst.drawTree();
		
		for (int i : bst.keys())
			StdOut.println(i);
	}


	//   if we use *put* to build a tree then it will 
	//   satisfy the BST property
	//      --> it will be a binary search tree
	public static BasicBinaryTree buildSearchTree( int[] data) {

		BasicBinaryTree bst = new BasicBinaryTree();
		for (int i=0; i < data.length; i++)
			bst.put(data[i]);
		return bst;

	}

	// build some trees manually to practice on
	public static BasicBinaryTree buildTree(int n) {

		if ( n == 1 ) {
			Node n1 = new Node(1);
			Node n2 = new Node(2);
			Node n3 = new Node(3);
			Node n4 = new Node(4);
			Node n5 = new Node(5);
			n1.left = n2;
			n1.right = n3;
			n2.right = n4;
			n3.right = n5;

			return new BasicBinaryTree(n1);
		}
		else if ( n == 2 ) {
			Node n1 = new Node(10);
			Node n2 = new Node(4);
			Node n3 = new Node(20);
			Node n4 = new Node(1);
			Node n5 = new Node(8);
			Node n6 = new Node(30);
			Node n7 = new Node(5);
			Node n8 = new Node(25);
			Node n9 = new Node(40);
			n1.left = n2;
			n1.right = n3;
			n2.left = n4;
			n2.right = n5;
			n3.right = n6;
			n5.left = n7;
			n6.left = n8;
			n6.right = n9;

			return new BasicBinaryTree(n1);
		}
		else {  //if (n == 3) {
			Node n1 = new Node(10);
			Node n2 = new Node(4);
			Node n3 = new Node(20);
			Node n4 = new Node(1);
			Node n5 = new Node(8);
			Node n6 = new Node(30);
			Node n7 = new Node(5);
			Node n8 = new Node(25);
			Node n9 = new Node(19);
			n1.left = n2;
			n1.right = n3;
			n2.left = n4;
			n4.right = n7;
			n3.left = n5;
			n3.right = n6;
			n5.left = n8;
			n5.right = n9;

			return new BasicBinaryTree(n1);
		}
	}


	// the functions below assume the binary search tree property holds

	//note put is recursive
	public void put(int key) { root = put(root, key); }


	private Node put(Node n, int key) {
		if (n == null) return new Node(key);
		if      (key < n.key) n.left  = put(n.left,  key);
		else if (key > n.key) n.right = put(n.right, key);
		return n;
	}

	// note contains is recursive
	public boolean contains(int key) { return contains(root, key); }

	private boolean contains(Node n, int key) {
		if (n == null) return false;
		if      (key < n.key) return contains(n.left,  key);
		else if (key > n.key) return contains(n.right, key);
		return true;
	}

	// note size is recursive
	public int size() {
		return size(root);
	}
	private int size(Node x) {
		if (x == null) return 0;
		return 1 + size(x.left)+ size(x.right);
	}
	
	
	public Queue<Integer> keys() {
		
		Queue<Integer> q = new Queue();
		return keysHelper(root, q);
	}
	
	private Queue<Integer> keysHelper(Node x, Queue<Integer> q) {
		
		if (x == null) return q;
		q.enqueue(x.key);
		keysHelper(x.left, q);
		keysHelper(x.right, q);
		
		return q;
		
	}
	
	// note height is recursive
	public int height() {
		
		return -1;  // for you to practice 
	}

	public boolean isEmpty() { return size() == 0;}


	//   misc  Graphviz   methods

	public void toGraphviz(String filename) {
		GraphvizBuilder gb = new GraphvizBuilder();
		toGraphviz (gb, null, root);
		gb.toFileUndirected (filename, "ordering=\"out\"");
	}
	private static void toGraphviz (GraphvizBuilder gb, Node parent, Node n) {
		if (n == null) { gb.addNullEdge (parent); return; }
		gb.addLabeledNode (n, Integer.toString (n.key));
		if (parent != null) gb.addEdge (parent, n);
		toGraphviz (gb, n, n.left);
		toGraphviz (gb, n, n.right);
	}

	public void drawTree() {
		if (root != null) {
			StdDraw.setPenColor (StdDraw.BLACK);
			StdDraw.setCanvasSize(1200,700);
			drawTree(root, .5, 1, .25, 0);
		}
	}
	private static void drawTree (Node n, double x, double y, double range, int depth) {
		int CUTOFF = 10;
		StdDraw.text (x, y, Integer.toString (n.key));
		StdDraw.setPenRadius (.007);
		if (n.left != null && depth != CUTOFF) {
			StdDraw.line (x-range, y-.08, x-.01, y-.01);
			drawTree (n.left, x-range, y-.1, range*.5, depth+1);
		}
		if (n.right != null && depth != CUTOFF) {
			StdDraw.line (x+range, y-.08, x+.01, y-.01);
			drawTree (n.right, x+range, y-.1, range*.5, depth+1);
		}
	}
}
