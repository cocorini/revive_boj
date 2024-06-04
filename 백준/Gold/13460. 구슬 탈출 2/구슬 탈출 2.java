import java.io.*;
import java.util.*;

public class Main {
	static int N, M, ANS = Integer.MAX_VALUE;
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	static char[][] first_board;
	static class Pos {
		int r, c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		first_board = new char[N][M];
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++)
				first_board[i][j] = input.charAt(j);
		}
		
		playToRecur(1, first_board);
		
		// 정답 출력
		if(ANS==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ANS);
	}

	private static void playToRecur(int cnt, char[][] board) {
		// 프루닝
		if(ANS<=cnt) return;
		// 10번 움직였는데도 게임 안끝남 -> 종료
		if(cnt==11) return;
		
		char[][] tmp_board = new char[N][M];
		
		// '상 하 좌 우'로 움직임
		for (int dir = 0; dir < 4; dir++) {
			// 초기화
			for (int i = 0; i < N; i++)
				for (int j = 0; j < M; j++)
					tmp_board[i][j] = board[i][j];
			
			char[][] move_board = moveBoard(dir, tmp_board);
			if(move_board[0][0]=='R') {
				ANS = Math.min(ANS, cnt);
				break;
			} else if(move_board[0][0]=='B') {
				continue;
			} else {
				playToRecur(cnt+1, move_board);
			}
		}
	}

	private static char[][] moveBoard(int dir, char[][] board) {
		// 구슬부터 찾기 -> dir에 따라 움직이는 구슬의 순서가 정해지겠네
		PriorityQueue<Pos> pq;
		// 상
		if(dir==0)
			pq = new PriorityQueue<>((p1, p2) -> Integer.compare(p1.r, p2.r));
		// 하
		else if(dir==1)
			pq = new PriorityQueue<>((p1, p2) -> Integer.compare(p2.r, p1.r));
		// 좌
		else if(dir==2)
			pq = new PriorityQueue<>((p1, p2) -> Integer.compare(p1.c, p2.c));
		// 우
		else
			pq = new PriorityQueue<>((p1, p2) -> Integer.compare(p2.c, p1.c));
		
		// 큐에 넣으면 알아서 큐 안에서 알아서 정렬될 것임
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if(board[i][j]=='B' || board[i][j]=='R') pq.offer(new Pos(i, j));
		
		// O에 나간 구슬 담기
		List<Character> quit_ball = new ArrayList<>();
		while(!pq.isEmpty()) {
			Pos p = pq.poll();
			int r = p.r, c = p.c;
			char ch = board[r][c];
			// 구슬 가는 길 탐색
			while(true) {
				int nr = r + dr[dir], nc = c + dc[dir];
				if(board[nr][nc]=='.') {
					r = nr;
					c = nc;
				}
				else if(board[nr][nc]=='O') {
					board[p.r][p.c] = '.';
					quit_ball.add(ch);
					break;
				}
				else {
					//앞으로 못감 구슬이 있거나, 벽이 있는 경우
					board[p.r][p.c] = '.';
					board[r][c] = ch;
					break;
				}
			}
		}
		// 만약에 quit_ball에 구슬이 들어가있으면?
		for (int i = 0; i < quit_ball.size(); i++)
			if(quit_ball.get(i)=='B')
				return new char[][] {{'B'}};
		
		if(quit_ball.isEmpty()) {
			// 그냥 이동만.. O에 아무도 안들어감
			char[][] new_board = new char[N][M];
			for (int i = 0; i < N; i++)
				for (int j = 0; j < M; j++)
					new_board[i][j] = board[i][j];
			return new_board;
		}
		else {
			// red만 나감!
			return new char[][] {{'R'}};
		}
	}
}