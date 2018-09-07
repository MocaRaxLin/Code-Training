package List;

import java.util.HashSet;
import java.util.Set;

import Util.Parser;

public class No817LinkedListComponents {

	public static void main(String[] args) {
		No817LinkedListComponents sol = new No817LinkedListComponents();
		Parser parser = new Parser();
		ListNode ln = new ListNode();
		String t = "[0,1,2,3]\n" + 
				"[0,1,3]\n" + 
				"[0,1,2,3,4]\n" + 
				"[0,3,1,4]\n" + 
				"[0,1,2,3,4]\n" + 
				"[0,2,4]\n" + 
				"[0]\n" + 
				"[]";
		String[] s = t.split("\n");
		for(int i = 0 ; i < s.length; i +=2) {
			int[] A = parser.parseArray(s[i]);
			ListNode head = ln.array2List(A);
			int[] G = parser.parseArray(s[i+1]);
			int ans = sol.numComponents(head, G);
			System.out.println(ans);
		}
	}

	public int numComponents(ListNode head, int[] G) {
        Set<Integer> set = new HashSet<Integer>();
        for(int g: G) set.add(g);
        
        int ret = 0;
        while(head != null){
            // only count the number of the component end
            if(set.contains(head.val) && (head.next == null || !set.contains(head.next.val) ) ) ret++; 
            head = head.next;
        }
        return ret;
    }
}
