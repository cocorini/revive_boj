import java.io.*;
import java.util.*;

public class Main {
	static int N, M, p[], r[];
	static class Pos {
		int x, y;
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static class Info extends Pos {
		double cost;
		public Info(int x, int y, double cost) {
			super(x, y);
			this.cost = cost;
		}
	}
	static PriorityQueue<Info> pq = new PriorityQueue<>((o1, o2) -> Double.compare(o1.cost, o2.cost));
	static Pos[] univ_god;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		univ_god = new Pos[N+1];
		visited = new boolean[N+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			univ_god[i] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		// MST 구현하기
		init();
		
		int cnt = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
			if(isUnion(x, y)) cnt++;
		}
		
		for (int i = 1; i <= N; i++)
			for (int j = i + 1; j <= N; j++)
				pq.offer(new Info(i, j, getDist(i, j)));
		
		double dist_sum = 0;
		
		while(cnt!=N-1) {
			Info p = pq.poll();
			if(isUnion(p.x, p.y)) {
				visited[p.x] = true;
				visited[p.y] = true;
				dist_sum += p.cost;
				cnt++;
			}
		}
		
		System.out.printf("%.2f", dist_sum);
	}

	private static double getDist(int x, int y) {
		return Math.sqrt(Math.pow(univ_god[x].x-univ_god[y].x, 2)+Math.pow(univ_god[x].y-univ_god[y].y, 2));
	}

	private static boolean isUnion(int x, int y) {
		x = find(x);
		y = find(y);
		if(x==y) return false;
		
		if(r[x]>=r[y]) {
			p[y] = x;
			r[x] += r[y];
		}else {
			p[x] = y;
			r[y] += r[x];
		}
		return true;
	}

	private static int find(int x) {
		if(x==p[x]) return p[x];
		return p[x] = find(p[x]);
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