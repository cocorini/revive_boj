import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
    static int N, X, ANS;
    static StringTokenizer st;
    static int[][] map;
    static boolean[] ramp;
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); X = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            ANS = 0;
             
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            // 행 별로 검사
            for (int i = 0; i < N; i++) {
                if(search_row(i)) ANS++;
                 
            }
            // 열 별로 검사
            for (int j = 0; j < N; j++) {
                if(search_col(j)) ANS++;
            }
            System.out.println("#"+test_case+" "+ANS);
        }
    }
 
    private static boolean search_col(int j) {
        ramp = new boolean[N];
        int same = 1;
        for (int i = 1; i < N; i++) {
            int cur = map[i][j];
            int prev = map[i-1][j];
            if(Math.abs(cur-prev) > 1) return false;
            if(cur==prev) {
                same++;
            } else if(cur>prev) {
                if(same >= X) {
                    for (int k = i-1; k >= i-X; k--) {
                        if(ramp[k]) return false;
                    }
                }else return false;
                same = 1;
            } else {
                for (int k = i+1; k < i+X; k++) {
                    if(k >= N) return false;
                    if(map[k][j]!=map[i][j]) return false;
                    ramp[k] = true;
                }
                i += X-1;
                same = 1;
            }
        }
        return true;
    }
 
    private static boolean search_row(int i) {
        ramp = new boolean[N];
        int same = 1;
        for (int j = 1; j < N; j++) {
            int cur = map[i][j];
            int prev = map[i][j-1];
            if(Math.abs(cur-prev) > 1) return false;
            if(cur==prev) {
                same++;
            } else if(cur>prev) {
                if(same >= X) {
                    for (int k = j-1; k >= j-X; k--) {
                        if(ramp[k]) return false;
                    }
                }else return false;
                same = 1;
            } else {
                for (int k = j+1; k < j+X; k++) {
                    if(k >= N) return false;
                    if(map[i][k]!=map[i][j]) return false;
                    ramp[k] = true;
                }
                j += X-1;
                same = 1;
            }
        }
        return true;
    }
}