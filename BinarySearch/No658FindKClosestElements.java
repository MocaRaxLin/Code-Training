package BinarySearch;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import Util.Parser;

public class No658FindKClosestElements {

	public static void main(String[] args) {
		No658FindKClosestElements sol = new No658FindKClosestElements();
		Parser parser = new Parser();
		String t = "[1,2,3,4,5]\n" + 
				"4\n" + 
				"3\n" + 
				"[2,4,4,4,6,7,8,8,8,9,11,15,22,22,46]\n" + 
				"6\n" + 
				"45\n" + 
				"[2,5,5,5,8]\n" + 
				"4\n" + 
				"3";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=3) {
			int[] A = parser.parseArray(s[i]);
			int k = Integer.parseInt(s[i+1]);
			int x = Integer.parseInt(s[i+2]);
			List<Integer> ans = sol.findClosestElements(A, k, x);
			System.out.println(Arrays.toString(ans.toArray()));
		}
	}

    public List<Integer> findClosestElements(int[] A, int k, int x) {
        List<Integer> ret = new LinkedList<Integer>();
        int s = 0, e = A.length;
        if(x < A[s]){
            for(int i = 0; i < k; i++) ret.add(A[i]);
            return ret;
        }
        if(A[e-1] < x){
            for(int i = e - k; i < e; i++) ret.add(A[i]);
            return ret;
        }
        
        while(e-s >1){
            int m = s + (e-s)/2;
            if(A[m] <= x) s = m;
            else e = m;
        }
        // A[e] will be the right most x if x in A
        // otherwise A[e] is the largest element smaller than x
        while(k > 0 && 0 <= s && e < A.length){
            if(x - A[s] <= A[e] - x){
                ret.add(0, A[s]);
                s--;
            }else{
                ret.add(A[e]);
                e++;
            }
            k--;
        }
        while(k > 0){
            if(0 <= s){
                ret.add(0, A[s]);
                s--;
            }else {
                ret.add(A[e]);
                e++;
            }
            k--;
        }
        return ret;
    }
}
