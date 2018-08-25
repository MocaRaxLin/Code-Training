package BinarySearch;

import java.util.ArrayList;
import java.util.List;

public class No729MyCalendarI {

    List<int[]> events;
    public No729MyCalendarI() {
        events = new ArrayList<int[]>();
    }
    
    public boolean book(int start, int end) {
    	// -> O(logn) for each call
    	
    	// Binary search the next event at index idx,
    	// check overlap with A[idx] and A[idx-1]
    	
        int[] e = new int[]{start, end-1};
        int idx = searchIdxNextOfE(events, e);
        if(idx == -1) return false;
        if(idx < events.size() && overlap(e, events.get(idx))) return false;
        if(0 < idx && overlap(e, events.get(idx-1))) return false;
        events.add(idx, e);
        return true;
    }
    
    private int searchIdxNextOfE(List<int[]> events, int[] e){
        int i = 0, j = events.size();
        while(i < j){
            int m = i + (j-i)/2;
            if(events.get(m)[0] == e[0]) return -1;
            else if(events.get(m)[0] < e[0]) i = m + 1;
            else j = m;
        }
        return i;
    }
    
    private boolean overlap(int[] o, int[] e){
        return o[0] <= e[1] && e[0] <= o[1];
        // non overlap will be e[1] < o[0] || o[1] < e[0]
        // use de morgan law to convert!
    }
}
