package OOD;

import java.util.HashMap;
import java.util.Map;

public class GoogleDoc {
	
	
	StringBuffer content;
	Map<String, gUser> users;
	public GoogleDoc() {
		content = new StringBuffer();
		users = new HashMap<String, gUser>();
	}
	
	private boolean inUserList(String name) {
		if(users.containsKey(name)) {
			System.out.println("user "+name+" exists!");
			return true;
		}else {
			System.out.println("user "+name+" does not exist!");
			return false;
		}
	}
	
	public boolean addUser(String name) {
		if(inUserList(name)) return false;
		users.put(name, new gUser(name));
		return true;
	}
	
	public boolean removeUser(String name) {
		if(!inUserList(name)) return false;
		users.remove(name);
		return true;
	}
	
	public boolean write(String name, char c) {
		if(!inUserList(name)) return false;
		users.get(name).write(content, c);
		return true;
	}
	
	public boolean delete(String name) {
		if(!inUserList(name)) return false;
		users.get(name).delete(content);
		return true;
	}
	
	public String getContent() {
		return content.toString();
	}
	
	public static void main(String[] args) {
		GoogleDoc doc = new GoogleDoc();
		System.out.println("doc.addUser(\"Alex\"): " + doc.addUser("Alex"));
		System.out.println("doc.addUser(\"Alex\"): " + doc.addUser("Alex"));
		System.out.println("doc.addUser(\"Bob\"): " + doc.addUser("Bob"));
		System.out.println("doc.removeUser(\"Alex\")" + doc.removeUser("Alex"));
		System.out.println("doc.removeUser(\"Alex\")" + doc.removeUser("Alex"));
		
		System.out.println(doc.write("FF", 'c'));
		System.out.println(doc.getContent());
		System.out.println(doc.write("Alex", 'h'));
		System.out.println(doc.getContent());
		System.out.println(doc.write("Bob", 'i'));
		System.out.println(doc.getContent());
		System.out.println(doc.write("Alex", '!'));
		System.out.println(doc.delete("FF"));
		System.out.println(doc.delete("Alex"));
		System.out.println(doc.getContent());
		System.out.println(doc.delete("Bob"));
		System.out.println(doc.getContent());
		System.out.println(doc.delete("Alex"));
		System.out.println(doc.getContent());
		System.out.println(doc.delete("Bob"));
		System.out.println(doc.getContent());
	}

}

class gUser{
	String name;
	public gUser(String name) {
		this.name = name;
	}
	
	public boolean write(StringBuffer content, char c) {
		if(content == null) return false;
		content.append(c);
		return true;
	}
	
	public boolean delete(StringBuffer sb) {
		if(sb == null || sb.length() == 0) return false;
		sb.deleteCharAt(sb.length()-1);
		return true;
	}
	
}