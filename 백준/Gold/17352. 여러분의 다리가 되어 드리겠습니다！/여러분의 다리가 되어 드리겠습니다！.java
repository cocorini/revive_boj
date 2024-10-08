import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] p;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException{
		N = Integer.parseInt(br.readLine());
		
		init();
		
		for (int i = 0; i < N-2; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken())-1;
			int r = Integer.parseInt(st.nextToken())-1;
			
			union(l, r);
		}
		
		for (int i = 1; i < N; i++) {
			if(find(i)!=find(0)) {
				System.out.println(1+" "+(i+1));
				return;
			}
		}
	}
	
	private static void init() {
		p = new int[N];
		
		for (int i = 0; i < N; i++) p[i] = i;
	}
	
	private static int find(int e) {
		if(p[e]==e) return p[e];
		return p[e] = find(p[e]);
	}

	private static void union(int n, int m) {
		int pn = find(n);
		int pm = find(m);
		
		if(pn <= pm) {
			p[pm] = p[pn];
		} else {
			p[pn] = p[pm];
		}	
	}
}