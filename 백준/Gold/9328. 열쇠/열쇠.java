import java.io.*;
import java.util.*;

public class Main {
	static StringTokenizer st;
	static int T, N, M;
	static char[][] map;
	static boolean[] key;
	static boolean[][] visited;
	static class Pos {
		int r, c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int[] dr = {0, -1, 0, 1}, dc = {-1, 0, 1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for (int test_case = 0; test_case < T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			visited = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < M; j++) map[i][j] = s.charAt(j);
			}
			key = new boolean[26];
			String s = br.readLine();
			if (s.charAt(0)!='0')
				for (int i = 0; i < s.length(); i++)
					key[s.charAt(i)-'a'] = true;
			
			bfs();
		}
	}

	private static void bfs() {
		int ans = 0;
		Deque<Pos> q = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			if(map[i][0]!='*') {
				q.offer(new Pos(i, 0));
				visited[i][0] = true;
			}
			if(map[i][M-1]!='*') {
				q.offer(new Pos(i, M-1));
				visited[i][M-1] = true;
			}
		}
		for (int j = 1; j < M-1; j++) {
			if(map[0][j]!='*') {
				q.offer(new Pos(0, j));
				visited[0][j] = true;
			}
			if(map[N-1][j]!='*') {
				 q.offer(new Pos(N-1, j));
				visited[N-1][j] = true;
			}
		}
		
		while(!q.isEmpty()) {
			Pos p = q.poll();
			int r = p.r, c = p.c;
			if(map[r][c]>='A' && map[r][c]<='Z') { // 문
				// 열쇠 없는 칸이면 못들어감 -> 해당 칸에서 4방 탐색 불가능
				if(!key[map[r][c]-'A']) continue;
			} else if(map[r][c]>='a' && map[r][c]<='z') { // 열쇠
				if(!key[map[r][c]-'a']) {
					key[map[r][c]-'a'] = true;
					// 키 없어서 못들어갔던 곳 들어가보자.
					for (int i = 0; i < N; i++)
						for (int j = 0; j < M; j++)
							if(map[i][j]==map[r][c]-'a'+'A' && visited[i][j]) q.offer(new Pos(i, j));
				}
			} else if(map[r][c]=='$') ans++;
			// 다음 칸 확인하기. 열쇠, 돈, 빈칸, 열쇠있는 문이면 다음 칸 확인.
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if(isIn(nr, nc) && !visited[nr][nc]) {
					if(map[nr][nc]!='*') {
						visited[nr][nc] = true;
						q.offer(new Pos(nr, nc));
					}
				}
			}
		}
		System.out.println(ans);
	}

	private static boolean isIn(int nr, int nc) {
		return nr>=0 && nc>=0 && nr<N && nc<M;
	}
}