package ArrayMatrix;

import java.util.HashSet;
import java.util.Set;

import Util.Parser;

public class No41FirstMissingPositive {

	public static void main(String[] args) {
		No41FirstMissingPositive sol = new No41FirstMissingPositive();
		Parser parser = new Parser();
		String t = "[1,2,0]\n" + 
				"[3,4,-1,1]\n" + 
				"[7,8,9,11,12]\n" + 
				"[5,4,2,1]\n" + 
				"[4,3,2,1]\n"+
				"[1,1]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] A = parser.parseArray(s[i]);
			int ans = sol.firstMissingPositive(A);
			System.out.println(ans);
		}
	}

	public int firstMissingPositive(int[] A) {
        // Thanks to:
        // https://leetcode.com/problems/first-missing-positive/discuss/17071/My-short-c++-solution-O(1)-space-and-O(n)-time
        
        // Intuition:
        // Put A[i] on the right place
        // use swap to put A[i] to index i-1, when A[i] != A[A[i] - 1]
        // eg. 5 to index at 4
        // then run from i = 0 to n-1, if A[i] != i + 1 then we miss i+1.
        
        // Q: why not A[i] != i + 1 ? because [1,1] gives loop at index 1
		// 
        
        int n = A.length;
        for(int i = 0; i < n; i++){
            while(0 < A[i] && A[i] <= n && A[i] != A[A[i] - 1]){
                swap(A, i, A[i] - 1);
            }
        }
        for(int i = 0; i < n; i++) if(A[i] != i+1) return i+1;
        return n + 1;
    }
    
    private void swap(int[] A, int i, int j){
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
    
    
	public int firstMissingPositive0(int[] A) {
        // --> time = O(n), space = O(n) , where n = A.length
        
        // For each A[i] we add it into a set, then check our return value in set or not.
        // While in set, then we keep + 1 until our answer is not in set
        // -> actual time O(2n)
        Set<Integer> set = new HashSet<Integer>();
        int ret = 1;
        for(int e: A){
            set.add(e);
            while(set.contains(ret)) ret++;
        }
        return ret;
    }
}
