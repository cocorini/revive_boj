import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static int[] dp_two, sum, dp_three, dp_one;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		sum = new int[N+1];
		dp_one = new int[N+1];
		dp_two = new int[N+1];
		dp_three = new int[N+1];
		// 부분합
		for (int i = 1; i <= N; i++) sum[i] = sum[i-1] + Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		// dp 배열 만들기
		// K~2K - 1그룹 뽑기
		dp_one[K] = sum[K];
		for (int i = K+1; i <= N; i++)
			dp_one[i] = Math.max(dp_one[i-1], sum[i]-sum[i-K]);
		dp_two[2*K] = sum[2*K];
		// 2K~3K - 2그룹 뽑기
		for (int i = 2*K+1; i <= N; i++) 
			dp_two[i] = Math.max(dp_two[i-1], sum[i]-sum[i-K]+dp_one[i-K]);
		// 3K~N - 3그룹 뽑기
		dp_three[3*K] = sum[3*K];
		for (int i = 3*K+1; i <= N; i++)
			dp_three[i] = Math.max(dp_three[i-1], sum[i]-sum[i-K]+dp_two[i-K]);
		System.out.println(dp_three[N]);
	}
}