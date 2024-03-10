import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int N, M, TIME = 0;
	static StringTokenizer st;
	static int[][] grid, visited;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};
	static class Pos {
		int r, c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static class sPos extends Pos{
		int eat_cnt, size;
		public sPos(int r, int c, int eat_cnt, int size) {
			super(r, c);
			this.eat_cnt = eat_cnt;
			this.size = size;
		}
	}
	static sPos shark = new sPos(0, 0, 0, 2);
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		grid = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
				if(grid[i][j]==9) {
					shark.r = i; shark.c = j; grid[i][j] = 0;
				}
			}
		}
		
		while(isSearch()) {}
		System.out.println(TIME);
	}

	private static boolean isSearch() {
		int sr = shark.r, sc = shark.c;
		int dist = Integer.MAX_VALUE;
		int fr = -1, fc = -1;
		bfs(sr, sc);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(i==sr && j==sc) continue;
				if(visited[i][j]>0 && grid[i][j]>0 && grid[i][j]<shark.size && dist>visited[i][j]) {
					dist = visited[i][j];
					fr = i; fc = j;
				}
			}
		}
		if(dist!=Integer.MAX_VALUE) {
			TIME += dist;
			grid[fr][fc] = 0;
			shark.r = fr; shark.c = fc; shark.eat_cnt++;
			if(shark.eat_cnt == shark.size) {
				shark.eat_cnt = 0;
				shark.size++;
			}
			return true;
		}
		return false;
	}
	
	private static void bfs(int sr, int sc) {
		visited = new int[N][N];
		Deque<Pos> q = new ArrayDeque<>();
		q.offer(new Pos(sr, sc));
		visited[sr][sc] = 0;
		while(!q.isEmpty()) {
			Pos p = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if(isIn(nr, nc) && visited[nr][nc]==0 && !(nr==sr && nc==sc)) {
					if(grid[nr][nc]<=shark.size) {
						q.offer(new Pos(nr, nc));
						visited[nr][nc] = visited[p.r][p.c]+1;
					}
				}
			}
		}
		// 이 함수가 종료된 시점에는 visited에 갈 수 있는 곳에 가는데 걸리는 시간이 저장된다.
	}

	private static boolean isIn(int r, int c) {
		return r<N && c<N && r>=0 && c>=0;
	}
}