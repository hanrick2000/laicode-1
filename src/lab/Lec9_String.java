package lab;

public class Lec9_String {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
		test2();
	}
	
	
	
	
	public static void test1() {
		String s1 = "abc";
		String s2 = "abc";
		System.out.println(s1 == s2); // true
		/*
		 * There is no need for maintaining several copies of String objects with the same literal, 
		 * since String objects are immutable
		 */
		
		/* Usually compiler are JVM will optimize the number of String objects created,
		 * it will maintain an internal area in HEAP, for the same String literal, it will only has
		 * one String object associated. 
		 */
		String s3 = new String("abc");
		System.out.println(s1 == s3); // false
		/*
		 * The String objects created with "new" will not use intern pool;
		 */
		System.out.println("--------------------------------------------------------");
		
		String sa = "a";
		String sb = "b";
		String sab = "a" + "b"; // compile time concat, "ab"
		System.out.println(sab == "a" + "b"); // true
		System.out.println(sab == sa + "b"); // false, sa.concat("b")
		System.out.println(sab == sa + sb);  // false, sa.concat(sb)
		String sa_internal = sa.intern();
		System.out.println(sa_internal == sa);
		
		/*
		 * Strings computed by constant expressions (§15.28) 
		 * 		are computed at compile time and then treated as if they were literals.
		 * Strings computed by concatenation at run time are newly created and therefore distinct.
		 * 
		 * sa + "b" is computed by concatenation at run time, so it's new
		 * "a" + "b" is computed by constant expression, in compile time, so the are the same with "ab"
		 * 
		 * sa_internal = sa.inter();
		 * sa.intern()方法，是返回字符串在池中的引用，并不会改变sa这个变量的引用，
		 * 就是s3还是指向堆中的那个"a"，并没有因调用了intern()方法而改变，实际上也不可能改变
		 * 
		 * 
		 */
	}
	
	public static void test2() {
		String s1 = "abc";
		String s2 = s1;
		// this is another string object, previous string is not changed
		s1 = "def";
		
		System.out.println(s1);
		System.out.println(s2);
		
	}

}
