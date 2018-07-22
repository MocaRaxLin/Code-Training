package BinarySearch;

import Util.Parser;

public class No378KthSmallestElementinaSortedMatrix {

	public static void main(String[] args) {
		No378KthSmallestElementinaSortedMatrix sol = new No378KthSmallestElementinaSortedMatrix();
		Parser parser = new Parser();
		String t = "[[1,5,9],[10,11,13],[12,13,15]]\n" + 
				"8\n" + 
				"[[1,1,2],[1,2,2]]\n" + 
				"2";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[][] M = parser.parseMatrix(s[i]);
			int k = Integer.parseInt(s[i+1]);
			int ans = sol.kthSmallest(M, k);
			System.out.println(ans);
		}
	}

	public int kthSmallest(int[][] M, int k) {
        // --> time = O(nm), where m = #row, n = #column
		
		// 85 / 85 test cases passed.
		// Runtime: 0 ms ... amazing!
		
        // T(nm) = T((nm)/2) + O(nm)
        
        // Master Theorom:
        // c = log1/log2 = 0
        // (nm)^(0+ε) = nm, when ε = 1
        // so case 3 internal nodes
        // T(nm) = Θ(nm)
        
        // Using Binary Search in Range Space
        // [min, max]
        // Compute the mid value, count # of element < mid
        // It measns mid is the (count+1)-th smallest element
        // If counter < k then update range = [mid+1 : j]
        // else update range = [i : mid]
        
        int m = M.length; if(m == 0) return 0;
        int n = M[0].length; if(n == 0) return 0;
        
        int i = M[0][0], j = M[m - 1][n - 1]; //[i, j]
        while(i < j) {
            int mid = i + (j - i) / 2;
            int count = 0,  c = n - 1;
            for(int r = 0; r < m; r++) {
                while(c >= 0 && mid < M[r][c]) c--;
                count += (c + 1); // idx + 1 = size
            }
            if(count < k) i = mid + 1; // the answer is in [m+1:j]
            else j = mid; // otherwise in [i:m], including cases count = k
        }
        return i;
    }
}
