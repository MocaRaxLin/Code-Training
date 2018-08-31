package Graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

import Util.Parser;

public class ExtraNHighestScoreRelativeMoives {

	public static void main(String[] args) {
		ExtraNHighestScoreRelativeMoives sol = new ExtraNHighestScoreRelativeMoives();
		Parser parser = new Parser();
		String t = "[1.2,3.6,2.4,4.8]\n" + 
				"[[1,2],[2,4],[3,1],[3,4]]\n" + 
				"1\n" + 
				"2\n" + 
				"[1.2,3.6,2.4,4.8]\n" + 
				"[[1,2],[2,4],[3,1],[3,4]]\n" + 
				"1\n" + 
				"10\n" + 
				"[1.2,3.6,2.4]\n" + 
				"[[1,2],[2,3]]\n" + 
				"1\n" + 
				"1\n" + 
				"[6.2,3.6,2.4,9.8,5.1,8.4,8.4,8.0]\n" + 
				"[[1,2],[2,5],[5,3],[3,1],[1,4],[4,6],[6,7],[4,7],[8,7]]\n" + 
				"1\n" + 
				"4";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=4) {
			float[] ratings = parser.parseArrayFloat(s[i]);
			Movie[] movies = new Movie[ratings.length+1];
			for(int m = 0; m < ratings.length; m++) movies[m+1] = new Movie(m+1, ratings[m]);
			int[][] edges = parser.parseMatrix(s[i+1]);
			for(int[] e: edges) {
				movies[e[0]].similarMoives.add(movies[e[1]]);
				movies[e[1]].similarMoives.add(movies[e[0]]);
			}
			int start = Integer.parseInt(s[i+2]);
			int N = Integer.parseInt(s[i+3]);
			Set<Movie> ans = sol.getMovieRecommendations(movies[start], N);
			
			Iterator<Movie> it = ans.iterator();
			while(it.hasNext()) System.out.print(it.next().toString()+", ");
			System.out.print("\n");
		}
	}

	private Set<Movie> getMovieRecommendations(Movie movie, int n) {
		Set<Movie> seen = new HashSet<Movie>();
		PriorityQueue<Movie> pq = new PriorityQueue<Movie>(new Comparator<Movie>() {
			public int compare(Movie a, Movie b) { return a.rating - b.rating < 0 ? -1: 1; }
		});
		
		// DFS
		Stack<Movie> stack = new Stack<Movie>();
		stack.push(movie);
		seen.add(movie);
		while(stack.size() > 0) {
			Movie m = stack.pop();
			if(pq.size() > n) pq.poll();
			Iterator<Movie> it = m.similarMoives.iterator();
			while(it.hasNext()) {
				Movie rm = it.next();
				if(!seen.contains(rm)) {
					stack.push(rm);
					pq.offer(rm);
					seen.add(rm);
				}
			}
		}
		
		Set<Movie> ret = new HashSet<Movie>();
		while(pq.size() > 0) ret.add(pq.poll());
		return ret;
	}

}

class Movie{
	int id;
	float rating;
	List<Movie> similarMoives;
	public Movie(int id, float rating) {
		similarMoives = new ArrayList<Movie>();
		this.id = id;
		this.rating = rating;
	}
	public String toString() {
		return "{"+id+","+rating+"}";
	}
}
