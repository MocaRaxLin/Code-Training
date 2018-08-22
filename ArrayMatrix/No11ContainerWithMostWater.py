class Solution(object):
    def maxArea(self, height):
        """
        :type height: List[int]
        :rtype: int
        """
        
        # --> O(n)
        
        ret, i, j = 0, 0, len(height) - 1
        
        while i < j:
            
            h = min(height[i], height[j])
            ret = max(ret, h * (j - i))
            
            if height[i] < height[j]:
                i += 1
            elif height[i] > height[j]:
                j -= 1
            else:
                i += 1
                j -= 1
        
        return ret