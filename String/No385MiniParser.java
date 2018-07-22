package String;

import java.util.LinkedList;
import java.util.List;

public class No385MiniParser {

	public static void main(String[] args) {
		No385MiniParser sol = new No385MiniParser();
		
		String[] input = new String[] {
				"[324,-11]",
				"-1",
				"[-1]",
				"[-2,[76,4,[-10],8,[-24,1]],9]",
				"[]",
				"[-1,3,[4,3,-21],5]"
		};
		for(String s:input) {
			NestedInteger ans = sol.deserialize(s);
			System.out.println(ans.show());
		}
	}
	
	private NestedInteger deserialize(String s) {
		// --> O(n), where n = s.length()
		
		// NOTE:
		// Parsing is a linear-time process.
		// Keep this in mind!
		
		// How ?
		// Global index pointer + Recursion 
		// eg. S = "[-2,[76,4,[-10],8,[-24,1]],9]"
		// By observation, we find out the tree structure as following.
		//               [ ]
		//    /           |             \
		// -2            [ ]              9
		//      /     /   |   \     \
		//     76    4   [ ]   8    [ ]
		//                |         / \
		//               10       -24  1
		//
		// Now it is time to travel this tree.
		
		// Three cases:
		// 1. Find an integer.
		//    collect all '-' and "[0-9]" to form a number string then parse it to integer.
		//    store it in Nested Integer and return it.
		// 2. meet a '['
		//    new a NestedInteger() as list and prepare to store elements parsed later.
		// 3. meet a ']'
		//    return the NestedInteger object list.
		
		// How to extract data from list like [-1,3,[4,3,-21],5]?
		// skip '['
		// store -1, 3
		// skip ','
		// s.charAt(6) is '[' != ']' go in deserialize(s, 6) and loop again 
		// store 4, 3, -21, then found ']', skip it and return list.
		// store [4,3,-21] to the list in this layer, skip ','
		// store 5, skip ']'
		// return list.
		
		// Keep in mind this typical recursive parsing program!
		
		return deserialize(s, new int[]{0});
	}

	public NestedInteger deserialize(String s, int[] p){
        if(p[0] == s.length()) return null;
        if(s.charAt(p[0]) == '-' || Character.isDigit(s.charAt(p[0]))){
            String num = "" + s.charAt(p[0]);
            p[0]++;
            while(p[0] < s.length() && Character.isDigit(s.charAt(p[0])) ){
                num += s.charAt(p[0]);
                p[0]++;
            }
            return new NestedInteger(Integer.parseInt(num));
        }
        p[0]++; // skip '['
        NestedInteger list = new NestedInteger();
        while(p[0] < s.length() && s.charAt(p[0]) != ']'){// accept inner '['
            NestedInteger child = deserialize(s, p);
            list.add(child);
            if(p[0] < s.length() && s.charAt(p[0]) == ',') p[0]++;
        }
        p[0]++; //skip ']'
        return list;
    }
	
	public NestedInteger deserialize0(String s) {
		// --> O(n^2), where n = s.length().
		// It is too slow, because we rescan lots of part.
		// eg. [[[[[[[[[[[[[[[[[[[[]]]]]]]]]]]]]]]]]]]] length = 40
		// In this worst case, we iterate 40 + 38 + 36 + ... + 2 times -> (40+2)(40/2)/2 -> (n^2)/4
		
        // number only like 324
        if(s.matches("\\-?\\d+")) return new NestedInteger(Integer.parseInt(s));
        else if(s.matches("\\[\\]")) return new NestedInteger();
        // has braced like [-2,[76,4,[-10],8,[-24,1]],9]
        NestedInteger ret = new NestedInteger();
        int i = 1;
        String ele = "";
        int brace = 0;
        while(i < s.length()){
            if(brace == 0 && (s.charAt(i) == ',' || i == s.length() - 1)){
                ret.add(deserialize(ele));
                ele = "";
            }else{
                if(s.charAt(i) == '[') brace++;
                else if(s.charAt(i) == ']') brace--;
                ele += s.charAt(i);
            }
            i++;
        }
        return ret;
    }
	
}

class NestedInteger{
	/*
	 *     // Constructor initializes an empty nested list.
	 *     public NestedInteger();
	 *
	 *     // Constructor initializes a single integer.
	 *     public NestedInteger(int value);
	 *
	 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
	 *     public boolean isInteger();
	 *
	 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
	 *     // Return null if this NestedInteger holds a nested list
	 *     public Integer getInteger();
	 *
	 *     // Set this NestedInteger to hold a single integer.
	 *     public void setInteger(int value);
	 *
	 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
	 *     public void add(NestedInteger ni);
	 *
	 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
	 *     // Return null if this NestedInteger holds a single integer
	 *     public List<NestedInteger> getList();
	 */
	
	int value;
	List<NestedInteger> list;
	public NestedInteger() {
		list = new LinkedList<NestedInteger>();
	}
	public NestedInteger(int value) {
		this.value = value;
	}
	public void add(NestedInteger ni) {
		list.add(ni);
	}
	public String show() {
		if(list == null) return String.valueOf(value);
		String ret = "[";
		for(int i = 0; i<list.size(); i++)
			ret += list.get(i).show() + ",";
		ret += "]";
		return ret;
	}
	
}