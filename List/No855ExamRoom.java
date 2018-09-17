package List;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class No855ExamRoom {}

class ExamRoom {
    // Fastest solution in LeetCode
    
    // Using PriorityQueue with valiable seats intervals
    // start and end points are seated.
    // Brilliant!!!
    
    int N;
    PriorityQueue<Edge> pq;
    
    /**
    *   为了处理边界问题，判断如果x，y为边界是，将dist设置为y - x
    *   对于正常情况，dist = (y - x) / 2;
    */
    class Edge {
        int start;
        int end;
        int dist;
        Edge(int x, int y) {
            start = x;
            end = y;
            if (x == -1) {
                dist = y;
            }
            else if (y == N) {
                dist = y - x - 1;
            }
            else {
                dist = (y - x) / 2;
            }
        }
    }

    public ExamRoom(int N) {
        this.N = N;
        /**pq按照dist排序，否则的话就按照start排序*/
        pq = new PriorityQueue<>(new Comparator<Edge>(){
            public int compare(Edge a, Edge b) {
                if (a.dist != b.dist) 
                    return b.dist - a.dist;
                
                return a.start - b.start;
            }
        });
        
        pq.offer(new Edge(-1, N));
    }
    
    /**选择位置：
    *  如果起点是-1，则设置为0；
    *  如果终点是N,则设置为N - 1;
    *  否则，取中点
    */
    // --> time = O(logn)
    public int seat() {
        Edge curEdge = pq.poll();
        if (curEdge.start == -1) {
            pq.offer(new Edge(0, curEdge.end));
            return 0;
        }
        else if (curEdge.end == N) {
            pq.offer(new Edge(curEdge.start, N - 1));
            return N - 1;
        }
        else {
            pq.offer(new Edge(curEdge.start, curEdge.start + curEdge.dist));
            pq.offer(new Edge(curEdge.start + curEdge.dist, curEdge.end));
            return curEdge.start + curEdge.dist;
        }
    }
    
    /**离开位置：
    *  如果是0的话，prev为空
    *  如果是N-1的话，next为空
    *  其他情况，取prev.start和next.end
    */
    
    // --> time = O(n)
    public void leave(int p) {
        Edge prev = null, next = null;
        
        List<Edge> edgeList = new ArrayList<>(pq); // remeber this pq to list!
        for (Edge edge : edgeList) {
            if (edge.start == p) {
                next = edge;
            }
            else if (edge.end == p) {
                prev = edge;
            }
            
            if (prev != null && next != null)
                break;
        }
        
        // prev = [?, p], next = [p, ?]
        int start = -1, end = N;
        if (prev != null) {
            pq.remove(prev);
            start = prev.start;
        }
        
        if (next != null) {
            pq.remove(next);
            end = next.end;
        }
        
        pq.offer(new Edge(start, end));
    }
}






class ExamRoom0 {
	
    List<Integer> seats;
    int N;
    public ExamRoom0(int N) {
        seats = new ArrayList<Integer>();
        this.N = N;
    }
    
    // --> time = O(n)
    public int seat() {
        if(seats.size() == 0){
            seats.add(0);
            return 0;
        }
        int idx = 0, disMax = 0, addIdx = 0;
        if(seats.get(0) != 0){
            idx = 0;
            disMax = seats.get(0);
            addIdx = 0;
        }
        int a, b, m;
        int i = 1;
        for(; i < seats.size(); i++){
            a = seats.get(i-1);
            b = seats.get(i);
            if(a + 1 == b) continue;
            m = a + (b-a)/2;
            if(m - a > disMax){
                idx = m;
                disMax = m-a;
                addIdx = i;
            }
        }
        a = seats.get(i-1);
        m = N - 1;
        if(a != N-1 && m - a > disMax){
            idx = m;
            disMax = m - a;
            addIdx = i;
        }
        seats.add(addIdx, idx);
        return idx;
    }
    
    // --> time = O(n)
    public void leave(int p) {
        for(int i = 0; i < seats.size(); i++){
            if(seats.get(i) == p) seats.remove(i);
        }
    }
}
