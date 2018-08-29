package String;

public class ExtraEmailHandler {

	public static void main(String[] args) {
		ExtraEmailHandler sol = new ExtraEmailHandler();
		String t = "a.b@example.com\n" + 
				"dupli......cate@example.com\n" + 
				"d.u.p.l.i.c.a.t.e@example.com\n" + 
				"ab+work@example.com\n" + 
				".ab+c.@haha.com\n" + 
				".ab..+canSeeMe@test.com\n" + 
				"....e......@nu.un\n" +
				"....@noChar.haha\n" +
				"..a..+.Ab@plus.com";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			String ans = sol.handleEmail(s[i]);
			System.out.println(ans);
		}
	}

	private String handleEmail(String email) {
		StringBuilder sb = new StringBuilder();
		
		char[] ca = email.toCharArray();
		int i = 0;
		while(i < ca.length && ca[i] == '.') sb.append(ca[i++]);
		
		String dots = "";
		boolean seenPlus = false;
		while(i < ca.length && ca[i] != '@') {
			if(!seenPlus) {
				if(ca[i] == '+') {
					seenPlus = true;
				}else if(ca[i] == '.') {
					dots += '.';
				}else{
					sb.append(ca[i]);
					dots = "";
				}
			}
			i++;
		}
		
		if(!seenPlus) sb.append(dots);
		while(i < ca.length) sb.append(ca[i++]);
		
		return sb.toString();
	}

}
