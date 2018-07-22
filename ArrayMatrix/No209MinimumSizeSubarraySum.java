package ArrayMatrix;

import Util.Parser;

public class No209MinimumSizeSubarraySum {

	public static void main(String[] args) {
		No209MinimumSizeSubarraySum sol = new No209MinimumSizeSubarraySum();
		Parser parser = new Parser();
		String t = "7\n" + 
				"[2,3,1,2,4,3]\n" + 
				"3\n" + 
				"[]\n" + 
				"1\n" + 
				"[1,2]\n" + 
				"3\n" + 
				"[1,1]\n" + 
				"9\n" + 
				"[1,1,1,1]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int T = Integer.parseInt(s[i]);
			int[] A = parser.parseArray(s[i+1]);
			int ans = sol.minSubArrayLen(T, A);
			System.out.println(ans);
		}
	}

    public int minSubArrayLen(int T, int[] A) {
        if(A.length == 0) return 0;
        int i = 0, j = 0; //j means A[j] is the next element
        int window = 0;
        int ret = Integer.MAX_VALUE;
        while(j <= A.length){
            if(window < T){
                if(j == A.length) break;
                window += A[j];
                j++;
            }else{
                ret = Math.min(ret, j-i);
                window -= A[i];
                i++;
            }
        }
        return ret == Integer.MAX_VALUE ? 0 : ret;
    }
}
