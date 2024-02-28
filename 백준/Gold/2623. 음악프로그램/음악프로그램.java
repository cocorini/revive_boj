import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static StringTokenizer st;
	static int[] visit;
	static List<Integer>[] list;
	static List<Integer> ans = new ArrayList<>();
	static boolean[] visited;
	static Deque<Integer> q = new ArrayDeque<>();
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
        visit = new int[N+1];
        visited = new boolean[N+1];
        list = new ArrayList[N+1];
        for (int i = 1; i <= N; i++)
			list[i] = new ArrayList<>();
        
        for (int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int n = Integer.parseInt(st.nextToken()), prev = -1;
        	for (int j = 0; j < n; j++) {
				int m = Integer.parseInt(st.nextToken());
				if(j > 0) {
					visit[m]++;
					list[prev].add(m);
					prev = m;
				}else prev = m;
			}
		}
        
        for (int i = 1; i <= N; i++)
			if(visit[i]==0) {
				visited[i] = true;
				q.offer(i);
			}
        
        if(topology_sort()) {
        	for (int i = 0; i < ans.size(); i++) System.out.println(ans.get(i));
        }else System.out.println(0);
    }

	private static boolean topology_sort() {
        while(!q.isEmpty()) {
        	int cur = q.poll();
        	ans.add(cur);
        	for (int i = 0; i < list[cur].size(); i++) {
        		int next = list[cur].get(i);
				if(visit[next]>0) visit[next]--;
				if(!visited[next] && visit[next]==0) {
					visited[next] = true;
					q.offer(next);
				}
			}	
        }
        for (int i = 1; i <= N; i++) {
			if(visit[i]!=0) return false;
		}
		return true;
	}
}