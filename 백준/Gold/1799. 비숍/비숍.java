import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, ANS = 0;
	static StringTokenizer st;
	static int[][] board;
	static boolean[][] line;
	static boolean[] line1, line2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		line = new boolean[N][N];
		line1 = new boolean[2*N-1];
		line2 = new boolean[2*N-1];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(i%2==0 && j%2==1) {
					line[i][j] = true;
				} else if(i%2==1 && j%2==0) {
					line[i][j] = true;
				}
			}
		}
		
		recur(0, 0, 0, false);
		int tmp_ans = ANS;
		ANS = 0;
		recur(0, 0, 0, true);
		System.out.println(tmp_ans+ANS);
	}
	private static void recur(int r, int c, int cnt, boolean flag) {
		if(ANS==N-1) return;
		if(r==N) {
			// 가장 끝까지 탐색 완료
			ANS = Math.max(ANS, cnt);
			return;
		}
		if(c>=N) {
			recur(r+1, 0, cnt, flag);
			return;
		}
		if(line[r][c]!=flag) {
			recur(r, c+1, cnt, flag);
			return;
		}
		if(board[r][c]==1) {
			// 대각 체크
			if(!line1[r+c] && !line2[N-1-(r-c)]) {
				line1[r+c] = true;
				line2[N-1-(r-c)] = true;
				recur(r, c+2, cnt+1, flag);
				line1[r+c] = false;
				line2[N-1-(r-c)] = false;
			}
		} 
		recur(r, c+2, cnt, flag);
	}
}