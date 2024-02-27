import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static int N, d, k, c;
	static int[] sushi;
	static int[] vari = new int[3001];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		sushi = new int[N+k-1];
		for (int i = 0; i < N; i++) sushi[i] = Integer.parseInt(br.readLine());
		for (int i = 0; i < k-1; i++) sushi[N+i] = sushi[i];
		
		int cnt = 0, ANS = 0;
		for (int i = 0; i < k; i++) {
			if(vari[sushi[i]]==0) cnt++;
			vari[sushi[i]]++;
		}
		if(vari[c]==0) ANS = cnt + 1;
		else ANS = cnt;
		
		for (int i = 0; i < N-1; i++) {
			vari[sushi[i]]--;
			if(vari[sushi[i]]==0) cnt--;
			if(vari[sushi[i+k]]==0) cnt++;
			vari[sushi[i+k]]++;
			if(vari[c]==0) ANS = Math.max(cnt+1, ANS);
			else ANS = Math.max(cnt, ANS);
		}
		System.out.println(ANS);
		
	}
}