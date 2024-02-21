import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] joint;
    static int[] plan;
    static int[] p, r;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());  M = Integer.parseInt(br.readLine());
        joint = new int[N][N];
        plan = new int[M];
        Init();
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				joint[i][j] = Integer.parseInt(st.nextToken());
				if(joint[i][j] == 1) union(i, j);
			}
		}
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) plan[i] = Integer.parseInt(st.nextToken())-1;
        
        for (int i = 1; i < M; i++) {
			int prev = plan[i-1];
			int next = plan[i];
			if(find(prev)!=find(next)) {
				System.out.println("NO");
				return;
			}
		}
        System.out.println("YES");
    }

	private static void Init() {
		p = new int[N];
		r = new int[N];
		for (int i = 0; i < N; i++) {
			p[i] = i;
			r[i] = 1;
		}
	}

	private static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x==y) return;
		
		if(r[x]<r[y]) {
			p[x] = y;
			r[y] += r[x];
		}else {
			p[y] = x;
			r[x] += r[y];
		}
	}

	private static int find(int x) {
		if(x==p[x]) return p[x];
		return p[x] = find(p[x]);
	}
}