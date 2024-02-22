import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static class Edge implements Comparable<Edge>{
    	int cost, s, e;
		public Edge(int cost, int s, int e) {
			this.cost = cost;
			this.s = s;
			this.e = e;
		}
		@Override
		public int compareTo(Edge E) {
			return Integer.compare(cost, E.cost);
		}
    }
    static StringTokenizer st;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static int[] p, r;
    
    public static void main(String[] args) throws IOException {
    	// 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
        
        for (int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int A = Integer.parseInt(st.nextToken());
        	int B = Integer.parseInt(st.nextToken());
        	int C = Integer.parseInt(st.nextToken());
			pq.add(new Edge(C, A, B)); // 우선 순위 큐에 간선 정보 넣어서 간선 가중치 값이 낮은 순으로 정렬하기
		}
        // 이미 정렬 되어 있으므로 큐에서 빼는 순서가 곧 간선 가중치가 낮은 순임 -> 그리디
        
        // 최소 스패닝 트리 제작 전에, 부모 정보, rank 정보 배열을 초기화
        Init();
        
        int cnt = 0;
        int ANS = 0;
        while(cnt != N-1) { // 중복되는 간선만 처리해준다면 V-1개 만큼의 간선이 존재할 때, 모두 연결된 그래프라는 것이 보장된다.
        	Edge E = pq.poll();
        	if(isUnion(E.e, E.s)) { // 기존에 연결되어 있던 간선이 아니라면~ 즉, 중복 제거하는 부분
        		ANS += E.cost;
        		cnt++;
        	}
        }
        System.out.println(ANS);
    }

	private static void Init() {
		p = new int[N+1];
		r = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			p[i] = i;
			r[i] = 1;
		}
	}

	private static boolean isUnion(int e, int s) {
		e = find(e);
		s = find(s);
		if(e==s) return false;
		
		if(r[e]>=r[s]) {
			r[e] += r[s];
			p[s] = e;
		}else {
			r[s] += r[e];
			p[e] = s;
		}
		return true;
	}

	private static int find(int e) {
		if(p[e]==e) return p[e];
		return p[e] = find(p[e]); // 압축!
	}
	
}