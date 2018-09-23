import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

import java.util.Map.Entry;

public class TestCode {

	public static void main(String[] args) {
		String[] t = new String[] {"wbrrbrtbw","vwesd","z","a","ab","b","bcdawr"};
		Arrays.sort(t);
		// Arrays.copyOfRange(A, 0, size); // copy A from index 0 to size-1.
		// System.out.println(Arrays.toString(t));
		
		Map<Integer, Integer> map = new HashMap<>();
		for(Map.Entry<Integer, Integer> pairs : map.entrySet()) {
			pairs.getKey();
			pairs.getValue();
		}
		for(Integer key: map.keySet()) { }
		for(Integer value: map.values()) {}
		
		
		map.size();
		//int value = map.remove(key);
		Set<Entry<Integer, Integer>> mapSet = map.entrySet();
		Iterator<Entry<Integer, Integer>> mapIte = mapSet.iterator();
		while(mapIte.hasNext()) System.out.println("key: "+mapIte.next().getKey());
		
		LinkedList<Integer> listL = new LinkedList<>();
		for(int i = 0; i < 10; i++) listL.add(i);
		listL.toArray(); // good way to transform list to array BUT ONLY for "String"
		new ArrayList<Integer>(listL); // copy a list
		listL.add(0, 5); // insert
		listL.set(0, 4); // set(index, element)
		listL.remove(0); // int for index, Object for object
		// Collections.sort(list, c);
		// listL.sort(); // remember to add comparator
		// System.out.println(Arrays.toString(listL.toArray()));
		
		ArrayList<Integer> listA = new ArrayList<>();
		Iterator<Integer> i = listL.iterator();
		
		Set<Integer> set = new HashSet<>();
		set.size();
		//set.remove((Object) 7); // for object
		Iterator<Integer> setIte = set.iterator();
		
		Queue<Integer> queue = new LinkedList<>();
		// queue.iterator()
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {return a[0] - b[0]; }
		});
		// pq.remove(3);
		// pq.peek();
		
		Comparator<int[]> cmp = new Comparator<int[]>() {
			public int compare(int[] a, int[] b){
            	return a[1] == b[1]? b[0] - a[0]: a[1] - b[1];
            }
		};
		int[][] intint = new int[][] {
			{2,7}, {1,7}, {5,1}, {5,7}, {2,6}, {4,1}
		};
		//for(int i = 0; i < intint.length; i++) System.out.println(Arrays.toString(intint[i]));
		Arrays.sort(intint, new Comparator<int[]>() {
			@Override
			public int compare(int[] i, int[] j) { return i[0] - j[0]; }
		});
		//Arrays.sort(intint, (i, j) -> (i[0] == j[0] ? j[1] - i[1]: i[0] - j[0]));
		//for(int i = 0; i < intint.length; i++) System.out.println(Arrays.toString(intint[i]));
		
		int[] array = new int[] {3,5,3,4,6,8,9,12,0,5,9,8,1,7,6,4};
		
		TreeSet<Integer> rbt = new TreeSet<Integer>();
		TreeMap<Integer, Integer> rbtMap = new TreeMap<>();
		Entry<Integer, Integer> entry = rbtMap.ceilingEntry(0);
		
		StringBuilder sb = new StringBuilder();
		sb.insert(0, "Head");
		StringBuilder sb2 = new StringBuilder();
		sb.append(sb2.toString());
		sb.deleteCharAt(sb.length()-1); //remove the last
		char sbHead = sb.charAt(0); // get first char
		sb.deleteCharAt(0); //remove the head
		sb = new StringBuilder(); // clear 
		sb.setLength(0); // clear
		
		sb.insert(0, "abcdefg");
		sb.length();
		char[] dst = new char[3]; // get chars in sb
		sb.getChars(2, 5, dst, 0); // from, to, dst, startAt
		String dstString = new String(dst); // char[] to string
		// System.out.println(dstString);
		
		int power = 1; // 0000 0001
		power <<= 3; // 0000 1000 -> power = 2^3 
		power >>= 2; // power = power / 4
		
		Stack<Integer> stack = new Stack<Integer>();
		Iterator<Integer> iterator = stack.iterator();
		
		Random r = new Random();
		r.nextInt(2);
		
		double dFloor = Math.floor(-0.55);
		
		
		Map<Integer, Set<Integer>> setMap = new HashMap<>();
		Set<Integer> setInMap = new HashSet<>();
		setInMap.add(12);
		setInMap.add(34);
		setMap.put(123, setInMap);
		setMap.get(123).add(56);
		setMap.get(123).add(78);
		Iterator<Integer> ite = setMap.get(123).iterator();
		// while(ite.hasNext()) System.out.println(ite.next());
		
		String helloworld = "Hi! hello  world!";
		String[] sss = helloworld.split("\\s+");
		
		
		String commaS = "a,,b,"; // split will ignore the last empty string
		//System.out.println(Arrays.toString(commaS.split(",")));
		
		
		String a = "A";
		String b = "a";
		// a.toLowerCase();
		// System.out.println(a.compareTo(b)); -32
		// a.compareTo(b) == -1, if a is front and b is rear in dictionary order(lexicographically)
		
		char c = '1' - '0';
		char d = 'a' - '0';
		// Character.isLetter(c);
//		System.out.println((int) c);
//		System.out.println((int) d);
		
		// char array of size 256 = char map :)
		// including 128 ASCII + 128 extended ASCII
		char[] cMap = new char[256];
		char[] hlwd = "helloworld".toCharArray(); 
		new String(hlwd); // char[] to String
		char[] emptyCa = new char[3];
		// System.out.println(emptyCa[0] == 0);
		
		// bit operation
		int bitA = 5;
		// System.out.println((bitA&1) == 1);
		
		
		//Priority Queue
		PriorityQueue<Integer> pq1 = new PriorityQueue<Integer>();
		pq1.contains(new Integer(0));
		
		// Deque (double end queue)
		Deque<String> deq = new LinkedList<String>();
		deq.iterator();
		// deq.pollFirst();
		// deq.offerLast();
		
		
		
	}

}
