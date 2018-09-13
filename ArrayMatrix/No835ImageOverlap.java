package ArrayMatrix;

import Util.Parser;

public class No835ImageOverlap {

	public static void main(String[] args) {
		No835ImageOverlap sol = new No835ImageOverlap();
		Parser parser = new Parser();
		String t = "[[1,1,0],[0,1,0],[0,1,0]]\n" + 
				"[[0,0,0],[0,1,1],[0,0,1]]\n" + 
				"[[1,0],[0,0]]\n" + 
				"[[0,1],[1,0]]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[][] A = parser.parseMatrix(s[i]);
			int[][] B = parser.parseMatrix(s[i+1]);
			int ans = sol.largestOverlap(A, B);
			System.out.println(ans);
		}
	}
	
	public int largestOverlap(int[][] A, int[][] B) {
        // --> time = O(N^4), space = O(1).
        
        // Intuition:
        // Let B move over A, get the overlap area,
        // counter add by 1 when both gird have 1 at position A[i][j] and B[i-h][j-k].
        
        // Key point is to compute the area range
        // Start from A's (0,0) -> convert to B's (-h,-k)
        // If B's (-h,-k) is out of range then set it 0 and convert it back to A's coordinate.
        // End points is similar
		
		// eg. h = -2, k = 1, N = 3
		// start = A(0,0) -> B(2,-1) x -> B(2,0) -> A(0,1)
		// end   = A(2,2) -> B(4, 1) x -> B(2,1) -> A(0,2)
        
        
        int N = A.length; if(N == 0) return 0;
        int ret = 0;
        for(int h = -N+1; h < N; h++){
            for(int k = -N+1; k < N; k++){
                int[] r = getRange(h, k, N);
                int count = 0;
                for(int i = r[0]; i <= r[2]; i++){
                    for(int j = r[1]; j <= r[3]; j++){
                        if(A[i][j] == 1 && B[i-h][j-k] == 1) count++;
                    }
                }
                ret = count > ret? count: ret;
            }
        }
        return ret;
    }
    
    private int[] getRange(int h, int k, int N){
        int[] ret = new int[4];
        int ai = 0, aj = 0, bi = -h, bj = -k;
        if(bi < 0){ bi = 0; ai = bi + h; }
        if(bj < 0){ bj = 0; aj = bj + k; }
        ret[0] = ai; ret[1] = aj;
        ai = N - 1; aj = N - 1; bi = ai - h; bj = aj - k;
        if(bi >= N){ bi = N-1; ai = bi + h; }
        if(bj >= N){ bj = N-1; aj = bj + k; }
        ret[2] = ai; ret[3] = aj;
        // System.out.println("("+h+","+k+")"+Arrays.toString(ret));
        return ret;
    }

}
