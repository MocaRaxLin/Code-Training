package DP;

public class No712MinimumASCIIDeleteSumforTwoStrings {

	public static void main(String[] args) {
		No712MinimumASCIIDeleteSumforTwoStrings sol = new No712MinimumASCIIDeleteSumforTwoStrings();
		
		String testcase = "sea\n"+
				"eat\n"+
				"delete\n"+
				"leet\n"+
				"r\n"+
				"e\n"+
				"e\n"+
				"e\n"+
				"igijekdtywibepwonjbwykkqmrgmtybwhwjiqudxmnniskqjfbkpcxukrablqmwjndlhblxflgehddrvwfacarwkcpmcfqnajqfxyqwiugztocqzuikamtvmbjrypfqvzqiwooewpzcpwhdejmuahqtukistxgfafrymoaodtluaexucnndlnpeszdfsvfofdylcicrrevjggasrgdhwdgjwcchyanodmzmuqeupnpnsmdkcfszznklqjhjqaboikughrnxxggbfyjriuvdsusvmhiaszicfa\n"+
				"ikhuivqorirphlzqgcruwirpewbjgrjtugwpnkbrdfufjsmgzzjespzdcdjcoioaqybciofdzbdieegetnogoibbwfielwungehetanktjqjrddkrnsxvdmehaeyrpzxrxkhlepdgpwhgpnaatkzbxbnopecfkxoekcdntjyrmmvppcxcgquhomcsltiqzqzmkloomvfayxhawlyqxnsbyskjtzxiyrsaobbnjpgzmetpqvscyycutdkpjpzfokvi";
		String[] s = testcase.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int ans = sol.minimumDeleteSum(s[i], s[i+1]);
			System.out.println(ans);
		}
		
	}

    public int minimumDeleteSum(String A, String B) {
    	// --> time O(nm), where n = A.length(), m = B.length()
    	
    	// Intuition:
    	// Think about Longest Common Subsequence, this question is the opposite.
    	//
    	// Let dp[i][j] denotes the minimum sum of deleted ASCII code of A[0:i-1] and B[0:j-1]
    	// if A[i-1] == B[j-1], then dp[i][j] = dp[i-1][j-1]
    	// if A[i-1] != B[j-1], then we must delete either of them.
    	// That is the minimum of dp[i-1][j] + A[i-1] and dp[i][j-1] + B[j-1] will be dp[i][j]
    	//
    	// basic case:
    	// dp[i][0] -> SumOfASCII(A[0:i-1])
    	// dp[0][i] -> SumOfASCII(B[0:i-1])
    	
        int n = A.length();
        int m = B.length();
        int[][] dp = new int[n+1][m+1];
        for(int i = 1; i < dp.length; i++) dp[i][0] = dp[i-1][0] + A.charAt(i-1);
        for(int i = 1; i < dp[0].length; i++) dp[0][i] = dp[0][i-1] + B.charAt(i-1);
        for(int i = 1; i < dp.length; i++){
            for(int j = 1; j < dp[i].length; j++){
                if(A.charAt(i-1) == B.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.min(dp[i][j-1] + B.charAt(j-1), dp[i-1][j] + A.charAt(i-1));
                }
            }
        }
        return dp[n][m];
    }

}
