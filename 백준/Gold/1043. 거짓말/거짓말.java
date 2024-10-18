import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static Set<Integer> set = new HashSet<>();
	static int N, M, prevLen;
	static List<Integer>[] list;
	static boolean[][] visited;
	
	
	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[M][N+1];
		
		st = new StringTokenizer(br.readLine());
		int size = Integer.parseInt(st.nextToken());
		while(size>0) {
			set.add(Integer.parseInt(st.nextToken()));
			size--;
		}
		
		list = new ArrayList[M];
		for (int i = 0; i < M; i++) {
			list[i] = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			int tmpLen = Integer.parseInt(st.nextToken());
			for (int j = 0; j < tmpLen; j++) {
				list[i].add(Integer.parseInt(st.nextToken()));
				visited[i][list[i].get(j)] = true;
			}
		}
		
		while(true) {
			prevLen = set.size();
			for (int i = 0; i < M; i++) {
				for (int node : set) {
					if(visited[i][node]) {
						for (int j = 0; j < list[i].size(); j++) set.add(list[i].get(j));
						break;
					}
				}
			}
			if(prevLen==set.size()) break;
		}

		// TODO 문제 정답 출력하기
		int ans = 0;
		a: for (int i = 0; i < M; i++) {
			for (int node : set) {
				if(visited[i][node]) continue a;
			}
			ans++;
		}
		
		System.out.println(ans);
	}

}