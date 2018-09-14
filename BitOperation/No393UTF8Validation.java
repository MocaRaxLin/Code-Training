package BitOperation;

import Util.Parser;

public class No393UTF8Validation {

	public static void main(String[] args) {
		No393UTF8Validation sol = new No393UTF8Validation();
		Parser parser = new Parser();
		String t = "[197,130,1]\n" + 
				"[235,140,4]\n" + 
				"[255,255]\n" + 
				"[0,42,74,123]\n" + 
				"[237]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] data = parser.parseArray(s[i]);
			boolean ans = sol.validUtf8(data);
			System.out.println(ans);
		}
	}

	public boolean validUtf8(int[] data) {
        if(data.length == 0) return true;
        
        int c1 = 1<<7, r1 = 0;
        int c2 = 7<<5, r2 = 3<<6;
        int c3 = 15<<4, r3 = 7<<5;
        int c4 = 31<<3, r4 = 15<<4;
        int ap = 3<<6, rap = 1<<7;
        
        int p = 0;
        while(p < data.length){
            int test = data[p++];
            if((test&c1) == r1) continue;
            if((test&c2) == r2){
                int i = 1;
                while(i-->0){
                    if(p == data.length || (data[p++]&ap) != rap) return false;
                }
            }else if((test&c3) == r3){
                int i = 2;
                while(i-->0){
                    if(p == data.length || (data[p++]&ap) != rap) return false;
                }
            }else if((test&c4) == r4){
                int i = 3;
                while(i-->0){
                    if(p == data.length || (data[p++]&ap) != rap) return false;
                }
            }else{
                return false;
            }
        }
        return true;
    }
}
