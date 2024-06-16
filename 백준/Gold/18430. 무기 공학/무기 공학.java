import java.io.*;
import java.util.*;

public class Main {
	static int N, M, ANS = 0;
	static int[][] treeMaterial, dr = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}, dc = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		treeMaterial = new int[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				treeMaterial[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		makeBoomerang(0, 0, 0);
		System.out.println(ANS);
	}

	private static void makeBoomerang(int r, int c, int sum) {
		if(c==M) {
			makeBoomerang(r+1, 0, sum);
			return;
		} else if(r==N) {
			// 합 계산
			ANS = Math.max(ANS, sum);
			return;
		}
		if(visited[r][c]) {
			makeBoomerang(r, c+1, sum);
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			int nr1 = r + dr[d][0];
			int nc1 = c + dc[d][0];
			int nr2 = r + dr[d][1];
			int nc2 = c + dc[d][1];
			if(isIn(nr1, nc1, nr2, nc2)) {
				if(!visited[nr1][nc1] && !visited[nr2][nc2]) {
					visited[nr1][nc1] = visited[nr2][nc2] = visited[r][c] = true;
					int tmpSum = treeMaterial[r][c]*2+treeMaterial[nr1][nc1]+treeMaterial[nr2][nc2];
					makeBoomerang(r, c+1, sum+tmpSum);
					visited[nr1][nc1] = visited[nr2][nc2] = visited[r][c] = false;
				}
			}
		}
		
		makeBoomerang(r, c+1, sum);
	}

	private static boolean isIn(int nr1, int nc1, int nr2, int nc2) {
		return nr1>=0 && nr1<N && nc1>=0 && nc1<M && nr2>=0 && nr2<N && nc2>=0 && nc2<M;
	}
}