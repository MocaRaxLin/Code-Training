package String;

public class No809ExpressiveWords {

	public static void main(String[] args) {
		No809ExpressiveWords sol = new No809ExpressiveWords();
		
		String S = "heeellooo";
		String[] words = new String[]{"hello", "hi", "helo"};
		int ans = sol.expressiveWords(S, words);
		System.out.println(ans);
	}
	
	public int expressiveWords(String S, String[] words) {
		// scan String S and provide pattern regular expression -> N
		// RegExp matching takes at worst N for each word in words
		// -> N + N*M --> O(NM), where N = S.length(), M = words.length
		
        String pattern = "";
        int amount = 0;
        char last = '.'; //this give the leading pattern ".{0}", so don't worry
        for(char c: S.toCharArray()){
            if(last == c){
                amount++;
            }else{
                pattern += last;
                if(amount >= 3) pattern+="{1,"+amount+"}";
                else pattern+="{"+amount+"}";
                amount = 1;
                last = c;
            }
        }
        //for last char
        pattern+=last;
        if(amount >= 3) pattern+="{1,"+amount+"}";
        else pattern+="{"+amount+"}";
        
        //System.out.println(pattern); //.{0}h{1}e{1,3}l{2}o{1,3}
        int ret = 0;
        for(String word: words){
            if(word.matches(pattern)) ret++;
        }
        return ret;
    }

}
