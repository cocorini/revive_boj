import java.util.*;
import java.io.*;

public class Solution {
	static StringTokenizer st;
	static int N, M;
	static List<Integer> list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int test_case = 1; test_case <= 10; test_case++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			list = new LinkedList<>();
			for (int i = 0; i < N; i++)
				list.add(Integer.parseInt(st.nextToken()));
			M = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int idx = 0;
			while(idx<M) {
				char input = st.nextToken().charAt(0);
				if(input=='I') {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					List<Integer> tmp_list = new LinkedList<>(); 
					for (int i = 0; i < y; i++)
						tmp_list.add(Integer.parseInt(st.nextToken()));
					list.addAll(x, tmp_list);
				}else if(input=='A') {
					int y = Integer.parseInt(st.nextToken());
					List<Integer> tmp_list = new LinkedList<>(); 
					for (int i = 0; i < y; i++)
						tmp_list.add(Integer.parseInt(st.nextToken()));
					list.addAll(tmp_list);
				}else if(input=='D') {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					for (int i = 0; i < y; i++) 
						list.remove(x);
				}
				idx++;
			}
			StringBuilder sb = new StringBuilder();
			sb.append("#"+test_case+" ");
			for (int i = 0; i < 10; i++) sb.append(list.get(i)+" ");
			System.out.println(sb);
		}	
	}
}