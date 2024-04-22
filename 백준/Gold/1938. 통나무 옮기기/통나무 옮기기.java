import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static char[][] arr;
	static int[][][] B = new int[3][1][2], E = new int[3][1][2];
	static int[] dr = {0, -1, 1, 0}, dc = {-1, 0, 0, 1};
	static class Pos {
		int r, c;
		boolean row;
		public Pos(int r, int c, boolean row) {
			this.r = r;
			this.c = c;
			this.row = row;
		}
	}
	static class qPos extends Pos{
		int cnt;
		public qPos(int r, int c, boolean row, int cnt) {
			super(r, c, row);
			this.cnt = cnt;
		}
	}
	static Pos BC, EC; // center pos
	static boolean[][][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new char[N][N];
		visited = new boolean[N][N][2];
		int bi = 0, ei = 0;
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < N; j++) {
				arr[i][j] = input.charAt(j);
				if(arr[i][j] == 'B') {
					B[bi][0][0] = i; B[bi++][0][1] = j;
				} else if(arr[i][j] == 'E') {
					E[ei][0][0] = i; E[ei++][0][1] = j;
				}
			}
		}
		// x좌표가 같으면 세로, 세로 일 때 false
		if(B[0][0][0]==B[1][0][0]) BC = new Pos(B[1][0][0], B[1][0][1], true);
		else BC = new Pos(B[1][0][0], B[1][0][1], false);
		
		if(E[0][0][0]==E[1][0][0]) EC = new Pos(E[1][0][0], E[1][0][1], true);
		else EC = new Pos(E[1][0][0], E[1][0][1], false);
		
		move();
	}


	private static void move() {
		Deque<qPos> q = new ArrayDeque<>();
		q.offer(new qPos(BC.r, BC.c, BC.row, 0));
		if(BC.row) visited[BC.r][BC.c][0] = true;
		else visited[BC.r][BC.c][1] = true;
		while(!q.isEmpty()) {
			qPos p = q.poll();
			boolean row = p.row;
			if(p.r==EC.r && p.c==EC.c && p.row==EC.row) {
				System.out.println(p.cnt);
				return;
			}
			if(p.row) {
				int lr = p.r, lc = p.c-1;
				int rr = p.r, rc = p.c+1;
				// 이동
				for (int d = 0; d < 4; d++) {
					int nlr = lr+dr[d], nlc = lc+dc[d];
					int nrr = rr+dr[d], nrc = rc+dc[d];
					int nr = p.r+dr[d], nc = p.c+dc[d];
					if(isIn(nlr, nlc, nrr, nrc, nr, nc) && !visited[nr][nc][0]) {
						if(isOk(nlr, nlc, nrr, nrc, nr, nc)) {
							visited[nr][nc][0] = true;
							q.offer(new qPos(nr, nc, row, p.cnt+1));
						}
					}
				}
				// 회전
				if(isRotate(p.r, p.c) && !visited[p.r][p.c][1]) {
					visited[p.r][p.c][1] = true;
					q.offer(new qPos(p.r, p.c, false, p.cnt+1));
				}
			}else {
				int lr = p.r-1, lc = p.c;
				int rr = p.r+1, rc = p.c;
				// 이동
				for (int d = 0; d < 4; d++) {
					int nlr = lr+dr[d], nlc = lc+dc[d];
					int nrr = rr+dr[d], nrc = rc+dc[d];
					int nr = p.r+dr[d], nc = p.c+dc[d];
					if(isIn(nlr, nlc, nrr, nrc, nr, nc) && !visited[nr][nc][1]) {
						if(isOk(nlr, nlc, nrr, nrc, nr, nc)) {
							visited[nr][nc][1] = true;
							q.offer(new qPos(nr, nc, row, p.cnt+1));
						}
					}
				}
				// 회전
				if(isRotate(p.r, p.c) && !visited[p.r][p.c][0]) {
					visited[p.r][p.c][0] = true;
					q.offer(new qPos(p.r, p.c, true, p.cnt+1));
				}
			}
		}
		System.out.println(0);
	}

	private static boolean isOk(int nlr, int nlc, int nrr, int nrc, int nr, int nc) {
		return arr[nlr][nlc]!='1' && arr[nrr][nrc]!='1' && arr[nr][nc]!='1';
	}

	private static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<N && nc>=0 && nc<N;
	}
	
	private static boolean isIn(int nlr, int nlc, int nrr, int nrc, int nr, int nc) {
		return nlr<N && nlr>=0 && nlc<N && nlc>=0 && nrr<N && nrr>=0 && nrc<N && nrc>=0 && nr<N && nr>=0 && nc<N && nc>=0;
	}
	
	private static boolean isRotate(int r, int c) {
		int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0}, dc = {-1, 0, 1, 1, 1, 0, -1, -1};
		for (int d = 0; d < 8; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(isIn(nr, nc)) {
				if(arr[nr][nc]=='1') return false;
			} else return false;
		}
		return true;
	}
}