package BinarySearch;

public class No744FindSmallestLetterGreaterThanTarget {

	public static void main(String[] args) {
		No744FindSmallestLetterGreaterThanTarget sol = new No744FindSmallestLetterGreaterThanTarget();
		String t = "cfj\n"
				+ "a\n"
				+ "cfj\n"
				+ "c\n"
				+ "cfj\n"
				+ "d\n"
				+ "cfj\n"
				+ "g\n"
				+ "cfj\n"
				+ "j\n"
				+ "cfj\n"
				+ "k\n"
				+ "ab\n"
				+ "z\n"
				+ "eeeeeennnn\n"
				+ "e\n";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			char[] A = s[i].toCharArray();
			char T = s[i+1].charAt(0);
			char ans = sol.nextGreatestLetter(A, T);
			System.out.println(ans);
		}
	}
	
    public char nextGreatestLetter(char[] A, char T) {
        // --> time is O(logn), where n = A.length
        
        // the answer in [i:j],
        // if the answer at index A.length, it means answer at index 0.
        
        // A[m] <= T -> answer in [m+1:j]
        // T < A[m]  -> answer in [i:m]
        
        int i = 0, j = A.length;
        while(i < j){
            int m = i + (j - i)/2; // to avoid overflow
            if(A[m] <= T) i = m + 1; // answer in [m+1, j]
            else j = m; // answer in [i:m]
        }
        
        return A[i%A.length];
    }

}