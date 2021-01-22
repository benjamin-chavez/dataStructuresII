package examples;

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

public class BasicBinaryTreeBensCode {

	private Node root;

	//nested node class
	private static class Node {
		public final int key;
		public Node left, right;
		public Node(int key) { this.key = key; }
	}

	// constructor for the class itself
	private BasicBinaryTreeBensCode( Node x) { root = x; }

	public BasicBinaryTreeBensCode() {};
	
	public int leftmost() { 
		// precondition: tree is not empty
		
		// my solution:
		for (Node x = root; x != null; x = x.left) {
			if (x.left == null)
				return x.key;
		}
		return -1;
		
//		// professor's solution
//		Node tmp = root;
//		while ( tmp.left != null) {
//			tmp = tmp.left;
//		}
//		return tmp.key;
	}
	
	public void preOrderPrint() {
		preOrderHelper(root);
	}
	
	private void preOrderHelper(Node x) {
		if ( x != null) {
			StdOut.println(x.key);	// visit node first
			preOrderHelper(x.left);
			preOrderHelper(x.right);
		}
	}
	
	public void inOrderPrint() {
		inOrderHelper(root);
	}
	
	private void inOrderHelper(Node x) {
		if ( x != null) {
			inOrderHelper(x.left);
			StdOut.println(x.key);	// visit node second
			inOrderHelper(x.right);
		}
	}
	
	public void postOrderPrint() {
		postOrderHelper(root);
	}
	
	private void postOrderHelper(Node x) {
		if ( x != null) {
			postOrderHelper(x.left);
			postOrderHelper(x.right);
			StdOut.println(x.key);	// visit node last
		}
	}
	
	public static void main(String[] args) {

		// manually building trees
		BasicBinaryTreeBensCode tree1 = buildTree(1);
		BasicBinaryTreeBensCode tree2 = buildTree(2);
		BasicBinaryTreeBensCode tree3 = buildTree(3);

//		tree1.drawTree();
//		tree1.toGraphviz("tree1.png");
//		tree2.toGraphviz("tree2.png");
//		tree3.toGraphviz("tree3.png");
		
		tree3.preOrderPrint();
		StdOut.println("");
		tree3.inOrderPrint();
		StdOut.println("");
		tree3 .postOrderPrint();

		
		StdOut.println("the size of tree1 is " + tree1.size());
		StdOut.println("the size of tree2 is " + tree2.size());
		StdOut.println("");
		
		StdOut.println("leftMost node of tree1 is " + tree1.leftmost());
		StdOut.println("leftMost node of tree2 is " + tree2.leftmost());
		StdOut.println("leftMost node of tree3 is " + tree3.leftmost());
	
		StdOut.println("");
		
		StdOut.println("height of tree1 is " + tree1.height());
		StdOut.println("height of tree2 is " + tree2.height());
		StdOut.println("height of tree3 is " + tree3.height());

		BasicBinaryTreeBensCode bst = buildSearchTree( new int[] {6,5,7,4,8,3,9,2,1});
		StdOut.println("leftMost node of bst is " + bst.leftmost());
		StdOut.println("height of bst is " + bst.height());
		bst.toGraphviz("searchTre.png");
	}


	//   if we use *put* to build a tree then it will 
	//   satisfy the BST property
	//      --> it will be a binary search tree
	public static BasicBinaryTreeBensCode buildSearchTree( int[] data) {

		BasicBinaryTreeBensCode bst = new BasicBinaryTreeBensCode();
		for (int i=0; i < data.length; i++)
			bst.put(data[i]);
		return bst;

	}

	// build some trees manually to practice on
	public static BasicBinaryTreeBensCode buildTree(int n) {

		if ( n == 1 ) {
			Node n1 = new Node(1);
			Node n2 = new Node(2);
			Node n3 = new Node(3);
			Node n4 = new Node(4);
			Node n5 = new Node(5);
			n1.left = n2;
			n1.right = n3;
			n2.right = n4;
			n3.left = n5;

			return new BasicBinaryTreeBensCode(n1);
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

			return new BasicBinaryTreeBensCode(n1);
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

			return new BasicBinaryTreeBensCode(n1);
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
		return sizeHelper(root);
	}
	
	private int sizeHelper(Node x) {
		// base case: x is null, this is the root of an empty tree
		if ( x == null)
			return 0;
		int leftSize = sizeHelper(x.left);
		int rightSize = sizeHelper(x.right);
		return 1 + leftSize + rightSize;
		
		// return 1 + sizeHelper(x.left)+ sizeHelper(x.right); 
	}

	// note height is recursive
	public int height() {
		return heightHelper(root) - 1;
//		return -1;  // for you to practice 
	}
	
	private int heightHelper(Node x) {
		if (x == null)
			return 0;
		int leftHeight = heightHelper(x.left);
		int rightHeight = heightHelper(x.right);
		
		return Math.max(leftHeight, rightHeight) + 1;
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
