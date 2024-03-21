import java.io.*;
import java.util.*;

public class Main {
    static StringTokenizer st;
    static int W, H;
    static int[][] maze;
    static int[][] visited;
    static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};
    static Deque<Pos> q = new ArrayDeque<>();
    static Pos end;
    static class Pos {
        int r, c;
        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        maze = new int[H][W];
        visited = new int[H][W];
        for (int i = 0; i < H; i++)
            for (int j = 0; j < W; j++)
                   visited[i][j] = Integer.MAX_VALUE;

        for (int i = 0; i < H; i++) {
            String s = br.readLine();
            for (int j = 0; j < W; j++) {
                if(s.charAt(j)>='0' && s.charAt(j)<='9') maze[i][j] = s.charAt(j)-'0';
                else {
                    if(s.charAt(j)=='T') {
                        maze[i][j] = 0;
                        visited[i][j] = 0;
                        q.offer(new Pos(i, j));
                    }
                    else if(s.charAt(j)=='R') maze[i][j] = -5;
                    else if(s.charAt(j)=='H') maze[i][j] = -100;
                    else if(s.charAt(j)=='E') {
                        maze[i][j] = -2;
                        end = new Pos(i, j);
                    }
                }
            }
        }

        bfs();
    }

	private static void bfs() {
		while(!q.isEmpty()) {
			Pos p = q.poll();
			for (int d = 0; d < 4; d++) {
				check(p.r, p.c, d);
			}
		}
		if(visited[end.r][end.c]==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(visited[end.r][end.c]);
	}

	private static void check(int R, int C, int d) {
		int r = R, c = C;
		// 하
		int val = visited[r][c];
		if(d==2) {
			for (int i = r+1; i < H; i++) {
				// 끝인데, 돌이 없는 경우
				if(i==H-1 && maze[i][c]>=0) break;
				// 돌이야
				if(maze[i][c]==-5) {
					if(visited[i-1][c]>val) {
						visited[i-1][c] = val;
						q.offer(new Pos(i-1, c));
					}
					break;
				}else if(maze[i][c]>=0) {
					val += maze[i][c];
				}else if(maze[i][c]==-100) {
					break;
				}else if(maze[i][c]==-2) {
					if(visited[i][c] > val) {
						visited[i][c] = val;
					}
				}
			}
		}
		// 상
		else if(d==0) {
			for (int i = r-1; i >= 0; i--) {
				// 끝인데, 돌이 없는 경우
				if(i==0 && maze[i][c]>=0) break;
				// 돌이야
				if(maze[i][c]==-5) {
					if(visited[i+1][c]>val) {
						visited[i+1][c] = val;
						q.offer(new Pos(i+1, c));
					}
					break;
				}else if(maze[i][c]>=0) {
					val += maze[i][c];
				}else if(maze[i][c]==-100) {
					break;
				}else if(maze[i][c]==-2) {
					if(visited[i][c] > val) {
						visited[i][c] = val;
					}
				}
			}
		}
		// 우
		else if(d==3) {
			for (int j = c+1; j < W; j++) {
				// 끝인데, 돌이 없는 경우
				if(j==W-1 && maze[r][j]>=0) break;
				// 돌이야
				if(maze[r][j]==-5) {
					if(visited[r][j-1]>val) {
						visited[r][j-1] = val;
						q.offer(new Pos(r, j-1));
					}
					break;
				}else if(maze[r][j]>=0) {
					val += maze[r][j];
				}else if(maze[r][j]==-100) {
					break;
				}else if(maze[r][j]==-2) {
					if(visited[r][j] > val) {
						visited[r][j] = val;
					}
				}
			}
		}
		// 좌
		else {
			for (int j = c-1; j >= 0; j--) {
				// 끝인데, 돌이 없는 경우
				if(j==0 && maze[r][j]>=0) break;
				// 돌이야
				if(maze[r][j]==-5) {
					if(visited[r][j+1]>val) {
						visited[r][j+1] = val;
						q.offer(new Pos(r, j+1));
					}
					break;
				}else if(maze[r][j]>=0) {
					val += maze[r][j];
				}else if(maze[r][j]==-100) {
					break;
				}else if(maze[r][j]==-2) {
					if(visited[r][j] > val) {
						visited[r][j] = val;
					}
				}
			}
		}
	}
}