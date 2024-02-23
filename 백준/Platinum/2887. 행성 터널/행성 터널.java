// 프림 쓰기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static StringTokenizer st;
//	static class Pos{
//		int x, y, z;
//		public Pos(int x, int y, int z) {
//			this.x = x;
//			this.y = y;
//			this.z = z;
//		}
//	}
//	static Pos[] planets;
	static class Pair implements Comparable<Pair>{
		int idx, val;
		public Pair(int idx, int val) {
			this.idx = idx;
			this.val = val;
		}
		@Override
		public int compareTo(Pair P) {
			return Integer.compare(val, P.val);
		}
	}
	
	static class Edge implements Comparable<Edge>{
		int cost, s, e;
		public Edge(int cost, int s, int e) {
			this.cost = cost;
			this.s = s;
			this.e = e;
		}
		@Override
		public int compareTo(Edge E) {
			return Integer.compare(cost, E.cost);
		}
	}
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
//	static boolean[] visited; // 트리에 속해있는지를 체크하는 방문 배열
//	static int[] min_edge; // 최소값을 갱신할 배열
	
	static Pair[] px, py, pz;
	static int[] p, r;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
//		min_edge = new int[N];
//		Arrays.fill(min_edge, Integer.MAX_VALUE);
//		min_edge[0] = 0;
//		visited = new boolean[N];
//		planets = new Pos[N];
		px = new Pair[N];
		py = new Pair[N];
		pz = new Pair[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
//			planets[i] = new Pos(x, y, z);
			px[i] = new Pair(i, x); py[i] = new Pair(i, y); pz[i] = new Pair(i, z);
		}
		Arrays.sort(px);
		Arrays.sort(py);
		Arrays.sort(pz);
		
		for (int i = 1; i < N; i++) {
//			px[i].val += px[i-1].val;
			pq.offer(new Edge(px[i].val-px[i-1].val, px[i-1].idx, px[i].idx));
//			py[i].val += py[i-1].val;
			pq.offer(new Edge(py[i].val-py[i-1].val, py[i-1].idx, py[i].idx));
//			pz[i].val += pz[i-1].val;
			pq.offer(new Edge(pz[i].val-pz[i-1].val, pz[i-1].idx, pz[i].idx));
		}
		
		Init();
		
		int cnt = 0;
		int sum = 0;
		while(cnt != N-1) {
			Edge E = pq.poll();
			if(isUnion(E.e, E.s)) {
				sum += E.cost;
				cnt++;
			}
		}
		                              
		System.out.println(sum);
	}


	private static boolean isUnion(int e, int s) {
		e = find(e);
		s = find(s);
		if(e==s) return false;
		
		if(r[s]>=r[e]) {
			r[s] += r[e];
			p[e] = s;
		}else {
			r[e] += r[s];
			p[s] = e;
		}
		return true;
	}

	private static int find(int e) {
		if(e==p[e]) return p[e];
		return p[e] = find(p[e]);
	}

	private static void Init() {
		p = new int[N];
		r = new int[N];
		for (int i = 0; i < N; i++) {
			p[i] = i;
			r[i] = 1;
		}
	}
}