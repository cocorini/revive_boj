import java.io.*;
import java.util.*;

public class Main {

	static int R, C, ANS = 1;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static String[] board;
	static int[] dr = {-1, 0, 1, 0},  dc = {0, -1, 0, 1};
	
	static class Info {
		int r, c;
		boolean[] alphabets;
		
		public Info(int r, int c, boolean[] alphabets) {
			this.r = r;
			this.c = c;
			this.alphabets = alphabets.clone();
		}
	}
	
	public static void main(String[] args) throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new String[R];
		
		for (int i = 0; i < R; i++)
			board[i] = br.readLine();
		
		boolean[] visited = new boolean[26];
		visited[board[0].charAt(0)-'A'] = true;
		
		backtracking(1, new Info(0, 0, visited));
		
		System.out.println(ANS);
	}
	
	private static void backtracking(int cnt, Info info) {
		// 넘을 수가 없음
		if(cnt>26) return;
		
		int r = info.r, c = info.c;
		boolean flag = false;
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(isIn(nr, nc) && !info.alphabets[board[nr].charAt(nc)-'A']) {
				Info newInfo = new Info(nr, nc, info.alphabets);
				newInfo.alphabets[board[nr].charAt(nc)-'A'] = true;
				backtracking(cnt+1, newInfo);
				flag = true;
			}
		}
		
		if(!flag) ANS = Math.max(ANS, cnt);
	}	

	private static boolean isIn(int r, int c) {
		return r<R && c<C && r>=0 && c>=0;
	}

}