class Solution:
    def addTwoNumbers(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        dummyhead = ListNode(-1)
        cur = dummyhead
        carry = 0
        while l1 is not None or l2 is not None:
            s = carry
            if l1 is not None:
                s += l1.val
                l1 = l1.next
            if l2 is not None:
                s += l2.val
                l2 = l2.next
            cur.next = ListNode(s%10)
            carry = int(s/10)
            cur = cur.next
        if carry > 0:
            cur.next = ListNode(carry)
        return dummyhead.next
    
    