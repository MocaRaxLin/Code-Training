package String;

import java.util.HashMap;
import java.util.HashSet;

public class No819MostCommonWord {

	public static void main(String[] args) {
		No819MostCommonWord sol = new No819MostCommonWord();
		String input0 = "Bob hit a ball, the hit BALL flew far after it was hit.";
		String[] input1 = new String[] { "hit" };
		String ans = sol.mostCommonWord(input0, input1);
		System.out.println(ans);;

	}
	
	public String mostCommonWord(String paragraph, String[] banned) {
		// Scan through the whole paragraph - > N
		// convert banned to hash map -> M
		// -> M + N*7 --> O(N+M)
		
        String ret = "";
        paragraph += ' '; //modify input to satisfy program
        int max = 0;
        String word = "";
        
        HashSet<String> bannedSet = new HashSet<String>(); //Use set to reduce memory usage
        for(String bWord: banned) bannedSet.add(bWord);
        
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for(char l: paragraph.toCharArray()){
            if(l == '!' ||l == '?' ||l == '\'' ||l == ',' ||l == ';' ||l == '.' ||l == ' '){
                if(word.length() > 0 && !bannedSet.contains(word)){
                	
                    //update map<String, Integer>
                    int amount = 0;
                    if(map.containsKey(word)) amount = map.get(word);
                    amount++;
                    map.put(word, amount);
                    
                    if(amount > max){
                        max = amount;
                        ret = word;
                    }
                }
                word = "";
            }else{
            	char c = Character.toLowerCase(l);
                word += c;
            }
        }
        return ret;
    }
}
