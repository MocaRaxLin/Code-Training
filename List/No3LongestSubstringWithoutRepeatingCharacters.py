class Solution(object):
    def lengthOfLongestSubstring(self, s):
        """
        :type s: str
        :rtype: int
        """
        d = {}
        maxLen = 0
        count = 0
        start = 0
        for idx, c in enumerate(s):
            if c in d and d[c] >= start:
                count = idx - d[c]
                start = d[c]
            else:
                count+=1
                maxLen = max(maxLen, count)
            d[c] = idx
            
        return maxLen