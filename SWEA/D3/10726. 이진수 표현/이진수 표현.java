import java.util.*;
import java.io.*;

public class Solution {
	static StringTokenizer st;
	static int N, M;
	static StringBuffer sb = new StringBuffer();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int checkbit = (1<<N)-1;
			M &= checkbit;
			if(M==checkbit) sb.append("#"+test_case+" ON\n");
			else sb.append("#"+test_case+" OFF\n");
		}
		System.out.println(sb);
	}
}