package String;

public class ExtraStringShift {

	public static void main(String[] args) {
		ExtraStringShift sol = new ExtraStringShift();
		String t = "abcd\n" + 
				"101\n" + 
				"0123456789\n" + 
				"25\n" + 
				"0123456789\n" + 
				"5\n" + 
				"0123456789\n" + 
				"-3";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			String ss = s[i];
			int shift = Integer.parseInt(s[i+1]);
			String ans = sol.leftShiftString(ss, shift);
			System.out.println(ans);
		}
	}

	private String leftShiftString(String s, int shift) {
		int n = s.length(); if(n == 0) return s;
		int move = shift%n; if(move == 0) return s;
		if(move < 0) move += n;
		return s.substring(move, n) + s.substring(0, move);
	}

}
