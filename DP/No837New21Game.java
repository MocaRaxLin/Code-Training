package DP;

public class No837New21Game {

	public static void main(String[] args) {
		No837New21Game sol = new No837New21Game();
		
		int[] N = new int[] {1, 0, 1, 4, 21};
		int[] K = new int[] {1, 0, 0, 3, 17};
		int[] W = new int[] {0, 1, 1, 4, 10};
		for(int i = 0; i < N.length; i++) {
			double ans = sol.new21Game(N[i], K[i], W[i]);
			System.out.println(ans);
		}
	}
	
	public double new21Game(int N, int K, int W) {
		// time --> O(K+W)
		// If current points < K, then draw to get [1 ~ W] points, possibility of having N or less points.
		// eg. N = 21, K = 17, W = 10;
		// current points:  17 18 19 20 21 22 23 24 25 26
		// possibility < N:  1  1  1  1  1  0  0  0  0  0
		// If current points is 16, then the next draw will make points to [17-26].
		// Therefore, P[16] = Sum(P[17] ~ P[26])/W
		// Similarly, P[15] = Sum(P[16] ~ P[25])/W
		// P[14] = Sum(P[15] ~ P[24])/W
		// .
		// .
		// . Until we get P[0], which means probability of having less or equal to 17 points,
		// when we have 0 point as the game starts.
		// This is a typical dynamic programming problem
		
		double[] P = new double[K+W+1];
        double probability = 0;
        for(int i = K; i <= N; i++){
            probability++;
            P[i] = 1;
        }
        probability = probability /= W;
		for(int b = K - 1; b >= 0; b--) {
            //System.out.println(probability);
            P[b] = probability;
            probability = P[b] + (P[b] - P[b+W])/W;
		}
        return P[0];
    }
}
