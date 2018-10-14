package DP;

import Util.Parser;

public class ExtraSameNoDice {

	public static void main(String[] args) {
		ExtraSameNoDice sol = new ExtraSameNoDice();
		Parser parser = new Parser();
		String t = "[2,1,3]\n"
				+ "[1,1,1,1]\n"
				+ "[1,2,3,4,5,6]\n"
				+ "[]\n"
				+ "[3]\n"
				+ "[4,6,2,5,2,4,3,4,4,1,4,5,5,6,1,1,4,5,1,5,5,1,1,3,4,4,2,1,4,5,7,1,4,6,3,4]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] A = parser.parseArray(s[i]);
			int ans = sol.minStepToSame(A);
			System.out.println(ans);
		}
	}

	private int minStepToSame(int[] A) {
		// --> O(n), where n = A.length
		
		// Intuition:
		// Find the steps convert to 1, 2, 3, 4, 5, 6 for all dices
		// And select the min one.
		
		int[] minStepsTo = new int[7];
		for(int e: A) {
			for(int i = 1; i < minStepsTo.length; i++) {
				minStepsTo[i] += i == e? 0: 7 - i == e? 2: 1;
			}
		}
		
		int ret = 0, minSteps = Integer.MAX_VALUE;
		for(int i = 1; i < minStepsTo.length; i++) {
			if(minStepsTo[i] < minSteps) {
				ret = i;
				minSteps = minStepsTo[i];
			}
		}
		System.out.println("side: " + ret);
		System.out.println("minSteps: " + minSteps);
		return minSteps;
	}

}
