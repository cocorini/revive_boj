import java.io.*;
import java.util.*;

public class Main {
	static StringTokenizer st;
	static int[][] sudoku = new int[9][9];
	static int[] rowbit = new int[9], colbit = new int[9], boxbit = new int[9];
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
			if (((rowbit[r]&(1<<i))==0) && ((colbit[c]&(1<<i))==0) && ((boxbit[r/3*3+c/3]&(1<<i))==0)) {
				rowbit[r] |= (1<<i);
				colbit[c] |= (1<<i);
				boxbit[r/3*3+c/3] |= (1<<i);
				sudoku[r][c] = i;
				recur(r, c+1);
				sudoku[r][c] = 0;
				rowbit[r] &= ~(1<<i);
				colbit[c] &= ~(1<<i);
				boxbit[r/3*3+c/3] &= ~(1<<i);
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
					rowbit[i] |= (1<<sudoku[i][j]);
					colbit[j] |= (1<<sudoku[i][j]);
					boxbit[i/3*3+j/3] |= (1<<sudoku[i][j]);
				}
			}
		}
	}
}