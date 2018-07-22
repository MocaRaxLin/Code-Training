package Math;

public class No858MirrorReflection {

	public static void main(String[] args) {
		No858MirrorReflection sol = new No858MirrorReflection();
		String t = "2\n" + 
				"1\n" + 
				"4\n" + 
				"0\n" + 
				"3\n" + 
				"1";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int p = Integer.parseInt(s[i]);
			int q = Integer.parseInt(s[i+1]);
			int ans = sol.mirrorReflection(p, q);
			System.out.println(ans);
		}
	}
	
	// Nice GCD function to remember
	int gcd(int a,int b){
        if (b == 0) return a;
        return gcd( b, a % b );
    }
	
    public int mirrorReflection(int p, int q) {
    	// --> O(LCM(p,q)/p)
    	
    	// source: https://ppt.cc/fEv6zx
    	// 
    	// Since the ray bounce back and fro,
    	// we can think this problem as q going up to catch p * h (h >= 1)
    	//
    	// Thus in this problem we want find h and k such that p*h = q*k
    	// 0: k -> odd
    	//    h -> even
    	// 1: k -> odd
    	//    h -> odd
    	// 2: k -> even
    	//    h -> odd
    	// 
    	
    	
    	
        if(q == 0) return 0;
        int h = 1;
        while(p*h%q != 0) h++;
        int k = p * h / q;
        //System.out.println(h+ ", " + k);
        if(k % 2 == 0) return 2;
        return h % 2 == 0 ? 0 : 1;
    }

}
