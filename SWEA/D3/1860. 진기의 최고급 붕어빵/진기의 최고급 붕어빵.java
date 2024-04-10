import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, K;
	static int[] men, fb, ready;
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			init();
			jinki(test_case);
		}
	}

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		men = new int[N];
		ready = new int[11112];
		fb = new int[11112];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) men[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(men);
	}

	private static void jinki(int t) {
		for (int i = M, cnt = 0; i < 11112; i++) {
			if(i%M==0) cnt++;
			fb[i] = cnt*K;
		}
		for (int i = 0, cnt = 1; i < men.length; i++) ready[men[i]] = cnt++;
		for (int i = 0; i < ready.length; i++) {
			if(fb[i]<ready[i]) {
				System.out.println("#"+t+" Impossible");
				return;
			}
		}
		System.out.println("#"+t+" Possible");
	}
}