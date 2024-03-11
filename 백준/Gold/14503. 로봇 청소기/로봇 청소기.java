import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static StringTokenizer st;
	static class Robot {
		int r, c, dir;
		public Robot(int r, int c, int dir) {
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}
	static Robot robot;
	static int[][] room;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1}; // 상 우 하 좌
	static int[] change_dir = {3, 0, 1, 2}, back_dir = {2, 3, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		robot = new Robot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		room = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		clean();
	}

	private static void clean() {
		int r = robot.r, c = robot.c, dir = robot.dir;
		int clean_cnt = 0;
		int idx = 0;
		while(true) {
			// 청소하기
			if(room[r][c]==0) {
				room[r][c] = -1;
				clean_cnt++;
				
			}
			// 주변 4칸 청소되지 않은 칸 있는지 찾기
			boolean isClean = true;
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(isIn(nr, nc)) {
					if(room[nr][nc]==0) isClean = false;
				}
			}
			if(!isClean) {
				// 반시계 방향으로 회전
				int ndir = change_dir[dir];
				int nr = r + dr[ndir];
				int nc = c + dc[ndir];
				if(isIn(nr, nc)) {
					if(room[nr][nc]==0) {
						r = nr;
						c = nc;	
					}
					dir = ndir;
				}
			}else {
				// 후진 가능?
				int ndir = back_dir[dir];
				int nr = r + dr[ndir];
				int nc = c + dc[ndir];
				if(isIn(nr, nc)) {
					if(room[nr][nc]==1) {
						System.out.println(clean_cnt);
						return;
					}
					r = nr;
					c = nc;
				}
			}
		}
	}

	private static boolean isIn(int nr, int nc) {
		return nr>=0 && nc>=0 && nr<N && nc<M;
	}

}