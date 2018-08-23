package HashMap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import Util.Parser;

public class No888FairCandySwap {

	public static void main(String[] args) {
		No888FairCandySwap sol = new No888FairCandySwap();
		Parser parser = new Parser();
		String t = "[1,1]\n" + 
				"[2,2]\n" + 
				"[1,2]\n" + 
				"[2,3]\n" + 
				"[2]\n" + 
				"[1,3]\n" + 
				"[1,2,5]\n" + 
				"[2,4]\n" + 
				"[8,73,2,86,32]\n" + 
				"[56,5,67,100,31]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[] A = parser.parseArray(s[i]);
			int[] B = parser.parseArray(s[i+1]);
			int[] ans = sol.fairCandySwap(A, B);
			System.out.println(Arrays.toString(ans));
		}
	}

    public int[] fairCandySwap(int[] A, int[] B) {
        // --> O(n)
    	
        Set<Integer> set = new HashSet<Integer>();
        int sumA = 0;
        for(int e: A){
            set.add(e);
            sumA += e;
        }
        int sumB = 0;
        for(int e: B) sumB += e;
        
        int diff = (sumA - sumB)/2;
        // diff = a - b => a = b + diff
        
        for(int b: B){
            if(set.contains(b+diff)) return new int[]{b+diff, b};
        }
        
        return null;
    }
}
