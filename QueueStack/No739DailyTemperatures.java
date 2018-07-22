package QueueStack;

import java.util.Arrays;

import Util.Parser;

public class No739DailyTemperatures {

	public static void main(String[] args) {
		No739DailyTemperatures sol = new No739DailyTemperatures();
		Parser parser = new Parser();
		String t = "[73,74,75,71,69,72,76,73]\n" + 
				"[30,30,31,40,32]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] T = parser.parseArray(s[i]);
			int[] ans = sol.dailyTemperatures(T);
			System.out.println(Arrays.toString(ans));
		}
	}

	public int[] dailyTemperatures(int[] T) {
        // Same as the next greater number
        int[] ret = new int[T.length];
        int[] stack = new int[T.length];
        int size = 0;
        for(int i = 0; i < T.length; i++){
            while(size > 0 && T[stack[size-1]] < T[i]){
                int idx = stack[--size];
                ret[idx] = i - idx;
            }
            stack[size++] = i;
        }
        return ret;
    }
}
