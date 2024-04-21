import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K, ANS = Integer.MAX_VALUE;
	static StringTokenizer st;
	static int[][] arr, rcs, tmp_arr;
	static int[] order;
	static boolean[] visited;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken()); K = Integer.parseInt(st.nextToken());
        arr = new int[N+1][M+1];
        tmp_arr = new int[N+1][M+1];
        rcs = new int[K][3];
        visited = new boolean[K];
        order = new int[K];
        for (int i = 1; i <= N; i++) {
        	st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				tmp_arr[i][j] = arr[i][j];
			}
		}
        for (int i = 0; i < K; i++) {
        	st = new StringTokenizer(br.readLine());
        	rcs[i][0] = Integer.parseInt(st.nextToken());
        	rcs[i][1] = Integer.parseInt(st.nextToken());
        	rcs[i][2] = Integer.parseInt(st.nextToken());
        }
        
        // 순열로 연산 순서 나열
        recur(0);
        System.out.println(ANS);
    }

	private static void recur(int cnt) {
		if(cnt==K) {
			// 회전
			for (int i = 0; i < K; i++) {
				rotate(order[i]);
			}
			// 최솟값 갱신하기
			for (int i = 1; i <= N; i++) {
				int sum = 0;
				for (int j = 1; j <= M; j++) {
					sum += arr[i][j];
				}
				ANS = Math.min(ANS, sum);
			}
			// 원상 복귀
			for (int i = 1; i <= N; i++) arr[i] = tmp_arr[i].clone();
			return;
		}
		for (int i = 0; i < K; i++) {
			if(!visited[i]) {
				visited[i] = true;
				order[cnt] = i;
				recur(cnt+1);
				visited[i] = false;
			}
		}
	}

	private static void rotate(int idx) {
		int r = rcs[idx][0], c = rcs[idx][1], s = rcs[idx][2];
		int r1 = r-s, c1 = c-s, r2 = r+s, c2 = c+s;
		recur2(r1, c1, r2, c2);
	}

	private static void recur2(int r1, int c1, int r2, int c2) {
		// 종료 조건
		if(r1>=r2 | c1>=c2) return;
		
		int end = arr[r2][c2];
		// 시계 방향
		for (int i = r2; i > r1; i--) {
			arr[i][c2] = arr[i-1][c2];
		}
		for (int j = c2 ; j > c1; j--) {
			arr[r1][j] = arr[r1][j-1];
		}
		for (int i = r1 ; i < r2; i++) {
			arr[i][c1] = arr[i+1][c1];
		}
		for (int j = c1; j < c2; j++) {
			arr[r2][j] = arr[r2][j+1];
		}
		arr[r2][c2-1] = end;
		recur2(r1+1, c1+1, r2-1, c2-1);
	}
}