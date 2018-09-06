package Graph;

import java.util.LinkedList;
import java.util.Queue;

import Util.Parser;

public class No841KeysandRooms {

	public static void main(String[] args) {
		No841KeysandRooms sol = new No841KeysandRooms();
		Parser parser = new Parser();
		String t = "[[1],[2],[3],[]]\n" + 
				"[[1,3],[3,0,1],[2],[0]]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[][] rooms = parser.parseMatrix(s[i]);
			boolean ans = sol.canVisitAllRooms(rooms);
			System.out.println(ans);
		}
	}

	public boolean canVisitAllRooms(int[][] rooms) {
		// --> O(V), where V is the number of keys in all rooms by using BFS
		
		// Intuition:
		// See keys, own it? Yes, ignore it. No, collect it.
		// Use BFS to travere each room.
		
        boolean[] pocket = new boolean[rooms.length];
        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(0);
        pocket[0] = true;
        while(q.size() > 0){
            int rId = q.poll();
            int[] keys = rooms[rId];
            int idx = 0;
            while(idx < keys.length) {
            	int key = keys[idx++];
                if(!pocket[key]){
                    pocket[key] = true;
                    q.offer(key);
                }
            }
        }
        for(boolean b: pocket){ if(!b) return false; }
        return true;
    }
}
