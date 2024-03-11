import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static int W, H, size, ANS;
	static int[][] room, adjacent_list;
	static class Pos{
		int r, c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static Pos robot;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1}, order;
	static List<Pos> dirty_list;
	static class Edge implements Comparable<Edge>{
		int node, cost;
		public Edge(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}
		@Override
		public int compareTo(Edge E) {
			return Integer.compare(cost, E.cost);
		}
	}
	static boolean[] visit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			dirty_list = new ArrayList<>();
			if(W==0 && H==0) return;
			room = new int[H][W];
			for (int i = 0; i < H; i++) {
				String s = br.readLine();
				for (int j = 0; j < W; j++) {
					char c = s.charAt(j);
					if(c=='.') {
						room[i][j] = 0;
					} else if(c=='o') {
						robot = new Pos(i, j);
						room[i][j] = 0;
					} else if(c=='*') {
						room[i][j] = 1;
						dirty_list.add(new Pos(i, j));
					} else if(c=='x') {
						room[i][j] = -1;
					}
				}
			}
			ANS = Integer.MAX_VALUE;
			size = dirty_list.size()+1;
			visit = new boolean[size];
			order = new int[size];
			adjacent_list = new int[size][size];
			if(find_Edge()<0) {
				System.out.println(-1);
				continue;
			}
			visit[0] = true;
			recur(1, 0, 0);
			if(ANS==Integer.MAX_VALUE) System.out.println(-1);
			else System.out.println(ANS);
		}
	}

	private static void recur(int cnt, int cur, int cost) {
		if(ANS<cost) return;
		if(cnt==size) {
			ANS = Math.min(ANS, cost);
			return;
		}
		for (int i = 0; i < size; i++) {
			if(!visit[i]) {
				visit[i] = true;
				recur(cnt+1, i, cost+adjacent_list[cur][i]);
				visit[i] = false;
			}
		}
	}

	private static int find_Edge() {
		for (int i = 0; i < dirty_list.size(); i++) {
			int r = dirty_list.get(i).r;
			int c = dirty_list.get(i).c;
			int res = bfs(robot.r, robot.c, r, c);
			if(res<0) return -1;
			adjacent_list[0][i+1] = res;
			adjacent_list[i+1][0] = res;
		}
		for (int i = 0; i < dirty_list.size(); i++) {
			int sr = dirty_list.get(i).r;
			int sc = dirty_list.get(i).c;
			for (int j = i + 1; j < dirty_list.size(); j++) {
				int er = dirty_list.get(j).r;
				int ec = dirty_list.get(j).c;
				int res = bfs(sr, sc, er, ec);
				if(res>0) {
					adjacent_list[j+1][i+1] = res;
					adjacent_list[i+1][j+1] = res;
				}
			}
		}
		return 0;
	}

	private static int bfs(int sr, int sc, int er, int ec) {
		Deque<Pos> q = new ArrayDeque<>();
		int[][] visited = new int[H][W];
		q.offer(new Pos(sr, sc));
		visited[sr][sc] = 0;
		while(!q.isEmpty()) {
			Pos p = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if(isIn(nr, nc) && visited[nr][nc]==0) {
					if(room[nr][nc]>=0) { // 빈칸
						visited[nr][nc] = visited[p.r][p.c] + 1;
						q.offer(new Pos(nr, nc));
						if(nr==er && nc==ec) return visited[nr][nc];
					}
				}
			}
		}
		return -1;
	}
	private static boolean isIn(int nr, int nc) {
		return nr<H && nc<W && nr>=0 && nc>=0;
	}

}