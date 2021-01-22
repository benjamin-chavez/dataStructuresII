package homework;  // Version 1.0

import algs13.Queue;
import stdlib.StdOut;
//Benjamin Chavez
/**
 * Complete the 5 methods marked ToDo
 * You must not change the declaration of any method.
 */

/**
 *  The LLST class implements methods of the Ordered Symbol table API using
 *  an *unordered* linked-list of generic key-value pairs.  
 *  The methods:  put, get, and delete are already implemented
 */

public class LLST<Key extends Comparable<Key>, Value extends Comparable<Value>> {
    private Node first;      // the linked list of key-value pairs

    // a helper linked list data type
    private class Node {
        private Key key;
        private Value val;
        private Node next;

        public Node(Key key, Value val, Node next)  {
            this.key  = key;
            this.val  = val;
            this.next = next;
        }
    }

    /**
     * Initializes an empty symbol table.
     */
    public LLST() {
    	first = null;
    }
    
    //zoom session notes: delete second to last node
    public void delete2tl() {
    	Node tmp = first;
    	
    	if ( first == null || first.next == null) return;
    	
    	// two nodes in list
    	if ( first.next.next == null) {
    		first= first.next;
    		return;
    	}
    		
    	
    	for ( tmp = first; tmp.next.next.next !=null; tmp = tmp.next) 
    	{
    		// for loop is simply advancing pointer
    	}
    	tmp.next = tmp.next.next;
    }
    
    /**
     * Returns the value associated with the given key in this symbol table.
     */
    public Value get(Key key) {
        if (key == null) throw new NullPointerException("argument to get() is null"); 
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key))
                return x.val;
        }
        return null;
    }

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old 
     * value with the new value if the symbol table already contains the specified key.
     * Deletes the specified key (and its associated value) from this symbol table
     * if the specified value is null.
     */
    public void put(Key key, Value val) {
        if (key == null) throw new NullPointerException("first argument to put() is null"); 
        if (val == null) {
            delete(key);
            return;
        }

        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
    }
    
    // Zoom Session 2: 1/19/20
//    public int consecpairs() {
//    	if ( first == null || first.next == null) return 0;
//    	int count = 0;
//    	
//    	for ( Node tmp = first; tmp != null; tmp = tmp.next) {
//    		if (tmp.key.equals(tmp.next.key)count ++; )
//    	}
//    	
//    	return -1;
//    }
    
//    public Key[] makeMyDay() { 
//    	
//    	@SuppressWarnings("unchecked")
//		Key[] X = (Key[])new Comparable[ this.size()];
//    	
//    	int count = 0;
//    	for ( Node tmp = first; tmp != null; tmp = tmp.next) {
//    		
//    		X[ count ] = tmp.key;
//    		count++;
//    	}
//    	
//    	return X;
//    }
//    
    
    
    

    /**
     * Removes the specified key and its associated value from this symbol table     
     * (if the key is in this symbol table).    
     */
    public void delete(Key key) {
        if (key == null) throw new NullPointerException("argument to delete() is null"); 
        first = delete(first, key);
    }

    // delete key in sub-list beginning at Node x
    // return: the sub-list with the key removed
    // warning: function call stack too large if table is large
    private Node delete(Node x, Key key) {
        if (x == null) return null;
        if (key.equals(x.key)) {
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }

    /**
     * size returns the number of key-value pairs in the symbol table.
     * it returns 0 if the symbol table is empty.
     */
   
    public int size () {
    	int count = 0;
    	if (first == null)
    		return count;
//    	int count = 0;
    	
    	for (Node x = first; x != null ; x = x.next) {
    		count += 1;	
    	}
    	return count;
    }
    /**
     * secondSmallestKey returns the second smallest key in the symbol table.
     * it returns null if the symbol table is empty or if it has only one key.
     * See if you can write it with only one loop
     */
    public Key secondSmallestKey () {
    	
    	Key smallest = first.key;
    	Key  secondSmallest = first.key;
    	int res1;
    	int res2;
    	
    	for (Node x = first; x != null; x = x.next) {
    		res1 = smallest.compareTo(x.key);
    		res2 = secondSmallest.compareTo(x.key);
    		if (res1 > 0) {
    			secondSmallest = smallest;
    			smallest = x.key;
    		} else if (res1 < 0 && smallest.equals(secondSmallest)) {
    			secondSmallest = x.key;
    		} else if (res2 > 0 ) {
    			secondSmallest = x.key;
    		}
    	}
    	return secondSmallest; // ToDo 2    fix this
    }


    /**
     * rank returns the number of keys in this symbol table that are less than the parameter key.
     * your implementation should be recursive. You will want to create a helper function
     */
    public int rank (Key key) {
    	int count = 0;
        return rank(key, first, count);
    }
    
    public int rank (Key key, Node x, int count) {
    	if (x == null) 
    		return count;
    	if ( key.compareTo(x.key) > 0 ) 
    		count += 1;
    	return rank(key, x.next, count);
    }

    /**
     * ceiling returns the smallest key in the symbol table that is greater than or equal to the given key.
     * it returns null if there is no such key.
     */
    public Key ceiling (Key key) {
    	// Set ceiling Key object
    	// Iterate through Linked List
    	// If the test parameter is equal to current key, return the current key
    	// If current key is less than the test parameter, do nothing
    	// If current key is greater than the test parameter
    	//		- && the ceiling key is null, then set the current key as the ceiling key
    	//		- && the current key is less than the ceiling key, then set the current key as the ceiling key
    	// Return the ceiling key
    	if(first == null) return null;
    	Key keyCeiling = first.key;
    	for (Node x = first; x != null; x = x.next) {
    		if ( x.key.compareTo(key) == 0 )
    			return x.key;
    		if ( x.key.compareTo(key) > 0 && x.key.compareTo(keyCeiling) < 0) {
    			keyCeiling = x.key;
    		}
    	}
//    	return null; // Todo 4  fix this
    	
    	return keyCeiling;
    }

    /**
     * A level.  
     * 
     * inverse returns the inverse of this symbol table.
     * if the symbol table contains duplicate values, you can use any of the keys for the inverse values
     */
    public LLST<Value, Key> inverse () {
    	LLST<Integer,String> invertedList = new LLST<Integer,String>();
    	for ( Node x = first; x != null; x = x.next) {
    		
    		invertedList.put(Integer.parseInt(String.valueOf(x.val)), String.valueOf(x.key));
    	}
    	return (LLST<Value, Key>) invertedList;
    }
    
    public Iterable<Key>  keys() {
    	Queue<Key> theKeys = new Queue<Key>();
    	for ( Node temp = first; temp != null; temp=temp.next) {
    		theKeys.enqueue(temp.key);
    	}
    	return theKeys;
    }
}