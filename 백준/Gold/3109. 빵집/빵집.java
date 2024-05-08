import java.io.*;
import java.util.*;

public class Main {
	static int R, C, Ridx = 0, Cidx = 0;
	static boolean colendflag;
	static char[][] store;
	static int[] dr = {-1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		store = new char[R][C];
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				store[i][j] = s.charAt(j);
				if(j==0) store[i][j] = '1';
			}
		}
	
		int ANS = 0;
		for (int i = 0; i < R; i++) {
			colendflag = false;
			dfs(i, 0);
		}
		for (int i = 0; i < R; i++) {
			if(store[i][C-1]=='1') ANS++;
		}
		System.out.println(ANS);
	}

	private static void dfs(int r, int c) {
		if(c==C-1) {
			colendflag = true;
			return;
		}
		
		if(store[r][c]=='1') {
			for (int d = 0; d < 3; d++) {
				int nr = r + dr[d];
				int nc = c + 1;
				if(isIn(nr, nc) && store[nr][nc]=='.') {
					store[nr][nc]='1';
					dfs(nr, nc);
					if(colendflag) return;
				}
			}
		}
	}

	private static boolean isIn(int nr, int nc) {
		return nr<R && nc<C && nr>=0 && nc>=0;
	}
}