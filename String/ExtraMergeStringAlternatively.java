package String;

public class ExtraMergeStringAlternatively {

	public static void main(String[] args) {
		ExtraMergeStringAlternatively sol = new ExtraMergeStringAlternatively();
		String t = "abc\n"
				+ "def\n"
				+ "abc\n"
				+ "stuvwx\n"
				+ "\n"
				+ "tttttttttttttt\n"
				+ "aaaaa\n"
				+ "bb\n"
				+ "\n"
				+ "";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			String a = s[i];
			String b = s[i+1];
			String ans = sol.mergeStrings(a,b);
			System.out.println(ans);
		}

	}

	private String mergeStrings(String a, String b) {
		char[] ret = new char[a.length()+b.length()];
		int i = 0, j = 0, p = 0, turn = 0;
		while(i < a.length() && j < b.length()) {
			if(turn == 0) ret[p++] = a.charAt(i++);
			else ret[p++] = b.charAt(j++);
			turn = ++turn%2;
		}
		while(i < a.length()) ret[p++] = a.charAt(i++);
		while(j < b.length()) ret[p++] = b.charAt(j++);
		return new String(ret);
		
	}
	
	
}
