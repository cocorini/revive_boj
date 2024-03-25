import java.io.*;
import java.util.*;

public class Main {
	static StringTokenizer st;
	static int N;
	static PriorityQueue<Integer> lpq = new PriorityQueue<>((o1, o2)->Integer.compare(o2, o1)), rpq = new PriorityQueue<>();
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		lpq.offer(Integer.parseInt(br.readLine()));
		sb.append(lpq.peek()+"\n");
		for (int i = 1; i < N; i++) {
			int x = Integer.parseInt(br.readLine());
			if(lpq.peek()<x) rpq.offer(x);
			else lpq.offer(x);
			int lsize = lpq.size(), rsize = rpq.size();
			if(lsize<rsize)
				lpq.offer(rpq.poll());
			else if(lsize==rsize+2) 
				rpq.offer(lpq.poll());
			sb.append(lpq.peek()+"\n");
		}
		System.out.println(sb);
	}
}