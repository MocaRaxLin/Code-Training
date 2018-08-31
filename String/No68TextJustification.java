package String;

import java.util.LinkedList;
import java.util.List;

import Util.Parser;
import Util.Show;

public class No68TextJustification {

	public static void main(String[] args) {
		No68TextJustification sol = new No68TextJustification();
		Parser parser = new Parser();
		Show show = new Show();
		String t = "[\"This\", \"is\", \"an\", \"example\", \"of\", \"text\", \"justification.\"]\n" + 
				"16\n" + 
				"[\"What\",\"must\",\"be\",\"acknowledgment\",\"shall\",\"be\"]\n" + 
				"16\n" + 
				"[\"Science\",\"is\",\"what\",\"we\",\"understand\",\"well\",\"enough\",\"to\",\"explain\",\"to\",\"a\",\"computer.\",\"Art\",\"is\",\"everything\",\"else\",\"we\",\"do\"]\n" + 
				"20";
		t = t.replace("\"", "");
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			String[] words = parser.parseArrayStr(s[i]);
			int maxWidth = Integer.parseInt(s[i+1]);
			List<String> ans = sol.fullJustify(words, maxWidth);
			show.showList(ans, true);
		}
	}

	public List<String> fullJustify(String[] words, int maxWidth) {
		// --> Linear time
		
		// Intuition:
		// For each line, we check how may words we can indude in and set a bound as [i, idx)
		// Form the line by string builder.
		// - if idx hit end or only one word is included, then we use left justification
		// - Else evenly distribute extra spaces between words. (using id < r to add one more space)
		
        List<String> ret = new LinkedList<String>();
        if(words.length == 0) return ret;
        int i = 0;
        // System.out.println(Arrays.toString(words));
        while(i < words.length){
            int idx = i;
            int width = words[idx++].length();
            // System.out.println(width);
            while(idx < words.length && width + words[idx].length() + 1 <= maxWidth){
                width += 1 + words[idx++].length();
                //System.out.println(width);
            }
            // System.out.println("i: "+ i+", idx: "+ idx);
            
            StringBuilder sb;
            int rest = maxWidth - width;
            int space = idx - i - 1;         

            if(idx == words.length || space == 0){
                sb = new StringBuilder();
                sb.append(words[i++]);
                while(i < idx) sb.append(' '+words[i++]);
                while(rest-->0) sb.append(' ');
            }else{
                sb = new StringBuilder();
                sb.append(words[i++]);
                
                int q = rest/space;
                int r = rest%space;
                int id = 0; // space id
                while(i < idx){
                    if(id++ < r) sb.append(' ');
                    for(int p = 0; p < q; p++) sb.append(' ');
                    sb.append(' '+words[i++]);
                }
            }
            // System.out.println("i: "+ i+", idx: "+ idx);
            ret.add(sb.toString());
        }
        return ret;
    }
}
