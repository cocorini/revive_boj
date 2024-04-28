import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] order_list;
	static class OpenDoors {
		int a, b;
		public OpenDoors(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}
	static OpenDoors door;
	static int ANS = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		door = new OpenDoors(0, 0);
		door.a = Integer.parseInt(st.nextToken());
		door.b = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		order_list = new int[Integer.parseInt(st.nextToken())];
		for (int i = 0; i < order_list.length; i++) 
			order_list[i] = Integer.parseInt(br.readLine());
		recur(0, 0);
		System.out.println(ANS);
	}

	private static void recur(int cnt, int move_sum) {
		// 프루닝
		if(move_sum>=ANS) return;
		// 종료 조건
		if(cnt==order_list.length) {
			ANS = move_sum;
			return;
		}
		int order = order_list[cnt];
		if(door.a==order || door.b==order) {
			recur(cnt+1, move_sum);
		} else {
			int tmp = door.a;
			door.a = order;
			recur(cnt+1, move_sum+Math.abs(tmp-order));
			door.a = tmp;
			
			tmp = door.b;
			door.b = order;
			recur(cnt+1, move_sum+Math.abs(tmp-order));
			door.b = tmp;
		}
	}
}