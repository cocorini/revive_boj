import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
  
public class Solution {
    static int N, K, MAX, ANS;
    static StringTokenizer st;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};
      
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); K = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            MAX = 0; ANS = 0;
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    MAX = Math.max(MAX, map[i][j]);
                }
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(map[i][j]==MAX) {
                        visited[i][j] = true;
                        recur(i, j, 1, false);
                        visited[i][j] = false;
                    }
                }
            }
            System.out.println("#"+test_case+" "+ANS);
        }
    }
  
    private static void recur(int r, int c, int len, boolean fix) {
        ANS = Math.max(ANS, len);
        // 낮은 지형으로만 이동 가능
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(isIn(nr, nc) && !visited[nr][nc]) {
                if(map[nr][nc] < map[r][c]) {
                    visited[nr][nc] = true;
                    recur(nr, nc, len+1, fix);
                    visited[nr][nc] = false;
                }else if(!fix) { // 공사 하실?
                    for (int k = 1; k <= K; k++) {
                        if(map[nr][nc]-k < map[r][c]) {
                            visited[nr][nc] = true;
                            map[nr][nc] -= k;
                            recur(nr, nc, len+1, true);
                            map[nr][nc] += k;
                            visited[nr][nc] = false;
                        }
                    }
                }
            }
        }
    }
  
    private static boolean isIn(int nr, int nc) {
        return nr<N && nc<N && nr>=0 && nc>=0;
    }
}