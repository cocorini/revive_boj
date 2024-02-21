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
    static int N, M, T, W;
    static StringTokenizer st;
    static int[] D, total;
    static int[] need_cnt;
    static List<Integer>[] list;
    static Deque<Integer> q = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        for (int t = 1; t <= T; t++) {
        	st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());  M = Integer.parseInt(st.nextToken());
            list = new ArrayList[N+1];
            for (int i = 1; i <= N; i++) list[i] = new ArrayList<>();
            need_cnt = new int[N+1];
            D = new int[N+1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) D[i] = Integer.parseInt(st.nextToken());
            total = Arrays.copyOf(D, D.length);
            for (int i = 0; i < M; i++) {
            	st = new StringTokenizer(br.readLine());
            	int prev = Integer.parseInt(st.nextToken());
            	int next = Integer.parseInt(st.nextToken());
            	list[prev].add(next);
            	need_cnt[next]++;
			}
            W = Integer.parseInt(br.readLine());
            topological_sort();
            System.out.println(total[W]);
        }
    }

	private static void topological_sort() {
		for (int i = 1; i <= N; i++)
			if(need_cnt[i]==0) q.offer(i);
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			for (int i = 0; i < list[cur].size(); i++) {
				int next = list[cur].get(i);
				need_cnt[next]--;
				if(need_cnt[next]==0) q.offer(next);
				total[next] = Math.max(total[next], total[cur]+D[next]);
			}
		}
	}
}