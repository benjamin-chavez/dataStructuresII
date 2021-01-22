/** Data Structures II
 * 
 * Benjamin Chavez
 * 
 * Get help from anyone?  No
 * 
 * Homework 1 Tester   Version 1.0
 * 
 * Instructions:  
 *      There are two java files associated with this homework.
 *      LLST.java is a java class you will use to implement a portion of the 
 *         Ordered Symbol Table API from section 3.1 of the text.
 *         See the ToDo items in that file.   
 *      
 *      This file is a driver program to test your LLST methods implementation.
 *      I have provided some testing code to allow you to test the size method.
 *      
 *      You will need to create additional functions to:
 *         test the other member functions in your LLST implementation.
 *       AND
 *         create a reasonable set of test cases for each; 
 *                
 *      call your testing functions from main
 *      You can follow the approach suggested by   sizeTest    or use your own approach.
 *      If you use your own approach, you must include enough documentation so that the
 *      grader is convinced that it is correctly testing the instance method.
 *                
 */

package homework;

import stdlib.StdIn;
import stdlib.StdOut;

public class hw1Tester {

		public static void main(String[] args) {

			// the simple test client code from the textbook pg 370
			// you may delete/comment this out if you wish
			LLST<String, Integer> st = new LLST<>();
			StdIn.fromFile("data/tinyST.txt");
			for (int i = 0; !StdIn.isEmpty(); i++)
			{
				String key = StdIn.readString();
				st.put(key, i);
			}
			for (String s : st.keys())
				StdOut.println(s + " " + st.get(s));
			System.out.println("");
			// To do:   call your testing modules
			
			allSizeTests();
			allSecondSmallestKeyTests();
			allRankTests();
			allCeilingTests();
			allInverseTests();
			
//			int answer = st.consecpairs();
		}

		/* performs all tests of the size method
		 * by calling   sizeTest   for a specific test case
		 */
		public static void allSizeTests() {
			sizeTest("",0);					// test size on an empty ST (symbol table)
			sizeTest("abcde",5);			// test size on a non-empty ST
			sizeTest("a", 1);				// test size on a non-empty ST with 1 item
			sizeTest("abcdefghijk",11);		// test size on a non-empty ST with 11 item
			System.out.println("");
			// ToDo   add at least 2 more test cases here
		    // label each test case with a comment describing what you are testing for.
			
		}
		// sample testing function.
		// param vals: data source for the keys for the symbol table
		//             each substring of length 1 is used as a key
		//             the value for each key is its position with the vals string
		// param expected:  the correct size value of the ST for the input:vals
		public static void sizeTest( String vals, int expected ) {
			
			// create and populate the table from the input string vals
			LLST<String,Integer> aList = new LLST<String,Integer>();
			for (int i=0; i < vals.length(); i++) {
				aList.put(vals.substring(i, i+1),i);
			}
			//  call the size function
			int result = aList.size();
			//report result
			if ( result == expected)  // test passes
				StdOut.format("sizeTest: Correct  String %s Answer: %d\n", vals,result);
			else
				StdOut.format("sizeTest: *Error*  String %s Expected: %d function result: %d\n", vals,expected,result);
		}
		
		//Todo: add your testing modules and functions here
		//See note about testing inverse function
		
		// Custom tests for secondSmallestKey Method:
		public static void allSecondSmallestKeyTests() {
			// TODO: write test cases
			secondSmallestKeyTest("abc", "b");		// general case: non-empty ST
			secondSmallestKeyTest("acb", "b");		// general case: non-empty ST
			secondSmallestKeyTest("bac", "b");		// general case: non-empty ST
			secondSmallestKeyTest("bca", "b");		// general case: non-empty ST
			secondSmallestKeyTest("cba", "b");		// edge case: inverted non-empty ST
			secondSmallestKeyTest("cab", "b");		// general case: non-empty ST
			secondSmallestKeyTest("cgeaq", "c");	// instruction example
			
//			secondSmallestKeyTest("", null);	// NEED TO IMPLEMENT STILL
//			secondSmallestKeyTest("a", null);	// NEED TO IMPLEMENT STILL
			System.out.println("");
		}
		
		public static <Key> void secondSmallestKeyTest( String vals, Key expected) {
			
			// create and populate the table from the input string vals
			LLST<String,Integer> aList = new LLST<String,Integer>();
			for (int i=0; i < vals.length(); i++) {
				aList.put(vals.substring(i, i+1),i);
			}
			// call the secondSmallesKey function
			String result = aList.secondSmallestKey();
			//report result
			if ( result.equals(expected)) // test passes
				StdOut.format("secondSmallestKeyTest: Correct String %s Answer: %s\n", vals,result);
			else
				StdOut.format("secondSmallestKeyTest: *Error* String %s Expected: %s function result: %s\n", vals,expected,result);
		}
		
		// Custom tests for rank Method:
		public static void allRankTests() {
			rankTest("acehlmprsx", "a", 0);		// edge case: first item in list
			rankTest("acehlmprsx", "b", 1);		// general case: course example
			rankTest("acehlmprsx", "c", 1);		// general case: course example
			rankTest("acehlmprsx", "z", 10);	// edge case: not in list, but would be last in list
			rankTest("acehlmprsx", "n", 6);		// general case: course example
			rankTest("", "n", 0);				// edge case: empty list
			System.out.println("");
			
		}
		
		public static <Key> void rankTest( String vals, String testKey, int expected) {
			
			// create and populate the table from the input string vals
			LLST<String,Integer> aList = new LLST<String,Integer>();
			for (int i=0; i < vals.length(); i++) {
				aList.put(vals.substring(i, i+1), i);
			}
			// call the rank function
			int result = aList.rank(testKey);
			// report result
			if (result == expected) 	// test passes
				StdOut.format("rankTest: Correct  String %s Answer: %d\n", vals,result);
			else
				StdOut.format("rankTest: *Error* String %s Expected: %d function result: %d\n", vals,expected,result);
		}

		// custom tests for cieling method:
		public static void allCeilingTests() {
			ceilingTest("bcde", "a", "b"  );		// general case: key not in list
			ceilingTest("abcde", "a", "a"  );		// general case: key in list
			ceilingTest("", "a", null);				// edge case: empty list
			System.out.println("");
		}
		
		public static <Key> void ceilingTest( String vals, String key, Key expected) {
			
			// create and populate the table from the input string vals
			LLST<String,Integer> aList = new LLST<String,Integer>();
			for (int i=0; i < vals.length(); i++) {
				aList.put(vals.substring(i, i+1), i);
			}
			
			// call the ceiling function
			String result = aList.ceiling(key);
			
			if (result == null)
				System.out.println("ceilingTest: Correct: null");
			else if ( result.equals(expected))	// test passes
				StdOut.format("ceilingTest: Correct String %s Answer: %s\n", vals, result);
			else
				StdOut.format("ceilingTest: *Error* String %s Expected: %s function result: %s\n", vals,expected,result);
		}

		
		public static void allInverseTests() {
			inverseTest("abcde", "01234");		// general case: non-empty list
			inverseTest("", "");				// edge case: empty list
			System.out.println("");
		}

		public static <Key, Value> void inverseTest( String vals, String expected) {
			
			// create and populate the table from the input string vals
			LLST<String,Integer> aList = new LLST<String,Integer>();
			for (int i=0; i <vals.length(); i++) {
				aList.put(vals.substring(i, i+1), i);
			}
			// call the inverse function
			LLST<Integer,String> result = aList.inverse();
				
			// iterate through the inverted list and create a string of the keys
			String resultString = "";
			for (Integer i : result.keys())
				resultString += i;
			// report result
			if ( resultString.equals(expected))
				StdOut.format("inverseTest: Correct String %s Answer: %s\n", vals, resultString);
			else
				StdOut.format("inverseTest: *Error* String %s Expected: %s function result: %s\n", vals,expected,resultString);
			
			
			
		}

}




















