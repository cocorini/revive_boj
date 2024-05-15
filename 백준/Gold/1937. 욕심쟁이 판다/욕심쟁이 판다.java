import java.io.*;
import java.util.*;

public class Main {
	static int N, forest[][], max_visited[][], dr[] = {-1, 0, 1, 0}, dc[] = {0, -1, 0, 1};
	static class Pos {
		int r, c, w;
		public Pos(int r, int c, int w) {
			this.r = r;
			this.c = c;
			this.w = w;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		forest = new int[N][N];
		max_visited = new int[N][N];
		
		PriorityQueue<Pos> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.w, o1.w));
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				forest[i][j] = Integer.parseInt(st.nextToken());
				pq.offer(new Pos(i, j, forest[i][j]));
			}
		}
		
		int ANS = 0;
		while(!pq.isEmpty()) {
			Pos p = pq.poll();
			max_visited[p.r][p.c] = 1;
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if(isIn(nr, nc) && forest[p.r][p.c] < forest[nr][nc]) max_visited[p.r][p.c] = Math.max(max_visited[p.r][p.c], max_visited[nr][nc]+1);
			}
			ANS = Math.max(ANS, max_visited[p.r][p.c]);
		}
		System.out.println(ANS);
	}

	private static boolean isIn(int nr, int nc) {
		return nr>=0 && nc>=0 && nr<N && nc<N;
	}
}