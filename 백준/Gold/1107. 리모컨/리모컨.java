import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br;
	static int N, M, ANS = Integer.MAX_VALUE, lenN;
	static boolean[] isNeedFixedNums = new boolean[10];

	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		input();
		
		// 오직 위 아래 버튼만 눌렀을 경우
		ANS = Math.abs(N-100);
		
		// 위, 아래, 숫자 버튼을 모두 누른 경우
		// 0~500000 중에서 되는 수는 -> N하고 몇칸 차이 나냐?
		a: for (int num = 0; num <= 999999; num++) {
			String numStr = ""+num;
//			System.out.print(numStr);
			for (int i = 0; i < numStr.length(); i++) {
				// 고장난 버튼이면
				if(isNeedFixedNums[numStr.charAt(i)-'0']) {
					continue a;
				}
			}
//			System.out.println(" : "+Math.abs(N-num) + numStr.length());
			// 현재 숫자와 N과의 차이 즉, 방향키만 누른거에 + 숫자 자릿수 => N이 되기 위해 누른 버튼 수
			ANS = Math.min(ANS, Math.abs(N-num)+numStr.length());
		}
		
		System.out.println(ANS);
	}

	private static void input() throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		lenN = (""+N).length();
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		
		if(M==0) return;
			
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++)
			isNeedFixedNums[Integer.parseInt(st.nextToken())] = true;
		
	}

}