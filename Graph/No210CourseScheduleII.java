package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import Util.Parser;

public class No210CourseScheduleII {

	public static void main(String[] args) {
		No210CourseScheduleII sol = new No210CourseScheduleII();
		Parser parser = new Parser();
		String t = "2\n" + 
				"[[1,0]]\n" + 
				"2\n" + 
				"[[1,0],[0,1]]\n" + 
				"9\n" + 
				"[[1,0],[4,0],[4,5],[5,1],[8,4],[8,5],[7,4],[7,8],[7,6],[6,3]]\n" + 
				"3\n" + 
				"[[1,0],[1,2],[0,1]]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int numCourses = Integer.parseInt(s[i]);
			int[][] prerequisites = parser.parseMatrix(s[i+1]);
			int[] ans = sol.findOrder(numCourses, prerequisites);
			System.out.println(Arrays.toString(ans));
		}
	}

	public int[] findOrder(int numCourses, int[][] prerequisites) {
        // --> time = O(V+E), where V is numCourses, E is prerequisites.length
        
        // build map
        List<List<Integer>> adj = new ArrayList<List<Integer>>();
        for(int i = 0; i < numCourses; i++) adj.add(new LinkedList<Integer>());
        int[] inDegree = new int[numCourses];
        for(int[] e: prerequisites){
            adj.get(e[1]).add(e[0]); // [course, prerequisite] => edge:[p,c]
            inDegree[e[0]]++;
        }
        
        // Kahn's
        
        // Use a queue to store nodes of in-degree 0
        // instead of naive linear search :)
        
        // Offer all in-degree 0 node
        int[] queue = new int[numCourses];
        int h = 0, r = 0; // size = rear - head
        for(int i = 0; i < numCourses; i++){
            if(inDegree[i] == 0) queue[r++] = i;
        }
        
        int[] order = new int[numCourses];
        int count = 0;
        
        while(count < numCourses && h < r){
            int u = queue[h++];  // poll
            order[count++] = u; // add to order
            inDegree[u] = -1;  // set visited
            
            Iterator<Integer> it = adj.get(u).iterator();
            while(it.hasNext()){
                int v = it.next();
                if(inDegree[v] == -1) continue; // skip visited node
                inDegree[v]--;
                if(inDegree[v] == 0) queue[r++] = v;
            }
        }
        
        return count < numCourses? new int[0]: order;
    } 
}
