package HashMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import Util.Parser;

public class ExtraLatestStudent {

	public static void main(String[] args) {
		ExtraLatestStudent sol = new ExtraLatestStudent();
		Parser parser = new Parser();
		String t = "[[\"09-01\",\"App\",\"540\",\"570\"],[\"09-01\",\"Bob\",\"540\",\"543\"],[\"09-01\",\"Catlyn\",\"540\",\"530\"],[\"09-02\",\"App\",\"540\",\"580\"],[\"09-02\",\"Bob\",\"540\",\"580\"],[\"09-02\",\"Catlyn\",\"540\",\"595\"]]\n"
				+ "[]";
		t = t.replace("\"", "");
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			String[][] attendances = parser.parseMatrixStr(s[i]);
			String ans = sol.findLatest(attendances);
			System.out.println(ans);
		}
	}

	private String findLatest(String[][] attendances) {
		if(attendances.length == 0) return "";
		Map<String, int[]> map = new HashMap<String, int[]>();
		for(String[] data: attendances) {
			int late = Integer.parseInt(data[3]) - Integer.parseInt(data[2]);
			late = late < 0? 0: late;
			int[] lateCount = new int[] {0, 0}; // sum of late, number of data
			if(map.containsKey(data[0])) lateCount = map.get(data[0]);
			lateCount[0] += late;
			lateCount[1]++;
			map.put(data[0], lateCount);
		}
		Map<String, Double> aveLateDate = new HashMap<String, Double>(); // average late of date
		for(Entry<String, int[]> e: map.entrySet()) {
			int[] v = e.getValue();
			aveLateDate.put(e.getKey(), (double) v[0]/ (double) v[1]);
			//System.out.println("date: "+ e.getKey() + ", aveLateDate: "+ aveLateDate.get(e.getKey()));
		}
		
		Map<String, Double> late_p = new HashMap<String, Double>();
		String latest = "";
		double maxSum = 0;
		for(String[] data: attendances) {
			int late = Integer.parseInt(data[3]) - Integer.parseInt(data[2]);
			double r_late = (double) late - aveLateDate.get(data[0]);
			r_late = r_late < 0? 0: r_late;
			double sumOfLate = 0;
			if(late_p.containsKey(data[1])) sumOfLate = late_p.get(data[1]);
			sumOfLate += r_late;
			if(maxSum < sumOfLate || (maxSum == sumOfLate && latest.compareTo(data[0]) > 0)) {
				latest = data[1];
				maxSum = sumOfLate;
			}
			late_p.put(data[1], sumOfLate);
		}
		// for(Entry<String, Double> e: late_p.entrySet()) System.out.println("name: "+ e.getKey() + ", totalLate: "+ e.getValue());
		return latest;
	}

}
