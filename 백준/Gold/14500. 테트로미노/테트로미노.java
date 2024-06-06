import java.io.*;
import java.util.*;

public class Main {
	static int N, M, paper[][];
	static List<boolean[][]>[] tetromino = new ArrayList[5];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
		paper = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) paper[i][j] = Integer.parseInt(st.nextToken());
		}
		
		makeTetro();
		
        int ANS = -1;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < tetromino[i].size(); j++) {
						int sum = 0;
						loop: for (int k = 0; k < tetromino[i].get(j).length; k++) {
							for (int l = 0; l < tetromino[i].get(j)[k].length; l++) {
								if(!tetromino[i].get(j)[k][l]) continue;
								int R = r+k, C = c+l;
								if(!isIn(R, C)) break loop;
								sum += paper[R][C];
							}
						}
						ANS = Math.max(ANS, sum);
					}
				}
			}
		}
		System.out.println(ANS);
	}

	private static boolean isIn(int r, int c) {
		return r<N && c<M && r>=0 && c>=0;
	}

	private static void makeTetro() {
		for (int i = 0; i < 5; i++)
			tetromino[i] = new ArrayList<>();
        // 0번 모양
		tetromino[0].add(new boolean[][] {{true, true, true, true}});
		tetromino[0].add(new boolean[][] {{true}, {true}, {true}, {true}});
		// 1번 모양
		tetromino[1].add(new boolean[][] {{true, true}, {true, true}});
		// 2번 모양
		tetromino[2].add(new boolean[][] {{false, false, true}, {true, true, true}});
		tetromino[2].add(new boolean[][] {{true, false, false}, {true, true, true}});
		tetromino[2].add(new boolean[][] {{true, true, true}, {false, false, true}});
		tetromino[2].add(new boolean[][] {{true, true, true}, {true, false, false}});
		
		tetromino[2].add(new boolean[][] {{true, false}, {true, false}, {true, true}});
		tetromino[2].add(new boolean[][] {{false, true}, {false, true}, {true, true}});
		tetromino[2].add(new boolean[][] {{true, true}, {true, false}, {true, false}});
		tetromino[2].add(new boolean[][] {{true, true}, {false, true}, {false, true}});
		
		// 3번 모양
		tetromino[3].add(new boolean[][] {{false, true, true}, {true, true, false}});
		tetromino[3].add(new boolean[][] {{true, true, false}, {false, true, true}});
		
		tetromino[3].add(new boolean[][] {{true, false}, {true, true}, {false, true}});
		tetromino[3].add(new boolean[][] {{false, true}, {true, true}, {true, false}});
		
		// 4번 모양
		tetromino[4].add(new boolean[][] {{true, true, true}, {false, true, false}});
		tetromino[4].add(new boolean[][] {{false, true, false}, {true, true, true}});
		
		tetromino[4].add(new boolean[][] {{true, false}, {true, true}, {true, false}});
		tetromino[4].add(new boolean[][] {{false, true}, {true, true}, {false, true}});
	}
}