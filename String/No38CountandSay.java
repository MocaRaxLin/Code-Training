package String;

public class No38CountandSay {

	public static void main(String[] args) {
		No38CountandSay sol = new No38CountandSay();
		// input cannot be too large
		String t = "1\n" + 
				"2\n" + 
				"3\n" + 
				"4\n" + 
				"5\n" + 
				"6\n" + 
				"7\n" + 
				"8\n" + 
				"9\n" + 
				"10";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int n = Integer.parseInt(s[i]);
			String ans = sol.countAndSay(n);
			System.out.println(ans);
		}
	}
	
    public String countAndSay(int n) {
        String s = "1";
        for(int i = 2; i <= n; i++){
            StringBuilder sb = new StringBuilder();
            char[] ca = s.toCharArray();
            
            char last = ca[0];
            int count = 1;
            for(int j = 1; j < ca.length; j++){
                if(last == ca[j]){
                    count++;
                }else{
                    sb.append(count);
                    sb.append(last);
                    last = ca[j];
                    count = 1;
                }
            }
            sb.append(count);
            sb.append(last);
            
            s = sb.toString();
        }
        return s;
    }

}
