import java.io.*;
import java.util.*;

public class Main {
	static int N, M, T, p[], r[];
	static class Edge {
		int s, e, w;
		public Edge(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
	}
	static PriorityQueue<Edge> pq;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		pq = new PriorityQueue<>((e1, e2) -> Integer.compare(e1.w, e2.w));
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			pq.offer(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		init();
		
		int cnt = 0, ans = 0;
		while(cnt!=N-1) {
			Edge E = pq.poll();
			if(isUnion(E.s, E.e)) {
				ans += T*cnt;
				cnt++;
				ans += E.w;
			}
		}
		System.out.println(ans);
	}

	private static boolean isUnion(int s, int e) {
		s = find(s);
		e = find(e);
		if(s==e) return false;
		
		if(p[s]>=p[e]) {
			p[e] = s;
			r[s] += r[e];
		}else {
			p[s] = e;
			r[e] += r[s];
		}
		return true;
	}

	private static int find(int s) {
		if(p[s]==s) return p[s];
		return p[s] = find(p[s]);
	}

	private static void init() {
		p = new int[N+1];
		r = new int[N+1];
		for (int i = 1; i <= N; i++) {
			p[i] = i;
			r[i] = 1;
		}
	}
}