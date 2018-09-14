package ArrayMatrix;

import java.util.LinkedList;
import java.util.List;

import Util.Parser;
import Util.Show;

public class No228SummaryRanges {

	public static void main(String[] args) {
		No228SummaryRanges sol = new No228SummaryRanges();
		Parser parser = new Parser();
		Show show = new Show();
		String t = "[0,1,2,4,5,7]\n" + 
				"[3,4,5,10,11,12]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] A = parser.parseArray(s[i]);
			List<String> ans = sol.summaryRanges(A);
			show.showList(ans, true);
		}
	}
	
	public List<String> summaryRanges(int[] A) {
        // --> O(n), where n = A.length
        
        // 1 pass and collect
        
        List<String> ret = new LinkedList<String>();
        if(A.length == 0) return ret;
        int base = A[0];
        int last = A[0];
        for(int i = 1; i < A.length; i++){
            if(A[i] - last == 1){
                last = A[i];
            }else{
                if(base == last) ret.add(""+base);
                else ret.add(base+"->"+last);
                base = A[i];
                last = A[i];
            }
        }
        if(base == last) ret.add(""+base);
        else ret.add(base+"->"+last);
        return ret;
    }

}
