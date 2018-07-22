package QueueStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class No726NumberofAtoms {

	public static void main(String[] args) {
		No726NumberofAtoms sol = new No726NumberofAtoms();
		String t = "\"H2O\"\n" + 
				"\"Mg(OH)2\"\n" + 
				"\"K4(ON(SO3)2)2\"\n" + 
				"\"Fe4(ONe16(SO3)2)2\"\n" +
				"\"CO2\"\n" + 
				"\"CO\"\n" + 
				"\"Fe\"\n" + 
				"\"Na(HCo3)\"\n" + 
				"\"Be32\"";
		t = t.replaceAll("\"", "");
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			String ans = sol.countOfAtoms(s[i]);
			System.out.println(ans);
		}
	}

	public String countOfAtoms(String formula) {
        // --> time = O(n + nlogn), where n = formula.length()
        // n -> iterate backwards, use stack to keep factors of multipliers
        // nlogn -> sort all atoms' names
        
        // Intuition:
        // Look from the back to know multiplier first
        // To avoid look back the whole inner atoms in parentheses again.
        
        // eg. "Fe4(ONe16(SO3)2)2", m = 1
        //
        // see 2, counter = 2
        // see ), push counter, stack = [ 2, 
        // m *= 2 = 2
        //
        // see 2, counter = 2
        // see ), push counter, stack = [ 2, 2, 
        // m *= 2 = 4
        //
        // see 3, counter = 3
        // see O, ele = "O", m = 4, store (O, 3*4), counter = 0
        // see S, ele = "S", m = 4, store (O, (0+1)*4), counter = 0
        // see (, stack pop 2, stack = [ 2, 
        // m /= 2 = 2
        //
        // see 6, counter = 6
        // see 1, counter = 16
        // see e, ele = "e"
        // see N, ele = "Ne", m = 2, store (Ne, 16*2), counter = 0
        // see O, ele = O, m = 2, store (O, 3*4 + (0+1)*2), counter = 0;
        // see (, stack pop 2, stack = [ 
        // m /= 2 = 1
        // 
        // see 4, counter = 4
        // see e, ele = "e"
        // see F, ele = "Fe", m = 1, store (Fe, 4*1), counter = 0
        //
        // Sort all collected elements' name and use string builder and hash map storage
        // to construct returned string.
        
        Map<String, Integer> map = new HashMap<String, Integer>();
        
        char state = 'u'; // u - upper, l - lower, d - digit
        char[] ca = formula.toCharArray();
        StringBuilder sb = new StringBuilder();
        
        int[] stack = new int[ca.length]; // for m
        int size = 0;
        int m = 1;
        
        int counter = 0; // count temporary parsed integer
        
        String[] atoms = new String[ca.length]; // store names of unique atoms
        int atomsSize = 0;
        
        for(int i = ca.length - 1; i >= 0; i--){
            if(Character.isLowerCase(ca[i])){
                sb.insert(0, ca[i]);
                state = 'l';
            }else if(Character.isUpperCase(ca[i])){
                sb.insert(0, ca[i]);
                String atom = sb.toString();
                int amount = 0;
                if(map.containsKey(atom)) amount = map.get(atom);
                else atoms[atomsSize++] = atom;
                if(counter == 0) counter = 1; // eg. NO N:1, O:1
                amount += counter*m;
                map.put(atom, amount);
                
                // reset
                counter = 0;
                sb = new StringBuilder();
                state = 'u';
            }else if(ca[i] == '('){
                m /= stack[--size];
                // state = 'u'
            }else if(ca[i] == ')'){
                if(state == 'd'){
                    stack[size++] = counter;
                    m *= counter;
                }else{ // state == 'u'
                    stack[size++] = 1;
                    //m *= 1;
                }
                counter = 0;
                state = 'u';
            }else{ // is digit
                if(state == 'd'){
                    counter += 10 * (ca[i] - '0');
                }else{ // state == 'u'
                    counter = ca[i] - '0';
                }
                state = 'd';
            }
        }
        
        //for(Map.Entry<String, Integer> p: map.entrySet()){ System.out.print(p.getKey() + ":" + p.getValue()+", "); }
        
        
        atoms = Arrays.copyOfRange(atoms, 0, atomsSize);
        Arrays.sort(atoms);
        //System.out.println(Arrays.toString(atoms));
        
        sb = new StringBuilder();
        for(String name: atoms){
            sb.append(name);
            int amount = map.get(name);
            if(amount > 1) sb.append(amount);
        }
        return sb.toString();
    }
}
