import java.io.*;
import java.util.*;

public class Solution {
	static StringTokenizer st;
	static int N, arr[], tmp_arr[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			arr = new int[3];
			for (int i = 0; i < 3; i++) arr[i] = Integer.parseInt(st.nextToken());
			tmp_arr = arr.clone();
			int ans = 0;
			for (int i = 1; i < 3; i++) {
				int prev = arr[i-1], cur = arr[i];
				if(prev>=cur) arr[i-1] = cur-1;
			}
			for (int i = 1; i >= 0; i--) {
				int prev = arr[i+1], cur = arr[i];
				if(prev<=cur) arr[i] = prev-1;
			}
			for (int i = 0; i < 3; i++) {
				if(arr[i]<=0) {
					ans = -1;
					break;
				}
				ans += tmp_arr[i]-arr[i];
			}
			System.out.println("#"+test_case+" "+ans);
		}
	}
}