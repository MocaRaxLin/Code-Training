package Math;

public class No836RectangleOverlap {

	public static void main(String[] args) {
		No836RectangleOverlap sol = new No836RectangleOverlap();
		//rec1 = [0,0,2,2], rec2 = [1,1,3,3]
		//rec1 = [0,0,1,1], rec2 = [1,0,2,1]
		//rec1 = [0,0,0,0], rec2 = [0,0,0,0]
		int[] rec1 = new int[] {-1,-1,0,0};
		int[] rec2 = new int[] {0,0,1,1};
		boolean ans = sol.isRectangleOverlap(rec1, rec2);
		System.out.println(ans);

	}
	
	public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
		// --> constant time
		// For each coordinate, we check 4 points x0, x1, y0, y1
		// eg. In X coordinate, x0 < A <= y0 < B <= y1 <= C.
		// Then we know position of x1 could be A, B or C.
		// However, only position A makes 2 rectangles not overlap possible,
		// so we check x and y coordinates, if either of them makes x1 at A, 
		// we return false, otherwise we return true.
		
        int x0, x1, y0, y1;
        //check y coordinate, 0, 2
        //check x coordinate, 1, 3
        for(int i = 0; i < 2; i++) {
        	if(rec1[i] < rec2[i]){
                x0 = rec1[i];
                x1 = rec1[i+2];
                y0 = rec2[i];
                y1 = rec2[i+2];
            }else{
                x0 = rec2[i];
                x1 = rec2[i+2];
                y0 = rec1[i];
                y1 = rec1[i+2];
            }
            if(x1 <= y0) return false;
        }
        return true;
    }
}
