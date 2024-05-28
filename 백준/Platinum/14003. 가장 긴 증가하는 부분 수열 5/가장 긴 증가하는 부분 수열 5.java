import java.io.*;
import java.util.*;

public class Main {
	static int N, arr[], memory[];
	static List<Integer> table = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		arr = new int[N];
		memory = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		table.add(arr[0]);
		memory[0] = 0;
		
		int idx = 1;
		for (int i = 1; i < N; i++) {
			if(arr[i]>table.get(idx-1)) {
				table.add(arr[i]);
				memory[i] = idx;
				idx++;
			} else
				binary_search(i);
		}
		
		int point = table.size()-1;
		List<Integer> ans = new ArrayList<>();
		for (int i = N-1; i >= 0; i--) {
			if(memory[i]==point) {
				point--;
				ans.add(arr[i]);
			}
		}
		Collections.reverse(ans);
		StringBuilder sb = new StringBuilder();
		sb.append(table.size()+"\n");
		for (int i = 0; i < table.size(); i++)
			sb.append(ans.get(i)+" ");
		System.out.println(sb);
	}

	private static void binary_search(int i) {
		int x = Collections.binarySearch(table, arr[i]);
		if(x < 0) x = - x - 1;
		table.set(x, arr[i]);
		memory[i] = x;
	}
}