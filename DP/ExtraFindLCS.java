package DP;

public class ExtraFindLCS {

	public static void main(String[] args) {
		ExtraFindLCS sol = new ExtraFindLCS();
		String A = "delete";
		String B = "leet";
		String LCS = sol.getLCS(A, B);
		System.out.println(LCS);
	}

	private String getLCS(String A, String B) {
		int n = A.length();
        int m = B.length();
        //O(nm)
        int[][] dp = new int[n+1][m+1];
        for(int i = 1; i < dp.length; i++){
            for(int j = 1; j < dp[i].length; j++){
                if(A.charAt(i-1) == B.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }
        
        String[] LCS = new String[]{"", A, B};
        int[] curASCII = new int[]{0};
        findMaxLCS(dp, n, m, "", LCS, curASCII);
		return LCS[0];
	}

	private void findMaxLCS(int[][] dp, int i, int j, String stringBuilder, String[] LCS, int[] curASCII) {
		if(dp[i][j] == 0){
			LCS[0] = stringBuilder;
//			// the code below is to find LCS with highest sum of ASCII code
//            String newLCS = stringBuilder;
//            int newASCII = 0;
//            for(char c: newLCS.toCharArray()) newASCII+=c;
//            if(newASCII > curASCII[0]){
//                LCS[0] = newLCS;
//                curASCII[0] = newASCII;
//            }
            return;
        }
        if(LCS[1].charAt(i-1) == LCS[2].charAt(j-1)){
            stringBuilder = LCS[1].charAt(i-1) + stringBuilder;
            findMaxLCS(dp, i-1, j-1, stringBuilder, LCS, curASCII);
        }else{
            if(dp[i-1][j] == dp[i][j]) findMaxLCS(dp, i-1, j, stringBuilder, LCS, curASCII);
//         // the code below is to find LCS with highest sum of ASCII code
//            if(dp[i][j-1] == dp[i][j]) findMaxLCS(dp, i, j-1, stringBuilder, LCS, curASCII);
            else if(dp[i][j-1] == dp[i][j]) findMaxLCS(dp, i, j-1, stringBuilder, LCS, curASCII);
        } 
	}

}
