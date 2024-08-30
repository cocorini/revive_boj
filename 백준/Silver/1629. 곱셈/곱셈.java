import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int A, B, C;
	static Map<Integer, Long> map = new HashMap<Integer, Long>();
	
	public static void main(String[] args) throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		System.out.println(divideConquer(B));
	}
	private static long divideConquer(int b) {
		if(b==1)
			return A%C;
		
		// b가 짝수면?
		if(b%2==0) {
			// 맵에 없을 경우
			if(map.getOrDefault(b/2, -1L) < 0) {
				long n = divideConquer(b/2);
				map.put(b/2, n);
			}
			return (map.get(b/2)%C*map.get(b/2)%C)%C;
		}
		else {
			// 맵에 없을 경우
			if(map.getOrDefault(b/2, -1L) < 0) {
				long n = divideConquer(b/2);
				map.put(b/2, n);
			}
			if(map.getOrDefault(b/2+1, -1L) < 0) {
				long n = divideConquer(b/2+1);
				map.put(b/2+1, n);
			}
			return (map.get(b/2)%C*map.get(b/2+1)%C)%C;
		}
	}
}