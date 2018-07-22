package GreedyHeap;

import java.util.Arrays;
import java.util.Comparator;

import Util.Parser;

public class No870AdvantageShuffle {

	public static void main(String[] args) {
		No870AdvantageShuffle sol = new No870AdvantageShuffle();
		Parser parser = new Parser();
		String t = "[2,7,11,15]\n" + 
				"[1,10,4,11]\n" + 
				"[4,3,2]\n" + 
				"[1,2,3]\n" + 
				"[5,4]\n" + 
				"[1,2]\n" + 
				"[2,1,2,5,2,1,3,1,1]\n" + 
				"[1,1,1,1,1,1,2,3,4]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[] A = parser.parseArray(s[i]);
			int[] B = parser.parseArray(s[i+1]);
			int[] ans = sol.advantageCount(A, B);
			System.out.println(Arrays.toString(ans));
		}
	}

	public int[] advantageCount(int[] A, int[] B) {
		// --> time = O(nlogn), where n = A.length
		// sorting + two pointer
		
		// Intuition:
		// Fix array B and pick elements in A into the right slot in the return array ret.
		//
		// Greedy:
		// Consider which slot to put the smallest A in the rest slots of B.
		//     If smallest A > smallest B, then put this smallest A here.
		//         Close this slot of smallest B and find the next smallest of A.
		//     Else smallest A <= smallest B, then put this A to the slot of biggest B
		//         Close this slot of biggest B and find the next smallest of A.
		
        if(A.length == 0) return new int[]{};
        Arrays.sort(A);
        int[][] idxB = new int[B.length][2];
        for(int i = 0; i < idxB.length; i++){
            idxB[i][0] = B[i];
            idxB[i][1] = i;
        }
        Arrays.sort(idxB, new Comparator<int[]>(){
            @Override
            public int compare(int[] i, int[] j){ return i[0] - j[0]; };
        });
        int[] ret = new int[A.length];
        int pA = 0, start = 0, end = idxB.length -1;
        while(pA < A.length){
            if(A[pA] > idxB[start][0]){
                ret[idxB[start][1]] = A[pA];
                start++;
            }else{
                ret[idxB[end][1]] = A[pA];
                end--;
            }
            pA++;
        }
        return ret;
    }
}
