package ArrayMatrix;


import Util.Parser;

public class ExtraMaximumDiffInArray {

	public static void main(String[] args) {
		ExtraMaximumDiffInArray sol = new ExtraMaximumDiffInArray();
		Parser parser = new Parser();
		String t = "[1,2,-3,0]\n"
				+ "[0,1,2,3,6,4]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] A = parser.parseArray(s[i]);
			int ans = sol.maxDiffInArray(A);
			System.out.println(ans);
		}
	}

	private int maxDiffInArray(int[] A) {
		// --> O(n)

		int maxDiff = -2000001; // -10^6 -10^6
		int minEle = 1000000; //e : [-10^6: 10^6]
		for(int e: A) {
			//System.out.println("e: "+ e+ ", maxDiff: "+maxDiff+", minele: "+minEle);
			if(e%2 == 1 || e%2 == -1) minEle = Math.min(minEle, e);
			else maxDiff = Math.max(maxDiff, e - minEle);
		}
		return maxDiff;
	}

}
