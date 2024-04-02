import java.io.*;
import java.util.*;

public class Main {
	static StringTokenizer st;
	static int N, M, K;
	static final int MAX = 1000000000;
	static int[][] map;
	static class Pos {
		int r, c, cnt;
		public Pos(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		map = new int[N+1][M+1];
		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= M; j++)
				map[i][j] = MAX;
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken()), d = Integer.parseInt(st.nextToken());
			make_line(r, c, d);
		}
		PriorityQueue<Pos> pq = new PriorityQueue<>((o1, o2)->Integer.compare(o1.cnt, o2.cnt));
		pq.offer(new Pos(1, 1, 0));
		map[1][1] = 0;
		while(!pq.isEmpty()) {
			Pos p = pq.poll();
			if(p.r==N && p.c==M) {
				System.out.println("YES\n"+p.cnt);
				return;
			}
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if(isIn(nr, nc) && map[nr][nc]>p.cnt+1 && map[nr][nc]>=0) {
					map[nr][nc] = p.cnt + 1;
					pq.offer(new Pos(nr, nc, p.cnt+1));
				}
			}
		}
		System.out.println("NO");	
	}

	private static void make_line(int r, int c, int depth) {
		for (int i = 0; i <= depth; i++) {
			if(isIn(r+i, c-depth+i)) map[r+i][c-(depth-i)] = -1;
			if(isIn(r+(depth-i), c+i)) map[r+(depth-i)][c+i] = -1;
			if(isIn(r-(depth-i), c+i)) map[r-(depth-i)][c+i] = -1;
			if(isIn(r-i, c-depth+i)) map[r-i][c-(depth-i)] = -1;
		}
	}

	private static boolean isIn(int nr, int nc) {
		return nr<=N && nc<=M && nr>0 && nc>0;
	}
}