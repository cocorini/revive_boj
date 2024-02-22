import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static class Edge implements Comparable<Edge>{
    	int s, e;
    	double cost;
		public Edge(double cost, int s, int e) {
			this.cost = cost;
			this.s = s;
			this.e = e;
		}
		@Override
		public int compareTo(Edge E) {
			return Double.compare(cost, E.cost);
		}
    }
    static class Pos{
    	double x, y;
    	public Pos(double x, double y) {
    		this.x = x;
    		this.y = y;
    	}
    }
    static Pos[] stars;
    static StringTokenizer st;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static int[] p, r;
    
    public static void main(String[] args) throws IOException {
    	// 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        stars = new Pos[N];
        
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	double x = Double.parseDouble(st.nextToken());
        	double y = Double.parseDouble(st.nextToken());
        	stars[i] = new Pos(x, y);
		}
        
        for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				double cost = Math.sqrt(Math.pow(stars[i].x-stars[j].x, 2)+Math.pow(stars[i].y-stars[j].y, 2));
				pq.add(new Edge(cost, i, j));
			}
		}
        
        Init();
        
        double ans = 0;
        int cnt = 0;
        while(cnt != N-1) {
        	Edge E = pq.poll();
        	if(isUnion(E.s, E.e)) {
        		ans += E.cost;
        		cnt++;
        	}
        }
        System.out.println(String.format("%.2f", ans)); // 소수점 둘째자리
    }

	private static void Init() {
		p = new int[N];
		r = new int[N];
		for (int i = 0; i < N; i++) {
			p[i] = i;
			r[i] = 1;
		}
	}

	private static boolean isUnion(int s, int e) {
		s = find(s);
		e = find(e);
		if(s==e) return false;
		
		if(r[s]>=r[e]) {
			r[s] += r[e];
			p[e] = s;
		}else {
			r[e] += r[s];
			p[s] = e;
		}
		return true;
	}

	private static int find(int s) {
		if(s==p[s]) return p[s];
		return p[s] = find(p[s]);
	}
}