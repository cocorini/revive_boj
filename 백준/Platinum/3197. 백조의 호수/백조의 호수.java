import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int R, C;
	static int[][] ground;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
	static PriorityQueue<State> q1 = new PriorityQueue<>();
	static Deque<Pos> q2;
	static class State implements Comparable<State>{
		int r, c, d, max_d;
		public State(int r, int c, int d, int max_d) {
			this.r = r;
			this.c = c;
			this.d = d;
			this.max_d = max_d;
		}
		@Override
		public int compareTo(State s) {
			return Integer.compare(d,  s.d);
		}
	}
	static class Pos{
		int r, c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static Pos[] swan = new Pos[2];
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); C = Integer.parseInt(st.nextToken());
		ground = new int[R][C];
		visited = new boolean[R][C];
		for (int i = 0, k = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				if(s.charAt(j)=='.') ground[i][j] = 0;
				else if(s.charAt(j)=='X') ground[i][j] = -1;
				else {
					ground[i][j] = 0;
					swan[k++] = new Pos(i, j);
				}
			}
		}
		
		q2 = new ArrayDeque<>();
		// 빙하 녹는 일수 ground에 표현하기
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(ground[i][j]==0) {
					visited[i][j] = true;
					for (int d = 0; d < 4; d++) {
						int ni = i + dr[d];
						int nj = j + dc[d];
						if(isIn(ni, nj) && !visited[ni][nj] && ground[ni][nj]==-1) {
							visited[ni][nj] = true;
							ground[ni][nj] = 1;
							q2.offer(new Pos(ni, nj));
						}
					}
				}
			}
		}
		// q2에는 처음 녹는 빙하의 좌표가 들어감
		
		// ground에 빙하가 녹는 날을 기록
		bfs();
		
		// L부터 L까지 가는 모든 경로 중에 경로마다 최댓값을 구하고 모든 최댓값 중 최솟값을 구한다.
		q1.offer(new State(swan[0].r, swan[0].c, 0, 0));
		visited = new boolean[R][C];
		while(!q1.isEmpty()) {
			State s = q1.poll();
			if(s.r==swan[1].r && s.c==swan[1].c) {
				System.out.println(s.max_d);
				return;
			}
			for (int d = 0; d < 4; d++) {
				int nr = s.r + dr[d];
				int nc = s.c + dc[d];
				if(isIn(nr, nc) && !visited[nr][nc]) {
					visited[nr][nc] = true;
					q1.offer(new State(nr, nc, ground[nr][nc], Math.max(s.d, s.max_d)));
				}
			}
		}
		
	}
	private static void bfs() {
		while(!q2.isEmpty()) {
			Pos p = q2.poll();
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if(isIn(nr, nc) &&!visited[nr][nc] && ground[nr][nc]==-1) {
					visited[nr][nc] = true;
					ground[nr][nc] = ground[p.r][p.c] + 1;
					q2.offer(new Pos(nr, nc));
				}
			}
		}
	}
	private static boolean isIn(int r, int c) {
		return r>=0 && c>=0 && r<R && c<C;
	}
}