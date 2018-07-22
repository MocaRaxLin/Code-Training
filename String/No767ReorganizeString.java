package String;

import java.util.HashMap;

public class No767ReorganizeString {

	public static void main(String[] args) {
		No767ReorganizeString sol = new No767ReorganizeString();
		
		String[] S = new String[] {"aaa", "a", "", "aab", "cb"};
		for(String word : S) {
			String ans = sol.reorganizeString(word);
			System.out.println(ans);
		}
	}
	
	public String reorganizeString(String S) {
		// collect all letters to map and retrieve one by one
		// collect -> N, retrieve -> 26*N (there are 26 letters in alphabet)
		// --> O(N), where N = S.length()
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for(char c: S.toCharArray()){ //collect and update hash map
            int freq = 0;
            if(map.containsKey(c)) freq = map.get(c);
            map.put(c, ++freq);
        }
        
        String ret = "";
        int mapSize = S.length();
        int[] maxFreq = new int[]{0};
        char maxChar = findMaxCharOtherthan(' ', map, maxFreq);
        while(mapSize > 0 && mapSize - maxFreq[0] >= 0){
            ret += maxChar;
            maxFreq[0]--;
            map.put(maxChar, maxFreq[0]);
            mapSize--;
            
            //prepare for next run
            maxFreq[0] = 0;
            maxChar = findMaxCharOtherthan(maxChar, map, maxFreq); // using array to pass by reference
            if(maxChar == ' ') break;
        }
        return mapSize == 0 ? ret: "";
    }
    
	// iterate hash map
    public char findMaxCharOtherthan(char lastChar, HashMap<Character, Integer> map, int[] freq){
        char ret = ' ';
        for(char key: map.keySet()){
            if(key!= lastChar && map.get(key) > freq[0]){
                freq[0] = map.get(key);
                ret = key;
            }
        }
        return ret;
    }
}
