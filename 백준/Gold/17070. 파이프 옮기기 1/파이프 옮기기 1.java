import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] house;
	static int[][][] ans;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		house = new int[N+1][N+1];
		ans = new int[N+1][N+1][3];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++)
				house[i][j] = Integer.parseInt(st.nextToken());
		}
		ans[1][2][0] = 1;
		for (int i = 1; i <= N; i++) {
			for (int j = 2; j <= N; j++) {
				if(house[i][j]==1) continue;
				if(house[i][j-1]==0) ans[i][j][0] += ans[i][j-1][2]+ans[i][j-1][0];
				if(house[i-1][j]==0) ans[i][j][1] += ans[i-1][j][1]+ans[i-1][j][2];
				if(house[i-1][j-1]==0 && house[i-1][j]==0 && house[i][j-1]==0) ans[i][j][2] += ans[i-1][j-1][2]+ans[i-1][j-1][0]+ans[i-1][j-1][1];
			}
		}
		
		
		System.out.println(ans[N][N][0]+ans[N][N][1]+ans[N][N][2]);
	}
}