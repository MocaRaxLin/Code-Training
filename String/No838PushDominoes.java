package String;

public class No838PushDominoes {

	public static void main(String[] args) {
		No838PushDominoes sol = new No838PushDominoes();
		
		String[] S = new String[] {
				".L.R...LR..L..", 
				"..", "L.", "R.", ".L", ".R",
				"L..L", "R..R", "R...L", "R..L",
				"R", ".", "", "L.R..L...LLR.L..RL....R...L"
		};
		for(String s : S) {
			String ans = sol.pushDominoes(s);
			System.out.println(ans);
		}
	}
	
	public String pushDominoes(String D) {
        // --> O(n), where n = D.length()
        
        // Thanks to:
        // https://leetcode.com/problems/push-dominoes/discuss/132332/C++JavaPython-Two-Pointers
        
		// Intution:
		// Use two pointer i j to frame region X...X
		// L...L -> LLLLL; L...R -> remain same; ... etc.
		// Don't forget corner cases eg. i = -1 or j = D.length()
		
        if(D.length() == 0) return "";
        char[] ca = D.toCharArray();
        int i = -1, j = 0;
        while(i < ca.length && j < ca.length){
            while(j < ca.length && ca[j] == '.') j++;
            if(j - i > 0) fillLR(ca, i, j);
            i = j;
            j++;
        }
        return new String(ca);
    }
    
    private void fillLR(char[] ca, int i, int j){
        if(i == -1 && j == ca.length) return;
        if(i == -1){
            if(ca[j] == 'L') while(i++<j) ca[i] = 'L';
        }else if(j == ca.length){
            if(ca[i] == 'R') while(i<j--) ca[j] = 'R';
        }else if(ca[i] == ca[j]){
            while(i++ < j) ca[i] = ca[j];
        }else if(ca[i] == 'R' && ca[j] == 'L'){
            while(i<j){
                ca[i++] = 'R';
                ca[j--] = 'L';
            }
        }
    }
    
    
	public String pushDominoes0(String dominoes) {
		// --> O(N), where N = dominoes.length()
		
		// Scan and find dot blocks like "A....B", where A, B = {' ', 'L', 'R'}.
		//
		// There are 9 possible scenarios.
		// A\B  ' '  L  R
		// ' '   .   L  .
		//  L    .   L  .
		//  R    R   C  R   
		// Where C means confrontation like R...L -> RR.LL 
		// we use if-else statement to address these 9 scenarios.
		
		// NOTE:
		// Try not to use String.substring, because it is time consuming,
		// Whenever we call substring, it takes O(n) to process, where n is input length.
		// Use Char Array instead! We change a char directly and it take constant time :)
		
        char[] S = dominoes.toCharArray();
        for(int i = 0; i < S.length; i++){
            char c = S[i];
            if(c == '.'){
                int s = i;
                while(i < S.length && S[i] == '.') i++;
                i--;
                int e = i;
                char left = s - 1 < 0 ? ' ' : S[s - 1];
                char right = e + 1 >= S.length ? ' ' : S[e + 1];
                if(left == ' ' || left == 'L'){
                    if(right == 'L'){
                        for(int j = s; j <= e; j++) S[j] = 'L';
                    }
                }else{
                    if(right == ' ' || right == 'R'){
                        for(int j = s; j <= e; j++) S[j] = 'R';
                    }else{
                        while(s < e){
                            S[s] = 'R';
                            S[e] = 'L';
                            s++;
                            e--;
                        }
                    }
                }
            }
        }
        return new String(S);
    }
}
