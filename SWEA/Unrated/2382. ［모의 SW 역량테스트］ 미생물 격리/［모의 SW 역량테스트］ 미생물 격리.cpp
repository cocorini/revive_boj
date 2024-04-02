import java.io.*;
import java.util.*;

public class Solution {
	static StringTokenizer st;
	static int N, M, K;
	static int[] dr = {0, -1, 1, 0, 0}, dc = {0, 0, 0, -1, 1};
	static int[] D = {0, 2, 1, 4, 3};
	static class Info {
		int r, c, n, dir;
		public Info(int r, int c, int n, int dir) {
			this.r = r;
			this.c = c;
			this.n = n;
			this.dir = dir;
		}
	}
	static Info[] list;
	static int[][] tmp_map, tmp_idxmap;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			list = new Info[K];
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				list[i] = new Info(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			simulation(test_case);
		}
	}
	
	private static void simulation(int t) {
		for (int time = 0; time < M; time++) {
			tmp_map = new int[N][N];
			tmp_idxmap = new int[N][N];
			// 무작정 이동시키기
			for (int i = 0; i < K; i++) {
				if(list[i].n==0) continue;
				list[i].r+=dr[list[i].dir];
				list[i].c+=dc[list[i].dir];
			}
			// Edge 확인.
			for (int i = 0; i < K; i++) {
				if(list[i].n==0) continue;
				if(isEdge(list[i].r, list[i].c)) {
					list[i].n /= 2;
					list[i].dir = D[list[i].dir];
				}
			}
			// 이동 후 군집 합치기 2개 이상!!!
			for (int i = 0; i < K; i++) {
				if(list[i].n==0) continue;
				int r = list[i].r, c =list[i].c;
				tmp_map[r][c] += list[i].n;
			}
			for (int i = 0; i < K; i++) {
				if(list[i].n==0) continue;
				for (int j = i + 1; j < K; j++) {
					if(list[i].r==list[j].r&&list[i].c==list[j].c) {
						if(list[i].n>list[j].n) {
							list[j].n = 0;
						}else {
							list[i].n = 0;
							break;
						}
					}
				}
			}
			for (int i = 0; i < K; i++) {
				if(list[i].n==0) continue;
				int r = list[i].r, c =list[i].c;
				list[i].n = tmp_map[r][c];
			}
		}
		int ans = 0;
		for (int i = 0; i < K; i++) ans += list[i].n;
		System.out.println("#"+t+" "+ans);
	}

	private static boolean isEdge(int r, int c) {
		return r==N-1 || r==0 || c==N-1 || c==0;
	}
}
