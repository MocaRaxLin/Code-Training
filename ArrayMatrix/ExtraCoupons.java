package ArrayMatrix;



import java.util.HashMap;
import java.util.Map;

import Util.Parser;

public class ExtraCoupons {

	public static void main(String[] args) {
		ExtraCoupons sol = new ExtraCoupons();
		Parser parser = new Parser();
		String t = "[5,3,4,2,3,4,5,7]\n"
				+ "[3,6,1,9]\n"
				+ "[1,2,3,1,2,2]\n"
				+ "[1]\n"
				+ "[1,2,3,4,1,1,2,1]\n"
				+ "[5,2,3,1,6,3,7,5]\n"
				+ "[1,2,3,4,2,1,4]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] coupons = parser.parseArray(s[i]);
			int ans = sol.minCoins(coupons);
			System.out.println(ans);
		}
	}

	public int minCoins(int[] coupons) {
		if(coupons.length < 2) return -1;
		int ret = Integer.MAX_VALUE;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i = 0; i < coupons.length; i++) {
			if(map.containsKey(coupons[i])) {
				int l = map.get(coupons[i]);
				ret = Math.min(ret, i - l + 1);
			}
			map.put(coupons[i], i);
		}
		return ret == Integer.MAX_VALUE? -1: ret;
	}

}
