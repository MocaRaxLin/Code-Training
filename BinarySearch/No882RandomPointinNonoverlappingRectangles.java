package BinarySearch;

import java.util.Random;

public class No882RandomPointinNonoverlappingRectangles {
	
	// no test case for this problem
	// please practice on leetcode directly.
	// https://leetcode.com/problems/random-point-in-non-overlapping-rectangles/description/
	
	Random random;
    int[][] rects;
    int[] A;
    int sum = 0;
    public No882RandomPointinNonoverlappingRectangles(int[][] rects) {
        this.rects = rects;
        random = new Random();
        A = new int[rects.length];
        for(int i = 0; i < rects.length; i++){
            sum += (rects[i][2] - rects[i][0] + 1) * (rects[i][3] - rects[i][1] + 1);
            A[i] = sum;
        }
    }
    
    public int[] pick() {
        // pick up a rectangle by their propotions!
        // i.e. a larger area rectangle has higher chance to be chosen.
        
        // binary searching for i
        // Again think of an example if stuck
        // eg. [4, 10, 20 ,27]
        // -> 0:0~3, 1:4~9, 2:10~19, 3:20:26
        // let key = 11, m = 2
        // key(11) < A[m](20), so this m is probably I want, j = m.
        // m = 1
        // key(11) > A[m](10), m = 1 is not I want, because we know m+1 start at 10.
        // Thus i = m+1.
        
        int key = random.nextInt(sum);
        int i = 0, j = A.length;
        while(i<j){
            int m = i + (j-i)/2;
            if(key < A[m]) j = m;
            else i = m+1;
        }
        int[] r = rects[i];
        int dx = random.nextInt(r[2]-r[0]+1);
        int dy = random.nextInt(r[3]-r[1]+1);
        return new int[]{r[0]+dx, r[1]+dy};
    }
		

}
