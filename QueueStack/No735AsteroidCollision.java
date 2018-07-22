package QueueStack;

import java.util.Arrays;

import Util.Parser;

public class No735AsteroidCollision {

	public static void main(String[] args) {
		No735AsteroidCollision sol = new No735AsteroidCollision();
		Parser parser = new Parser();
		String t = "[5,10,-5]\n" + 
				"[8,-8]\n" + 
				"[10,2,-5]\n" + 
				"[-2,-1,1,2]\n" + 
				"[1,0,-1]\n" + 
				"[]\n" + 
				"[-2,-2,1,-2]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] asteroids = parser.parseArray(s[i]);
			int[] ans = sol.asteroidCollision(asteroids);
			System.out.println(Arrays.toString(ans));
		}
	}

	public int[] asteroidCollision(int[] asteroids) {
		// --> time = O(n), where n = asteroids.length
		
		// Hint:
		// Say a row of asteroids is stable.
		// What happens when a new asteroid is added on the right?
		
		// If top > 0 and in < 0
		//     top + in > 0 -> ignore in -> check next
		//     top + in = 0 -> eliminate both -> pop and check next
		//     top + in < 0 -> pop top and check again -> pop
		// else
		//    push in -> check next
		
        int[] stack = new int[asteroids.length];
        int size = 0;
        
        int i = 0;
        while(i < asteroids.length){
            if(size == 0 || size > 0 && stack[size-1] <= 0 || asteroids[i] >= 0){
                stack[size++] = asteroids[i];
                i++;
            }else if(stack[size-1] + asteroids[i] > 0){ // ignore in
                i++;
            }else if(stack[size-1] + asteroids[i] < 0){ // pop top
                size--;
            }else{ // eliminate both
                i++;
                size--;
            }
        }
        return Arrays.copyOfRange(stack, 0, size);
    }
}
