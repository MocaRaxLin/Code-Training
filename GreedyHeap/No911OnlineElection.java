package GreedyHeap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class No911OnlineElection {
	
	int[] ocur;
    int[] last;
    int[] top;
    public No911OnlineElection(int[] persons, int[] times) {
    	// --> O(nlogn)
        ocur = new int[5000];
        last = new int[5000];
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>(){
            public int compare(Integer i, Integer j){ return ocur[j] == ocur[i]? last[j] - last[i]: ocur[j] - ocur[i]; }
        });
        top = new int[times[times.length-1]+1];
        int idx = 0;
        for(int i = 0; i < times.length; i++){
            while(idx<times[i]){
                // System.out.print(idx+", ");
                top[idx++] = pq.size() > 0? pq.peek(): -1;
            }
            // System.out.println("top: " + pq.peek());
            
            ocur[persons[i]]++;
            last[persons[i]] = times[i];
            
            // System.out.println(persons[i] +", "+times[i]);
            // System.out.println(Arrays.toString(ocur));
            // System.out.println(Arrays.toString(last));
            
            pq.remove(persons[i]);
            pq.add(persons[i]);
        }
        top[idx++] = pq.size() > 0? pq.peek(): -1;
    }
    
    public int q(int t) {
    	// --> O(1)
        return t >= top.length? top[top.length-1]: top[t];
    }
}

//
//["TopVotedCandidate","q","q","q","q","q","q"]
//[[[0,1,1,0,0,1,0],[0,5,10,15,20,25,30]],[3],[12],[25],[15],[24],[8]]
//["TopVotedCandidate","q","q","q","q","q","q","q","q","q","q"]
//[[[0,0,0,0,1],[0,6,39,52,75]],[45],[49],[59],[68],[42],[37],[99],[26],[78],[43]]
//["TopVotedCandidate","q","q","q","q","q","q","q","q","q","q"]
//[[[0,1,0,1,1],[24,29,31,76,81]],[28],[24],[29],[77],[30],[25],[76],[75],[81],[80]]
