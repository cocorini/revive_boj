import java.io.*;
import java.util.*;

public class Main {
	static int N, arr[];
	static List<Integer> table = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		table.add(arr[0]);
		
		findLIS();
	}

	private static void findLIS() {
		int ans = 1;
		for (int i = 1; i < N; i++) {
			if(arr[i]>table.get(ans-1)) {
				table.add(arr[i]);
				ans++;
			} else {
				// 이분탐색
				int x = Collections.binarySearch(table, arr[i]);
				if(x < 0) table.set(-x-1, arr[i]);
				else table.set(x, arr[i]);
			}
		}
		System.out.println(ans);
	}
}