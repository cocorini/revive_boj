import java.io.*;
import java.util.*;

public class Main {
	static int N, M, group = 1;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};
	static boolean[][] visited;
	static char[][] fieldMap;
	static class Pos{
		int r, c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fieldMap = new char[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++)
				fieldMap[i][j] = s.charAt(j);
		}

		for (int r = 0; r < N; r++)
			for (int c = 0; c < M; c++)
				if(!visited[r][c]) {
					bfs(r, c);
					group++;
				}
		
		System.out.println(group-1);
	}
	private static void bfs(int R, int C) {
		Deque<Pos> q = new ArrayDeque<>();
		q.offer(new Pos(R, C));
		visited[R][C] = true;
		while(!q.isEmpty()) {
			Pos p = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if(isIn(nr, nc)){
					char c = fieldMap[nr][nc];
					int dir = 0;
					if(c=='L') dir = 1;
					else if(c=='D') dir = 2;
					else if(c=='R') dir = 3;
					int nnr = nr + dr[dir];
					int nnc = nc + dc[dir];
					if(nnr==p.r && nnc==p.c && !visited[nr][nc]) {
						visited[nr][nc] = true;
						q.offer(new Pos(nr, nc));
					}
				}
			}
			int dir = 0;
			if(fieldMap[p.r][p.c]=='L') dir = 1;
			else if(fieldMap[p.r][p.c]=='D') dir = 2;
			else if(fieldMap[p.r][p.c]=='R') dir = 3;
			if(!visited[p.r+dr[dir]][p.c+dc[dir]]) {
				visited[p.r+dr[dir]][p.c+dc[dir]] = true;
				q.offer(new Pos(p.r+dr[dir], p.c+dc[dir]));
			}
		}
	}
	private static boolean isIn(int r, int c) {
		return r<N && r>=0 && c<M && c>=0;
	}
}