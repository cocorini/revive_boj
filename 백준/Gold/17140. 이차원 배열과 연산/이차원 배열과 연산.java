import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	static int r, c, k, R, C;
	static StringTokenizer st;
	static int[][] arr = new int[100][100];
	static Map<Integer, Integer> map;
	static PriorityQueue<Pair> pq;
	static class Pair implements Comparable<Pair>{
		int num, cnt;
		public Pair(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Pair p) {
			int x = Integer.compare(cnt, p.cnt);
			if(x==0) {
				return Integer.compare(num, p.num);
			}return x;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		R = 3; C = 3;
		simulation();
	}

	private static void simulation() {
		int time = 0;
		while(time<=100) {
			// 문제 종료 조건
			if(arr[r-1][c-1]==k) {
				System.out.println(time);
				return;
			}
			// 연산 R 수행
			if(R>=C) {
				int tmpC = C;
				// 행 원소
				for (int i = 0; i < R; i++) {
					map = new TreeMap<>();
					for (int j = 0; j < C; j++) {
						if(arr[i][j] != 0) map.put(arr[i][j], map.getOrDefault(arr[i][j], 0)+1);
					}
					pq = new PriorityQueue<>();
					for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
						Integer key = entry.getKey();
						Integer val = entry.getValue();
						pq.offer(new Pair(key, val));
					}
					int[] tmp = new int[100];
					int idx = 0;
					while(!pq.isEmpty()) {
						Pair p = pq.poll();
						if(idx+1 < 100) tmp[idx++] = p.num;
						if(idx+1 < 100) tmp[idx++] = p.cnt;
					}
					arr[i] = tmp.clone();
					tmpC = Math.max(tmpC, idx);
				}
				C = tmpC;
			}
			// 연산 C 수행
			else {
				int tmpR = R;
				for (int j = 0; j < C; j++) {
					map = new TreeMap<>();
					for (int i = 0; i < R; i++) {
						if(arr[i][j]!=0) map.put(arr[i][j], map.getOrDefault(arr[i][j], 0)+1);
					}
					pq = new PriorityQueue<>();
					for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
						Integer key = entry.getKey();
						Integer val = entry.getValue();
						pq.offer(new Pair(key, val));
					}
					
					int[] tmp = new int[100];
					int idx = 0;
					while(!pq.isEmpty()) {
						Pair p = pq.poll();
						if(idx+1 < 100) tmp[idx++] = p.num;
						if(idx+1 < 100) tmp[idx++] = p.cnt;
					}
					for (int i = 0; i < 100; i++) arr[i][j] = tmp[i];
					tmpR = Math.max(tmpR, idx);
				}
				R = tmpR;
			}
			time++;
		}
		System.out.println(-1);
	}
}