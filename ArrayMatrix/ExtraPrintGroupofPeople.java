package ArrayMatrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import Util.Parser;

public class ExtraPrintGroupofPeople {

	public static void main(String[] args) {
		ExtraPrintGroupofPeople sol = new ExtraPrintGroupofPeople();
		Parser parser = new Parser();
		String t = "[3,3,3,3,3,1,3]\n" + 
				"[2,1,1,2,1]\n" + 
				"[2,2,2,2]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] A = parser.parseArray(s[i]);
			String ans = sol.printGroup(A);
			System.out.println(ans);
		}
	}

	private String printGroup(int[] A) {
		int n = A.length; if(n == 0) return "";
		Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		for(int i = 0; i < n; i++) {
			if(map.containsKey(A[i])) {
				map.get(A[i]).add(i);
			}else {
				List<Integer> list = new LinkedList<Integer>();
				list.add(i);
				map.put(A[i], list);
			}
		}
		
		List<Box> boxs = new ArrayList<Box>();
		for(Map.Entry<Integer, List<Integer>> p: map.entrySet()) {
			Box b = new Box(p.getKey(), p.getValue());
			boxs.add(b);
		}
		Comparator<Box> cmp = new Comparator<Box>() {
			public int compare(Box a, Box b) { return b.size - a.size; }
		};
		Collections.sort(boxs, cmp);
		
		StringBuilder sb = new StringBuilder();
		Iterator<Box> it = boxs.iterator();
		while(it.hasNext()) {
			Box b = it.next();
			Iterator<Integer> it2 = b.ids.iterator();
			while(it2.hasNext()) {
				for(int i = 0; i < b.size; i++) {
					sb.append(it2.next() + " ");
				}
				sb.append("\n");
			}
		}
		return sb.toString();
	}
	
	

}

class Box{
	int size;
	List<Integer> ids;
	public Box(int size, List<Integer> ids) {
		this.size = size;
		this.ids = ids;
	}
}
