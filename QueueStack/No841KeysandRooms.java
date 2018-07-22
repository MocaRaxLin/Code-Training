package QueueStack;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class No841KeysandRooms {

	public static void main(String[] args) {
		No841KeysandRooms sol = new No841KeysandRooms();
		
		List<List<List<Integer>>> input = new LinkedList<List<List<Integer>>>();
		
		// [[1],[2],[3],[]]
		List<List<Integer>> list = new LinkedList<List<Integer>>();
		List<Integer> keySet = new LinkedList<Integer>();
		keySet.add(1); list.add(keySet);
		keySet = new LinkedList<Integer>();
		keySet.add(2); list.add(keySet);
		keySet = new LinkedList<Integer>();
		keySet.add(3); list.add(keySet);
		keySet = new LinkedList<Integer>();
		list.add(keySet);
		input.add(list);
		
		// [[1,3],[3,0,1],[2],[0]]
		list = new LinkedList<List<Integer>>();
		keySet = new LinkedList<Integer>();
		keySet.add(1); keySet.add(3); list.add(keySet);
		keySet = new LinkedList<Integer>();
		keySet.add(3); keySet.add(0); keySet.add(1); list.add(keySet);
		keySet = new LinkedList<Integer>();
		keySet.add(2); list.add(keySet);
		keySet = new LinkedList<Integer>();
		keySet.add(0); list.add(keySet);
		input.add(list);
		
		// [[]]
		list = new LinkedList<List<Integer>>();
		keySet = new LinkedList<Integer>();
		list.add(keySet);
		input.add(list);
		
		for(int i = 0; i < input.size(); i++) {
			boolean ans = sol.canVisitAllRooms(input.get(i));
			System.out.println(ans);
		}
	}
	
	public boolean canVisitAllRooms(List<List<Integer>> rooms) {
		// --> O(n), where n = rooms.size(),
		// because we only need try to fill up value True to all indices in array isOpened.
		
		// We open room i and get keys room.get(i).
		// We use those key to open others(rooms), and get the keys from those rooms again.
		// Repeat the above process.
		// This problem can be solve by stack, but here we use queue.
		
        boolean[] isOpened = new boolean[rooms.size()];
        Queue<List<Integer>> queue = new LinkedList<List<Integer>>();
        isOpened[0] = true;
        queue.offer(rooms.get(0));
        while(!queue.isEmpty()){
            List<Integer> keys = queue.poll();
            for(int i = 0; i < keys.size(); i++){
                int key = keys.get(i);
                if(isOpened[key]) continue;
                else{
                    isOpened[key] = true;
                    queue.offer(rooms.get(key));
                }
            }
        }
        for(int i = 0; i < isOpened.length; i++)
            if(!isOpened[i]) return false;
        return true;
    }
}
