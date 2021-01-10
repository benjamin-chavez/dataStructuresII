package algs31;
import stdlib.*;
/* ***********************************************************************
 *  Compilation:  javac Person.java
 *  Execution:    java Person
 *
 *  Implementing equals() in a client-defined type.
 *
 *************************************************************************/

public final class ClassNotesXPerson implements Comparable<ClassNotesXPerson> {
	private final String name;
	private final long info;

	public ClassNotesXPerson(String name, long info) {
		this.name = name;
		this.info = info;
	}
	
	public int compareTo(ClassNotesXPerson that ) {
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
		ClassNotesXPerson that = (ClassNotesXPerson) y;
		return (this.name.equals(that.name)) && (this.info == that.info);
	}

	public String toString() {
		return name + " " + info;
	}

	public static void main(String[] args) {
		ClassNotesXPerson a = new ClassNotesXPerson("Alice", 1234);
//		XPersonClassNotes b = new XPersonClassNotes("Alice", 1234);
		ClassNotesXPerson b = new ClassNotesXPerson("Alice", 8341);
		ClassNotesXPerson c = new ClassNotesXPerson("Bob",   1234);
		ClassNotesXPerson d = new ClassNotesXPerson("Alice", 4321);
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
