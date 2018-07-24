package Graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import Util.Parser;

public class No207CourseSchedule {

	public static void main(String[] args) {
		No207CourseSchedule sol = new No207CourseSchedule();
		Parser parser = new Parser();
		String t = "2\n" + 
				"[[1,0]]\n" + 
				"2\n" + 
				"[[1,0],[0,1]]\n" + 
				"9\n" + 
				"[[1,0],[4,0],[4,5],[5,1],[8,4],[8,5],[7,4],[7,8],[7,6],[6,3]]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int numCourses = Integer.parseInt(s[i]);
			int[][] prerequisites = parser.parseMatrix(s[i+1]);
			boolean ans = sol.canFinish(numCourses, prerequisites);
			System.out.println(ans);
		}
	}

	public boolean canFinish(int numCourses, int[][] prerequisites) {
        // --> time = O(V+E), where V is numCourses, E is prerequisites.length
        
        // Use Kahn's algorithm to do topological sort
        // while there is remaining vertices,
        // pick up a vertex with in-degree 0,
        // remove this node(eg. Set in-dgree -1)
        // and decrease in-degrees of children vertices by 1.
        
        // build map
        List<List<Integer>> adj = new ArrayList<List<Integer>>();
        for(int i = 0; i < numCourses; i++) adj.add(new LinkedList<Integer>());
        int[] inDegree = new int[numCourses];
        for(int[] e: prerequisites){
            adj.get(e[1]).add(e[0]); // [course, prerequisite] => edge:[p,c]
            inDegree[e[0]]++;
        }
        
        // naive Kahn's
        int count = 0;
        while(count < numCourses){
            
            int u = 0; // find a vertex with in-degree 0
            while(u < numCourses && inDegree[u] != 0) u++; // Improved in No210CourseScheduleII
            if(u == numCourses) return false;
            
            inDegree[u] = -1; // set vertex visited
            
            // decrease in-degree of adjacent nodes by 1
            Iterator<Integer> it = adj.get(u).iterator();
            while(it.hasNext()){
                int v = it.next();
                inDegree[v]--;
            }
            
            count++;
        }
        return true;
    }
}
