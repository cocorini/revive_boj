import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static int N, R, Q;
	static List<Integer>[] tree;
	static int[] child_cnt;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		child_cnt = new int[N+1];
		Arrays.fill(child_cnt, 1);
		visited = new boolean[N+1];
		tree = new ArrayList[N+1];
		for (int i = 0; i <= N; i++) tree[i] = new ArrayList<>();
		
		for (int i = 0; i < N-1; i++) {
			int u, v;
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			tree[u].add(v);
			tree[v].add(u);
		}
		
		visited[R] = true;
		recur(R);
		
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int q = Integer.parseInt(st.nextToken());
			System.out.println(child_cnt[q]);
		}
	}

	private static void recur(int node) {
		for (int i = 0; i < tree[node].size(); i++) {
			int child = tree[node].get(i);
			if(!visited[child]) {
				visited[child] = true;
				recur(child);
				child_cnt[node] += child_cnt[child];
			}
		}
		// 리프 노드에 다다른다면?
		if(tree[node].size()==1) return;
	}
}