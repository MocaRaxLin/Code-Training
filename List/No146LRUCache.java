package List;

import java.util.HashMap;
import java.util.Map;

public class No146LRUCache {
	// Intuition:
	// Use double linkedlist to manage cache, delete node in constant time
	// Use HashMap<key, Node(val)> to get the node in constant time.
	
    Node dummyhead;
    Node tail;
    int size;
    int cap;
    Map<Integer, Node> map;
    public No146LRUCache(int capacity) {
        cap = capacity;
        dummyhead = new Node(-1, -1);
        tail = dummyhead;
        size = 0;
        map = new HashMap<Integer, Node>();
    }
    
    public int get(int key) {
        if(map.containsKey(key)) return getNode(key).val;
        else return -1;
    }
    
    private Node getNode(int key){
        Node n = map.get(key);
        if(n == tail) return n;
        
        Node left = n.prev;
        Node right = n.next;
        left.next = n.next;
        right.prev = n.prev;
        
        n.next = null;
        n.prev = tail;
        tail.next = n;
        tail = n;
        
        return n;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node n = getNode(key);
            n.val = value;
        }else{
            Node n = new Node(key, value);
            n.prev = tail;
            tail.next = n;
            tail = n;
            map.put(key, n);
            size++;
            if(size > cap){
                dummyhead = dummyhead.next;
                dummyhead.prev = null;
                map.remove(dummyhead.key, dummyhead);
                size--;
            }
        }
    }
}

// Use class Node in No460 
//class Node{
//    int key;
//    int val;
//    Node prev;
//    Node next;
//    public Node(int k, int v){
//        key = k;
//        val = v;
//    }
//}

// Useful Test cases
//
//["LRUCache","put","put","get","put","get","put","get","get","get"]
//[[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
//["LRUCache","put","put","get","put","get","get","put","get","get","get"]
//[[2],[1,1],[2,2],[1],[3,3],[2],[3],[4,4],[1],[3],[4]]
//["LRUCache","put","get","put","get","get"]
//[[1],[2,1],[2],[3,2],[2],[3]]
//["LRUCache","put","put","get","get","get","put","put","get","get","get","get"]
//[[3],[2,2],[1,1],[2],[1],[2],[3,3],[4,4],[3],[2],[1],[4]]
