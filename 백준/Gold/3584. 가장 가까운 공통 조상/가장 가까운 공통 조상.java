import java.io.*;
import java.util.*;

public class Main {
	static StringTokenizer st;
	static int N, ans;
	static int[][] tree;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			N = Integer.parseInt(br.readLine());
			tree = new int[N+1][1];
			visited = new boolean[N+1];
			ans = -1;
			for (int i = 1; i <= N; i++) tree[i][0] = -1;
			for (int i = 0; i < N-1; i++) {
				st = new StringTokenizer(br.readLine());
				int p = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
				tree[c][0] = p;
			}
			
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken()), B = Integer.parseInt(st.nextToken());
			check_parent(A);
			find_common_parent(B);
			System.out.println(ans);
		}
	}

	private static void find_common_parent(int node) {
		if(visited[node]) {
			ans = node;
			return;
		}
		find_common_parent(tree[node][0]);
	}

	private static void check_parent(int node) {
		visited[node] = true;
		if(tree[node][0] < 0) return;
		check_parent(tree[node][0]);
	}
}