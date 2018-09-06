package backtracking;

public class No526BeautifulArrangement {

	public static void main(String[] args) {
		No526BeautifulArrangement sol = new No526BeautifulArrangement();
		String t = "1\n" + 
				"2\n" + 
				"3\n" + 
				"4\n" + 
				"5\n" + 
				"6\n" + 
				"7\n" + 
				"8\n" + 
				"9\n" + 
				"10\n" + 
				"11\n" + 
				"12\n" + 
				"13\n" + 
				"14\n" + 
				"15";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int N = Integer.parseInt(s[i]);
			int ans = sol.countArrangement(N);
			System.out.println(ans);
		}
	}
	
	
	public int countArrangement(int N) {
		// --> O(N^N)
		
		// Thanks to:
		// https://leetcode.com/problems/beautiful-arrangement/discuss/99711/Java-6ms-beats-98-back-tracking-(swap)-starting-from-the-back
		// He uses swap and backtacking
		
		// Try all possible swap
		// speed: back to front > front to back. Don't know why.
        int[] count = new int[1];
        int[] A = new int[N+1];
        for(int i = 0; i < A.length; i++) A[i] = i;
        backtracking(A, N, count);
        return count[0];
    }
    
    private void backtracking(int[] A, int idx, int[] count){
        if(idx == 0){
            count[0]++;
            return;
        }
        for(int i = idx; i > 0; i--){
            if(A[i]%idx == 0 || idx%A[i] == 0){
                swap(A, i, idx);
                backtracking(A, idx-1, count);
                swap(A, i, idx);
            }
        }
    }
    
    private void swap(int[] A, int i, int j){
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
    
    
    

	public int countArrangement0(int N) {
		// --> O(N^N)
		// Try all possible cases.
		// Slow
		
        int[] count = new int[1];
        boolean[] used = new boolean[N+1];
        backtracking(used, 1, N, count);
        return count[0];
    }
    
    private void backtracking(boolean[] used, int idx, int N, int[] count){
        //System.out.println(Arrays.toString(A));
        if(idx == N+1){
            count[0]++;
            return;
        }
        for(int i = 1; i < N+1; i++){
            if(!used[i] && (i%idx == 0 || idx%i == 0)){
                used[i] = true;
                backtracking(used, idx+1, N, count);
                used[i] = false;
            }
        }
    }
}
