import java.io.*;
import java.util.*;

public class Main {
	static int N, M, ANS = Integer.MAX_VALUE;
	static int[][] lab;
	static class Pos {
		int r, c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static List<Pos> virus_list;
	static int[] virus_sellist, dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		lab = new int[N][N];
		virus_list = new ArrayList<>();
		virus_sellist = new int[M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				lab[i][j] = Integer.parseInt(st.nextToken());
				if(lab[i][j]==2) virus_list.add(new Pos(i, j));
			}
		}
		
		recur(0, 0);
		if(ANS==Integer.MAX_VALUE) ANS = 0;
		System.out.println(ANS-1);
	}

	private static void recur(int idx, int cnt) {
		if(cnt==M) {
			ANS = Math.min(ANS, spreadVirus());
			return;
		}
		for (int i = idx; i < virus_list.size(); i++) {
			virus_sellist[cnt] = i;
			recur(i+1, cnt+1);
		}
	}

	private static int spreadVirus() {
		Deque<Pos> q = new ArrayDeque<>();
		for (int i = 0; i < M; i++)
			q.offer(virus_list.get(virus_sellist[i]));
		
		int[][] tmplab = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				tmplab[i][j] = lab[i][j];
				if(lab[i][j]==2) {
					tmplab[i][j] = 0;
					for (int k = 0; k < M; k++) {
						int r = virus_list.get(virus_sellist[k]).r;
						int c = virus_list.get(virus_sellist[k]).c;
						if(i==r && j==c) tmplab[i][j] = -1;
					}
				}
			}
		}
		
		while(!q.isEmpty()) {
			Pos p = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = p.r+dr[d];
				int nc = p.c+dc[d];
				if(isIn(nr, nc) && tmplab[nr][nc]==0) {
					tmplab[nr][nc] = tmplab[p.r][p.c] - 1;
					q.offer(new Pos(nr, nc));
				}
			}
		}
		
		int max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(tmplab[i][j]==0) return Integer.MAX_VALUE;
				max = Math.max(max, -tmplab[i][j]);
			}
		}
		return max;
	}

	private static boolean isIn(int nr, int nc) {
		return nr<N && nc<N && nr>=0 && nc>=0;
	}
}