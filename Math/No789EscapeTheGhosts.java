package Math;

import Util.Parser;

public class No789EscapeTheGhosts {

	public static void main(String[] args) {
		No789EscapeTheGhosts sol = new No789EscapeTheGhosts();
		Parser parser = new Parser();
		String t = "[[1,0],[0,3]]\n" + 
				"[0,1]\n" + 
				"[[1, 0]]\n" + 
				"[2, 0]\n" + 
				"[[2, 0]]\n" + 
				"[1, 0]\n" + 
				"[[1,9],[2,-5],[3,8],[9,8],[-1,3]]\n" + 
				"[8,-10]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[][] ghosts = parser.parseMatrix(s[i]);
			int[] target = parser.parseArray(s[i+1]);
			boolean ans = sol.escapeGhosts(ghosts, target);
			System.out.println(ans);
		}
	}

	public boolean escapeGhosts(int[][] ghosts, int[] target) {
        // --> O(n), where n = ghost.length
        
        // Thanks to:
        // https://leetcode.com/problems/escape-the-ghosts/discuss/116522/C++JavaPython-Easy-and-Concise-Solution
        // https://leetcode.com/problems/escape-the-ghosts/discuss/116678/Why-interception-in-the-middle-is-not-a-good-idea-for-ghosts.
		
		// Intuition:
		// We have to reach the target before ghosts.
		
        int steps = Math.abs(target[0]) + Math.abs(target[1]);
        for(int[] g: ghosts){
            int gd = Math.abs(g[0] - target[0]) + Math.abs(g[1] - target[1]);
            if(steps >= gd) return false;
        }
        return true;
    }
}
