package BitOperation;

import java.util.Arrays;
import java.util.Comparator;

import Util.Parser;

public class ExtraSortByNumberOfOne {

	public static void main(String[] args) {
		ExtraSortByNumberOfOne sol = new ExtraSortByNumberOfOne();
		Parser parser = new Parser();
		String t = "[3,5,3,1,5,7,9,3,9,3]\n" + 
				"[1,8,53,3,124,324123,7777777]\n" + 
				"[1,2,3,4,5,6,7,8,9,10]\n" + 
				"[0,1,4,8,16,32]\n" + 
				"[1]\n" + 
				"[0]\n" + 
				"[]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] A = parser.parseArray(s[i]);
			sol.sortByNumberOfOne(A);
			System.out.println(Arrays.toString(A));
		}
	}

	private void sortByNumberOfOne(int[] A) {
		
		Comparator<Integer> cmp = new Comparator<Integer>() {
			public int compare(Integer a, Integer b) {
				int oneA = getOnes(a);
				int oneB = getOnes(b);
				return oneA == oneB? a - b: oneA - oneB;
			}

			private int getOnes(Integer a) {
				int count = 0, n = a;
				while(n > 0) {
					if((n&1) == 1) count++;
					n >>= 1;
				}
				return count;
			}	
		};
		Integer[] AInts = new Integer[A.length];
		for(int i = 0; i < A.length; i++) AInts[i] = A[i];
		Arrays.sort(AInts, cmp);
		for(int i = 0; i < A.length; i++) A[i] = AInts[i];
	}

}
