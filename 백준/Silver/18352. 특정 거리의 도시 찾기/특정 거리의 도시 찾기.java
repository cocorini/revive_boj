import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K, X;
	static StringTokenizer st;
	static List<Integer>[] list;
	static Deque<Integer> q = new ArrayDeque<>();
	static int[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		list = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) list[i] = new ArrayList<>();
		visited = new int[N+1];
		Arrays.fill(visited, 100000000);
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken());
			list[s].add(e);
		}
		
		q.add(X);
		visited[X] = 0;
		while(!q.isEmpty()) {
			int cur = q.poll();
			for (int i = 0; i < list[cur].size(); i++) {
				int next = list[cur].get(i);
				if(visited[next] > visited[cur]+1) {
					visited[next] = visited[cur]+1;
					q.offer(next);
				}
			}
		}
		
		boolean flag = false;
		for (int i = 1; i <= N; i++) {
			if(visited[i]==K) {
				flag = true;
				System.out.println(i);
			}
		}
		if(!flag) System.out.println(-1);
	}
}