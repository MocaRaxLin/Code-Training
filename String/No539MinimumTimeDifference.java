package String;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class No539MinimumTimeDifference {

	public static void main(String[] args) {
		No539MinimumTimeDifference sol = new No539MinimumTimeDifference();
		
		List<List<String>> inputs = new LinkedList<List<String>>();
		List<String> l1 = new LinkedList<String>();
		l1.add("23:59"); l1.add("00:00"); l1.add("4:2");
		inputs.add(l1);
		List<String> l2 = new LinkedList<String>();
		l2.add("7:00"); l2.add("12:00");
		inputs.add(l2);
		List<String> l3 = new LinkedList<String>();
		l3.add("0:1"); l3.add("0:30");
		inputs.add(l3);
		List<String> l4 = new LinkedList<String>();
		l4.add("00:00"); l4.add("23:59"); l4.add("00:00");
		inputs.add(l4);
		
		for(int i = 0; i < inputs.size(); i++) {
			int ans = sol.findMinDifference(inputs.get(i));
			System.out.println(ans);
		}
	}
	
	public int findMinDifference(List<String> timePoints) {
		// --> O(n), where n = timPoints.size()
		// By taking advantage of only 24*60 time slots,
		// we can create timeExist[24*60] to accelerate sorting part.
		// This is Counting Sort!!! It takes O(n)!!!
		
        boolean[] timeExist = new boolean[24*60];
        for(int i = 0; i < timePoints.size(); i++){
            String time[] = timePoints.get(i).split(":");
            int h = Integer.parseInt(time[0]);
            int m = Integer.parseInt(time[1]);
            //System.out.println("h: "+h+", m: "+m);
            if(timeExist[60*h+m]) return 0;
            else timeExist[60*h+m] = true;
        }
        
        int minTime = Integer.MAX_VALUE;
        int i = 0;
        while(!timeExist[i]) i++;
        int first = i, last = i;
        //System.out.println(i);
        i++;
        for(; i < timeExist.length; i++){
            if(timeExist[i]){
                minTime = Math.min(minTime, i - last);
                last = i;
                //System.out.println(i);
            }
        }
        minTime = Math.min(minTime, i + first - last);
        return minTime;
    }
	
	public int findMinDifference0(List<String> timePoints) {
		// --> O(NlogN), where N = timePoints.size()
		// 1. standardize format "1:3" -> "01:03" -> O(N)
		// 2. sorting -> O(NlogN)
		//    ["23:34","01:01","15:55","4:33"] -> ["01:01","4:33","15:55","23:34"]
		// 3. run a cycle to find minimum time difference. -> O(N)
		//    time difference = time[i+1] - time[i].
		//    Remember to check cross day! 24:00 + time[start] - time[end].
		
        String[] timeArray = new String[timePoints.size()];
        for(int i = 0; i < timePoints.size(); i++){
            String time[] = timePoints.get(i).split(":");
            char[] format = new char[5];
            format[2] = ':';
            for(int j = 0; j < 2; j++){
                if(time[j].length() == 1){
                	format[3*j] = '0';
                	format[3*j+1] = time[j].charAt(0);
                }else{
                	format[3*j] = time[j].charAt(0);
                	format[3*j+1] = time[j].charAt(1);
                }
            }
            timeArray[i] = new String(format);
        }
        
        Arrays.sort(timeArray);
        int minTime = Integer.MAX_VALUE;
        int d = clockSubstract(timeArray[timeArray.length-1], timeArray[0], true);
        minTime = Math.min(minTime, d);
        for(int i = 0; i < timeArray.length - 1; i++){
            d = clockSubstract(timeArray[i], timeArray[i+1], false);
            minTime = Math.min(minTime, d);
        }
        return minTime;
    }
    
    public int clockSubstract(String a, String b, boolean crossDay){
        if(crossDay){
            String[] t = b.split(":");
            int bH = 24 + Integer.parseInt(t[0]);
            b = String.valueOf(bH) + ":" + t[1];
        }
        String[] aT = a.split(":");
        int aH = Integer.parseInt(aT[0]);
        int aM = Integer.parseInt(aT[1]);
        String[] bT = b.split(":");
        int bH = Integer.parseInt(bT[0]);
        int bM = Integer.parseInt(bT[1]);
        return (bH -aH)*60 + bM-aM;
    }
}
