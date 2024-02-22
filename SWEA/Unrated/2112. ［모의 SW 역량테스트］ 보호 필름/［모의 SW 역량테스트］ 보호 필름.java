import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int D, W, K, ANS;
	static int[][] film; 
	static StringTokenizer st; 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());		
		for (int test_case = 1; test_case <= T; test_case++) {
			// 입력 받기
			ANS = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			film = new int[D][W];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			recur(0, 0);
			
			System.out.println("#"+test_case+" "+ANS);
		}
	}
	
	// cnt가 뎁스, n이 바꾼 횟수
	private static void recur(int cnt, int n) {
		// 종료 조건 : 모든 열이 합격 기준을 통과
		if(n >= ANS) return;
		if(isCheck()) {
			ANS = n;
			return;
		}
		if(cnt == D) return;
		
		recur(cnt+1, n);
		int[] tmp = film[cnt].clone();
		Arrays.fill(film[cnt], 0);
		recur(cnt+1, n+1);
		Arrays.fill(film[cnt], 1);
		recur(cnt+1, n+1);
		film[cnt] = tmp;
	}
	
	private static boolean isCheck() {
		int cnt = 0;
		if(K==1) return true;
		for (int j = 0; j < W; j++) {
			boolean bf = false;
			int prev_flag = film[0][j];
			int cnt0 = 1, cnt1 = 1;
			for (int i = 1; i < D; i++) {
				int flag = film[i][j];
				if(flag==prev_flag) {
					if(flag==0) cnt0++;
					else cnt1++;
					if(cnt0==K || cnt1==K) {
						cnt++;
						bf = true;
					}
				}else{
					cnt0 = 1; cnt1 = 1;
				}
				prev_flag = flag;
				if(bf) break;
			}
		}
		if(cnt==W) return true;
		return false;
	}
}