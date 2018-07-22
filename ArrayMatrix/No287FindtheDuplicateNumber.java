package ArrayMatrix;

import Util.Parser;

public class No287FindtheDuplicateNumber {

	public static void main(String[] args) {
		No287FindtheDuplicateNumber sol = new No287FindtheDuplicateNumber();
		Parser parser = new Parser();
		String t = "[1,3,4,2,2]\n"
				+ "[1,6,4,8,9,4,2,3,7,5]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] A = parser.parseArray(s[i]);
			int ans = sol.findDuplicate(A);
			System.out.println(ans);
		}
	}

    public int findDuplicate(int[] A) {
        // --> time = O(n), space = O(n)
        int[] bucket = new int[A.length];
        for(int i = 0; i < A.length; i++){
            if(bucket[A[i]] == 0) bucket[A[i]]++;
            else return A[i];
        }
        return -1;
        
        
        // --> time = O(nlogn), space = O(1)
        // A.length must > 1
        /*
        Arrays.sort(A);
        for(int i = 1; i < A.length; i++){
            if(A[i] == A[i-1]) return A[i];
        }
        return -1;
        */
    }
}
