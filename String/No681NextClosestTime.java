package String;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class No681NextClosestTime {

	public static void main(String[] args) {
		No681NextClosestTime sol = new No681NextClosestTime();
		String t = "\"23:59\"\n" + 
				"\"19:34\"\n" + 
				"\"00:00\"\n" + 
				"\"14:25\"";
		t = t.replace("\"", "");
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			String ans = sol.nextClosestTime(s[i]);
			System.out.println(s[i]);
			System.out.println(ans);
		}
	}
	
	List<String> candid;
    boolean[] used;
    public String nextClosestTime(String time) {
        candid = new LinkedList<String>();
        used = new boolean[10];
        for(int i = 0; i < time.length(); i++){
            if(i == 2) continue;
            used[time.charAt(i) - '0'] = true;
        }
        // System.out.println(Arrays.toString(used));
        char[] sb = new char[5];
        collectCandid(sb, 0);
        
        String ret1 = "";
        int maxTime1 = Integer.MAX_VALUE;
        String ret2 = "";
        int maxTime2 = Integer.MAX_VALUE;
        Iterator<String> it = candid.iterator();
        while(it.hasNext()){
            String cur = it.next();
            // int t = timeSubstract(cur, time); // next close;
            int t = timeSubstract(time, cur); // prev close;
            // System.out.println(cur + "->" + t);
            if(t > 0 && t < maxTime1){
                ret1 = cur;
                maxTime1 = t;
            }else if(t < 0 && t < maxTime2){
                ret2 = cur;
                maxTime2 = t;
            }
        }
        return ret1.length() == 0? ret2.length() == 0? time: ret2: ret1;
    }
    
    private int timeSubstract(String to, String from){
        int a = toMin(to);
        int b = toMin(from);
        return a - b;
    }
    
    private int toMin(String time){
        int h = time.charAt(0)*10 + time.charAt(1);
        int m = time.charAt(3)*10 + time.charAt(4);
        return h*60+m;
    }
    
    private void collectCandid(char[] sb, int p){
        if(p == sb.length){
            candid.add(new String(sb));
            return;
        }
        for(int i = 0; i < used.length; i++){
            if(p == 0 && i > 2) break;
            if(p == 1 && sb[0] == '2' && i > 3) break;
            if(p == 3 && i > 5) break;
            if(used[i]){
                sb[p++] = (char)('0'+i);
                if(p == 2) sb[p++] = ':';
                collectCandid(sb, p);
                if(p == 3) p--;
                p--;
            }
        }
    }

}
