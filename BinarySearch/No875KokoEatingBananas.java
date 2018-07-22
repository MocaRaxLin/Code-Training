package BinarySearch;

import Util.Parser;

public class No875KokoEatingBananas {

	public static void main(String[] args) {
		No875KokoEatingBananas sol = new No875KokoEatingBananas();
		Parser parser = new Parser();
		String t = "[3,6,7,11]\n" + 
				"8\n" + 
				"[30,11,23,4,20]\n" + 
				"5\n" + 
				"[30,11,23,4,20]\n" + 
				"6\n" + 
				"[332484035, 524908576, 855865114, 632922376, 222257295, 690155293, 112677673, 679580077, 337406589, 290818316, 877337160, 901728858, 679284947, 688210097, 692137887, 718203285, 629455728, 941802184]\n" + 
				"823855818";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[] piles = parser.parseArray(s[i]);
			int H = Integer.parseInt(s[i+1]);
			int ans = sol.minEatingSpeed(piles, H);
			System.out.println(ans);
		}
	}
	
	public int minEatingSpeed(int[] piles, int H) {
        // --> time = O(NlogM), M is the max value in piles, N is piles.length
		
		// Intuition:
		// The best way to comsume all piles in minimal time
		// is eat the max value as K in our piles.
		// However that is not the minimal K we want.
		
		// So let's find the "minima"l K in [1,max] using binary search
		// i = 1, j = max+1, -> m
		// compute the hour h needed to finish piles
		// if h > H then i = m + 1. (m is not we want)
		// Else h <= H then j = m. (m is we want, so keep m for i+1 to reach it)
		
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < piles.length; i++){
            max = max < piles[i] ? piles[i]: max;
        }
        
        int i = 1, j = max+1;
        while(i < j){
            int m = i + (j-i)/2;
            int h = 0;
            for(int p: piles){
                h += p/m;
                if(p%m != 0) h++;
            }
            if(h > H) i = m+1;
            else j = m;
        }
        return i;
    }
}
