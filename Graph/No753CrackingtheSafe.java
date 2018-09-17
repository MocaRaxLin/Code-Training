package Graph;

import java.util.HashSet;
import java.util.Set;

public class No753CrackingtheSafe {

	public static void main(String[] args) {
		No753CrackingtheSafe sol = new No753CrackingtheSafe();
		String t = "1\n" + 
				"1\n" + 
				"3\n" + 
				"2";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int n = Integer.parseInt(s[i]);
			int k = Integer.parseInt(s[i+1]);
			String ans = sol.crackSafe(n, k);
			System.out.println(ans);
		}
	}
	
	
	public String crackSafe(int n, int k) {
		// --> time = O(?), I think it is exponential time.
        
        // Thanks to:
        // https://www.youtube.com/watch?v=iPLQgXUiU14
        // For questions understood.
        
		// We can reduce this quesiton to a graph question finding a Hamilton path.
		
        // Find Hamilton Path (visit each node once)
        // backtracking
        sb = new StringBuilder();
        seen = new HashSet<String>();
        char[] digits = new char[n];
        for(int i = 0; i < n; i++) digits[i] = '0'; // reset
        haveHamiltonPath(new String(digits), (int) Math.pow(k, n), n, k); // always true
        for(int d = 0; d < n-1; d++) sb.insert(0, '0');
        return sb.toString();
    }
    
    StringBuilder sb;
    Set<String> seen;
    private boolean haveHamiltonPath(String u, int V, int n, int k){
        if(seen.size() == V) return true;
        StringBuilder next = new StringBuilder(u);
        next.deleteCharAt(0);
        for(int d = 0; d < k; d++){
            next.append(d);
            String v = next.toString();
            if(!seen.contains(v)){
                sb.append(d);
                seen.add(v);
                if(haveHamiltonPath(v, V, n, k)) return true;
                seen.remove(v);
                sb.deleteCharAt(sb.length()-1);
            }
            next.deleteCharAt(next.length()-1);
        }
        return false;
    }
}
