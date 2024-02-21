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
    static int N, M, MIN = 0;
    static class Edge implements Comparable<Edge>{
    	int cost, s, e;
    	public Edge(int s, int e, int cost){
    		this.cost = cost;
    		this.s = s;
    		this.e = e;
    	}
		@Override
		public int compareTo(Edge E) {
			return Integer.compare(cost, E.cost);
		}
    }
    static PriorityQueue<Edge> points;
    static int[] p, r;
    static StringTokenizer st;
    
    public static void main(String[] args) throws IOException {
    	// 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); M = Integer.parseInt(br.readLine());
        points = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			points.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
        
        // Init
        Init();
        int edge_cnt = 0; // 진행하면서 얻은 간선 개수
        // 간선이 N-1개면 모든 정점을 지나는 것.
        while(edge_cnt != N-1) {
        	Edge next = points.poll();
        	if(IsNowUnion(next.s, next.e)) {
        		// 이제 연결 된거
        		edge_cnt++;
        		MIN += next.cost;
        	}
        }
        System.out.println(MIN);
    }

	private static boolean IsNowUnion(int x, int y) {
		x = find(x);
		y = find(y);
		if(x==y) return false; // 원래 연결 되어 있던 상태
		
		if(r[x]>r[y]) {
			r[x] += r[y];
			p[y] = x;
		}else {
			r[y] += r[x];
			p[x] = y;
		}
		return true; // 이번에 연결함.
	}

	private static int find(int x) {
		if(x==p[x]) return p[x];
		return p[x] = find(p[x]);
	}

	private static void Init() {
		p = new int[N+1];
		r = new int[N+1];
		for (int i = 1; i <= N; i++) {
			p[i] = i;
			r[i] = 1;
		}
	}
}