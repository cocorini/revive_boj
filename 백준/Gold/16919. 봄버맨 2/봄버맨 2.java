import java.io.*;
import java.util.*;

public class Main {
	static int R, C, N;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};
	static int[][][] table;
	static StringBuilder[] sb = new StringBuilder[8];
	static class Pos {
		int r, c;
		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		table = new int[8][R][C];
		
		sb[1] = new StringBuilder();
		for (int i = 0; i < R; i++) {
			String input = br.readLine();
			for (int j = 0; j < C; j++) {
				char c = input.charAt(j);
				if(c=='.') table[0][i][j] = -1;
				table[1][i][j] = table[0][i][j];
			}
			sb[1].append(input+"\n");
		}
		
		for (int t = 2; t < 8; t++) {
			if(t==2) {
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						table[t][i][j] = table[t-1][i][j];
						if(table[t][i][j]<0) table[t][i][j] = t;
					}
				}
			} else if(t%2==1) {
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						if(table[t][i][j]==t-3) {
							table[t][i][j] = -1;
							for (int d = 0; d < 4; d++) {
								int ni = i + dr[d];
								int nj = j + dc[d];
								if(isIn(ni, nj)&&table[t][ni][nj]!=t-3) table[t][ni][nj] = -1;
							}
						}
					}
				}
			} else if(t%2==0) {
				for (int i = 0; i < R; i++)
					for (int j = 0; j < C; j++)
						if(table[t][i][j]<0) table[t][i][j] = t;
			}
			
			if(t<7) {
				// 다음 테이블 갱신하기
				for (int i = 0; i < R; i++)
					for (int j = 0; j < C; j++)
						table[t+1][i][j] = table[t][i][j];
			}
			
			// StringBuilder 담기
			sb[t] = new StringBuilder();
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if(table[t][i][j]<0) sb[t].append(".");
					else sb[t].append("O");
				}
				sb[t].append("\n");
			}
//			System.out.println(sb[t]);
		}
		
		if(N<5) System.out.println(sb[N]);
		else System.out.println(sb[N%4+4]);
		
	}

	private static boolean isIn(int nr, int nc) {
		return nr<R && nc<C && nr>=0 && nc>=0;
	}
}