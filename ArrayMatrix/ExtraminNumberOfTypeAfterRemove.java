package ArrayMatrix;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Util.Parser;

public class ExtraminNumberOfTypeAfterRemove {

	public static void main(String[] args) {
		ExtraminNumberOfTypeAfterRemove sol = new ExtraminNumberOfTypeAfterRemove();
		Parser p = new Parser();
		String t = "[1,1,1,2,2,3]\n" + 
				"2\n" + 
				"[1,1,1,2,2,3]\n" + 
				"3\n" + 
				"[2,3,1,2,3,1,2,3,1]\n" + 
				"3\n" + 
				"[5,3,1,1,2,5,4,1,3,2,3,3,1]\n" + 
				"6\n" + 
				"[1]\n" + 
				"1\n" + 
				"[]\n" + 
				"4\n" +
				"[1]\n" +
				"2\n"+
				"[4,6,1,2,6,5,2,5,1,4,3,5,3,3]\n" + 
				"4\n" + 
				"[5,1]\n" + 
				"2\n" + 
				"[6,6,6]\n" + 
				"1\n" + 
				"[5,4,8,7]\n" + 
				"0";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[] ids = p.parseArray(s[i]);
			int m = Integer.parseInt(s[i+1]);
			int ans = sol.deleteProducts(ids, m);
			System.out.println(ans);
		}
	}

	private int deleteProducts(int[] ids, int m) {
		// --> O(n), where n = ids.length
		
		int n = ids.length; if(n == 0) return 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int e: ids) {
			int freq = map.getOrDefault(e, 0);
			map.put(e, ++freq);
		}
		
		List<Integer> list = new LinkedList<Integer>();
		for(Map.Entry<Integer,Integer> pair: map.entrySet()) {
			list.add(pair.getValue());
		}
		
		Collections.sort(list, new Comparator<Integer>() { public int compare(Integer o1, Integer o2) { return o2 - o1; } });
		
		int ret = 0, count = 0, size = n - m;
		Iterator<Integer> it = list.iterator();
		while(it.hasNext() && count < size) {
			count += it.next();
			ret++;
		}
		return ret;
	}

}
