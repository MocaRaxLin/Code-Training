package String;

import java.util.LinkedList;
import java.util.List;

import Util.Show;

public class No816AmbiguousCoordinates {

	public static void main(String[] args) {
		No816AmbiguousCoordinates sol = new No816AmbiguousCoordinates();
		Show show = new Show();
		
		String[] input = new String[] {
				"(123)", "(00011)", "(0123)", "(100)"
		};
		for(int i = 0 ; i < input.length; i++) {
			List<String> ans = sol.ambiguousCoordinates(input[i]);
			show.showList(ans, true);
		}
	}

    public boolean isValid(String number){
    	// Use Regexp to check the number is valid
        return number.matches("(0|[1-9]\\d*)(\\.\\d*[1-9])?");
    }
    
	public List<String> ambiguousCoordinates(String S) {
		// We separate S and put a dot into each part (left and right)
		// collect valid string number, make combination based on cl and cr
		// -> N^3, where N = S.length()
		// separate -> N, put dot -> N, combination -> (N/2)(N/2)
		// -> N*(N + (1/4)*N^2)
		// -->O(N^3)
		
        List<String> ret = new LinkedList<String>();
        String number = S.substring(1, S.length() - 1);
        String left = "", right = number;
        for(int i = 1; i < number.length(); i++){
            left += right.charAt(0);
            right = right.substring(1);
            //System.out.println("left= "+left+", right= "+right);
            LinkedList<String> cl = new LinkedList<String>();
            LinkedList<String> cr = new LinkedList<String>();
            
            if(isValid(left)) cl.add(left);
            for(int j = 1; j < left.length(); j++){
                String wdNumber = left.substring(0, j) + "." + left.substring(j);
                if(isValid(wdNumber)) cl.add(wdNumber);
            }
            if(isValid(right)) cr.add(right);
            for(int j = 1; j < right.length(); j++){
                String wdNumber = right.substring(0, j) + "." + right.substring(j);
                if(isValid(wdNumber)) cr.add(wdNumber);
            }
            
            for(int h = 0; h < cl.size(); h++){
                for(int k = 0; k < cr.size(); k++){
                    ret.add("("+cl.get(h)+", "+cr.get(k)+")");
                }
            }
        }
        return ret;
    }
	

}
