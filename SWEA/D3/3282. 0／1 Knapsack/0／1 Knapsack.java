import java.io.*;
import java.util.*;

public class Solution {
	static int N, K;
	static StringTokenizer st;
	static int[] dp;
	static class Pair{
		int v, c;
		public Pair(int v, int c) {
			this.v = v;
			this.c = c;
		}
	}
	static Pair[] obj;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			obj = new Pair[N+1];
			dp = new int[K+1];
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				int v = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
				obj[i] = new Pair(v, c);
			}
			for (int i = 1; i <= N; i++) {
				for (int j = K; j >= obj[i].v; j--) {
					dp[j] = Math.max(dp[j], dp[j-obj[i].v]+obj[i].c);
				}
			}
			System.out.println("#"+test_case+" "+dp[K]);
		}
	}
}