package ArrayMatrix;

import java.util.Arrays;

import Util.Parser;

public class No350IntersectionofTwoArraysII {

	public static void main(String[] args) {
		No350IntersectionofTwoArraysII sol = new No350IntersectionofTwoArraysII();
		Parser parser = new Parser();
		String t = "[]\n" + 
				"[]\n" + 
				"[1,1,2,2]\n" + 
				"[2,1,2,4]\n" + 
				"[2,3,4]\n" + 
				"[4,3,2]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[] A = parser.parseArray(s[i]);
			int[] B = parser.parseArray(s[i+1]);
			int[] ans = sol.intersect(A, B);
			System.out.println(Arrays.toString(ans));
		}
	}
	
	public int[] intersect(int[] A, int[] B) {
        // --> time = O(nlogn + mlogm + n + m), where n = A.length, m = B.length
        // 
		// The interesting thing is that
		// Hashmap does not go that fast O(n+m) @_@
		//
		// Using 1st follow-up method.
        // Pick up the common elements.
        
        Arrays.sort(A);
        Arrays.sort(B);
        int[] ret = new int[Math.min(A.length, B.length)];
        int i = 0, j = 0, k = 0;
        while(i < A.length && j < B.length){
            if(A[i] == B[j]){
                ret[k] = A[i];
                i++;
                j++;
                k++;
            }else if(A[i] < B[j]){
                i++;
            }else if(B[j] < A[i]){
                j++;
            }
        }
        return Arrays.copyOfRange(ret, 0, k);
    }
	
	// Follow up:
	//
	// What if the given array is already sorted? How would you optimize your algorithm?
	// See the code aboved.
	//
	// What if A's size is small compared to B's size? Which algorithm is better?
	// We can use binary search if |A| = n << |B| = m
	// But we have to compare O(nlogm) and O(n+m) which one is better to decide our method.
	// 
	// What if elements of B are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
	// Read small part first and then second, third, and so on. (ie. B[0:t-1], B[t:s-1], B[s:...])
	// Update A by removing intersected elements.
	// --> time = O(nlogn + k * [(m/k)log(m/k) + n] )
	//     sort A, deal with k parts, each part is size of m/k and update A
	//          = O( nlogn + m(logm - logk) + kn )
	// if k = 1 -> O(nlogn + mlogm)
	// if k = m -> O(nlogn + mn)
	//    --> we can use binary search O(mlogn) if n is still large.
}
