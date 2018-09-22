package ArrayMatrix;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import Util.Parser;
import Util.Show;

public class No218TheSkylineProblem {

	public static void main(String[] args) {
		No218TheSkylineProblem sol = new No218TheSkylineProblem();
		Parser parser = new Parser();
		Show show = new Show();
		String t = "[[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]\n" + 
				"[[1,2,4],[3,4,4]]\n" + 
				"[[1,6,2],[3,7,7]]\n" + 
				"[[1,7,8],[2,3,4]]\n" + 
				"[]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[][] buildings = parser.parseMatrix(s[i]);
			List<int[]> ans = sol.getSkyline(buildings);
			show.showListIntArray(ans);
		}
	}

	public List<int[]> getSkyline(int[][] buildings) {
        // --> time = O(n^2), where n = buildings.length
        
        // Thanks to:
        // Divide and Conquer
        // Merge methods:
        // https://www.geeksforgeeks.org/the-skyline-problem-using-divide-and-conquer-algorithm/
        
        if(buildings.length == 0) return new LinkedList<int[]>();
        
        LinkedList<int[]> criticals = DandC(buildings, 0, buildings.length-1);
        
        return criticals;
    }
    
    public LinkedList<int[]> DandC(int[][] buildings, int s, int e){
        // System.out.println("s: "+s+", e: "+e);
        
        LinkedList<int[]> ret = new LinkedList<int[]>();
        if(e < s) return ret;
        else if(s == e){
            int[] lefttop = new int[]{buildings[s][0], buildings[s][2]};
            int[] rightbot = new int[]{buildings[s][1], 0};
            ret.addLast(lefttop);
            ret.addLast(rightbot);
            return ret;
        }
        
        int m = s + (e-s)/2;
        LinkedList<int[]> l = DandC(buildings, s, m);
        LinkedList<int[]> r = DandC(buildings, m+1, e);
        
        if(l.size() == 0) return r;
        else if(r.size() == 0) return l;
        int h1 = 0, h2 = 0, h = 0;
        while(l.size() > 0 && r.size() > 0){
            int[] c = null;
            if(r.getFirst()[0] < l.getFirst()[0]){
                c = r.removeFirst();
                h2 = c[1];
            }else if(l.getFirst()[0] < r.getFirst()[0]){
                c = l.removeFirst();
                h1 = c[1];
            }else{
                c = l.removeFirst();
                h1 = c[1];
                c = r.removeFirst();
                h2 = c[1];
            }
            h = Math.max(h1, h2);
            if(ret.size() == 0 || ret.getLast()[1] != h) ret.addLast(new int[]{c[0], h});
        }
        
        while(l.size() > 0){
            int[] c = l.removeFirst();
            h1 = c[1];
            h = Math.max(h1, h2);
            if(ret.size() == 0 || ret.getLast()[1] != h) ret.addLast(new int[]{c[0], h});
        }
        
        while(r.size() > 0){
            int[] c = r.removeFirst();
            h2 = c[1];
            h = Math.max(h1, h2);
            if(ret.size() == 0 || ret.getLast()[1] != h) ret.addLast(new int[]{c[0], h});
        }
        
        return ret;
    }
    
    
    
	public List<int[]> getSkyline0(int[][] buildings) {
        // --> time = O(n^2), where n = buildings.length
        
        // Thanks to:
        // https://briangordon.github.io/2014/08/the-skyline-problem.html
        
        if(buildings.length == 0) return new LinkedList<int[]>();
        
        // find the height of all critical points
        int[][] criticals = new int[2*buildings.length][2];
        for(int i = 0; i < buildings.length; i++){
            criticals[2*i][0] = buildings[i][0];
            criticals[2*i+1][0] = buildings[i][1];
        }
        
        for(int[] b: buildings){
            for(int[] c: criticals){
                if(b[0] <= c[0] && c[0] < b[1]){
                    c[1] = Math.max(c[1], b[2]);
                }
            }
        }
        
        
        // Collect heights 
        
        Arrays.sort(criticals, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){ return a[0] - b[0]; }
        });
        
        List<int[]> ret = new LinkedList<int[]>();
        int[] last = new int[]{criticals[0][0], criticals[0][1]};
        ret.add(last);
        for(int i = 1; i < criticals.length; i++){
            if(criticals[i][1] == last[1]) continue;
            last = new int[]{criticals[i][0], criticals[i][1]};
            ret.add(last);
        }
        return ret;
    }
}
