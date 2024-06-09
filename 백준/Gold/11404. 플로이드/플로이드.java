import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static final int MAX = 100000000;
	static int[][] visited;
	static PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> Integer.compare(e1.w, e2.w));
	static class Edge {
		int s, e, w;
		public Edge(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
	}
	
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
			pq.offer(new Edge(a, b, c));
		}
		while(!pq.isEmpty()) {
			Edge E = pq.poll();
			int s = E.s, e = E.e, w = E.w;
			if(visited[s][e] >= w) {
				visited[s][e] = w;
				for (int node = 1; node <= N; node++) {
					if(visited[s][node] > visited[s][e]+visited[e][node]) {
						visited[s][node] = visited[s][e]+visited[e][node];
						pq.offer(new Edge(s, node, visited[s][node]));
					}
					if(visited[node][e] > visited[node][s]+visited[s][e]) {
						visited[node][e] = visited[node][s]+visited[s][e];
						pq.offer(new Edge(node, e, visited[node][e]));
					}
				}
			}
		}
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