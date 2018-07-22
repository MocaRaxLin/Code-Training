package DP;

import java.util.ArrayList;
import java.util.List;

import Util.Parser;
import Util.Show;

public class No638ShoppingOffers {

	public static void main(String[] args) {
		Parser parser = new Parser();
		No638ShoppingOffers sol = new No638ShoppingOffers();
		
		// input Constraints:
		// - There are at most 6 kinds of items, 100 special offers.
		// - For each item, you need to buy at most 6 of them.
		// - You are not allowed to buy more items than you want, even if that would lower the overall price.
		String t = "[2,5]\n" + 
				"[[3,0,5],[1,2,10]]\n" + 
				"[3,2]\n" + 
				"[6,2,8,6,10,5]\n" + 
				"[[5,1,6,2,0,2,19],[3,3,5,3,5,2,2],[6,0,4,3,2,0,14],[5,5,4,1,6,3,23],[3,0,5,2,1,5,35],[1,5,4,3,1,2,36],[5,3,5,4,3,0,1],[6,6,4,2,4,1,5],[3,3,2,6,1,0,33],[2,5,1,2,4,6,23],[3,6,2,6,2,6,14],[6,6,0,3,3,4,17],[0,4,5,3,5,0,15],[6,1,0,6,4,0,14],[6,4,4,3,3,5,8],[4,2,4,3,6,2,30],[3,4,0,3,1,4,3],[4,2,6,3,3,4,12],[6,4,2,5,1,5,16],[3,1,0,0,3,2,3]]\n" + 
				"[2,4,5,3,6,3]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=3) {
			List<Integer> price = parser.parseList(s[i], true);
			List<List<Integer>> special = parser.parseListList(s[i+1], true);
			List<Integer> needs = parser.parseList(s[i+2], true);
			int ans = sol.shoppingOffers(price, special, needs);
			System.out.println(ans);
		}
	}

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
    	// --> time O(6*1000)
    	
    	// Using recursion.
    	// eg. dp[A,B] = min(A*Pa + B*Pb, dp[A-ai, B-bi] + Pi) for every special offer i.
    	// Because the input constraint limits A and B,
    	// recursion is faster than dynamin programming
    	
        int minPrice = 0;
        for(int i = 0; i < price.size(); i++) minPrice += price.get(i) * needs.get(i);
        for(int i = 0; i < special.size(); i++){
            List<Integer> sp = special.get(i);
            List<Integer> childNeeds = new ArrayList<Integer>();
            if(getChildNeeds(needs, sp, childNeeds)){
                int minChildPrice = shoppingOffers(price, special, childNeeds);
                minPrice = Math.min(minPrice, minChildPrice + sp.get(sp.size() - 1));
            }
        }
        return minPrice;
    }
    public boolean getChildNeeds(List<Integer> needs, List<Integer> sp, List<Integer> childNeeds){
        for(int i = 0; i < needs.size(); i++){
            int chNdi = needs.get(i) - sp.get(i);
            if(chNdi < 0) return false;
            childNeeds.add(chNdi);
        }
        return true;
    }
    
    
    public int shoppingOffersDP(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int[] p = new int[price.size()];
        for(int i = 0; i < price.size(); i++) p[i] = price.get(i);
        
        int[] nd = new int[6];
        for(int i = 0; i < needs.size(); i++) nd[i] = needs.get(i);
        
        int[][][][][][] dp = new int[7][7][7][7][7][7];
        
        // basic case dp[0][0][0][0][0][0] = 0
        
        // fill up table dp
        //System.out.println(Arrays.toString(nd));
        int[] i = new int[6];
        for(;i[0] <= nd[0]; i[0]++){ for(;i[1] <= nd[1]; i[1]++){ for(;i[2] <= nd[2]; i[2]++){
        for(;i[3] <= nd[3]; i[3]++){ for(;i[4] <= nd[4]; i[4]++){ for(;i[5] <= nd[5]; i[5]++){
            //System.out.println(Arrays.toString(i));
            // Compute one by one
            for(int o = 0; o < p.length; o++) dp[i[0]][i[1]][i[2]][i[3]][i[4]][i[5]] += p[o]*i[o];
            // Use special offers
            for(int s = 0; s < special.size(); s++){
                List<Integer> sp = special.get(s);
                int[] j = new int[6];
                if(useSpecial(sp, i, j)){
                    dp[i[0]][i[1]][i[2]][i[3]][i[4]][i[5]] = Math.min(
                        dp[i[0]][i[1]][i[2]][i[3]][i[4]][i[5]],
                        dp[j[0]][j[1]][j[2]][j[3]][j[4]][j[5]] + sp.get(sp.size() - 1));
                }
            }
            //System.out.println(dp[i[0]][i[1]][i[2]][i[3]][i[4]][i[5]]);
        } i[5] = 0;} i[4] = 0;} i[3] = 0;} i[2] = 0;} i[1] = 0;}
        return dp[nd[0]][nd[1]][nd[2]][nd[3]][nd[4]][nd[5]];
    }
    
    public boolean useSpecial(List<Integer> sp, int[] cur, int[] from){
        int items = sp.size() - 1;
        for(int i = 0; i < items; i++){
            from[i] = cur[i] - sp.get(i);
            if(from[i] < 0) return false;
        }
        return true;
    }
}
