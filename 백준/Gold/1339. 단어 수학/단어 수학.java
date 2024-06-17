import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] visited = new int[26];
	static PriorityQueue<Pair> pq = new PriorityQueue<>((p1, p2) -> Integer.compare(p2.val, p1.val));
	static class Pair {
		int val, alphabet;
		public Pair(int val, int alphabet) {
			this.val = val;
			this.alphabet = alphabet;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Arrays.fill(visited, 0);
		
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < input.length(); j++) {
				char c = input.charAt(j);
				visited[c-'A'] += Math.pow(10, input.length()-j-1);
			}
		}
		for (int i = 0; i < 26; i++) pq.offer(new Pair(visited[i], i));
		int ans = 0;
		for (int i = 9; i >= 0; i--) {
			if(!pq.isEmpty()) {
				Pair p = pq.poll();
				ans += p.val*i;
			}
		}
		System.out.println(ans);
	}
}