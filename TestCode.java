import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

public class TestCode {

	public static void main(String[] args) {
//		String[] t = new String[] {"wbrrbrtbw","vwesd","z","a","ab","b","bcdawr"};
//		Arrays.sort(t);
//		System.out.println(Arrays.toString(t));
//		
		Map<Integer, Integer> map = new HashMap<>();
		for(Map.Entry<Integer, Integer> pairs : map.entrySet()) {
			pairs.getKey();
			pairs.getValue();
		}
		for(Integer key: map.keySet()) { }
		for(Integer value: map.values()) {}
		
		map.size();
		Set<Entry<Integer, Integer>> mapSet = map.entrySet();
		Iterator<Entry<Integer, Integer>> mapIte = mapSet.iterator();
		while(mapIte.hasNext()) System.out.println("key: "+mapIte.next().getKey());
		
		LinkedList<Integer> listL = new LinkedList<>();
		ArrayList<Integer> listA = new ArrayList<>();
		Iterator<Integer> i = listL.iterator();
		
		List<Integer> list = new LinkedList<>();
		
		Set<Integer> set = new HashSet<>();
		set.size();
		//set.remove((Object) 7); // for object
		Iterator<Integer> setIte = set.iterator();
		
		Queue<Integer> queue = new LinkedList<>();
		
		
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
		sb = new StringBuilder(); // clear 
		sb.setLength(0); // clear
		
		int power = 1;
		power <<= 3;
		power >>= 2;
		
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
	}

}