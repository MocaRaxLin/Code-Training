package Graph;

import java.util.LinkedList;
import java.util.List;

import Util.Parser;
import Util.Show;

public class No802FindEventualSafeStates {

	public static void main(String[] args) {
		No802FindEventualSafeStates sol = new No802FindEventualSafeStates();
		Parser parser = new Parser();
		Show show = new Show();
		String t = "[[1,2],[2,3],[5],[0],[5],[],[]]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[][] graph = parser.parseMatrix(s[i]);
			List<Integer> ans = sol.eventualSafeNodes(graph);
			show.showListInt(ans, true);
		}
	}
	
	public List<Integer> eventualSafeNodes(int[][] graph) {
        // --> O(N), where N = graph.length, number of nodes.
		
        // You can use reverse topological sort to extract 0-outDegree nodes gradually.
        // But it takes ElogV and hard to implement
        
        // Thanks to:
        // https://www.youtube.com/watch?v=6ySoJbyBs4E&feature=youtu.be
        // This is an O(n) solution by DFS to find and get rid of cycle.
        
        List<Integer> safe = new LinkedList<Integer>();
        int N = graph.length; if(N == 0) return safe;
        int[] acyclic = new int[N]; // 1 -> acyclic, 0 -> not visited, -1 -> loop
        
        for(int i = 0; i < N; i++){
            if(acyclic[i] == 1 || (acyclic[i] == 0 && isAcyclic(graph, i, acyclic))){
                safe.add(i);
            }
        }
        return safe;
    }
    
    private boolean isAcyclic(int[][] graph, int idx, int[] acyclic){
        if(acyclic[idx] != 0) return acyclic[idx] == 1;
        acyclic[idx] = -1; // assume node idx in loop
        for(int i = 0; i < graph[idx].length; i++){
            int v = graph[idx][i];
            // if any child node is in loop, then node idx is in loop too.
            if(!isAcyclic(graph, v, acyclic)){
                return false;
            }
        }
        acyclic[idx] = 1; // assumption is wrong, correct the tag.
        return true;
    }
}
