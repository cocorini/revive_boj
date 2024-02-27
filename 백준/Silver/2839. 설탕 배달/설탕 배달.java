import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static int T, N;
	static int[] dp3 = new int[1700], dp5 = new int[1001];
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int ans = Integer.MAX_VALUE;
		for (int i = 1; i <= 1000; i++) dp5[i] = 5*i;
		for (int i = 1; i < 1700; i++) dp3[i] = 3*i;
		for (int i = 0; i <= 1000; i++) {
			int sum = dp5[i];
			for (int j = 0; j < 1700; j++) {
				if(dp3[j]==N-sum) {
					ans = Math.min(ans, i+j);
				}else if(dp3[j] > N-sum) break;
			}
		}
		if(ans==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);
	}
}