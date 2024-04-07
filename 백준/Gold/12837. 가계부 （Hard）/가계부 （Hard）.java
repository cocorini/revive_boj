import java.io.*;
import java.util.*;

public class Main {
	static StringTokenizer st;
	static int N, Q;
	static long[] tree;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		tree = new long[N*4];
		
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int val = Integer.parseInt(st.nextToken());
			long p = Long.parseLong(st.nextToken());
			long q = Long.parseLong(st.nextToken());
			
			if(val==1) {
				update(1, N, 1, p, q);
			}else {
				sb.append(sum(1, N, 1, p, q)).append("\n");
			}
		}
		System.out.println(sb);
	}

	private static void update(int start, int end, int node, long idx, long val) {
		if(start>idx || end < idx) return;
		
		if(start == end) {
			tree[node] += val;
			return;
		}
		
		update(start, (start+end)/2, node*2, idx, val);
		update((start+end)/2+1, end, node*2+1, idx, val);
		tree[node] = tree[node*2] + tree[node*2+1];
	}
	
	private static long sum(int start, int end, int node, long left, long right) {
        if (start > right || end < left) {
            return 0;
        }
        if (start >= left && end <= right) {
            return tree[node];
        }
        return sum(start, (start + end) / 2, node * 2, left, right) + sum((start + end) / 2 + 1, end, node * 2 + 1, left, right);
    }
}