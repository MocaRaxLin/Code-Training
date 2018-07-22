package DP;

public class No808SoupServings {

	public static void main(String[] args) {
		No808SoupServings sol = new No808SoupServings();
		
		// Input constraints
		// -> 0 <= N <= 10^9.
		// -> Answers within 10^-5 of the true value will be accepted as correct.
		int[] input = new int[] {50,
				34,
				436,
				123,
				100000000,
				0,
				99999999,
				500,
				800,
				2000,
				4000,
				10000
				};
		for(int ele: input) {
			double ans = sol.soupServings(ele);
			System.out.println(ans);
		}
	}
	
	public double soupServings(int N) {
		// time --> O(N^2), space --> O(N^2)
		// if N >= 10000 both of time and space are O(1)
		
		// Intuition:
		// the answer of [A,B] depends on [A-100,B], [A-75,B-25], [A-50,B-50], [A-25,B-75]
		// First, to save memory for DP, we use 25ml as a new unit.
		// The dependency is converted to [A-4,B], [A-3,B-1], [A-2,B-2], [A-1,B-3]
		
		// So let dp[i,j] denotes the sum of probabilities of "A used up first"
		// and "half of both A and B used up at the same time"
		// when we have i ml of A and j ml of B.
		//
		// Recursive Function:
		// dp[i,j] = 0.25 * (dp[i-4,j] + dp[i-3,j-1] + dp[i-2,j-2] + dp[i-1,j-3])
		// if any of i - d or j - d < 0, just use 0 as indices
		//
		// Basic cases
		// dp[0,0] = 0.5, dp[A,0] = 0 for A > 0, dp[0,B] = 1 for B > 0.
		
		// How about the memory usage?
		// N could be very large to 10^9, and (10^18)/25 hits memory limit definitely.
		// More Analysis!
		// We know each time we take either [A-4,B], [A-3,B-1], [A-2,B-2], [A-1,B-3]
		// Look at them carefully.
		// It implies we take 1 unit from A first, 
		// then "evenly" take A and B [A-3,B], [A-2,B-1], [A-1,B-2], [A,B-3].
		// So, when we iterate roughly N/2 times, A is empty first.
		// When N is very large this almost happens,
		// so if the probability > 99.9999% just output 1.0
		// Here we found the N is 10000.
		
		if(N >= 10000) return 1.0;
        int n = N/25 + (N%25 == 0? 0: 1);
        double[][] dp = new double[n+1][n+1];
        dp[0][0] = 0.5;
        //dp[A][B]
        for(int i = 1; i <= n; i++) dp[0][i] = 1.0;
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                int a, b;
                a = i - 4 >= 0? i - 4: 0;
                b = j;
                dp[i][j] += dp[a][b];
                a = i - 3 >= 0? i - 3: 0;
                b = j - 1 >= 0? j - 1: 0;
                dp[i][j] += dp[a][b];
                a = i - 2 >= 0? i - 2: 0;
                b = j - 2 >= 0? j - 2: 0;
                dp[i][j] += dp[a][b];
                a = i - 1 >= 0? i - 1 :0;
                b = j - 3 >= 0? j - 3 :0;
                dp[i][j] += dp[a][b];
                dp[i][j]*=0.25;
            }
        }
        return dp[n][n];
    }

}
