package List;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class No56MergeIntervals {

	class Interval {
		int start;
		 int end;
		 Interval() { start = 0; end = 0; }
		 Interval(int s, int e) { start = s; end = e; }
	}

	public List<Interval> merge(List<Interval> intervals) {
		// --> O(nlog), where n = intervals.size().
		
		// sort ans merge with the last
		
        List<Interval> list = new ArrayList<Interval>();
        Collections.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval i, Interval j){ return i.start - j.start; }
        });
        
        Iterator<Interval> it = intervals.iterator();
        if(!it.hasNext()) return list;
        list.add(it.next());
        while(it.hasNext()){
            Interval itv = it.next();
            Interval last = list.get(list.size() - 1);
            if(last.end < itv.start) list.add(itv);
            else last.end = last.end < itv.end? itv.end: last.end;
        }
        return list;
    }
}
