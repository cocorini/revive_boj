import java.io.*;
import java.util.*;

public class Main {
	static int A, B;
	static Set<Long> table;
	static class Pair {
		long x;
		int cnt;
		public Pair(long x, int cnt) {
			this.x = x;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		table = new TreeSet<>();
		
		Deque<Pair> q = new ArrayDeque<>();
		q.offer(new Pair((long)A, 0));
		while(!q.isEmpty()) {
			Pair p = q.poll();
			long x = p.x;
			if(x==B) {
				System.out.println(p.cnt+1);
				return;
			}
			if(2*x<=(long) B && !table.contains(2*x)) {
				table.add(2*x);
				q.offer(new Pair(2*x, p.cnt+1));
			}
			if(x*10+1<=(long) B && !table.contains(x*10+1)) {
				table.add(x*10+1);
				q.offer(new Pair(x*10+1, p.cnt+1));
			}
		}
		System.out.println(-1);
	}
}