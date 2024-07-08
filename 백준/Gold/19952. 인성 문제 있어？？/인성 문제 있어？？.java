import java.io.*;
import java.util.*;

public class Main {
	static int T, W, H, O, F, sr, sc, er, ec;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};
	static Map<String, Integer> maze;
	static boolean[][] visited;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static class Pos {
		int r, c, powerByTime;
		public Pos(int r, int c, int powerByTime) {
			this.r = r;
			this.c = c;
			this.powerByTime = powerByTime;
		}
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		
		for (int t = 0; t < T; t++) {
			input();
			if(isFine()) sb.append("잘했어!!\n");
			else sb.append("인성 문제있어??\n");
		}
		
		System.out.println(sb);
	}
	
	private static boolean isFine() {
		Deque<Pos> q = new ArrayDeque<>();
		q.offer(new Pos(sr, sc, F));
		visited[sr][sc] = true;
		while(!q.isEmpty()) {
			Pos pos = q.poll();
			int power = pos.powerByTime;
			if(power == 0) continue;
			int val = maze.getOrDefault(""+pos.r+" "+pos.c, 0);
			for (int d = 0; d < 4; d++) {
				int nr = pos.r + dr[d];
				int nc = pos.c + dc[d];
				int nval = maze.getOrDefault(""+nr+" "+nc, 0);
				if(isIn(nr, nc) && power>=nval-val && !visited[nr][nc]) {
					if(nr==er&&nc==ec) return true;
					visited[nr][nc] = true;
					q.offer(new Pos(nr, nc, power-1));
				}
			}
		}
		return false;
	}

	private static boolean isIn(int nr, int nc) {
		return nr<H && nc<W && nr>=0 && nc>=0;
	}

	private static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		O = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());
		sr = Integer.parseInt(st.nextToken())-1;
		sc = Integer.parseInt(st.nextToken())-1;
		er = Integer.parseInt(st.nextToken())-1;
		ec = Integer.parseInt(st.nextToken())-1;
		maze = new HashMap<>();
		for (int i = 0; i < O; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int l = Integer.parseInt(st.nextToken());
			maze.put(""+r+" "+c, l);
		}
		visited = new boolean[H][W];
	}
}