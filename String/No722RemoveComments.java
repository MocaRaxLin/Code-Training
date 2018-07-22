package String;

import java.util.LinkedList;
import java.util.List;

import Util.Show;

public class No722RemoveComments {

	public static void main(String[] args) {
		No722RemoveComments sol = new No722RemoveComments();
		Show show = new Show();
		String[] source = new String[] {
				"/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;",
				"/* This is a test", "   multiline  ", "   comment for ", "   testing */", "a = b + c;",
				"}",
				"a//ds/*edc//45",
				"2134*/d//djfj/*qwer*/end",
				"a/*/b//*c","blank","d/*/e*//f",
				"e/*/eabeea/*///*c*////*dc*//bcadcde/*/acbe//*d/*/*//ae//*dc//*cc//*//*eaebb*//",
				"a/*wer", "ff//end*/line",
				"a/*b", "c*/d/*e", "f*/g", 
				"a/*bcd*/e",
				"a/*b//cd*/e", "ert*/end"
		};
		List<String> ans = sol.removeComments(source);
		show.showList(ans, true);
	}
	
	private List<String> removeComments(String[] source) {
		// Don't think too much, just SCAN and COLLECT!
		// --> O(N), where N is number of char in the source array.
		
		// NOTE:
		// Regular Expression helps less here, because there is no EXACT pattern.
		// Each line could be "A//B", "A/*B//C", "/*/A*///B", you name it.
		// Also functions for RegExp, like matches(), replaceAll(), replaceFirst(), are hard to use, 
		// because we don't know "//" or "/*" appears first. Even though we know which appears first, 
		// only the replaceFirst("//", "") can be used.
		// If we try to use replaceFirst("/\\*.*\\*/", "") or replaceFirst("/\\*.*", ""),
		// here comes a problem. How do we know when will "*/" appears? 
		// So we still need to check the rest of chars and find the end of comment.
		
		String cache = "";
        boolean isCommenting = false;
        List<String> ret = new LinkedList<String>();
        
        for(String line : source){
            for(int i = 0; i < line.length(); i++){
                if(i + 1 < line.length()){
                    String symbol = "" + line.charAt(i) + line.charAt(i + 1);
                    //System.out.println(symbol);
                    if(!isCommenting && symbol.equals("//")){
                        break;
                    }else if(!isCommenting && symbol.equals("/*")){
                        isCommenting = true;
                        i+=1;
                        continue;
                    }else if(isCommenting && symbol.equals("*/")){
                        isCommenting = false;
                        i+=1;
                        continue;
                    }
                }
                if(!isCommenting) cache += line.charAt(i);
            }
            if(!isCommenting && cache.length() > 0){
                ret.add(cache);
                cache = "";
            }
        }
        return ret;
	}
}
