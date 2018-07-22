package String;

import Util.Parser;

public class No848ShiftingLetters {

	public static void main(String[] args) {
		No848ShiftingLetters sol = new No848ShiftingLetters();
		Parser parser = new Parser();
		
		String testcase = "abc\n" + 
				"[3,5,9]\n" + 
				"ruu\n" + 
				"[26,7,19]\n" + 
				"t\n" + 
				"[52]";
		String[] s = testcase.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[]shifts = parser.parseArray(s[i+1]);
			String ans = sol.shiftingLetters(s[i], shifts);
			System.out.println(ans);
		}
	}

    public String shiftingLetters(String S, int[] shifts) {
    	// --> time = O(N)
    	
    	// Intuition:
    	// We use suffix sum to accumulate how many times a char need to shift.
    	// Always check the char in 'a' to 'z'
    	
        int N = S.length();
        int[] sufSum = new int[N];
        sufSum[N-1] = shifts[N-1];
        sufSum[N-1]%=26;
        for(int i = N-2; i >= 0; i--){
        	sufSum[i] = sufSum[i+1] + shifts[i];
        	sufSum[i]%=26;
        }
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < N; i++){
            char c = S.charAt(i);
            c += sufSum[i];
            if(c > 'z') c -= 26;
            sb.append(c);
        }
        return sb.toString();
    }
    
}
