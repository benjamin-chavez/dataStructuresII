// Exercise 3.1.5 (Solution published at http://algs4.cs.princeton.edu/)
package algs31;
import stdlib.*;
import algs13.Queue;
/* ***********************************************************************
 *  Compilation:  javac SequentialSearchST.java
 *  Execution:    java SequentialSearchST
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/31elementary/tinyST.txt
 *
 *  Symbol table implementation with sequential search in an
 *  unordered linked list of key-value pairs.
 *
 *  % more tinyST.txt
 *  S E A R C H E X A M P L E
 *
 *  % java SequentialSearchST < tiny.txt
 *  L 11
 *  P 10
 *  M 9
 *  X 7
 *  H 5
 *  C 4
 *  R 3
 *  A 8
 *  E 12
 *  S 0
 *
 *************************************************************************/

public class ClassNotesSequentialSearchST<K, V> {
	private int N;           // number of key-value pairs
	private Node<K,V> first;      // the linked list of key-value pairs

	// a helper linked list data type
	private static class Node<K,V> {
		public final K key;
		public V val;
		public Node<K,V> next;

		public Node(K key, V val, Node<K,V> next)  {
			this.key  = key;
			this.val  = val;
			this.next = next;
		}
	}

	// return number of key-value pairs
	public int size() { return N; }

	// is the symbol table empty?
	public boolean isEmpty() { return size() == 0; }

	// does this symbol table contain the given key?
	public boolean contains(K key) {
		return get(key) != null;
	}

	// return the value associated with the key, or null if the key is not present

	
	// write get here
	
	public V get(K key) {
		// Search for key, return associated value
		
//		 My Implementation
		 for (Node<K,V> x = first; x !=null; x = x.next)
		 {
		 	if (key.equals(x.key))
		 		return x.val;
		 }
		 return null;
		 
		// Professor Implementation 
//		for (Node temp = first; temp != null; temp = temp.next) {
//			if ( temp.key.equals(key) )
//				return temp.val;
//		}
//		return null;
	}

	// add a key-value pair, replacing old key-value pair if key is already present
	public void put(K key, V val) {
		if (val == null) { delete(key); return; }
		for (Node<K,V> x = first; x != null; x = x.next)
			if (key.equals(x.key)) { x.val = val; return; }
		first = new Node<>(key, val, first);
		N++;
	}

	// remove key-value pair with given key (if it's in the table)
	// Professor Implementation 
	public void delete(K key) {
		// Need to write a helper function to return the corrected list
		// because there is no way for this interface to specify
		// a smaller list
//		first = deleteHelper(first, key);
		
	}
	
//	public Node deleteHelper( Node x, K key) {
//		// two base cases:
//		if (x == null ) return null;
//		if ( x.key.equals(key))
//			return x.next;
//		
//		// general case
//		x.next = deleteHelper(x.next, key);
//		
//		return x;
//	}

	// delete key in linked list beginning at Node<K,V> x
	// warning: function call stack too large if table is large
	private Node<K,V> delete(Node<K,V> x, K key) {
		if (x == null) return null;
		if (key.equals(x.key)) { N--; return x.next; }
		x.next = delete(x.next, key);
		return x;
	}


	// return all keys as an Iterable
	public Iterable<K> keys()  {
		Queue<K> queue = new Queue<>();
		for (Node<K,V> x = first; x != null; x = x.next)
			queue.enqueue(x.key);
		return queue;
	}




	/* *********************************************************************
	 * Test client
	 **********************************************************************/
	public static void main(String[] args) {
		StdIn.fromString ("S E A R C H E X A M P L E");
		ClassNotesSequentialSearchST<String, Integer> st = new ClassNotesSequentialSearchST<>();
		for (int i = 0; !StdIn.isEmpty(); i++) {
			String key = StdIn.readString();
			st.put(key, i);
		}
		for (String s : st.keys())
			StdOut.println(s + " " + st.get(s));
	}
}
