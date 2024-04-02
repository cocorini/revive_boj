import java.io.*;
import java.util.*;

public class Main {
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, M, castle[][], visited[][];
	static int[] dr = {1, 0, -1, 0}, dc = {0, 1, 0, -1};
	static boolean dir[];
	static Map<Integer, Integer> map = new HashMap<>();
	static List<boolean[]> list = new ArrayList<>();
	static class Pos {
		int r, c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		init();
		find_dir(0);
		flud_fill();
		union();
		System.out.println(sb);
	}

	private static void union() {
		int MAX = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int d = 0; d < 4; d++) {
					int nr = i+dr[d];
					int nc = j+dc[d];
					if(isIn(nr, nc) && visited[nr][nc] != visited[i][j])
						MAX = Math.max(MAX, map.get(visited[nr][nc])+map.get(visited[i][j]));
				}
			}
		}
		sb.append(MAX);
	}

	private static void flud_fill() {
		int cnt = 1, max_width = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(visited[i][j]==0) {
					visited[i][j] = cnt;
					Deque<Pos> q = new ArrayDeque<>();
					q.offer(new Pos(i, j));
					int width = 1;
					while(!q.isEmpty()) {
						Pos p = q.poll();
						int k = castle[p.r][p.c];
						for (int d = 0; d < 4; d++) {
							boolean direc = list.get(k)[d];
							if(!direc) {
								int nr = p.r+dr[d];
								int nc = p.c+dc[d];
								if(isIn(nr, nc) && visited[nr][nc]==0) {
									visited[nr][nc] = cnt;
									q.offer(new Pos(nr, nc));
									width++;
								}
							}
						}
					}
					map.put(cnt, width);
					cnt++;
					max_width = Math.max(max_width, width);
				}
			}
		}
		sb.append((cnt-1)+"\n"+max_width+"\n");
	}

	private static boolean isIn(int nr, int nc) {
		return nr>=0 && nc>=0 && nr<N && nc<M;
	}

	private static void find_dir(int cnt) {
		if(cnt==4) {
			list.add(dir.clone());
			return;
		}
		dir[cnt] = false;
		find_dir(cnt+1);
		dir[cnt] = true;
		find_dir(cnt+1);
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		castle = new int[N][M];
		visited = new int[N][M];
		dir = new boolean[4];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				castle[i][j] = Integer.parseInt(st.nextToken());
		}
	}
}