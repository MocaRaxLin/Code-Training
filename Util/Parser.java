package Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Parser {
	public String[] parseArrayStr(String line) {
		//line = line.replaceAll("\\s", "");
		String[] s = line.split("[,|\\[|\\]]");
		if(s.length == 0) return new String[] {};
		String[] ret = new String[s.length-1];
		for(int i = 0; i < ret.length; i++) ret[i] = s[i+1];
		return ret;
	}
	
	public String[][] parseMatrixStr(String line){
		//line = line.replaceAll("\\s", "");
		String arrays = line.substring(1, line.length() - 1);
		if(arrays.length() == 0) return new String[0][0];
		arrays = arrays.replaceAll("\\],", "] ");
		String[] a = arrays.split(" ");
		if(a.length == 1) {
			String[][] ret = new String[1][];
			ret[0] = parseArrayStr(a[0]);
			return ret;
		}else {
			String[][] ret = new String[a.length][];
			for(int i = 0; i < ret.length; i++) ret[i] = parseArrayStr(a[i]);
			return ret;
		}
	}
	
	public int[] parseArray(String line) {
		line = line.replaceAll("\\s", "");
		String[] s = line.split("[,|\\[|\\]]");
		if(s.length == 0) return new int[] {};
		int[] ret = new int[s.length-1];
		for(int i = 0; i < ret.length; i++) ret[i] = Integer.parseInt(s[i+1]);
		return ret;
	}
	
	public List<Integer> parseList(String line, boolean isArray){
		line = line.replaceAll("\\s", "");
		int[] a = parseArray(line);
		List<Integer> ret = null;
		if(isArray) ret = new ArrayList<Integer>();
		else ret = new LinkedList<Integer>();
		for(int i: a) ret.add(i);
		return ret;
	}
	
	public int[][] parseMatrix(String line) {
		line = line.replaceAll("\\s", "");
		String arrays = line.substring(1, line.length() - 1);
		if(arrays.length() == 0) return new int[0][0];
		arrays = arrays.replaceAll("\\],", "] ");
		//System.out.println(arrays);
		String[] a = arrays.split(" ");
		//System.out.println("a .length: "+ a.length);
		//Show.showArray(a);
		if(a.length == 1) {
			int[][] ret = new int[1][];
			ret[0] = parseArray(a[0]);
			return ret;
		}else {
			int[][] ret = new int[a.length][];
			for(int i = 0; i < ret.length; i++) ret[i] = parseArray(a[i]);
			return ret;
		}
	}
	
	public List<List<Integer>> parseListList(String line, boolean isArray){
		int[][] a = parseMatrix(line);
		for(int i = 0; i < a.length; i++) {
			System.out.println(Arrays.toString(a[i]));
		}
		List<List<Integer>> ret = null;
		if(isArray) ret = new ArrayList<List<Integer>>();
		else ret = new LinkedList<List<Integer>>();
		for(int i = 0; i < a.length; i++) {
			List<Integer> inner = null;
			if(isArray) inner = new ArrayList<Integer>();
			else inner = new LinkedList<Integer>();
			for(int j: a[i]) inner.add(j);
		}
		return ret;
	}
	
	public double[] parseArrayDouble(String line) {
		line = line.replaceAll("\\s", "");
		String[] s = line.split("[,|\\[|\\]]");
		if(s.length == 0) return new double[] {};
		double[] ret = new double[s.length-1];
		for(int i = 0; i < ret.length; i++) ret[i] = Double.parseDouble(s[i+1]);
		return ret;
	}
	
	public char[][] stringMatrix2CharMatrix(String[][] m){
		char[][] ret = new char[m.length][];
		for(int i = 0; i < m.length; i++) {
			char[] r = new char[m[i].length];
			for(int j = 0; j < r.length; j++)  r[j] = m[i][j].charAt(0);
			ret[i] = r;
		}
		return ret;
	}
	
}
