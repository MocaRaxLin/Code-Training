package Graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import Util.Parser;

public class ExtraGetRecommendation {

	public static void main(String[] args) {
		ExtraGetRecommendation sol = new ExtraGetRecommendation();
		Parser parser = new Parser();
		String t = "[[0,4],[0,2],[0,1],[1,0],[1,2],[2,3],[1,3]\n"
				+ "[[0,0],[2,0],[2,2],[4,2],[0,2],[3,3],[3,1],[1,1]]\n"
				+ "1\n"
				+ "2";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=4) {
			int[][] follows = parser.parseMatrix(s[i]);
			int[][] likes = parser.parseMatrix(s[i+1]);
			int targetUser = Integer.parseInt(s[i+2]);
			int threshold = Integer.parseInt(s[i+3]);
			int[] ans = sol.getRecommendation(follows, likes, targetUser, threshold);
			System.out.println(Arrays.toString(ans));
		}
		
	}
	
	// follows = [[A,B], [C,D]]:A follows B, C follows D
	// likes = [[A,T1], [B,T2]]: A likes T1, B likes T2
	// targetUser: user id
	// threshold:  threshold
	public int[] getRecommendation(int[][] follows, int[][] likes, int targetUser, int threshold) {
		// --> O(Ef+El), where Ef = follows.length, El = likes.length
		// Maybe we can improve it?
		
		// Brute force:
		// build graph or links
		// Scan all tweets that haven't been like by the target user and
		// have been liked by at least threshold amount of people followed by the target user.
		
		// scan all tweet -> O(El)
		// 1. if tweet T[i] is liked by target user, then skip T[i]
		// 2. check if T[i] is liked by someone followed by target user, then count++.
		//    (count people who is satisfied)
		//    (use Set.contain() to reach constant time) 
		// 3. when count == threshold, add T[i]
		// 4. check the next tweet T[i+1]
		
		
		// init follows graph using Map<Integer, Set<Integer>>
		Map<Integer, Set<Integer>> followMap = new HashMap<>();
		for(int i = 0; i < follows.length; i++) {
			if(followMap.containsKey(follows[i][0])) {
				followMap.get(follows[i][0]).add(follows[i][1]);
			}else{
				Set<Integer> set = new HashSet<>();
				set.add(follows[i][1]);
				followMap.put(follows[i][0], set);
			}
		}
		
		// init likes graph using Map<Integer, Set<Integer>>
		Map<Integer, Set<Integer>> tweetMap = new HashMap<>();
		for(int i = 0; i < likes.length; i++) {
			if(tweetMap.containsKey(likes[i][1])) {
				tweetMap.get(likes[i][1]).add(likes[i][0]);
			}else {
				Set<Integer> set = new HashSet<>();
				set.add(likes[i][0]);
				tweetMap.put(likes[i][1], set);
			}
		}
		
		// scan and collect tweets -> O(El), check all links
		List<Integer> rTweets = new LinkedList<>();
		for(Entry<Integer, Set<Integer>> e: tweetMap.entrySet()) {
			Set<Integer> likedBy = e.getValue();
			if(likedBy.contains(targetUser)) continue;
			int count = 0;
			Iterator<Integer> it = likedBy.iterator();
			while(it.hasNext()) {
				if(followMap.get(targetUser).contains(it.next())) {
					count++;
					if(count == threshold) {
						rTweets.add(e.getKey());
						break;
					}
				}
			}
		}
		
		int i = 0;
		int[] ret = new int[rTweets.size()];
		Iterator<Integer> it2 = rTweets.iterator();
		while(it2.hasNext()) ret[i++] = it2.next();
		return ret;
	}

}
