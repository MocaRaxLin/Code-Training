package ArrayMatrix;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import Util.Parser;
import Util.Show;

public class No15ThreeSum {

	public static void main(String[] args) {
		No15ThreeSum sol = new No15ThreeSum();
		Parser parser = new Parser();
		Show show = new Show();
		String t = "[-1,0,1,2,-1,-4]\n" + 
				"[]\n" + 
				"[0,0,0,0]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] A = parser.parseArray(s[i]);
			List<List<Integer>> ans = sol.threeSum(A);
			show.showListListInt(ans);
		}
	}

	public List<List<Integer>> threeSum(int[] A) {
		// --> O(n^2), where n = A.length
		// 99ms for 313 testcases on LeetCode
		
		// Sort First! -> O(nlong)
		//
		// Use 2 pointers
		// Fix i, counter direction j and k
		// For each A[i] we try to find A[j] and A[k], 
		// such that R = A[i] + A[j] + A[k] = 0.
		// Later on j++, k-- to find the next match i, j, k.
		//
		// If R > 0 use smaller k (k--), 
		// else R < 0 use bigger j (j++).
		
		// Duplicate Handling:
		// if A[i] == A[i-1] do not use this i, i++.
		// if A[j] == A[j-1] do not use this j, j++.
		// if A[k] == A[k+1] do not use this k, k--.
		// go to the next index.
		
        List<List<Integer>> ret = new LinkedList<List<Integer>>();
        Arrays.sort(A);
        //System.out.println(Arrays.toString(A));
        for(int i = 0; i < A.length; i++){
            if(i > 0 && A[i] == A[i-1]) continue;
            int j = i+1, k = A.length - 1;
            while(j < k){
                if(j > i+1 && A[j] == A[j-1]) j++;
                else if(k < A.length - 1 && A[k] == A[k+1]) k--;
                else if(A[i] + A[j] + A[k] < 0) j++;
                else if(A[i] + A[j] + A[k] > 0) k--;
                else{
                    // got A[i] + A[j] + A[k] == 0
                    List<Integer> list = new LinkedList<Integer>();
                    list.add(A[i]);
                    list.add(A[j]);
                    list.add(A[k]);
                    ret.add(list);
                    j++;
                    k--;
                }
            }
        }
        return ret;
    }
	
	// ps.
    // Some people may try HashSet.
    // It is OK, but need a clear mind to handle duplicate cases.
    // Also using special data structure will comsume more time.
	// Here I provide the accepted code.
    public List<List<Integer>> threeSum0(int[] A) {
    	// --> time = O(nlogn)
    	// 430ms for 313 testcases on LeetCode.
        Arrays.sort(A);
        List<List<Integer>> ret = new LinkedList<List<Integer>>();
        for(int i = 0; i < A.length; i++){
            if(i > 0 && A[i] == A[i-1]) continue;
            HashSet<Integer> seen = new HashSet<Integer>();
            for(int j = i+1; j < A.length; j++){
                if(seen.contains(-A[i]-A[j])){
                    List<Integer> l = new LinkedList<Integer>();
                    l.add(A[i]);
                    l.add(-A[i]-A[j]);
                    l.add(A[j]);
                    ret.add(l);
                    while(j + 1 < A.length && A[j+1] == A[j]) j++;
                }
                seen.add(A[j]);
            }
        }
        return ret;
    }
}
