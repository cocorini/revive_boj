import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K;
	static boolean[][] notebook;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		notebook = new boolean[N][M];
		K = Integer.parseInt(st.nextToken());
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken()), C = Integer.parseInt(st.nextToken());
			int[][] sticker = new int[R][C];
			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < C; j++) sticker[i][j] = Integer.parseInt(st.nextToken());
			}
			stickToNote(R, C, sticker);
		}
		int ANS = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++) 
				if(notebook[i][j]) ANS++;
		System.out.println(ANS);
	}

	private static void stickToNote(int R, int C, int[][] sticker) {
		// 0, 90, 180, 270 도 회전 - rotate 이용
		for (int rotate = 0; rotate < 4; rotate++) {
			int[][] n_sticker = rotateSticker(sticker, R, C, rotate);
			int r = n_sticker.length;
			int c = n_sticker[0].length;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					int cnt = 0;
					for (int k = 0; k < r; k++) {
						for (int l = 0; l < c; l++) {
							if(isIn(i+k, j+l)) {
								if(n_sticker[k][l]==1 && !notebook[i+k][j+l]) cnt++;
								else if(n_sticker[k][l]==0) cnt++;
							}							
						}
					}
					if(cnt==r*c) {
						for (int k = 0; k < r; k++)
							for (int l = 0; l < c; l++)
								if(n_sticker[k][l]==1) notebook[i+k][j+l] = true;
						return;
					}
				}
			}
		}
		
	}

	private static boolean isIn(int i, int j) {
		return i<N && j<M && i>=0 && j>=0;
	}

	private static int[][] rotateSticker(int[][] sticker, int R, int C, int rotate) {
		if(rotate==0) return sticker;
		else if(rotate==1) {
			int[][] n_sticker = new int[C][R];
			for (int i = 0; i < R; i++)
				for (int j = 0; j < C; j++)
					n_sticker[j][R-1-i] = sticker[i][j];
			return n_sticker;
		}
		else if(rotate==2) {
			int[][] n_sticker = new int[R][C];
			for (int i = 0; i < R; i++)
				for (int j = 0; j < C; j++) 
					n_sticker[R-1-i][C-1-j] = sticker[i][j];
			return n_sticker;
		}
		int[][] n_sticker = new int[C][R];
		for (int i = 0; i < R; i++)
			for (int j = 0; j < C; j++)
				n_sticker[C-1-j][i] = sticker[i][j];
		return n_sticker;
	}

}