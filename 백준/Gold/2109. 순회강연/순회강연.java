import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static PriorityQueue<Pair> lecturePlanQueue = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.p, o1.p));
	static boolean[] visited = new boolean[10001];
	static class Pair {
		int p, d;
		public Pair(int p, int d) {
			this.p = p;
			this.d = d;
		}
	}
	static BufferedReader br;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int ansPrice = makeLecturePlan();
		System.out.println(ansPrice);
	}

	private static int makeLecturePlan() throws IOException {
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			lecturePlanQueue.add(new Pair(p, d));
		}
		int maxPrice = 0;
		
		while(!lecturePlanQueue.isEmpty()) {
			Pair pair = lecturePlanQueue.poll();
			if(!visited[pair.d]) {
				visited[pair.d] = true;
				maxPrice += pair.p;
			}
			else {
				for (int i = pair.d-1; i >= 1; i--) {
					if(visited[i]) continue;
					visited[i] = true;
					maxPrice += pair.p;
					break;
				}
			}
		}
		return maxPrice;
	}
}