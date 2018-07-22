package String;

public class No680ValidPalindromeII {

	public static void main(String[] args) {
		No680ValidPalindromeII sol = new No680ValidPalindromeII();
		
		String[] S = new String[] {"", "aba","abca","ab","abc"};
		for(String s: S) {
			boolean ans = sol.validPalindrome(s);
			System.out.println(ans);
		}
	}
	
	public boolean validPalindrome(String s) {
		// --> O(N), where N = s.length()
		
		// we use i and j as start and end pointer to scan the whole string s.
		// if s[i] = s[j] then scan inwards, but if they are not the same
		// then either s[i:j-1] or s[i+1:j] is a palindrome
		// otherwise string s is not a valid palindrome the question asks for.
		
		// Since isPalindrome(s) and isPalindromeDeleteChar(s, 1) share the most part of codes, 
		// we combine them together. therefore isPalindrome(s) is equivalent as 
		// isPalindromeDeleteChar(s, 0);
		
		// Also to avoid String.substring which takes O(n) to process
		// we can put index i and j as arguments :)
		// Always remember this!
		
        return isPalindromeDeleteChar(s, 0, s.length() - 1, 1);
    }
    
    public boolean isPalindromeDeleteChar(String s, int i, int j, int d){
        while(i < j){
            if(s.charAt(i) == s.charAt(j)){
                i++; j--;
            }else{
                return d > 0 &&
                    ( isPalindromeDeleteChar(s, i + 1, j, d - 1) ||
                     isPalindromeDeleteChar(s, i, j - 1, d - 1) );
            }
        }
        return true;
    }
}
