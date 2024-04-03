import java.io.*;
import java.util.*;

public class Main {
	static StringTokenizer st;
	static int N, M;
	static boolean[] visited;
	static List<Integer>[] child, parent;
	
	public static void main(String[] args) throws IOException {
		init();
		int ans = 0;
		for (int i = 1; i <= N; i++) {
			visited = new boolean[N+1];
			int up = recur_child(i);
			int down = recur_parent(i);
			if(up+down-1==N) ans++;
		}
		System.out.println(ans);
	}

	private static int recur_parent(int cur) {
		int cnt = 1;
		visited[cur] = true;
		for (int next : parent[cur]) {
			if(!visited[next]) cnt += recur_parent(next);
		}
		return cnt;
	}

	private static int recur_child(int cur) {
		int cnt = 1;
		visited[cur] = true;
		for (int next : child[cur]) {
			if(!visited[next]) cnt += recur_child(next);
		}
		return cnt;
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 방향 그래프
		child = new ArrayList[N+1];
		parent = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) child[i] = new ArrayList<>();
		for (int i = 1; i <= N; i++) parent[i] = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
			child[b].add(a);
			parent[a].add(b);
		}
	}
}