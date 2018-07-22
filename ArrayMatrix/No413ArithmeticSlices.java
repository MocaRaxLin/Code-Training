package ArrayMatrix;

import Util.Parser;

public class No413ArithmeticSlices {

	public static void main(String[] args) {
		No413ArithmeticSlices sol = new  No413ArithmeticSlices();
		Parser parser = new Parser();
		String t = "[1,2,3,4]\n" + 
				"[1,2,3,4,7,5,3,-1,0,1,9,9,9,9,9]\n" + 
				"[]\n" + 
				"[4,5,7,7,7,3,4,5,4,4,2,2,5,36,4,62,46,4,7,8,8,8,8]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] A = parser.parseArray(s[i]);
			int ans = sol.numberOfArithmeticSlices(A);
			System.out.println(ans);
		}
	}
	
    public int numberOfArithmeticSlices(int[] A) {
    	// --> time = O(n), where n = A.length.
    	
    	// eg. [ 1,2,3,4, 7,5,3, -1,0,1, 9,9,9,9,9]
    	// len =       4      3       3          5
    	// counter     3      1       1          6 ... by n(n-1)/2, where n = len - 2
    	//
    	// Look carefully
    	//      1,2,3,4 -> <1,2,3>, <2,3,4> -> 2
    	//      7,5,3 -> 1
    	//      -1,0,1 -> 1
    	//      9,9,9,9,9 -> <9,9,9>, <9,9,9>, <9,9,9> -> 3
    	// Check only the center part of array is good,
    	// so iterator i = 1 to n-2.
    	
        if(A.length < 3) return 0;
        int ret = 0;
        int counter = 0;
        for(int i = 1; i < A.length-1; i++){
            if(A[i-1] + A[i+1] == 2*A[i]) counter++;
            else{
                ret += counter*(counter+1)/2;
                counter = 0;
            }
        }
        ret += counter*(counter+1)/2;
        return ret;
    }

}
