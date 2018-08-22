package GreedyHeap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class No23MergekSortedLists {

	// 25ms
    public ListNode mergeKLists25(ListNode[] lists) {
        // --> O(knlogk), where k = lists.length, n = average of length of list
        PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(new Comparator<ListNode>() {
			@Override
			public int compare(ListNode a, ListNode b) {return a.val - b.val; }
		});
        
        for(int i = 0; i < lists.length; i++){
            if(lists[i] != null) pq.offer(lists[i]);
        }
        
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        
        while(pq.size() > 0){
            cur.next = pq.poll();
            if(cur.next.next != null) pq.offer(cur.next.next);
            cur = cur.next;
        }
        
        return dummy.next;
    }
    
    
    // 16ms
    public ListNode mergeKLists16(ListNode[] lists) {
        return partion(lists, 0, lists.length-1);
    }
    
    private ListNode partion(ListNode[] lists, int i, int j){
        if(i == j) return lists[i];
        else if(i > j) return null;
        else{
            int m = i + (j-i)/2;
            ListNode a = partion(lists, i, m);
            ListNode b = partion(lists,  m+1, j);
            return merge(a, b);
        }
    }
    
    private ListNode merge(ListNode a, ListNode b){
        if(a == null) return b;
        else if(b == null) return a;
        else{
            if(a.val < b.val){
                a.next = merge(a.next, b);
                return a;
            }else{
                b.next = merge(a, b.next);
                return b;
            }
        }
    }

}

class ListNode{
	int val;
	ListNode next;
	ListNode(int x) { val = x; }
}
