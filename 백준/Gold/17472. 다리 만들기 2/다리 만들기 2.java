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
    static int N, M, island_cnt = 1;
    static int[][] map;
    static class Edge implements Comparable<Edge>{
    	int s, e;
    	int cost;
		public Edge(int cost, int s, int e) {
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
    	int r, c;
    	public Pos(int r, int c) {
    		this.r = r;
    		this.c = c;
    	}
    }
    static StringTokenizer st;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static int[] p, r;
    static int[] dr = {-1, 0, 1, 0, 0}, dc= {0, -1, 0, 1, 0};
    static List<Pos>[] border = new ArrayList[7];
    
    public static void main(String[] args) throws IOException {
    	// 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
        for (int i = 1; i <= 6; i++)
			border[i] = new ArrayList<>();
        
        find_border();
        
        for (int i = 1; i < island_cnt; i++) {
        	int count = 0;
        	for (int j = 1; j < island_cnt; j++) {
				if(i==j) continue;
				for (int ii = 0; ii < border[i].size(); ii++) {
	        		int ir = border[i].get(ii).r;
	        		int ic = border[i].get(ii).c;
					a: for (int jj = 0; jj < border[j].size(); jj++) {
						int jr = border[j].get(jj).r;
		        		int jc = border[j].get(jj).c;
		        		if(ir == jr || ic == jc) { // 가로, 세로 다리만 취급
		        			if(ir == jr && ic == jc) continue;
		        			if(Math.abs(ir-jr)+Math.abs(ic-jc) >= 1) {// 다리 길이는 2 이상
		        				// 가로 다리라면 가로로 연결 되어 있어야 함. 이거 어케 판단함..?!
		        				// 만일 가로라면?
		        				if(ir==jr) {
		        					// ic>jc 라면?
		        					if(ic>jc) {
		        						if(isIn(ir, ic+1) && isIn(ir, jc-1)) {
		        							if(map[ir][ic+1]==i && map[ir][jc-1]==j) { // 가능
		        								for (int k = jc; k <= ic; k++) {
													if(map[ir][k]!=0) continue a;
												}
		        								int cost = Math.abs(ic-jc);
		        		        				pq.offer(new Edge(cost+1, i, j));
		        		        				count++;
		        							}
		        						}
		        					}else {
		        						if(isIn(ir, ic-1) && isIn(ir, jc+1)) {
		        							if(map[ir][ic-1]==i && map[ir][jc+1]==j) {
		        								for (int k = ic; k <= jc; k++) {
													if(map[ir][k]!=0) continue a;
												}
		        								int cost = Math.abs(ic-jc);
		        		        				pq.offer(new Edge(cost+1, i, j));
		        		        				count++;
		        							}
		        						}
		        					}
		        				} else { // 세로로 연결된 다리라면
		        					if(ir>jr) {
		        						if(isIn(ir+1, ic) && isIn(jr-1, ic)) {
		        							if(map[ir+1][ic]==i && map[jr-1][ic]==j) { // 가능
		        								for (int k = jr; k <= ir; k++) {
													if(map[k][ic]!=0) continue a;
												}
		        								int cost = Math.abs(ir-jr);
		        		        				pq.offer(new Edge(cost+1, i, j));
		        		        				count++;
		        							}
		        						}
		        					}else {
		        						if(isIn(ir-1, ic) && isIn(jr+1, ic)) {
		        							if(map[ir-1][ic]==i && map[jr+1][ic]==j) { // 가능
		        								for (int k = ir; k <= jr; k++) {
													if(map[k][ic]!=0) continue a;
												}
		        								int cost = Math.abs(ir-jr);
		        		        				pq.offer(new Edge(cost+1, i, j));
		        		        				count++;
		        							}
		        						}
		        					}
		        				}
		        			}
		        		}
					}
				}
        	}
        	if(count==0) {
        		System.out.println(-1);
            	return;
        	}
		}
        
        Init();
        
        int ans = 0;
        int cnt = 0;
        if(pq.size() < island_cnt-2) {
        	System.out.println(-1);
        	return;
        }
        
        while(cnt != island_cnt-2) {
        	Edge E = pq.poll();
        	if(isUnion(E.e, E.s)) {
        		ans += E.cost;
        		cnt++;
        	}
        	if(pq.isEmpty() && cnt != island_cnt-2) {
        		ans = -1;
        		break;
        	}
        }
        
        System.out.println(ans);
    }
    
	private static void Init() {
		p = new int[island_cnt];
		r = new int[island_cnt];
		
		for (int i = 1; i < island_cnt; i++) {
			r[i] = 1;
			p[i] = i;
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
		if(e==p[e]) return p[e];
		return p[e] = find(p[e]);
	}
	
	private static void find_border() {
		Deque<Pos> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j]==1 && !visited[i][j]) {
					q.offer(new Pos(i, j));
					while(!q.isEmpty()) {
						Pos pos = q.poll();
						for (int d = 0; d < 5; d++) {
							int nr = pos.r + dr[d];
							int nc = pos.c + dc[d];
							if(isIn(nr, nc) && map[nr][nc]==1 && !visited[nr][nc]) {
								map[nr][nc] = island_cnt;
								visited[nr][nc] = true;
								for (int dd = 0; dd < 4; dd++) {
									int nnr = nr + dr[dd];
									int nnc = nc + dc[dd];
									if(isIn(nnr, nnc) && map[nnr][nnc]==0) {
										border[island_cnt].add(new Pos(nnr, nnc));
									}
								}
								q.offer(new Pos(nr, nc));
							}
						}
					}
					island_cnt++;
				}
			}
		}
	}

	private static boolean isIn(int nr, int nc) {
		return nr>=0 && nc>=0 && nr<N && nc<M;
	}
}