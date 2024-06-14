import java.io.*;
import java.util.*;

public class Main {
	static int N, M, X;
	static int[][] visited;
	static List<Edge>[] list;
	static class Edge {
		int node, cost;
		public Edge(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}
	}
	static PriorityQueue<Edge> pq;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		visited = new int[N+1][N+1];
		list = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
			for (int j = 1; j <= N; j++) {
				visited[i][j] = Integer.MAX_VALUE;
				if(i==j) visited[i][j]=0;
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			list[s].add(new Edge(e, w));
		}
		
		for (int start = 1; start <= N; start++) {
			pq = new PriorityQueue<>((e1, e2)->Integer.compare(e1.cost, e2.cost));
			pq.offer(new Edge(start, 0));
			while(!pq.isEmpty()) {
				Edge E = pq.poll();
				for (int i = 0; i < list[E.node].size(); i++) {
					int next_node = list[E.node].get(i).node;
					int next_cost = E.cost + list[E.node].get(i).cost;
					if(visited[start][next_node] > next_cost) {
						visited[start][next_node] = next_cost;
						pq.offer(new Edge(next_node, next_cost));
					}	
				}
			}
		}
		int ANS = 0;
		for (int i = 1; i <= N; i++)
			ANS = Math.max(ANS, visited[X][i]+visited[i][X]);
		System.out.println(ANS);
	}
}