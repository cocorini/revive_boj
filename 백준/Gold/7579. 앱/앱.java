import java.io.*;
import java.util.*;

public class Main {
	static StringTokenizer st, memory, cost;
	static int N, M, ANS = Integer.MAX_VALUE;
	static int[] m, c;
	static int[][] dp;
	
	
	public static void main(String[] args) throws IOException {
		init();
		
		for (int j = c[0]; j <= 10000; j++) {
			dp[0][j] = m[0];
			if(dp[0][j]>=M) ANS = Math.min(ANS, j);
		}
		for (int i = 1; i < N; i++) {
			for (int j = 0; j <= 10000; j++) {
				dp[i][j] = dp[i-1][j];
				if(j>=c[i]) dp[i][j] = Math.max(dp[i][j], dp[i-1][j-c[i]]+m[i]);
				if(dp[i][j]>=M) ANS = Math.min(ANS, j);
			}
		}
		System.out.println(ANS);
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		memory = new StringTokenizer(br.readLine());
		cost = new StringTokenizer(br.readLine());
		m = new int[N];
		c = new int[N];
		dp = new int[N][10001];
		for (int i = 0; i < N; i++) m[i] = Integer.parseInt(memory.nextToken());
		for (int i = 0; i < N; i++) c[i] = Integer.parseInt(cost.nextToken());
	}

}