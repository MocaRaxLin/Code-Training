package String;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class No451SortCharactersByFrequency {

	public static void main(String[] args) {
		No451SortCharactersByFrequency sol = new No451SortCharactersByFrequency();
		String t = "\"tree\"\n" + 
				"\"cccaaa\"\n" + 
				"\"Aabb\"";
		t = t.replace("\"", "");
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			String ans = sol.frequencySort(s[i]);
			System.out.println(ans);
		}
	}

	public String frequencySort(String s) {
		// --> O(n^2)
		
		// But it is faster because we only use array :)
		// Collect and count frequencies, pick up the char of highest freqency, append on char array.
		// Here we reuse ca from input s just like stack by array
		
        int[] map = new int[128];
        char[] ca = s.toCharArray();
        for(char c : ca) map[c]++;
        
        int size = 0;
        while(size < ca.length){
            int idxMax = 0;
            for(int i = 0; i < map.length; i++)
                if(map[i] > map[idxMax]) idxMax = i;

            while(map[idxMax]-- > 0)
                ca[size++] = (char) idxMax; // int to char, use array for appending fixed amount of chars.
            
        }
        return new String(ca); // char[] to new String
    }
	
	public String frequencySort0(String s) {
		// --> O(nlogn), where n = s.length().
		// The bottleneck is the sorting by frequency.
		
		// First thought, but probably not an idea one.
		// Little bit slow because we call too many library functions.
		
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for(char c : s.toCharArray()){
            int freq = 0;
            if(map.containsKey(c)) freq = map.get(c);
            map.put(c, ++freq);
        }
        
        Tuple[] tuples = new Tuple[map.size()];
        int idx = 0;
        for(Map.Entry<Character,Integer> e : map.entrySet()){
            tuples[idx++] = new Tuple(e.getKey(), e.getValue());
        }
        Arrays.sort(tuples, new Comparator<Tuple>(){
           public int compare(Tuple a, Tuple b){
               return b.freq - a.freq; // a, b if return > 0 then swap => b, a
           } 
        });
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < tuples.length; i++){
            while(tuples[i].freq-- > 0){
                sb.append(tuples[i].c);
            }
        }
        return sb.toString();
    }
}

class Tuple{
    char c;
    int freq;
    public Tuple(char c, int freq){
        this.c = c;
        this.freq = freq;
    }
}