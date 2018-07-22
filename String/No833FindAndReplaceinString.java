package String;

import java.util.Arrays;

public class No833FindAndReplaceinString {

	public static void main(String[] args) {
		No833FindAndReplaceinString sol = new No833FindAndReplaceinString();
		
		String S = "abcd";
		int[] indexes = new int[] {0, 2};
		String[] sources = new String[]{"a", "cd"};
		String[] targets = new String[]{"eee", "fff"};
		String ans = sol.findReplaceString(S, indexes, sources, targets);
		System.out.println(ans);

	}
	
	public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
		//using string builder and match array
		// --> O(N+Q), where N = S.length(), Q = index.length
		// ps. if we only use string builder -> N + QlogQ, because we have to sort replacing info.
		// if we use sorting, we had better sort them in reverse order,
		// because when we operate replacement, it won't influence indices in the front part of S
		
        int[] match = new int[S.length()];
        Arrays.fill(match, -1);
        for(int i = 0; i < indexes.length; i++){
            int ix = indexes[i];
            if(S.substring(ix, ix + sources[i].length()).equals(sources[i])){
                match[ix] = i;
            }
        }
        
        String ret = "";
        for(int i = 0; i < match.length; i++){
            int ip = match[i];
            if(ip >= 0){
                ret += targets[ip];
                i += sources[ip].length() - 1;
            }else{
                ret += S.charAt(i);
            }
        }
        return ret;
	}
}
