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
	
	public boolean canTransform(String start, String end) {
		// --> time = O(n)
		
		// l denotes how many L we should meet later,
		// so end gives l++, start decrease l by 1 (l--).
		// when l < 0 -> false
		//
		// r denotes how many R we should meet later,
		// so start gives l++, end decrease l by 1 (l--).
		// when r < 0 -> false
		//
		// Moreover, if both r and l > 0, 
		// it means we are waiting L and R in the same time, 
		// which is not allowed!
		// eg. RL -> LR.
		//
		// l and r should be balanced in the end.
		
        if(start.length() != end.length()) return false;
        int l = 0, r = 0;
        for(int i = 0; i < start.length(); ++i) {
            if(start.charAt(i) == 'R') r++;
            if(start.charAt(i) == 'L') l--;
            if(end.charAt(i) == 'R') r--;
            if(end.charAt(i) == 'L') l++;
            if(l < 0 || r < 0 || l > 0 && r > 0) return false;
        }
        return l==0 && r==0;
    }

	private boolean canTransform0(String start, String end) {
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
