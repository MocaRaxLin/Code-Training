package String;

public class No831MaskingPersonalInformation {

	public static void main(String[] args) {
		No831MaskingPersonalInformation sol = new No831MaskingPersonalInformation();
		String[] input = new String[] {
				"LeetCode@LeetCode.com", "AB@qq.com", "1(234)567-890", "86-(10)12345678", "(123)4567890"
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
		
        if(S.contains("@")){
            String[] names = S.toLowerCase().split("@");
            return names[0].charAt(0) + "*****" + names[0].charAt(names[0].length() - 1) + "@" + names[1];
        }else{
            String digits = S.replaceAll("\\D+", ""); //UpperCase means not digit
            String ret = "***-***-" + digits.substring(digits.length() - 4);
            if(digits.length() > 10){
                String addOn = "+";
                for(int i = 10; i < digits.length(); i++) addOn += "*";
                ret = addOn + "-" + ret;
            }
            return ret;
        }
    }
}
