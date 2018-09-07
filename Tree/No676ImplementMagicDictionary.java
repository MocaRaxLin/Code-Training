package Tree;

public class No676ImplementMagicDictionary {
	class Trie{ // inner class :)
        boolean isWord;
        Trie[] children;
        public Trie(){
            isWord = false;
            children = new Trie[26];
        }
    }
    
    Trie root;
    /** Initialize your data structure here. */
    public No676ImplementMagicDictionary() {
        root = new Trie();
    }
    
    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        for(String w: dict){
            Trie cur = root;
            char[] ca = w.toCharArray();
            for(int i = 0 ; i < ca.length; i++){
                if(cur.children[ca[i] - 'a'] == null) cur.children[ca[i] - 'a'] = new Trie();
                cur = cur.children[ca[i] - 'a'];
            }
            cur.isWord = true;
        }
    }
    
    
    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        char[] wca = word.toCharArray();
        for(int d = 0; d < wca.length; d++){ // split at index d
            Trie cur = root;
            int idx = 0;
            while(idx < d){
                if(cur.children[wca[idx] - 'a'] == null) return false;
                cur = cur.children[wca[idx++] - 'a'];
            }
            // try all char at index d except wca[d]
            // skip index at d check from d+1
            for(int i = 0; i < 26; i++){
                if(cur.children[i] == null || i == wca[d] - 'a') continue;
                if(hasWord(cur.children[i], wca, d+1)) return true;
            }
        }
        return false;
    }
    
    private boolean hasWord(Trie node, char[] ca, int idx){
        while(idx < ca.length){
            if(node.children[ca[idx] - 'a'] == null) return false;
            node = node.children[ca[idx++] - 'a'];
        }
        return node.isWord;
    }

}

//Test cases:
//
//["MagicDictionary", "buildDict", "search", "search", "search", "search"]
//[[], [["hello","leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
//["MagicDictionary", "buildDict", "search", "search", "search", "search"]
//[[], [["hello","hallo","leetcode"]], ["hello"], ["hallo"], ["hell"], ["leetcoded"]]
