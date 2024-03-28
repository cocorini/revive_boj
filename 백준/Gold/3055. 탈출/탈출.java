import java.io.*;
import java.util.*;

public class Main {
	static StringTokenizer st;
	static int R, C;
	static int[][] water;
	static final int value = 2000000000;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};
	static Deque<Pos> wq= new ArrayDeque<>();
	static Deque<KakPos>  kq = new ArrayDeque<>();
	static class Pos {
		int r, c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static class KakPos extends Pos{
		int time;
		public KakPos(int r, int c, int time) {
			super(r, c);
			this.time = time;
		}
	}
	static Pos cave;
	public static void main(String[] args) throws IOException {
		init();
		
		while(!wq.isEmpty()) {
			Pos p = wq.poll();
			for (int d = 0; d < 4; d++) {
				int nr = p.r+dr[d];
				int nc = p.c+dc[d];
				if(isIn(nr, nc) && water[nr][nc]==value) {
					water[nr][nc] = water[p.r][p.c]+1;
					wq.offer(new Pos(nr, nc));
				}
			}
		}
		while(!kq.isEmpty()) {
			KakPos p = kq.poll();
			int ntime = p.time+1;
			for (int d = 0; d < 4; d++) {
				int nr = p.r+dr[d];
				int nc = p.c+dc[d];
				if(isIn(nr, nc) && water[nr][nc]>ntime && !visited[nr][nc]) {
					if(nr==cave.r && nc==cave.c) {
						System.out.println(ntime);
						return;
					}
					visited[nr][nc] = true;
					kq.offer(new KakPos(nr, nc, ntime));
				}
			}
		}
		System.out.println("KAKTUS");
	}

	private static boolean isIn(int nr, int nc) {
		return nr<R && nc<C && nr>=0 && nc>=0;
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		visited = new boolean[R][C];
		water = new int[R][C];
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				if(s.charAt(j)=='.') {
					water[i][j] = value;
				}else if(s.charAt(j)=='*') {
					water[i][j] = 0;
					wq.offer(new Pos(i, j));
				}else if(s.charAt(j)=='D') {
					cave = new Pos(i, j);
					water[i][j] = Integer.MAX_VALUE;
				}else if(s.charAt(j)=='S') {
					visited[i][j] = true;
					water[i][j] = value;
					kq.offer(new KakPos(i, j, 0));
				}else if(s.charAt(j)=='X') {
					water[i][j] = -5;
				}
			}
		}
	}
}