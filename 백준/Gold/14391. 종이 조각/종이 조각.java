import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, M;
	static int[][] paper;
	static boolean[][] visited;
	static int ANS = 0;
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		paper = new int[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++)
				paper[i][j] = input.charAt(j)-'0';
		}
		
		paperPiece();

	}

	private static void paperPiece() {
		cutPaper(0, 0);
		System.out.println(ANS);
	}

	private static void cutPaper(int r, int c) {
		if(r==N) {
			ANS = Math.max(ANS, sumPaper());
			return;
		}
		
		if(c==M) {
			cutPaper(r+1, 0);
			return;
		}
		
		visited[r][c] = true;
		cutPaper(r, c+1);
		visited[r][c] = false;
		cutPaper(r, c+1);
	}

	private static int sumPaper() {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(visited[i][j]) {
					int tmp = 0;
					while(j < M) {
						if(!visited[i][j]) break;
						tmp *= 10;
						tmp += paper[i][j];
						j++;
					}
					sum += tmp;
				}
			}
		}
		
		for (int j = 0; j < M; j++) {
			for (int i = 0; i < N; i++) {
				if(!visited[i][j]) {
					int tmp = 0;
					while(i < N) {
						if(visited[i][j]) break;
						tmp *= 10;
						tmp += paper[i][j];
						i++;
					}
					sum += tmp;
				}
			}
		}
		
		return sum;
	}

}