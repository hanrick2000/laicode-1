package lai_online;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Class08_String2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// test();
		// test2();
		// test6();
//		test7();
//		test9();
//		test9_1();
//		test9_2();
//		test10();
//		test10_1();
//		test11_1();
//		test11();
		test12();
	}

	public static void test() {
		String input = "abcdef";
		int n = 2;
		String output = rightShift(input, n);
		System.out.println(output);
	}

	/*
	 * task4 right shift by n characters Right shift a given string by n
	 * characters.
	 */
	public static String rightShift(String input, int n) {
		// Write your solution here.
		if (input == null || input.length() == 0)
			return input;
		char[] arr = input.toCharArray();
		int len = input.length();
		// len - 1 - n + 1 = len - n
		if (n >= len) {
			return rightShift(input, n % len);
		}

		reverse(arr, 0, len - n - 1);
		reverse(arr, len - n, len - 1);
		reverse(arr, 0, len - 1);
		return new String(arr);
	}

	public static void reverse(char[] arr, int i, int j) {
		while (i <= j) {
			swap(arr, i++, j--);
		}
	}

	public static void swap(char[] arr, int i, int j) {
		char temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	/*
	 * task5 Reverse Words In A Sentence I Reverse the words in a sentence.
	 * Assumptions Words are separated by single space There are no heading or
	 * tailing white spaces Examples “I love Google” → “Google love I” Corner
	 * Cases If the given string is null, we do not need to do anything.
	 */
	public static void test5() {
		String input = "an apple";
		String output = task5_reverseWords(input);
		System.out.println(output);
	}

	public static String task5_reverseWords(String input) {
		// write your solution here
		if (input == null || input.length() == 0) {
			return input;
		}
		char[] arr = input.toCharArray();

		int s = 0, f = 0;

		// reverse each word
		while (f <= arr.length) {

			if (f == arr.length || arr[f] == ' ') {
				reverse(arr, s, f - 1);
				f++;
				s = f;
			} else {
				f++;
			}
		}

		System.out.println(new String(arr));
		// reverse the whole scentence.
		reverse(arr, 0, arr.length - 1);

		return new String(arr);
	}

	/*
	 * task6 Longest Substring Without Repeating Characters Given a string, find
	 * the length of the longest substring without repeating characters. For
	 * example, the longest substring without repeating letters for "abcabcbb"
	 * is "abc", which the length is 3. For "bbbbb" the longest substring is
	 * "b", with the length of 1.
	 */

	public static void test6() {
		String s = "abcabcbb";
		int maxLen = task6_lengthOfLongestSubstring(s);
		System.out.println("maxLen = " + maxLen);
	}

	public static int task6_lengthOfLongestSubstring(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}

		int slow = 0, fast = 0;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		int maxLen = 0;
		int start = 0, end = 0;
		while (fast < s.length()) {
			char cur = s.charAt(fast);
			if (!map.containsKey(cur)) {
				map.put(cur, 1);
			} else {
				map.put(cur, map.get(cur) + 1);
			}

			while (map.get(cur) > 1 && slow <= fast) {
				char slow_char = s.charAt(slow);
				map.put(slow_char, map.get(slow_char) - 1);
				slow++;
			}
			if (maxLen < fast - slow + 1) {
				maxLen = fast - slow + 1;
				start = slow;
				end = fast;
			}
			fast++;
		}

		System.out.println("maxLen = " + maxLen);
		System.out.println(s.substring(start, end + 1));
		return maxLen;
	}

	public static String lengthOfLongestSubstring2(String s) {
		Set<Character> set = new HashSet<Character>();
		String result = "";
		int left = 0, right = 0;
		while (right < s.length()) {
			if (set.contains(s.charAt(right))) {
				set.remove(s.charAt(left++));
			} else {
				set.add(s.charAt(right++));
				if (right - left > result.length()) {
					result = s.substring(left, right);
				}
			}
		}
		return result;
	}

	/*
	 * task7 Find All Anagrams Of Short String In A Long String Find all
	 * anagrams of String s in String l, return all the starting indices.
	 */

	public static void test7() {
		String s = "abc";
		String l = "abzadefcba";
		List<Integer> result = task7_allAnagrams(s, l);
		System.out.println(result);
	}

	public static List<Integer> task7_allAnagrams(String s, String l) {
		List<Integer> result = new ArrayList<Integer>();
		if (s == null || l == null || s.length() == 0 || l.length() == 0) {
			return result;
		}
		if (s.length() > l.length()) {
			return result;
		}
		Map<Character, Integer> map = countMap(s);
		mapIterator(map);

		int match = 0;
		for (int i = 0; i < l.length(); i++) {
			char temp = l.charAt(i);
			Integer count = map.get(temp);
			if (count != null) {
				map.put(temp, count - 1);
				// ???
				if (count == 1) {
					match++;
				}
			}

			System.out.println("1: i = " + i + "  count = " + count);
			System.out.println("1: match = " + match);

			if (i >= s.length()) {
				temp = l.charAt(i - s.length());
				count = map.get(temp);
				if (count != null) {
					map.put(temp, count + 1);
					if (count == 0) {
						match--;
					}
				}
			}

			System.out.println("2: i = " + i + "  count = " + count);
			System.out.println("2: match = " + match);

			if (match == map.size()) {
				result.add(i - s.length() + 1);
			}

			mapIterator(map);
		}
		return result;
	}

	public static Map<Character, Integer> countMap(String s) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (char ch : s.toCharArray()) {
			Integer count = map.get(ch);
			if (count == null) {
				map.put(ch, 1);
			} else {
				map.put(ch, count + 1);
			}
		}
		return map;
	}

	public static void mapIterator(Map<Character, Integer> map) {
		System.out.println("-----------------------------------");
		for (Map.Entry<Character, Integer> entry : map.entrySet()) {
			System.out.print(entry.getKey() + "  ");
			System.out.println(entry.getValue());
		}
		System.out.println("***********************************");
	}

	public static void test2() {
		String input = "appledogapple";
		String s = "apple";
		String t = "abcdefg";

		String result = replaceII(input, s, t);
		System.out.println(result);
	}

	
	/*
	 * task8
	 * String Replace
	 * Given an original string input, and two strings S and T, replace all occurrences of S in input with T.
	 * 
	 * 
	 * Assumptions
	 * input, S and T are not null, S is not empty string
	 * Examples
	 * input = "appledogapple", S = "apple", T = "cat", input becomes "catdogcat"
	 * input = "dodododo", S = "dod", T = "a", input becomes "aoao"
	 * 
	 * 
	 * two situations:
	 * 1) replace a longer string with a shorter string
	 *    step1: find every single occurrence of s1 in the original string, and just replace s1 with s2, until 
	 *    we are done
	 * 2) replace a shorter string with a longer string
	 *    deal with extra spaces. 
	 *    step1: counter how many teimes s1 show up in original string. e.g, twice 
	 *    step2: 2 * (s2.size - s1.size)
	 *    
	 *    s: all letter to the right-hand of s are processed area. 
	 *    f: current index to move
	 * 
	 */
	public static String replaceII(String input, String s, String t) {
		if (s.length() >= t.length()) {
			return replaceWithShorter(input, s, t);
		} else {
			return replaceWithLonger(input, s, t);
		}
	}

	public static String replaceWithShorter(String input, String s, String t) {
		char[] array = input.toCharArray();
		// record the current end
		int nextStart = 0;
		for (int i = 0; i < input.length();) {
			if (i <= input.length() - s.length() && equalSubArray(input, i, s)) {
				// copy the shorter target to this place
				copyFromLeft(array, nextStart, t);
				// update i
				i += s.length();
				// update end
				nextStart += t.length();
			} else {
				array[nextStart++] = input.charAt(i++);
			}
		}
		return new String(array, 0, nextStart);
	}

	public static String replaceWithLonger(String input, String s, String t) {
		// first find all substring's ending index, which matches t
		ArrayList<Integer> matches = new ArrayList<Integer>();
		for (int i = 0; i <= input.length() - s.length();) {
			if (equalSubArray(input, i, s)) {
				matches.add(i + s.length() - 1);
				i += s.length();
			} else {
				i++;
			}
		}

		int newLength = input.length() + matches.size()
				* (t.length() - s.length());

		// replace from the last
		int lastIndex = matches.size() - 1;
		int end = newLength - 1;

		System.out.println("lastIndex = " + lastIndex);
		System.out.println("end = " + end);
		System.out.println("input.length = " + input.length());
		System.out.println(matches);
		char[] result = new char[newLength];
		for (int i = input.length() - 1; i >= 0;) {
			if (lastIndex >= 0 && i == matches.get(lastIndex)) {
				copyFromRight(result, end, t);
				lastIndex--;
				// update i
				i -= s.length();
				// update end
				end -= t.length();
			} else {
				result[end--] = input.charAt(i--);
			}
		}

		return new String(result);

	}

	public static boolean equalSubArray(String input, int index, String s) {
		for (int i = 0; i < s.length(); i++) {
			if (input.charAt(index + i) != s.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	public static void copyFromLeft(char[] array, int index, String t) {
		for (int i = 0; i < t.length(); i++) {
			array[index++] = t.charAt(i);
		}
	}

	public static void copyFromRight(char[] array, int index, String t) {
		for (int i = t.length() - 1; i >= 0; i--) {
			array[index--] = t.charAt(i);
		}
	}

	
	/*
	 * task9 
	 * Compress String
	 * Given a string, replace adjacent, 
	 * repeated characters with the character followed by the number of repeated occurrences. 
	 * If the character does not has any adjacent, repeated occurrences, it is not changed.
	 * 
	 * Assumptions
	 * The string is not null
	 * The characters used in the original string are guaranteed to be ‘a’ - ‘z’
	 * There are no adjacent repeated characters with length > 9
	 * Examples
	 * “abbcccdeee” → “ab2c3de3”
	 */

	public static void test9() {
		String input = "abbcccdeee";
		String output = task9_compress(input);
		System.out.println(input);
		System.out.println(output);
	}

	public static String task9_compress(String input) {
		// write your solution here
		// the last index of compressed string. not include
		if (input == null || input.length() == 0) {
			return input;
		}
		int end = 0;
		int counter = 1; // the counter
		int candidateIndex = 0;
		char[] array = input.toCharArray();
		for (int i = 1; i < array.length; i++) {
			if (array[i] == array[candidateIndex]) {
				counter++;
//				System.out.println("counter = " + counter);
			} else {

				// array[i] != array[candidatesIndex]
				// now, i points the next candidate's index
				// put the candidates into array[end]
				array[end++] = array[candidateIndex];

				if (counter > 1) {
					// put the counter into the candidates
					String temp = Integer.toString(counter);

					for (int j = 0; j < temp.length(); j++) {
						array[end++] = temp.charAt(j);
					}

				}
				candidateIndex = i;
				counter = 1;

			}
		}

		// the last type of char in original string.
		array[end++] = array[candidateIndex];
		if (counter > 1) {
			String temp = Integer.toString(counter);
			for (int j = 0; j < temp.length(); j++) {
				array[end++] = temp.charAt(j);
			}
		}
		return new String(array, 0, end);
	}

	public static void printArray(char[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	
	/*
	 * task9.1
	 * compress 
	 * e.g 
	 * aaabbbcccce => a3b3c4e1
	 * 
	 * step1: deal with the cases where the adjacent occurrence of the letters >= 2, which 
	 *        will make the original string shorter
	 * step2: deal with the adjacent occurrence of the letter == 1
	 */
	
	public static void test9_1() {
		String input = "aaabbcccceddf";
		String output = task9_1_compress(input);
		System.out.println(output);
	}
	
	public static String task9_1_compress(String input) {
		if (input == null || input.length() == 0) {
			return input;
		}
		
		int counter = 1;
		char[] array = input.toCharArray();
		int length = array.length;
	
		//step1:  aaabbbccdee => a3b3c2de2	
		int step1Len = compressStep1(array, length);

		// step2
		// replace single char a with a1
		// a3b3c2de2 => a3b3c2d1e2
		// step2.1
		// get the new length 
		// find how many single char, the char not followed with a number.
		counter = 0;
		for(int i = 0; i < step1Len; i ++) {
			if (isSingleChar(array, i, step1Len)) {
				counter ++;
			}
		}
		int step2Len = step1Len + counter;
		
		
		//step2.2 from right hand to left hand
		char[] result = new char[step2Len];
		int end = step2Len - 1;
		for(int i = step1Len - 1; i >= 0; i --) {
			if (isSingleChar(array, i, step1Len)) {
				result[end --] = '1';
				result[end --] = array[i];
			} else {
				result[end --] = array[i];
			}
		}
		
		String rev =  new String(result, 0, step2Len);
//		System.out.println(rev);
		return rev;
	
	}
	
	public static boolean isSingleChar(char[] array, int index, int arrLength) {
		if (index >= arrLength) {
			return false;
		}
		if (array[index] >= 'a' && array[index] <= 'z') {
			if (index == arrLength - 1) {
				return true;
			} else {
				// if the next char is still 'a' -'z'
				if (array[index + 1] >= 'a' && array[index] <= 'z') {
					return true;
				}
			}
		}
		return false;
	}
	
	// aaabccdeef => a3bc2de2f, return the newLength, this is replace a longer string with shorter
	// assume: array is not null and array.length != 0
	// input: array, len is the length of array
	public static int compressStep1(char[] array, int length) {
		int end = 0;
		int candidateIndex = 0;
		int counter = 1;
		for(int i = 1; i < length; i ++) {
			if (array[i] == array[candidateIndex]) {
				counter ++;
			} else {
				// copy the candidate into array[end]
				array[end ++ ] = array[candidateIndex];
				if (counter > 1) {
					String temp = Integer.toString(counter);
					for(int j = 0; j < temp.length();j ++) {
						array[end ++] = temp.charAt(j);
					}
				}
				// next candidate
				candidateIndex = i;
				// set counter = 1
				counter = 1;
			}
		}	
		// last type of char
		array[end ++] = array[candidateIndex];
		if (counter > 1) {
			String temp = Integer.toString(counter);
			for(int j = 0; j < temp.length();j ++) {
				array[end ++] = temp.charAt(j);
			}
		}
		return end;
	}
	
	
	
	/*
	 * task10
	 * Decompress String I
	 * Given a string in compressed form, decompress it to the original string. 
	 * The adjacent repeated characters in the original string are compressed to 
	 * have the character followed by the number of repeated occurrences. 
	 * If the character does not have any adjacent repeated occurrences, it is not compressed.
	 */
	public static void test10() {
		char i = '1';
		
		int ith = i - '0';
		System.out.println(ith);
		String s = "0"; 
		Integer i1 = Integer.parseInt(s);
		System.out.println(i1);
	}
	public static void test10_1() {
		String input = "a5b";  // abbbb cddee
		
		int counter = getNewLength(input);
		System.out.println(counter);
		
		String output = task10_decompress(input);
	}
	
	public static String task10_decompress(String input) {
		if (input == null || input.length() == 0) {
			return input;
		}
		
		int newLen = getNewLength(input);
		char[] array = new char[newLen];
		
		int end = newLen - 1;
		for(int i = input.length() - 1; i >= 0; i --) {
			if (isChar(input.charAt(i))) {
				if (i == input.length() - 1) {
					// the last element
					array[end --] = input.charAt(i);
				} else {
					int j = i + 1;
					while( j < input.length() && isNum(input.charAt(j))) {
						j ++;
					}
					String counter_str = input.substring(i + 1, j);
					if (counter_str.length() > 0) {
						int counter = Integer.parseInt(counter_str);
						while(counter > 0) {
							array[end --] = input.charAt(i);
							counter --;
						}
					} else {
						array[end --] = input.charAt(i);
					}
				}
			}
		}
		
		String rev = new String(array);
		return rev;
	}
	
	public static int getNewLength(String input) {
		int counter = 0;
		int s = 0, f = 1;
		
//		int curCounter = 1;
		while(f < input.length()) {
			if (isChar(input.charAt(f))) {
				counter ++;
			} else {
				while(f < input.length() && isNum(input.charAt(f))) {
					f ++;
				}
				String counter_str = input.substring(s + 1, f);
				System.out.println("counter_str = " + counter_str);
				counter += Integer.parseInt(counter_str);
			}
			s = f;
			f ++;	
		}
		
		// check the last char is number or char, if char, ++, if number, do nothing
		if (isChar(input.charAt(input.length() - 1))) {
			counter ++;
		}
		System.out.println("s = " + s);
		System.out.println("f = " + f);
		return counter;
	}
	
	public static boolean isChar(char ch) {
		return ch >= 'a' && ch <= 'z';
	}
	
	public static boolean isNum(char ch) {
		return ch >= '0' && ch <= '9';
	}
	
	
	
	/*
	 * task11
	 * Decompress String II
	 * 
	 * Given a string in compressed form, decompress it to the original string. 
	 * The adjacent repeated characters in the original string 
	 * are compressed to have the character followed by the number of repeated occurrences.
	 * 
	 * Assumptions
	 * The string is not null
	 * The characters used in the original string are guaranteed to be ‘a’ - ‘z’
	 * There are no adjacent repeated characters with length > 9
	 * Examples
	 * “a1c0b2c4” → “abbcccc”
	 */
	
	
	public static void test11() {
		String input = "a0b1c4d1";  // bccccd
		int counter = task11_getNewLength(input);
		System.out.println("counter = " + counter);
		
		String output = task11_decompress(input);
		System.out.println("output = ");
		System.out.println(output);
	}
	
	public static String task11_decompress(String input) {
		if (input == null || input.length() == 0) {
			return input;
		}
		int newLen = task11_getNewLength(input);
		char[] array = new char[newLen];
		
		int end = newLen - 1;
		System.out.println("end = " + end);
		System.out.println("input = ");
		System.out.println(input);
		
		// !!! pay attention here. 
		for(int i = input.length() - 1; i >= 0; i --) {
//			System.out.println("1: curChar = " + input.charAt(i));
			if (isChar(input.charAt(i))) {
				int j = i + 1;
				while(j < input.length() && isNum(input.charAt(j))) {
					j ++;
				}
				String temp = input.substring(i + 1, j);
				System.out.println("temp = " + temp);
				int counter = Integer.parseInt(temp);
				if (counter > 0) {
					while(counter > 0) {
						array[end --] = input.charAt(i);
						counter --;
					}
				} 
			}
		}
		
		String rev = new String(array);
		System.out.println(rev);
		return rev;
	}
	
	
	public static void test11_1() {
		String input = "a1c0b2c4";  // abbcccc
		int counter = task11_getNewLength(input);
		System.out.println("counter = " + counter);
	}
	public static int task11_getNewLength(String input) {
		int counter = 0;
		int s = 0, f = 1;
		
		while(f < input.length()) {
			if (isNum(input.charAt(f))) {
				while(f < input.length() && isNum(input.charAt(f))) {
					f ++;
				}
				String temp = input.substring(s + 1, f);
				Integer cur_counter = Integer.parseInt(temp);
				counter += cur_counter;
			}
			s = f;
			f ++;
		}
	
		return counter;
	}
	
	
	/*
	 * task12
	 * Encode space
	 * In URL encoding, whenever we see an space " ", we need to replace it with "20%". 
	 * Provide a method that performs this encoding for a given string.
	 * 
	 * Examples
	 * "google/q?flo wer market" → "google/q?flo20%wer20%market"
	 */
	public static void test12() {
		String input = "google/q?flo wer market";
		String output = task12_encodeSpace(input);
		
		System.out.println(output);
	}
	
	public static String task12_encodeSpace(String input) {
		if (input == null || input.length() == 0) {
			return input;
		}
		
		// get new length
		int counter = 0;
		for(int i = 0; i < input.length(); i ++) {
			if (input.charAt(i) ==' ') {
				counter ++;
			}
		}
		
		int newLen = input.length() + 2 * counter;
		
		char[] array = new char[newLen];
		
		// from right hand to left hand. replace ' ' with "20%"
		int end = newLen - 1;
		String target = "20%";
		for(int i = input.length() - 1; i >= 0; i --) {
			char curChar = input.charAt(i);
			if (curChar == ' ') {
				for(int j = target.length() - 1; j >= 0; j --) {
					array[end --] = target.charAt(j);
				}
			} else {
				array[end --] = curChar;
			}
		}
		
		return new String(array);
	}
	
	
	
	
	
	
	
	
	
	

}
