package String;

public class ExtraCSVFormatter {

	public static void main(String[] args) {
		ExtraCSVFormatter sol = new ExtraCSVFormatter();
		String[][] s = {{"Name,Course,Percent,Letter Grade", "Mark Johnson,Biology,75,B", "Susan Smith,Mathematics,84,B+",
			"Bob Doe,,80,B+", "Emma Knight,Physics,91,", "Jenny Lee,English,95,A+", ",Mathematics,100,A++"},
				{"a,b,c", "aa,bb,cc",",bbb,","aaaa,,cccc","a,,"},
				{}, {""}};
		for(int i = 0; i < s.length; i++) {
			String ans = sol.formatCSV(s[i]);
			System.out.println(ans);
		}
		
	}

	private String formatCSV(String[] s) {
		if( s.length == 0) return "";
		int col = 1;
		for(char c : s[0].toCharArray()) if(c == ',') col++;
		
		int[] maxWid = new int[col];
		for(int i = 0; i < s.length; i++) {
			String[] items = s[i].split(",");
			for(int j = 0; j < items.length; j++) maxWid[j] = Math.max(maxWid[j], items[j].length());
			// s[i] == 0 doesn't influence widthes[last]
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < s.length; i++) {
			String[] items = s[i].split(",");
			for(int j = 0; j < col; j++) {
				int space = maxWid[j];
				if(j < items.length) space-=items[j].length();
				while(space-->0) sb.append(' ');
				String item = j < items.length? items[j]: "";
				if(j != col - 1) item += ", ";
				sb.append(item);
			}
			sb.append('\n');
		}
		return sb.toString();
	}

}
