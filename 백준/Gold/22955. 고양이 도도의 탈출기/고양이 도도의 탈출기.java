import java.io.*;
import java.util.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static StringTokenizer st;
    static final int MAX = 50000000;
    static int N, M;
    static int[][] room;
    static int[] dr = { 0, 0 }, dc = { -1, 1 };

    static class Info extends Pos {
        int cost;

        public Info(int r, int c, int cost) {
            super(r, c);
            this.cost = cost;
        }
    }

    static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static Pos cat, exit;
    static int[][] visited;

    public static void main(String[] args) throws IOException {
        Init();
        bfs();
    }

    private static void bfs() {
        PriorityQueue<Info> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        pq.offer(new Info(cat.r, cat.c, 0));
        visited[cat.r][cat.c] = 0;
        // 현재칸 중심으로 짜기
        while (!pq.isEmpty()) {
            Info p = pq.poll();
            int r = p.r, c = p.c;
            if (room[r][c] < 0) {
                continue;
            } else if (room[r][c] == 0) {
                if (r == exit.r && c == exit.c) {
                    System.out.println(p.cost);
                    return;
                }
                int nr = r + 1;
                int nc = c;
                // 밑에 칸이 사다리일 때
                if (isIn(nr, nc) && room[nr][nc] == 5 && visited[nr][nc] > p.cost + 5) {
                    visited[nr][nc] = p.cost + 5;
                    pq.offer(new Info(nr, nc, p.cost + 5));
                }
            } else if (room[r][c] == 5) {
                int nr = r - 1;
                int nc = c;
                if (isIn(nr, nc) && (room[nr][nc] == 0 || room[nr][nc] == 5) && visited[nr][nc] > p.cost + 5) {
                    visited[nr][nc] = p.cost + 5;
                    pq.offer(new Info(nr, nc, p.cost + 5));
                }
                nr = r + 1;
                if (isIn(nr, nc) && room[nr][nc] == 5 && visited[nr][nc] > p.cost + 5) {
                    visited[nr][nc] = p.cost + 5;
                    pq.offer(new Info(nr, nc, p.cost + 5));
                }
            } else if (room[r][c] == 10) {
                for (int i = r + 1; i < N; i++) {
                    int val = room[i][c];
                    if (val < 10) {
                        if (visited[i][c] > p.cost + 10) {
                            visited[i][c] = p.cost + 10;
                            pq.offer(new Info(i, c, p.cost + 10));
                        }
                        break;
                    }
                }
                continue;
            }

            // 다음으로 갈 수 있는 칸 찾아보기
            for (int d = 0; d < 2; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (isIn(nr, nc) && room[nr][nc] >= 0 && visited[nr][nc] > p.cost + 1) {
                    visited[nr][nc] = p.cost + 1;
                    pq.offer(new Info(nr, nc, p.cost + 1));
                }
            }
        }
        System.out.println("dodo sad");
    }

    private static boolean isIn(int nr, int nc) {
        return nr >= 0 && nc >= 0 && nr < N && nc < M;
    }

    private static void Init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        room = new int[N][M];
        visited = new int[N][M];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                visited[i][j] = MAX;
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = s.charAt(j);
                if (c == 'C') {
                    cat = new Pos(i, j);
                } else if (c == 'D') {
                    room[i][j] = -1;
                } else if (c == 'E') {
                    exit = new Pos(i, j);
                } else if (c == 'L') {
                    room[i][j] = 5;
                } else if (c == 'X') {
                    room[i][j] = 10;
                }
            }
        }
    }
}