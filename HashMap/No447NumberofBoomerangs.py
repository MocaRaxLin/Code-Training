class Solution(object):
    def numberOfBoomerangs(self, points):
        """
        :type points: List[List[int]]
        :rtype: int
        """
        
        # Intuition:
        # Fix point i and iterate other points as j.
        # Collect frequency of points that have the same distance in square.
        # Compute and sum up permuation
        # eg. (0,0), (1,0), (0,1), (-1,0), (0,-1)
        # fix (0,0) -> {1:4} -> 4*3
        # fix (1,0) -> {1:1, 2:2, 4:1} ->2*1
        # .
        # .
        # .
        # -> 12 + 2 + 2 + 2 + 2 = 20 :)


        ret = 0
        pSize = len(points)
        for i in range(0, pSize):
            dic = {}
            for j in range(0, pSize):
                if j == i:
                    continue
                dx = points[j][0] - points[i][0]
                dy = points[j][1] - points[i][1]
                dis = dx**2 + dy**2
                freq = dic.get(dis, 0)
                dic[dis] = freq + 1
            
            for key, value in dic.items():
                if value > 1:
                    ret += value*(value-1)
        
        return ret
        