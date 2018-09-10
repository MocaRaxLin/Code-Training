package String;

import java.util.LinkedList;
import java.util.Queue;

public class No481MagicalString {

	public static void main(String[] args) {
		No481MagicalString sol = new No481MagicalString();
		String t = "1\n" + 
				"4\n" + 
				"7\n" + 
				"9\n" + 
				"142\n" + 
				"14613\n" + 
				"100000";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int n = Integer.parseInt(s[i]);
			int ans = sol.magicalString(n);
			System.out.println(ans);
		}
	}

	public int magicalString(int n) {
		// --> time = O(n), space < n.
		
		// If we use A[n+1] to store the magic sequence of length n, 
		// yes, performance will be better.
		// But if n is every large, it might hit MLE(memory limit exceeded).
		// Thus we use queue to poll those used numbers, try to save more memory for on-coming numbers
		
        if(n == 0) return 0;
        if(n < 4) return 1;
        int count = 1, size = 3;
        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(2);
        int nextInt = 1;
        while(q.size() > 0){
            int d = q.poll();
            while(d-->0){
                q.offer(nextInt);
                size++;
                if(nextInt == 1) count++;
                if(size == n) return count;
            }
            nextInt = nextInt == 1? 2: 1;
        }
        return 0;
    }
}
