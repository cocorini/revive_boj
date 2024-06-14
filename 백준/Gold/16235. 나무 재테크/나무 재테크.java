import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K;
	static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1}, dc = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[][] A, food;
	static PriorityQueue<Integer>[][] treeAge;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = new int[N][N];
		food = new int[N][N];
		treeAge = new PriorityQueue[N][N];
		
		// A배열 채우기 - 기계가 겨울마다 추가하는 양분의 값
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				food[i][j] = 5; // 초기 양분값
				treeAge[i][j] = new PriorityQueue<>();
			}
		}
		
		// 처음 시점에서 주어지는 나무들의 정보를 받아서 treeAge 배열에 삽입
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int age = Integer.parseInt(st.nextToken());
			treeAge[r][c].offer(age);
		}
		
		simulation();
	}

	private static void simulation() {
		int year = 0;
		while(year++<K) {
//			System.out.println(year+"년");
			int[][] addFoodInSummer = seasonSpring();
			seasonSummer(addFoodInSummer);
			seasonFall();
			seasonWinter();
		}
		int ans = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				ans += treeAge[i][j].size();
		System.out.println(ans);
	}

	private static void seasonWinter() {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				food[i][j] += A[i][j];
	}

	private static void seasonFall() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(treeAge[i][j].isEmpty()) continue;
				PriorityQueue<Integer> tmpPQ = new PriorityQueue<>();
				while(!treeAge[i][j].isEmpty()) {
					int age = treeAge[i][j].poll(); // 다시 넣어줘야함!
					tmpPQ.offer(age);
					// 번식
					if(age%5==0) {
						for (int d = 0; d < 8; d++) {
							int ni = i + dr[d];
							int nj = j + dc[d];
							if(isIn(ni, nj)) treeAge[ni][nj].offer(1);
						}
					}
				}
				treeAge[i][j] = tmpPQ;
			}
		}
	}

	private static boolean isIn(int ni, int nj) {
		return ni<N && nj<N && ni>=0 && nj>=0;
	}

	private static void seasonSummer(int[][] addFood) {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				food[i][j] += addFood[i][j];
	}

	private static int[][] seasonSpring() {
		int[][] addFood = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(treeAge[i][j].isEmpty()) continue;
				PriorityQueue<Integer> tmpPQ = new PriorityQueue<>();
				while(!treeAge[i][j].isEmpty()) {
					int age = treeAge[i][j].poll();
					if(food[i][j]>=age) {
						food[i][j]-=age;
						tmpPQ.offer(age+1);
					} else addFood[i][j]+=age/2;
				}
				treeAge[i][j] = tmpPQ;
			}
		}
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(food[i]));
//		}
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(treeAge[i][j].size()+" ");
//			}
//			System.out.println();
//		}
		
		return addFood;
	}
}