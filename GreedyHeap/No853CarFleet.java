package GreedyHeap;

import java.util.Arrays;
import java.util.Comparator;

import Util.Parser;

public class No853CarFleet {

	public static void main(String[] args) {
		No853CarFleet sol = new No853CarFleet();
		Parser parser = new Parser();
		String t = "12\n" + 
				"[10,8,0,5,3]\n" + 
				"[2,4,1,1,3]\n" + 
				"25\n" + 
				"[4,2,10,23,15]\n" + 
				"[10,12,5,2,10]\n" + 
				"10\n" + 
				"[3]\n" + 
				"[3]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=3) {
			int T = Integer.parseInt(s[i]);
			int[] p = parser.parseArray(s[i+1]);
			int[] v = parser.parseArray(s[i+2]);
			int ans = sol.carFleet(T, p, v);
			System.out.println(ans);
		}
	}
	
	public int carFleet(int T, int[] p, int[] v) {
        // --> time = O(nlogn + n), space = O(n), where n = number of cars
        
        // Greedy:
        // Given 2 cars c1 c0 and c0 is closer to end than c1.
        // if c1 is able to catch c0, then (T - p1)/v1 <= (T - p0)/v0
        // That is the time for c1 to end <= the time for c0 to the end.
        //
        // So sort all cars by start position in ascending order
        // Check fleets from the last car to the first car.
        // If the time for following car to end <= the time for leading car to end
        // then both cars are in the same fleet.
        // 
        // Use the leading car as a criterion, check the next following car.
        
        
        double[][] cars = new double[p.length][2]; // 0 - distance to end, 1 - speed
        for(int i = 0; i < cars.length; i++){
            cars[i][0] = (double) (T - p[i]);
            cars[i][1] = (double) v[i];
        }
        // Let the car at back if it has shorter distance to end
        Arrays.sort(cars, new Comparator<double[]>(){
            @Override
            public int compare(double[] i, double[] j){ return i[0] < j[0]? 1: -1; }
        });                                             // 1 - swap, -1 - not swap
        
        // for(int i = 0; i < cars.length; i++) System.out.print(Arrays.toString(cars[i])+", ");
        // System.out.println();
        
        int ret = 0;
        int lead = cars.length - 1;
        while(lead >= 0){
            double leaderTime = cars[lead][0]/cars[lead][1];
            for(int follow = lead - 1; follow >= -1; follow--){
                if(follow == -1){ ret++; lead = -1; break; }
                double followerTime = cars[follow][0]/cars[follow][1];
                if(followerTime > leaderTime){ ret++; lead = follow; break; }
            }
        }
        return ret;
	}
	
}
