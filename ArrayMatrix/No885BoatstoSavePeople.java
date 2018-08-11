package ArrayMatrix;

import java.util.Arrays;

import Util.Parser;

public class No885BoatstoSavePeople {

	public static void main(String[] args) {
		No885BoatstoSavePeople sol = new No885BoatstoSavePeople();
		Parser parser = new Parser();
		String t = "[1,2]\n" + 
				"3\n" + 
				"[3,2,2,1]\n" + 
				"3\n" + 
				"[3,5,3,4]\n" + 
				"5\n" + 
				"[1,7,6,4,4,7,5]\n" + 
				"8";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[] people = parser.parseArray(s[i]);
			int limit = Integer.parseInt(s[i+1]);
			int ans = sol.numRescueBoats(people, limit);
			System.out.println(ans);
		}
	}

    public int numRescueBoats(int[] people, int limit) {
        // --> O(nlogn), where n = people.length
        
        // ps. please remember the last one to boat by using i <= j! XD
        
        Arrays.sort(people);
        int i = 0, j = people.length - 1, boat = 0;
        while(i <= j){
            if(i != j && people[i] + people[j] <= limit){
                boat++;
                i++;
                j--;
            }else{ // 1-person boat Q_Q
                boat++;
                j--;
            }
        }
        return boat;
    }
}
