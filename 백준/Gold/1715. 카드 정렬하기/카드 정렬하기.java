import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static long ans = 0;
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws IOException{
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) 
			pq.offer(Integer.parseInt(br.readLine()));
		
		while(!pq.isEmpty()) {
			if(pq.size()==1) {
				System.out.println(ans);
				return;
			}
			int n = pq.poll();
			int m = pq.poll();
			
			ans += n+m;
			pq.offer(n+m);
		}
	}
}
