import java.io.*;
import java.util.*;

public class Main {
	static final int DIV = 1000000007;
	static long[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		dp = new long[N+1];
		if(N==1) {
			System.out.println(0);
			return;
		}
		dp[2] = 1;
		
		for (int i = 3; i <= N; i++)
			dp[i] = ((i-1)%DIV * (dp[i-2] + dp[i-1])%DIV)%DIV;
		
		dp[N] = (dp[N] * fac(N)%DIV)%DIV;
		System.out.println(dp[N]);
	}

	private static long fac(int n) {
		if(n==1) return 1;
		return (n%DIV * fac(n-1)%DIV)%DIV;
	}
}