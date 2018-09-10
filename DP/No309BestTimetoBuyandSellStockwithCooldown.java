package DP;

import Util.Parser;

public class No309BestTimetoBuyandSellStockwithCooldown {

	public static void main(String[] args) {
		No309BestTimetoBuyandSellStockwithCooldown sol = new No309BestTimetoBuyandSellStockwithCooldown();
		Parser parser = new Parser();
		String t = "[1,2,3,0,2]\n" + 
				"[]\n" + 
				"[1,2,4]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] p = parser.parseArray(s[i]);
			int ans = sol.maxProfit(p);
			System.out.println(ans);
		}
	}

	public int maxProfit(int[] p) {
        // --> O(n), where n = p.length
        
        // Thanks to:
        // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/discuss/75927/Share-my-thinking-process
        //
        // Let buy[i] and sell[i] denote the max profit we gain ending with buy and sell at day i.
        // buy[i] = max(sell[i-2]-price, buy[i-1]) , rest a day before buy, don't buy
        // sell[i] = max(buy[i-1]+price, sell[i-1]), sell today, hold it.
        //
        // Basic case:
        // sell[0] = 0
        // sell[1] = MAX(buy[0]+p[1], sell[0])
        // buy[0] = -p[0]
        // buy[1] = -p[1]
		
		if(p.length < 2) return 0;
        int[] sell = new int[3];
        int[] buy = new int[3];
        sell[0] = 0;
        buy[0] = -p[0];
        sell[1] = Math.max(buy[0]+p[1], sell[0]);
        buy[1] = Math.max(-p[1], buy[0]);
        for(int i = 2; i < p.length; i++){
            buy[2] = Math.max(sell[0]-p[i], buy[1]);
            sell[2] = Math.max(buy[1]+p[i], sell[1]);
            sell[0] = sell[1]; sell[1] = sell[2];
            buy[0] = buy[1]; buy[1] = buy[2];
        }
        return p.length == 2? sell[1]: sell[2];
    }
}
