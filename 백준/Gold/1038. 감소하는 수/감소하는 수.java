import java.util.*;
import java.io.*;

class Main {
    static int N;
    static Set<Long> set = new TreeSet<>();
    
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        
        for (int ans = 0; ans < 10; ans++) recur(0, 0, ans);
        
        int idx = 0;
        for (long num : set) {
			if(idx==N) {
				System.out.println(num);
				return;
			}
			idx++;
		}
        System.out.println(-1);
    }
    
    private static void recur(int cnt, int n, long ans) {
    	if(cnt==10) {
    		return;
    	}
    	set.add(ans);
    	
    	int lastNum = (int)ans%10;
    	
    	for (int i = 0; i < 10; i++) {
			if(lastNum > i) recur(cnt+1, i, ans*10+i);
		}
    }
}