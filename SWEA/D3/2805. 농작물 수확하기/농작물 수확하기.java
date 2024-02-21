import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
	static int N;
	static int[][] farm;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			farm = new int[N][N];
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < N; j++)
					farm[i][j] = s.charAt(j)-'0';
			}
			System.out.println("#"+test_case+" "+howMuch());
		}
	}

	private static int howMuch() {
		int center = N/2;
		int sum = 0;
		for (int j = 0; j <= center; j++) {
			for (int i = j; i < N-j; i++) {
				if(j!=0) sum += farm[i][center+j]+farm[i][center-j];
				else sum += farm[i][center];
			}
		}
		return sum;
	}
}
