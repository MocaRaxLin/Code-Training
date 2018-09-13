package BitOperation;

public class No371SumofTwoIntegers {

	public static void main(String[] args) {
		No371SumofTwoIntegers sol = new No371SumofTwoIntegers();
		String t = "1\n" + 
				"2\n" + 
				"7\n" + 
				"-1\n" + 
				"11\n" + 
				"0\n" + 
				"-9\n" + 
				"9";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int a = Integer.parseInt(s[i]);
			int b = Integer.parseInt(s[i+1]);
			int ans = sol.getSum(a, b);
			System.out.println(ans);
		}
		
	}
	
	public int getSum(int a, int b) {
        // Think about bit operation
        
        if(a == 0) return b;
        if(b == 0) return a;
        while(b != 0){
            int carry = a & b;
            a ^= b;
            b = carry << 1;
        }
        return a;
    }

}
