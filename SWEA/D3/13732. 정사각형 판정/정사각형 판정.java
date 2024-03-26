import java.io.*;
import java.util.*;

public class Solution {
	static StringTokenizer st;
	static int N;
	static int[][] grid;
	static class Pos {
		int r, c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int[] dr = {-1, 0, 1, 0}, dc= {0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			grid = new int[N][N];
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < N; j++) {
					if(s.charAt(j)=='.') {
						grid[i][j] = 0;
					}else {
						grid[i][j] = 1;
					}
				}
			}
			a: for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(grid[i][j]==1) {
						bfs(i, j);
						System.out.println("#"+test_case+check(i, j));
						break a;
					}
				}
			}
		}
	}

	private static String check(int r, int c) {
		int size = 0;
		for (int j = c; j < N; j++) {
			if(grid[r][j]==2) size++;
		}
		
		for (int i = r; i < r+size; i++)
			for (int j = c; j < c+size; j++)
				if(grid[i][j]==2) grid[i][j] = 3;
				else grid[i][j] = -1;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(grid[i][j] < 3 && grid[i][j] != 0) return " no";
			}
		}
		return " yes";
	}

	private static void bfs(int r, int c) {
		Deque<Pos> q = new ArrayDeque<>();
		q.offer(new Pos(r, c));
		grid[r][c] = 2;
		while(!q.isEmpty()) {
			Pos p = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if(isIn(nr, nc) && grid[nr][nc]==1) {
					grid[nr][nc]=2;
					q.offer(new Pos(nr, nc));
				}
			}
		}
	}

	private static boolean isIn(int nr, int nc) {
		return nr>=0 && nc>=0 && nr<N && nc<N;
	}
}