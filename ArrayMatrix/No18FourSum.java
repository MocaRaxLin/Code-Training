package ArrayMatrix;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import Util.Parser;
import Util.Show;

public class No18FourSum {

	public static void main(String[] args) {
		No18FourSum sol = new No18FourSum();
		Parser parser = new Parser();
		Show show = new Show();
		String t = "[1,0,-1,0,-2,2]\n" + 
				"0\n" + 
				"[0,0,0,0,0]\n" + 
				"0";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[] A = parser.parseArray(s[i]);
			int T = Integer.parseInt(s[i+1]);
			List<List<Integer>> ans = sol.fourSum(A, T);
			show.showListListInt(ans);
		}
	}

    public List<List<Integer>> fourSum(int[] A, int T) {
    	// --> O(n^3), where n = A.length
    	
    	// The idea is based on 3 Sum
    	// We fix 2 indices i, j this time
    	
    	// A[i] and A[j] check pruning unneccessary computing branch
    	// Accelarate lots of time
    	// 75ms to 28ms for 282 testcases
    	
        List<List<Integer>> ret = new LinkedList<List<Integer>>();
        Arrays.sort(A);
        for(int i = 0; i < A.length; i++){
        	
        	if(4*A[i] > T) break; // A[i] is too large
            if(i + 3 < A.length && A[i] + A[A.length-3] + A[A.length-2] + A[A.length-1] < T) continue; // A[i] is too small
            
            if(i > 0 && A[i] == A[i-1]) continue;
            for(int j = i+1; j < A.length; j++){
            	
            	if(A[i] + 3*A[j] > T) break; // A[j] is too large
                if(j + 2 < A.length && A[i] + A[j] + A[A.length-2] + A[A.length-1] < T) continue; // A[j] is too small
                
                if(j > i+1 && A[j] == A[j-1]) continue;
                int h = j + 1, k = A.length - 1;
                while(h < k){
                    if(h > j + 1 && A[h] == A[h-1]) h++;
                    else if(k < A.length - 1 && A[k] == A[k+1]) k--;
                    else if(A[i] + A[j] + A[h] + A[k] < T) h++;
                    else if(A[i] + A[j] + A[h] + A[k] > T) k--;
                    else{
                        List<Integer> l = new LinkedList<Integer>();
                        l.add(A[i]);
                        l.add(A[j]);
                        l.add(A[h]);
                        l.add(A[k]);
                        ret.add(l);
                        h++;
                        k--;
                    }
                }
            }
        }
        return ret;
    }
}
