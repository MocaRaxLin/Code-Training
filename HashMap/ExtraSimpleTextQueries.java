package HashMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Util.Parser;
import Util.Show;

public class ExtraSimpleTextQueries {

	public static void main(String[] args) {
		ExtraSimpleTextQueries sol = new ExtraSimpleTextQueries();
		Parser parser = new Parser();
		Show show = new Show();
		String t = "[\"bob and alice like to text each other\",\"bob does not like to ski\",\"alice likes to ski\"]\n"
				+ "[\"bob alice\",\"alice\",\"like\"]";
		t = t.replace("\"", "");
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			String[] sentences = parser.parseArrayStr(s[i]);
			String[] queries = parser.parseArrayStr(s[i+1]);
			int[][] ans = sol.queriesInSentences(sentences, queries);
			show.showMatrix(ans);
		}
	}

	private int[][] queriesInSentences(String[] sentences, String[] queries) {
		
		int n = sentences.length;
		// use array list not array directly, like adjecent graph.
		List<Map<String, Integer>> senMap = new ArrayList<Map<String, Integer>>();
		for(int i = 0; i < n; i++) {
			Map<String, Integer> wordMap = new HashMap<String, Integer>();
			String[] words = sentences[i].split(" ");
			for(String w: words) {
				int freq = 0;
				if(wordMap.containsKey(w)) freq = wordMap.get(w);
				wordMap.put(w, ++freq);
			}
			senMap.add(wordMap);
		}
		
		int m = queries.length;
		int[][] ret = new int[m][]; // not sure how many elements in each row.
		for(int i = 0; i < m; i++) {
			// collect words in each query
			Map<String, Integer> qMap = new HashMap<String, Integer>();
			String[] words = queries[i].split(" ");
			System.out.println(Arrays.toString(words));
			for(String w: words) {
				int freq = 0;
				if(qMap.containsKey(w)) freq = qMap.get(w);
				qMap.put(w, ++freq);
			}
			
			// compare and find the sentences that satisfy all words in this query.
			int[] senIdx = new int[n];
			int size = 0;
			for(int j = 0; j < n; j++) {
				if(isFit(qMap, senMap.get(j))) senIdx[size++] = j;
			}
			ret[i] = Arrays.copyOfRange(senIdx, 0, size);
		}
		return ret;
	}

	private boolean isFit(Map<String, Integer> qMap, Map<String, Integer> senMapj) {
		for(Map.Entry<String, Integer> e: qMap.entrySet()) {
			if(!senMapj.containsKey(e.getKey())) return false; // word not in this sentence
			if(senMapj.get(e.getKey()) < e.getValue()) return false; // word freq. not enough
		}
		return true;
	}

}
