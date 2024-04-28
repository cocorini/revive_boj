import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][][] adjwho;
	static int[][] adjcnt, seat_arr, love;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};
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
		adjwho = new int[N][N][4];
		adjcnt = new int[N][N];
		seat_arr = new int[N][N];
		love = new int[N*N+1][4];
		for (int i = 0; i < N; i++) {
			adjcnt[i][0]++;
			adjcnt[0][i]++;
			adjcnt[i][N-1]++;
			adjcnt[N-1][i]++;
		}
		
		
		for (int i = 0; i < N*N; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			for (int j = 0; j < 4; j++)
				love[n][j] = Integer.parseInt(st.nextToken());
			
			select_seat(n);
		}
		//  정답 확인
		int score = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int tmp_cnt = 0;
				for (int k = 0; k < adjcnt[i][j]; k++) {
					for (int l = 0; l < 4; l++) {
						if(adjwho[i][j][k] == love[seat_arr[i][j]][l]) tmp_cnt++;
					}
				}
				score += Math.pow(10, tmp_cnt-1);
			}
		}
		System.out.println(score);
	}

	private static void select_seat(int n) {
		Pos seat = new Pos(-1, -1);
		a: for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if(seat_arr[i][j]==0) {
					seat.r = i; seat.c = j;
					break a;
				}
		
		int love_cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int tmp_cnt = 0;
				if(seat_arr[i][j]==0) {
					// 좋아하는 학생 몇명 있어?
					for (int k = 0; k < adjcnt[i][j]; k++) {
						for (int l = 0; l < 4; l++) {
							if(adjwho[i][j][k] == love[n][l]) tmp_cnt++;
						}
					}
					if(love_cnt<tmp_cnt) {
						seat.r = i;
						seat.c = j;
						love_cnt = tmp_cnt;
					} else if(love_cnt==tmp_cnt) {
						if(4-adjcnt[seat.r][seat.c]<4-adjcnt[i][j]) {
							seat.r = i;
							seat.c = j;
						}
					}
				}
			}
		}
		for (int d = 0; d < 4; d++) {
			int nr = seat.r + dr[d];
			int nc = seat.c + dc[d];
			if(isIn(nr, nc)) {
				adjcnt[nr][nc]++;
				for (int i = 0; i < 4; i++) {
					if(adjwho[nr][nc][i]==0) {
						adjwho[nr][nc][i] = n;
						break;
					}
				}
			}
		}
		seat_arr[seat.r][seat.c] = n;	
	}
	private static boolean isIn(int nr, int nc) {
		return nr<N && nc<N && nr>=0 && nc>=0;
	}
}