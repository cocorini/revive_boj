import java.io.*;
import java.util.*;

public class Main {
	private static int H, W, maxH = 0, maxHidx = -1;
	private static StringTokenizer st;
	private static int[] block;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		block = new int[W];
		for (int i = 0; i < W; i++) {
			block[i] = Integer.parseInt(st.nextToken());
			if(maxH<=block[i]) {
				maxH = block[i];
				maxHidx = i;
			}
		}
		
		int idx = 0;
		int start = idx;
		int ans = 0;
		int h = block[start];
		while(idx<=maxHidx) {
			if(block[idx]>=h) {
				if(h==0) {
					start = idx++;
					h = block[start];
					continue;
				}
				for (int i = idx - 1; i > start; i--) ans+=h-block[i];
				start = idx;
				h = block[start];
			}
			idx++;
		}
		idx = W-1;
		start = idx;
		h = block[start];
		while(maxHidx<=idx){
			if(block[idx]>=h) {
				if(h==0) {
					start = idx--;
					h = block[start];
					continue;
				}
				for (int i = idx + 1; i < start; i++) ans+=h-block[i];
				start = idx;
				h = block[start];
			}
			idx--;
		}
		System.out.println(ans);
	}
}