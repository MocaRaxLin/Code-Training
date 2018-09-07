package List;

public class ListNode {
	int val;
	ListNode next;
	public ListNode() {}
	
	public ListNode(int v) {
		this.val = v;
	}
	
	public ListNode array2List(int[] A) {
		ListNode dummy = new ListNode(-1);
		ListNode cur = dummy;
		for(int i = 0 ; i < A.length; i++) {
			cur.next = new ListNode(A[i]);
			cur = cur.next;
		}
		return dummy.next;
	}
}
