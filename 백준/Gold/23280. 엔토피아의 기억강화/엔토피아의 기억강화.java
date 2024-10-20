import java.io.*;
import java.util.*;

public class Main {

	static int N, A, B;
	static int[] order;
	static int[][][] dp;
	static final int MAX = 2100000000;
	static Map<Integer, Pos> map;
	
	static class Pos {
		int r, c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException{
		input();
		mapInit();
		
		/* 3차원 DP */
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= 12; j++) {
				for (int k = 1; k <= 12; k++) {
					if(dp[i-1][j][k]!=MAX) {
						int leftDist = (Math.abs(map.get(j).r-map.get(order[i]).r)+Math.abs(map.get(j).c-map.get(order[i]).c))+A;
						int rightDist = (Math.abs(map.get(k).r-map.get(order[i]).r)+Math.abs(map.get(k).c-map.get(order[i]).c))+B;
						
						// 왼손으로 움직인 경우
						dp[i][order[i]][k] = Math.min(dp[i][order[i]][k], leftDist+dp[i-1][j][k]);
						
						// 오른손으로 움직인 경우
						dp[i][j][order[i]] = Math.min(dp[i][j][order[i]], rightDist+dp[i-1][j][k]);
					}
				}
			}
		}
		
		int ans = MAX;
		
		for (int i = 1; i <= 12; i++)
			for (int j = 1; j <= 12; j++)
				ans = Math.min(ans, dp[N][i][j]);
		
		System.out.println(ans);
	}

	private static void mapInit() {
		map = new HashMap<>();
		map.put(1, new Pos(0, 0));
		map.put(2, new Pos(0, 1));
		map.put(3, new Pos(0, 2));
		map.put(4, new Pos(1, 0));
		map.put(5, new Pos(1, 1));
		map.put(6, new Pos(1, 2));
		map.put(7, new Pos(2, 0));
		map.put(8, new Pos(2, 1));
		map.put(9, new Pos(2, 2));
		map.put(10, new Pos(3, 0));
		map.put(11, new Pos(3, 1));
		map.put(12, new Pos(3, 2));
	}

	private static void input() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		dp = new int[N+1][13][13];
		for (int i = 0; i <= N; i++)
			for (int j = 0; j < 13; j++)
				for (int k = 0; k < 13; k++)
					dp[i][j][k] = MAX;
		
		// 테이블 행이 왼손, 열이 오른손
		dp[0][1][3] = 0;
		
		order = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) order[i] = Integer.parseInt(st.nextToken());
	}

}
