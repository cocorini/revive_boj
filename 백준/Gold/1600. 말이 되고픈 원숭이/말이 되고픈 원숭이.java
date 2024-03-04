import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	private static int K, W, H, Ans = Integer.MAX_VALUE;
	private static int[][] field;
	private static boolean[][][] visited;
	private static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1}, hdr = {2, 2, -2, -2, 1, 1, -1, -1}, hdc= {1, -1, 1, -1, 2, -2, 2, -2};
	private static class State{
		int r, c, k, cnt;
		State(int r, int c, int k, int cnt){
			this.r = r;
			this.c = c;
			this.k = k;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken()); H = Integer.parseInt(st.nextToken());
		field = new int[H][W];
		visited = new boolean[H][W][K+1];
		
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				field[i][j] = Integer.parseInt(st.nextToken());
				if(field[i][j] == 1) 
					for (int k = 0; k <= K; k++) visited[i][j][k]=true;
			}
		}
		bfs();
		if(Ans==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(Ans);
	}

	private static void bfs() {
		Deque<State> q = new ArrayDeque<>();
		q.offer(new State(0, 0, K, 0));
		for (int k = 0; k <= K; k++) visited[0][0][k]=true;
		
		while(!q.isEmpty()) {
			State state = q.poll();
			int r = state.r, c = state.c, k = state.k, cnt = state.cnt;
			if(r==H-1 && c==W-1) {
				Ans = Math.min(Ans, cnt);
				continue;
			}
			for (int d = 0; d < 4; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				if(isIn(nr, nc) && !visited[nr][nc][k]) {
					visited[nr][nc][k] = true;
					int ncnt = cnt + 1;
					q.add(new State(nr, nc, k, ncnt));
				}
			}
			if(k > 0) {
				for (int d = 0; d < 8; d++) {
					int nr = r+hdr[d];
					int nc = c+hdc[d];
					if(isIn(nr, nc) && !visited[nr][nc][k-1]) {
						visited[nr][nc][k-1] = true;
						int ncnt = cnt + 1;
						q.add(new State(nr, nc, k-1, ncnt));
					}
				}
			}
		}
	}

	private static boolean isIn(int r, int c) {
		return r<H && c<W && r>=0 && c>=0;
	}
}