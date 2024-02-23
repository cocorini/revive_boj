import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static StringTokenizer st;
	static class Edge implements Comparable<Edge>{
		int cost, e;
		public Edge(int cost, int e) {
			this.cost = cost;
			this.e = e;
		}
		@Override
		public int compareTo(Edge E) {
			return Integer.compare(cost, E.cost);
		}
	}
	static PriorityQueue<Edge> pq;
	static List<Edge>[] list;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int origin_sum;
		while(true) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); N = Integer.parseInt(st.nextToken());
			if(M==0 && N==0) break;
			origin_sum = 0;
			list = new ArrayList[M];
			for (int i = 0; i < M; i++) list[i] = new ArrayList<>();
			visited = new boolean[M];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int z = Integer.parseInt(st.nextToken());
				list[x].add(new Edge(z, y));
				list[y].add(new Edge(z, x));
				origin_sum += z;
			}
			System.out.println(origin_sum-Prim());
		}
	}

	private static int Prim() {
		pq = new PriorityQueue<>();
		pq.offer(new Edge(0, 0));
		
		int ans = 0;
		while(!pq.isEmpty()) {
			Edge E = pq.poll();
			int cur = E.e;
			if(visited[cur]) continue; // 이미 방문했던 값
			visited[cur] = true;
			ans += E.cost;
			for (int i = 0; i < list[cur].size(); i++) {
				int next = list[cur].get(i).e;
				int ncost = list[cur].get(i).cost;
				if(!visited[next]) pq.offer(new Edge(ncost, next));
			}
		}
		return ans;
	}
}
