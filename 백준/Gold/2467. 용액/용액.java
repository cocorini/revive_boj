import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, lnum, rnum;
	static int ANS = Integer.MAX_VALUE;
	static int[] nums;
	
	public static void main(String[] args) throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());

		// for문
		for (int i = 0; i < N; i++) {
			// -1한 값을 binarysearch(long findNum)의 파라미터로 넣기
			binarySearch(i);
		}
		
		System.out.println(lnum+" "+rnum);

	}

	private static void binarySearch(int idx) {
		
		int left = idx+1, right = N-1;
		int targetNum = -nums[idx];
		int num = nums[idx];
		
		while(left<=right) {
			int mid = (left+right)/2;
			if(ANS > Math.abs(num+nums[mid])) {
				ANS = Math.abs(num+nums[mid]);
				lnum = num; rnum = nums[mid];
			}
			
			// 이분 탐색
			if(targetNum<nums[mid]) {
				right = mid-1;
			} else if(targetNum>nums[mid]){
				left = mid+1;
			} else {
				break;
			}
			
		}
	}

}