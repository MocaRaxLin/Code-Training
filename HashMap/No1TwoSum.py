class Solution(object):
    def twoSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        hashmap = {}
        for i, e in enumerate(nums):
            if e in hashmap:
                return hashmap[e], i
            else:
                hashmap[target - e] = i # this way is faster