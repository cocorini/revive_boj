import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] milk_city;
	static StringTokenizer st;
	static Pair[][] table;
	static class Pair {
		int cnt, milk_type;
		public Pair(int cnt, int milk_type) {
			this.cnt = cnt;
			this.milk_type = milk_type;
		}
	}
	static int[] next_type = {1, 2, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		milk_city = new int[N][N];
		table = new Pair[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				milk_city[i][j] = Integer.parseInt(st.nextToken());
				table[i][j] = new Pair(0, 0);
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(milk_city[i][j]==0 && table[i][j].cnt==0) {
					table[i][j].cnt = 1;
					table[i][j].milk_type = 0;
					drink_milk(i, j);
//					for (int k = 0; k < N; k++) {
//						for (int l = 0; l < N; l++) {
//							System.out.print(table[k][l].cnt+" ");
//						}
//						System.out.println();
//					}
//					System.out.println();
				}
			}
		}
		
		System.out.println(table[N-1][N-1].cnt);
	}

	private static void drink_milk(int R, int C) {
		for (int i = R+1; i < N; i++) {
			if(table[i][C].cnt>=table[i-1][C].cnt+1) break;
			if(milk_city[i][C]==next_type[table[i-1][C].milk_type]) {
				table[i][C] = new Pair(table[i-1][C].cnt+1, milk_city[i][C]);
			} else table[i][C] = table[i-1][C];
		}
		for (int j = C+1; j < N; j++) {
			if(table[R][j].cnt>=table[R][j-1].cnt+1) break;
			if(milk_city[R][j]==next_type[table[R][j-1].milk_type]) {
				table[R][j] = new Pair(table[R][j-1].cnt+1, milk_city[R][j]);
			} else table[R][j] = table[R][j-1];
		}
		for (int i = R+1; i < N; i++) {
			for (int j = C+1; j < N; j++) {
				int cur_type = milk_city[i][j];
				int up_ntype = next_type[table[i-1][j].milk_type], up_ncnt = table[i-1][j].cnt;
				int left_ntype = next_type[table[i][j-1].milk_type], left_ncnt = table[i][j-1].cnt;
				if(up_ntype==cur_type) up_ncnt++;
				if(left_ntype==cur_type) left_ncnt++;
				if(up_ncnt>left_ncnt) {
					if(up_ntype==cur_type) table[i][j] = new Pair(up_ncnt, cur_type);
					else table[i][j] = new Pair(up_ncnt, table[i-1][j].milk_type);
				}else {
					if(left_ntype==cur_type) table[i][j] = new Pair(left_ncnt, cur_type);
					else table[i][j] = new Pair(left_ncnt, table[i][j-1].milk_type);
				}
			}
		}
	}
}