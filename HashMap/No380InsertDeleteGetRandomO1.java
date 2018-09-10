package HashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class No380InsertDeleteGetRandomO1 {


    /** Initialize your data structure here. */
    List<Integer> list;
    Map<Integer, Integer> map;
    Random random;
    public No380InsertDeleteGetRandomO1() {
        list = new ArrayList<Integer>();
        map = new HashMap<Integer, Integer>();
        random = new Random();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(map.containsKey(val)) return false;
        map.put(val, list.size());
        list.add(val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val)) return false;
        // Important!
        // remove the val
        // if val is not the last one, 
        // use the last one to fill the space
        int n = list.size();
        int idx = map.remove(val);
        if(idx < n - 1){
            int last = list.get(n - 1);
            list.set(idx, last);
            map.put(last, idx);
        }
        list.remove(n - 1);
        
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int r = random.nextInt(list.size());
        return list.get(r);
    }

}
