import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, cctv_cnt = 0, Ans = Integer.MAX_VALUE;
    static StringTokenizer st;
    static int[][] office, tmp;
    static class Pos{
    	int r, c;
    	Pos(int r, int c){
    		this.r = r;
    		this.c = c;
    	}
    }
    static List<Pos> CCTV = new ArrayList<>();
    static int[] dir;
    static List<Pos>[] direction = new ArrayList[6];
    
    public static void main(String[] args) throws IOException {
    	// 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
        office = new int[N][M];
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				office[i][j] = Integer.parseInt(st.nextToken());
				if(office[i][j]>0 && office[i][j]<6) {
					CCTV.add(new Pos(i, j));
					cctv_cnt++;
				}
			}
		}
        dir = new int[cctv_cnt];
        for (int i = 1; i < 6; i++) {
			direction[i] = new ArrayList<>();
			if(i==1) {
				direction[i].add(new Pos(-1, 0));
				direction[i].add(new Pos(-1, 0));
				direction[i].add(new Pos(-1, 0));
				direction[i].add(new Pos(-1, 0));
			}
		}
        
        recur(0);
        System.out.println(Ans);
    }

	private static void recur(int cnt) {
		if(cnt==cctv_cnt) {
			tmp = new int[N][M];
			for (int i = 0; i < N; i++) tmp[i] = office[i].clone();
			// cctv 방향 전환 완료.
			for (int ii = 0; ii < cctv_cnt; ii++) {
				Pos p = CCTV.get(ii);
				int cctv_idx = tmp[p.r][p.c];
				int direc = dir[ii];
				if(cctv_idx==1) {
					if(direc==0) {
						// 상
						for (int i = p.r; i >= 0 ; i--) {
							if(tmp[i][p.c]==6) break;
							else if(tmp[i][p.c] > 0) continue;
							else tmp[i][p.c] = -1;
						}
					}else if(direc==1) {
						// 우
						for (int j = p.c; j < M ; j++) {
							if(tmp[p.r][j]==6) break;
							else if(tmp[p.r][j] > 0) continue;
							else tmp[p.r][j] = -1;
						}
					}else if(direc==2) {
						// 하
						for (int i = p.r; i < N ; i++) {
							if(tmp[i][p.c]==6) break;
							else if(tmp[i][p.c] > 0) continue;
							else tmp[i][p.c] = -1;
						}
					}else if(direc==3) {
						// 좌
						for (int j = p.c; j >= 0 ; j--) {
							if(tmp[p.r][j]==6) break;
							else if(tmp[p.r][j] > 0) continue;
							else tmp[p.r][j] = -1;
						}
					}
				}else if(cctv_idx==2) {
					if(direc==0) {
						// 상
						for (int i = p.r; i >= 0 ; i--) {
							if(tmp[i][p.c]==6) break;
							else if(tmp[i][p.c] > 0) continue;
							else tmp[i][p.c] = -1;
						}
						// 하
						for (int i = p.r; i < N ; i++) {
							if(tmp[i][p.c]==6) break;
							else if(tmp[i][p.c] > 0) continue;
							else tmp[i][p.c] = -1;
						}
					}else {
						// 우
						for (int j = p.c; j < M ; j++) {
							if(tmp[p.r][j]==6) break;
							else if(tmp[p.r][j] > 0) continue;
							else tmp[p.r][j] = -1;
						}
						// 좌
						for (int j = p.c; j >= 0 ; j--) {
							if(tmp[p.r][j]==6) break;
							else if(tmp[p.r][j] > 0) continue;
							else tmp[p.r][j] = -1;
						}
					}
				}else if(cctv_idx==3) {
					if(direc==0) {
						// 상
						for (int i = p.r; i >= 0 ; i--) {
							if(tmp[i][p.c]==6) break;
							else if(tmp[i][p.c] > 0) continue;
							else tmp[i][p.c] = -1;
						}
						// 우
						for (int j = p.c; j < M ; j++) {
							if(tmp[p.r][j]==6) break;
							else if(tmp[p.r][j] > 0) continue;
							else tmp[p.r][j] = -1;
						}
					}else if(direc==1) {
						// 하
						for (int i = p.r; i < N ; i++) {
							if(tmp[i][p.c]==6) break;
							else if(tmp[i][p.c] > 0) continue;
							else tmp[i][p.c] = -1;
						}
						// 우
						for (int j = p.c; j < M ; j++) {
							if(tmp[p.r][j]==6) break;
							else if(tmp[p.r][j] > 0) continue;
							else tmp[p.r][j] = -1;
						}
					}else if(direc==2) {
						// 하
						for (int i = p.r; i < N ; i++) {
							if(tmp[i][p.c]==6) break;
							else if(tmp[i][p.c] > 0) continue;
							else tmp[i][p.c] = -1;
						}
						// 좌
						for (int j = p.c; j >= 0 ; j--) {
							if(tmp[p.r][j]==6) break;
							else if(tmp[p.r][j] > 0) continue;
							else tmp[p.r][j] = -1;
						}
					}else {
						// 상
						for (int i = p.r; i >= 0 ; i--) {
							if(tmp[i][p.c]==6) break;
							else if(tmp[i][p.c] > 0) continue;
							else tmp[i][p.c] = -1;
						}
						// 좌
						for (int j = p.c; j >= 0 ; j--) {
							if(tmp[p.r][j]==6) break;
							else if(tmp[p.r][j] > 0) continue;
							else tmp[p.r][j] = -1;
						}
					} 
				}else if(cctv_idx==4) {
						if(direc==0) {
							// 상
							for (int i = p.r; i >= 0 ; i--) {
								if(tmp[i][p.c]==6) break;
								else if(tmp[i][p.c] > 0) continue;
								else tmp[i][p.c] = -1;
							}
							// 우
							for (int j = p.c; j < M ; j++) {
								if(tmp[p.r][j]==6) break;
								else if(tmp[p.r][j] > 0) continue;
								else tmp[p.r][j] = -1;
							}
							// 좌
							for (int j = p.c; j >= 0 ; j--) {
								if(tmp[p.r][j]==6) break;
								else if(tmp[p.r][j] > 0) continue;
								else tmp[p.r][j] = -1;
							}
						}else if(direc==1) {
							// 하
							for (int i = p.r; i < N ; i++) {
								if(tmp[i][p.c]==6) break;
								else if(tmp[i][p.c] > 0) continue;
								else tmp[i][p.c] = -1;
							}
							// 우
							for (int j = p.c; j < M ; j++) {
								if(tmp[p.r][j]==6) break;
								else if(tmp[p.r][j] > 0) continue;
								else tmp[p.r][j] = -1;
							}
							// 좌
							for (int j = p.c; j >= 0 ; j--) {
								if(tmp[p.r][j]==6) break;
								else if(tmp[p.r][j] > 0) continue;
								else tmp[p.r][j] = -1;
							}
						}else if(direc==2) {
							// 하
							for (int i = p.r; i < N ; i++) {
								if(tmp[i][p.c]==6) break;
								else if(tmp[i][p.c] > 0) continue;
								else tmp[i][p.c] = -1;
							}
							// 좌
							for (int j = p.c; j >= 0 ; j--) {
								if(tmp[p.r][j]==6) break;
								else if(tmp[p.r][j] > 0) continue;
								else tmp[p.r][j] = -1;
							}
							// 상
							for (int i = p.r; i >= 0 ; i--) {
								if(tmp[i][p.c]==6) break;
								else if(tmp[i][p.c] > 0) continue;
								else tmp[i][p.c] = -1;
							}
						}else {
							// 상
							for (int i = p.r; i >= 0 ; i--) {
								if(tmp[i][p.c]==6) break;
								else if(tmp[i][p.c] > 0) continue;
								else tmp[i][p.c] = -1;
							}
							// 하
							for (int i = p.r; i < N ; i++) {
								if(tmp[i][p.c]==6) break;
								else if(tmp[i][p.c] > 0) continue;
								else tmp[i][p.c] = -1;
							}
							// 우
							for (int j = p.c; j < M ; j++) {
								if(tmp[p.r][j]==6) break;
								else if(tmp[p.r][j] > 0) continue;
								else tmp[p.r][j] = -1;
							}
						}
					}else if(cctv_idx==5) {
						// 하
						for (int i = p.r; i < N ; i++) {
							if(tmp[i][p.c]==6) break;
							else if(tmp[i][p.c] > 0) continue;
							else tmp[i][p.c] = -1;
						}
						// 좌
						for (int j = p.c; j >= 0 ; j--) {
							if(tmp[p.r][j]==6) break;
							else if(tmp[p.r][j] > 0) continue;
							else tmp[p.r][j] = -1;
						}
						// 상
						for (int i = p.r; i >= 0 ; i--) {
							if(tmp[i][p.c]==6) break;
							else if(tmp[i][p.c] > 0) continue;
							else tmp[i][p.c] = -1;
						}
						// 우
						for (int j = p.c; j < M ; j++) {
							if(tmp[p.r][j]==6) break;
							else if(tmp[p.r][j] > 0) continue;
							else tmp[p.r][j] = -1;
						}
					}
			}
			int sum = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(tmp[i][j]==0) sum++;
				}
			}
			Ans = Math.min(Ans, sum);
			return;
		}
		
		Pos p = CCTV.get(cnt);
		if(office[p.r][p.c]==1) {
			for (int i = 0; i < 4; i++) {
				dir[cnt] = i;
				recur(cnt+1);
			}
		}else if(office[p.r][p.c]==2) {
			for (int i = 0; i < 2; i++) {
				dir[cnt] = i;
				recur(cnt+1);
			}
		}else if(office[p.r][p.c]==3) {
			for (int i = 0; i < 4; i++) {
				dir[cnt] = i;
				recur(cnt+1);
			}
		}else if(office[p.r][p.c]==4) {
			for (int i = 0; i < 4; i++) {
				dir[cnt] = i;
				recur(cnt+1);
			}
		}else {
			dir[cnt] = 0;
			recur(cnt+1);
		}
	}
    
}