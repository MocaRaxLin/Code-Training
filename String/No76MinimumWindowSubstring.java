package String;

public class No76MinimumWindowSubstring {

	public static void main(String[] args) {
		No76MinimumWindowSubstring sol = new No76MinimumWindowSubstring();
		String t = "\"ADOBECODEBANC\"\n" + 
				"\"ABC\"";
		t = t.replaceAll("\"", "");
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			String ans = sol.minWindow(s[i], s[i+1]);
			System.out.println(ans);
		}
	}

    public String minWindow(String s, String t) {
        // --> O(n), it takes linear time
        
        // window sliding template for substring problem
        // Brilliant method hard to figure out, so remember it!
        
        // 1. Count needed chars with ASCII map, and keep size of chars we need.
        // 2. Scan the string s with start pointer i and end pointer j. (two pointers)
        // 3. j goes first. If map[s[j]] > 0, then take this char i.e. count--, map[s[j]]--.
        //    otherwise just map[s[j]]--. (record this char by a negative value)
        // 4. We take all chars (count is 0) -> update head = i and len = j - i.
        // 5. Check if head is a char in t by testing map[s.charAt(i)] == 0? Yes then count++.
        //    - because if head in t, map[head] must > 0 in the beggining
        // 6. Release the head char s[i] i.e. map[s.charAt(i)]++, i++
        // 7. if count still = 0, then go to 4 again.
        //    - update len, head.
        //    - check head in t? Yes then count++.
        //    - release head to map, 
        
        int[] map = new int[128]; // ASCII size of 128 [00:7F]
        for(char c: t.toCharArray()) map[c]++;
        int count = t.length();
        int i = 0, j = 0, len = Integer.MAX_VALUE, head = 0;
        while(j < s.length()){
            if(map[s.charAt(j++)]-- > 0) count--;
            while(count == 0){
                if(j-i<len) len = j - (head = i);
                if(map[s.charAt(i++)]++ == 0) count++; 
            }
        }
        return len == Integer.MAX_VALUE? "": s.substring(head, head+len);
    }
}
