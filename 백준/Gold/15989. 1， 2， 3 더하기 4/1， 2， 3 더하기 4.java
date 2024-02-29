import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static int N, T;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		dp = new int[4][10001];
		Arrays.fill(dp[1], 1);
		dp[2][0] = 1;
		dp[2][1] = 1;
		dp[3][0] = 1;
		dp[3][1] = 1;
		dp[3][2] = 2;
		
		for (int i = 2; i <= 3; i++) {
			for (int j = i; j <= 10000; j++) {
				dp[i][j] = dp[i-1][j]+dp[i][j-i];
			}
		}
		for (int i = 0; i < T; i++) {
			N = Integer.parseInt(br.readLine());
			System.out.println(dp[3][N]);
		}
	}
}