package GreedyHeap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import Util.Parser;
import Util.Show;

public class No406QueueReconstructionbyHeight {

	public static void main(String[] args) {
		No406QueueReconstructionbyHeight sol = new No406QueueReconstructionbyHeight();
		Parser parser = new Parser();
		Show show = new Show();
		String t = "[[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]\n" + 
				"[[4,2],[5,1],[7,0]]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[][] people = parser.parseMatrix(s[i]);
			int[][] ans = sol.reconstructQueue(people);
			show.showMatrix(ans);
		}
	}
	
	public int[][] reconstructQueue(int[][] people) {
		// --> O(nlogn), where n = people.length
		
		// Inspired by https://leetcode.com/problems/queue-reconstruction-by-height/discuss/89345/Easy-concept-with-PythonC++Java-Solution
		
		// Insert people by the order of height and if the hights are the same we order by ascending k (# of people whose height >= him)
		//
		// Then whenever we insert person to the list, 
		// just insert to position index at p[1],
		// because people in list have higher or same hieght to him.
		
        Arrays.sort(people, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                return a[0] == b[0]? a[1] - b[1]: b[0] - a[0];
            }
        });
        List<int[]> list = new ArrayList<int[]>();
        for(int[] p: people){
            list.add(p[1], p);
        }
        
        return list.toArray(new int[people.length][2]); // good list to array transform
    }
}
