import java.io.*;
import java.util.*;
  
public class Solution {
    static StringTokenizer st;
    static int N, B, ANS;
    static int[] H;
      
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            H = new int[N];
            for (int i = 0; i < N; i++) H[i] = Integer.parseInt(st.nextToken());
            ANS = Integer.MAX_VALUE;
            recur(0, 0);
            System.out.println("#"+test_case+" "+ANS);
        }
    }
  
    private static void recur(int cnt, int sum) {
        if(cnt==N) {
            if(sum>=B) ANS = Math.min(ANS, sum-B);
            return;
        }
        recur(cnt+1, sum);
        recur(cnt+1, sum+H[cnt]);
    }
}