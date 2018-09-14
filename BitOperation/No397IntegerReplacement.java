package BitOperation;

public class No397IntegerReplacement {

	public static void main(String[] args) {
		No397IntegerReplacement sol = new No397IntegerReplacement();
		String t = "8\n" + 
				"173\n" + 
				"92\n" + 
				"21";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int n = Integer.parseInt(s[i]);
			int ans = sol.integerReplacement(n);
			System.out.println(ans);
		}
	}

	public int integerReplacement(int n) {
		// --> time = O(logn)
		
		// Thanks to:
		// https://leetcode.com/problems/integer-replacement/discuss/87928/Java-12-line-4(5)ms-iterative-solution-with-explanations.-No-other-data-structures.
		
		// Intution:
		// When n is even the operation is fixed -> n /= 2.
		// When n is odd, either n+1 or n-1.
		//
		// We want n be devided by 2 as many as possible,
		// so we want the operation that makes result even.
		// i.e. the one n+1 or n-1 can be devided by 4.
		// n = 2k + 1  -->  n + 1 = 2k + 2,  n - 1 = 2k
		// k + 1 vs. k who can be devided by 2 ?
		// That is the key point.
		// 
		// Exception: n = 3, we will pick n-1.
		
        if (n == Integer.MAX_VALUE) return 32; //n = 2^31-1;
        int count = 0;
        while (n > 1){
            if (n % 2 == 0) n /= 2;
            else{
                if ( (n + 1) % 4 == 0 && n != 3 ) n++;
                else n--;
            }
            count++;
        }
        return count;
    }
}
