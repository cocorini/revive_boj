import java.io.*;
import java.util.*;

public class Main {

	public static int N;
	public static int[] tower;
	public static Stack<Pair> st;
	
	public static class Pair {
		int val, index;
		public Pair(int val, int index) {
			this.val = val;
			this.index = index;
		}
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		tower = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			tower[i] = Integer.parseInt(st.nextToken());
		
		findReceivedTower();
	}

	private static void findReceivedTower() {
		
		StringBuilder sb = new StringBuilder();
		st = new Stack();
		st.add(new Pair(tower[0], 0));
		sb.append("0 ");
		for (int i = 1; i < N; i++) {
			if(st.firstElement().val<=tower[i]) {
				st.clear();
				sb.append("0 ");
				st.add(new Pair(tower[i], i));
			} else {
				while(!st.isEmpty()) {
					if(st.peek().val <= tower[i]) st.pop();
					else {
						sb.append(st.peek().index+1+" ");
						st.add(new Pair(tower[i], i));
						break;
					}
				}
			}
		}
		System.out.println(sb);
	}
}