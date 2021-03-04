package LectureIterable;

import java.util.Iterator;
import java.util.TreeSet;
import stdlib.StdOut;

public class UsingIterable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TreeSet<Integer> ts = new TreeSet();
		
		ts.add(10);
		ts.add(14);
		ts.add(4);
		ts.add(15);
		ts.add(17);
		
		
		// Short Version
		for( Integer q : ts) {
			StdOut.println(q);
		}
		
		
		//Long Version
		Iterator<Integer> mit = ts.iterator();
		
		while( mit.hasNext() ) {
			StdOut.println(mit.next());
		}
	}

}
