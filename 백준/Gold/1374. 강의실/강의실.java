import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PriorityQueue<Lecture> pq = new PriorityQueue<Lecture>(
			new Comparator<Lecture>() {
				@Override
				public int compare(Lecture o1, Lecture o2) {
					if(o1.start==o2.start) return Integer.compare(o1.end, o2.end);
					return Integer.compare(o1.start, o2.start);
				};
	});
	static PriorityQueue<Integer> endTimeQueue = new PriorityQueue<Integer>();
	
	static class Lecture {
		int idx, start, end;
		public Lecture(int idx, int start, int end) {
			this.idx = idx;
			this.start = start;
			this.end = end;
		}
	}
	
	public static void main(String[] args) throws IOException{
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken())-1;
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			pq.offer(new Lecture(idx, start, end));
		}
		
		int ans = 0;
		
		a: while(!pq.isEmpty()) {
			Lecture lecture = pq.poll();
			
			while (!endTimeQueue.isEmpty() && endTimeQueue.peek()<=lecture.start){
				endTimeQueue.poll();
            }
			endTimeQueue.offer(lecture.end);
            ans = Math.max(ans, endTimeQueue.size());
		}
		
		System.out.println(ans);
	}

}