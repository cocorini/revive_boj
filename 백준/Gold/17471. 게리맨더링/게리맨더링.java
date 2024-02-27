import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class Main {
	private static int N, Ans = Integer.MAX_VALUE;
	private static int[] city, teamA, teamB;
	private static List<Integer>[] graph;
	private static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String[] s = br.readLine().split(" ");
		city = new int[N+1];
		graph = new ArrayList[N+1];
		
		for (int i = 1; i < N+1; i++)
			city[i] = Integer.parseInt(s[i-1]);
		
		for (int i = 1; i < N+1; i++) {
			s = br.readLine().split(" ");
			graph[i] = new ArrayList<>();
			for (int j = 1; j <= Integer.parseInt(s[0]); j++)
				graph[i].add(Integer.parseInt(s[j]));
		}
		
		
		for (int R = 1; R <= N/2; R++) {
			teamA = new int[R];
			teamB = new int[N-R];
			recur(0, 1, R);
		}
		
		if(Ans==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(Ans);
	}

	private static void recur(int cnt, int start, int R) {
		int sumA = 0, sumB = 0;
		if(cnt==R) {
			for (int i = 1, k = 0; i <= N; i++) {
				boolean isA = false;
				for (int j = 0; j < R; j++) {
					if(teamA[j]==i) {
						isA = true;
						sumA += city[i];
						break;
					}
				}
				if(!isA) {
					sumB += city[i];
					teamB[k++] = i;
				}
			}
			// 팀 결성 완료.
//			System.out.println("teamA: "+Arrays.toString(teamA));
//			System.out.println("teamB: "+Arrays.toString(teamB));
//			System.out.println("===============================");
			// 이렇게 맞춘 팀이 선거구로 나눌 수 있는 구획인지 확인하기.
			if(check()) Ans = Math.min(Ans, Math.abs(sumA-sumB));
			return;
		}
		
		for (int i = start; i <= N; i++) {
			teamA[cnt] = i;
			recur(cnt+1, i+1, R);
		}
	}

	private static boolean check() {
		boolean A = true, B = true;
		if(teamA.length > 1) {
			A = bfs(teamA[0], true);
		}
		if(teamB.length > 1) {
			B = bfs(teamB[0], false);
		}
		return A && B;
	}
	private static boolean bfs(int node, boolean isA) {
		visited = new boolean[N+1];
		visited[node] = true;
		Deque<Integer> q = new ArrayDeque<>();
		q.add(node);
		while(!q.isEmpty()) {
			int cur = q.poll();
			for (int i = 0; i < graph[cur].size() ; i++) {
				int next = graph[cur].get(i);
				if((!isA && isTeamB(cur, next)) || (isA && isTeamA(cur, next))) {
					if(!visited[next]) {
						visited[next] = true;
						q.add(next);
					}
				}
			}
		}
		if(isA) {
			for (int i = 0; i < teamA.length; i++) {
				int a = teamA[i];
				if(!visited[a]) return false;
			}
		}else {
			for (int i = 0; i < teamB.length; i++) {
				int b = teamB[i];
				if(!visited[b]) return false;
			}
		}
		return true;
	}
	private static boolean isTeamA(int cur, int next) {
		boolean c = false, n = false;
		for (int i = 0; i < teamA.length; i++) {
			if(teamA[i]==cur) c = true;
			else if(teamA[i]==next) n = true;
		}
		return c && n;
	}
	
	private static boolean isTeamB(int cur, int next) {
		boolean c = false, n = false;
		for (int i = 0; i < teamB.length; i++) {
			if(teamB[i]==cur) c = true;
			else if(teamB[i]==next) n = true;
		}
		return c && n;
	}
}