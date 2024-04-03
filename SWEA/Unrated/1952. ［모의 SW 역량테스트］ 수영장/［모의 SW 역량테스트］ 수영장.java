import java.io.*;
import java.util.*;

public class Solution {
	static StringTokenizer st;
	static int N, ANS;
	static int[] cost, plan;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			cost = new int[4];
			for (int i = 0; i < 4; i++) cost[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			plan = new int[12];
			for (int i = 0; i < 12; i++) plan[i] = Integer.parseInt(st.nextToken());
			ANS = Integer.MAX_VALUE;
			recur(0, 0);
			System.out.println("#"+test_case+" "+ANS);
		}
	}

	private static void recur(int month, int ans) {
		if(month==12) {
			ANS = Math.min(ANS, ans);
			return;
		}
		if(plan[month]==0) recur(month+1, ans);
		
		// 1일
		if(month+1 <= 12) recur(month+1, ans+plan[month]*cost[0]);
		
		// 1달
		if(month+1 <= 12) recur(month+1, ans+cost[1]);
		
		// 3달
		if(month+3 <= 12) recur(month+3, ans+cost[2]);
		
		// 1년
		if(month+12 <= 12) recur(12, ans+cost[3]);
	}

}