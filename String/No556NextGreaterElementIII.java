package String;

public class No556NextGreaterElementIII {

	public static void main(String[] args) {
		No556NextGreaterElementIII sol = new No556NextGreaterElementIII();
		
		int[] input = new int[] {
				534976,
				10,
				123456789,
				464710,
				2346785,
				4123,
				12,
				10
		};
		for(int i = 0; i < input.length; i++) {
			int ans = sol.nextGreaterElement(input[i]);
			System.out.println(ans);
		}
	}
	
	public int nextGreaterElement(int n) {
		// time --> O(N), space --> O(N), where N = number of digits in n.
		
		// https://www.geeksforgeeks.org/find-next-greater-number-set-digits/
		// hard to think of a solution QQ
		
		
        char[] c = ("" + n).toCharArray();
        int len = c.length;
        if(len == 0) return -1;
        
        // I. check if String n is descending.
        // if so, there is no way to make greater number
        // Also it handles single-digit number
        int i = 1;
        while(i < len && c[i - 1] >= c[i]) i++;
        if(i == len) return -1;
        
        // II. other cases
        // scan from the right, find the first position
        // that c[i] < c[i+1]
        i = len - 2;
        while(i >= 0){
            if(c[i] < c[i + 1]) break;
            i--;
        }
        
        // III. scan from i + 1 to the end, find the position at minGD,
        // that c[minGD] is the smallest number that greater than c[i]
        int minGD = i + 1; // index of minimum greater digit
        for(int j = i + 1; j < c.length; j++)
            if(c[j] > c[i] && c[j] <= c[minGD]) minGD = j;
        
        // swap char at i with minGD
        char tmp = c[i];
        c[i] = c[minGD];
        c[minGD] = tmp;
        
        // sort c[i+1, len-1] in ascending order to make it smallest
        // here we use bucket sort, because only sort 0 to 9
        // time --> O(N)
        bucketSort(c, i+1);
        
        long val = Long.parseLong(new String(c));
        return val > Integer.MAX_VALUE ? -1 : (int) val;
    }
    
    public void bucketSort(char[] c, int start){
        // bucket sort --> O(n)
        int[] bucket = new int[10];
        for(int i = start; i < c.length; i++) bucket[c[i] -'0']++;
        int p = start;
        for(int i = 0; i < bucket.length; i++){
            while(bucket[i] > 0){
                c[p] = (char)(i + '0');
                bucket[i]--;
                p++;
            }
        }
    }
}
