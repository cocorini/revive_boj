import java.io.*;
import java.util.*;

public class Main {
	static int X, Y;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		if(X==Y) {
			System.out.println(0);
			return;
		}
		
		long diff = Y-X;
		long idx = 0;
		for(long i=1;i*i<=diff;i++) idx = i;
		long date = idx*2-1;
		
		long gap = idx*idx;
		for (long i = idx; i >= 1; i--) {
			if(gap+i<=diff) {
				gap+=i;
				date++;
				i++;
			}
		}
		System.out.println(date);
	}
}