import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, M, Q;
	static int[] visited;
	static StringBuilder ans = new StringBuilder();
	
	static List<Integer>[] list;

	public static void main(String[] args) throws IOException{
		input();
		
		st = new StringTokenizer(br.readLine());
		Q = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < Q; i++) {

			visited = new int[N+1];
			for (int j = 1; j <= N; j++) visited[j] = 2100000000;
			visited[1] = 0;
			
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			list[b].add(a);
			
			findDist();
			
		}
		
		System.out.println(ans);
	}

	private static void findDist() {
		
		bfs();
		
		StringBuilder sb = new StringBuilder();
		sb.append("0");
		
		for (int i = 2; i <= N; i++) {
			if(visited[i]==2100000000) sb.append(" -1");
			else
				sb.append(" "+visited[i]);
		}
		
		ans.append(sb+"\n");
	}

	private static void bfs() {
		Deque<Integer> q = new ArrayDeque<>();
		q.add(1);
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for (int i = 0; i < list[cur].size(); i++) {
				int next = list[cur].get(i);
				if(visited[cur]+1<visited[next]) {
					visited[next] = visited[cur]+1;
					q.offer(next);
				}
			}
		}
	}

	private static void input() throws IOException{
		// TODO n, m, 도로 리스트, q, 주어지는 query 입력받기
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) list[i] = new ArrayList<>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
	}
}