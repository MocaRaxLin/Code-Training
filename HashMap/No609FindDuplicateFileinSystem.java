package HashMap;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import Util.Show;

public class No609FindDuplicateFileinSystem {

	public static void main(String[] args) {
		No609FindDuplicateFileinSystem sol = new No609FindDuplicateFileinSystem();
		Show show = new Show();
		
		String[][] S = new String[][] {
				{"root/a 1.txt(abcd) 2.txt(efgh)","root/c 3.txt(abcd)","root/c/d 4.txt(efgh)","root 4.txt(efgh)"},
				{"root/a 1.txt(abcd) 2.txt(efsfgh)","root/c 3.txt(abdfcd)","root/c/d 4.txt(efggdfh)"}
		};
		
		for(String[] s: S) {
			List<List<String>> ans = sol.findDuplicate(s);
			show.showListList(ans);
		}
	}

	public List<List<String>> findDuplicate(String[] paths) {
		// --> O(n), where n = number of files in paths
		//
		// We use hash map to store each file's content and list index,
		// and create a list or add to a list for each directory. -> O(n)
		//
		// By taking advantage of key = content, 
		// we can easily check whether the content is duplicate.
		//
		// After we collect all directories into lists,
		// the final step is to remove none duplicate content. -> O(n)
		// Here we just remove lists size of 1
		
        List<List<String>> ret = new LinkedList<List<String>>();
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for(int i = 0; i < paths.length; i++){
            String[] words = paths[i].split(" ");
            String path = words[0];
            //System.out.println(path);
            for(int j = 1; j < words.length; j++){
                //System.out.println(words[j]);
                String[] item = words[j].split("[(|)]");
                String filename = item[0];
                String contentKey = item[1];
                
                if(!map.containsKey(contentKey)){
                    map.put(contentKey, map.size());
                    List<String> newList = new LinkedList<String>();
                    ret.add(newList);
                }
                int listIndex = map.get(contentKey);
                ret.get(listIndex).add(path + "/" + filename);
            }
        }
        // distill only duplicate file, remove list size of 1
        for(int i = 0; i < ret.size(); i++){
            if(ret.get(i).size() == 1){
                ret.remove(i);
                i--;
            }
        }
        return ret;
    }
}
