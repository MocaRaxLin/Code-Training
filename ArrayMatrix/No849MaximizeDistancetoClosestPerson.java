package ArrayMatrix;

import Util.Parser;

public class No849MaximizeDistancetoClosestPerson {

	public static void main(String[] args) {
		No849MaximizeDistancetoClosestPerson sol = new No849MaximizeDistancetoClosestPerson();
		Parser parser = new Parser();
		String t = "[1,0,0,0,1,0,1]\n" + 
				"[1,0,0]\n" + 
				"[0,1]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] seats = parser.parseArray(s[i]);
			int ans = sol.maxDistToClosest(seats);
			System.out.println(ans);
		}
	}

    public int maxDistToClosest(int[] seats) {
    	// --> time O(n), where n = seats.length
    	
    	// Go though the seats and find the longest distance between two 1's.
    	// If the first or the last is 0, than use the whole length
    	
    	// Be careful about Integer divided by 2,
    	// break when you find the target while looping
    	// test edge cases like [0], [0,1] [1,0].
    	
        int dis = 0;
        int last = 0;
        int idx = 0;
        for(; idx < seats.length - 1; idx++){
            if(seats[idx] == 1){
                dis = idx;
                if(seats[0] == 1) dis/=2;
                last = idx;
                break;
            }
        }
        for(; idx < seats.length - 1; idx++){
            if(seats[last] == 1 && seats[idx] == 1){
                int newDis = idx - last;
                newDis/=2;
                dis = Math.max(dis, newDis);
                last = idx;
            }
        }
        int newDis = idx - last;
        if(seats[last] == 1 && seats[idx] == 1) newDis/=2;
        dis = Math.max(dis, newDis);
        return dis;
    }
}
