package algs14;
import stdlib.*;
public class XCountingRecursion {
	// f creates a string of length N, counting the total size of all strings created
	public static String f (long N) {
		if (N == 0) {
			return "";
		} else {
			String result = "*" + f(N - 1);
			numOps = numOps + result.length();
			return result;
		}
	}
	private static long numOps;
	public static void main (String[] args) {
		long MIN = 50L;
		long MAX = 4000L; // If too big, you may get a StackOverflowException
		Stopwatch sw = new Stopwatch ();
		numOps = 0;
		f(MIN);
		double prevCount = numOps;
		double prevTime = sw.elapsedTime ();
		for (long N = MIN*2; N <= MAX; N=N*2) {
			sw = new Stopwatch ();
			numOps = 0;
			f(N);
			long count = numOps; 
			double time = sw.elapsedTime ();
			//StdOut.format ("Elapsed count f(%5d): %10d: %10f [%10f : %10f]\n", N, count, count / prevCount, time, time/prevTime);
			StdOut.format ("Elapsed count f(%5d): %10d: %10f\n", N, count, count / prevCount);
			prevCount = count;
			prevTime = time;
		}
	}
}