package Tree;

public class No331VerifyPreorderSerializationofaBinaryTree {

	public static void main(String[] args) {
		No331VerifyPreorderSerializationofaBinaryTree sol = new No331VerifyPreorderSerializationofaBinaryTree();
		String t = "\"9,3,4,#,#,1,#,#,2,#,6,#,#\"\n" + 
				"\"1,#\"\n" + 
				"\"9,#,#,1\"";
		t = t.replace("\"", "");
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			boolean ans = sol.isValidSerialization(s[i]);
			System.out.println(ans);
		}
	}
	
	public boolean isValidSerialization(String preorder) {
        // --> time = O(n), where n = preoder.length().
        
		// We can use stack, pop X,#,# and push #
		// Repeat until input string end and stack has last element "#".
		
		// Here we introduce new method out-in degree difference (#out - #in).
		// In the end the difference is 0 if the tree serie is valid.
		
        // 150 / 150 test cases passed.
        // Runtime: 5 ms
    
        // Idea source:
        // https://www.youtube.com/watch?v=_mbnPPHJmTQ
        
        // null node uses 1 out degree from tree
        // non-null node uses 1 out degree from tree and gives 2 out degree for tree
        // if number of out degree < 0 then the tree cannot be tree.
        // Is a valid serialization if outInDegDiff is 0 in the end.
        
        String[] sa = preorder.split(",");
        int outInDegDiff = 1;
        for(int i = 0; i < sa.length; i++){
            if(--outInDegDiff < 0) return false;
            if(!sa[i].equals("#")) outInDegDiff+=2;
        }
        return outInDegDiff == 0;
    }

}
