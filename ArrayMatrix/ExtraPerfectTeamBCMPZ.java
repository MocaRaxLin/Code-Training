package ArrayMatrix;

public class ExtraPerfectTeamBCMPZ {

	public static void main(String[] args) {
		ExtraPerfectTeamBCMPZ sol = new ExtraPerfectTeamBCMPZ();
		String t = "bcmpz\n" + 
				"pcmpp\n" + 
				"ppppppp\n" + 
				"cpcp\n" + 
				"zb\n" + 
				"\n" +
				"ppccmmbbzz";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int ans = sol.noOfPerfectTeam(s[i]);
			System.out.println(ans);
		}
	}

	private int noOfPerfectTeam(String s) {
		if(s.length() < 5) return 0;
		int[] counts = new int[5]; // b,c,m,p,z
		for(char c : s.toCharArray()) {
			switch (c) {
			case 'b':
				counts[0]++;
				break;
			case 'c':
				counts[1]++;
				break;
			case 'm':
				counts[2]++;
				break;
			case 'p':
				counts[3]++;
				break;
			case 'z':
				counts[4]++;
				break;
			default:
				System.out.println("Unknown subject.");
				return -1;
			}
		}
		int min = 500000; // because 5 <= n <= 5*10^5
		for(int count: counts) min = Math.min(min, count);
		return min;
	}

}
