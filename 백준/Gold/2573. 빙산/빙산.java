import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static int N, M;
	static int[][][] ice;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};
	static class Pos {
		int r, c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ice = new int[N][M][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				ice[i][j][0] = Integer.parseInt(st.nextToken());
			}
		}
		simulation();
	}

	private static void simulation() {
		Deque<Pos> q;
		int time = 0;
		while(true) {
			// flud fill 확인
			if(!isGroup()) {
				System.out.println(time);
				return;
			}
			q = new ArrayDeque<>();
			int zero_cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(ice[i][j][0]!=0) {
						q.offer(new Pos(i, j));
						ice[i][j][1] = check(i, j);
					} else zero_cnt++;
				}
			}
			if(zero_cnt==N*M) {
				System.out.println(0);
				return;
			}
			while(!q.isEmpty()) {
				Pos p = q.poll();
				ice[p.r][p.c][0] -= ice[p.r][p.c][1];
				if(ice[p.r][p.c][0]<0) ice[p.r][p.c][0] = 0;
			}
			time++;
		}
	}

	private static boolean isGroup() {
		Deque<Pos> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];
		a: for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(ice[i][j][0]>0) {
					visited[i][j] = true;
					q.offer(new Pos(i, j));
					while(!q.isEmpty()) {
						Pos p = q.poll();
						for (int d = 0; d < 4; d++) {
							int nr = p.r + dr[d];
							int nc = p.c + dc[d];
							if(isIn(nr, nc) && !visited[nr][nc] && ice[nr][nc][0]>0) {
								visited[nr][nc] = true;
								q.offer(new Pos(nr, nc));
							}
						}
					}
					for (int ii = 0; ii < N; ii++) {
						for (int jj = 0; jj < M; jj++) {
							if(!visited[ii][jj] && ice[ii][jj][0]>0) return false;
						}
					}
					break a;
				}
			}
		}
		return true;
	}

	private static int check(int r, int c) {
		int cnt = 0;
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(isIn(nr, nc) && ice[nr][nc][0]==0) cnt++;
		}
		return cnt;
	}

	private static boolean isIn(int r, int c) {
		return r<N && c<M && r>=0 && c>=0;
	}

}