package ArrayMatrix;

import Util.Parser;

public class No718MaximumLengthofRepeatedSubarray {

	public static void main(String[] args) {
		No718MaximumLengthofRepeatedSubarray sol = new No718MaximumLengthofRepeatedSubarray();
		Parser parser = new Parser();
		// Input Constraint
		// -> 1 <= len(A), len(B) <= 1000
		// -> 0 <= A[i], B[i] < 100
		String testcase = "[1,2,3,2,1]\n" + 
				"[3,2,1,4,7]\n" + 
				"[0]\n" + 
				"[0]\n" + 
				"[1,2,3,4,5,6,7,8]\n" + 
				"[1,2,3,4]\n" + 
				"[1,2,3,4]\n" + 
				"[1,2,3,4,5,6,7,8]\n" + 
				"[0,0,0,0,1]\n" + 
				"[1,0,0,0,0]";
		String[] s = testcase.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[] A = parser.parseArray(s[i]);
			int[] B = parser.parseArray(s[i+1]);
			int ans = sol.findLength(A, B);
			System.out.println(ans);
		}
	}
	public int findLength(int[] A, int[] B) {
		// --> time = O(nm), where n = A.length, m = B.length
		// (n+m) * Min(n,m) -> O(nm)
		
		// Window Sliding!
		// eg. A = [1,4,2,5], B = [7,4,2]
		//
		// 1.     [1,4,2,5]
		//    [7,4,2]
		// 2.     [1,4,2,5]
		//      [7,4,2]
		// 3.     [1,4,2,5]
		//        [7,4,2]
		// 4.     [1,4,2,5]
		//          [7,4,2]
		// 5.     [1,4,2,5]
		//            [7,4,2]
		// 6.     [1,4,2,5]
		//              [7,4,2]
		// Go though the overlap part in each step.
		// Collect result to find the longest repeated sub array.
        int ret = 0;
        for(int i = B.length - 1; i > 0; i--){
            int counter = 0;
            for(int j = 0; j < A.length && i + j < B.length; j++){
                if(B[i+j] == A[j]) ret = Math.max(++counter, ret);
                else counter = 0;
            }
        }
        for(int i = 0; i < A.length; i++){
            int counter = 0;
            for(int j = 0; j < B.length && i + j < A.length; j++){
                if(A[i+j] == B[j]) ret = Math.max(++counter, ret);
                else counter = 0;
            }
        }
        return ret;
    }
}
