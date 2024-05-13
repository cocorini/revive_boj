import java.io.*;
import java.util.*;

public class Main {
	static int N, M, lab[][], dr[] = {-1, 0, 1, 0}, dc[] = {0, -1, 0, 1}, ANS = Integer.MAX_VALUE;
	static class Pos {
		int r, c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static List<Pos> virus_pos = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		lab = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				lab[i][j] = Integer.parseInt(st.nextToken());
				if(lab[i][j]==1) lab[i][j] = -1;
				else if(lab[i][j]==2) {
					virus_pos.add(new Pos(i, j));
					lab[i][j] = -2; // virus 상태 모두 빈칸으로 바꾸기
				}
			}
		}
		recur(-1, 0);
		if(ANS==Integer.MAX_VALUE) ANS = -1;
		System.out.println(ANS);
	}

	private static void recur(int idx, int cnt) {
		if(cnt==M) {
			ANS = Math.min(ANS, spreadVirus());
			return;
		}
		
		for (int i = idx + 1; i < virus_pos.size(); i++) {
			lab[virus_pos.get(i).r][virus_pos.get(i).c] = 1;
			recur(i, cnt+1);
			lab[virus_pos.get(i).r][virus_pos.get(i).c] = -2;
		}
	}

	private static int spreadVirus() {
		Deque<Pos> q = new ArrayDeque<>();
		int max_time = 0;
		int[][] tmp_lab = new int[N][N];
		for (int i = 0; i < N; i++) 
			for (int j = 0; j < N; j++) {
				tmp_lab[i][j] = lab[i][j];
				if(tmp_lab[i][j]==1) q.offer(new Pos(i, j));
			}
		
		while(!q.isEmpty()) {
			Pos p = q.poll();
			if(max_time>=ANS) return Integer.MAX_VALUE-1;
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if(isIn(nr, nc)) {
					if(tmp_lab[nr][nc]==0) {
						max_time = Math.max(max_time, tmp_lab[p.r][p.c]);
						tmp_lab[nr][nc] = tmp_lab[p.r][p.c] + 1;
						q.offer(new Pos(nr, nc));
					} else if(tmp_lab[nr][nc]==-2) {
						tmp_lab[nr][nc] = tmp_lab[p.r][p.c] + 1;
						q.offer(new Pos(nr, nc));
					}
				}
			}
		}
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if(tmp_lab[i][j]==0) return Integer.MAX_VALUE;
		
		return max_time;
	}

	private static boolean isIn(int nr, int nc) {
		return nr<N && nr>=0 && nc<N && nc>=0;
	}
}