import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static Map<String, Info> timetable = new HashMap<>();
	static class Info {
		int day, cost;
		public Info(int day, int cost) {
			this.day = day;
			this.cost = cost;
		}
	}
	static boolean[] date_arr = new boolean[100];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int W = Integer.parseInt(st.nextToken()), D = Integer.parseInt(st.nextToken()), P = Integer.parseInt(st.nextToken());	
			timetable.put(name, new Info(7*W+D, P));
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int have_money = Integer.parseInt(st.nextToken());
			Info I = timetable.get(name);
			if(have_money>=I.cost)
				date_arr[I.day] = true;
		}
		int max_date = 0;
		for (int i = 0; i < 100; i++) {
			if(date_arr[i]) {
				int tmp_date = 0;
				for (int j = i; j < 100; j++) {
					if(!date_arr[j]) {
						i = j-1;
						break;
					}
					tmp_date++;
				}
				max_date = Math.max(max_date, tmp_date);
			}
		}
		System.out.println(max_date);
	}
}