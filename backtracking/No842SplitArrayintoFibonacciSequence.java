package backtracking;

import java.util.LinkedList;
import java.util.List;

import Util.Show;

public class No842SplitArrayintoFibonacciSequence {

	public static void main(String[] args) {
		No842SplitArrayintoFibonacciSequence sol = new No842SplitArrayintoFibonacciSequence();
		Show show = new Show();
		
		String[] inputs = new String[] {
				"123456579",
				"12",
				"0123",
				"11235813",
				"1101111"
		};
		for(String s: inputs) {
			List<Integer> ans = sol.splitIntoFibonacci(s);
			show.showListInt(ans, true);
		}
	}
	
	public List<Integer> splitIntoFibonacci(String S) {
		// --> O(2^n), where n = S.length()
		
		// eg. 1234
		// x
		// 1           12    123 1234
		// 2    23 234  3 34   4
		// 3 34  4      4
		// 4
		// like Pascal triangle at level 4 -> 1 4 6 4 1
		// Total number of Nodes to travel is C40+C41+C42+C43+C44 = 2^4 :(
		// Too slow!
		// Use backtracking.
		
		// Intuition:
		// If f[i-2] + f[i-1] = f[i], add f[i] into list, 
		// otherwise include the next digit and check f[i] again.
		// If we check all possible f[i], and none of them satisfies
		// Fibonacii: f[i-2] + f[i-1] = f[i], 
		// then pop F[i-1], include the next digit and check again.
		
        List<Integer> ret = new LinkedList<Integer>();
        backtrack(S, 0, ret);
        return ret;
    }
    
    public boolean backtrack(String s, int index, List<Integer> list){
        if(index == s.length() && list.size() >= 3) return true;
        for(int i = index; i < s.length(); i++){
            if(s.charAt(index) == '0' && i > index) break;
            long num = Long.parseLong(s.substring(index, i + 1));
            if(num > Integer.MAX_VALUE) break;
            int size = list.size();
            if(size >= 2 && num > list.get(size-2) + list.get(size-1)) break;
            if(size <= 1 || num == list.get(size-2) + list.get(size-1)){
                list.add((int) num);
                if(backtrack(s, i+1, list)) return true;
                list.remove(list.size() - 1);
            }
        }
        return false;
    }
}
