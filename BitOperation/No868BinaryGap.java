package BitOperation;

public class No868BinaryGap {

	public static void main(String[] args) {
		No868BinaryGap sol = new No868BinaryGap();
		String t = "0\n" + 
				"1\n" + 
				"5\n" + 
				"6\n" + 
				"8\n" + 
				"22\n" + 
				"33\n" + 
				"37";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int x = Integer.parseInt(s[i]);
			int ans = sol.binaryGap(x);
			System.out.println(ans);
		}
	}

	public int binaryGap(int x) {
		// --> time = O(logx)
		
		// We do arithmatic left shit on input x (like divided by 2),
		// so the total shift times will be logx/log2
		
		// Intuition:
		// We use x & 1 to read rightmost digit of x.
		// If the digit is 0 then current count (curLen) + 1.
		// Else the digit is 1 then update the answer (maxLen) by curLen
		// Thus initially maxLen = 0 and curLen = 1.
		// ps. 1001 count as length of 3
		
		// eg. 37 -> 0b100101 -> return 3
		
        int maxLen = 0;
        int curLen = 1;
        while(x > 0 && (x & 1) == 0) x >>= 1;
        while(x > 0){
            x >>= 1;
            if((x & 1) == 0) curLen++;
            else{
                maxLen = Math.max(maxLen, curLen);
                curLen = 1;
            }
        }
        return maxLen;
    }
}
