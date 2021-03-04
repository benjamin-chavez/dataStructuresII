package LectureIterable;

import java.util.Iterator;

import stdlib.StdOut;

public class myArrayList {

	private int[] data;
	private int N; // how full is the array
	
	public myArrayList(int capacity) {
	// Constructor: Initialize the private data
		
		data = new int[capacity];
		N = 0;
	}
	
	public void add(int val) {
		
		// if ( N >= data.length )		// resize
		data[N] = val;
		N++;
	}
	
	public int get(int i) {
		// would need data checking
		return data[i];
	}
	
	public Iterator<Integer> iterator() {
		return new myArrayListIterator();
	}
	
	
	//define the iterator for the above Iterator function	
	private class myArrayListIterator implements Iterator<Integer> {
		private int cur = -1;
		
		public boolean hasNext() {
			if ( cur > N) return false;
			return true;
		}
		
		public Integer next() {
			cur++;
			return data[cur];
		}
		
	}
	

	
	
	
	public static void main(String[] args) {
		StdOut.println("hello");
		
		myArrayList al1 = new myArrayList(10);
		al1.add(13);
		al1.add(29);
		al1.add(22);
		al1.add(24);
		al1.add(21);
//		StdOut.println(al1.get(1));
		
		Iterator<Integer> mit = al1.iterator();
		while ( mit.hasNext()) {
			StdOut.println(mit.next());
		}
	}

}
