package algs13;
import stdlib.*;
public class XFixedCapacityStackOfStrings2 {
	private static int numStacks = 0;
	public  static int numStacks() { return numStacks; } 
	private int stackId;
	private int stackId() { return stackId; }

	private String[] a; // holds the items
	private int N;      // number of items in stack
	// a[0]..a[N-1] are non null
	// a[N]..a[a.length-1] are null
	public XFixedCapacityStackOfStrings2 (int capacity) {
		this.stackId = numStacks++;
		this.a = new String[capacity];
		this.N = 0;
	}
	public int size ()        { return N; }
	public boolean isEmpty () { return (N == 0); }
	public void push (String item) {
		if (item == null) throw new IllegalArgumentException ();
		if (N >= a.length) throw new RuntimeException ("Stack full");
		a[N] = item;
		N++;
	}
	public String pop () {
		if (N <= 0) throw new RuntimeException ("Stack empty");
		N--;
		String result = a[N];
		a[N] = null;
		return result;
	}

	public static void main(String[] args) {
		//Trace.showObjectIdsRedundantly (true);
		Trace.showBuiltInObjects (true);
		Trace.drawSteps();
		//Trace.drawStepsOfMethod ("main");
		//Trace.drawStepsOfMethod ("printPop");
		Trace.run ();
		XFixedCapacityStackOfStrings2 stack1 = new XFixedCapacityStackOfStrings2 (7);
		XFixedCapacityStackOfStrings2 stack2 = new XFixedCapacityStackOfStrings2 (3);
		stack1.push ("a");
		stack2.push ("b");
		stack1.push ("c");
		stack2.push ("d");
		stack1.push ("e");
		stack2.push ("f");
		stack1.push ("g");
		printPop(stack2);
		printPop(stack1);
	}
	private static void printPop (XFixedCapacityStackOfStrings2 s) {
		int id = s.stackId();
		int num = XFixedCapacityStackOfStrings2.numStacks();
		StdOut.printf("Stack %d of %d:\n", id, num);
		while (!s.isEmpty()) {
			StdOut.println (s.pop ());
		}
	}
}
