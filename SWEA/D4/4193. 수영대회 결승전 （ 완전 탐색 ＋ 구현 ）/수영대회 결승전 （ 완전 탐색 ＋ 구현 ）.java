import java.io.*;
import java.util.*;

public class Solution {
	static StringTokenizer st;
	static int N;
	static int[][] pool;
	static class Pos{
		int r, c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static class Info extends Pos{
		int time;
		public Info(int r, int c, int time) {
			super(r, c);
			this.time = time;
		}
	}
	static Pos start, end;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			pool = new int[N][N];
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					pool[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			st = new StringTokenizer(br.readLine());
			start = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			st = new StringTokenizer(br.readLine());
			end = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			bfs(test_case);
		}
	}

	private static void bfs(int t) {
		PriorityQueue<Info> pq = new PriorityQueue<>((o1, o2)->Integer.compare(o1.time, o2.time));
		pq.offer(new Info(start.r, start.c, 0));
		visited[start.r][start.c] = true;
		while(!pq.isEmpty()) {
			Info p = pq.poll();
			if(p.r==end.r&&p.c==end.c) {
				System.out.println("#"+t+" "+p.time);
				return;
			}
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if(isIn(nr, nc) && !visited[nr][nc]) {
					visited[nr][nc] = true;
					if(pool[nr][nc]==0) {
						pq.offer(new Info(nr, nc, p.time+1));
					} else if(pool[nr][nc]==2) {
						if(p.time%3==0) {
							pq.offer(new Info(nr, nc, p.time+3));
						} else if(p.time%3==1) {
							pq.offer(new Info(nr, nc, p.time+2));
						} else if(p.time%3==2) {
							pq.offer(new Info(nr, nc, p.time+1));
						}
					}
				}
			}
		}
		System.out.println("#"+t+" "+-1);
	}

	private static boolean isIn(int nr, int nc) {
		return (nr<N && nc<N && nr>=0 && nc>=0);
	}
}