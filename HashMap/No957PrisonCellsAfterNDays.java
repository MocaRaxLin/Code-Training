package HashMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import Util.Parser;

public class No957PrisonCellsAfterNDays {

	public static void main(String[] args) {
		No957PrisonCellsAfterNDays sol = new No957PrisonCellsAfterNDays();
		Parser parser = new Parser();
		String t = "[0,1,0,1,1,0,0,1]\n" + 
				"7\n" + 
				"[1,0,0,1,0,0,1,0]\n" + 
				"1000000000";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[] cells = parser.parseArray(s[i]);
			int N = Integer.parseInt(s[i+1]);
			int[] ans = sol.prisonAfterNDays(cells, N);
			System.out.println(Arrays.toString(ans));
		}
	}

	public int[] prisonAfterNDays(int[] cells, int N) {
        Set<Integer> set = new HashSet<Integer>();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int[] next = new int[8];
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= 6; j++){
                if(cells[j-1] == cells[j+1]) next[j] = 1;
                else next[j] = 0;
            }
            //System.out.println(Arrays.toString(cells));
            // System.out.println(Arrays.toString(next));
            int code = toInt(next);
            // System.out.println(code);
            if(set.contains(code)) break;
            else{
                set.add(code);
                map.put(map.size()+1, code);
            }
            cells = Arrays.copyOfRange(next, 0, next.length);
        }
        
        N = N % set.size();
        if(N == 0) N = set.size();
        // System.out.println("loop: "+ set.size());
        int[] decode = toCells(map.get(N));
        
        return decode;
    }
    
    public int[] toCells(int code){
        int[] ret = new int[8];
        for(int i = 0; i < 8; i++){
            ret[i] = code % 2;
            code = code/2;
        }
        return ret;
    }
    
    public int toInt(int[] bits){
        int ret = 0;
        for(int i = 0; i < 8; i++){
            if(bits[i] == 1) ret += (1 << i);
        }
        return ret;
    }
}
