package String;

public class No831MaskingPersonalInformation {

	public static void main(String[] args) {
		No831MaskingPersonalInformation sol = new No831MaskingPersonalInformation();
		String[] input = new String[] {
				"LeetCode@LeetCode.com", "A@qq.com", "AB@qq.com", "1(234)567-890", "86-(10)12345678", "(123)4567890",
				"+1 (333) 444-5678", "+91 (333) 444-5678", "+111 (333) 444-5678", "333 444 5678", "(333) 444-5678"
		};
		for(int i = 0 ; i < input.length; i++) {
			String ans = sol.maskPII(input[i]);
			System.out.println(ans);
		}

	}
	
	public String maskPII(String S) {
		// format problem think about Regexp
		// Regular Expression
		// -> use for string matching
		// -> use for replaceAll()
		// --> O(N), where N = S.length();
		
        if(S.indexOf('@') > 0){
            S = S.toLowerCase();
            StringBuilder sb = new StringBuilder();
            sb.append(S.charAt(0));
            sb.append("*****");
            sb.append(S.charAt(S.indexOf('@') - 1));
            sb.append('@');
            for(int i = S.indexOf('@') + 1; i < S.length(); i++) sb.append(S.charAt(i));
            return sb.toString();
        }else{
            String digits = S.replaceAll("\\D+", ""); //big D means not digit
            StringBuilder sb = new StringBuilder();
            int i = digits.length() - 1;
            for(int j = 0; j < 4; j++) sb.insert(0, digits.charAt(i--));
            sb.insert(0, "***-***-");
            if(digits.length() > 10){
                if(digits.length()%10 == 1) sb.insert(0, "+*-");
                else if(digits.length()%10 == 2) sb.insert(0, "+**-");
                else if(digits.length()%10 == 3) sb.insert(0, "+***-");
            }
            return sb.toString();
        }
    }
}
