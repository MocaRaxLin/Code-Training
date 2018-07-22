package String;

public class No788RotatedDigits {

	public static void main(String[] args) {
		No788RotatedDigits sol = new No788RotatedDigits();
		
		int N = 10;
		int ans = sol.rotatedDigits(N);
		System.out.println(ans);

	}
	
	public int rotatedDigits(int N) {
		// -> O(NlogN), where logN is # of digits of N
        int ret = 0;
        for(int i = 1; i <= N; i++){
            String origin = String.valueOf(i); // int to String, ps. Integer.parseInt(String); String to int
            if(origin.matches("[0|1|2|5|6|8|9]+")){ //RegExp is powerful remember it!
                String rotated = "";
                for(char c : origin.toCharArray()){
                    if(c == '2') rotated+='5';
                    else if(c == '5') rotated+='2';
                    else if(c == '6') rotated+='9';
                    else if(c == '9') rotated+='6';
                    else rotated+=c;
                }
                //System.out.println("origin: "+origin+", new: "+ rotated);
                if(!origin.equals(rotated)) ret++;
            }
        }
        return ret;
    }
}
