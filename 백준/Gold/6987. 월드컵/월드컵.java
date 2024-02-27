import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int[][] res, game;
	private static boolean check;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int t = 0; t < 4; t++) {
			res = new int[6][3];
			game = new int[15][2];
			check = false;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 18; c++)
				res[c/3][c%3] = Integer.parseInt(st.nextToken());
			
			for (int i = 0, k = 0; i < 6; i++) {
				for (int j = i + 1; j < 6; j++) {
					game[k][0] = i;
					game[k++][1] = j;
				}
			}
			
			combi(0);
			if(check) System.out.print("1 ");
			else System.out.print("0 ");
		}
	}

	private static void combi(int cnt) {
		if(cnt==15) {
			for (int i = 0; i < 6; i++)
				for (int j = 0; j < 3; j++)
					if(res[i][j] > 0) return;
			
			check = true;
			return;
		}
		
		int A = game[cnt][0], B = game[cnt][1];
		if(res[A][0]>0 && res[B][2]>0) {
			res[A][0]--;
			res[B][2]--;
			combi(cnt+1);
			if(check) return;
			res[A][0]++;
			res[B][2]++;
		}
		if(res[A][1]>0 && res[B][1]>0) {
			res[A][1]--;
			res[B][1]--;
			combi(cnt+1);
			if(check) return;
			res[A][1]++;
			res[B][1]++;
		}
		if(res[A][2]>0 && res[B][0]>0) {
			res[A][2]--;
			res[B][0]--;
			combi(cnt+1);
			if(check) return;
			res[A][2]++;
			res[B][0]++;
		}
	}
}