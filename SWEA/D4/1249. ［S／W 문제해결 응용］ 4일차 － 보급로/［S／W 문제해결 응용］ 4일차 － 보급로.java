import java.io.*;
import java.util.*;

public class Solution {
	static StringTokenizer st;
	static int N, map[][];
	static class Pos {
		int r, c, time;
		public Pos(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}
	static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < N; j++)
					map[i][j] = s.charAt(j)-'0';
			}
			bfs(test_case);
		}
	}

	private static void bfs(int t) {
		PriorityQueue<Pos> pq = new PriorityQueue<>((o1, o2)->Integer.compare(o1.time, o2.time));
		pq.offer(new Pos(0, 0, 0));
		while(!pq.isEmpty()) {
			Pos p = pq.poll();
			if(p.r==N-1 && p.c==N-1) {
				System.out.println("#"+t+" "+p.time);
				return;
			}
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if(isIn(nr, nc) && !visited[nr][nc]) {
					visited[nr][nc] = true;
					pq.offer(new Pos(nr, nc, p.time+map[nr][nc]));
				}
			}
		}
	}

	private static boolean isIn(int nr, int nc) {
		return nr>=0 && nc>=0 && nr<N && nc<N;
	}
}