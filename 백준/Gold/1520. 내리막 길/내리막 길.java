import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] arr, dp;
	static int[] dr = {-1, 0, 1, 0},  dc = {0, -1, 0, 1};
	static class Pair{
		int r, c;
		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static class Pos extends Pair {
		int cost;
		public Pos(int r, int c, int cost) {
			super(r, c);
			this.cost = cost;
		}
	}
	static PriorityQueue<Pos> pq;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		dp = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		
		bfs();
		
		System.out.println(dp[N-1][M-1]);
	}
	
	private static void bfs() {
		pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.cost, o1.cost));
		pq.offer(new Pos(0, 0, arr[0][0]));
		dp[0][0] = 1;
		while(!pq.isEmpty()) {
			Pos p = pq.poll();
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if(isIn(nr, nc) && arr[nr][nc]<arr[p.r][p.c]) {
					if(dp[nr][nc]==0) pq.offer(new Pos(nr, nc, arr[nr][nc]));
					dp[nr][nc] += dp[p.r][p.c];
				}
			}
		}
	}

	private static boolean isIn(int nr, int nc) {
		return nr>=0 && nc>=0 && nr<N && nc<M;
	}
}