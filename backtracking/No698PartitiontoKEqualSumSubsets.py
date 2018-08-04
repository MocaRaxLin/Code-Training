class Solution(object):
    def fillUpBucket(self, A, fromIdx, N, used, space):
        if space == 0:
            return True
        
        for i in range(fromIdx, N):
            if not used[i] and space >= A[i]:
                used[i] = True
                if self.fillUpBucket(A, i+1, N, used, space-A[i]):
                    return True
                used[i] = False
        
        return False
    
    def canPartitionKSubsets(self, A, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: bool
        """
        if k == 1: return True
        n = len(A)
        sumA = sum(A)
        if n < k or sumA % k != 0: return False
        A.sort(reverse = True)
        limit = sumA/k
        
        # ckeck if our biggest A[i] over the limit
        if A[0] > limit: return False
    
        used = [False] * n
        
        # Fill up buckets one by one, 
        # if we cannot fill a bucket to limit,
        # then we don't need to try the rest of them
        for i in range(n):
            if not used[i] and not self.fillUpBucket(A, i, n, used, limit):
                    return False
        return True
        
        