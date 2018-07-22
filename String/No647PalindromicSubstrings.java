package String;

public class No647PalindromicSubstrings {

	public static void main(String[] args) {
		No647PalindromicSubstrings sol = new No647PalindromicSubstrings();
		
		String[] S = new String[] {"", "aaaa", "aaa", "aba", "abc"};
		for(String s: S) {
			int ans = sol.countSubstrings(s);
			System.out.println(ans);
		}
		
	}
	
	public int countSubstrings(String S) {
		// --> O(N^2), where N = S.length()
		
		// Whenever you face Palindrome, think about the following concept
		// "xSx" is palindrome if and only if "S" is palindrome.
		// Pali("xSx") <=> Pali("S")
		// This property sometimes allow us reduce time complexity by O(N)
		// eg. The time complexity of this problem is O(N^3) if we use brute-force methods.
		
		// Pick center and expand around it.
		// The center can be single char 'x' or none ''.
		// eg. Expand: "axa", "aa" -> "daxad", "taat";
		//
		// This method runs through S twice,
		// the first one for single char center, 
		// the second one for none char center.
		
        int ret = 0;
        char[] c = S.toCharArray();
        for(int t = 0; t < 2; t++){
            for(int i = 0; i < c.length - t; i++){
                int s = i, e = i + t;
                while(0 <= s && e < c.length && c[s] == c[e]){
                    ret++; s--; e++;
                }
            }
        }
        return ret;
    }
	
	public int countSubstrings0(String S) {
		// First version
		// This is faster than countSubstrings1(String S),
		// because there is no need to calculate
		// "i < c.length - t" and "e = i + t"
		// Of course it is not necessary to convert S into char array.
		
        int ret = 0;
        char[] c = S.toCharArray();
        for(int i = 0; i < c.length; i++){ //for odd
            int s = i, e = i;
            while(0 <= s && e < c.length && c[s] == c[e]){
                ret++;
                s--;
                e++;
            }
        }
        for(int i = 0; i < c.length - 1; i++){ // for even
            int s = i, e = i+1;
            while(0 <= s && e < c.length && c[s] == c[e]){
                ret++;
                s--;
                e++;
            }
        }
        return ret;
    }
}
