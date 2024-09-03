import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder str = new StringBuilder(), subStr = new StringBuilder();
	static Stack<Character> st = new Stack<Character>() {
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (char ch : st) sb.append(ch);
			return sb.toString();
		}
	};
	
	public static void main(String[] args) throws IOException{
		str.append(br.readLine());
		subStr.append(br.readLine());
		bombString();
	}

	private static void bombString() {
		// 1. str를 stack에 계속 쌓기
		// m i r k o v C 4
		// 2. 최악 10^6*36 O(NM)
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			st.add(ch);
			if(st.size()>=subStr.length()) {
				int tmp_len = 0;
				boolean flag = true;
				for (int j = st.size()-1, k = subStr.length()-1; k >= 0; j--, k--) {
					if(st.get(j)!=subStr.charAt(k)) {
						flag = false;
						break;
					} else tmp_len++;
				}
				if(flag)
					for (int j = 0; j < tmp_len; j++) st.pop();
			}	
		}
		if(st.isEmpty()) System.out.println("FRULA");
		else System.out.println(st.toString());
	}
}
