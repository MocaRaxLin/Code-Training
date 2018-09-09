package BitOperation;

import Util.Parser;

public class No421MaximumXORofTwoNumbersinanArray {

	public static void main(String[] args) {
		No421MaximumXORofTwoNumbersinanArray sol = new No421MaximumXORofTwoNumbersinanArray();
		Parser parser = new Parser();
		String t = "[3,10,5,25,2,8]\n"
				+ "[3,31,6,7,8,0,4,32,6,3,57]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] A = parser.parseArray(s[i]);
			int ans = sol.findMaximumXOR(A);
			System.out.println(ans);
		}
	}
	
	class Trie{
        Trie[] d;
        public Trie(){
            d = new Trie[2];
        }
    }
	
	public int findMaximumXOR(int[] A) {
		// --> O(n), where n = A.length
		
		// Intuition:
		// Build a trie and for each A[i] find the complementary number that close to A[i]
		
        if(A.length < 2) return 0;
        int ret = Integer.MIN_VALUE;
        Trie root = new Trie();
        addTrie(root, A[0]);
        for(int i = 1; i < A.length; i++){
            int c = getNearestComplement(root, A[i]);
            // System.out.println("A[i]: "+ A[i] + ", c: "+ c);
            ret = (A[i]^c) > ret? (A[i]^c): ret;
            addTrie(root, A[i]);
        }
        return ret;
    }
    
    private int getNearestComplement(Trie root, int a){
        Trie cur = root;
        boolean[] aBit = getBits(a);
        int ret = 0;
        for(int i = 31; i >= 0; i--){
            ret <<= 1;
            if(cur.d[0] != null && cur.d[1] != null){
                if(!aBit[i]) ret += 1;
                cur = aBit[i]? cur.d[0]: cur.d[1];
            }else if(cur.d[0] == null){
                ret += 1;;
                cur = cur.d[1];
            }else{
                cur = cur.d[0];
            }
        }
        return ret;
    }
    
    private void addTrie(Trie root, int a){
        boolean[] bits = getBits(a);
        // System.out.println(a +" to: " + Arrays.toString(bits));
        Trie cur = root;
        for(int i = 31; i >= 0; i--){
            if(bits[i] && cur.d[1] == null) cur.d[1] = new Trie();
            else if(!bits[i] && cur.d[0] == null) cur.d[0] = new Trie();
            
            cur = bits[i]? cur.d[1]: cur.d[0];
        }
    }
    
    private boolean[] getBits(int a){
        boolean[] bits = new boolean[32]; // true state for 1
        int idx = 0;
        while(a > 0){
            bits[idx++] = (a & 1) == 1;
            a >>= 1;
        }
        return bits;
    }

}
