import java.io.*;
import java.util.*;

public class Main {
	static StringTokenizer st;
	static int N, M;
	static boolean[] small = new boolean[10001];
	static boolean[][] visited;
	static class Pair {
		int n, x, cnt;
		public Pair(int n, int x, int cnt) {
			this.n = n;
			this.x = x;
			this.cnt = cnt;
		}
	}
	static int[] dx = {-1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N+150][150];
		for (int i = 0; i < M; i++) {
			int val = Integer.parseInt(br.readLine());
			small[val] = true;
		}
		bfs();
	}

	private static void bfs() {
		Deque<Pair> q = new ArrayDeque<>();
		if(small[2]) {
			System.out.println(-1);
			return;
		}
		q.offer(new Pair(2, 1, 1));
		while(!q.isEmpty()) {
			Pair p = q.poll();
			for (int d = 0; d < 3; d++) {
				int nx = p.x + dx[d];
				if(!small[p.n+nx] && nx>=1 && !visited[p.n+nx][nx]) {
					if(p.n+nx==N) {
						System.out.println(p.cnt + 1);
						return;
					}
					visited[p.n+nx][nx] = true;
					if(p.n+nx<=N) q.offer(new Pair(p.n+nx, nx, p.cnt+1));
				}
			}
		}
		System.out.println(-1);
	}
}