import java.io.*;
import java.util.*;

public class Main {
	static StringTokenizer st;
	static BufferedWriter bw;
	static int N, L;
	static Deque<int[]> dq = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		init();
		for (int i = 0; i < N; i++) {
			int val = Integer.parseInt(st.nextToken());
			while(!dq.isEmpty() && dq.peekLast()[1] > val) dq.pollLast();
			dq.offerLast(new int[] {i, val});
			if(i-dq.peekFirst()[0]>=L) dq.pollFirst();
			bw.write(dq.peekFirst()[1]+" ");
		}
		bw.flush();
		bw.close();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
	}
	
}