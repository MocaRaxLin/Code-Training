package HashMap;

import java.util.HashMap;
import java.util.Map;

import Util.Parser;

public class No904FruitIntoBaskets {

	public static void main(String[] args) {
		No904FruitIntoBaskets sol = new No904FruitIntoBaskets();
		Parser parser = new Parser();
		String t = "[0,1,2,2]\n" + 
				"[1,2,3,2,2]\n" + 
				"[3,3,3,1,2,1,1,2,3,3,4]\n" +
				"[5,2,3,1,6,3,7,5]\n" + 
				"[0]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] tree = parser.parseArray(s[i]);
			int ans = sol.totalFruit(tree);
			System.out.println(ans);
		}
	}

	public int totalFruit(int[] tree) {
		// O(n)
		
        int ret = 1;
        Map<Integer, Integer> map = new HashMap<Integer,Integer>();
        int i = 0, j = 0;
        while(j < tree.length){
            int occur = map.getOrDefault(tree[j], 0) + 1;
            map.put(tree[j], occur);
            j++;
            while(i < tree.length && map.size() == 3){
                int freq = map.get(tree[i]) - 1;
                if(freq == 0) map.remove(tree[i]);
                else map.put(tree[i], freq);
                i++;
            }
            ret = Math.max(ret, j - i);
        }
        return ret;
    }
}
