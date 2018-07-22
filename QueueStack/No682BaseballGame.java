package QueueStack;

import Util.Parser;

public class No682BaseballGame {

	public static void main(String[] args) {
		No682BaseballGame sol = new No682BaseballGame();
		Parser parser = new Parser();
		String t = "[\"\"]\n" + 
				"[\"5\",\"2\",\"C\",\"D\",\"+\"]\n" + 
				"[\"5\",\"-2\",\"4\",\"C\",\"D\",\"9\",\"+\",\"+\"]";
		t = t.replaceAll("\"", "");
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			String[] ops = parser.parseArrayStr(s[i]);
			int ans = sol.calPoints(ops);
			System.out.println(ans);
		}
	}

	public int calPoints(String[] ops) {
        int[] stack = new int[ops.length];
        int size = 0;
        int ret = 0;
        for(int i = 0; i < ops.length; i++){
            if(ops[i].equals("C")){
                ret -= stack[--size];
            }else if(ops[i].equals("D")){
                stack[size] = stack[size-1]*2;
                ret += stack[size++];
            }else if(ops[i].equals("+")){
                stack[size] = stack[size-1] + stack[size-2];
                ret += stack[size++];
            }else{
                stack[size] = Integer.parseInt(ops[i]);
                ret += stack[size++];
            }
        }
        return ret;
    }
}
