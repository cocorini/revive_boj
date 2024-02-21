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
    static int N;
    static StringTokenizer st;
    static int[] time, level, ans;
    static List<Integer>[] list;
    static Deque<Integer> q = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        time = new int[N+1];
        level = new int[N+1];
        ans = new int[N+1];
        list = new ArrayList[N+1];
        for (int i = 0; i <= N; i++) list[i] = new ArrayList<>();
        
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; ; j++) {
                int n = Integer.parseInt(st.nextToken());
                if(n==-1) break;
                if(j==0) {
                    time[i] = n;
                    ans[i] = n;
                }
                else {
                    list[n].add(i);
                    level[i]++;
                }
            }
        }
        for (int i = 1; i <= N; i++) 
        	if(level[i]==0) q.offer(i);

        while(!q.isEmpty()) {
            int from = q.poll();
            for (int i = 0; i < list[from].size(); i++) {
                int to = list[from].get(i);
//                if(level[to] < 0) continue;
                level[to]--;
                if(level[to] == 0) q.offer(to);
                ans[to] = Math.max(ans[from] + time[to], ans[to]);
            }
        }

        for (int i = 1; i <= N; i++) System.out.println(ans[i]);
    }
}