package String;

public class ExtraCommentRemover {

	public static void main(String[] args) {
		ExtraCommentRemover sol = new ExtraCommentRemover();
		String[][] s = new String[][]{ {
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
		},{
			"public class HelloWorld {","	// This program will print \"Hello, World\"","	public static void main(Stirng[] args) {",
			"		System.out.println(\"Hello/*, World*/\"); // A single line comment", "		/* A sample comment",
			"		   spanning multiple", "		   lines*/	","	/*} // I should be commented XD","}"
		} };
		for(int i = 0; i < s.length; i++) {
			String ans = sol.removeComment(s[i]);
			System.out.println(ans);
		}

	}

	public String removeComment(String[] lines) {
		
		// Replace comment into a single space
		// Add '\n' by yourself
		
		StringBuilder sb = new StringBuilder();
		String comment = "";
		
		boolean isCommenting = false;
		for(String line: lines) {
			char[] ca = line.toCharArray();
			int n = ca.length;
			for(int i = 0; i < n; i++) {
				if(isCommenting) {
					if(ca[i] == '*' && i+1<n && ca[i+1] == '/') {
						sb.append(' ');
						isCommenting = false;
						comment = "";
						i++;
					}else if(ca[i] == '/' && i+1<n && ca[i+1] == '/') {
						comment += ' ';
						break;
					}else{
						comment += ca[i];
					}
				}else{
					if(ca[i] == '/' && i+1<n && ca[i+1] == '/') {
						sb.append(' ');
						break; // end this line
					}else if(ca[i] == '/' && i+1<n && ca[i+1] == '*') {
						comment = "/*";
						isCommenting = true;
						i++;
					}else{
						sb.append(ca[i]);
					}
				}
			}
			if(isCommenting) comment+='\n';
			else sb.append('\n');
		}
		if(comment.length() > 0) sb.append(comment);
		return sb.toString();
	}
}
