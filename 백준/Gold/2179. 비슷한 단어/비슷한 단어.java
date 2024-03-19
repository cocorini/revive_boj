import java.util.*;
import java.io.*;

// 왜이렇게 어렵게 생각했지? 시간 N=20000에 100자 이하라 안되는 케이스 있을 것이라고 생각했음..
public class Main {
	static StringTokenizer st;
	static int N, ANS = -1;
	static String S="", T="";
	static String[] words;
	static HashMap<String, Integer> map = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		words = new String[N];
		
		for (int i = 0; i < N; i++) {
			words[i] = br.readLine();
			map.put(words[i], i);
		}
		
		Arrays.sort(words, (o1, o2) -> Integer.compare(o1.length(), o2.length()));
		
		for (int i = 0; i < N - 1; i++) {
			String s = words[i];
			for (int j = i + 1; j < N; j++) {
				String t = words[j];
				// 공통된 단어 체크하기
				int cnt = 0;
				for (int l = 0; l < s.length(); l++) {
					if(s.charAt(l)==t.charAt(l)) cnt++;
                    else break;
				}
				// ANS가 갱신되어야 할 때
				if(ANS<cnt) {
					ANS = cnt;
					if(map.get(s)<=map.get(t)) {
						S = s;
						T = t;
					}else {
						S = t;
						T = s;
					}
				}else if(ANS==cnt) {
					String tmpS = s, tmpT = t;
					if(map.get(s)>map.get(t)) {
						tmpS = t;
						tmpT = s;
					}
					if(map.get(tmpS)<map.get(S)) {
						S = tmpS;
						T = tmpT;
					}else if(map.get(tmpS)==map.get(S)) {
						if(map.get(tmpT)<map.get(T)) {
							T = tmpT;
						}
					}
				}
			}
		}
		System.out.println(S+"\n"+T);
	}
}