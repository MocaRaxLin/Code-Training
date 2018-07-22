package ArrayMatrix;

import java.util.HashSet;
import java.util.Set;

import Util.Parser;

public class No874WalkingRobotSimulation {

	public static void main(String[] args) {
		No874WalkingRobotSimulation sol = new No874WalkingRobotSimulation();
		Parser parser = new Parser();
		String t = "[4,-1,3]\n" + 
				"[]\n" + 
				"[4,-1,4,-2,4]\n" + 
				"[[2,4]]\n" + 
				"[-2,8,3,7,-1]\n" + 
				"[[-4,-1],[1,-1],[1,4],[5,0],[4,5],[-2,-1],[2,-5],[5,1],[-3,-1],[5,-3]]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[] commands = parser.parseArray(s[i]);
			int[][] obstacles = parser.parseMatrix(s[i+1]);
			int ans = sol.robotSim(commands, obstacles);
			System.out.println(ans);
		}
	}

public int robotSim(int[] commands, int[][] obstacles) {
        
        // Encode data of obstacles
        Set<Long> set = new HashSet<Long>();
        for(int[] o: obstacles){
            long encode = o[0];
            encode <<= 16;
            encode += o[1];
            set.add(encode);
        }
        
        // Map 90 degree right rotation
        // (x,y) -> (r,c)
        
        int maxSquare = 0;
        int ror = 0, roc = 0;
        
        int[] dr = new int[]{0,1,0,-1};
        int[] dc = new int[]{1,0,-1,0};
        int d = 0;
        
        for(int c: commands){
            if(c == -1){ // turn right
                d = (d + 1)%4;
            }else if(c == -2){ // turn left
                d = (d + 3)%4; // we cannot apply negative number to % mod.
            }else{
                for(int step = 0; step < c; step++){
                    int nextR = ror + dr[d];
                    int nextC = roc + dc[d];
                    
                    long code = nextR;
                    code <<= 16;
                    code += nextC;
                    if(set.contains(code)) break;
                    
                    ror = nextR;
                    roc = nextC;
                    maxSquare = Math.max(maxSquare, ror*ror + roc*roc);
                }
            }
        }
        return maxSquare;
    }
}
