import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] arr;
	static List<Integer> list = new LinkedList<>();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		input();
		findInvSequence();
		
	}
	
	private static void findInvSequence() {
		for (int i = 0; i < N; i++) {
			int idx = arr[i];
			list.add(list.size()-idx, i+1);
		}
		StringBuilder sb = new StringBuilder();
		while(!list.isEmpty()) sb.append(list.remove(0)+" ");
		System.out.println(sb);
	}

	private static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
	}
}