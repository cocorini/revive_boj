import java.io.*;
import java.util.*;

public class Main {
	static int N, Left, Right;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};
	static int[][] region, union;
	static class Pos {
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
		Left = Integer.parseInt(st.nextToken());
		Right = Integer.parseInt(st.nextToken());
		region = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				region[i][j] = Integer.parseInt(st.nextToken());
		}
		movPop();
	}

	private static void movPop() {
		int date = 0;
		while(true) {
			union = new int[N][N];
			int count = 1;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(union[i][j]==0) bfs(i, j, count++);
				}
			}
			if(count==N*N+1) {
				System.out.println(date);
				return;
			}
			date++;
		}
	}

	private static void bfs(int R, int C, int count) {
		Deque<Pos> q = new ArrayDeque<>();
		q.offer(new Pos(R, C));
		union[R][C] = count;
		int pop_sum = region[R][C];
		int region_cnt = 1;
		while(!q.isEmpty()) {
			Pos p = q.poll();
			int r = p.r, c = p.c;
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(isIn(nr, nc) && union[nr][nc]==0 && Math.abs(region[r][c]-region[nr][nc])>=Left && Math.abs(region[r][c]-region[nr][nc])<=Right) {
					union[nr][nc] = count;
					pop_sum += region[nr][nc];
					region_cnt++;
					q.offer(new Pos(nr, nc));
				}
			}
		}
		if(region_cnt==1) return;
		int pop_avg = pop_sum/region_cnt;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if(union[i][j]==count) region[i][j] = pop_avg;
	}

	private static boolean isIn(int nr, int nc) {
		return nr<N && nc<N && nr>=0 && nc>=0;
	}
}