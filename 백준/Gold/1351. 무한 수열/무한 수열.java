import java.io.*;
import java.util.*;

public class Main {
	static long N;
	static int P, Q;
	static Map<Long, Long> map = new HashMap<>();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Long.parseLong(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		map.put(0L, 1L);
		System.out.println(DivideConquer(N));
	}

	private static long DivideConquer(long cnt) {
		if(cnt==0) return 1;
		long L, R;
		if(map.getOrDefault(cnt/P, -1L) < 0) {
			L = DivideConquer(cnt/P);
			map.put(cnt/P, L);
		}
		else L = map.get(cnt/P);
		if(map.getOrDefault(cnt/Q, -1L) < 0) {
			R = DivideConquer(cnt/Q);
			map.put(cnt/Q, R);
		}
		else R = map.get(cnt/Q);
		
		return L+R;
	}

}