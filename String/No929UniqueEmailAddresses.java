package String;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Util.Parser;
import Util.Show;

public class No929UniqueEmailAddresses {

	public static void main(String[] args) {
		No929UniqueEmailAddresses sol = new No929UniqueEmailAddresses();
		Parser parser = new Parser();
		String t = "[\"test.email+alex@leetcode.com\",\"test.e.mail+bob.cathy@leetcode.com\",\"testemail+david@lee.tcode.com\"]\n"
				+ "[\"unique@email.com\", \"unique@email.com\", \"uni.que@email.com\", \"unique+test@email.com\", \"unique@anotheremail.com\"]\n"
				+ "[\"unique@example.com\", \"uni.que@example.com\", \"unique+test@example.com\", \"unique@anotherexample.com\"] ";
		t = t.replace("\"", "");
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			String[] emails = parser.parseArrayStr(s[i]);
			// int ans = sol.numUniqueEmails(emails);
			int ans = sol.mostCommonEmails(emails);
			System.out.println(ans);
		}
	}
	
	public int mostCommonEmails(String[] emails) {
		List<String> list = new LinkedList<String>();
		int ret = 0;
        Map<String, Integer> map = new HashMap<String, Integer>();
        for(int i = 0; i < emails.length; i++){
            StringBuilder sb = new StringBuilder();
            int j = 0;
            for(; j < emails[i].length(); j++){
            	if(emails[i].charAt(j) == '@') break;
            	else if(emails[i].charAt(j) == '.') continue;
            	else if(emails[i].charAt(j) == '+'){
                    while(emails[i].charAt(j) != '@') j++;
                    break;
                }
                sb.append(emails[i].charAt(j));
            }
            sb.append(emails[i].substring(j, emails[i].length()));
            
            String tmp = sb.toString();
            System.out.println(tmp);
            
            int freq = map.getOrDefault(tmp, 0);
            map.put(tmp, ++freq);
            if(freq > ret) {
            	list = new LinkedList<String>();
            	list.add(tmp);
            	ret = freq;
            }else if(freq == ret){
            	list.add(tmp);
            }
        }
        Show show = new Show();
        show.showList(list, true);
        return ret;
    }
	
	
	public int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet<String>();
        for(int i = 0; i < emails.length; i++){
            StringBuilder sb = new StringBuilder();
            int j = 0;
            for(; j < emails[i].length(); j++){
                if(emails[i].charAt(j) == '.') continue;
                if(emails[i].charAt(j) == '+'){
                    while(emails[i].charAt(j) != '@') j++;
                    break;
                }
                sb.append(emails[i].charAt(j));
            }
            sb.append(emails[i].substring(j, emails[i].length()));
            //System.out.println(sb.toString());
            set.add(sb.toString());
        }
        return set.size();
    }

}
