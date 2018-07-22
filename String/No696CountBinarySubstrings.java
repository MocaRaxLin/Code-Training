package String;

public class No696CountBinarySubstrings {

	public static void main(String[] args) {
		No696CountBinarySubstrings sol = new No696CountBinarySubstrings();
		
		String[] S = new String[] {"","01001","1","0","00110011","10101"};
		for(String s: S) {
			int ans = sol.countBinarySubstrings(s);
			System.out.println(ans);
		}
	}
	
	public int countBinarySubstrings(String s) {
		// --> time = O(N), space = O(1)
		// using prev and cur variable
		// we get rid of var last using c[i - 1] == c[i] instead,
		// since c[i - 1] store the same value as var last
		
        int ret = 0;
        char[] c = s.toCharArray();
        int prev = 0;
        int cur = 1; //for first char
        
        for(int i = 1; i < c.length; i++){
            if(c[i - 1] == c[i]){
                cur++;
            }else{
                ret += Math.min(prev, cur);
                prev = cur;
                cur = 1;
            }
        }
        return ret + Math.min(prev, cur);
    }
	
	public int countBinarySubstrings0(String s) {
		// --> time = O(N), where N = s.length()
		// --> space = O(N), but we can improve it to O(1)
		//     using prev and cur instead of frequency array
		
		// hint:
		// 000111 -> 3
		// 11100 -> 2
		// 00011100 -> 5
		
		// From given the given hint, we know we can group by chars,
		// and then calculate minimum frequency two by two.
		// eg. 000111001111001 -> 332421
		// -> ret = min(3,3) + min(3,2) + min(2,4) + min(4,2) + min(2,1)  :)
		
		// counter variable not needed, because frequency array exists.
		char[] cArray = s.toCharArray();
        int[] freqs = new int[cArray.length+1];
        int fEnd = 0;
        
        char last = ' ';
        for(char c: cArray){
            if(last == c){
                freqs[fEnd]++;
            }else{
                fEnd++;
                freqs[fEnd] = 1;
                last = c;
            }
        }
        fEnd++;
        
        int ret = 0;
        for(int i = 1; i < fEnd - 1; i++)
            ret += Math.min(freqs[i], freqs[i + 1]);
        return ret;
    }
}
