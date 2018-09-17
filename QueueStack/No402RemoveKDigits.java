package QueueStack;

import java.util.Arrays;

public class No402RemoveKDigits {

	public static void main(String[] args) {
		No402RemoveKDigits sol = new No402RemoveKDigits();
		String t = "\"1432219\"\n" + 
				"3\n" + 
				"\"127431\"\n" + 
				"1\n" + 
				"\"15149\"\n" + 
				"2\n" + 
				"\"10200\"\n" + 
				"1\n" + 
				"\"10\"\n" + 
				"2\n" + 
				"\"0\"\n" + 
				"0\n" + 
				"\"1234567890\"\n" + 
				"9";
		t = t.replace("\"", "");
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			String num = s[i];
			int k = Integer.parseInt(s[i+1]);
			String ans = sol.removeKdigits(num, k);
			System.out.println(ans);
		}
	}

	public String removeKdigits(String num, int k) {
		// --> O(n), where n = num.length.
		
		// Intuition:
		// KEY -> find the peak when you try to append new digits
		// eg. 1234560 k = 6, when we try to append 0, we got a peak 6 -> 0
		// so we pop 6, then we see 5 -> 0 antoher peak.
		// Thus with k = 6, we will pop 123456 and append 0 at last.
		
        char[] stack = new char[num.length()];
        int size = 0;
        int i = 0;
        while(i < num.length()){
            while(k > 0 && size > 0 && stack[size-1] > num.charAt(i)){
                size--;
                k--;
            }
            stack[size++] = num.charAt(i);
            i++;
        }
        
        // k remaining
        while(k-- > 0) size--;
        
        // remove leading 0
        int head = 0;
        while(head < stack.length && stack[head] == '0') head++;
        String ret = size - head <= 0? "0": new String(Arrays.copyOfRange(stack, head ,size));
        
        return ret;
        
    }
}
