package ArrayMatrix;

import java.util.TreeMap;

public class No732MyCalendarIII {
	// Thanks to:
    // https://leetcode.com/problems/my-calendar-iii/discuss/109556/JavaC++-Clean-Code
    
    // This is to find the maximum number of concurrent ongoing event at any time.
    
    // smart !
    
    private TreeMap<Integer, Integer> timeline = new TreeMap<>();
    public int book(int s, int e) {
        // --> O(2logn+n)
        timeline.put(s, timeline.getOrDefault(s, 0) + 1); // 1 new event will be starting at [s]
        timeline.put(e, timeline.getOrDefault(e, 0) - 1); // 1 new event will be ending at [e];
        int ongoing = 0, k = 0;
        for (int v : timeline.values())
            k = Math.max(k, ongoing += v);
        return k;
    }

}
