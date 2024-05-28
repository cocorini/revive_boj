import java.io.*;
import java.util.*;

public class Main {
	static int N, table[][][];
	static List<Integer> commands = new ArrayList<>();
	static int power[][] = {{1,2,2,2,2}, {2,1,3,4,3}, {2,3,1,3,4}, {2,4,3,1,3}, {2,3,4,3,1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int input = Integer.parseInt(st.nextToken());
		while(input != 0) {
			commands.add(input);
			input = Integer.parseInt(st.nextToken());
		}
		if(commands.size()==0) System.out.println(0);
		else {
			N = commands.size();
			table = new int[N][5][5];
			for (int i = 0; i < N; i++) 
				for (int j = 0; j < 5; j++) 
					for (int k = 0; k < 5; k++) 
						table[i][j][k] = Integer.MAX_VALUE;
			playDDR();
		}
	}

	private static void playDDR() {
		table[0][0][commands.get(0)] = 2;
		table[0][commands.get(0)][0] = 2;
		
		for (int i = 1; i < N; i++) {
			int command = commands.get(i);
			for (int j = 0; j < 5; j++) {
				for (int k = 0; k < 5; k++) {
					// 행은 왼발, 열은 오른발
					if(table[i-1][j][k]!=Integer.MAX_VALUE) {
						// 두 발 같은곳에 놓을 수 없음
						if(command!=k) table[i][command][k] = Math.min(table[i-1][j][k]+power[command][j], table[i][command][k]); // 왼발이 움직였어.
						if(command!=j) table[i][j][command] = Math.min(table[i-1][j][k]+power[command][k], table[i][j][command]);// 오른발이 움직였어.
					}
				}
			}
		}
		int ANS = Integer.MAX_VALUE;
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++)
				ANS = Math.min(ANS, table[N-1][i][j]);
		System.out.println(ANS);
	}
}