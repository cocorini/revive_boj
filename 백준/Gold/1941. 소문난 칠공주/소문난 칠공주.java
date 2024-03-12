import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {
	static int[][] seat = new int[5][5];
	static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};
	static boolean[][] visited = new boolean[5][5];
	static int ans = 0 ;
	static class Pos{
		int r, c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 5; i++) {
			String s = br.readLine();
			for (int j = 0; j < 5; j++) {
				if(s.charAt(j)=='S') {
					seat[i][j] = 1;
				}
			}
		}
		recur(0, 0, 0);
		System.out.println(ans);
	}

	private static void recur(int cnt, int R, int C) {
		if(cnt==7) {
			if(!isStick()) return;
			int scnt = 0;
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if(visited[i][j] && seat[i][j]==1) scnt++;
				}
			}
			if(scnt>=4) ans++;
			return;
		}
		if(C==5) {
			recur(cnt, R+1, 0);
			return;
		}
		if(R==5) return;
		
		visited[R][C] = true;
		recur(cnt+1, R, C+1);
		visited[R][C] = false;
		recur(cnt, R, C+1);
	}

	private static boolean isStick() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if(visited[i][j]) {
					if(bfs(i, j)) return true;
					else return false;
				}
			}
		}
		return false;
	}

	private static boolean bfs(int r, int c) {
		boolean[][] tmp_visited = new boolean[5][5];
		Deque<Pos> q = new ArrayDeque<>();
		q.offer(new Pos(r, c));
		tmp_visited[r][c] = true;
		int cnt = 1;
		while(!q.isEmpty()) {
			Pos p = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if(isIn(nr, nc)&&visited[nr][nc]&&!tmp_visited[nr][nc]) {
					q.offer(new Pos(nr, nc));
					tmp_visited[nr][nc] = true;
					cnt++;
				}
			}
		}
		if(cnt==7) return true;
		return false;
	}

	private static boolean isIn(int ni, int nj) {
		return ni<5 && nj<5 && ni>=0 && nj>=0;
	}	
}