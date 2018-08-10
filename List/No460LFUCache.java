package List;

import java.util.HashMap;
import java.util.Map;

public class No460LFUCache {
	
	// Intuition:
	// Use double linkedlist to insert and delete nodes in constant time
	// Use Map to access nodes by key in O(1)
	// Use Map to access the last nodes of specific freq in O(1)
	
	// Key points:
	// 1. Move because freq++
	//    - not move:
	//      eg. 0 '1' 3 -> 0 '2' 3  or  1 1 1 '1' -> 1 1 1 '2'
	//    - move :
	//      insert it behind the last node of freq+1 if this node exist,
	//      if the node is not exist then insert it behind the last node of original freq instead.
	//      eg. either 0 '1' 2 2 3-> 0 2 2 '2' 3 or  0 '1' 1 -> 0 1 '2'
	// 2. Put
	//    - poll the first node first
	//    - insert new node behind the last node of freq = 0 if this node exist,
	//      otherwise just put at as the first node and set its freq = 0.
	// 3. double linkedlist
	//    - Please use dummy head and tail, because they help avoid Null pointer exceptions.
	//    eg. init: H - T
	//        -> add 3 nodes: H - n1 - n2 - n3 - T
	
	
    Map<Integer, Node> map;
    Map<Integer, Node> freqMap; // the last node of freq key
    Node head;
    Node tail;
    int size;
    int cap;
    public No460LFUCache(int capacity) {
        map = new HashMap<Integer, Node>();
        freqMap = new HashMap<Integer, Node>();
        
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        
        size = 0;
        cap = capacity;
    }
    
    public int get(int key) {
        // System.out.println("get("+ key+")");
        if(map.containsKey(key)) return getNode(key).val;
        else return -1;
    }
    
    private Node getNode(int key) {
        Node n = map.get(key);
        // update freq map of key n.freq
        if(n == freqMap.get(n.freq)){
            if(n.prev != head && n.freq == n.prev.freq) freqMap.put(n.freq, n.prev);
            else freqMap.remove(n.freq);
        }
        
        // the cases we don't move node n
        // -> at tail.
        // -> node of freq (n.freq+1) does not exist.
        if(n.next == tail || n.freq + 1 < n.next.freq){
            // System.out.println("if(n.next == tail || n.freq + 1 < n.next.freq)");
            freqMap.put(++n.freq, n);
            
            // Test
            // showList(head, tail);
            // showMap(map, freqMap);
            
            return n;
        }
        
        // move node n
        // delete from current position
        Node left = n.prev;
        Node right = n.next;
        left.next = n.next;
        right.prev = n.prev;
        
        // insert behind the last node of freq n.freq+1 or n.freq
        Node front = freqMap.get(n.freq+1);
        if(front == null) front = freqMap.get(n.freq);
        Node back = front.next;
        front.next = n;
        n.prev = front;
        n.next = back;
        back.prev = n;
        
        // update last node of freq n.freq+1
        freqMap.put(++n.freq, n);
        
        // Test
        // showList(head, tail);
        // showMap(map, freqMap);
        
        return n;
    }
    
    public void put(int key, int value) {
        // System.out.println("put("+ key + ","+ value + ")");
        if(cap == 0) return;
        if(map.containsKey(key)){
            Node n = getNode(key);
            n.val = value;
        }else if(size == 0){
            Node n = new Node(key, value);
            head.next = n;
            n.prev = head;
            n.next = tail;
            tail.prev = n;
            map.put(key, n);
            freqMap.put(0, n);
            size++;
        }else{
            Node n = new Node(key, value);
            
            // remove first
            if(size + 1 > cap){
                Node first = head.next;
                Node firstBack = first.next;
                head.next = first.next;
                firstBack.prev = head;
                map.remove(first.key);
                if(first == freqMap.get(first.freq)) freqMap.remove(first.freq);
                size--;
            }
            
            // if there is a node with freq = 0, insert behind the node.
            // else insert behind head.
            Node last0 = head;
            if(freqMap.containsKey(0)) last0 = freqMap.get(0);
            
            Node back = last0.next;
            n.next = back;
            back.prev = n;
            last0.next = n;
            n.prev = last0;
            
            map.put(key, n);
            freqMap.put(0, n);
            
            size++;
        }
        
        // Test
        // showList(head, tail);
        // showMap(map, freqMap);
    }
    
//    private void showList(Node h, Node t){
//        Node c = h.next;
//        System.out.print("[");
//        while(c != t){
//            System.out.print("["+c.key+","+c.val+","+c.freq +"],");
//            c = c.next;
//        }
//        System.out.print("]\n");
//    }
//    
//    private void showMap(Map<Integer, Node> m, Map<Integer, Node> f){
//        System.out.print("[");
//        for(Map.Entry<Integer, Node> e : m.entrySet()){
//            int key = e.getKey();
//            Node c = e.getValue();
//            System.out.print("["+key+":("+c.key+","+c.val+","+c.freq +")],");
//        }
//        System.out.print("]\n");
//        System.out.print("[");
//        for(Map.Entry<Integer, Node> e : f.entrySet()){
//            int key = e.getKey();
//            Node c = e.getValue();
//            System.out.print("["+key+":("+c.key+","+c.val+","+c.freq +")],");
//        }
//        System.out.print("]\n");
//    }

}


class Node{
    int key;
    int val;
    int freq;
    Node prev;
    Node next;
    public Node(int k, int v){
        key = k;
        val = v;
        freq = 0;
    }
}