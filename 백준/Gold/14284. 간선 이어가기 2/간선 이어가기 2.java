import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, s, t, ANS = Integer.MAX_VALUE;
	static StringTokenizer st;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static List<Edge>[] list;
	static class Edge implements Comparable<Edge>{
		int e, cost;
		public Edge(int e, int cost){
			this.e = e;
			this.cost = cost;
		}
		@Override
		public int compareTo(Edge E) {
			return Integer.compare(cost, E.cost);
		}
	}
	static int[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList[N+1];
		for (int i = 1; i <= N; i++)
			list[i] = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list[a].add(new Edge(b, c));
			list[b].add(new Edge(a, c));
		}
		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		
		visited = new int[N+1];
		Arrays.fill(visited, Integer.MAX_VALUE);
		
		dijkstra();
	}
	private static void dijkstra() {
		visited[s] = 0;
		pq.offer(new Edge(s, 0));
		
		while(!pq.isEmpty()) {
			Edge E = pq.poll();
			int cur = E.e;
			for (int i = 0; i < list[cur].size(); i++) {
				int next = list[cur].get(i).e;
				int ncost = visited[cur] + list[cur].get(i).cost;
				if(visited[next] > ncost) {
					visited[next] = ncost;
					pq.offer(new Edge(next, ncost));
				}
			}
		}
		System.out.println(visited[t]);
	}
}