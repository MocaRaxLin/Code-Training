package String;

import java.util.Arrays;

public class ExtraStringMatch {

	public static void main(String[] args) {
		ExtraStringMatch sol = new ExtraStringMatch();
		
		// String s = "shikisfatababaababhahaha";
		// String p = "ababaabab";
		// String s = "a";
		// String p = "a";
		String s = "here is a simple example";
		String p = "example";
		int ans = sol.KMPMatch(s, p);
		System.out.println(ans);
	}

	private int BMMatch(String S, String P) {
		// BM Algorithm
		// Boyer-Moore Algorithm
		// --> O(Σ + m + nm) when m << n, Σ is the size of alphabet
		// but the worst case is unlikely achieved for English text
		// the average time is close to O(n/m)

		// BM assumes alphabet is fixed, finite size.
		// Here we use all Characters.

		// Looking-Glass Heuristic:
		// When testing a possible placement of P against S,
		// begin the comparisons from the end of P and move backwards to the front.
		//
		// Character-Jump Heuristic:
		// During the matching, a mismatching S[i] = c with P[j] is handled as follows.
		// If c is not contained anywhere in P, then shift P completely past S[i].
		// Otherwise, shift P until an occurrence of c in P gets aligned with S[i].

		// eg.
		// _____________a_____________
		// i
		// ___a____b___ l + 1 <= j, l < j
		// l j
		// ___a____b___ i = i + m - (l + 1), the part after 'a' to the end.
		//
		// _____________a_____________
		// i
		// ________b_a_ j < l + 1, j < l
		// j l
		// ________b_a_ i = i + m - j, the part from 'b' to the end.

		int m = P.length();
		int n = S.length();

		// Build last table for char
		int[] last = new int[(1 << 16)]; // 65536
		Arrays.fill(last, -1);
		for (int i = 0; i < m; i++)
			last[P.charAt(i)] = i;

		// mqtching
		int i = m - 1, j = m - 1;
		while (i < n) {
			if (S.charAt(i) == P.charAt(j)) {
				if (j == 0)
					return i;
				i--;
				j--;
			} else {
				i = i + m - Math.min(j, 1 + last[S.charAt(i)]);
				j = m - 1;
			}
		}

		return -1;
	}

	private int KMPMatch(String S, String P) {
		// KMP Algorithm
		// Knuth-Morris-Pratt Algorithm
		// --> O(n+m), where n = s.length(), m = p.length()

		// Evaluate Function of longest common prefix (LCP) of pattern p.
		// Store lengths of each LCP into array Fp.
		// The following is intuition. Thanks to PTT, I'll tune index to base 0.
		// source: https://www.ptt.cc/bbs/b99902HW/M.1300796484.A.EE1.html
		//
		// Let T = ababaabab, F(0) = 0
		// T = a babaabab i = 1
		// ababaabab j = 0, T[i]!=T[j] and j=0, so F(1) = 0
		// T = ab abaabab i = 2
		// ababaabab j = 0, T[i]==T[j], so F(2) = j+1 = 1
		// using: j++; F[i] = j; i++;
		// T = aba baabab i = 3
		// a babaabab j = 1, T[i]==T[j], so F(3) = j+1 = 2
		// T = abab aabab i = 4
		// ab abaabab j = 2, T[i]==T[j], so F(4) = j+1 = 3
		// T = ababa abab i = 5
		// aba baabab j = 3, T[i]!=T[j], so only process j=F(j-1)
		// T = ababa abab i = 5
		// a babaabab j = 1, Again T[i]!=T[j], so j=F(j-1)
		// T = ababa abab i = 5
		// ababaabab j = 0, T[i]==T[j], so F(5) = j+1 = 1
		// T = ababaa bab i = 6
		// a babaabab j = 1, T[i]==T[j], so F(6) = j+1 = 2
		// T = ababaab ab i = 7
		// ab abaabab j = 2, T[i]==T[j], so F(7) = j+1 = 3
		// T = ababaaba b i = 8
		// aba baabab j = 3, T[i]==T[j], so F(8) = j+1 = 4
		// End >w< ~ T = ababaabab
		//   p = [ a b a b a a b a b ]
		// LCP = [ 0 0 1 2 3 1 2 3 4 ]

		if (P.length() == 0 || S.length() == 0)
			return -1;
		int[] LCP = new int[P.length()];
		// LCP[0] = 0, ps. LCP stores the length of longest common prefix.
		int i = 1, j = 0;
		while (i < LCP.length) {
			if (P.charAt(i) == P.charAt(j)) {
				LCP[i] = j + 1;
				j++;
				i++;
			} else if (j > 0) {
				j = LCP[j - 1];
			} else {
				LCP[i] = 0;
				i++;
			}
		}

		// match S with P by taking advantage of Fp (LCP)
		// Almost the same as LCP, but only use not make Fp Array.
		// i starts as 0 not 1, careful!
		i = 0;
		j = 0;
		while (i < S.length()) {
			if (S.charAt(i) == P.charAt(j)) {
				j++;
				i++;
				if (j == P.length())
					return i - P.length();
			} else if (j > 0) {
				// not match at j, but we already succeed p[0:j-1]
				j = LCP[j - 1];
			} else {
				// s[i] != p[j] && j == 0
				i++;
			}
		}

		return -1;
	}

	private int bruteForceMatch(String s, String p) {
		// --> O(nm), where n = s.length(), m = p.length()
		// Brute force
		int n = s.length();
		int m = p.length();
		for (int i = 0; i <= n - m; i++) {
			for (int j = 0; j < m; j++) {
				if (p.charAt(j) != s.charAt(i + j))
					break;
				if (j == m - 1)
					return i;
			}
		}
		return -1;
	}

}
