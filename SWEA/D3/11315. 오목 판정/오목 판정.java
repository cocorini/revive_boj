import java.io.*;
import java.util.*;

public class Solution {
	static StringTokenizer st;
	static BufferedReader br;
	static int N, T;
	static int[][] board;
	static int[] dr = {0, -1, -1, -1}, dc = {1, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		init();
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < N; j++) {
					if(s.charAt(j)=='o') board[i][j] = 1;
				}
			}
			System.out.println("#"+test_case+" "+omok());
		}

	}

	private static String omok() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(board[i][j] == 1) {
					a: for (int d = 0; d < 4; d++) {
						int nr = i + dr[d];
						int nc = j + dc[d];
						for (int k = 0; k < 4; k++) {
							if (!(isIn(nr, nc) && board[nr][nc]==1)) continue a;
							nr += dr[d];
							nc += dc[d];
						}
						return "YES";
					}
				}
			}
		}
		return "NO";
	}

	private static boolean isIn(int nr, int nc) {
		return nr>=0 && nc>=0 && nr<N && nc<N;
	}

	private static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
	}
}