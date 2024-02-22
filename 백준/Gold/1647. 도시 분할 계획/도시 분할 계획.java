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
    static int N, M;
    static int[] p, r;
    static StringTokenizer st;
    static class Edge implements Comparable<Edge>{
    	int cost, s, e;
    	Edge(int cost, int s, int e){
    		this.cost = cost;
    		this.s = s;
    		this.e = e;
    	}
		@Override
		public int compareTo(Edge E) {
			return Integer.compare(cost, E.cost);
		}
    }
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    
    public static void main(String[] args) throws IOException {
    	// 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
        Init();
        for (int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int A = Integer.parseInt(st.nextToken());
        	int B = Integer.parseInt(st.nextToken());
        	int C = Integer.parseInt(st.nextToken());
        	pq.offer(new Edge(C, A, B)); // 간선 등록
		}
        int cnt = 0;
        int sum = 0;
        while(cnt != N-2) {
        	Edge E = pq.poll();
        	if(isUnion(E.s, E.e)) {
        		sum += E.cost;
        		cnt++;
        	}
        }
        System.out.println(sum);
    }

	private static boolean isUnion(int a, int b) {
		a = find(a);
		b = find(b);
		if(a==b) return false;
		
		if(r[a]>=r[b]) {
			r[a] += r[b];
			p[b] = a;
		}else {
			r[b] += r[a];
			p[a] = b;
		}
		return true;
	}

	private static int find(int a) {
		if(a==p[a]) return p[a];
		return p[a] = find(p[a]);
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