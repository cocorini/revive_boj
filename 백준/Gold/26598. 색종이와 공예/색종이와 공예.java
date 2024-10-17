import java.io.*;
import java.util.*;

public class Main {
	/*
	 *	백만
	 *	visited 처리를 하고
	 *	이중 for문으로 탐색하는데,
	 *	그냥 위에 행을 쭉 탐색한다음에 거기서 무조건 한붓으로 색칠하는거임
	 *	그렇게 했을 때 알파벳이 다르면?
	 *	BaboBabo
	 * */
	
	static class Pos {
		int r, c;
		public Pos (int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int N, M;
	static String[] input;
	
	static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
		
		input = new String[N];
		
		for (int i = 0; i < N; i++)
			input[i] = br.readLine();
		
		boolean[][] visited = new boolean[N][M];
		
		ArrayDeque<Pos> q = new ArrayDeque<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(!visited[i][j]) {
					int li = i, lj = j, ri = i, rj = j;
					
					visited[i][j] = true;
					q.offer(new Pos(i, j));
					while(!q.isEmpty()) {
						Pos p = q.poll();
						for (int d = 0; d < 4; d++) {
							int nr = p.r + dr[d];
							int nc = p.c + dc[d];
							
							if(isIn(nr, nc) && !visited[nr][nc] && input[p.r].charAt(p.c)==input[nr].charAt(nc)) {
								visited[nr][nc] = true;
								li = Math.min(li, nr);
								lj = Math.min(lj, nc);
								ri = Math.max(nr, ri);
								rj = Math.max(nc, rj);
								q.offer(new Pos(nr, nc));
							}
						}
					}
					char c = input[i].charAt(j);
					for (int k = li; k <= ri; k++) {
						for (int k2 = lj; k2 <= rj; k2++) {
							if(c!=input[k].charAt(k2)) {
								System.out.println("BaboBabo");
								return;
							}
						}
					}
				}
			}
		}
		
		System.out.println("dd");

	}

	private static boolean isIn(int nr, int nc) {
		return nr<N && nc<M && nr>=0 && nc>=0;
	}

}