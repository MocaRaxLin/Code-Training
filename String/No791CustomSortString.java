package String;

import java.util.HashMap;

public class No791CustomSortString {

	public static void main(String[] args) {
		No791CustomSortString sol = new No791CustomSortString();
		String S = "cba";
		String T = "abcd";
		String ans = sol.customSortString(S, T);
		System.out.println(ans);
	}
	
	public String customSortString(String S, String T) {
		// scan S store to map -> M
		// scan T update map -> N
		// string builder for String ret, it runs |T| -> N
		// -> M + 2*N
		// --> O(M+N)
		
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for(char c : S.toCharArray()) map.put(c, 0);
        
        String appendix = "";
        for(char c: T.toCharArray()){
            if(map.containsKey(c)){
                int t = map.get(c);
                t++;
                map.put(c, t);
            }else{
                appendix += c;
            }
        }
        
        String ret = "";
        for(char c : S.toCharArray()){
            int times = map.get(c);
            String tmp = "";
            for(int i = 0; i < times; i++) tmp+=c;
            ret += tmp;
        }
        ret+=appendix;
        return ret;
    }
}
