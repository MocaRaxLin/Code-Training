package String;

import java.util.Stack;

public class No388LongestAbsoluteFilePath {

	public static void main(String[] args) {
		No388LongestAbsoluteFilePath sol = new No388LongestAbsoluteFilePath();
		String t = "\"dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext\"\n" + 
				"\"dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext\"\n" + 
				"\"a\"";
		String[] s = t.split("\"\n");
		for(int i = 0; i < s.length; i++) {
			String input = s[i].replaceAll("\"", "");
			int ans = sol.lengthLongestPath(input);
			System.out.println(ans);
		}
	}

	public int lengthLongestPath(String input) {
        // -- >(nm), where n = input.length, m is the greatest level
        
        // Intution:
        // Explore level by level or go back to certain higher level.
        
        int ret = 0;
        Stack<Integer> stack = new Stack<Integer>();
        int curLen = 0;
        int curLevel = -1;
        String[] lines = input.split("\n");
        for(int i = 0; i < lines.length; i++){
            int level = lines[i].lastIndexOf('\t') + 1;
            int dir_len = lines[i].length() - level + 1; // add '/' in the front
            while(curLevel >= 0 && curLevel >= level){
                curLen -= stack.pop();
                curLevel--;
            }
            // now curLevel + 1 must equal to level
            stack.push(dir_len);
            curLen += dir_len;
            curLevel++;
            
            if(lines[i].contains(".")) ret = Math.max(ret, curLen);
        }
        return ret == 0? 0: ret - 1; // if no file exists return 0.
    }
}
