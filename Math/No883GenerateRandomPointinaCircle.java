package Math;

public class No883GenerateRandomPointinaCircle {

	// no test case for this problem
	// please practice on leetcode directly.
	// https://leetcode.com/problems/generate-random-point-in-a-circle/description/
	
	// Thanks to:
    // https://programming.guide/random-point-within-circle.html
    
    double R;
    double x;
    double y;
    public No883GenerateRandomPointinaCircle(double radius, double x_center, double y_center) {
        R = radius;
        x = x_center;
        y = y_center;
    }
    
    public double[] randPoint() {
        double len = R*Math.sqrt(Math.random());
        double rad = 2*Math.PI*Math.random();
        return new double[]{x + len*Math.cos(rad), y + len*Math.sin(rad)};
    }
}
