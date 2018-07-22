package HashMap;

import java.util.LinkedList;
import java.util.List;

public class HashTable<K, V> {

	// Test
	public static void main(String[] args) {
		HashTable<String, String> ht = new HashTable<>();
		ht.put("LJR", "0987654321");
		String n = ht.get("LJR");
		System.out.println(n);
	}

	// Class starts here
	int capacity;
	List<Node<K, V>>[] bucket;

	public HashTable() {
		this(701);
	}

	public HashTable(int capacity) {
		this.capacity = capacity;
		bucket = new List[this.capacity];
	}
	
	// Some ideal hash functions:
	// m = capacity of hash table
	// k = hashCode
	// h(k) = hash value
	// α = n/m
	//
	// 1. index = k % m;
	//    eg. if we want load factor α = 3 for n = 2000,
	//        then closest prime to 2^p for any p is m = 701.
	//    We do not suggest using 2^p as m,
	//    because h(k) become the p lowest-order bit of k,
	//    though it is still a good approach by index = k & (2^p - 1);
	//    eg. k: 14 = 1110, m = 4 = 100, so index = 2 = 10.
	//
	// 2. index = (int)( m * ( (float)k * A % 1) ), where 0 < A < 1
	//    kA mod 1 means the fractional part of kA, that is kA - (int)kA.
	//    The difference between the above one is that the value m is not critical.
	//    This hash function works better with A = 0.6180339887...
	//    Therefore let m = 2^p and extract p bits as have value is available.
	//
	//    eg. k = 123456, p = 14, m = 2^14 = 16384, w = 32
	//    Let s = 2654435769 such that A = s / 2^32 closes to 0.618.
	//    k * s = (76300 * 2^32) + 17612864 = r0:r1
	//    the 14 most significant bits of r1 is h(k) = 67
	//
	//    Example Code:
	//    long k = 123456; int p = 14; int w = 32;
	//    long intTop = 1; intTop<<=w;
	//    //float A = 0.618f; A = s/2^32
	//    long s = 2654435769L;
	//    long hk = k*s%intTop; // get r1
	//    hk = hk >> (w-p); // left shift (32 - 14) bits
	// 
	//    But after all this method needs large integer computation,
	//    you should handle it carefully.
	
	private int hashFucntion(int hashCode) {
		// --> time = O(n/m)
		// A good hash function must computes in constant time, 
		// distribute the set of hash code uniformly.
		int ret = hashCode % capacity;
		return ret;
	}
	
	public void put(K key, V value) {
		// --> time = O(1)
		int hashCode = key.hashCode();
		int idx = hashFucntion(hashCode);
		if (bucket[idx] == null)
			bucket[idx] = new LinkedList<>();
		Node<K, V> node = new Node<K, V>(idx, hashCode, key, value);
		node.showNode();
		bucket[idx].add(node);
	}

	public V get(K key) {
		// --> average time = O(α)
		int hashCode = key.hashCode();
		int idx = hashFucntion(hashCode);
		if (bucket[idx] == null)
			return null;
		for (int i = 0; i < bucket[idx].size(); i++) {
			Node<K, V> curNode = bucket[idx].get(i);
			if (curNode.hashCode == hashCode)
				return curNode.value;
		}
		return null;
	}
	
	public void delete(K key) {
		// --> average time = O(α)
		int hashCode = key.hashCode();
		int idx = hashFucntion(hashCode);
		if (bucket[idx] != null) {
			for(int i = 0; i < bucket[idx].size(); i++) {
				if(bucket[idx].get(i).hashCode() == hashCode) {
					bucket[idx].remove(i);
				}
			}
		}
	}
	
	
}

class Node<K, V> {
	int bucketIdx;
	int hashCode;
	K key;
	V value;

	public Node(int bucketIdx, int hashCode, K key, V value) {
		this.bucketIdx = bucketIdx;
		this.hashCode = hashCode;
		this.key = key;
		this.value = value;
	}

	public void showNode() {
		System.out.println("bucketIdx: " + bucketIdx +
				", Hash code: " + hashCode + " , Key: " + key.toString() +
				" , value: " + value.toString());
	}

}