package List;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ExtraListAnnotationsInString {

	public static void main(String[] args) {
		ExtraListAnnotationsInString sol = new ExtraListAnnotationsInString();
//		String t = "hello world\n"
//				+ "3\n"
//				+ "A\n"
//				+ "1\n"
//				+ "4\n"
//				+ "B\n"
//				+ "3\n"
//				+ "5\n"
//				+ "C\n"
//				+ "2\n"
//				+ "8";
		String t = "hello world\n"
				+ "5\n"
				+ "A\n"
				+ "0\n"
				+ "2\n"
				+ "C\n"
				+ "4\n"
				+ "6\n"
				+ "D\n"
				+ "9\n"
				+ "10\n"
				+ "X\n"
				+ "4\n"
				+ "10\n"
				+ "Y\n"
				+ "10\n"
				+ "11";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length;) {
			String input = s[i++];
			int n = Integer.parseInt(s[i++]);
			List<Annotation> listAnno = new LinkedList<Annotation>();
			while(n-->0) {
				String label = s[i++];
				int start = Integer.parseInt(s[i++]);
				int end = Integer.parseInt(s[i++]);
				listAnno.add(new Annotation(start, end, label));
			}
			List<overlapAnnotation> ans = sol.findOverlap(input, listAnno);
			Iterator<overlapAnnotation> it = ans.iterator();
			while(it.hasNext()) System.out.println(it.next().toString());
		}
	}

	public List<overlapAnnotation> findOverlap(String s, List<Annotation> listAnno){
		if(s.length() == 0) return new LinkedList<overlapAnnotation>();
		List<Set<String>> sets = new ArrayList<Set<String>>();
		for(int i = 0; i < s.length(); i++) sets.add(new HashSet<String>());
		Iterator<Annotation> it = listAnno.iterator();

		// O(n|s|)
		while(it.hasNext()){
			Annotation an = it.next();
			int start = an.start < 0? 0: an.start;
			int end = an.end >= s.length()? s.length()-1: an.end;
			for(int i = start; i <= end; i++) sets.get(i).add(an.label);
		}

		// O(|s|n)
		List<overlapAnnotation> ret = new LinkedList<overlapAnnotation>();
		int i = 0;
		Set<String> prev = sets.get(i);
		while(i < s.length() && prev.size() == 0) prev = sets.get(++i);
		int j = i+1;
		for(; j < s.length(); j++){
			if(!hasSameEle(prev, sets.get(j))){
				if(prev.size() != 0) ret.add(new overlapAnnotation(i, j-1, prev));
				i = j;
				prev = sets.get(j);
			}
		}
		if(prev.size() != 0) ret.add(new overlapAnnotation(i, j-1, prev));
		return ret;
	}

	public boolean hasSameEle(Set<String> a, Set<String> b){
		return a.containsAll(b) && b.containsAll(a);
	}
	
}

class Annotation{
	int start;
	int end;
	String label;
	public Annotation(int s, int e, String l) {
		start = s; end = e; label = l;
	}
}

class overlapAnnotation{
	int start;
	int end;
	Set<String> labelSet;
	public overlapAnnotation(int start, int end, Set<String> labelSet){
		this.start = start;
		this.end = end;
		this.labelSet = labelSet;
	}
	public String toString() {
		return labelSet+" [" + start +","+end+"]";
	}
}


/*
012345678910
hello world
 ----        
  A                
   ---      
    B       
  -------
     C



A: [1,4] 
B: [3,5]
C: [2,8]


return
[1,1]  A
[2,2]  A, C
[3,4]  A, B, C
[5,5]  B, C
[6,8]  C
*/