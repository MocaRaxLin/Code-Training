package ArrayMatrix;

import java.util.Arrays;

import Util.Parser;

public class No923ThreeSumWithMultiplicity {

	public static void main(String[] args) {
		No923ThreeSumWithMultiplicity sol = new No923ThreeSumWithMultiplicity();
		Parser p = new Parser();
		String t = "[1,1,2,2,3,3,4,4,5,5]\n" + 
				"8\n" + 
				"[1,1,2,2,2,2]\n" + 
				"5";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[] A = p.parseArray(s[i]);
			int T = Integer.parseInt(s[i+1]);
			int ans = sol.threeSumMulti(A, T);
			System.out.println(ans);
		}
		
	}

	public int threeSumMulti(int[] A, int T) {
        // --> time = O(nlogn+n^2), where n = A.length
		
		// Intuition:
		// Idea from two sum, use 2 pointer on a sorted array.
		// Since we only need the number of satisfied tuple, 
		// we can use multiplication instead of plus.
		
		// Fix first element f, and find T-f in the rest of A
		//
		// case 1: a + b = T - f
		// a, a, a, b, b, b, b -> 3*4 combinations
		//
		// case 2: c + c = T - f
		// c, c, c, c, c -> C 5 takes 2 -> 5*4/2 = 10 combinations
		
        int mod = (int) Math.pow(10, 9) + 7;
        Arrays.sort(A);
        int ret = 0;
        for(int i = 0; i < A.length; i++){
            ret += twoSumMulti(A, i, T - A[i]);
            ret %= mod;
        }
        return ret;
    }
    
    private int twoSumMulti(int[] A, int start, int T){
        int ret = 0, i = start + 1, j = A.length-1;
        while(i <= j){
            if(A[i]+A[j] < T) i++;
            else if(A[i]+A[j] > T) j--;
            else{
                if(A[i] == A[j]){
                    int al = A[i], count = 0;
                    while(i < A.length && al == A[i]){
                        count++; i++;
                    }
                    ret += count*(count-1)/2;
                }else{
                    int al = A[i], ar = A[j], l = 0, r = 0;
                    while(i < A.length && al == A[i]){
                        l++; i++;
                    }
                    while(0 <= j && A[j] == ar){
                        r++; j--;
                    }
                    ret += l*r;
                }
                
            }
        }
        return ret;
    }
}
