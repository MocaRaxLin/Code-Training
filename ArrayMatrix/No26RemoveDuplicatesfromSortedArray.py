class Solution(object):
    def removeDuplicates(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """

        # --> O(n), where n = len(nums)

        if len(nums) == 0:
            return 0
        size = 1
        for i in range(1, len(nums)):
            if nums[size - 1] != nums[i]:
                nums[size] = nums[i]
                size += 1
        return size