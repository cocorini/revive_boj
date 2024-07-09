import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] A;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }

    private static void solution() {
        Arrays.sort(A);
        int left = 0, right = 1;
        int ans = Integer.MAX_VALUE;
        while(left<=right && right<N){
            if(A[right]-A[left]>=M){
                ans = Math.min(ans, A[right]-A[left]);
                left++;
            }
            else if(A[right]-A[left]<M) right++;
        }
        System.out.println(ans);
    }

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken())+1000000000;
        }
    }
}