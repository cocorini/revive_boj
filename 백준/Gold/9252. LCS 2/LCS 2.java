import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static String L, S;
	static int[][] table;
	static class Pos {
		int r, c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}	
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		L = br.readLine();
		S = br.readLine();
		L = " "+L;
		S = " "+S;
		table = new int[S.length()][L.length()];
		
		isLCS();
	}

	private static void isLCS() {
		StringBuffer sb = new StringBuffer();
		// O(N^2)
		for (int i = 1; i < S.length(); i++) {
			char ch = S.charAt(i);
			for (int j = 1; j < L.length(); j++) {
				if(ch==L.charAt(j)) table[i][j] = table[i-1][j-1]+1;
				else table[i][j] = Math.max(table[i-1][j], table[i][j-1]);
			}
		}
		Deque<Pos> q = new ArrayDeque<>();
		q.offer(new Pos(S.length()-1, L.length()-1));
		while(!q.isEmpty()) {
			Pos p = q.poll();
			int r = p.r, c = p.c;
			if(table[r][c]==0) {
				System.out.println(sb.length()+"\n"+sb.reverse());
				return;
			}
			if(table[r-1][c] == table[r][c]) {
				q.offer(new Pos(r-1, c));
			} else if(table[r][c-1] == table[r][c]) {
				q.offer(new Pos(r, c-1));
			} else {
				sb.append(L.charAt(c));
				q.offer(new Pos(r-1, c-1));
			}
		}
	}	
}