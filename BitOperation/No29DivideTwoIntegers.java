package BitOperation;

public class No29DivideTwoIntegers {

	public static void main(String[] args) {
		No29DivideTwoIntegers sol = new No29DivideTwoIntegers();
		String t = "10\n" + 
				"3\n" + 
				"-2147483648\n" + 
				"1\n" + 
				"-2147483648\n" + 
				"-2147483648\n" + 
				"-2147483648\n" + 
				"2\n" + 
				"7\n" + 
				"-3";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int A = Integer.parseInt(s[i]);
			int B = Integer.parseInt(s[i+1]);
			int ans = sol.divide(A, B);
			System.out.println(ans);
		}
	}
	
	public int divide0(int A, int B) {
		// --> O()
		
		// Thanks to:
		// https://leetcode.com/problems/divide-two-integers/discuss/142849/C++JavaPython-Should-Not-Use-%22long%22-Int
		
		// Make up the largest part c = b*(2^k)
		// then a -= c and repeat
		
        if (A == 1 << 31 && B == -1) return (1 << 31) - 1;
        int a = Math.abs(A), b = Math.abs(B), res = 0, x = 0;
        while (a - b >= 0) {
            for (x = 0; a - (b << x << 1) >= 0; x++);
            res += 1 << x;
            a -= b << x;
        }
        return (A > 0) == (B > 0) ? res : -res;
    }

	public int divide(int A, int B) {
		// --> O(1)
		
		// Intuition:
		// 15 / 3 -> 
		
        if (A == 1 << 31 && B == -1) return (1 << 31) - 1;
        int a = Math.abs(A), b = Math.abs(B), res = 0;
        for (int x = 31; x >= 0; x--) {
        	// a - (b <<< x) >= 0, divide by 2^x on both sides
            if ((a >>> x) - b >= 0) { // arithmetic shift (i.e. * / 2)
                res += 1 << x;
                a -= b << x;
            }
        }
        return (A > 0) == (B > 0) ? res : -res;
    }
}
