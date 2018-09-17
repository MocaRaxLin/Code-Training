package Graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import Util.Parser;
import Util.Show;

public class No332ReconstructItinerary {

	public static void main(String[] args) {
		No332ReconstructItinerary sol = new No332ReconstructItinerary();
		Parser parser = new Parser();
		Show show = new Show();
		String t = "[[\"MUC\",\"LHR\"],[\"JFK\",\"MUC\"],[\"SFO\",\"SJC\"],[\"LHR\",\"SFO\"]]\n" + 
				"[[\"JFK\",\"SFO\"],[\"JFK\",\"ATL\"],[\"SFO\",\"ATL\"],[\"ATL\",\"JFK\"],[\"ATL\",\"SFO\"]]\n" + 
				"[[\"JFK\",\"TPE\"],[\"LAX\",\"JFK\"],[\"TPE\",\"LAX\"],[\"JFK\",\"ANG\"],[\"ANG\",\"LAX\"],[\"LAX\",\"TCP\"],[\"TCP\",\"JFK\"]]\n" + 
				"[[\"JFK\",\"KUL\"],[\"JFK\",\"NRT\"],[\"NRT\",\"JFK\"]]\n" + 
				"[[\"EZE\",\"AXA\"],[\"TIA\",\"ANU\"],[\"ANU\",\"JFK\"],[\"JFK\",\"ANU\"],[\"ANU\",\"EZE\"],[\"TIA\",\"ANU\"],[\"AXA\",\"TIA\"],[\"TIA\",\"JFK\"],[\"ANU\",\"TIA\"],[\"JFK\",\"TIA\"]]";
		t = t.replace("\"", "");
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			String[][] tickets = parser.parseMatrixStr(s[i]);
			List<String> ans = sol.findItinerary(tickets);
			show.showList(ans, true);
		}
	}
	
	public List<String> findItinerary(String[][] tickets) {
        // --> time = O(ElogE + E), where E = tickets.length
        
        // In the begining, you may think using path extend and drop end solution.
        // But after you drop an edge, the edge become available again, and program will take it again.
        // It lead to infinite loop, and hard to fix.
        
        // Thanks to:
        // https://leetcode.com/problems/reconstruct-itinerary/discuss/78766/Share-my-solution
        
        // Intuition:
		// Finding Euler Path in a graph (visited all edges once)
        // Key point: the path exists
        // DFS searching, when ever we hit an end, we put the edge to the head of path,
        // because we know there must be a path help the node go to the path end.
        
        List<String> path = new LinkedList<String>();
        int E = tickets.length; if(E == 0) return path;
        
        // build braph
        Map<String, PriorityQueue<String>> G = new HashMap<String, PriorityQueue<String>>();
        for(String[] t : tickets){
            PriorityQueue<String> pq = G.getOrDefault(t[0], new PriorityQueue<String>());
            pq.add(t[1]);
            if(pq.size() == 1) G.put(t[0], pq);
        }
        
        // if there is a path, it return true;
        gotPath(G, "JFK", path, E);
        return path;
    }
    
    private void gotPath(Map<String, PriorityQueue<String>> G, String iata, List<String> path, int E){
        
        PriorityQueue<String> arrivals = G.get(iata);
        while (arrivals != null && arrivals.size() > 0)
            gotPath(G, arrivals.poll(), path, E);
        path.add(0, iata);
    }

}
