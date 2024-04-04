import java.io.*;
import java.util.*;

public class Main {
	static StringTokenizer st;
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Deque<Integer> dq = new ArrayDeque<>();
		dq.offer(0);
		int ans = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int next = Integer.parseInt(st.nextToken());
			if(dq.peekLast()<next) {
				ans++;
				dq.offerLast(next);
			} else {
				while(dq.peekLast()>next) dq.pollLast();
				if(dq.peekLast()<next) {
					ans++;
					dq.offerLast(next);
				}
			}
		}
		System.out.println(ans);
	}
}