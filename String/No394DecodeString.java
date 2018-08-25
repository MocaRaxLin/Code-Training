package String;

public class No394DecodeString {

	public static void main(String[] args) {
		No394DecodeString sol = new No394DecodeString();
		String t = "\"3[a]2[bc]\"\n" + 
				"\"3[a2[c]]\"\n" + 
				"\"\"\n" + 
				"\"2[abc]3[cd]ef\"\n" + 
				"\"2[3[4[ab]c]]\"\n" + 
				"\"11[a13[b]]\"\n" + 
				"\"2[%]6[&4[*(]]\"";
		t = t.replace("\"", "");
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			String ans = sol.decodeString(s[i]);
			System.out.println(ans);
		}
	}

	public String decodeString(String s) {
		String[] line = new String[]{s};
        int[] p = new int[]{0};
        return helper(line, p);
	}
	
    private String helper(String[] s, int[] p){
    	// --> O(n), where n is the length of decoded string
    	
    	// Use global pointer to indicate the current char
    	// -> face char, then just append it.
    	// -> face digits, then compose the freq of inner string and
    	//    call recursion to dive into the inner part in the rest of string.
    	// -> return when pointer hit a ']' or the end
    	
        StringBuilder sb = new StringBuilder();
        while(p[0] < s[0].length() && s[0].charAt(p[0]) != ']'){
            if(Character.isDigit(s[0].charAt(p[0]))){
                int freq = 0;
                while(p[0] < s[0].length() && Character.isDigit(s[0].charAt(p[0]))){
                    freq = freq * 10 + s[0].charAt(p[0]++) - '0';
                }
                p[0]++; // '['
                String inner = helper(s, p);
                p[0]++; // ']'
                
                while(freq-->0) sb.append(inner);
                
            }else{
                sb.append(s[0].charAt(p[0]++));
            }
        }
        return sb.toString();
    }

}
