import java.io.*;
import java.util.*;

public class Main {
	static int R, C, cnt = 0;
	static boolean isFind;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		isFind = false;
		findVisitCnt((int) Math.pow(2, N), 0, 0);
		System.out.println(cnt);
	}

	private static void findVisitCnt(int N, int r, int c) {
		if(N==2) {
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					if(r+i==R && c+j==C) {
						isFind = true;
						return;
					}
					cnt++;
				}
			}
			return;
		}
		
		if(!isFind && R<r+N/2 && C<c+N/2) {
			findVisitCnt(N/2, r, c);
		}
		else if(!isFind && R<r+N/2 && C>=c+N/2) {
			cnt += N/2*N/2;
			findVisitCnt(N/2, r, c+N/2);
		}
		else if(!isFind && R>=r+N/2 && C<c+N/2) {
			cnt += N/2*N/2*2;
			findVisitCnt(N/2, r+N/2, c);
		}
		else if(!isFind && R>=r+N/2 && C>=c+N/2) {
			cnt += N/2*N/2*3;
			findVisitCnt(N/2, r+N/2, c+N/2);
		}
	}
}