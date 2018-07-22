package BitOperation;

public class No869ReorderedPowerof2 {

	public static void main(String[] args) {
		No869ReorderedPowerof2 sol = new No869ReorderedPowerof2();
		String t = "1\n" + 
				"4\n" + 
				"46\n" + 
				"4021\n" + 
				"23\n" + 
				"125\n" + 
				"9046\n" + 
				"4802\n" + 
				"12384791";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int x = Integer.parseInt(s[i]);
			boolean ans = sol.reorderedPowerOf2(x);
			System.out.println(ans);
		}
	}

	public boolean reorderedPowerOf2(int x) {
        // --> time = 32 * 10 constant time 
        
        // Intuition:
        // Count number of each digit 0 to 9 in x
        // and compare them to all integers of power of two.
        
        // eg. x = 60275620
        //     Number of each digits in x -> 0:2, 2:2, 5:1, 6:2, 7:1
        // compare x to -> 1, 2, 4, 8, 16, 32, 64, 128,
        //                 256, 512, 1024, 2048, 4096 ... to 2^31
        
        int[] xC = countDigits(x);
        int p = 1;
        for(int i = 0; i < 32; i++){ // int has 32 bits
            int[] powerOfTwo = countDigits(p);
            if(sameAmountDigits(xC, powerOfTwo)) return true;
            p <<= 1;
        }
        return false;
    }
    
    public boolean sameAmountDigits(int[] a, int[] b){
        // time = 10, constant time
        for(int i = 0; i < 10; i++){
            if(a[i] != b[i]) return false;
        }
        return true;
    }
    public int[] countDigits(int x){
        // time = O(9), constant time
        // input 1 <= N <= 10^9
        int[] ret = new int[10];
        while(x > 0){
            ret[x%10]++;
            x /= 10;
        }
        return ret;
    }
}
