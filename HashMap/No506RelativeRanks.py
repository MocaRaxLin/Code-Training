class Solution(object):
    def findRelativeRanks(self, nums):
        """
        :type nums: List[int]
        :rtype: List[str]
        """
        
        # Use score as key to store athletes' ranks
        
        A = sorted(nums, reverse = True)
        m = {0:"Gold Medal", 1:"Silver Medal", 2:"Bronze Medal"}
        dic = {}
        for i in range(0, min(len(A), 3)):
            dic[A[i]] = m[i]
        for i in range(3, len(A)):
            dic[A[i]] = str(i+1)
        
        ret = []
        for e in nums:
            ret.append(dic[e])
        return ret