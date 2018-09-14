package BinarySearch;

import java.util.Arrays;

import Util.Parser;

public class No274HIndex {

	public static void main(String[] args) {
		No274HIndex sol = new No274HIndex();
		Parser parser = new Parser();
		String t = "[3,0,6,1,5]\n" + 
				"[4,6,2,7,9,0,2,3,7,2,9,3]\n" + 
				"[0,0,2,3,3,4,4,4]\n" + 
				"[1,1,1,1,1]\n" + 
				"[0,0,1]\n" + 
				"[]\n" + 
				"[0]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] citations = parser.parseArray(s[i]);
			int ans = sol.hIndex(citations);
			System.out.println(ans);
		}
	}
	public int hIndex(int[] citations) {
		// --> O(nlogn), where n = citations.length.
		
		// Find the max index i where A[i] <= N-i
		
        if(citations.length == 0) return 0;
        Arrays.sort(citations);
        int N = citations.length;
        int i = 0, j = N;
        while(i<j){
            int m = i + (j-i)/2;
            if(citations[m] < N - m) i = m + 1;
            else j = m;
        }
        return N - i;
    }
}
