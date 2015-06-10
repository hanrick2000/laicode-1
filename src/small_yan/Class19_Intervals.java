package small_yan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Class19_Intervals {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test();
//		test1_2();
		test2();
	}
	
	public static class Interval{
		int start;
		int end;
		public Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "[ " +start + " , " + end + " ]";
		}
	}
	
	public static void test() {
		List<Interval> list = new ArrayList<Class19_Intervals.Interval>();
		list.add(new Interval(1, 2));
		list.add(new Interval(0, 3));
		list.add(new Interval(2, 5));
		list.add(new Interval(6, 7));
		
		List<Interval> result = task1_merge(list);
		System.out.println(result);
		
		// test task2
		int length = task1_1_intervals_covered_length(list);
		System.out.println("length = " + length);
	}
	
	/*
	 * task1
	 * 1 sort by start
	 * 2 maintain last interval
	 * 3 check intersect
	 */
	public static Comparator<Interval> myComp = new Comparator<Class19_Intervals.Interval>() {
		@Override
		public int compare(Interval o1, Interval o2) {
			// TODO Auto-generated method stub
			if (o1.start == o2.start) {
				return 0;
			}
			return o1.start < o2.start ? -1 : 1;
		}
	};
	public static List<Interval> task1_merge(List<Interval> list) {
		
		
		Collections.sort(list, myComp);
		ArrayList<Interval> result = new ArrayList<Class19_Intervals.Interval>();
		Interval last = list.get(0);
		for(int i = 1; i < list.size();i ++) {
			Interval cur = list.get(i);
			if (cur.start <= last.end) {//intersect
				last.end = Math.max(last.end, cur.end);
			} else {
				result.add(last);
				last = cur;
			}
		}
		result.add(last);
		return result;
	} 
	
	
	/*
	 * task1.1 Intervals Covered Length
	 */
	public static int task1_1_intervals_covered_length(List<Interval> list) {
		Collections.sort(list, myComp);
		int length = 0;
		Interval last = list.get(0);
		int start = last.start;
		int end = last.end;
		for(int i = 1; i < list.size(); i ++) {
			Interval cur = list.get(i);
			if (cur.start <= last.end) {
				end = Math.max(cur.end, last.end);
			} else {
				length += end - start;
				start = cur.start;
				end = cur.end;
			}
		}
		length += end - start;
		
		return length;
	}
	
	/*
	 * task1.2
	 * determine if a target is covered by a list of intervals(no intersect), sorted by the start
	 * [0,2][4,6][7,10]
	 * is 1 covered by the list of intervals?
	 * is 3 covered by the list of intervals? 
	 * 
	 * Binary search.--> largest smaller or equals to the point O(log n)
	 * --> the only candidate can cover the point
	 */
	public static void test1_2() {
		List<Interval> list = new ArrayList<Class19_Intervals.Interval>();
		list.add(new Interval(0, 2));
		list.add(new Interval(4, 12));
		list.add(new Interval(8, 10));
		int target = 11;
//		System.out.println(task1_2_covered_by_intervals(list, target));
		System.out.println(task1_2_1_covered_by_intervals(list, target));
	}
	
	public static boolean task1_2_covered_by_intervals(List<Interval> list, int target) {
		Collections.sort(list,myComp);
		int start = 0; 
		int end = list.size() - 1;
		while(start + 1 < end) {
			int mid = start + (end - start)/2;
			if (list.get(mid).start == target) {
				return true;
			} else if (list.get(mid).start < target) {
				start = mid;
			} else {
				end = mid;
			}
		}
		if (list.get(end).start <= target) {
			// check whether list.get(end).end >= target
			if (list.get(end).end >= target) {
				return true;
			}
		} else if (list.get(start).start <= target) {
			if (list.get(start).end >= target) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * task1.2.1
	 * 1 element is convered by intervals ? 
	 * intervals could be with intersects
	 * [0,2]  [4,12]  [8,10]
	 * is 11 covered by the list of intervals
	 * 
	 * 1 merge intervals-> list of intervals without intersect -> check covered during the merge process
	 * 2 tree-->interval tree, segment tree O(log n)
	 * 
	 */
	
	public static boolean task1_2_1_covered_by_intervals(List<Interval> list, int target) {
		Collections.sort(list, myComp);
		Interval last = list.get(0);
		for(int i = 1; i < list.size(); i ++) {
			Interval cur = list.get(i);
			if (cur.start <= last.end) {
				last.end = Math.max(cur.end, last.end);
			} else {
				// check whether in last
				if (last.start <= target && target <= last.end) {
					return true;
				}
				last = cur;
			}
		}
		if (last.start <= target && target <= last.end) {
			return true;
		}
		return false;
	}
	
	// using interval tree, segment tree O(log n)
	public static boolean task1_2_2_covered_by_intervals2(List<Interval> list, int target) {
		return false;
	}
	
	/*
	 * task1.3
	 * determine if an interval is covered by a list of intervals
	 * 1 merge intervals --> list of intervals without intersect -> check covered during merge process 
	 *   O(n)
	 * 2 tree-> segment tree. O(log n)
	 */
	
	/*
	 * task2
	 * insert intervals
	 * [0,2][3,5][6,7] + [1,4] --> [0,5][6,7]
	 * 1 list of intervals: without intersect --> insert [1,4], 
	 *   find the largest smaller interval.start than target interval[1,4]
	 *   e.g 
	 * 2 from that position, merge
	 * 
	 */
	
	public static void test2() {
		ArrayList<Interval> list = new ArrayList<Class19_Intervals.Interval>();
		list.add(new Interval(0, 2));
		list.add(new Interval(3, 5));
		list.add(new Interval(6, 7));
		
		System.out.println(list);
		
		Interval interval = new Interval(9, 10);
		
		ArrayList<Interval> result = task2_insert_interval(list, interval);
		System.out.println(result);
		
	}
	
	public static ArrayList<Interval> task2_insert_interval(ArrayList<Interval> list, Interval interval) {
		// sort the list
		Collections.sort(list, myComp);
		//do binary search
		int start = 0, end = list.size() - 1;
		int position = -1;
		while(start + 1 < end) {
			int mid = start + (end - start)/2;
			if (list.get(mid).start == interval.start) {
				mid = end;
			} else if (list.get(mid).start < interval.start) {
				start = mid;
			} else {
				end = mid;
			}
		}
		
		if (list.get(end).start <= interval.start) {
			position = end;
		} else if (list.get(start).start <= interval.start) {
			position = start;
		} else {
			position = -1;
		}
		
		// merge
		ArrayList<Interval> result = new ArrayList<Interval>();
		if (position == -1) {
			Interval last = interval;
			for(int i = 0; i < list.size(); i ++) {
				Interval cur = list.get(i);
				if (cur.start <= last.end) { //intersect
					last.end = Math.max(cur.end, last.end);
				} else { // cur.start > last.end
					// add the last into result;
					result.add(last);
					last = cur;
				}
			}
			result.add(last);
		} else {
			// first add all interval before position into result
			for(int i = 0; i< position; i ++) {
				result.add(list.get(i));
			}
			System.out.println(result);
			Interval last = list.get(position);
			
			
			for(int i = position; i < list.size(); i ++) {
				Interval cur = null;
				if (i == position) {
					 cur = interval;
				} else {
					cur = list.get(i);
				}
				if (cur.start <= last.end) { //intersect
					last.end = Math.max(cur.end, last.end);
				} else { // cur.start > last.end
					// add the last into result;
					result.add(last);
					last = cur;
				}
			}
			result.add(last);
		}
		
		return result;
	}
	
	/*
	 * task2.1 merge two interval list
	 * [0,2][3,5][6,7]
	 * [1,4][6,9]
	 * 
	 * merge to one list, --> sorted by start ---> merge
	 */
	
	/*
	 * task3 interval subtraction
	 * 
	 */
	
	
	
	
	
	
}
