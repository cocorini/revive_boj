import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static StringTokenizer st;
	public static int N, C;
	public static int[] house;
	public static boolean[] visited;
	public static int L, R, ANS = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		house = new int[N];
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			house[i] = Integer.parseInt(s);
		}
		Arrays.sort(house);
		
		install();
	}

	private static void install() {
		int left = 1, right = house[N-1]-house[0]+1, tmpC, mid, start;
		
		while(left <= right) {
			tmpC = 1;
			mid = (left+right)/2;
			start = house[0];
			
			// 집 간 거리가 mid 이상이면 공유기 설치
			for (int i = 1; i < N; i++) {
				if(house[i]-start >= mid) {
					tmpC++;
					start = house[i];
				}
			}
			
			// 공유기 설치 개수가 C보다 많은 경우? 간격을 늘려서 개수를 줄여
			if(tmpC >= C) {
				left = mid+1;
			} else {
				right = mid-1;
			}
		}
		System.out.println(left-1);
	}
}