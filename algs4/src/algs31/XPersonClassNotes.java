package algs31;
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac Person.java
 *  Execution:    java Person
 *
 *  Implementing equals() in a client-defined type.
 *
 *************************************************************************/

public final class XPersonClassNotes implements Comparable<XPersonClassNotes> {
	private final String name;
	private final long info;

	public XPersonClassNotes(String name, long info) {
		this.name = name;
		this.info = info;
	}
	
	public int compareTo(XPersonClassNotes that ) {
		if ( this.name.compareTo(that.name) < 0)
			return -1;
		else if (this.name.compareTo(that.name) > 0)
			return 1;
		
		if ( this.info < that.info) 
			return -1;
		else if (this.info > that.info) 
			return 1;
		return 0;
	}
	
	// how you're supposed to implement equals
	public boolean equals(Object y) {
		if (y == this) return true;
		if (y == null) return false;
		if (y.getClass() != this.getClass()) return false;
		XPersonClassNotes that = (XPersonClassNotes) y;
		return (this.name.equals(that.name)) && (this.info == that.info);
	}

	public String toString() {
		return name + " " + info;
	}

	public static void main(String[] args) {
		XPersonClassNotes a = new XPersonClassNotes("Alice", 1234);
//		XPersonClassNotes b = new XPersonClassNotes("Alice", 1234);
		XPersonClassNotes b = new XPersonClassNotes("Alice", 8341);
		XPersonClassNotes c = new XPersonClassNotes("Bob",   1234);
		XPersonClassNotes d = new XPersonClassNotes("Alice", 4321);
		StdOut.println("a = " + a);
		StdOut.println("b = " + b);
		StdOut.println("c = " + c);
		StdOut.println("d = " + d);
		StdOut.println("a == a: " + a.equals(a));
		StdOut.println("a == b: " + a.equals(b));
		StdOut.println("a == c: " + a.equals(c));
		StdOut.println("a == d: " + a.equals(d));
		
//		 does a come before b alphabetically?
		if ( a.compareTo(b) < 0) {
			StdOut.println(a + " comes before " + b);
		}
		StdOut.println("a compareTo b: " + a.compareTo(b));
		StdOut.println("a compareTo c: " + a.compareTo(c));
		StdOut.println("b compareTo d: " + b.compareTo(d));
	}



}
