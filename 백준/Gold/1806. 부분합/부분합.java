import java.io.*;
import java.util.*;

public class Main {
	static int N, S, nums[], sums[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		nums = new int[N];
		sums = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			if(i > 0) sums[i] = nums[i]+sums[i-1];
			else sums[i] = nums[i];
		}
		prefixSum();
	}

	private static void prefixSum() {
		int left = 0, right = 0, ANS = Integer.MAX_VALUE;
		while(left<=right && right<N) {
			int val = sums[right]-sums[left]+nums[left];
			if(val<S) {
				right++;
			} else {
				ANS = Math.min(ANS, right-left+1);
				left++;
			}
			if(ANS==1) {
				System.out.println(1);
				return;
			}
		}
		if(ANS==Integer.MAX_VALUE) System.out.println(0);
		else System.out.println(ANS);
	}
}