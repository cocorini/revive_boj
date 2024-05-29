import java.io.*;
import java.util.*;

public class Main {
	static int N, lines[], dp[];
	static Map<Integer, Integer> index = new HashMap<>(), ordered_lines = new TreeMap<>();
	static List<Integer> arr = new ArrayList<>();
	static List<Integer> ordered_linelist;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			index.put(e, s);
			ordered_lines.put(s, e);
		}
		
		ordered_linelist = new ArrayList<>(ordered_lines.values());
		dp = new int[ordered_linelist.size()];
		arr.add(ordered_linelist.get(0));
		dp[0] = 0;
		
		int CNT = 0;
		for (int i = 1; i < ordered_linelist.size(); i++) {
			int idx;
			if(arr.get(arr.size()-1)<ordered_linelist.get(i)) {
				arr.add(ordered_linelist.get(i));
				idx = arr.size()-1;
			}
			else idx = binary_search(i);
			dp[i] = idx;
			CNT = Math.max(CNT, idx);
		}
		
		StringBuilder sb = new StringBuilder();
		List<Integer> delete_lines = new ArrayList<>();
		for (int i = dp.length-1, cnt = CNT; i >= 0; i--) {
			if(cnt==dp[i]) cnt--;
			else delete_lines.add(index.get(ordered_linelist.get(i)));
		}
		Collections.reverse(delete_lines);
		sb.append(delete_lines.size()+"\n");
		for (int i = 0; i < delete_lines.size(); i++) sb.append(delete_lines.get(i)+"\n");
		System.out.println(sb);
	}

	private static int binary_search(int i) {
		int target = ordered_linelist.get(i);
		int x = Collections.binarySearch(arr, target);
		if(x<0) x = -x-1;
		arr.set(x, target);
		return x;
	}
}