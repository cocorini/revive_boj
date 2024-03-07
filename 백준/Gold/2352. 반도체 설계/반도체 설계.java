// binarysearch 메소드 이용해보기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] port;
	static StringTokenizer st;
	static List<Integer> LIS = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		port = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) port[i] = Integer.parseInt(st.nextToken());
		semiconductor();
	}

	private static void semiconductor() {
		LIS.add(port[0]);
		for (int i = 1; i < N; i++) {
			int idx = Collections.binarySearch(LIS, port[i]);
			if(-idx-1<LIS.size()) LIS.set(-idx-1, port[i]);
			else LIS.add(port[i]);
		}
		System.out.println(LIS.size());
	}
}