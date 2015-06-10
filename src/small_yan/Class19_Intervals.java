package small_yan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;


public class Class19_Intervals {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test();
//		test1_2();
//		test2();
//		test3();
		test4_1();
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
	 * [0,5) - [1,2) = {[0,1), [2,5)}
	 * [0,5) - [-1,6) = {}
	 * [0,5) - [3,7) = [0,3)
	 * [0,5) - [-1,2) =[2,5)
	 * [0,5) - [6,9) = [0,5)
	 * [0,5) - [-3,-1) = [0,5) 
	 * 
	 * 分清楚几种情况就好，画图
	 * 
	 */
	public static void test3() {
		Interval i1 = new Interval(0, 5);
		Interval i2 = new Interval(1, 2);
		
		List<Interval> res = task3_subtract(i1, i2);
		System.out.println(res);
	}
	public static List<Interval> task3_subtract(Interval i1, Interval i2) {
		List<Interval> res = new ArrayList<Class19_Intervals.Interval>();
		if (i1.start < i2.start) {
			res.add(new Interval(i1.start, Math.min(i2.start, i1.end)));
		}
		if (i1.end > i2.end) {
			res.add(new Interval(Math.max(i2.end, i1.start), i1.end));
		}
		return res;
	}
	
	
	/*
	 * Swipe Line Algorithm
	 * |   | | | |   | 
	 * 0 1 2 3 4 5 6 7 8
	 * ­­ separate the interval to two end points, and each endpoints is a “line”.
	 * ­­ the type of endpoints can be different and can contain different information.
	 */
	
	/*
	 * task4 number of intervals intersected
	  
	 * task4.0 all numbers of intervals intersected
	 * 
	 * task 4.1
	 * User login facebook, and each session is record as (start_timestamp, end_timestamp), track all number
	 * of users logged in
	 * [0,5)
	 * [2,4)
	 * [3,7)
	 * 
	 * 0,2,3,4,5,7
	 * 
	 * counter = 0
	 * 
	 * [0,2) ->1
	 * [2,3) ->2
	 * [3,4) ->3
	 * [4,5) ->2
	 * [5,7) ->1
	 * 
	 * intervals --> end point --> start end point / end end point
	 * start end point --> counter ++
	 * end end point --> counter --
	 */

	public static void test4_1() {
		List<Interval> sessions = new ArrayList<Class19_Intervals.Interval>();
		sessions.add(new Interval(0, 5));
		sessions.add(new Interval(2, 4));
		sessions.add(new Interval(3, 7));
		sessions.add(new Interval(6, 9));
		
		int max = task4_1maxLoginUser_1(sessions);
		System.out.println(max);
		System.out.println("---------------------");
		int max2 = task4_1_maxLoginUser_2(sessions);
		System.out.println("max2 = " + max2);
	}
	
	// this we can only find the max number of log user
	public static int task4_1maxLoginUser_1(List<Interval> sessions) {
		ArrayList<Integer> start = new ArrayList<Integer>();
		ArrayList<Integer> end = new ArrayList<Integer>();
		
		for(int i = 0; i < sessions.size(); i ++) {
			Interval cur = sessions.get(i);
			start.add(cur.start);
			end.add(cur.end);
		}
		Collections.sort(start);
		Collections.sort(end);
		
		int i = 0, j = 0;
		int counter = 0;
		int globalMax = Integer.MIN_VALUE;
		while(i < start.size() && j < end.size()) {
			if (start.get(i) < end.get(j)) {
				counter ++;
				i ++;
				if (globalMax < counter) {
					globalMax = counter;
				}
			} else {
				counter --;
				j ++;
			}
			System.out.println("count = " + counter);
		}
		
		return globalMax;
		
	}
	
	public static final int START = -1;
	public static final int END = -2;
	
	public static int task4_1_maxLoginUser_2(List<Interval> sessions) {
		ArrayList<Item> list = new ArrayList<Class19_Intervals.Item>();
		for(int i = 0; i < sessions.size(); i++) {
			Interval cur = sessions.get(i);
			Item item1 = new Item(cur.start, START);
			Item item2 = new Item(cur.end, END);
			list.add(item1);
			list.add(item2);
		}
		
		Collections.sort(list);
		for(Item i : list) {
			System.out.println(i.val + " " + i.type + " ");
		}
		System.out.println();
		int couter = 0;
		int max = -1;
		for(int i = 0; i < list.size() - 1; i ++) {
			Item cur = list.get(i);
//			Item next = list.get(i + 1);
//			if (cur.type == START && next.type == START) {
//				couter ++;
//			}
//			if (cur.type == START && next.type == END) {
//				couter ++;
//			}
//			if (cur.type == END && next.type == END) {
//				couter --;
//			}
//			if (cur.type == END && next.type == START) {
//				couter --;
//			}
			if (cur.type == START) {
				couter ++;
			}
			if (cur.type == END) {
				couter --;
			}
			if (max < couter) {
				max = couter; 
			}
			System.out.println(couter);
		}
		return max;
	}
	
	public static class Interval2{
		Item item1;
		Item item2;
		public Interval2(Item i1, Item i2) {
			this.item1 = i1;
			this.item2 = i2;
		}
	}
	
	public static class Item implements Comparable<Item>{
		int val;
		int type;
		public Item(int v, int t) {
			this.val = v;
			this.type = t;
		}
		@Override
		public int compareTo(Item o) {
			// TODO Auto-generated method stub
			if (this.val == o.val) {
				return 0;
			}
			return this.val < o.val ? -1 : 1;
		}
	}
	
	
	
	/*
	 * task4.3
	 * 给出一堆人,然后你知道他们每个人的meeting schedule,返回所有人前三个共同的meeting schedule,
	 * 已经知道的方法:public ​List<Timeslot>​getSchedule(String person){},
	 * 自己 要完成的方法:public ArrayList<Timeslot> getFirstThreeCommonSlot(List<String>people){}
	 */
	
	/*
	 * task4.4
	 * 4.3 a lot of people, each person has a birth year and dead year, what is the year with most people alive?
	 * <start_time, end_time, 1>
	 */
	
	/*
	 * task4.5
	 * 
	 * 4.4 <start_time, end_time, value>
	 * 比如有一组数据: 1, 3, 100
	 * 2, 4, 20
	 * 5, 6, 300
	 * 。。。
	 * 这些数据时间点可能有重合。在时间段2~3之间,value的和是100+200 = 300. 找出这 组数据中最高的value和。
	 * int globalValue;
	 * start_time : globalValue +=value 
	 * end_time: globalValue ­=value
	 */
	
	
	/*
	 * task5
	 * skyline （求轮廓，求面积）
	 * 
	 * Given n houses on the ground with each house represented by a rectangle. 
	 * The i­th rectangleisrepresentedas​[start_i,end_i,height_i],​
	 * where 0<=i<n.The rectangles may overlap with each other. 
	 * How can we calculate the total area that these rectangles cover.
	 * Example: input = {<1,3,1>, <2,4,2>}, output = 5.
	 * 
	 */
	public static class Building{
		public int start;
		public int end;
		public int height;
		public Building(int s, int e, int h) {
			this.start = s;
			this.end = e;
			this.height = h;
		}
	}
	/*
	 * method1 
	 * 1 find all end points and sort O(n log n)
	 * 2 traverse all the buildings, update the height of the end points. O(n*n)
	 */
	
	/*
	 * method2
	 * 1) find all the end point and sort
	 * 2) max heap maintain the highest building at each point
	 * 
	 * 1). if (maxHeap is not empty and peek().end > cur.start)
	 * a. current is higher than heap.peek()
	 * b. current is not higher than heap.peek()
	 * 2). while (heap.peek() < cur.start)
	 * 3). if heap.isEmpty()
	 */
	public static class StartComparator implements Comparator<Building> {

		@Override
		public int compare(Building o1, Building o2) {
			// TODO Auto-generated method stub
			if (o1.start == o2.start) {
				return 0;
			}
			return o1.start < o2.start ? -1 : 1;
		}
	}
	
	public static class HeightComparator implements Comparator<Building> {

		@Override
		public int compare(Building o1, Building o2) {
			// TODO Auto-generated method stub
			if (o1.height == o2.height) {
				return 0;
			}
			return o1.height < o2.height? -1 : 1;
		}
	}
	
	public static int totalArea(Building[] buildings) {
		assert buildings != null;
		if (buildings.length == 0) {
			return 0;
		}
		Arrays.sort(buildings, new StartComparator());
		PriorityQueue<Building> maxHeap =  
				new PriorityQueue<Class19_Intervals.Building>(buildings.length, new HeightComparator());
		int area = 0;
		// left means the start position of current contour
		int left = Integer.MIN_VALUE;
		Building fakeLast = new Building(Integer.MAX_VALUE, Integer.MAX_VALUE, 0);
		// swipe the building one by one
		for(int i = 0; i <= buildings.length; i ++) {
			Building cur = i < buildings.length ? buildings[i] : fakeLast;
			//poll all buildings having end position <= cur.start
			while(!maxHeap.isEmpty() && maxHeap.peek().end <= cur.start) {
				Building top = maxHeap.poll();
				// update left if needed and calculate the area
				area += Math.max(0, top.end - left) * top.height;
				left = Math.max(top.end, left);
			}
			
			// if max heap is empty, meaning we can start a new contour
			if (maxHeap.isEmpty()) {
				left = cur.start;
			} else {
				if (maxHeap.peek().height < cur.height) {
					// if cur.height larger than the peek building's height
					// means the cur contour with the peek building's height can be ended 
					// and a new contour should start
					area += (cur.start - left)*maxHeap.peek().height;
					left = cur.start;
				}
			}
			maxHeap.offer(cur);
		}
		return area;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
