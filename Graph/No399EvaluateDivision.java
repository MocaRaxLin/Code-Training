package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Util.Parser;

public class No399EvaluateDivision {

	public static void main(String[] args) {
		No399EvaluateDivision sol = new No399EvaluateDivision();
		Parser parser = new Parser();
		String t = "[[\"a\",\"b\"],[\"b\",\"c\"]]\n" + 
				"[2.0,3.0]\n" + 
				"[[\"a\",\"c\"],[\"b\",\"c\"],[\"a\",\"e\"],[\"a\",\"a\"],[\"x\",\"x\"]]\n" + 
				"[[\"a\",\"b\"],[\"c\",\"d\"]]\n" + 
				"[1.0,1.0]\n" + 
				"[[\"a\",\"c\"],[\"b\",\"d\"],[\"b\",\"a\"],[\"d\",\"c\"]]";
		t = t.replaceAll("\"", "");
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=3) {
			System.out.println(s[i]);
			String[][] equations = parser.parseMatrixStr(s[i]);
			for(String[] a: equations) System.out.println(Arrays.toString(a));
			
			System.out.println(s[i+1]);
			double[] values = parser.parseArrayDouble(s[i+1]);
			System.out.println(Arrays.toString(values));
			
			System.out.println(s[i+2]);
			String[][] queries = parser.parseMatrixStr(s[i+2]);
			for(String[] b: queries) System.out.println(Arrays.toString(b));
			
			double[] ans = sol.calcEquation(equations, values, queries);
			System.out.println(Arrays.toString(ans));
		}
	}
	
	public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
		// --> time = O(E+QV)
		
		// Important:
		// Build Graph and Work on it!
		
        Map<String, Integer> idxMap = new HashMap<String, Integer>();
        int V = 0;
        
        // Build Graph -> time = O(E), space = O(V^2)
        List<List<Integer>> adj = new ArrayList<List<Integer>>();
        for(String[] e: equations){
            if(!idxMap.containsKey(e[0])) idxMap.put(e[0], V++);
            if(!idxMap.containsKey(e[1])) idxMap.put(e[1], V++);
            int u = idxMap.get(e[0]), v = idxMap.get(e[1]);
            while(adj.size() < V) adj.add(new LinkedList<Integer>());
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        double[][] cost = new double[V][V];
        for(int i = 0; i < equations.length; i++){
            int u = idxMap.get(equations[i][0]), v = idxMap.get(equations[i][1]);
            cost[u][v] = values[i];
            cost[v][u] = 1 / values[i];
        }
        
        // time -> O(QV)
        // do iterative DFS and find path from u to v
        // to find result of each query from u to v
        // eg. E = [[a,b,3], [c,b,2]] , q = [a,c]
        //     imply => [[b,a,0.333], [b,c,0.5]]
        //     path result: {a,1} -> {b,3} -> {c,1.5}.
        //     a -> b -> c ==> 1 * 3 * 0.5
        
        double[] res = new double[queries.length];
        Arrays.fill(res, -1);
        for(int i = 0; i < queries.length; i++){
            if(idxMap.containsKey(queries[i][0]) && idxMap.containsKey(queries[i][1])){
                int u = idxMap.get(queries[i][0]), v = idxMap.get(queries[i][1]);
                
                if(cost[u][v] != 0){ // online improvement 1
                    res[i] = cost[u][v];
                    continue;
                }
                
                boolean[] seen = new boolean[V];
                int[] stack = new int[V];
                double[] resStack = new double[V];
                
                int size = 0;
                stack[size] = u; // [node, pathResult]
                resStack[size] = 1;
                size++;
                
                while(size > 0){
                    size--; // pop
                    int node = stack[size];
                    double result = resStack[size];
                    cost[u][node] = result; // online improvement 1
                    seen[node] = true;
                    
                    if(node == v){ // v is found
                        res[i] = result;
                        break;
                    }else{
                        Iterator<Integer> ite = adj.get(node).iterator();
                        while(ite.hasNext()){
                            int next = ite.next();
                            if(seen[next]) continue;
                            stack[size] = next;
                            resStack[size] = result * cost[node][next];
                            size++;
                        }
                    }
                }
            }
        }
        return res;
    }
	
	// Follow up:
	// If there is a huge amount of queries, how can we improve preformance?
	// 
	// In my solution each query takes O(V), so total time to comsume Q queries takes O(QV)
	// The ideal time to complete a query is constant time.
	// It is like given a query [u,v], there is a edge (u,v) to help me get answer directly.
	// Complete a graph as a dense graph having edges size of O(V^2).
	// Detail compute using Floyd–Warshall.
	// It is the same as finding all pairs shortest path.
	// Now we can get direct answer from cost[u][v]
	// 
	// Time complexity
	// Old: O(E) + O(QV) = O(V^2)
	// New: O(EV) + O(Q) = O(V^3)
	//
	// if Q > V use Floyd–Warshall.
	// Else use online improvement.
	//
	// That old one become new one gradually. -- nice shot
	// We use O(E) time to build graph
	// for each query (u,v) we check if we saw it before?
	//     yes: return the stored result
	//     no : do DFS find result of (u,v) store the cost in matrix.
	// 
	// Online improvement 1 -- prefer
	// When we do DFS store results of all path (u, tmpNode),
	// so next time when we face any query start from u
	// we can get the result directly.
	//
	
	public double[] calcEquationFW(String[][] equations, double[] values, String[][] queries) {
        Map<String, Integer> idxMap = new HashMap<String, Integer>();
        int V = 0;
        
        // Indexing nodes
        for(String[] e: equations){
            if(!idxMap.containsKey(e[0])) idxMap.put(e[0], V++);
            if(!idxMap.containsKey(e[1])) idxMap.put(e[1], V++);
        }
        
        // Build graph
        double[][] cost = new double[V][V];
        for(int i = 0; i < equations.length; i++){
            int u = idxMap.get(equations[i][0]), v = idxMap.get(equations[i][1]);
            cost[u][v] = values[i];
            cost[v][u] = 1 / values[i];
        }
        
        // Floyd–Warshall
        for(int i = 0; i < V; i++){
            for(int j = 0; j < V; j++){
                for(int k = 0; k < V; k++){
                    if(cost[i][j] == 0 && cost[i][k] != 0 && cost[k][j] != 0){
                        cost[i][j] = cost[i][k] * cost[k][j];
                    }
                }
            }
        }
        
        double[] ret = new double[queries.length];
        Arrays.fill(ret, -1);
        for(int i = 0; i < queries.length; i++){
            if(idxMap.containsKey(queries[i][0]) && idxMap.containsKey(queries[i][1])){
                int u = idxMap.get(queries[i][0]), v = idxMap.get(queries[i][1]);
                ret[i] = cost[u][v] == 0? -1: cost[u][v];
            }
        }
        return ret;
    }
	
}
