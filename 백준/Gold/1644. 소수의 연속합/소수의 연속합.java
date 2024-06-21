import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static List<Integer> sosuArr = new ArrayList<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		if(N==1) {
			System.out.println(0);
			return;
		}
        
		makeSosuArr();
		int ANS = findContinuSum();
		System.out.println(ANS);
	}

	private static int findContinuSum() {
		int l = 0, r = 0;
		int cnt = 0;
		int sum = sosuArr.get(l);
		while(r < sosuArr.size()) {
			if(sum<N) {
				r++;
				if(r < sosuArr.size()) sum += sosuArr.get(r);
			} else if(sum==N) {
				cnt++;
				sum -= sosuArr.get(l++);
			} else if(sum>N) {
				sum -= sosuArr.get(l++);
			}
		}
		return cnt;
	}

	private static void makeSosuArr() {
		loopA: for (int i = 2; i <= N; i++) {
			for (int j = 2; j*j <= i; j++) {
				if(i%j==0) continue loopA;
			}
			sosuArr.add(i);
		}
	}
}