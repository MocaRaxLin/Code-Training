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
	
	public String pushDominoes(String dominoes) {
		// --> O(N), where N = dominoes.length()
		// Scan and find dot blocks like "A....B", where A, B = {' ', 'L', 'R'}.
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
