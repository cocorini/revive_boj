import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Main {
	static int N, M;
	static int[] students, arr;
	static List<Integer>[] tall;
	static Deque<Integer> q = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]); M = Integer.parseInt(s[1]);
		tall = new ArrayList[N+1];
		arr = new int[N+1];
		for (int i = 1; i <= N; i++) tall[i] = new ArrayList<>();
		
		for (int i = 0; i < M; i++) {
			int from, to;
			s = br.readLine().split(" ");
			from = Integer.parseInt(s[0]); to = Integer.parseInt(s[1]);
			tall[from].add(to);
			arr[to]++;
		}
		
		for (int i = 1; i <= N; i++)
			if(arr[i]==0) q.add(i);
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			System.out.print(cur+" ");
			for (int i = 0; i < tall[cur].size(); i++) {
				int next = tall[cur].get(i);
				if(arr[next]==0) continue;
				if(arr[next]-1 == 0) q.add(next);
				arr[next]--;
			}
		}	
	}
}