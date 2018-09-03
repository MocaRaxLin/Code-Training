package String;

public class No777SwapAdjacentinLRString {

	public static void main(String[] args) {
		No777SwapAdjacentinLRString sol = new No777SwapAdjacentinLRString();
		String t = "\"X\"\n" + 
				"\"L\"\n" + 
				"\"RXXLRXRXL\"\n" + 
				"\"XRLXXRRLX\"\n" + 
				"\"RXXLRXRXL\"\n" + 
				"\"XRLXXXRRL\"\n" + 
				"\"XLXR\"\n" + 
				"\"XLRX\"\n" + 
				"\"RL\"a\n" + 
				"\"LR\"\n" + 
				"\"XXXXXXRXXXXXXXXLXXXXXXXXXLXXXXXXXLXXXXRXXXXXXXXXXX\"\n" + 
				"\"XXXXXXXXRXXXXXXXLXXXXXLXXXXXXXXLXXXXXXXXXXXRXXXXXX\"\n" +
				"\"RLX\"\n" + 
				"\"XLR\"";
		t = t.replace("\"", "");
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			String start = s[i];
			String end = s[i+1];
			boolean ans = sol.canTransform(start, end);
			System.out.println(ans);
		}
	}

	private boolean canTransform(String start, String end) {
		// --> O(n)
		
		// Intuition:
		// All indices of R in end are greater or equal to ones in start
		// eg.  start = RXXXXXX
		//        end = XXXXRXX
		// Indices of L are the opposites.
		
		
        if(start.length() != end.length()) return false;
        
        // check R
        int i = 0, j = 0;
        while(i < start.length()){
            if(start.charAt(i) == 'R'){
                j = i > j? i : j;
                while(j < end.length() && end.charAt(j) != 'R'){
                    if(end.charAt(j) == 'L') return false;
                    else if(end.charAt(j) == 'X') j++;
                }
                if(j == end.length()) return false;
                j++;
            }
            i++;
        }
        
        
        // check L
        i = 0;
        j = 0;
        while(j < end.length()){
            if(end.charAt(j) == 'L'){
                i = j > i? j: i;
                while(i < start.length() && start.charAt(i) != 'L'){
                    if(start.charAt(i) == 'R') return false;
                    else if(start.charAt(i) == 'X') i++;
                }
                if(i == start.length()) return false;
                i++;
            }
            j++;
        }
        return true;
    }
}
