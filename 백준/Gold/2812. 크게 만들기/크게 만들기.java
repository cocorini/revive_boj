import java.io.*;
import java.util.*;

public class Main {
	static StringTokenizer st;
	static int N, K;
	static String input;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		input = br.readLine();
		
		dfs(0, 0);
	}

	private static void dfs(int cnt, int idx) {
		if(cnt==N-K) {
			System.out.println(sb);
			return;
		}
		int val = input.charAt(idx)-'0', index = idx;
		for (int i = idx; i <= K+cnt; i++) {
			if(i>=N) break;
			if(val<input.charAt(i)-'0') {
				val = input.charAt(i)-'0';
				index = i;
			}
		}
		sb.append(val);
		dfs(cnt+1, index+1);
	}
}