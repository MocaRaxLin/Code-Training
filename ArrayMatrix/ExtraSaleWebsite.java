package ArrayMatrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Util.Parser;
import Util.Show;

public class ExtraSaleWebsite {

	public static void main(String[] args) {
		ExtraSaleWebsite sol = new ExtraSaleWebsite();
		Show show = new Show();
		Parser p = new Parser();
		String t ="[[\"item1\",\"10\",\"15\"],[\"item2\",\"3\",\"4\"],[\"item3\",\"17\",\"8\"]]\n" + 
				"1\n" + 
				"0\n" + 
				"2\n" + 
				"1";
		t = t.replace("\"", "");
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=5) {
			String[][] items = p.parseMatrixStr(s[i]);
			int sortPar = Integer.parseInt(s[i+1]);
			int sortOrd = Integer.parseInt(s[i+2]);
			int itemsPerPage = Integer.parseInt(s[i+3]);
			int page = Integer.parseInt(s[i+4]);
			List<String[]> ans = sol.sortItems(items, sortPar, sortOrd, itemsPerPage, page);
			show.showListArray(ans);
		}
	}

	private List<String[]> sortItems(String[][] items, int sortPar, int sortOrd, int itemsPerPage, int page) {
		List<String[]> itemList = new ArrayList<String[]>();
		for(String[] e: items) itemList.add(e);
		
		Collections.sort(itemList, new Comparator<String[]>() {
			public int compare(String[] a, String[] b) {
				if(sortPar == 0) return sortOrd == 0? a[0].compareTo(b[0]): b[0].compareTo(a[0]);
				else return sortOrd == 0? Integer.parseInt(a[sortPar]) - Integer.parseInt(b[sortPar]): Integer.parseInt(b[sortPar]) - Integer.parseInt(a[sortPar]);
			}
		});
		
		List<String[]> ret = new ArrayList<String[]>();
		int idx = itemsPerPage*page;
		for(int i = 0; i < itemsPerPage && idx < itemList.size(); i++) {
			ret.add(itemList.get(idx++));
		}
		return ret;
	}

}
