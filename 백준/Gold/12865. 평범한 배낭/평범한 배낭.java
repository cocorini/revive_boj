import java.io.*;
import java.util.*;

public class Main {
	static StringTokenizer st;
	static int N, K;
	static int[][] v_pack;
	static class Obj{
		int W, V;
		public Obj(int w, int v) {
			W = w;
			V = v;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		v_pack = new int[N+1][K+1];
		Obj[] objs = new Obj[N+1];
		objs[0] = new Obj(0, 0);
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int W = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			objs[i] = new Obj(W, V);
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				v_pack[i][j] = v_pack[i-1][j];
				if(j>=objs[i].W) v_pack[i][j] = Math.max(v_pack[i][j], v_pack[i-1][j-objs[i].W]+objs[i].V);
			}
		}
		
		System.out.println(v_pack[N][K]);
	}
}