package ArrayMatrix;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class No881RandomFlipMatrix {

	// no test case for this problem
	// please practice on leetcode directly.
	// https://leetcode.com/problems/random-flip-matrix/description/
	
    Map<Integer, Integer> map;
    Random random;
    int n, m;
    int bound;
    public No881RandomFlipMatrix(int n_rows, int n_cols) {
        map = new HashMap<Integer, Integer>();
        random = new Random();
        n = n_rows;
        m = n_cols;
        bound = n*m;
    }
    
    public int[] flip() {
        int r = random.nextInt(bound--);
        int x = map.getOrDefault(r, r);
        if(map.containsKey(bound)) map.put(r, map.get(bound));
        else map.put(r, bound);
        return new int[]{ x/m, x%m };
    }
    
    public void reset() {
        map.clear();
        bound = n*m;
    }
    
    // Thanks to:
    // https://leetcode.com/problems/random-flip-matrix/discuss/154053/Java-AC-Solution-call-Least-times-of-Random.nextInt()-function
    // https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
    
    // So Clever!
    
    // [0 1 2 3 4 5|], bound = 6
    // nextInt(6)
    // r = 3, bound = 5, x = 3
    // !containsKey(bound) -> put{r:bound}
    // map = {3:5, }
    // -> return 3
    //
    // [0 1 2 5 4|3], bound = 5
    // nextInt(5)
    // r = 0, bound = 4, x = 0
    // !containsKey(bound)
    // map = {3:5, 0:4, }
    // -> return 0
    //
    // [4 1 2 5|0 3], bound = 4
    // nextInt(4)
    // r = 3, bound = 3, x = 5 (3 in map)
    // containsKey(bound) -> put {r:get(bound)} -> {3:5}
    // map = {3:5, 0:4, }
    // -> return 5
    //
    // [4 1 2|5 0 3], bound = 3
    // nextInt(3)
    // r = 1, bound = 2, x = 1
    // !containsKey(bound)
    // map = {3:5, 0:4, 1:2, }
    // -> return 1
    //
    // [4 2|1 5 0 3], bound = 2
    // nextInt(2)
    // r = 0, bound = 1, x = 4 (0 in map)
    // containsKey(bound) -> put {r:get(bound)} -> {0:2}
    // map = {3:5, 0:2, 1:2, }
    // -> return 4
    //
    // [2|4 1 5 0 3], bound = 1
    // nextInt(1)
    // r = 0, bound = 0, x = 2 (0 in map)
    // containsKey(bound) -> put {r:get(bound)} -> {0:2}
    // map = {3:3, 0:2, 1:2, }
    // -> return 2
    //
    // [|2 4 1 5 0 3], bound = 0
    //
    // Done!
    
}
