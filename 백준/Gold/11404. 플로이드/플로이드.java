import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static final int MAX = 100000000;
	static int[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		visited = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(visited[i], MAX);
			visited[i][i] = 0;
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if(visited[a][b] > c) visited[a][b] = c;
		}
		
		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= N; j++)
				for (int k = 1; k <= N; k++)
					visited[j][k] = Math.min(visited[j][i]+visited[i][k], visited[j][k]);
		
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(visited[i][j]==MAX) visited[i][j] = 0;
				sb.append(visited[i][j]+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}