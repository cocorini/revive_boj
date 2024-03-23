import java.io.*;
import java.util.*;

public class Main {
	static StringTokenizer st;
	static int N;
	static int[] liquid;
	static class Pair {
		int l1, l2;
		public Pair(int l1, int l2) {
			this.l1 = l1;
			this.l2 = l2;
		}
	}
	static Pair ANS = new Pair(0, 0);
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		liquid = new int[N];
		for (int i = 0; i < N; i++) liquid[i] = Integer.parseInt(st.nextToken());
		
		find();
	}
	private static void find() {
		Arrays.sort(liquid);
		int left = 0, right = N-1, SUM = Integer.MAX_VALUE;
		while(left<right) {
			int sum = liquid[left]+liquid[right];
			if(sum>0) {
				if(Math.abs(SUM) > Math.abs(sum)) {
					SUM = sum;
					ANS.l1 = liquid[left];
					ANS.l2 = liquid[right];
				}
				right--;
			} else if(sum<0) {
				if(Math.abs(SUM) > Math.abs(sum)) {
					SUM = sum;
					ANS.l1 = liquid[left];
					ANS.l2 = liquid[right];
				}
				left++;
			} else {
				ANS.l1 = liquid[left];
				ANS.l2 = liquid[right];
				break;
			}
		}
		System.out.println(ANS.l1+" "+ANS.l2);
	}
}