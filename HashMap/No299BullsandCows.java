package HashMap;

public class No299BullsandCows {

	public static void main(String[] args) {
		No299BullsandCows sol = new No299BullsandCows();
		String t = "\"1807\"\n" + 
				"\"7810\"\n" + 
				"\"1123\"\n" + 
				"\"0111\"";
		t = t.replace("\"", "");
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			String secret = s[i];
			String guess = s[i+1];
			String ans = sol.getHint(secret, guess);
			System.out.println(ans);
		}
	}

	public String getHint(String secret, String guess) {
        int[] need = new int[10];
        int a = 0, b = 0;
        
        for(int i = 0; i < secret.length(); i++){
            int s = secret.charAt(i) - '0';
            int g = guess.charAt(i) - '0';
            if(s == g) a++;
            else{
                // need[i] < 0: i is needed.
                // need[i] > 0: i is over needed.
                if(need[s]++ < 0) b++;
                if(need[g]-- > 0) b++;
            }
        }
        
        return a+"A"+b+"B";
    }
	
	public String getHint2Pass(String secret, String guess) {
		// --> O(n), where n = length of secret
		
		// Intuition:
		// Check A and collect needed digits to freqency map
		// Run though B and check how many digits are needed but not in right positions.
		
		// Follow up 1 pass?
		
        int[] feq = new int[10];
        boolean[] used = new boolean[secret.length()];
        int a = 0;
        for(int i = 0; i < secret.length(); i++){
            if(secret.charAt(i) == guess.charAt(i)){
                a++;
                used[i] = true;
            }else{
                feq[secret.charAt(i) - '0']++;
            }
        }
        int b = 0;
        for(int i = 0; i < guess.length(); i++){
            if(used[i]) continue;
            if(feq[guess.charAt(i)-'0'] > 0){
                b++;
                feq[guess.charAt(i)-'0']--;
            }
        }
        return a+"A"+b+"B";
    }
}
