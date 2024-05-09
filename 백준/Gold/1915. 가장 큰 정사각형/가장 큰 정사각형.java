import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] arr, dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		dp = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = s.charAt(j)-'0';
			}
		}
		DP();
	}

	private static void DP() {
		int ans = 0;
		for (int i = 0; i < N; i++)
			dp[i][0] = arr[i][0];
		for (int j = 0; j < M; j++) 
			dp[0][j] = arr[0][j];
		
		for (int i = 1; i < N; i++)
			for (int j = 1; j < M; j++) {
				if(arr[i][j]!=0) dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
			}
		
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				ans = Math.max(ans, dp[i][j]);
		
		System.out.println(ans*ans);
	}
}