package ArrayMatrix;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

import Util.Parser;
import Util.Show;

public class ExtraCPUScheduling {

	public static void main(String[] args) {
		ExtraCPUScheduling sol = new ExtraCPUScheduling();
		Parser parser = new Parser();
		String t = "[0,1,4]\n" + 
				"[5,2,3]\n" + 
				"3\n" + 
				"[0,1,3,9]\n" + 
				"[2,1,7,5]\n" + 
				"2\n" + 
				"[0,1,3,5,6]\n" + 
				"[5,3,6,1,4]\n" + 
				"3";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=3) {
			int[] arriTime = parser.parseArray(s[i]);
			int[] exeTime = parser.parseArray(s[i+1]);
			int slot = Integer.parseInt(s[i+2]);
			double ans = sol.getAverageWaitingTime(arriTime, exeTime, slot);
			System.out.println(ans);
		}
	}

	private double getAverageWaitingTime(int[] arriTime, int[] exeTime, int slot) {
		// --> O(N/slot), where N = sum(exeTime)
		
		// Intuition:
		// Just do as cpu does.
		
		// 1. get a task T from Q if Q.size > 0, or take new task[i++]
		// 2. extend current time by current slot = min(exeTime of T, fix slot)
		// 3. accumulate waiting time of taskes in Q by waiting += q.size()*curSlot;
		// 4. update curTime, check new arrived tasks,
		//    acculmulate wating time of new tasks by waiting += curTime - arrivalTimeOfNewTask one by one.
		// 5. if T's remaining time > 0, put it back to Q.
		// 6. Stop when there is no task remains.
		
		if(arriTime.length == 0) return 0;
		
		// sort all tasks by arrival time
		int[][] tasks = new int[arriTime.length][2];
		for(int i = 0; i < arriTime.length; i++)  tasks[i] = new int[] {arriTime[i], exeTime[i]};
		Arrays.sort(tasks, new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {return a[0] - b[0]; }
		});
		
		//Show show = new Show();
		//show.showMatrix(tasks);
		
		int curTime = 0;
		float waiting = 0;
		Queue<int[]> q = new LinkedList<int[]>();
		
		int i = 0;
		while(i < tasks.length) {
			if(q.size() == 0) q.offer(tasks[i++]);
			while(q.size() > 0) {
				int[] t = q.poll();
				int curSlot = t[1] < slot? t[1]: slot;
				
				waiting += q.size()*curSlot;
				curTime += curSlot;
				
				while(i < tasks.length && tasks[i][0] <= curTime) {
					waiting += curTime - tasks[i][0];
					q.offer(tasks[i++]);
				}
				
				t[1] -= slot;
				if(t[1] > 0) q.offer(t);
			}
		}

		
		return waiting/tasks.length;
	}

}
