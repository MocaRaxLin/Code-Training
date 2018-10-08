package ArrayMatrix;

import Util.Parser;

public class ExtraEvenOddWinner {

	public static void main(String[] args) {
		ExtraEvenOddWinner sol = new ExtraEvenOddWinner();
		Parser p = new Parser();
		String t = "[3,5,6]\n" + 
				"[4,5,7]\n" + 
				"Even\n" + 
				"[3,5,6]\n" + 
				"[4,5,7]\n" + 
				"Odd\n" + 
				"[1]\n" + 
				"[-1]\n" + 
				"Even\n" + 
				"[1,2,3]\n" + 
				"[2,1,3]\n" + 
				"Even\n" + 
				"[7,-1,3]\n" + 
				"[4,2,5]\n" + 
				"Odd";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=3) {
			int[] A = p.parseArray(s[i]);
			int[] B = p.parseArray(s[i+1]);
			String ans = sol.findWinner(A, B, s[i+2]);
			System.out.println(ans);
		}
	}

	private String findWinner(int[] A, int[] B, String instr) {
		if(A.length == 0) return "Tie";
		int i = instr.equals("Even")? 0: 1;
		int scoreA = 0, scoreB = 0;
		for(; i < A.length; i+=2) {
			scoreA += A[i] - B[i];
			scoreB += B[i] - A[i];
		}
		return scoreA == scoreB? "Tie": scoreA > scoreB? "Maria": "Andrea";
	}

}
