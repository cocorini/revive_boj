import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] table = new int[1000002];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			table[s] += 1;
			table[e+1] -= 1;
		}
		for (int i = 1; i <= 1000000; i++) table[i] += table[i-1];
		
		st = new StringTokenizer(br.readLine());
		int Q = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Q; i++)
			sb.append(table[Integer.parseInt(st.nextToken())]+"\n");
		System.out.println(sb);
	}
}