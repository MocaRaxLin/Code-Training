package String;

public class ExtraPressAForCapLock {

	public static void main(String[] args) {
		ExtraPressAForCapLock sol = new ExtraPressAForCapLock();
		String t = "my keyboard is broken!\n"
				+ "MochaRax has great preformance on his ML class. :)";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			String ans = sol.pressA(s[i]);
			System.out.println(ans);
		}
	}

	private String pressA(String s) {
		if(s.length() == 0) return "";
		StringBuilder sb = new StringBuilder();
		boolean lock = false;
		for(char c : s.toCharArray()) {
			if(c == 'A' || c == 'a') lock = !lock;
			else if(!Character.isLetter(c)) sb.append(c);
			else if(lock) sb.append(Character.toUpperCase(c));
			else sb.append(Character.toLowerCase(c));
		}
		return sb.toString();
	}

}
