package ArrayMatrix;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class No731MyCalendarII {
	
	List<int[]> events;
    List<int[]> overlaps;
    
    public No731MyCalendarII() {
        events = new LinkedList<int[]>();
        overlaps = new LinkedList<int[]>();
    }
    
    public boolean book(int start, int end) {
    	// --> O(n) for each call, where n is the total number of events we tend to insert.
    	
        int[] e = new int[]{start, end-1};
        // Check new event overlaps our old overlapping regions
        Iterator<int[]> it = overlaps.iterator();
        while(it.hasNext()){
            int[] o = it.next();
            if(overlap(o, e) != null) return false;
        }
        
        // Generate new overlapping regions and insert the event
        it = events.iterator();
        while(it.hasNext()){
            int[] oldEvent = it.next();
            int[] newOverlap = overlap(e, oldEvent);
            if(newOverlap != null) overlaps.add(newOverlap);
        }
        events.add(e);
        return true;
    }
    
    private int[] overlap(int[] o, int[] e){
        if(e[1] < o[0] || o[1] < e[0]) return null;
        int i = Math.max(o[0], e[0]);
        int j = Math.min(o[1], e[1]);
        return new int[]{i, j};
    }

}
