class Solution(object):
        
    def longestPalindrome(self, s):
        """
        :type s: str
        :rtype: str
        """

        # --> O(n^2)
        # -> O(n) if we use Manacher's Algorithm
        # ref: https://www.youtube.com/watch?v=nbTSfrEfo6M
        
        start = 0
        maxLen = 1
        sLen = len(s)
        for idx in range(sLen):
            
            i = idx - 1
            j = idx + 1
            while 0 <= i and j < sLen and s[i] == s[j]:
                if j - i + 1 > maxLen:
                    maxLen = j - i + 1
                    start = i
                i -= 1
                j += 1

                    
            i = idx
            j = idx + 1
            while 0 <= i and j < sLen and s[i] == s[j]:
                if j - i + 1 > maxLen:
                    maxLen = j - i + 1
                    start = i
                i -= 1
                j += 1
            
        return s[start:start+maxLen]
        