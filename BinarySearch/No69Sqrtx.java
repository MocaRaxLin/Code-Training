package BinarySearch;

public class No69Sqrtx {

	public static void main(String[] args) {
		No69Sqrtx sol = new No69Sqrtx();
		String t = "4\n" + 
				"6\n" + 
				"99\n" + 
				"24\n" + 
				"1\n" + 
				"0\n" + 
				"2147395599\n" + 
				"2147395600";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int x = Integer.parseInt(s[i]);
			int ans = sol.mySqrt(x);
			System.out.println(ans);
		}
	}

    public int mySqrt(int x) {
    	// --> time = O(logn), where n = x.
    	
    	// Intuition:
    	// [1,2,3,...,m,...,x] i = 1, j = x, m = i + (j-i)/2.
    	// if m == x/m then return m
    	// if m < x/m then need bigger m, so sqrt(x) in [m,j]
    	// if m > x/m then need smaller m, so sqrt(x) in [i,m]
    	//
    	// Finally in i:i+1 use the smaller one i
    	// eg,
    	// [1,2,3,4,5,6,7]
    	// i = 1, j = 7 -> m = 4
    	// 4 > 7/4 -> [i,m]
    	//
    	// [1,2,3,4]
    	// i = 1, j = 4 -> m = 2
    	// 2 < 7/2 -> [m:j]
    	//
    	// [2,3,4]
    	// i = 2, j = 4 -> m = 3
    	// 3 > 7/3 -> [i:m]
    	//
    	// [2,3] -> return i, because j is from m such that m > x/m !
    	
        if(x == 0) return 0;
        int i = 1, j = x;
        while(j - i > 1){ //be careful the last case i:i+1
            int m = i + (j-i)/2;
            if(m == x/m) return m;
            else if(m < x/m) i = m; // i = m is available because of j-i>1
            else j = m; // j = m is available because of j-i>1
        }
        return i;
    }
}
