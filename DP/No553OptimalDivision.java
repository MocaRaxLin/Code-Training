package DP;

public class No553OptimalDivision {

	public static void main(String[] args) {
		No553OptimalDivision sol = new No553OptimalDivision();
		int[][] a = new int[][] {
			{1000,100,10,2}, 
			{23,4,234,324,44},
			{34,5,22},
			{2,3,4,5},
			{3,2,5,92,657,10000,8,4,45,753,34,46,4,6}
		};
		for(int i = 0; i < a.length; i++) {
			String ans = sol.optimalDivision(a[i]);
			System.out.println(ans);
		}
		
	}
	
	public String optimalDivision(int[] nums) {
        // --> O(n), where n = nums.length
		
		// By math!
		
		// Eg1. if we want a/b/c/d to maximum, b/c/d must be minimum.
		// 2 cases shows up.
		// One is b/(c/d) = b * (d/c), the other is b/c/d = b * (1/c*d)
		// Because d > 1, the second one always smaller than the first one.
		// Thus the answer is a/(b/c/d).
		
		// Eg2. if we want a/b/c/d/e to maximum, b/c/d/e must be minimum.
		// More cases show up, but the smallest combination is b/c/d/e = b * (1/c*d*e)
		// Thus the answer is a/(b/c/d/e).
		// ... the rest examples are the same.
		
        if(nums.length == 1) return "" + nums[0];
        if(nums.length == 2) return nums[0] + "/" + nums[1];
        String ret = nums[0] + "/(";
        for(int i = 1; i < nums.length; i++){
            ret += i == 1 ? nums[i] : "/" + nums[i];
        }
        ret += ")";
        return ret;
    }
	
	static public String optimalDivision0(int[] nums) {
        // time --> O(n^3), space -> O(n^3), where n = nums.length
		
		// It looks like Chain matrix multiplication.
		// I know it is weird, but it is to me.
		// Use DP
		
		// To get maximum value, we divide maximum value by minimum value.
		// To get minimum value, we divide minimum value by maximum value.
		// minDp[i, j] = min( minDP[i, k] / maxDP[k+1, j]), for i <= k < j
		// maxDp[i, j] = max( maxDP[i, k] / minDP[k+1, j]), for i <= k < j
		// use solMinDp and solMaxDp to store answer string
		
        float[][] minDp = new float[nums.length][nums.length];
        float[][] maxDp = new float[nums.length][nums.length];
        String[][] solMinDp = new String[nums.length][nums.length];
        String[][] solMaxDp = new String[nums.length][nums.length];
        
        // basic cases -> O(n)
        for(int i = 0; i < nums.length; i++){
            minDp[i][i] = nums[i];
            maxDp[i][i] = nums[i];
            solMinDp[i][i] = "" + nums[i];
            solMaxDp[i][i] = "" + nums[i];
        }
        
        // fill up table diagonally -> O(n^3)
        for(int len = 1; len < nums.length; len++){
            for(int i = 0; i + len < nums.length; i++){
                int j = i + len;
                float min = Float.MAX_VALUE;
                float max = Float.MIN_VALUE;
                for(int k = i; k < j; k++){
                    float minVal = minDp[i][k]/maxDp[k+1][j];
                    if(minVal < min){
                        min = minVal;
                        // string concatenation -> O(n) or O(1) ?
                        solMinDp[i][j] = k + 1 == j ? solMinDp[i][k]+"/"+solMinDp[k+1][j]
                            : solMinDp[i][k]+"/("+solMinDp[k+1][j]+")" ;
                    }
                    float maxVal = maxDp[i][k]/minDp[k+1][j];
                    if(max < maxVal){
                        max = maxVal;
                        solMaxDp[i][j] = k + 1 == j ? solMaxDp[i][k]+"/"+solMinDp[k+1][j]
                            : solMaxDp[i][k]+"/("+solMinDp[k+1][j]+")";
                    }
                }
                minDp[i][j] = min;
                maxDp[i][j] = max;
            }
        }
        
        return solMaxDp[0][nums.length - 1];
    }

}
