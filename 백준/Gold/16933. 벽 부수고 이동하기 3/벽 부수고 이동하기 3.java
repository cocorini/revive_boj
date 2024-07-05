import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[] dr = {0, -1, 0, 1}, dc = {-1, 0, 1, 0};
    static int[][] inputMap;
    static boolean[][][] visited;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static class Node {
        int r, c, k, cnt;
        boolean isDay;
        public Node(int r, int c, int k, int cnt, boolean isDay) {
            this.r = r;
            this.c = c;
            this.k = k;
            this.cnt = cnt;
            this.isDay = isDay;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        if(N==1 && M==1) {
            System.out.println(1);
            return;
        }
        int ANS = bfs();
        System.out.println(ANS);
    }

    private static int bfs() {
        PriorityQueue<Node> q = new PriorityQueue<>(Comparator.comparingInt(e -> e.cnt));
        q.offer(new Node(0, 0, 0, 1, true));
        for (int i=0;i<=K;i++) visited[0][0][i] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if(isIn(nr, nc)){
                    // 다음칸이 도착 칸
                    if(nr == N-1 && nc == M-1) {
                        return cur.cnt+1;
                    }
                    // 그냥 땅
                    if(inputMap[nr][nc] == 0){
                        if(!visited[nr][nc][cur.k]){
                            visited[nr][nc][cur.k] = true;
                            q.offer(new Node(nr, nc, cur.k, cur.cnt+1, !cur.isDay));
                        }
                    }
                    // 벽
                    else {
                        // 지금 부셔도 됨
                        if(cur.k<K){
                            if(!visited[nr][nc][cur.k+1]){
                                visited[nr][nc][cur.k+1] = true;
                                // 낮 -> 벽 부수기 가능
                                if(cur.isDay){
                                    q.offer(new Node(nr, nc, cur.k+1, cur.cnt+1, !cur.isDay));
                                }
                                // 밤 -> 바로는 못 부수고 다음 번에 부순다..
                                else {
                                    q.offer(new Node(nr, nc, cur.k+1, cur.cnt+2, cur.isDay));
                                }
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }

    private static boolean isIn(int nr, int nc) {
        return nr >= 0 && nr < N && nc >= 0 && nc < M;
    }

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        inputMap = new int[N][M];
        visited = new boolean[N][M][K+1];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++)
                inputMap[i][j] = input.charAt(j) - '0';
        }
    }
}