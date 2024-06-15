import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] A, prefixSum;
	static long[] modArr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// N, M 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = new int[N+1];
		prefixSum = new int[N+1];
		modArr = new long[M];
		// A 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken())%M;
			prefixSum[i] = (prefixSum[i-1]%M+A[i])%M;
		}
		findMod();
		findSectionCnt();
	}

	private static void findSectionCnt() {
		long ANS = 0;
		for (int i = 0; i < M; i++) ANS += modArr[i]*(modArr[i]-1)/2;
		System.out.println(ANS);
	}

	private static void findMod() {
		for (int i = 0; i <= N; i++) modArr[prefixSum[i]]++;
	}
}