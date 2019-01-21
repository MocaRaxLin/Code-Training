package OOD;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class SocialNetwork {
	private Map<Integer, sUser> database;
	private Queue<Integer> Q;
	private int maxId;
	public SocialNetwork() {
		database = new HashMap<>();
		Q = new LinkedList<>();
		maxId = 0;
	}
	
	public sUser createUser() {
		int id = Q.size() > 0? Q.poll(): maxId++;
		sUser u = new sUser(id);
		database.put(id, u);
		return u;
	}
	
	public boolean deleteUser(sUser user) {
		if(!database.containsKey(user.getId())) {
			System.out.println("user is not in database.");
			return false;
		}
		database.remove(user.getId());
		Q.offer(user.getId());
		return true;
	}
	
	public int getSize() {
		return database.size();
	}
	
	public Map<Integer, sUser> getDatabase(){
		return database;
	}
	
	public static void main(String[] args) {
		SocialNetwork sn = new SocialNetwork();
		sUser u1 = sn.createUser();
		sUser u2 = sn.createUser();
		System.out.println("u1.addFriend(sn, u2.getId()): " + u1.addFriend(sn, u2.getId()));
		System.out.println("u2.addFriend(sn, u1.getId()): " + u2.addFriend(sn, u1.getId()));
		System.out.println("u1.sendMsg(u2.getId(), \"hi\"): " + u1.sendMsg(u2.getId(), "hi"));
		System.out.println("u2.readAllMsg(): " + u2.readAllMsg());
		u2.cleanAllMsg();
		System.out.println("u2.deFriend(u1.getId(): " + u2.deFriend(u1.getId()));
		System.out.println("u1.sendMsg(u2.getId(), \"hi2\"): " + u1.sendMsg(u2.getId(), "hi2"));
		System.out.println("sn.deleteUser(u1): " + sn.deleteUser(u1));
		sUser u3 = sn.createUser();
		System.out.println(u3.getId());
	}
}

class sUser{
	private int id;
	private Map<Integer, sUser> friends;
	private List<Message> msgList;
	public sUser(int id) {
		this.id = id;
		this.friends = new HashMap<>();
		this.msgList = new LinkedList<>();
	}
	
	public int getId() {
		return id;
	}
	
	public Map<Integer, sUser> getFriends() {
		return friends;
	}
	
	public boolean addFriend(SocialNetwork sn, int id) {
		sUser u = sn.getDatabase().get(id);
		if(u == null) return false;
		friends.put(id, u);
		return true;
	}
	
	public boolean deFriend(int id) {
		if(!friends.containsKey(id)) return false;
		friends.remove(id);
		return true;
	}
	
	public boolean sendMsg(int id, String s) {
		if(!friends.containsKey(id)) {
			System.out.println("The user is not your fiend yet!");
			return false;
		}
		sUser u = friends.get(id);
		return u.recieveMsg(new Message(this.id, s));
	}
	
	public boolean recieveMsg(Message msg) {
		if(msg == null) return false;
		if(!friends.containsKey(msg.from)) return false;
		msgList.add(msg);
		return true;
	}
	
	public String readAllMsg() {
		Iterator<Message> it = msgList.iterator();
		StringBuilder sb = new StringBuilder();
		while(it.hasNext()) sb.append(it.next().toString() + "\n");
		return sb.toString();
	}
	
	public void cleanAllMsg() {
		this.msgList = new LinkedList<>();
	}
}

class Message{
	int from;
	String msg;
	public Message(int id, String m) {
		from = id;
		msg = m;
	}
	public String toString() {
		return "From: "+from+": "+msg;
	}
}
