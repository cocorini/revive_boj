import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};
	static boolean[][] cave;
	static int[] throwStickOrder;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static class Pos {
		int r, c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		input();
		simulation();
		output();	
	}

	private static void output() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(!cave[i][j]) sb.append('.');
				else sb.append('x');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

	private static void simulation() {
		boolean isLeft;
		for (int t = 0; t < throwStickOrder.length; t++) {
			if(t%2==1) isLeft = false;
			else isLeft = true;
			int ROW = N-1-throwStickOrder[t]+1;
			if(isLeft) {
				for (int i = 0; i < M; i++) {
					if(cave[ROW][i]) {
						// 해당 부분의 미네랄 깨짐
						cave[ROW][i] = false;
						// 주요 로직
						DropMineralInAir();
						break;
					}
				}
			} else {
				for (int i = M-1; i >= 0; i--) {
					if(cave[ROW][i]) {
						// 해당 부분의 미네랄 깨짐
						cave[ROW][i] = false;
						// 주요 로직
						DropMineralInAir();
						break;
					}
				}
			}
		}
	}

	private static void DropMineralInAir() {
		boolean[][] visited = new boolean[N][M];
		ArrayDeque<Pos> q = new ArrayDeque<>();
		for (int j = 0; j < M; j++) {
			if(cave[N-1][j]) {
				visited[N-1][j] = true;
				q.offer(new Pos(N-1, j));
				while(!q.isEmpty()) {
					Pos pos = q.poll();
					for (int d = 0; d < 4; d++) {
						int nr = pos.r + dr[d];
						int nc = pos.c + dc[d];
						if(isIn(nr, nc) && !visited[nr][nc] && cave[nr][nc]) {
							visited[nr][nc] = true;
							q.offer(new Pos(nr, nc));
						}
					}
				}
			}
		}
		
		for (int j = 0; j < M; j++)
			for (int i = N-1; i >= 0; i--)
				if(!visited[i][j] && cave[i][j]) {
					q.offer(new Pos(i, j));
					break;
				}
		
		// 공중에 있는 미네랄이 없어 글면 return;
		if(q.isEmpty()) return;
		
		int dropDist = 1000;
		while(!q.isEmpty()) {
			Pos pos = q.poll();
			int tmpDropDist = N-1-pos.r;
			for (int i = pos.r+1; i < N; i++) {
				if(cave[i][pos.c])
					tmpDropDist = Math.min(tmpDropDist, Math.abs(i-pos.r-1));
			}
			dropDist = Math.min(dropDist, tmpDropDist);
		}

		PriorityQueue<Pos> pq = new PriorityQueue<>((o1, o2)->Integer.compare(o2.r, o1.r));
		
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if(!visited[i][j] && cave[i][j]) 
					pq.offer(new Pos(i, j));

		dropMineral(dropDist, pq);
	}

	private static void dropMineral(int dropDist, PriorityQueue<Pos> pq) {
		while(!pq.isEmpty()) {
			Pos pos = pq.poll();
			cave[pos.r][pos.c] = false;
			cave[pos.r+dropDist][pos.c] = true;
		}
	}

	private static boolean isIn(int nr, int nc) {
		return nr>=0 && nc>=0 && nr<N && nc<M;
	}

	private static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cave = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				if(input.charAt(j)=='.') cave[i][j] = false; 
				else cave[i][j] = true;
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int len = Integer.parseInt(st.nextToken());
		
		throwStickOrder = new int[len];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < len; i++) throwStickOrder[i] = Integer.parseInt(st.nextToken());
	}
}