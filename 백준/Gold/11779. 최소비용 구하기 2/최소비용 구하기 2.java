import java.io.*;
import java.util.*;

public class Main {
	static StringTokenizer st;
	static int N, M, S, E;
	static class Edge {
		int e, cost;
		public Edge(int e, int cost) {
			this.e = e;
			this.cost = cost;
		}
	}
	static class Edge2 extends Edge{
		int[] arr;
		public Edge2(int e, int cost, int[] arr) {
			super(e, cost);
			this.arr = new int[arr.length+1];
			for (int i = 0; i < arr.length; i++)
				this.arr[i] = arr[i];
			this.arr[this.arr.length-1] = e;
		}
	}
	static List<Edge>[] Node;
	static int[] visited;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		Node = new ArrayList[N+1];
		visited = new int[N+1];
		for (int i = 1; i <= N; i++) visited[i] = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) Node[i] = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			int s, e, cost;
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			Node[s].add(new Edge(e, cost));
		}
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		dijkstra(S);	
	}

	private static void dijkstra(int start) {
		PriorityQueue<Edge2> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
		pq.offer(new Edge2(start, 0, new int[0]));
		visited[start] = 0;
		while(!pq.isEmpty()) {
			Edge2 cur = pq.poll();
			if(visited[cur.e] < cur.cost) continue;
			if(cur.e==E) {
				sb.append(visited[E]+"\n");
				sb.append(cur.arr.length+"\n");
				for (int i = 0; i < cur.arr.length; i++) sb.append(cur.arr[i]+" ");
				System.out.println(sb);
				return;
			}
			for (int n = 0; n < Node[cur.e].size(); n++) {
				Edge next = Node[cur.e].get(n);
				if(visited[next.e] > cur.cost+next.cost) {
					visited[next.e] = cur.cost+next.cost;
					pq.offer(new Edge2(next.e, visited[next.e], cur.arr));
				}
			}
		}
		
	}
}