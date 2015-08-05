package lai_online;

import java.util.*;
import java.util.Map.Entry;
public class Class07_Hash_String1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test3();
//		test1();
		test();
	}
	
	
	/*
	 * task1
	 * Top K Frequent Words
	 * Given a composition with different kinds of words, return a list of the top K most frequent words in the composition.
	 * Assumptions
	 * the composition is not null and is not guaranteed to be sorted
	 * K >= 1 and K could be larger than the number of distinct words in the composition, 
	 * in this case, just return all the distinct words
	 * Return a list of words ordered from most frequent one to least frequent one (the list could be of size K or smaller than K)
	 * Examples
	 * Composition = ["a", "a", "b", "b", "b", "b", "c", "c", "c", "d"], top 2 frequent words are [“b”, “c”]
	 * Composition = ["a", "a", "b", "b", "b", "b", "c", "c", "c", "d"], top 4 frequent words are [“b”, “c”, "a", "d"]
	 * Composition = ["a", "a", "b", "b", "b", "b", "c", "c", "c", "d"], top 5 frequent words are [“b”, “c”, "a", "d"]
	 * 
	 * 
	 * 1 use hashMap<String, Integer>() to count the Strings
	 * 2 traverse the map and put every string into a k sized minHeap.
	 * 3 pop all elements in minHeap
	 */
	public static void test1() {
		String[] combo = {"a","c","d","a","b","b","c","d","d","a","d"};
		String[] output = topKFrequent(combo, 1);
		for(String str: output) {
			System.out.println(str);
		}
	}
	
	public static String[] topKFrequent(String[] combo, int k) {
		// Write your solution here.
		final HashMap<String, Integer> map = new HashMap<String, Integer>();
		for(String str: combo) {
			if (map.containsKey(str)) {
				map.put(str, map.get(str) + 1);
			} else {
				map.put(str, 1);
			}
		}
		
		PriorityQueue<String> minHeap = new PriorityQueue<String>(k, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				int f1 = map.get(o1);
				int f2 = map.get(o2);
				if (f1 == f2) {
					return 0;
				}
				return f1 < f2 ? -1: 1;
			}
		});
		// traverse the map and put them into the k size minHeap
		for(Map.Entry<String, Integer> entry: map.entrySet()) {
			System.out.println("--------");
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
			
			if (minHeap.size() < k) {
				minHeap.offer(entry.getKey());
			} else if (entry.getValue() > map.get(minHeap.peek())) {
				minHeap.poll();
				minHeap.offer(entry.getKey());
			}
		}
		System.out.println("minHeap.size = ");
		System.out.println(minHeap.size());
		
		String[] output = null;
		if (k > minHeap.size()) {
			output = new String[minHeap.size()];
		} else {
			output = new String[k];
		}
		
		for(int i = minHeap.size() - 1; i >=0; i --) {
			output[i] = minHeap.poll();
		}
		return output;
	}
	
	
	
	

	/*
	 * task3
	 */
	public static void test3() {
		String input = "abc";
		String output = task3_deDup(input);
		System.out.println(output);
	}

	public static String task3_deDup(String input) {
		// write your solution here
		if (input == null || input.length() <= 0) {
			return input;
		}

		char[] str = input.toCharArray();
		int s = 0, f = 1;
		while (f < str.length) {
			if (str[s] != str[f]) {
				++s;
				str[s] = str[f];
			}
			f++;
		}

		String output = new String(str, 0, s + 1);
		System.out.println(output);
		return output;
	}

	/*
	 * remove spaces
	 */

	public String removeSpaces(String input) {
		// Write your solution here.
		if (input == null || input.length() == 0) {
			return input;
		}
		// counter for words
		int count = 0;
		int s = 0, f = 0;
		char[] arr = input.toCharArray();
		while (f < arr.length) {
			// skip the leading space
			while (f < arr.length && arr[f] == ' ') {
				f++;
			}
			if (f == arr.length) {
				break;
			}
			// already have word, add one space after the word
			if (count > 0) {
				arr[s++] = ' ';
			}
			while (s < arr.length && f < arr.length && arr[f] != ' ') {
				arr[s++] = arr[f++];
			}
			count++;
		}

		return new String(arr, 0, s);

	}

	public String remove(String input, String t) {
		// Write your solution here.
		if (input == null || input.length() == 0)
			return input;
		char[] arr = input.toCharArray();
		HashSet<Character> set = new HashSet<Character>();
		int s = 0;
		for (int i = 0; i < t.length(); i++) {
			set.add(t.charAt(i));
		}
		for (int f = 0; f < arr.length; f++) {
			if (!set.contains(arr[f])) {
				arr[s++] = arr[f];
			}
		}
		return new String(arr, 0, s);
	}
	
	
	
	// missing number
	public static void test() {
		int[] array = {1};
		int miss = missing(array);
		System.out.println("miss = " + miss);
	}
	public static int missing(int[] array) {
	    // write your solution here
	    int result = array[0];
	    for(int i = 1; i < array.length; i ++) {
	      result ^= array[i];
	    }
	    for(int i = 1; i <= array.length + 1; i ++) {
	      result ^= i;
	    }
	    return result;
	  }
}
