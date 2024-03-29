import java.io.*;
import java.util.*;

public class Main {
	static StringTokenizer st;
	static int N;
	static int[] dr = {0, -1, 0, 1}, dc = {1, 0, -1, 0};
	static int[][] input;
	static boolean[][] grid = new boolean[301][301];
	static List<Integer>[][] gen = new ArrayList[4][11];
	static class Pos {
		int r, c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		init();
		init_gentable();
		for (int idx = 0; idx < N; idx++) {
			int r = input[idx][1]+150, c = input[idx][0]+150, d = input[idx][2], g = input[idx][3];
			for (int i = 0; i < gen[d][g].size(); i++) {
				grid[r][c] = true;
				int dir = gen[d][g].get(i);
				r+=dr[dir];
				c+=dc[dir];
			}
			grid[r][c] = true;
		}
		System.out.println(find_square());
	}

	private static int find_square() {
		int cnt = 0;
		for (int i = 0; i <= 299; i++) {
			for (int j = 0; j <= 299; j++) {
				if(grid[i][j] && grid[i+1][j] && grid[i][j+1] && grid[i+1][j+1]) cnt++;
			}
		}
		return cnt;
	}

	private static void init_gentable() {
		gen[0][0].add(0);
		gen[1][0].add(1);
		gen[2][0].add(2);
		gen[3][0].add(3);
		
		for (int i = 0; i < 4; i++) {
			for (int j = 1; j <= 10; j++) {
				for (int k = 0; k < gen[i][j-1].size(); k++)
					gen[i][j].add(gen[i][j-1].get(k));
				
				for (int k =  gen[i][j-1].size()-1; k >= 0; k--) {
					if(gen[i][j-1].get(k)==0) gen[i][j].add(1);
					else if(gen[i][j-1].get(k)==1) gen[i][j].add(2);
					else if(gen[i][j-1].get(k)==2) gen[i][j].add(3);
					else if(gen[i][j-1].get(k)==3) gen[i][j].add(0);
				}
			}
		}
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		input = new int[N][4];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++)
				input[i][j] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 11; j++)
				gen[i][j] = new ArrayList<>();
	}

}