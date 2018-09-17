package BinarySearch;

public class No668KthSmallestNumberinMultiplicationTable {

	public static void main(String[] args) {
		No668KthSmallestNumberinMultiplicationTable sol = new No668KthSmallestNumberinMultiplicationTable();
		String t = "3\n" + 
				"3\n" + 
				"5\n" + 
				"4\n" + 
				"5\n" + 
				"3\n" + 
				"678\n" + 
				"32\n" + 
				"43\n" + 
				"10\n" + 
				"10\n" + 
				"99";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=3) {
			int m = Integer.parseInt(s[i]);
			int n = Integer.parseInt(s[i+1]);
			int k = Integer.parseInt(s[i+2]);
			int ans = sol.findKthNumber(m, n, k);
			System.out.println(ans);
		}
	}

	public int findKthNumber(int m, int n, int k) {
        // --> O((n+m)log(nm))
        
        // recall No378KthSmallestElementinaSortedMatrix
		
		// Binary search in range [1:m*n]
		// Find the number which has (k-1) smaller numbers before it.
		// Count in 'F' way which is gradually decreasing c from (n-1) and accumulate c to count.
		// eg. mid = 9, m = 4, n = 7
		// table:    1  2   3   4   5   6   7   c = 7 , 1 - 7
		//           2  4   6   8  10  12  14   c = 4 , 2 - 8
		//           3  6   9  12  15  18  21   c = 2 , 3 - 6
		//           4  8  12  16  20  24  28   c = 1 , 4
		//           count = 7 + 4 + 2 + 1 = 14.
		
        int i = 1, j = m*n+1;
        while(i < j){
            int mid = i + (j-i)/2;
            int count = 0, c = n;
            for(int r = 1; r <= m; r++){
                while(c > 0 && mid < r*c) c--;
                count += c;
            }
            if(count < k) i = mid+1;
            else j = mid;
        }
        return i;
    }
}
