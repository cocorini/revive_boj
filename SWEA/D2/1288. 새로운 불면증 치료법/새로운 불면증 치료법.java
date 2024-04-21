import java.util.*;
import java.io.*;

public class Solution {
	StringTokenizer st;
	static int N, visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			visited = 0;
			counting_sheep(test_case);
		}
	}
	private static void counting_sheep(int t) {
		int tmpN = N;
		while(visited != (1<<10)-1) {
			int n = 1;
			while(n<=tmpN) {
				int num = (tmpN/n)%10;
				visited |= (1<<num);
				n*=10;
			}
			tmpN += N;
		}
		System.out.println("#"+t+" "+(tmpN-N));
	}
}