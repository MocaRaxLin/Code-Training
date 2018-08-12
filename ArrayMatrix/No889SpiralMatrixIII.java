package ArrayMatrix;

import Util.Show;

public class No889SpiralMatrixIII {

	public static void main(String[] args) {
		No889SpiralMatrixIII sol = new No889SpiralMatrixIII();
		Show show = new Show();
		String t = "1\n" + 
				"4\n" + 
				"0\n" + 
				"0\n" + 
				"3\n" + 
				"4\n" + 
				"0\n" + 
				"0\n" + 
				"4\n" + 
				"5\n" + 
				"1\n" + 
				"4\n" + 
				"93\n" + 
				"61\n" + 
				"55\n" + 
				"60";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=4) {
			int R = Integer.parseInt(s[i]);
			int C = Integer.parseInt(s[i+1]);
			int r0 = Integer.parseInt(s[i+2]);
			int c0 = Integer.parseInt(s[i+3]);
			int[][] ans = sol.spiralMatrixIII(R, C, r0, c0);
			show.showMatrix(ans);
		}
	}

	public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
		// --> O(RC)
		
		// Intuition:
		// Use top, bot, left, right to frame positions we've met.
		// Whenever we expend our frame we turn right.
		// If we walk out of map[R][C],
		// we go back to the side of same border but new position out of our frame.
		// i.e. That is the while loop does.
		
		// Please remember!
		// Don't try to initialize a matrix in the beginning,
		// because maybe you don't need that much space.
		
        int[] di = new int[]{0,1,0,-1};
        int[] dj = new int[]{1,0,-1,0};
        int t = 0; // 0 - right, 1 - bot, 2 - left, 3 - top
        
        int top = r0;
        int bot = r0;
        int left = c0;
        int right = c0;
        
        int[][] ret = new int[R*C][2];
        int size = 0;
        ret[size++] = new int[]{r0, c0};
        // System.out.println("("+r0+","+c0+")");
        
        while(size < R*C){
            r0 += di[t];
            c0 += dj[t];
            // System.out.println("("+r0+","+c0+")");
            // System.out.println("(top: "+top+", "+"bot: "+bot+", left: "+left+", right: "+right+")");
            
            while(r0 < 0 || c0 < 0 || R <= r0 || C <= c0){
                // System.out.println("In while: ("+r0+","+c0+")");
                if(t == 0){ // right
                    r0 = bot + 1;
                    c0 = C - 1;
                }else if(t == 1){ //bot
                    r0 = R - 1;
                    c0 = left - 1;
                }else if(t == 2){ //left
                    r0 = top - 1;
                    c0 = 0;
                }else if(t == 3){ // top
                    r0 = 0;
                    c0 = right + 1;
                }
                t+=1;
                t%=4;
            }
            
            // System.out.println("Final: ("+r0+","+c0+")");
            ret[size++] = new int[]{r0, c0};
            
            // change direction if needed
            if(right < c0){ right = c0; t++; }
            else if(bot < r0){ bot = r0; t++; }
            else if(c0 < left){ left = c0; t++; }
            else if(r0 < top){ top = r0; t++; }
            t %= 4;
            
        }
        // System.out.println();
        return ret;
    }
}
