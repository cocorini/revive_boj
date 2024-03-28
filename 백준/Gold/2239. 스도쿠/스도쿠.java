import java.io.*;
import java.util.*;

public class Main {
	static StringTokenizer st;
	static int[][] sudoku = new int[9][9];
	static boolean[][] row = new boolean[9][10], col = new boolean[9][10], box = new boolean[9][10];
	static boolean ans = false;

	public static void main(String[] args) throws IOException {
		init();
		recur(0, 0);
	}

	private static void recur(int r, int c) {
		if(ans) return;
		if(c==9) {
			recur(r+1, 0);
			return;
		}
		if(r==9) {
			// 완성 했는지 확인
			// 완성했네?
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(sudoku[i][j]);
				}
				System.out.println();
			}
			ans = true;
			return;
		}
		if(sudoku[r][c]!=0) {
			recur(r, c+1);
			return;
		}
		for (int i = 1; i <= 9; i++) {
			if(!row[r][i] && !col[c][i] && !box[r/3*3+c/3][i]) {
				row[r][i] = true;
				col[c][i] = true;
				box[r/3*3+c/3][i] = true;
				sudoku[r][c] = i;
				recur(r, c+1);
				sudoku[r][c] = 0;
				row[r][i] = false;
				col[c][i] = false;
				box[r/3*3+c/3][i] = false;
			}
		}
	}
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 9; i++) {
			String s = br.readLine();
			for (int j = 0; j < 9; j++) {
				sudoku[i][j] = s.charAt(j)-'0';
				if(sudoku[i][j]!=0) {
					row[i][sudoku[i][j]] = true;
					col[j][sudoku[i][j]] = true;
					box[i/3*3+j/3][sudoku[i][j]] = true;
				}
			}
		}
	}
}